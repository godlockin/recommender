package com.st.recommender.model.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRaw {
    private String id;
    private String brandID;
    private long commentCount;
    private long favCount;
    private long goodIndex;
    private long itemID;
    private String itemTitle;
    private String itemURL;
    private long liveID;
    private long livePrice;
    private String price;
    private String shopID;
    private String shopName;
    private String taoLeafCategoryID;
    private String taoLeafCategoryName;
    private String taoRootCategoryID;
    private String taoRootCategoryName;
}
