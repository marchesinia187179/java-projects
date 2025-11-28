package org.example.strategy.sorting;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class SortingStrategy {
    protected String name;

    protected void swap(List<Integer> integers, int firstIndex, int secondIndex) {
        int tmp = integers.get(firstIndex);
        integers.set(firstIndex, integers.get(secondIndex));
        integers.set(secondIndex, tmp);
    }

    public abstract void sort(List<Integer> integers);
}
