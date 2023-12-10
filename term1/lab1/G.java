import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int y = scan.nextInt();
        int copy = Math.min(x, y);
        n--;
        int low = -1;
        int high = Integer.MAX_VALUE - 1;
        while (high - low > 1) {
            int bin = (high + low) / 2;
            int time = bin / x + bin / y;
            if (time >= n) {
                high = bin;
            } else {
                low = bin;
            }
        }
        System.out.println(high + copy);
    }
}