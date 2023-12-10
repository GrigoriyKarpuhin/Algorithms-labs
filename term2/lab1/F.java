import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int a0 = scan.nextInt();
        int layer = (int) (Math.log(n) / Math.log(2)) + 1;
        int[][] sparse = new int[layer][n];
        sparse[0][0] = a0;
        for (int i = 1; i < n; i++) {
            sparse[0][i] = (23 * sparse[0][i - 1] + 21563) % 16714589;
        }
        for (int i = 1; i < layer; i++) {
            for (int j = 0; j < n; j++) {
                int b = (int) Math.pow(2, i - 1);
                if (j + b >= n) {
                    break;
                }
                sparse[i][j] = Math.min(sparse[i - 1][j], sparse[i - 1][j + b]);
            }
        }
        int u = scan.nextInt();
        int v = scan.nextInt();
        for (int i = 0; i < m; i++) {
            u--;
            v--;
            int ans;
            int l = Math.min(u, v);
            int r = Math.max(u, v);
            int k = (int) (Math.log(r - l + 1) / Math.log(2));
            ans = Math.min(sparse[k][l], sparse[k][r - (1 << k) + 1]);
            u++;
            v++;
            if (i == m - 1) {
                System.out.println(u + " " + v + " " + ans);
            }
            u = (17 * u + 751 + ans + 2 * (i + 1)) % n + 1;
            v = (13 * v + 593 + ans + 5 * (i + 1)) % n + 1;
        }
    }
}