import java.util.Arrays;

final public class MultiplicationOfLargeIntegers {
    static int multiple(String a, String b) {

        final int n = Math.max(a.length(), b.length());

        a = leftExtend(a, n);
        b = leftExtend(b, n);

        final int half = n / 2;

        final int c0, c1, c2;

        if (half > 1) {
            final String a1 = a.substring(0, half);
            final String b1 = b.substring(0, half);
            final String a0 = a.substring(half);
            final String b0 = b.substring(half);

            c2 = multiple(a1, b1);
            c0 = multiple(a0, b0);
            c1 = multiple(
                String.valueOf(Integer.parseInt(a1) + Integer.parseInt(a0)),
                String.valueOf(Integer.parseInt(b1) + Integer.parseInt(b0))
            ) - (c2 + c0);
        } else {
            final int a1 = Character.getNumericValue(a.charAt(0));
            final int b1 = Character.getNumericValue(b.charAt(0));
            final int a0 = Character.getNumericValue(a.charAt(1));
            final int b0 = Character.getNumericValue(b.charAt(1));

            c2 = a1 * b1;
            c0 = a0 * b0;
            c1 = (a1 + a0) * (b1 + b0) - (c2 + c0);
        }

        final int c = c2 * (int) Math.pow(10, n) + c1 * (int) Math.pow(10, half) + c0;

        if (Integer.parseInt(a) * Integer.parseInt(b) != c) {
            throw new RuntimeException("Warning, not match result!");
        }

        return c;
    }

    private static String leftExtend(final String num, final int n) {
        if (num.length() < n) {
            final StringBuilder builder = new StringBuilder(num);
            char[] offset = new char[n - num.length()];
            Arrays.fill(offset, '0');
            builder.insert(0, offset);
            return builder.toString();
        }
        return num;
    }
}
