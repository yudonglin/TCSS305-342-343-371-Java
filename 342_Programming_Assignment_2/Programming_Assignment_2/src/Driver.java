public class Driver {
    public static void main(String[] args) {
        var a = new ArrayHeap<Integer>(100);
        a.add(new Node<>(1, 10));
        a.add(new Node<>(1, 15));
        a.add(new Node<>(1, 5));
        a.add(new Node<>(1, 9));
        a.add(new Node<>(1, 4));
        a.add(new Node<>(1, 32));
        a.add(new Node<>(1, 1));

        System.out.println(a.size());

        while (!a.isEmpty()) {
            var b = a.removeMin();
            System.out.println(b);
        }

        System.out.println(a);
    }
}
