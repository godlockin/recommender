package com.st.recommender.recommenders.als.anchorToLeafCategory;

import com.st.recommender.common.utils.DataUtils;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.model.origin.AnchorRaw;
import com.st.recommender.model.origin.LiveRaw;
import com.st.recommender.model.origin.ProductRaw;
import com.st.recommender.service.DataConvertService;
import com.st.recommender.service.abstractgroup.AbstractDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtLCAlsDataConvertServiceImpl extends AbstractDataConvertServiceImpl<Map<Class, List>, List> implements DataConvertService<Map<Class, List>, List> {

    @Override
    protected Function<Map<Class, List>, List> dataConverter() {
        return map -> {
            List<List<AnchorLeafRaw>> resultList = new ArrayList<>();

            List<LiveRaw> lives = map.get(LiveRaw.class);
            List<AnchorRaw> anchorRaw = map.get(AnchorRaw.class);
            List<ProductRaw> productRaw = map.get(ProductRaw.class);
            List<AnchorLeafRaw> anchorLeaves = map.get(AnchorLeafRaw.class);
            if (DataUtils.isAllCollectionsEmpty(lives, anchorRaw, productRaw, anchorLeaves)) {
                return resultList;
            }

            if (!CollectionUtils.isEmpty(anchorLeaves)) {
                return Collections.singletonList(anchorLeaves);
            }

            if (DataUtils.isAnyCollectionEmpty(lives, anchorRaw, productRaw)) {
                return resultList;
            }

            Map<String, Long> anchorToLive = lives.stream().collect(Collectors.toMap(LiveRaw::getAnchorID, LiveRaw::getLiveID));
            Map<Long, List<ProductRaw>> liveToProduct = productRaw.stream().collect(Collectors.groupingBy(ProductRaw::getLiveID));
            Map<String, Map<String, List<ProductRaw>>> anchorToProduct = anchorToLive.entrySet().stream()
                    .map(e -> MutablePair.of(e.getKey(), liveToProduct.getOrDefault(e.getValue(), new ArrayList<>())))
                    .filter(pair -> !CollectionUtils.isEmpty(pair.getValue()))
                    .map(pair -> MutablePair.of(pair.getKey(), pair.getValue().stream().collect(Collectors.groupingBy(ProductRaw::getBrandID))))
                    .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));

            resultList.add(anchorToProduct.entrySet().stream()
                    .map(e -> e.getValue().entrySet().stream()
                            .map(se -> AnchorLeafRaw.builder().anchorID(e.getKey())
                                    .leafCategory(se.getKey())
                                    .count((long) se.getValue().size())
                                    .build()).collect(Collectors.toList()))
                    .flatMap(Collection::stream)
                    .filter(anchorLeaf -> 0L < anchorLeaf.getCount())
                    .collect(Collectors.toList()));
            return resultList;
        };
    }

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY;
    }

}
