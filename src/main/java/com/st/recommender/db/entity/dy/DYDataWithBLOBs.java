package com.st.recommender.db.entity.dy;

import java.io.Serializable;

public class DYDataWithBLOBs extends DYData implements Serializable {
    private String productId;

    private String authorId;

    private String nickname;

    private String liveLabel;

    private String uniqueId;

    private String authorTags;

    private String productTitle;

    private String category;

    private String brandName;

    private String v2CategoryBig;

    private String v2CategoryFirst;

    private String v2CategorySecond;

    private String v2CategoryThird;

    private String extInfo;

    private String subTitle;

    private String top1Category;

    private String top2Category;

    private String top3Category;

    private String itemTop1Province;

    private String itemTop2Province;

    private String itemTop3Province;

    private String top1BrandName;

    private String top2BrandName;

    private String top3BrandName;

    private String top1V2CategoryFirst;

    private String top2V2CategoryFirst;

    private String top3V2CategoryFirst;

    private String top1V2CategorySecond;

    private String top2V2CategorySecond;

    private String top3V2CategorySecond;

    private String top1Province;

    private String top2Province;

    private String top3Province;

    private String label;

    private static final long serialVersionUID = 1L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId == null ? null : authorId.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getLiveLabel() {
        return liveLabel;
    }

    public void setLiveLabel(String liveLabel) {
        this.liveLabel = liveLabel == null ? null : liveLabel.trim();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId == null ? null : uniqueId.trim();
    }

    public String getAuthorTags() {
        return authorTags;
    }

