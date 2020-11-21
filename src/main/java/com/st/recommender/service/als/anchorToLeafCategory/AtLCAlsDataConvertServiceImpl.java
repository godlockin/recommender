package com.st.recommender.service.als.anchorToLeafCategory;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.opt.als.AnchorLeaf;
import com.st.recommender.model.origin.Anchor;
import com.st.recommender.service.abstractgroup.AbstractDataConvertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AtLCAlsDataConvertServiceImpl extends AbstractDataConvertServiceImpl<Anchor, List<AnchorLeaf>> {
    @Override
    protected Function<Anchor, List<AnchorLeaf>> dataConverter() {
        return anchor -> {
            List<AnchorLeaf> list = new ArrayList<>();
            String anchorId = anchor.getAnchorID();
            String lcc = anchor.getTaobaoLeafCategoryCount();
            if (StringUtils.isAnyBlank(anchorId, lcc)) {
                return list;
            }

            return Arrays.stream(lcc.split(","))
                    .filter(StringUtils::isNotBlank)
                    .filter(str -> 0 < str.indexOf(':'))
                    .map(str -> {
                        String[] items = str.split(":");
                        return AnchorLeaf.builder()
                                .anchorID(anchorId)
                                .leafCategory(items[0])
                                .count(Long.parseLong(items[1]))
                                .build();
                    })
                    .filter(al -> Optional.ofNullable(al.getCount()).filter(count -> 0L < count).isPresent())
                    .collect(Collectors.toList());
        };
    }

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY; }
}
