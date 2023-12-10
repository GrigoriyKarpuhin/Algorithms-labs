import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        StringBuilder balls = new StringBuilder();
        int result = 0;
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            balls.append(scan.nextInt());
        }
        scan.close();
        while (true) {
            boolean changed = false;
            for (int i = 0; i < balls.length() - 2; i++) {
                int first = balls.charAt(i);
                int second = balls.charAt(i + 1);
                int third = balls.charAt(i + 2);
                if (first == second && second == third) {
                    int quantity = 3;
                    while (i + quantity < balls.length() && balls.charAt(i + quantity) == first) {
                        quantity++;
                    }
                    changed = true;
                    balls.delete(i, i + quantity);
                    result += quantity;
                }
            }
            if (!changed) {
                break;
            }
        }
        System.out.println(result);
    }
}