    public void setAuthorTags(String authorTags) {
        this.authorTags = authorTags == null ? null : authorTags.trim();
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getV2CategoryBig() {
        return v2CategoryBig;
    }

    public void setV2CategoryBig(String v2CategoryBig) {
        this.v2CategoryBig = v2CategoryBig == null ? null : v2CategoryBig.trim();
    }

    public String getV2CategoryFirst() {
        return v2CategoryFirst;
    }

    public void setV2CategoryFirst(String v2CategoryFirst) {
        this.v2CategoryFirst = v2CategoryFirst == null ? null : v2CategoryFirst.trim();
    }

    public String getV2CategorySecond() {
        return v2CategorySecond;
    }

    public void setV2CategorySecond(String v2CategorySecond) {
        this.v2CategorySecond = v2CategorySecond == null ? null : v2CategorySecond.trim();
    }

    public String getV2CategoryThird() {
        return v2CategoryThird;
    }

    public void setV2CategoryThird(String v2CategoryThird) {
        this.v2CategoryThird = v2CategoryThird == null ? null : v2CategoryThird.trim();
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo == null ? null : extInfo.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getTop1Category() {
        return top1Category;
    }

    public void setTop1Category(String top1Category) {
        this.top1Category = top1Category == null ? null : top1Category.trim();
    }

    public String getTop2Category() {
        return top2Category;
    }

    public void setTop2Category(String top2Category) {
        this.top2Category = top2Category == null ? null : top2Category.trim();
    }

    public String getTop3Category() {
        return top3Category;
    }

    public void setTop3Category(String top3Category) {
        this.top3Category = top3Category == null ? null : top3Category.trim();
    }

    public String getItemTop1Province() {
        return itemTop1Province;
    }

    public void setItemTop1Province(String itemTop1Province) {
        this.itemTop1Province = itemTop1Province == null ? null : itemTop1Province.trim();
    }

    public String getItemTop2Province() {
        return itemTop2Province;
    }

    public void setItemTop2Province(String itemTop2Province) {
        this.itemTop2Province = itemTop2Province == null ? null : itemTop2Province.trim();
    }

    public String getItemTop3Province() {
        return itemTop3Province;
    }

    public void setItemTop3Province(String itemTop3Province) {
        this.itemTop3Province = itemTop3Province == null ? null : itemTop3Province.trim();
    }

    public String getTop1BrandName() {
        return top1BrandName;
    }

    public void setTop1BrandName(String top1BrandName) {
        this.top1BrandName = top1BrandName == null ? null : top1BrandName.trim();
    }

    public String getTop2BrandName() {
        return top2BrandName;
    }

    public void setTop2BrandName(String top2BrandName) {
        this.top2BrandName = top2BrandName == null ? null : top2BrandName.trim();
    }

    public String getTop3BrandName() {
        return top3BrandName;
    }

    public void setTop3BrandName(String top3BrandName) {
        this.top3BrandName = top3BrandName == null ? null : top3BrandName.trim();
    }

    public String getTop1V2CategoryFirst() {
        return top1V2CategoryFirst;
    }

    public void setTop1V2CategoryFirst(String top1V2CategoryFirst) {
        this.top1V2CategoryFirst = top1V2CategoryFirst == null ? null : top1V2CategoryFirst.trim();
    }

    public String getTop2V2CategoryFirst() {
        return top2V2CategoryFirst;
    }

    public void setTop2V2CategoryFirst(String top2V2CategoryFirst) {
        this.top2V2CategoryFirst = top2V2CategoryFirst == null ? null : top2V2CategoryFirst.trim();
    }

    public String getTop3V2CategoryFirst() {
        return top3V2CategoryFirst;
    }

    public void setTop3V2CategoryFirst(String top3V2CategoryFirst) {
        this.top3V2CategoryFirst = top3V2CategoryFirst == null ? null : top3V2CategoryFirst.trim();
    }

    public String getTop1V2CategorySecond() {
        return top1V2CategorySecond;
    }

    public void setTop1V2CategorySecond(String top1V2CategorySecond) {
        this.top1V2CategorySecond = top1V2CategorySecond == null ? null : top1V2CategorySecond.trim();
    }

    public String getTop2V2CategorySecond() {
        return top2V2CategorySecond;
    }

    public void setTop2V2CategorySecond(String top2V2CategorySecond) {
        this.top2V2CategorySecond = top2V2CategorySecond == null ? null : top2V2CategorySecond.trim();
    }

    public String getTop3V2CategorySecond() {
        return top3V2CategorySecond;
    }

    public void setTop3V2CategorySecond(String top3V2CategorySecond) {
        this.top3V2CategorySecond = top3V2CategorySecond == null ? null : top3V2CategorySecond.trim();
    }

    public String getTop1Province() {
        return top1Province;
    }

    public void setTop1Province(String top1Province) {
        this.top1Province = top1Province == null ? null : top1Province.trim();
    }

    public String getTop2Province() {
        return top2Province;
    }

    public void setTop2Province(String top2Province) {
        this.top2Province = top2Province == null ? null : top2Province.trim();
    }

    public String getTop3Province() {
        return top3Province;
    }

    public void setTop3Province(String top3Province) {
        this.top3Province = top3Province == null ? null : top3Province.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", authorId=").append(authorId);
        sb.append(", nickname=").append(nickname);
        sb.append(", liveLabel=").append(liveLabel);
        sb.append(", uniqueId=").append(uniqueId);
        sb.append(", authorTags=").append(authorTags);
        sb.append(", productTitle=").append(productTitle);
        sb.append(", category=").append(category);
        sb.append(", brandName=").append(brandName);
        sb.append(", v2CategoryBig=").append(v2CategoryBig);
        sb.append(", v2CategoryFirst=").append(v2CategoryFirst);
        sb.append(", v2CategorySecond=").append(v2CategorySecond);
        sb.append(", v2CategoryThird=").append(v2CategoryThird);
        sb.append(", extInfo=").append(extInfo);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", top1Category=").append(top1Category);
        sb.append(", top2Category=").append(top2Category);
        sb.append(", top3Category=").append(top3Category);
        sb.append(", itemTop1Province=").append(itemTop1Province);
        sb.append(", itemTop2Province=").append(itemTop2Province);
        sb.append(", itemTop3Province=").append(itemTop3Province);
        sb.append(", top1BrandName=").append(top1BrandName);
        sb.append(", top2BrandName=").append(top2BrandName);
        sb.append(", top3BrandName=").append(top3BrandName);
        sb.append(", top1V2CategoryFirst=").append(top1V2CategoryFirst);
        sb.append(", top2V2CategoryFirst=").append(top2V2CategoryFirst);
        sb.append(", top3V2CategoryFirst=").append(top3V2CategoryFirst);
        sb.append(", top1V2CategorySecond=").append(top1V2CategorySecond);
        sb.append(", top2V2CategorySecond=").append(top2V2CategorySecond);
        sb.append(", top3V2CategorySecond=").append(top3V2CategorySecond);
        sb.append(", top1Province=").append(top1Province);
        sb.append(", top2Province=").append(top2Province);
        sb.append(", top3Province=").append(top3Province);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}