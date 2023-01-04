import java.util.Objects;

public class Main {
    public static void main(String[] args){
        int a = 10;
        int b = 20;
        Integer a2 = 10;
        Integer b2 = 20;

        System.out.println(Objects.hashCode(a));
        System.out.println(Objects.hashCode(b));
        System.out.println(a2.hashCode());
        System.out.println(b2.hashCode());
    }
}
