package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Driver {

    // a ArrayList that will be used to hold all output lines
    private static final ArrayList<String> outputLines = new ArrayList<>(15);

    public static void main(String[] args) throws IOException {
        // read data from the input file
        var inputData = new BufferedReader(new FileReader("input.txt")).lines().toList();
        // generate results for tests
        generateTest1(inputData, 1, 4);
        generateTest2(inputData, 5, 8);
        generateTest3(inputData, 9, 13);
        // write the result tot the output file
        Files.write(Paths.get("output.txt"), outputLines, StandardCharsets.UTF_8);
    }

    // handle the test 1 - Output for Encoding
    public static void generateTest1(List<String> inputData, int start, int end) {
        outputLines.add("Test 1: Output for Encoding");
        for (int i = start; i < end; i++) {
            var _temp = new StringBuilder();
            // covert string format linked list to actual linked list
            var _temp_linked_list = RLE.convert_string_charters_to_LinkedList(inputData.get(i));
            // the message that is encoded using the linked list
            var encoded_msg = RLE.encode(_temp_linked_list);
            _temp.append(_temp_linked_list.toStringWithLength());
            _temp.append(" [");
            _temp.append(encoded_msg);
            _temp.append(":");
            _temp.append(encoded_msg.length());
            _temp.append("] [");
            _temp.append((float) _temp_linked_list.size() / encoded_msg.length());
            _temp.append("]");
            // put the data into the output array list
            outputLines.add(_temp.toString());
        }
    }

    // handle the test 2 - Input for Decoding
    public static void generateTest2(List<String> inputData, int start, int end) {
        outputLines.add("Test 2: Output for Decoding");
        for (int i = start; i < end; i++) {
            outputLines.add("[" + inputData.get(i) + "] " + RLE.decode(inputData.get(i)));
        }
    }

    // handle the test 3 - Input for Equality
    public static void generateTest3(List<String> inputData, int start, int end) {
        outputLines.add("Test 3: Output for equality");
        for (int i = start; i < end; i++) {
            // split the text based on space
            var _temp = inputData.get(i).split("\\s+");
            // ensure the temp array is not empty
            if (_temp.length > 0) {
                // ensure the second element is not null
                var elem2 = _temp.length > 1 ? _temp[1] : "";
                outputLines.add("[" + _temp[0] + "]" + " [" + elem2 + "] [" + RLE.equal(_temp[0], elem2) + "]");
            } else {
                throw new IllegalAccessError("Cannot preform comparison when due to empty input text");
            }
        }
    }

}
