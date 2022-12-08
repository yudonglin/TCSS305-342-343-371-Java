// Figures A.5-A.6

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

class InputLib {
    /**
     * fopen opens infile or System.in if infile == "-".
     */
    public static BufferedReader fopen(final String infile) {
        final BufferedReader inbuf;
        try {
            final InputStream instream;
            if (infile.equals("-"))
                instream = System.in;
            else
                instream = new FileInputStream(infile);

            final InputStreamReader in = new InputStreamReader(instream);
            inbuf = new BufferedReader(in);
        } catch (final java.io.IOException e) {
            throw new InputError(e.getMessage());
        }

        return inbuf;
    }

    /**
     * fclose closes inbuf, which fopen returned earlier.
     */
    public static void fclose(final BufferedReader inbuf) {
        try {
            inbuf.close();
        } catch (final java.io.IOException e) {
            throw new InputError(e.getMessage());
        }
    }

    /**
     * getLine reads and returns the next line from inbuf.
     * It returns null on EOF; it's OK to keep calling
     * after EOF was reached.
     * Note that getLine() returns a String with no CR, whether
     * the line ends with a CR or ends via EOF.
     * Thus it is not quite like fgets() in C.
     */
    public static String getLine(final BufferedReader inbuf) {
        final String line;
        try {
            line = inbuf.readLine();
        } catch (final java.io.IOException e) {
            throw new InputError(e.getMessage());
        }

        return line;
    }

    static class InputError extends Error {
        public InputError(final String s) {
            super(s);
        }
    }

}
