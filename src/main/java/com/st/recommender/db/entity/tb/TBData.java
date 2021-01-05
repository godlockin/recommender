package com.st.recommender.db.entity.tb;

import java.io.Serializable;

public class TBData implements Serializable {
    private Double fansNums;

    private Double pvAverage;

    private Double goodsAverage;

    private Double livehourAverage;

    private Double pv;

    private Double liveDuration;

    private Double shelfTimes;

    private Double speakTimes;

    private Double livePrice;

    private Double historyPrice;

    private Double realPrice;

    private Double allLiveRealsale;

    private Double speakTimeRealsale;

    private Double malePercent;

    private Double femalePercent;

    private static final long serialVersionUID = 1L;

    public Double getFansNums() {
        return fansNums;
    }

    public void setFansNums(Double fansNums) {
        this.fansNums = fansNums;
    }

    public Double getPvAverage() {
        return pvAverage;
    }

    public void setPvAverage(Double pvAverage) {
        this.pvAverage = pvAverage;
    }

    public Double getGoodsAverage() {
        return goodsAverage;
    }

    public void setGoodsAverage(Double goodsAverage) {
        this.goodsAverage = goodsAverage;
    }

    public Double getLivehourAverage() {
        return livehourAverage;
    }

    public void setLivehourAverage(Double livehourAverage) {
        this.livehourAverage = livehourAverage;
    }

    public Double getPv() {
        return pv;
    }

    public void setPv(Double pv) {
        this.pv = pv;
    }

    public Double getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(Double liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Double getShelfTimes() {
        return shelfTimes;
    }

    public void setShelfTimes(Double shelfTimes) {
        this.shelfTimes = shelfTimes;
    }

    public Double getSpeakTimes() {
        return speakTimes;
    }

    public void setSpeakTimes(Double speakTimes) {
        this.speakTimes = speakTimes;
    }

    public Double getLivePrice() {
        return livePrice;
    }

    public void setLivePrice(Double livePrice) {
        this.livePrice = livePrice;
    }

    public Double getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(Double historyPrice) {
        this.historyPrice = historyPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getAllLiveRealsale() {
        return allLiveRealsale;
    }

    public void setAllLiveRealsale(Double allLiveRealsale) {
        this.allLiveRealsale = allLiveRealsale;
    }

    public Double getSpeakTimeRealsale() {
        return speakTimeRealsale;
    }

    public void setSpeakTimeRealsale(Double speakTimeRealsale) {
        this.speakTimeRealsale = speakTimeRealsale;
    }

    public Double getMalePercent() {
        return malePercent;
    }

    public void setMalePercent(Double malePercent) {
        this.malePercent = malePercent;
    }

    public Double getFemalePercent() {
        return femalePercent;
    }

    public void setFemalePercent(Double femalePercent) {
        this.femalePercent = femalePercent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fansNums=").append(fansNums);
        sb.append(", pvAverage=").append(pvAverage);
        sb.append(", goodsAverage=").append(goodsAverage);
        sb.append(", livehourAverage=").append(livehourAverage);
        sb.append(", pv=").append(pv);
        sb.append(", liveDuration=").append(liveDuration);
        sb.append(", shelfTimes=").append(shelfTimes);
        sb.append(", speakTimes=").append(speakTimes);
        sb.append(", livePrice=").append(livePrice);
        sb.append(", historyPrice=").append(historyPrice);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", allLiveRealsale=").append(allLiveRealsale);
        sb.append(", speakTimeRealsale=").append(speakTimeRealsale);
        sb.append(", malePercent=").append(malePercent);
        sb.append(", femalePercent=").append(femalePercent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}