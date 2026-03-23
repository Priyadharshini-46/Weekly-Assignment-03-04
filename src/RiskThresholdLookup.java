import java.util.*;

public class RiskThresholdLookup {

    // ----------- LINEAR SEARCH (Unsorted) -----------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("Found: " + found);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // ----------- BINARY SEARCH INSERTION POINT -----------
    public static int insertionPoint(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length;

        while (low < high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }

        return low; // lower_bound
    }

    // ----------- FLOOR (largest ≤ target) -----------
    public static Integer floor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // ----------- CEILING (smallest ≥ target) -----------
    public static Integer ceiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid] == target)
                return arr[mid];

            if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    // Counter helper
    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        // -------- Linear Search --------
        linearSearch(unsorted, target);

        // -------- Sort for Binary Search --------
        Arrays.sort(unsorted);
        System.out.println("Sorted Risks: " + Arrays.toString(unsorted));

        Counter counter = new Counter();

        // -------- Insertion Point --------
        int pos = insertionPoint(unsorted, target, counter);
        System.out.println("\nInsertion Point (lower_bound): index " + pos);

        // -------- Floor & Ceiling --------
        Integer floorVal = floor(unsorted, target, counter);
        Integer ceilVal = ceiling(unsorted, target, counter);

        System.out.println("Floor (≤ " + target + "): " + floorVal);
        System.out.println("Ceiling (≥ " + target + "): " + ceilVal);

        System.out.println("Comparisons (Binary Ops): " + counter.count);
        System.out.println("Time Complexity: O(log n)");
    }
}