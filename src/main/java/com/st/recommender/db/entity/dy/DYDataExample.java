package com.st.recommender.db.entity.dy;

import java.util.ArrayList;
import java.util.List;

public class DYDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DYDataExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFollowerCountIsNull() {
            addCriterion("follower_count is null");
            return (Criteria) this;
        }

        public Criteria andFollowerCountIsNotNull() {
            addCriterion("follower_count is not null");
            return (Criteria) this;
        }

        public Criteria andFollowerCountEqualTo(Integer value) {
            addCriterion("follower_count =", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountNotEqualTo(Integer value) {
            addCriterion("follower_count <>", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountGreaterThan(Integer value) {
            addCriterion("follower_count >", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("follower_count >=", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountLessThan(Integer value) {
            addCriterion("follower_count <", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountLessThanOrEqualTo(Integer value) {
            addCriterion("follower_count <=", value, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountIn(List<Integer> values) {
            addCriterion("follower_count in", values, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountNotIn(List<Integer> values) {
            addCriterion("follower_count not in", values, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountBetween(Integer value1, Integer value2) {
            addCriterion("follower_count between", value1, value2, "followerCount");
            return (Criteria) this;
        }

        public Criteria andFollowerCountNotBetween(Integer value1, Integer value2) {
            addCriterion("follower_count not between", value1, value2, "followerCount");
            return (Criteria) this;
        }

        public Criteria andLiveHourIsNull() {
            addCriterion("live_hour is null");
            return (Criteria) this;
        }

        public Criteria andLiveHourIsNotNull() {
            addCriterion("live_hour is not null");
            return (Criteria) this;
        }

        public Criteria andLiveHourEqualTo(Integer value) {
            addCriterion("live_hour =", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourNotEqualTo(Integer value) {
            addCriterion("live_hour <>", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourGreaterThan(Integer value) {
            addCriterion("live_hour >", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_hour >=", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourLessThan(Integer value) {
            addCriterion("live_hour <", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourLessThanOrEqualTo(Integer value) {
            addCriterion("live_hour <=", value, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourIn(List<Integer> values) {
            addCriterion("live_hour in", values, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourNotIn(List<Integer> values) {
            addCriterion("live_hour not in", values, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourBetween(Integer value1, Integer value2) {
            addCriterion("live_hour between", value1, value2, "liveHour");
            return (Criteria) this;
        }

        public Criteria andLiveHourNotBetween(Integer value1, Integer value2) {
            addCriterion("live_hour not between", value1, value2, "liveHour");
            return (Criteria) this;
        }

        public Criteria andUserPeakIsNull() {
            addCriterion("user_peak is null");
            return (Criteria) this;
        }

        public Criteria andUserPeakIsNotNull() {
            addCriterion("user_peak is not null");
            return (Criteria) this;
        }

        public Criteria andUserPeakEqualTo(Integer value) {
            addCriterion("user_peak =", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakNotEqualTo(Integer value) {
            addCriterion("user_peak <>", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakGreaterThan(Integer value) {
            addCriterion("user_peak >", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_peak >=", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakLessThan(Integer value) {
            addCriterion("user_peak <", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakLessThanOrEqualTo(Integer value) {
            addCriterion("user_peak <=", value, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakIn(List<Integer> values) {
            addCriterion("user_peak in", values, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakNotIn(List<Integer> values) {
            addCriterion("user_peak not in", values, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakBetween(Integer value1, Integer value2) {
            addCriterion("user_peak between", value1, value2, "userPeak");
            return (Criteria) this;
        }

        public Criteria andUserPeakNotBetween(Integer value1, Integer value2) {
            addCriterion("user_peak not between", value1, value2, "userPeak");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountIsNull() {
            addCriterion("gift_uv_count is null");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountIsNotNull() {
            addCriterion("gift_uv_count is not null");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountEqualTo(Integer value) {
            addCriterion("gift_uv_count =", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountNotEqualTo(Integer value) {
            addCriterion("gift_uv_count <>", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountGreaterThan(Integer value) {
            addCriterion("gift_uv_count >", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("gift_uv_count >=", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountLessThan(Integer value) {
            addCriterion("gift_uv_count <", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountLessThanOrEqualTo(Integer value) {
            addCriterion("gift_uv_count <=", value, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountIn(List<Integer> values) {
            addCriterion("gift_uv_count in", values, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountNotIn(List<Integer> values) {
            addCriterion("gift_uv_count not in", values, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountBetween(Integer value1, Integer value2) {
            addCriterion("gift_uv_count between", value1, value2, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andGiftUvCountNotBetween(Integer value1, Integer value2) {
            addCriterion("gift_uv_count not between", value1, value2, "giftUvCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountIsNull() {
            addCriterion("room_ticket_count is null");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountIsNotNull() {
            addCriterion("room_ticket_count is not null");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountEqualTo(Integer value) {
            addCriterion("room_ticket_count =", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountNotEqualTo(Integer value) {
            addCriterion("room_ticket_count <>", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountGreaterThan(Integer value) {
            addCriterion("room_ticket_count >", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_ticket_count >=", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountLessThan(Integer value) {
            addCriterion("room_ticket_count <", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountLessThanOrEqualTo(Integer value) {
            addCriterion("room_ticket_count <=", value, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountIn(List<Integer> values) {
            addCriterion("room_ticket_count in", values, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountNotIn(List<Integer> values) {
            addCriterion("room_ticket_count not in", values, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountBetween(Integer value1, Integer value2) {
            addCriterion("room_ticket_count between", value1, value2, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andRoomTicketCountNotBetween(Integer value1, Integer value2) {
            addCriterion("room_ticket_count not between", value1, value2, "roomTicketCount");
            return (Criteria) this;
        }

        public Criteria andTotalUserIsNull() {
            addCriterion("total_user is null");
            return (Criteria) this;
        }

        public Criteria andTotalUserIsNotNull() {
            addCriterion("total_user is not null");
            return (Criteria) this;
        }

        public Criteria andTotalUserEqualTo(Integer value) {
            addCriterion("total_user =", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserNotEqualTo(Integer value) {
            addCriterion("total_user <>", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserGreaterThan(Integer value) {
            addCriterion("total_user >", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_user >=", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserLessThan(Integer value) {
            addCriterion("total_user <", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserLessThanOrEqualTo(Integer value) {
            addCriterion("total_user <=", value, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserIn(List<Integer> values) {
            addCriterion("total_user in", values, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserNotIn(List<Integer> values) {
            addCriterion("total_user not in", values, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserBetween(Integer value1, Integer value2) {
            addCriterion("total_user between", value1, value2, "totalUser");
            return (Criteria) this;
        }

        public Criteria andTotalUserNotBetween(Integer value1, Integer value2) {
            addCriterion("total_user not between", value1, value2, "totalUser");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNull() {
            addCriterion("product_count is null");
            return (Criteria) this;
        }

        public Criteria andProductCountIsNotNull() {
            addCriterion("product_count is not null");
            return (Criteria) this;
        }

        public Criteria andProductCountEqualTo(Integer value) {
            addCriterion("product_count =", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotEqualTo(Integer value) {
            addCriterion("product_count <>", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThan(Integer value) {
            addCriterion("product_count >", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_count >=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThan(Integer value) {
            addCriterion("product_count <", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountLessThanOrEqualTo(Integer value) {
            addCriterion("product_count <=", value, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountIn(List<Integer> values) {
            addCriterion("product_count in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotIn(List<Integer> values) {
            addCriterion("product_count not in", values, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountBetween(Integer value1, Integer value2) {
            addCriterion("product_count between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andProductCountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_count not between", value1, value2, "productCount");
            return (Criteria) this;
        }

        public Criteria andItemAmountIsNull() {
            addCriterion("item_amount is null");
            return (Criteria) this;
        }

        public Criteria andItemAmountIsNotNull() {
            addCriterion("item_amount is not null");
            return (Criteria) this;
        }

        public Criteria andItemAmountEqualTo(Double value) {
            addCriterion("item_amount =", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountNotEqualTo(Double value) {
            addCriterion("item_amount <>", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountGreaterThan(Double value) {
            addCriterion("item_amount >", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("item_amount >=", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountLessThan(Double value) {
            addCriterion("item_amount <", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountLessThanOrEqualTo(Double value) {
            addCriterion("item_amount <=", value, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountIn(List<Double> values) {
            addCriterion("item_amount in", values, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountNotIn(List<Double> values) {
            addCriterion("item_amount not in", values, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountBetween(Double value1, Double value2) {
            addCriterion("item_amount between", value1, value2, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemAmountNotBetween(Double value1, Double value2) {
            addCriterion("item_amount not between", value1, value2, "itemAmount");
            return (Criteria) this;
        }

        public Criteria andItemVolumeIsNull() {
            addCriterion("item_volume is null");
            return (Criteria) this;
        }

        public Criteria andItemVolumeIsNotNull() {
            addCriterion("item_volume is not null");
            return (Criteria) this;
        }

        public Criteria andItemVolumeEqualTo(Integer value) {
            addCriterion("item_volume =", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeNotEqualTo(Integer value) {
            addCriterion("item_volume <>", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeGreaterThan(Integer value) {
            addCriterion("item_volume >", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_volume >=", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeLessThan(Integer value) {
            addCriterion("item_volume <", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("item_volume <=", value, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeIn(List<Integer> values) {
            addCriterion("item_volume in", values, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeNotIn(List<Integer> values) {
            addCriterion("item_volume not in", values, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeBetween(Integer value1, Integer value2) {
            addCriterion("item_volume between", value1, value2, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andItemVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("item_volume not between", value1, value2, "itemVolume");
            return (Criteria) this;
        }

        public Criteria andFinalPriceIsNull() {
            addCriterion("final_price is null");
            return (Criteria) this;
        }

        public Criteria andFinalPriceIsNotNull() {
            addCriterion("final_price is not null");
            return (Criteria) this;
        }

        public Criteria andFinalPriceEqualTo(Double value) {
            addCriterion("final_price =", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceNotEqualTo(Double value) {
            addCriterion("final_price <>", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceGreaterThan(Double value) {
            addCriterion("final_price >", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("final_price >=", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceLessThan(Double value) {
            addCriterion("final_price <", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceLessThanOrEqualTo(Double value) {
            addCriterion("final_price <=", value, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceIn(List<Double> values) {
            addCriterion("final_price in", values, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceNotIn(List<Double> values) {
            addCriterion("final_price not in", values, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceBetween(Double value1, Double value2) {
            addCriterion("final_price between", value1, value2, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andFinalPriceNotBetween(Double value1, Double value2) {
            addCriterion("final_price not between", value1, value2, "finalPrice");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNull() {
            addCriterion("commission_rate is null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIsNotNull() {
            addCriterion("commission_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionRateEqualTo(Integer value) {
            addCriterion("commission_rate =", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotEqualTo(Integer value) {
            addCriterion("commission_rate <>", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThan(Integer value) {
            addCriterion("commission_rate >", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("commission_rate >=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThan(Integer value) {
            addCriterion("commission_rate <", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateLessThanOrEqualTo(Integer value) {
            addCriterion("commission_rate <=", value, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateIn(List<Integer> values) {
            addCriterion("commission_rate in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotIn(List<Integer> values) {
            addCriterion("commission_rate not in", values, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateBetween(Integer value1, Integer value2) {
            addCriterion("commission_rate between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andCommissionRateNotBetween(Integer value1, Integer value2) {
            addCriterion("commission_rate not between", value1, value2, "commissionRate");
            return (Criteria) this;
        }

        public Criteria andShelfTimesIsNull() {
            addCriterion("shelf_times is null");
            return (Criteria) this;
        }

        public Criteria andShelfTimesIsNotNull() {
            addCriterion("shelf_times is not null");
            return (Criteria) this;
        }

        public Criteria andShelfTimesEqualTo(Long value) {
            addCriterion("shelf_times =", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotEqualTo(Long value) {
            addCriterion("shelf_times <>", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesGreaterThan(Long value) {
            addCriterion("shelf_times >", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("shelf_times >=", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesLessThan(Long value) {
            addCriterion("shelf_times <", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesLessThanOrEqualTo(Long value) {
            addCriterion("shelf_times <=", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesIn(List<Long> values) {
            addCriterion("shelf_times in", values, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotIn(List<Long> values) {
            addCriterion("shelf_times not in", values, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesBetween(Long value1, Long value2) {
            addCriterion("shelf_times between", value1, value2, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotBetween(Long value1, Long value2) {
            addCriterion("shelf_times not between", value1, value2, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderIsNull() {
            addCriterion("item_top1_gender is null");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderIsNotNull() {
            addCriterion("item_top1_gender is not null");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderEqualTo(Integer value) {
            addCriterion("item_top1_gender =", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderNotEqualTo(Integer value) {
            addCriterion("item_top1_gender <>", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderGreaterThan(Integer value) {
            addCriterion("item_top1_gender >", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_top1_gender >=", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderLessThan(Integer value) {
            addCriterion("item_top1_gender <", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderLessThanOrEqualTo(Integer value) {
            addCriterion("item_top1_gender <=", value, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderIn(List<Integer> values) {
            addCriterion("item_top1_gender in", values, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderNotIn(List<Integer> values) {
            addCriterion("item_top1_gender not in", values, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderBetween(Integer value1, Integer value2) {
            addCriterion("item_top1_gender between", value1, value2, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1GenderNotBetween(Integer value1, Integer value2) {
            addCriterion("item_top1_gender not between", value1, value2, "itemTop1Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderIsNull() {
            addCriterion("item_top2_gender is null");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderIsNotNull() {
            addCriterion("item_top2_gender is not null");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderEqualTo(Integer value) {
            addCriterion("item_top2_gender =", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderNotEqualTo(Integer value) {
            addCriterion("item_top2_gender <>", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderGreaterThan(Integer value) {
            addCriterion("item_top2_gender >", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_top2_gender >=", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderLessThan(Integer value) {
            addCriterion("item_top2_gender <", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderLessThanOrEqualTo(Integer value) {
            addCriterion("item_top2_gender <=", value, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderIn(List<Integer> values) {
            addCriterion("item_top2_gender in", values, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderNotIn(List<Integer> values) {
            addCriterion("item_top2_gender not in", values, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderBetween(Integer value1, Integer value2) {
            addCriterion("item_top2_gender between", value1, value2, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop2GenderNotBetween(Integer value1, Integer value2) {
            addCriterion("item_top2_gender not between", value1, value2, "itemTop2Gender");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeIsNull() {
            addCriterion("item_top1_age is null");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeIsNotNull() {
            addCriterion("item_top1_age is not null");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeEqualTo(String value) {
            addCriterion("item_top1_age =", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeNotEqualTo(String value) {
            addCriterion("item_top1_age <>", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeGreaterThan(String value) {
            addCriterion("item_top1_age >", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeGreaterThanOrEqualTo(String value) {
            addCriterion("item_top1_age >=", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeLessThan(String value) {
            addCriterion("item_top1_age <", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeLessThanOrEqualTo(String value) {
            addCriterion("item_top1_age <=", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeLike(String value) {
            addCriterion("item_top1_age like", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeNotLike(String value) {
            addCriterion("item_top1_age not like", value, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeIn(List<String> values) {
            addCriterion("item_top1_age in", values, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeNotIn(List<String> values) {
            addCriterion("item_top1_age not in", values, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeBetween(String value1, String value2) {
            addCriterion("item_top1_age between", value1, value2, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop1AgeNotBetween(String value1, String value2) {
            addCriterion("item_top1_age not between", value1, value2, "itemTop1Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeIsNull() {
            addCriterion("item_top2_age is null");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeIsNotNull() {
            addCriterion("item_top2_age is not null");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeEqualTo(String value) {
            addCriterion("item_top2_age =", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeNotEqualTo(String value) {
            addCriterion("item_top2_age <>", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeGreaterThan(String value) {
            addCriterion("item_top2_age >", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeGreaterThanOrEqualTo(String value) {
            addCriterion("item_top2_age >=", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeLessThan(String value) {
            addCriterion("item_top2_age <", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeLessThanOrEqualTo(String value) {
            addCriterion("item_top2_age <=", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeLike(String value) {
            addCriterion("item_top2_age like", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeNotLike(String value) {
            addCriterion("item_top2_age not like", value, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeIn(List<String> values) {
            addCriterion("item_top2_age in", values, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeNotIn(List<String> values) {
            addCriterion("item_top2_age not in", values, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeBetween(String value1, String value2) {
            addCriterion("item_top2_age between", value1, value2, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop2AgeNotBetween(String value1, String value2) {
            addCriterion("item_top2_age not between", value1, value2, "itemTop2Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeIsNull() {
            addCriterion("item_top3_age is null");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeIsNotNull() {
            addCriterion("item_top3_age is not null");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeEqualTo(String value) {
            addCriterion("item_top3_age =", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeNotEqualTo(String value) {
            addCriterion("item_top3_age <>", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeGreaterThan(String value) {
            addCriterion("item_top3_age >", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeGreaterThanOrEqualTo(String value) {
            addCriterion("item_top3_age >=", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeLessThan(String value) {
            addCriterion("item_top3_age <", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeLessThanOrEqualTo(String value) {
            addCriterion("item_top3_age <=", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeLike(String value) {
            addCriterion("item_top3_age like", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeNotLike(String value) {
            addCriterion("item_top3_age not like", value, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeIn(List<String> values) {
            addCriterion("item_top3_age in", values, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeNotIn(List<String> values) {
            addCriterion("item_top3_age not in", values, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeBetween(String value1, String value2) {
            addCriterion("item_top3_age between", value1, value2, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andItemTop3AgeNotBetween(String value1, String value2) {
            addCriterion("item_top3_age not between", value1, value2, "itemTop3Age");
            return (Criteria) this;
        }

        public Criteria andTop1GenderIsNull() {
            addCriterion("top1_gender is null");
            return (Criteria) this;
        }

        public Criteria andTop1GenderIsNotNull() {
            addCriterion("top1_gender is not null");
            return (Criteria) this;
        }

        public Criteria andTop1GenderEqualTo(Integer value) {
            addCriterion("top1_gender =", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderNotEqualTo(Integer value) {
            addCriterion("top1_gender <>", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderGreaterThan(Integer value) {
            addCriterion("top1_gender >", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("top1_gender >=", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderLessThan(Integer value) {
            addCriterion("top1_gender <", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderLessThanOrEqualTo(Integer value) {
            addCriterion("top1_gender <=", value, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderIn(List<Integer> values) {
            addCriterion("top1_gender in", values, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderNotIn(List<Integer> values) {
            addCriterion("top1_gender not in", values, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderBetween(Integer value1, Integer value2) {
            addCriterion("top1_gender between", value1, value2, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop1GenderNotBetween(Integer value1, Integer value2) {
            addCriterion("top1_gender not between", value1, value2, "top1Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderIsNull() {
            addCriterion("top2_gender is null");
            return (Criteria) this;
        }

        public Criteria andTop2GenderIsNotNull() {
            addCriterion("top2_gender is not null");
            return (Criteria) this;
        }

        public Criteria andTop2GenderEqualTo(Integer value) {
            addCriterion("top2_gender =", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderNotEqualTo(Integer value) {
            addCriterion("top2_gender <>", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderGreaterThan(Integer value) {
            addCriterion("top2_gender >", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("top2_gender >=", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderLessThan(Integer value) {
            addCriterion("top2_gender <", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderLessThanOrEqualTo(Integer value) {
            addCriterion("top2_gender <=", value, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderIn(List<Integer> values) {
            addCriterion("top2_gender in", values, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderNotIn(List<Integer> values) {
            addCriterion("top2_gender not in", values, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderBetween(Integer value1, Integer value2) {
            addCriterion("top2_gender between", value1, value2, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andTop2GenderNotBetween(Integer value1, Integer value2) {
            addCriterion("top2_gender not between", value1, value2, "top2Gender");
            return (Criteria) this;
        }

        public Criteria andCityRateIsNull() {
            addCriterion("city_rate is null");
            return (Criteria) this;
        }

        public Criteria andCityRateIsNotNull() {
            addCriterion("city_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCityRateEqualTo(Double value) {
            addCriterion("city_rate =", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateNotEqualTo(Double value) {
            addCriterion("city_rate <>", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateGreaterThan(Double value) {
            addCriterion("city_rate >", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateGreaterThanOrEqualTo(Double value) {
            addCriterion("city_rate >=", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateLessThan(Double value) {
            addCriterion("city_rate <", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateLessThanOrEqualTo(Double value) {
            addCriterion("city_rate <=", value, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateIn(List<Double> values) {
            addCriterion("city_rate in", values, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateNotIn(List<Double> values) {
            addCriterion("city_rate not in", values, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateBetween(Double value1, Double value2) {
            addCriterion("city_rate between", value1, value2, "cityRate");
            return (Criteria) this;
        }

        public Criteria andCityRateNotBetween(Double value1, Double value2) {
            addCriterion("city_rate not between", value1, value2, "cityRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateIsNull() {
            addCriterion("myfollow_rate is null");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateIsNotNull() {
            addCriterion("myfollow_rate is not null");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateEqualTo(Double value) {
            addCriterion("myfollow_rate =", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateNotEqualTo(Double value) {
            addCriterion("myfollow_rate <>", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateGreaterThan(Double value) {
            addCriterion("myfollow_rate >", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateGreaterThanOrEqualTo(Double value) {
            addCriterion("myfollow_rate >=", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateLessThan(Double value) {
            addCriterion("myfollow_rate <", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateLessThanOrEqualTo(Double value) {
            addCriterion("myfollow_rate <=", value, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateIn(List<Double> values) {
            addCriterion("myfollow_rate in", values, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateNotIn(List<Double> values) {
            addCriterion("myfollow_rate not in", values, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateBetween(Double value1, Double value2) {
            addCriterion("myfollow_rate between", value1, value2, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andMyfollowRateNotBetween(Double value1, Double value2) {
            addCriterion("myfollow_rate not between", value1, value2, "myfollowRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateIsNull() {
            addCriterion("other_rate is null");
            return (Criteria) this;
        }

        public Criteria andOtherRateIsNotNull() {
            addCriterion("other_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOtherRateEqualTo(Double value) {
            addCriterion("other_rate =", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateNotEqualTo(Double value) {
            addCriterion("other_rate <>", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateGreaterThan(Double value) {
            addCriterion("other_rate >", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateGreaterThanOrEqualTo(Double value) {
            addCriterion("other_rate >=", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateLessThan(Double value) {
            addCriterion("other_rate <", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateLessThanOrEqualTo(Double value) {
            addCriterion("other_rate <=", value, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateIn(List<Double> values) {
            addCriterion("other_rate in", values, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateNotIn(List<Double> values) {
            addCriterion("other_rate not in", values, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateBetween(Double value1, Double value2) {
            addCriterion("other_rate between", value1, value2, "otherRate");
            return (Criteria) this;
        }

        public Criteria andOtherRateNotBetween(Double value1, Double value2) {
            addCriterion("other_rate not between", value1, value2, "otherRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateIsNull() {
            addCriterion("video_rate is null");
            return (Criteria) this;
        }

        public Criteria andVideoRateIsNotNull() {
            addCriterion("video_rate is not null");
            return (Criteria) this;
        }

        public Criteria andVideoRateEqualTo(Double value) {
            addCriterion("video_rate =", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateNotEqualTo(Double value) {
            addCriterion("video_rate <>", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateGreaterThan(Double value) {
            addCriterion("video_rate >", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateGreaterThanOrEqualTo(Double value) {
            addCriterion("video_rate >=", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateLessThan(Double value) {
            addCriterion("video_rate <", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateLessThanOrEqualTo(Double value) {
            addCriterion("video_rate <=", value, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateIn(List<Double> values) {
            addCriterion("video_rate in", values, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateNotIn(List<Double> values) {
            addCriterion("video_rate not in", values, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateBetween(Double value1, Double value2) {
            addCriterion("video_rate between", value1, value2, "videoRate");
            return (Criteria) this;
        }

        public Criteria andVideoRateNotBetween(Double value1, Double value2) {
            addCriterion("video_rate not between", value1, value2, "videoRate");
            return (Criteria) this;
        }

        public Criteria andTop1AgeIsNull() {
            addCriterion("top1_age is null");
            return (Criteria) this;
        }

        public Criteria andTop1AgeIsNotNull() {
            addCriterion("top1_age is not null");
            return (Criteria) this;
        }

        public Criteria andTop1AgeEqualTo(String value) {
            addCriterion("top1_age =", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeNotEqualTo(String value) {
            addCriterion("top1_age <>", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeGreaterThan(String value) {
            addCriterion("top1_age >", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeGreaterThanOrEqualTo(String value) {
            addCriterion("top1_age >=", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeLessThan(String value) {
            addCriterion("top1_age <", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeLessThanOrEqualTo(String value) {
            addCriterion("top1_age <=", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeLike(String value) {
            addCriterion("top1_age like", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeNotLike(String value) {
            addCriterion("top1_age not like", value, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeIn(List<String> values) {
            addCriterion("top1_age in", values, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeNotIn(List<String> values) {
            addCriterion("top1_age not in", values, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeBetween(String value1, String value2) {
            addCriterion("top1_age between", value1, value2, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop1AgeNotBetween(String value1, String value2) {
            addCriterion("top1_age not between", value1, value2, "top1Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeIsNull() {
            addCriterion("top2_age is null");
            return (Criteria) this;
        }

        public Criteria andTop2AgeIsNotNull() {
            addCriterion("top2_age is not null");
            return (Criteria) this;
        }

        public Criteria andTop2AgeEqualTo(String value) {
            addCriterion("top2_age =", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeNotEqualTo(String value) {
            addCriterion("top2_age <>", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeGreaterThan(String value) {
            addCriterion("top2_age >", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeGreaterThanOrEqualTo(String value) {
            addCriterion("top2_age >=", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeLessThan(String value) {
            addCriterion("top2_age <", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeLessThanOrEqualTo(String value) {
            addCriterion("top2_age <=", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeLike(String value) {
            addCriterion("top2_age like", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeNotLike(String value) {
            addCriterion("top2_age not like", value, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeIn(List<String> values) {
            addCriterion("top2_age in", values, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeNotIn(List<String> values) {
            addCriterion("top2_age not in", values, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeBetween(String value1, String value2) {
            addCriterion("top2_age between", value1, value2, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop2AgeNotBetween(String value1, String value2) {
            addCriterion("top2_age not between", value1, value2, "top2Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeIsNull() {
            addCriterion("top3_age is null");
            return (Criteria) this;
        }

        public Criteria andTop3AgeIsNotNull() {
            addCriterion("top3_age is not null");
            return (Criteria) this;
        }

        public Criteria andTop3AgeEqualTo(String value) {
            addCriterion("top3_age =", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeNotEqualTo(String value) {
            addCriterion("top3_age <>", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeGreaterThan(String value) {
            addCriterion("top3_age >", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeGreaterThanOrEqualTo(String value) {
            addCriterion("top3_age >=", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeLessThan(String value) {
            addCriterion("top3_age <", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeLessThanOrEqualTo(String value) {
            addCriterion("top3_age <=", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeLike(String value) {
            addCriterion("top3_age like", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeNotLike(String value) {
            addCriterion("top3_age not like", value, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeIn(List<String> values) {
            addCriterion("top3_age in", values, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeNotIn(List<String> values) {
            addCriterion("top3_age not in", values, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeBetween(String value1, String value2) {
            addCriterion("top3_age between", value1, value2, "top3Age");
            return (Criteria) this;
        }

        public Criteria andTop3AgeNotBetween(String value1, String value2) {
            addCriterion("top3_age not between", value1, value2, "top3Age");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeIsNull() {
            addCriterion("max_item_volume is null");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeIsNotNull() {
            addCriterion("max_item_volume is not null");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeEqualTo(Integer value) {
            addCriterion("max_item_volume =", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeNotEqualTo(Integer value) {
            addCriterion("max_item_volume <>", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeGreaterThan(Integer value) {
            addCriterion("max_item_volume >", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_item_volume >=", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeLessThan(Integer value) {
            addCriterion("max_item_volume <", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("max_item_volume <=", value, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeIn(List<Integer> values) {
            addCriterion("max_item_volume in", values, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeNotIn(List<Integer> values) {
            addCriterion("max_item_volume not in", values, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeBetween(Integer value1, Integer value2) {
            addCriterion("max_item_volume between", value1, value2, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxItemVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("max_item_volume not between", value1, value2, "maxItemVolume");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesIsNull() {
            addCriterion("max_shelf_times is null");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesIsNotNull() {
            addCriterion("max_shelf_times is not null");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesEqualTo(Long value) {
            addCriterion("max_shelf_times =", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesNotEqualTo(Long value) {
            addCriterion("max_shelf_times <>", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesGreaterThan(Long value) {
            addCriterion("max_shelf_times >", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesGreaterThanOrEqualTo(Long value) {
            addCriterion("max_shelf_times >=", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesLessThan(Long value) {
            addCriterion("max_shelf_times <", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesLessThanOrEqualTo(Long value) {
            addCriterion("max_shelf_times <=", value, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesIn(List<Long> values) {
            addCriterion("max_shelf_times in", values, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesNotIn(List<Long> values) {
            addCriterion("max_shelf_times not in", values, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesBetween(Long value1, Long value2) {
            addCriterion("max_shelf_times between", value1, value2, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andMaxShelfTimesNotBetween(Long value1, Long value2) {
            addCriterion("max_shelf_times not between", value1, value2, "maxShelfTimes");
            return (Criteria) this;
        }

        public Criteria andLabelTmpIsNull() {
            addCriterion("label_tmp is null");
            return (Criteria) this;
        }

        public Criteria andLabelTmpIsNotNull() {
            addCriterion("label_tmp is not null");
            return (Criteria) this;
        }

        public Criteria andLabelTmpEqualTo(Double value) {
            addCriterion("label_tmp =", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpNotEqualTo(Double value) {
            addCriterion("label_tmp <>", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpGreaterThan(Double value) {
            addCriterion("label_tmp >", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpGreaterThanOrEqualTo(Double value) {
            addCriterion("label_tmp >=", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpLessThan(Double value) {
            addCriterion("label_tmp <", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpLessThanOrEqualTo(Double value) {
            addCriterion("label_tmp <=", value, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpIn(List<Double> values) {
            addCriterion("label_tmp in", values, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpNotIn(List<Double> values) {
            addCriterion("label_tmp not in", values, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpBetween(Double value1, Double value2) {
            addCriterion("label_tmp between", value1, value2, "labelTmp");
            return (Criteria) this;
        }

        public Criteria andLabelTmpNotBetween(Double value1, Double value2) {
            addCriterion("label_tmp not between", value1, value2, "labelTmp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}