import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Driver {
    // how many times you want to loop to get an average result
    static final short LOOP_TIMES = 5;
    static long javaHashMapExecutingTime = 0;
    static long javaHashSetExecutingTime = 0;
    static long customHashMapExecutingTime = 0;
    static List<String> words;

    public static void calculatingJavaHashMapExecutingTime() {
        HashMap<String, String> javaHashMap = new HashMap<>(HashTable.SIZE);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            javaHashMap.clear();
            for (String word : new ArrayList<>(words)) {
                var _lower_case_char_array = word.toLowerCase().toCharArray();
                Arrays.sort(_lower_case_char_array);
                javaHashMap.put(Arrays.toString(_lower_case_char_array), "");
            }
        }
        javaHashMapExecutingTime = (System.currentTimeMillis() - startTime) / LOOP_TIMES;
    }

    public static void calculatingJavaHashSetExecutingTime() {
        HashSet<String> javaHashSet = new HashSet<>(HashTable.SIZE);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            javaHashSet.clear();
            for (String word : new ArrayList<>(words)) {
                var _lower_case_char_array = word.toLowerCase().toCharArray();
                Arrays.sort(_lower_case_char_array);
                javaHashSet.add(Arrays.toString(_lower_case_char_array));
            }
        }
        javaHashSetExecutingTime = (System.currentTimeMillis() - startTime) / LOOP_TIMES;
    }


    public static void main(String[] args) throws IOException {
        // read data from the input file
        var inputFile = new BufferedReader(new FileReader("input.txt"));
        var inputData = inputFile.lines().toList();
        // close the input file
        inputFile.close();

        // read words from the Word file
        inputFile = new BufferedReader(new FileReader("words.txt"));
        words = inputFile.lines().toList();
        inputFile.close();

        // test the speed of java hash map
        calculatingJavaHashMapExecutingTime();

        // test the speed of java hash set
        calculatingJavaHashSetExecutingTime();

        // init the custom hash table
        var dictTable = new HashTable();

        // add everything again but use the java String's hashing method
        for (String word : new ArrayList<>(words)) {
            dictTable.addUsingJavaHashing(word);
        }
        // print out the collisions for java hashing method
        int javaHashingCollisionCount = dictTable.getCollisions();

        // add everything again but use the custom hashing method
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LOOP_TIMES; i++) {
            // reset the HashTable
            dictTable = new HashTable();
            for (String word : new ArrayList<>(words)) {
                dictTable.add(word);
            }
        }
        customHashMapExecutingTime = (System.currentTimeMillis() - startTime) / LOOP_TIMES;
        int customHashingCollisionCount = dictTable.getCollisions();

        // output
        StringBuilder outputLines = new StringBuilder();
        outputLines.append("Part 1:\nCollision Count: ");
        outputLines.append(customHashingCollisionCount);
        outputLines.append("\nJava's Collision Count: ");
        outputLines.append(javaHashingCollisionCount);
        outputLines.append("\nTime Comparison\n---------------------------\nData structure  |  Time(ms)\n---------------------------");
        outputLines.append("\nJava Hash Map        ");
        outputLines.append(javaHashMapExecutingTime);
        outputLines.append("\nJava Hash Set        ");
        outputLines.append(javaHashSetExecutingTime);
        outputLines.append("\nself Hash Table      ");
        outputLines.append(customHashMapExecutingTime);
        outputLines.append("\n\nPart 2:\n");
        int indexOfKey;
        for (String key : inputData) {
            indexOfKey = dictTable.search(key);
            outputLines.append(key);
            outputLines.append(" ");
            if (indexOfKey < 0) {
                outputLines.append("0\n");
            } else {
                var theResults = dictTable.get(indexOfKey).getValues();
                outputLines.append(theResults.size());
                for (String word : theResults) {
                    outputLines.append(" ");
                    outputLines.append(word);
                }
                outputLines.append("\n");
            }
        }
        // write the result to the output file
        Files.writeString(Paths.get("output.txt"), outputLines.toString(), StandardCharsets.UTF_8);
    }
}
