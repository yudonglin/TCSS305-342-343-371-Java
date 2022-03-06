import java.util.Arrays;

public class HashTable {

    public static final int SIZE = 50_000;
    private final Anagram[] table;
    private int collisions;

    public HashTable() {
        this.table = new Anagram[SIZE];
        this.collisions = 0;
    }

    private int hashCode(String key, boolean usingJavaHashing) {
        var keyInCharArray = key.toCharArray();
        Arrays.sort(keyInCharArray);
        var keyInString = Arrays.toString(keyInCharArray);
        int prime_index = 0;
        if (!usingJavaHashing) {
            for (char c : keyInCharArray) {
                prime_index += c * c;
            }
        } else {
            prime_index = Math.abs(key.hashCode());
        }
        prime_index %= SIZE;
        if (this.table[prime_index] == null || this.table[prime_index].getKey().equals(keyInString)) {
            return prime_index;
        } else {
            collisions++;
            int index;
            for (int i = 1; i < SIZE - 1; i++) {
                index = (prime_index + i) % SIZE;
                if (this.table[index] == null || this.table[index].getKey().equals(keyInString)) {
                    return index;
                }
            }
        }
        return -1;
    }

    public void add(String key) {
        var key2 = key.toLowerCase();
        var index = this.hashCode(key2, false);
        if (this.table[index] == null) {
            var keyInCharArray = key2.toCharArray();
            Arrays.sort(keyInCharArray);
            this.table[index] = new Anagram(Arrays.toString(keyInCharArray));
        }
        this.table[index].addValue(key2);
    }

    public void add(String key, boolean usingJavaHashing) {
        var key2 = key.toLowerCase();
        var index = this.hashCode(key2, usingJavaHashing);
        if (this.table[index] == null) {
            var keyInCharArray = key2.toCharArray();
            Arrays.sort(keyInCharArray);
            this.table[index] = new Anagram(Arrays.toString(keyInCharArray));
        }
        this.table[index].addValue(key2);
    }

    public int search(String word) {
        var index = this.hashCode(word.toLowerCase(), false);
        return (index < 0 || this.table[index] == null) ? -1 : index;
    }

    public Anagram get(int index) {
        return table[index];
    }

    public int getCollisions() {
        return collisions;
    }
}
