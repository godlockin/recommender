package com.st.recommender.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j
public class DataUtils {

    public static boolean isAllCollectionsEmpty(Collection... collections) {
        return isAllCollectionMatch(Stream.of(collections), CollectionUtils::isEmpty);
    }

    public static boolean isAnyCollectionEmpty(Collection... collections) {
        return isAnyCollectionMatch(Stream.of(collections), CollectionUtils::isEmpty);
    }

    public static boolean isAllCollectionMatch(Stream<Collection> stream, Predicate<Collection> predicate) {
        return stream.allMatch(predicate);
    }

    public static boolean isAnyCollectionMatch(Stream<Collection> stream, Predicate<Collection> predicate) {
        return stream.anyMatch(predicate);
    }

    public static <T> T getNotNullValue(Map base, String key, Class<T> clazz, Object defaultValue) {
        return handleNullValue(base.get(key), clazz, defaultValue);
    }

    public static <T> T handleNullValue(Object base, Class<T> clazz, Object defaultValue) {
        return clazz.cast(Optional.ofNullable(base).orElse(defaultValue));
    }

    public static <E> void forEach(Integer maxIndex, Iterable<? extends E> elements, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        int index = 0;
        for (E element : elements) {
            action.accept(index++, element);
            if (maxIndex > 0 && maxIndex < index) {
                break;
            }
        }
    }

    public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new Iterator<T>() {
                            public T next() {
                                return e.nextElement();
                            }

                            public boolean hasNext() {
                                return e.hasMoreElements();
                            }

                            public void forEachRemaining(Consumer<? super T> action) {
                                while (e.hasMoreElements()) action.accept(e.nextElement());
                            }
                        },
                        Spliterator.ORDERED), false);
    }

    public static <T> List<T> handlePaging(int page, int size, List<T> dataList) {
        int total = dataList.size();
        page = Math.max(1, page);
        int start = (page - 1) * size;
        int end = Math.min(total, page * size);
        return (start <= end) ? dataList.subList(start, end) : new ArrayList<>();
    }
}
