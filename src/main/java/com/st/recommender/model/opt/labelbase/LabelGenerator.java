package com.st.recommender.model.opt.labelbase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelGenerator {
    private int idx;
    private String label;
    private AtomicInteger valuesIndexer = new AtomicInteger(0);
    private AtomicInteger valuesCounter = new AtomicInteger(0);
    private ConcurrentHashMap<String, MutablePair<Integer, AtomicInteger>> valueItemCounter = new ConcurrentHashMap<>();

    public LabelGenerator(String label, int idx) {
        this.idx = idx;
        this.label = label;
    }

    public void appendValue(String value) {
        if (!valueItemCounter.containsKey(value)) {
            valueItemCounter.put(value, MutablePair.of(valuesIndexer.getAndIncrement(), new AtomicInteger(0)));
        }
        valueItemCounter.get(value).getValue().incrementAndGet();
        valuesCounter.incrementAndGet();
    }
}
