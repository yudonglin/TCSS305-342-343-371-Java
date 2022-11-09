import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuickSort.sort(new ArrayList<>(List.of(new Character[]{'U', 'W', 'T', 'A', 'C', 'O', 'M', 'A'})));
        MultiplicationOfLargeIntegers.multiple("23", "14");
        System.out.println(MultiplicationOfLargeIntegers.multiple("2101", "1130"));
    }
}