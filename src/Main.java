import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = List.of(1, 2, 3, 4);
        System.out.println(a.stream().filter(n -> n == 5).toList());
    }
}
