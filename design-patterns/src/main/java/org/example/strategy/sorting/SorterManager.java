package org.example.strategy.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SorterManager {
    private List<Integer> integers;

    void sort(SortingStrategy sorter) {
        if (integers.isEmpty()) {
            System.out.println("The list is empty");
            return;
        }

        System.out.println("Input list: " + integers);
        long timeStart = System.nanoTime();

        sorter.sort(integers);

        long nanoTime = System.nanoTime() - timeStart;
        double millisTime = nanoTime / Math.pow(10,6);
        double secondsTime = nanoTime / Math.pow(10,9);
        System.out.println(sorter.getName() + " time: "
                + nanoTime + "ns | "
                + millisTime + "ms | "
                + secondsTime + "s"
        );
        System.out.println("Output list: " + integers);
    }
}
