import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = scan.nextInt();
        }
        for(int j = 0; j < k; j++) {
            int request = scan.nextInt();
            int low = -1;
            int high = array.length;
            while (high - low > 1) {
                int bin = (high + low) / 2;
                if (array[bin] > request) {
                    high = bin;
                } else {
                    low = bin;
                }
            }
            if (low == -1 || high != array.length && array[high] - request < request - array[low]) {
                System.out.println(array[high]);
            } else {
                System.out.println(array[low]);
            }
        }
        scan.close();
    }
}
 