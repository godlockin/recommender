package com.st.recommender.model.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopRaw {
    private String id;
    private String userID;
    private String starts;
    private String shopURL;
    private String shopType;
    private String shopName;
    private String shopID;
    private String sellerServiceType;
    private String sellerServiceScore;
    private String sellerServiceLevelText;
    private String sellerServiceLevel;
    private String sellerType;
    private String sellerNick;
    private String logisticsServiceType;
    private String logisticsServiceScore;
    private String logisticsServiceLevelText;
    private String logisticsServiceLevel;
    private String itemDescriptionType;
    private String itemDescriptionScore;
    private String itemDescriptionLevelText;
    private String itemDescriptionLevel;
    private String goodRatePercentage;
    private String fans;
    private String creditlevel;
    private String allItemCount;
}
