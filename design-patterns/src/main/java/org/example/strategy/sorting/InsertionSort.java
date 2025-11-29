package org.example.strategy.sorting;

import java.util.List;

public class InsertionSort extends SortingStrategy {
    public InsertionSort() {
        this.name = "InsertionSort";
    }

    @Override
    public void sort(List<Integer> integers) {
        for (int i = 1; i < integers.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (integers.get(j - 1) < integers.get(j)) break;
                swap(integers, j, j - 1);
            }
        }
    }
}
