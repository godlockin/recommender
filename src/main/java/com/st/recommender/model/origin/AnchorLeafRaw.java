package com.st.recommender.model.origin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnchorLeafRaw {
    private String anchorID;
    private String leafCategory;
    private Long count;
}
