public class Exp {

    public static void main(String[] args) {
        var q1 = new Queue();
        for (int i = 0; i < 5; i++) {
            q1.push(String.valueOf(i));
        }
        System.out.println("before reversing: " + q1);
        reverseQueue(q1);
        System.out.println("after reversing: " + q1);

        var s1 = new Stack();
        for (int i = 0; i < 5; i++) {
            s1.push(String.valueOf(i));
        }
        System.out.println("before reversing: " + s1);
        for (int i = 0; i < 5; i++) {
            s1.push(s1.pop());
        }
        reverseStack(s1);
        System.out.println("after reversing: " + s1);
    }

    public static void reverseQueue(Queue _q) {
        if (_q.size() > 0) {
            var temp = _q.pop();
            reverseQueue(_q);
            _q.push(temp);
        }
    }

    public static void reverseStack(Stack _q) {
        if (_q.size() > 0) {
            var temp = _q.pop();
            reverseStack(_q);
            appendStackLeft(_q, temp);
        }
    }

    public static void appendStackLeft(Stack _q, String value) {
        if (_q.size() == 0) {
            _q.push(value);
        } else {
            var temp = _q.pop();
            appendStackLeft(_q, value);
            _q.push(temp);
        }
    }
}
