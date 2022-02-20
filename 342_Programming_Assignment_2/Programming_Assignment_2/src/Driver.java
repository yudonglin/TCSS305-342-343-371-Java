import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Driver {
    public static void main(String[] args) throws IOException {
        // read data from the input file
        var inputFile = new BufferedReader(new FileReader("input.txt"));
        var inputData = inputFile.lines().toList();
        inputFile.close();

        StringBuilder outputLines = new StringBuilder();

        for (String _str : inputData) {
            if (_str.length() > 0) {
                HuffmanTree hfmTree = new HuffmanTree();
                var result = hfmTree.encode(_str);
                outputLines.append("[" + _str + "]\n\n");
                outputLines.append("======================================\n");
                outputLines.append("char    frequency   code\n");
                outputLines.append("--------------------------------------\n");
                for (char key : result.frequencyTable.keySet()) {
                    outputLines.append(key + "        " + result.frequencyTable.get(key).frequency + "        " + result.frequencyTable.get(key).code + "\n");
                }
                outputLines.append("======================================\n");
                outputLines.append("[Encoded Bit: " + result.encodedBit + "][" + result.encodedBit.length() + "]\n");
                var decodedMsg = hfmTree.decode(result.encodedBit);
                outputLines.append("[Decoded String: " + decodedMsg + "][" + decodedMsg.length() * 8 + "]\n");
                outputLines.append("[Compression Ratio: " + (float) decodedMsg.length() * 8 / result.encodedBit.length() + "]\n");
                outputLines.append("\n");
            }
        }
        // write the result tot the output file
        Files.writeString(Paths.get("output.txt"), outputLines.toString(), StandardCharsets.UTF_8);

    }
}
