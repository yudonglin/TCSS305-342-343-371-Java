import java.util.ArrayList;
import java.util.Collections;

final public class QuickSort {

    static void sort(final ArrayList<Character> arr) {
        ArrayList<Character> testCopy = new ArrayList<>(arr);
        sort(arr, 0, arr.size() - 1);
        Collections.sort(testCopy);
        if (!testCopy.equals(arr)) {
            throw new RuntimeException("Warning, not match result!");
        }
    }

    private static void sort(final ArrayList<Character> arr, final int l, final int r) {
        if (l < r) {
            int s = hoarePartition(arr, l, r);
            sort(arr, l, s - 1);
            sort(arr, s + 1, r);
        }
    }

    private static int hoarePartition(final ArrayList<Character> arr, final int l, final int r) {
        char p = arr.get(l);
        int i = l;
        int j = r + 1;
        do {
            do {
                i++;
            } while (!(arr.get(i) >= p));
            do {
                j--;
            } while (!(arr.get(j) <= p));
            Collections.swap(arr, i, j);
        } while (!(i >= j));

        Collections.swap(arr, i, j);
        Collections.swap(arr, l, j);

        return j;
    }
}
