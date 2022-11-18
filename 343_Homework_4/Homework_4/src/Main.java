import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //System.out.println(ChangeMaking(new int[]{1, 3, 4}, 6));
        //System.out.println(ChangeMaking(new int[]{1, 5, 6, 8}, 11));

        final char[] pattern = "ACATCTCA".toCharArray();
        final char[] search = "GAGTAATCCTTCACTTCAAGGCCAGTCTTCACATCTCATCAGA".toCharArray();
        System.out.println(HorspoolMatching(pattern, search));
    }

    private static int ChangeMaking(int[] dimensions, int n) {
        int[] F = new int[n + 1];
        int m = dimensions[dimensions.length - 1];
        for (int i = 1; i <= n; i++) {
            int temp = Integer.MAX_VALUE;
            int j = 0;
            while (j <= m && j < dimensions.length && i >= dimensions[j]) {
                temp = Integer.min(F[i - dimensions[j]], temp);
                j++;
            }
            F[i] = temp + 1;
        }
        return F[n];
    }

    private static HashMap<Character, Integer> ShiftTable(char[] P) {
        final HashMap<Character, Integer> table = new HashMap<>();
        for (final Character _c : P) {
            table.put(_c, P.length);
        }

        for (int i = 0; i <= P.length - 2; i++) {
            table.put(P[i], P.length - 1 - i);
        }
        return table;
    }

    private static int HorspoolMatching(char[] P, char[] T) {
        final HashMap<Character, Integer> table = ShiftTable(P); //generate Table of shifts
        int m = P.length;
        int n = T.length;
        int i = m - 1;
        while (i <= n - 1) {
            int k = 0;
            while (k <= m - 1 && P[m - 1 - k] == T[i - k]) {
                k = k + 1;
            }
            if (k == m) {
                return i - m + 1;
            } else {
                i = i + table.get(T[i]);
            }
            System.out.println(i);
        }
        return -1;
    }
}