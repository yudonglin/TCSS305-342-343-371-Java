package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Driver {

    public static void main(String[] args) throws IOException {
        // read data from the input file
        var inputData = new BufferedReader(new FileReader("input.txt")).lines().toList();
        // a ArrayList that will be used to hold all output lines
        ArrayList<String> outputLines = new ArrayList<>(30);
        // handle the test 1
        outputLines.add("Test 1: Output for Encoding");
        for (int i = 1; i < 4; i++) {
            var _temp = new StringBuilder();
            var _temp_linked_list = RLE.convert_string_charters_to_LinkedList(inputData.get(i));
            var encoded_msg = RLE.encode(_temp_linked_list);
            _temp.append(_temp_linked_list.toStringWithLength());
            _temp.append(" [");
            _temp.append(encoded_msg);
            _temp.append(":");
            _temp.append(encoded_msg.length());
            _temp.append("] [");
            _temp.append((float) _temp_linked_list.size() / encoded_msg.length());
            _temp.append("]");
            outputLines.add(_temp.toString());
        }
        // handle the test 2
        outputLines.add("Test 2: Output for Decoding");
        for (int i = 5; i < 8; i++) {
            outputLines.add("[" + inputData.get(i) + "] " + RLE.decode(inputData.get(i)));
        }
        // handle the test 3
        outputLines.add("Test 3: Output for equality");
        for (int i = 9; i < 13; i++) {
            var _temp = inputData.get(i).split("\\s+");
            var elem2 = _temp.length > 1 ? _temp[1] : "";
            outputLines.add("[" + _temp[0] + "]" + " [" + elem2 + "] [" + RLE.equal(_temp[0], elem2) + "]");
        }
        // write the result tot the output file
        Files.write(Paths.get("output.txt"), outputLines, StandardCharsets.UTF_8);
    }

}
