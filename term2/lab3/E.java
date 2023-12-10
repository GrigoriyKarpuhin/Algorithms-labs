import java.util.Scanner;
 
public class E {
    private static final int max = 200000;
    private static final int log = 18;
    private static int nodeCount = 0;
    private static final int[][] dynamic = new int[max][log];
    private static final int[] rootParents = new int[max];
    private static final int[] depths = new int[max];
    private static final int[] powers = new int[log];
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
 
        powers[0] = 1;
        for (int i = 1; i < log; ++i) {
            powers[i] = powers[i - 1] * 2;
        }
 
        for (int i = 0; i < m; ++i) {
            char op = scanner.next().charAt(0);
            if (op == '+') {
                int v = scanner.nextInt();
                v--;
 
                depths[++nodeCount] = depths[v] + 1;
                dynamic[nodeCount][0] = v;
                rootParents[nodeCount] = nodeCount;
 
                for (int j = 1; j < log; ++j) {
                    dynamic[nodeCount][j] = dynamic[dynamic[nodeCount][j - 1]][j - 1];
                }
            } else if (op == '-') {
                int v = scanner.nextInt();
                v--;
 
                rootParents[v] = dynamic[v][0];
            } else {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                System.out.println(search(LCA(u - 1, v - 1)) + 1);
            }
        }
 
        scanner.close();
    }
 
    private static int search(int v) {
        if (v == rootParents[v])
            return v;
 
        rootParents[v] = search(rootParents[v]);
        return rootParents[v];
    }
 
    private static int LCA(int u, int v) {
        if (depths[v] > depths[u]) {
            int t = v;
            v = u;
            u = t;
        }
 
        for (int i = log - 1; i >= 0; --i) {
            if (depths[u] - depths[v] >= powers[i]) {
                u = dynamic[u][i];
            }
        }
 
        if (u == v) {
            return u;
        }
 
        for (int i = log - 1; i >= 0; --i) {
            if (dynamic[v][i] != dynamic[u][i]) {
                u = dynamic[u][i];
                v = dynamic[v][i];
            }
        }
 
        return dynamic[v][0];
    }
}