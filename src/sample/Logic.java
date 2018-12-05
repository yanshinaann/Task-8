package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Logic {
    static List process(List<Integer> list) {
        int maxValue = findMax(list);
        int minValue = findMin(list);
        return swapElements(list, minValue, maxValue);
    }

    private static List<Integer> swapElements(List<Integer> list, int minIndex, int maxIndex) {
        Iterator nextIterator = list.listIterator();
        int nextIndex = 0;
        Iterator previousIterator = list.listIterator(list.size());
        int previousIndex = list.size() - 1;
        int tempNext, tempPrevious;

        for (int i = 0; i < minIndex; i++, nextIndex++) nextIterator.next();
        for (int i = list.size(); i >= maxIndex; i--, previousIndex--) ((ListIterator) previousIterator).previous();

        for (int i = nextIndex; i < previousIndex; ++i, previousIndex--, nextIndex++) {
            tempNext = (int) nextIterator.next();
            tempPrevious = (int) ((ListIterator) previousIterator).previous();
            list.set(nextIndex, tempPrevious);
            list.set(previousIndex, tempNext);
        }
        return list;
    }

    public static List<Integer> readFromFile(String path) throws FileNotFoundException {

        List<Integer> list = new ArrayList();
        File file = new File(path);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            list.add(Integer.parseInt(scan.next()));
        }
        return list;
    }

    private static int findMax(List<Integer> list) {
        int maxValue = list.get(0);
        int value;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = iterator.next();
            if (value > maxValue) maxValue = value;
        }
        return maxValue;
    }

    private static int findMin(List<Integer> list) {
        int minValue = list.get(0);
        int value;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = iterator.next();
            if (value < minValue) minValue = value;
        }
        return minValue;
    }

    private static int firstIndexOf(List<Integer> list, int maxValue) {
        int firstIndexValue = 0;
        int value;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            value = iterator.next();
            firstIndexValue++;
            if (value == maxValue) return firstIndexValue;
        }
        return 0;
    }

    private static int lastIndexOf(List<Integer> list, int minValue) {
        int lastIndexValue = list.size();
        int value;
        ListIterator<Integer> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            value = iterator.previous();
            lastIndexValue--;
            if (value == minValue) return lastIndexValue;
        }
        return 0;
    }
}
