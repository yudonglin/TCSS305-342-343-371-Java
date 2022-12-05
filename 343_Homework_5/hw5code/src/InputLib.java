// Figures A.5-A.6

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

class InputLib {
    /**
     * fopen opens infile or System.in if infile == "-".
     */
    public static BufferedReader fopen(String infile) {
        BufferedReader inbuf;
        try {
            InputStream instream;
            if (infile.equals("-"))
                instream = System.in;
            else
                instream = new FileInputStream(infile);

            InputStreamReader in = new InputStreamReader(instream);
            inbuf = new BufferedReader(in);
        } catch (java.io.IOException e) {
            throw new InputError(e.getMessage());
        }

        return inbuf;
    }

    /**
     * fclose closes inbuf, which fopen returned earlier.
     */
    public static void fclose(BufferedReader inbuf) {
        try {
            inbuf.close();
        } catch (java.io.IOException e) {
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
    public static String getLine(BufferedReader inbuf) {
        String line;
        try {
            line = inbuf.readLine();
        } catch (java.io.IOException e) {
            throw new InputError(e.getMessage());
        }

        return line;
    }

    static class InputError extends Error {
        public InputError(String s) {
            super(s);
        }
    }

}
