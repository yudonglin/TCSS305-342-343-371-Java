import java.util.Arrays;

public class HashTable {

    public static final int SIZE = 50_000;
    private final Anagram[] table;
    private int collisions;

    public HashTable() {
        this.table = new Anagram[SIZE];
        this.collisions = 0;
    }

    public int hashCode(String key) {
        int prime_index = 0;
        for (char c : key.toCharArray()) {
            prime_index += c * c;
        }
        return prime_index;
    }

    private int findValidIndex(String key, int hashCodeOfKey) {
        var keyInCharArray = key.toCharArray();
        Arrays.sort(keyInCharArray);
        var keyInString = Arrays.toString(keyInCharArray);
        hashCodeOfKey = Math.abs(hashCodeOfKey) % SIZE;
        if (this.table[hashCodeOfKey] == null || this.table[hashCodeOfKey].getKey().equals(keyInString)) {
            return hashCodeOfKey;
        } else {
            collisions++;
            int index;
            for (int i = 1; i < SIZE - 1; i++) {
                index = (hashCodeOfKey + i) % SIZE;
                if (this.table[index] == null || this.table[index].getKey().equals(keyInString)) {
                    return index;
                }
            }
        }
        return -1;
    }

    private void add(String key, int hashCodeOfKey) {
        var index = this.findValidIndex(key, hashCodeOfKey);
        if (this.table[index] == null) {
            this.table[index] = new Anagram(key);
        }
        this.table[index].addValue(key);
    }

    public void add(String key) {
        var key2 = key.toLowerCase();
        this.add(key2, this.hashCode(key2));
    }

    public void addUsingJavaHashing(String key) {
        var key2 = key.toLowerCase();
        this.add(key2, key2.hashCode());
    }

    public int search(String word) {
        var key2 = word.toLowerCase();
        var index = this.findValidIndex(key2, this.hashCode(key2));
        return (index < 0 || this.table[index] == null) ? -1 : index;
    }

    public Anagram get(int index) {
        return table[index];
    }

    public int getCollisions() {
        return collisions;
    }
}
