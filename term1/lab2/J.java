import java.util.ArrayList;
import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] stones = new int[n];
        ArrayList<Integer> jumps = new ArrayList<>();
        ArrayList<Integer> jumpsLen = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            stones[i] = scan.nextInt();
        }
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MIN_VALUE;
            int last = Integer.MIN_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int nextStone = dp[i - j] + stones[i];
                    if (dp[i] < nextStone) {
                        dp[i] = nextStone;
                        last = j;
                    }
                }
            }
            jumpsLen.add(last);
        }
        int m = n;
        while (true) {
            jumps.add(m);
            if (m == 1) {
                break;
            }
            m -= jumpsLen.get(m - 2);
        }
        System.out.println(dp[n - 1]);
        System.out.println(jumps.size() - 1);
        for (int i = jumps.size() - 1; i >= 0; i--) {
            System.out.print(jumps.get(i) + " ");
        }
    }
}