import java.util.*;

public class AccountLookupSystem {

    // ----------- LINEAR SEARCH (First & Last Occurrence) -----------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First occurrence: " + first);
        System.out.println("Last occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Time Complexity: O(n)\n");
    }

    // ----------- BINARY SEARCH (Find one occurrence) -----------
    public static int binarySearch(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    // ----------- COUNT OCCURRENCES USING BINARY SEARCH -----------
    public static int countOccurrences(String[] arr, String target, Counter counter) {
        int index = binarySearch(arr, target, counter);
        if (index == -1) return 0;

        int count = 1;

        // left side
        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            counter.count++;
            count++;
            i--;
        }

        // right side
        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            counter.count++;
            count++;
            i++;
        }

        return count;
    }

    // Counter helper
    static class Counter {
        int count = 0;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        // -------- Linear Search --------
        linearSearch(logs, "accB");

        // -------- Sort before Binary Search --------
        Arrays.sort(logs);
        System.out.println("Sorted Logs: " + Arrays.toString(logs));

        // -------- Binary Search --------
        Counter counter = new Counter();
        int index = binarySearch(logs, "accB", counter);

        System.out.println("\nBinary Search:");
        System.out.println("Found at index: " + index);

        int totalCount = countOccurrences(logs, "accB", counter);
        System.out.println("Total occurrences: " + totalCount);
        System.out.println("Comparisons: " + counter.count);
        System.out.println("Time Complexity: O(log n) for search + O(k) for duplicates");
    }
}