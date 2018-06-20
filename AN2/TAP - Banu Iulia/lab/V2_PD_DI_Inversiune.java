import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Se consideră un vector cu n elemente. Se numeşte inversiune semnificativă a vectorului o pereche perechi (i, j) cu proprietatea că i < j şi ai > 2*aj. Să de determine numărul de inversiuni semnificative din vector.

public class Inversiune {

    public static final boolean showDebug = false;

    private int inversiuni = 0;

    private List<Integer> merge(List<Integer> left, List<Integer> right) {

        List<Integer> generatedList = new ArrayList<>();

        List<Integer> sortedLeft = smartMergeSort(new ArrayList<>(left));
        List<Integer> sortedRight = smartMergeSort(new ArrayList<>(right));

        while (!sortedLeft.isEmpty() && !sortedRight.isEmpty()) {
            if (showDebug)
                System.out.println(" * left: " + sortedLeft + " right: " + sortedRight);
            boolean selectingRight = false;
            int selectedRightElement = sortedRight.get(0);
            Integer selectedLeftElement = sortedLeft.get(0);
            if (selectedRightElement < selectedLeftElement)
                selectingRight = true;

            if (selectingRight) {
                int startIndex = binarySearchLeft(selectedRightElement * 2, sortedLeft);
                int result = sortedLeft.size() - startIndex;
                inversiuni += result;
            }

            if (selectingRight) {
                generatedList.add(selectedRightElement);
                sortedRight.remove(0);
            } else {
                generatedList.add(selectedLeftElement);
                sortedLeft.remove(0);
            }
        }
        generatedList.addAll(sortedLeft.stream().collect(Collectors.toList()));
        generatedList.addAll(sortedRight.stream().collect(Collectors.toList()));
        return generatedList;
    }

    private int biggestPow2(int size) {
        if (size <= 1)
            return size;
        int max = 1;
        while (max * 2 < size)
            max *= 2;
        return max;
    }

    private int binarySearchLeft(int element, List<Integer> list) {
        int maxN = biggestPow2(list.size());
        int number = 0;
        while (maxN >= 1) {
            if (list.get(number + maxN - 1) < element)
                number += maxN;
            maxN /= 2;
        }
        return number;
    }

    private List<Integer> smartMergeSort(List<Integer> elements) {
        if (elements.size() <= 1)
            return elements;

        int middle = elements.size() / 2;

        List<Integer> left = elements.subList(0, middle);
        List<Integer> right = elements.subList(middle, elements.size());
        if (showDebug)
            System.out.println("smartMergeSort " + left + " " + right + " => ");
        List<Integer> merge = merge(left, right);
        if (showDebug)
            System.out.println("smartMergeSort " + left + " " + right + " => " + merge);
        return merge;
    }
    public String toString() {
        return "Divide et Impera Strategy";
    }
    public void setElements(ArrayList<Integer> elements) {
        smartMergeSort(elements);
    }
    public Integer select() {
        return inversiuni;
    }
    public boolean canSelect() {
        return true;
    }
}
