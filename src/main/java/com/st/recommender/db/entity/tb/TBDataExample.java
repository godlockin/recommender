package com.st.recommender.db.entity.tb;

import java.util.ArrayList;
import java.util.List;

public class TBDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TBDataExample() {
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

        public Criteria andFansNumsIsNull() {
            addCriterion("fans_nums is null");
            return (Criteria) this;
        }

        public Criteria andFansNumsIsNotNull() {
            addCriterion("fans_nums is not null");
            return (Criteria) this;
        }

        public Criteria andFansNumsEqualTo(Double value) {
            addCriterion("fans_nums =", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsNotEqualTo(Double value) {
            addCriterion("fans_nums <>", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsGreaterThan(Double value) {
            addCriterion("fans_nums >", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsGreaterThanOrEqualTo(Double value) {
            addCriterion("fans_nums >=", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsLessThan(Double value) {
            addCriterion("fans_nums <", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsLessThanOrEqualTo(Double value) {
            addCriterion("fans_nums <=", value, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsIn(List<Double> values) {
            addCriterion("fans_nums in", values, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsNotIn(List<Double> values) {
            addCriterion("fans_nums not in", values, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsBetween(Double value1, Double value2) {
            addCriterion("fans_nums between", value1, value2, "fansNums");
            return (Criteria) this;
        }

        public Criteria andFansNumsNotBetween(Double value1, Double value2) {
            addCriterion("fans_nums not between", value1, value2, "fansNums");
            return (Criteria) this;
        }

        public Criteria andPvAverageIsNull() {
            addCriterion("pv_average is null");
            return (Criteria) this;
        }

        public Criteria andPvAverageIsNotNull() {
            addCriterion("pv_average is not null");
            return (Criteria) this;
        }

        public Criteria andPvAverageEqualTo(Double value) {
            addCriterion("pv_average =", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageNotEqualTo(Double value) {
            addCriterion("pv_average <>", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageGreaterThan(Double value) {
            addCriterion("pv_average >", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageGreaterThanOrEqualTo(Double value) {
            addCriterion("pv_average >=", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageLessThan(Double value) {
            addCriterion("pv_average <", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageLessThanOrEqualTo(Double value) {
            addCriterion("pv_average <=", value, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageIn(List<Double> values) {
            addCriterion("pv_average in", values, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageNotIn(List<Double> values) {
            addCriterion("pv_average not in", values, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageBetween(Double value1, Double value2) {
            addCriterion("pv_average between", value1, value2, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andPvAverageNotBetween(Double value1, Double value2) {
            addCriterion("pv_average not between", value1, value2, "pvAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageIsNull() {
            addCriterion("goods_average is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageIsNotNull() {
            addCriterion("goods_average is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageEqualTo(Double value) {
            addCriterion("goods_average =", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageNotEqualTo(Double value) {
            addCriterion("goods_average <>", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageGreaterThan(Double value) {
            addCriterion("goods_average >", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageGreaterThanOrEqualTo(Double value) {
            addCriterion("goods_average >=", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageLessThan(Double value) {
            addCriterion("goods_average <", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageLessThanOrEqualTo(Double value) {
            addCriterion("goods_average <=", value, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageIn(List<Double> values) {
            addCriterion("goods_average in", values, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageNotIn(List<Double> values) {
            addCriterion("goods_average not in", values, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageBetween(Double value1, Double value2) {
            addCriterion("goods_average between", value1, value2, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andGoodsAverageNotBetween(Double value1, Double value2) {
            addCriterion("goods_average not between", value1, value2, "goodsAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageIsNull() {
            addCriterion("livehour_average is null");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageIsNotNull() {
            addCriterion("livehour_average is not null");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageEqualTo(Double value) {
            addCriterion("livehour_average =", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageNotEqualTo(Double value) {
            addCriterion("livehour_average <>", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageGreaterThan(Double value) {
            addCriterion("livehour_average >", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageGreaterThanOrEqualTo(Double value) {
            addCriterion("livehour_average >=", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageLessThan(Double value) {
            addCriterion("livehour_average <", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageLessThanOrEqualTo(Double value) {
            addCriterion("livehour_average <=", value, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageIn(List<Double> values) {
            addCriterion("livehour_average in", values, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageNotIn(List<Double> values) {
            addCriterion("livehour_average not in", values, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageBetween(Double value1, Double value2) {
            addCriterion("livehour_average between", value1, value2, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andLivehourAverageNotBetween(Double value1, Double value2) {
            addCriterion("livehour_average not between", value1, value2, "livehourAverage");
            return (Criteria) this;
        }

        public Criteria andPvIsNull() {
            addCriterion("pv is null");
            return (Criteria) this;
        }

        public Criteria andPvIsNotNull() {
            addCriterion("pv is not null");
            return (Criteria) this;
        }

        public Criteria andPvEqualTo(Double value) {
            addCriterion("pv =", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvNotEqualTo(Double value) {
            addCriterion("pv <>", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvGreaterThan(Double value) {
            addCriterion("pv >", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvGreaterThanOrEqualTo(Double value) {
            addCriterion("pv >=", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvLessThan(Double value) {
            addCriterion("pv <", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvLessThanOrEqualTo(Double value) {
            addCriterion("pv <=", value, "pv");
            return (Criteria) this;
        }

        public Criteria andPvIn(List<Double> values) {
            addCriterion("pv in", values, "pv");
            return (Criteria) this;
        }

        public Criteria andPvNotIn(List<Double> values) {
            addCriterion("pv not in", values, "pv");
            return (Criteria) this;
        }

        public Criteria andPvBetween(Double value1, Double value2) {
            addCriterion("pv between", value1, value2, "pv");
            return (Criteria) this;
        }

        public Criteria andPvNotBetween(Double value1, Double value2) {
            addCriterion("pv not between", value1, value2, "pv");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIsNull() {
            addCriterion("live_duration is null");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIsNotNull() {
            addCriterion("live_duration is not null");
            return (Criteria) this;
        }

        public Criteria andLiveDurationEqualTo(Double value) {
            addCriterion("live_duration =", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotEqualTo(Double value) {
            addCriterion("live_duration <>", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationGreaterThan(Double value) {
            addCriterion("live_duration >", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationGreaterThanOrEqualTo(Double value) {
            addCriterion("live_duration >=", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationLessThan(Double value) {
            addCriterion("live_duration <", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationLessThanOrEqualTo(Double value) {
            addCriterion("live_duration <=", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIn(List<Double> values) {
            addCriterion("live_duration in", values, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotIn(List<Double> values) {
            addCriterion("live_duration not in", values, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationBetween(Double value1, Double value2) {
            addCriterion("live_duration between", value1, value2, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotBetween(Double value1, Double value2) {
            addCriterion("live_duration not between", value1, value2, "liveDuration");
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

        public Criteria andShelfTimesEqualTo(Double value) {
            addCriterion("shelf_times =", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotEqualTo(Double value) {
            addCriterion("shelf_times <>", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesGreaterThan(Double value) {
            addCriterion("shelf_times >", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesGreaterThanOrEqualTo(Double value) {
            addCriterion("shelf_times >=", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesLessThan(Double value) {
            addCriterion("shelf_times <", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesLessThanOrEqualTo(Double value) {
            addCriterion("shelf_times <=", value, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesIn(List<Double> values) {
            addCriterion("shelf_times in", values, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotIn(List<Double> values) {
            addCriterion("shelf_times not in", values, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesBetween(Double value1, Double value2) {
            addCriterion("shelf_times between", value1, value2, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andShelfTimesNotBetween(Double value1, Double value2) {
            addCriterion("shelf_times not between", value1, value2, "shelfTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesIsNull() {
            addCriterion("speak_times is null");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesIsNotNull() {
            addCriterion("speak_times is not null");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesEqualTo(Double value) {
            addCriterion("speak_times =", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesNotEqualTo(Double value) {
            addCriterion("speak_times <>", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesGreaterThan(Double value) {
            addCriterion("speak_times >", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesGreaterThanOrEqualTo(Double value) {
            addCriterion("speak_times >=", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesLessThan(Double value) {
            addCriterion("speak_times <", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesLessThanOrEqualTo(Double value) {
            addCriterion("speak_times <=", value, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesIn(List<Double> values) {
            addCriterion("speak_times in", values, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesNotIn(List<Double> values) {
            addCriterion("speak_times not in", values, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesBetween(Double value1, Double value2) {
            addCriterion("speak_times between", value1, value2, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andSpeakTimesNotBetween(Double value1, Double value2) {
            addCriterion("speak_times not between", value1, value2, "speakTimes");
            return (Criteria) this;
        }

        public Criteria andLivePriceIsNull() {
            addCriterion("live_price is null");
            return (Criteria) this;
        }

        public Criteria andLivePriceIsNotNull() {
            addCriterion("live_price is not null");
            return (Criteria) this;
        }

        public Criteria andLivePriceEqualTo(Double value) {
            addCriterion("live_price =", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceNotEqualTo(Double value) {
            addCriterion("live_price <>", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceGreaterThan(Double value) {
            addCriterion("live_price >", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("live_price >=", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceLessThan(Double value) {
            addCriterion("live_price <", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceLessThanOrEqualTo(Double value) {
            addCriterion("live_price <=", value, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceIn(List<Double> values) {
            addCriterion("live_price in", values, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceNotIn(List<Double> values) {
            addCriterion("live_price not in", values, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceBetween(Double value1, Double value2) {
            addCriterion("live_price between", value1, value2, "livePrice");
            return (Criteria) this;
        }

        public Criteria andLivePriceNotBetween(Double value1, Double value2) {
            addCriterion("live_price not between", value1, value2, "livePrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceIsNull() {
            addCriterion("history_price is null");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceIsNotNull() {
            addCriterion("history_price is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceEqualTo(Double value) {
            addCriterion("history_price =", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceNotEqualTo(Double value) {
            addCriterion("history_price <>", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceGreaterThan(Double value) {
            addCriterion("history_price >", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("history_price >=", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceLessThan(Double value) {
            addCriterion("history_price <", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceLessThanOrEqualTo(Double value) {
            addCriterion("history_price <=", value, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceIn(List<Double> values) {
            addCriterion("history_price in", values, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceNotIn(List<Double> values) {
            addCriterion("history_price not in", values, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceBetween(Double value1, Double value2) {
            addCriterion("history_price between", value1, value2, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andHistoryPriceNotBetween(Double value1, Double value2) {
            addCriterion("history_price not between", value1, value2, "historyPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNull() {
            addCriterion("real_price is null");
            return (Criteria) this;
        }

        public Criteria andRealPriceIsNotNull() {
            addCriterion("real_price is not null");
            return (Criteria) this;
        }

        public Criteria andRealPriceEqualTo(Double value) {
            addCriterion("real_price =", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotEqualTo(Double value) {
            addCriterion("real_price <>", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThan(Double value) {
            addCriterion("real_price >", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("real_price >=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThan(Double value) {
            addCriterion("real_price <", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceLessThanOrEqualTo(Double value) {
            addCriterion("real_price <=", value, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceIn(List<Double> values) {
            addCriterion("real_price in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotIn(List<Double> values) {
            addCriterion("real_price not in", values, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceBetween(Double value1, Double value2) {
            addCriterion("real_price between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andRealPriceNotBetween(Double value1, Double value2) {
            addCriterion("real_price not between", value1, value2, "realPrice");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleIsNull() {
            addCriterion("all_live_realsale is null");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleIsNotNull() {
            addCriterion("all_live_realsale is not null");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleEqualTo(Double value) {
            addCriterion("all_live_realsale =", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleNotEqualTo(Double value) {
            addCriterion("all_live_realsale <>", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleGreaterThan(Double value) {
            addCriterion("all_live_realsale >", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleGreaterThanOrEqualTo(Double value) {
            addCriterion("all_live_realsale >=", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleLessThan(Double value) {
            addCriterion("all_live_realsale <", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleLessThanOrEqualTo(Double value) {
            addCriterion("all_live_realsale <=", value, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleIn(List<Double> values) {
            addCriterion("all_live_realsale in", values, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleNotIn(List<Double> values) {
            addCriterion("all_live_realsale not in", values, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleBetween(Double value1, Double value2) {
            addCriterion("all_live_realsale between", value1, value2, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andAllLiveRealsaleNotBetween(Double value1, Double value2) {
            addCriterion("all_live_realsale not between", value1, value2, "allLiveRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleIsNull() {
            addCriterion("speak_time_realsale is null");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleIsNotNull() {
            addCriterion("speak_time_realsale is not null");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleEqualTo(Double value) {
            addCriterion("speak_time_realsale =", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleNotEqualTo(Double value) {
            addCriterion("speak_time_realsale <>", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleGreaterThan(Double value) {
            addCriterion("speak_time_realsale >", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleGreaterThanOrEqualTo(Double value) {
            addCriterion("speak_time_realsale >=", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleLessThan(Double value) {
            addCriterion("speak_time_realsale <", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleLessThanOrEqualTo(Double value) {
            addCriterion("speak_time_realsale <=", value, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleIn(List<Double> values) {
            addCriterion("speak_time_realsale in", values, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleNotIn(List<Double> values) {
            addCriterion("speak_time_realsale not in", values, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleBetween(Double value1, Double value2) {
            addCriterion("speak_time_realsale between", value1, value2, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andSpeakTimeRealsaleNotBetween(Double value1, Double value2) {
            addCriterion("speak_time_realsale not between", value1, value2, "speakTimeRealsale");
            return (Criteria) this;
        }

        public Criteria andMalePercentIsNull() {
            addCriterion("male_percent is null");
            return (Criteria) this;
        }

        public Criteria andMalePercentIsNotNull() {
            addCriterion("male_percent is not null");
            return (Criteria) this;
        }

        public Criteria andMalePercentEqualTo(Double value) {
            addCriterion("male_percent =", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentNotEqualTo(Double value) {
            addCriterion("male_percent <>", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentGreaterThan(Double value) {
            addCriterion("male_percent >", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentGreaterThanOrEqualTo(Double value) {
            addCriterion("male_percent >=", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentLessThan(Double value) {
            addCriterion("male_percent <", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentLessThanOrEqualTo(Double value) {
            addCriterion("male_percent <=", value, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentIn(List<Double> values) {
            addCriterion("male_percent in", values, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentNotIn(List<Double> values) {
            addCriterion("male_percent not in", values, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentBetween(Double value1, Double value2) {
            addCriterion("male_percent between", value1, value2, "malePercent");
            return (Criteria) this;
        }

        public Criteria andMalePercentNotBetween(Double value1, Double value2) {
            addCriterion("male_percent not between", value1, value2, "malePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentIsNull() {
            addCriterion("female_percent is null");
            return (Criteria) this;
        }

        public Criteria andFemalePercentIsNotNull() {
            addCriterion("female_percent is not null");
            return (Criteria) this;
        }

        public Criteria andFemalePercentEqualTo(Double value) {
            addCriterion("female_percent =", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentNotEqualTo(Double value) {
            addCriterion("female_percent <>", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentGreaterThan(Double value) {
            addCriterion("female_percent >", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentGreaterThanOrEqualTo(Double value) {
            addCriterion("female_percent >=", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentLessThan(Double value) {
            addCriterion("female_percent <", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentLessThanOrEqualTo(Double value) {
            addCriterion("female_percent <=", value, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentIn(List<Double> values) {
            addCriterion("female_percent in", values, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentNotIn(List<Double> values) {
            addCriterion("female_percent not in", values, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentBetween(Double value1, Double value2) {
            addCriterion("female_percent between", value1, value2, "femalePercent");
            return (Criteria) this;
        }

        public Criteria andFemalePercentNotBetween(Double value1, Double value2) {
            addCriterion("female_percent not between", value1, value2, "femalePercent");
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