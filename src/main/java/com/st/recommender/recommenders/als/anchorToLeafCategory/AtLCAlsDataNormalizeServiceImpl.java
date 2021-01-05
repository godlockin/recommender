package com.st.recommender.recommenders.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.constants.NormalizationEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.origin.AnchorLeafRaw;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.DataNormalizeService;
import com.st.recommender.service.abstractgroup.AbstractDataNormalizeServiceImpl;
import com.st.recommender.service.common.NormalizeFuncUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtLCAlsDataNormalizeServiceImpl extends AbstractDataNormalizeServiceImpl<List<List<AnchorLeafRaw>>, List<List<ScoreModel>>> implements DataNormalizeService<List<List<AnchorLeafRaw>>, List<List<ScoreModel>>> {

    @Override
    protected BiFunction<Param, Object, List<List<ScoreModel>>> dataNormalizer() {
        return (param, obj) -> {

            List<List<ScoreModel>> result = Collections.synchronizedList(new ArrayList<>());

            NormalizationEnum normalizationEnum = findNormalizationFunc().apply(param);
            Function<List<MutablePair<String, Double>>, Map<String, List<MutablePair<String, Double>>>> itemNormalization
                    = NormalizeFuncUtils.findItemsNormalizeFunc(normalizationEnum);

            Map<String, List<AnchorLeafRaw>> anchorLeafMap = ((List<List<AnchorLeafRaw>>) obj)
                    .stream().flatMap(Collection::stream)
                            .collect(Collectors.groupingBy(AnchorLeafRaw::getAnchorID));

            anchorLeafMap.entrySet().parallelStream()
                    .map(e -> {
                        String anchorId = e.getKey();
                        List<AnchorLeafRaw> anchorLeaves = e.getValue();

                        List<MutablePair<String, Double>> scoreList = anchorLeaves.stream()
                                .map(anchorLeaf -> MutablePair.of(anchorLeaf.getLeafCategory(), anchorLeaf.getCount().doubleValue()))
                                .collect(Collectors.toList());

                        Map<String, List<MutablePair<String, Double>>> normalizedScoreMap = itemNormalization.apply(scoreList);

                        return normalizedScoreMap.values().stream()
                                .flatMap(Collection::stream)
                                .map(pair -> ScoreModel.builder().userId(anchorId).itemId(pair.getKey()).score(pair.getValue()).build())
                                .collect(Collectors.toList());
                    }).forEach(result::add);
            return result;
        };
    }

    @Override
    public AlgorithmEnum algorithm() {
        return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY;
    }

}
