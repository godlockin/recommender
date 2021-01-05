package com.st.recommender.db.entity.dy;

import java.io.Serializable;

public class DYData implements Serializable {
    private Integer followerCount;

    private Integer liveHour;

    private Integer userPeak;

    private Integer giftUvCount;

    private Integer roomTicketCount;

    private Integer totalUser;

    private Integer productCount;

    private Double itemAmount;

    private Integer itemVolume;

    private Double finalPrice;

    private Integer commissionRate;

    private Long shelfTimes;

    private Integer itemTop1Gender;

    private Integer itemTop2Gender;

    private String itemTop1Age;

    private String itemTop2Age;

    private String itemTop3Age;

    private Integer top1Gender;

    private Integer top2Gender;

    private Double cityRate;

    private Double myfollowRate;

    private Double otherRate;

    private Double videoRate;

    private String top1Age;

    private String top2Age;

    private String top3Age;

    private Integer maxItemVolume;

    private Long maxShelfTimes;

    private Double labelTmp;

    private static final long serialVersionUID = 1L;

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getLiveHour() {
        return liveHour;
    }

    public void setLiveHour(Integer liveHour) {
        this.liveHour = liveHour;
    }

    public Integer getUserPeak() {
        return userPeak;
    }

    public void setUserPeak(Integer userPeak) {
        this.userPeak = userPeak;
    }

    public Integer getGiftUvCount() {
        return giftUvCount;
    }

    public void setGiftUvCount(Integer giftUvCount) {
        this.giftUvCount = giftUvCount;
    }

    public Integer getRoomTicketCount() {
        return roomTicketCount;
    }

    public void setRoomTicketCount(Integer roomTicketCount) {
        this.roomTicketCount = roomTicketCount;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getItemVolume() {
        return itemVolume;
    }

    public void setItemVolume(Integer itemVolume) {
        this.itemVolume = itemVolume;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Integer commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Long getShelfTimes() {
        return shelfTimes;
    }

    public void setShelfTimes(Long shelfTimes) {
        this.shelfTimes = shelfTimes;
    }

    public Integer getItemTop1Gender() {
        return itemTop1Gender;
    }

    public void setItemTop1Gender(Integer itemTop1Gender) {
        this.itemTop1Gender = itemTop1Gender;
    }

    public Integer getItemTop2Gender() {
        return itemTop2Gender;
    }

    public void setItemTop2Gender(Integer itemTop2Gender) {
        this.itemTop2Gender = itemTop2Gender;
    }

    public String getItemTop1Age() {
        return itemTop1Age;
    }

    public void setItemTop1Age(String itemTop1Age) {
        this.itemTop1Age = itemTop1Age == null ? null : itemTop1Age.trim();
    }

    public String getItemTop2Age() {
        return itemTop2Age;
    }

    public void setItemTop2Age(String itemTop2Age) {
        this.itemTop2Age = itemTop2Age == null ? null : itemTop2Age.trim();
    }

    public String getItemTop3Age() {
        return itemTop3Age;
    }

    public void setItemTop3Age(String itemTop3Age) {
        this.itemTop3Age = itemTop3Age == null ? null : itemTop3Age.trim();
    }

    public Integer getTop1Gender() {
        return top1Gender;
    }

    public void setTop1Gender(Integer top1Gender) {
        this.top1Gender = top1Gender;
    }

    public Integer getTop2Gender() {
        return top2Gender;
    }

    public void setTop2Gender(Integer top2Gender) {
        this.top2Gender = top2Gender;
    }

    public Double getCityRate() {
        return cityRate;
    }

    public void setCityRate(Double cityRate) {
        this.cityRate = cityRate;
    }

    public Double getMyfollowRate() {
        return myfollowRate;
    }

    public void setMyfollowRate(Double myfollowRate) {
        this.myfollowRate = myfollowRate;
    }

    public Double getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(Double otherRate) {
        this.otherRate = otherRate;
    }

    public Double getVideoRate() {
        return videoRate;
    }

    public void setVideoRate(Double videoRate) {
        this.videoRate = videoRate;
    }

    public String getTop1Age() {
        return top1Age;
    }

    public void setTop1Age(String top1Age) {
        this.top1Age = top1Age == null ? null : top1Age.trim();
    }

    public String getTop2Age() {
        return top2Age;
    }

    public void setTop2Age(String top2Age) {
        this.top2Age = top2Age == null ? null : top2Age.trim();
    }

    public String getTop3Age() {
        return top3Age;
    }

    public void setTop3Age(String top3Age) {
        this.top3Age = top3Age == null ? null : top3Age.trim();
    }

    public Integer getMaxItemVolume() {
        return maxItemVolume;
    }

    public void setMaxItemVolume(Integer maxItemVolume) {
        this.maxItemVolume = maxItemVolume;
    }

    public Long getMaxShelfTimes() {
        return maxShelfTimes;
    }

    public void setMaxShelfTimes(Long maxShelfTimes) {
        this.maxShelfTimes = maxShelfTimes;
    }

    public Double getLabelTmp() {
        return labelTmp;
    }

    public void setLabelTmp(Double labelTmp) {
        this.labelTmp = labelTmp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", followerCount=").append(followerCount);
        sb.append(", liveHour=").append(liveHour);
        sb.append(", userPeak=").append(userPeak);
        sb.append(", giftUvCount=").append(giftUvCount);
        sb.append(", roomTicketCount=").append(roomTicketCount);
        sb.append(", totalUser=").append(totalUser);
        sb.append(", productCount=").append(productCount);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", itemVolume=").append(itemVolume);
        sb.append(", finalPrice=").append(finalPrice);
        sb.append(", commissionRate=").append(commissionRate);
        sb.append(", shelfTimes=").append(shelfTimes);
        sb.append(", itemTop1Gender=").append(itemTop1Gender);
        sb.append(", itemTop2Gender=").append(itemTop2Gender);
        sb.append(", itemTop1Age=").append(itemTop1Age);
        sb.append(", itemTop2Age=").append(itemTop2Age);
        sb.append(", itemTop3Age=").append(itemTop3Age);
        sb.append(", top1Gender=").append(top1Gender);
        sb.append(", top2Gender=").append(top2Gender);
        sb.append(", cityRate=").append(cityRate);
        sb.append(", myfollowRate=").append(myfollowRate);
        sb.append(", otherRate=").append(otherRate);
        sb.append(", videoRate=").append(videoRate);
        sb.append(", top1Age=").append(top1Age);
        sb.append(", top2Age=").append(top2Age);
        sb.append(", top3Age=").append(top3Age);
        sb.append(", maxItemVolume=").append(maxItemVolume);
        sb.append(", maxShelfTimes=").append(maxShelfTimes);
        sb.append(", labelTmp=").append(labelTmp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}