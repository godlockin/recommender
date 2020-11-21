package com.st.recommender.model.opt.als;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnchorLeaf {
    private String anchorID;
    private String leafCategory;
    private Long count;
}
