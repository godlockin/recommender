package com.st.recommender.model.opt.labelbase;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.List;

@Data
@Builder
public class SeedProfile {
    private String id;
    private List<MutablePair<Long, Long>> items;
}
