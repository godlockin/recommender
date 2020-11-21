package com.st.recommender.model.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anchor {
    private String id;
    private String anchorID;
    private String anchorName;
    private String anchorType;
    private double avgPraiseCount;
    private long avgPV;
    private long fansNum;
    private long itemCount;
    private long liveCount;
    private String liveDurationCount;
    private String liveDurationRate;
    private String priceCount;
    private String priceRate;
    private long shopCount;
    private String speakTAORootCategory;
    private String startHourCount;
    private String startHourRate;
    private String taobaoLeafCategoryCount;
    private String taobaoLeafCategoryRate;
    private String taobaoRootCategoryCount;
    private String taobaoRootCategoryRate;
    private String taobaoRootCategorySpeakTimeRealsale;
}
