package com.st.recommender.model.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveRaw {
    private String id;
    private String anchorID;
    private String anchorName;
    private long channelID;
    private long fansAdd;
    private long itemCount;
    private long labelID;
    private long liveDuration;
    private long liveID;
    private String liveTitle;
    private String location;
    private long praiseCount;
    private long pv;
    private double pvPerHour;
    private long shopCount;
    private long startTime;
}
