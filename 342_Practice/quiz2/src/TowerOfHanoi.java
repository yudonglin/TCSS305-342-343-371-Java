public class TowerOfHanoi {
    public static void main(String[] args) {
        var s1 = new Stack();
        var s2 = new Stack();
        var s3 = new Stack();

        for (int i = 0; i < 10; i++) {
            s1.push(String.valueOf(i));
        }

        move(s1.size(), s1, s3, s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    public static void move(int n, Stack source, Stack target, Stack auxiliary) {
        if (n > 0) {
            move(n - 1, source, auxiliary, target);
            target.push(source.pop());
            move(n - 1, auxiliary, target, source);
        }
    }

}
