package org.example.strategy.sorting;

import java.util.List;

public class SelectionSort extends SortingStrategy {
    public SelectionSort() {
        this.name = "SelectionSort";
    }

    @Override
    public void sort(List<Integer> integers) {
        for (int i = 0; i < integers.size() - 1; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < integers.size(); j++) {
                if (integers.get(j) < integers.get(minimumIndex)) minimumIndex = j;
            }

            if (integers.get(minimumIndex) < integers.get(i)) swap(integers, i, minimumIndex);
        }
    }
}
