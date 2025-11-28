package org.example.strategy.sorting;

import java.util.List;

public class BubbleSort extends SortingStrategy {
    public BubbleSort() {
        this.name = "BubbleSort";
    }

    @Override
    public void sort(List<Integer> integers) {
        boolean swapped;
        for (int i = 0; i < integers.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < integers.size() - 1 - i; j++) {
                if (integers.get(j) > integers.get(j + 1)) {
                    swap(integers, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
}
