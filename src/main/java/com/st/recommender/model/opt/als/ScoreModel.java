package com.st.recommender.model.opt.als;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreModel {
    private String userId;
    private String itemId;
    private Double score;
}
