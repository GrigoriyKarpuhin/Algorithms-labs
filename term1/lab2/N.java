import java.util.Scanner;

public class N {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        String s2 = scan.nextLine();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int l = 0; l <= s1.length(); l++) {
            dp[l][0] = l;
        }
        for (int l = 0; l <= s2.length(); l++) {
            dp[0][l] = l;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int buf = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, buf);
                }
            }
        }
        System.out.println(dp[s1.length()][s2.length()]);
    }
}