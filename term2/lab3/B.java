import java.util.*;

public class B {
    private static int[] depth;
    private static int[][] parent;
    private static ArrayList<Integer>[] adjList;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        depth = new int[n + 1];
        int logN = (int) (Math.log(n) / Math.log(2)) + 1;
        parent = new int[n + 1][logN];
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            if(i != 1) {
                int parentVertex = scan.nextInt();
                parent[i][0] = parentVertex;
                adjList[parentVertex].add(i);
            }
        }
        preprocess(n, logN);

        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            int lca = getLCA(u, v);
            System.out.println(lca);
        }
    }

    private static void preprocess(int n, int logN) {
        dfs(1, 0);
        for (int j = 1; j < logN; j++) {
            for (int i = 1; i <= n; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    private static void dfs(int node, int par) {
        depth[node] = depth[par] + 1;
        for (int child : adjList[node]) {
            if (child != par) {
                dfs(child, node);
            }
        }
    }

    private static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        int logN = parent[0].length;
        for (int i = logN - 1; i >= 0; i--) {
            if ((diff & (1 << i)) != 0) {
                u = parent[u][i];
            }
        }

        if (u == v) {
            return u;
        }
        
        for (int i = logN - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }
}