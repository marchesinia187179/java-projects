package org.example.strategy.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.random.RandomGenerator;

public class SortingStrategyMain {
    private static List<Integer> createList(List<Integer> list, int size, int minValue, int maxValue) {
        for (int i = 0; i < size; i++) {
            RandomGenerator rnd = RandomGenerator.getDefault();
            list.add(rnd.nextInt(minValue, maxValue));
        }
        return list;
    }

    public static void main(String[] args) {
        SorterManager sorterManager = new SorterManager(new LinkedList<>());
        List<Integer> listForBubbleSort = createList(new LinkedList<>(), 10, 0, 100);
        List<Integer> listForSelectionSort = createList(new LinkedList<>(), 10, 0, 100);

        // BubbleSort test
        sorterManager.setIntegers(listForBubbleSort);
        sorterManager.sort(new BubbleSort());

        System.out.println("---");

        // SelectionSort test
        sorterManager.setIntegers(listForSelectionSort);
        sorterManager.sort(new SelectionSort());
    }
}
