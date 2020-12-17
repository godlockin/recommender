package com.st.recommender.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class AlgorithmMapping {
        public static Map<BaseEnum, BaseEnum> MAPPING = new HashMap<>();

        static {
            // als
            // user base
            MAPPING.put(AlgorithmEnum.ANCHOR_TO_LEAF_CATEGORY, AlgorithmEnum.ALS);
            MAPPING.put(AlgorithmEnum.SINGLE_ANCHOR_TO_LEAF_CATEGORY, AlgorithmEnum.ALS);

            // item base
            MAPPING.put(AlgorithmEnum.LEAF_TO_ANCHOR_CATEGORY, AlgorithmEnum.ALS);

            // label base
            MAPPING.put(AlgorithmEnum.LABEL_SIMPLE_SIM, AlgorithmEnum.LABEL_BASE);
        }

        private AlgorithmMapping() {
        }
    }
}
