package com.st.recommender.common.utils;

import com.st.recommender.constants.ResultEnum;
import com.st.recommender.exception.RecommenderException;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiFunction;

public class DateUtils {

    public static final String DATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_YYYYMM = "yyyyMM";
    public static final String DATE_YYYY = "yyyy";
    public static final String DEFAULT_DATE_FORMAT = DATE_YYYYMMDD_HHMMSS;
    public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";
    public static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
    private static final Map<DATE_TYPE, BiFunction<ZonedDateTime, Integer, ZonedDateTime>> functionMap = new HashMap() {{
        put(DATE_TYPE.YEAR, addYears());
        put(DATE_TYPE.MONTH, addMonths());
        put(DATE_TYPE.WEEK, addWeeks());
        put(DATE_TYPE.DAY, addDays());
        put(DATE_TYPE.HOUR, addHours());
        put(DATE_TYPE.MINUTE, addMinutes());
        put(DATE_TYPE.SECOND, addSeconds());
    }};

    public static ZonedDateTime parseDate(String date) {
        return parseDate(date, DEFAULT_DATE_FORMAT);
    }

    public static ZonedDateTime parseDate(String date, String format) {
        try {
            return LocalDateTime.parse(date
                    , DateTimeFormatter.ofPattern(Optional.ofNullable(format).orElse(DEFAULT_DATE_FORMAT))
            ).atZone(ZoneId.of(DEFAULT_ZONE_ID));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RecommenderException(ResultEnum.PARAMETER_CHECK);
        }
    }

    public static ZonedDateTime calcZonedDateTime(int num, DATE_TYPE timeUnit) {
        return calcZonedDateTime(getZonedDateTime(), num, timeUnit);
    }

    public static ZonedDateTime calcZonedDateTime(ZonedDateTime zonedDateTime, int num, DATE_TYPE timeUnit) {
        return functionMap.getOrDefault(timeUnit, addDays()).apply(Optional.ofNullable(zonedDateTime).orElse(getZonedDateTime()), num);
    }

    public static String formatZonedDateTime() {
        return formatZonedDateTime(getZonedDateTime(), DEFAULT_DATE_FORMAT);
    }

    public static String formatZonedDateTime(ZonedDateTime zonedDateTime) {
        return formatZonedDateTime(zonedDateTime, DEFAULT_DATE_FORMAT);
    }

    public static String formatZonedDateTime(ZonedDateTime zonedDateTime, String format) {
        return formatZonedDateTime(zonedDateTime, DateTimeFormatter.ofPattern(format));
    }

    public static String formatZonedDateTime(ZonedDateTime zonedDateTime, DateTimeFormatter pattern) {
        return zonedDateTime.format(pattern);
    }

    public static String getZonedDateTimeFormatted(String zoneId, String format) {
        return formatZonedDateTime(getZonedDateTime(zoneId), format);
    }

    public static String getZonedDateTimeFormatted(String format) {
        return getZonedDateTimeFormatted(DEFAULT_ZONE_ID, format);
    }

    public static String getZonedDateTimeFormatted() {
        return getZonedDateTimeFormatted(DEFAULT_ZONE_ID, DEFAULT_DATE_FORMAT);
    }

    public static ZonedDateTime getZonedDateTime() {
        return getZonedDateTime(DEFAULT_ZONE_ID);
    }

    public static ZonedDateTime getZonedDateTime(String zoneId) {
        return getZonedDateTime(ZoneId.of(zoneId));
    }

    public static ZonedDateTime getZonedDateTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_FORMAT);
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(Optional.ofNullable(format).orElse(DEFAULT_DATE_FORMAT))
                .format(Optional.ofNullable(date).orElse(new Date()));
    }

    public static ZonedDateTime getDateDiff(int num, DATE_TYPE timeUnit) {
        return getDateDiff(getZonedDateTime(), num, timeUnit);
    }

    public static ZonedDateTime getDateDiff(ZonedDateTime base, int num, DATE_TYPE timeUnit) {
        return functionMap.getOrDefault(timeUnit, addDays())
                .apply(Optional.ofNullable(base).orElse(getZonedDateTime()), num);
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addYears() {
        return ZonedDateTime::plusYears;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addMonths() {
        return ZonedDateTime::plusMonths;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addWeeks() {
        return ZonedDateTime::plusWeeks;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addDays() {
        return ZonedDateTime::plusDays;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addHours() {
        return ZonedDateTime::plusHours;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addMinutes() {
        return ZonedDateTime::plusMinutes;
    }

    private static BiFunction<ZonedDateTime, Integer, ZonedDateTime> addSeconds() {
        return ZonedDateTime::plusSeconds;
    }

    public static String getDateTime() {
        return formatDate(new Date());
    }

    public static Date getCleanDate() {
        return getCleanDate(new Date());
    }

    public static Date getCleanDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Optional.ofNullable(date).orElse(new Date()));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static ZonedDateTime getLegalDate(String dateStr) {
        dateStr = StringUtils.isBlank(dateStr) ? "2000-01-01 00:00:00" : dateStr;
        dateStr = 10 == dateStr.length() ? dateStr + " 00:00:00" : dateStr;
        return parseDate(dateStr);
    }

    public enum DATE_TYPE {
        YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND
    }
}
