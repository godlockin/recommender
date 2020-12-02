package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.common.utils.DataUtils;
import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.opt.als.AnchorLeaf;
import com.st.recommender.model.origin.Anchor;
import com.st.recommender.model.origin.Live;
import com.st.recommender.model.origin.Product;
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
            List<List<AnchorLeaf>> resultList = new ArrayList<>();

            List<Live> lives = map.get(Live.class);
            List<Anchor> anchors = map.get(Anchor.class);
            List<Product> products = map.get(Product.class);
            List<AnchorLeaf> anchorLeaves = map.get(AnchorLeaf.class);
            if (DataUtils.isAllCollectionsEmpty(lives, anchors, products, anchorLeaves)) {
                return resultList;
            }

            if (!CollectionUtils.isEmpty(anchorLeaves)) {
                return Collections.singletonList(anchorLeaves);
            }

            if (DataUtils.isAnyCollectionEmpty(lives, anchors, products)) {
                return resultList;
            }

            Map<String, Long> anchorToLive = lives.stream().collect(Collectors.toMap(Live::getAnchorID, Live::getLiveID));
            Map<Long, List<Product>> liveToProduct = products.stream().collect(Collectors.groupingBy(Product::getLiveID));
            Map<String, Map<String, List<Product>>> anchorToProduct = anchorToLive.entrySet().stream()
                    .map(e -> MutablePair.of(e.getKey(), liveToProduct.getOrDefault(e.getValue(), new ArrayList<>())))
                    .filter(pair -> !CollectionUtils.isEmpty(pair.getValue()))
                    .map(pair -> MutablePair.of(pair.getKey(), pair.getValue().stream().collect(Collectors.groupingBy(Product::getBrandID))))
                    .collect(Collectors.toMap(MutablePair::getKey, MutablePair::getValue));

            resultList.add(anchorToProduct.entrySet().stream()
                    .map(e -> e.getValue().entrySet().stream()
                            .map(se -> AnchorLeaf.builder().anchorID(e.getKey())
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
    public AlgorithmEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }

}
