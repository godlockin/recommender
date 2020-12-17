package com.st.recommender.recommenders.label.LabelSimpleSim;

import com.st.recommender.constants.AlgorithmEnum;
import com.st.recommender.model.input.Param;
import com.st.recommender.model.opt.labelbase.SeedProfile;
import com.st.recommender.service.DataNormalizeService;
import com.st.recommender.service.abstractgroup.AbstractDataNormalizeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Slf4j
@Service
public class LssDataNormalizeServiceImpl extends AbstractDataNormalizeServiceImpl<Map<Class, List>, Map<Class, List>> implements DataNormalizeService<Map<Class, List>, Map<Class, List>> {

    @Override
    public AlgorithmEnum algorithm() { return AlgorithmEnum.LABEL_SIMPLE_SIM; }

    @Override
    protected BiFunction<Param, Object, Map<Class, List>> dataNormalizer() {
        return (param, obj) -> {
            List<SeedProfile> seedProfiles = ((Map<Class, List>) obj).get(SeedProfile.class);
            seedProfiles.parallelStream().forEach(profile -> profile.getItems().sort((p1, p2) -> p2.getValue().compareTo(p1.getValue())));
            return (Map<Class, List>) obj;
        };
    }
}
