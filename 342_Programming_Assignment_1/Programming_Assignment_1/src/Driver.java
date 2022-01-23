package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        var plainTexts = new BufferedReader(new FileReader("input.txt"));
        var text = plainTexts.readLine();
        while (text != null) {
            System.out.println(text);
            text = plainTexts.readLine();
        }
    }

}
