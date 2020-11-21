package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.opt.als.AnchorLeaf;
import com.st.recommender.model.opt.als.ScoreModel;
import com.st.recommender.service.abstractgroup.AbstractDataNormalizeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtLCAlsDataNormalizeServiceImpl extends AbstractDataNormalizeServiceImpl<List<AnchorLeaf>, List<ScoreModel>> {

    @Override
    protected Function<List<AnchorLeaf>, List<ScoreModel>> dataNormalizer() {
        return list -> list.stream()
                .filter(al -> StringUtils.isNoneBlank(al.getAnchorID(), al.getLeafCategory()))
                .filter(al -> Optional.ofNullable(al.getCount()).filter(count -> 0L < count).isPresent())
                .map(al -> ScoreModel.builder()
                        .userId(al.getAnchorID())
                        .itemId(al.getLeafCategory())
                        .score(al.getCount().doubleValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }

}
