import java.util.*;

public class C {
    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    private static int[] depth;
    private static int[][] parent;
    private static int[][] minCost;
    private static ArrayList<Pair<Integer, Integer>>[] adjList;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        depth = new int[n + 1];
        int logN = (int) (Math.log(n) / Math.log(2)) + 1;
        parent = new int[n + 1][logN];
        minCost = new int[n + 1][logN];
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            if(i != 1) {
                int parentVertex = scan.nextInt();
                int cost = scan.nextInt();
                parent[i][0] = parentVertex;
                minCost[i][0] = cost;
                adjList[parentVertex].add(new Pair<>(i, cost));
            }
        }
        preprocess(n, logN);

        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            int lca = getLCA(u, v);
            int min = getMin(u, v);
            System.out.println(min);
        }
    }

    private static void preprocess(int n, int logN) {
        dfs(1, 0);
        for (int j = 1; j < logN; j++) {
            for (int i = 1; i <= n; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                    minCost[i][j] = Math.min(minCost[i][j - 1], minCost[parent[i][j - 1]][j - 1]);
                }
            }
        }
    }

    private static void dfs(int node, int par) {
        depth[node] = depth[par] + 1;
        for (Pair<Integer, Integer> child : adjList[node]) {
            if (child.getKey() != par) {
                dfs(child.getKey(), node);
            }
        }
    }

    private static int getMin(int u, int v) {
        int lca = getLCA(u, v);
        int min = Integer.MAX_VALUE;
        int logN = parent[0].length;
        for (int i = logN - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[lca]) {
                min = Math.min(min, minCost[u][i]);
                u = parent[u][i];
            }
        }

        for (int i = logN - 1; i >= 0; i--) {
            if (depth[v] - (1 << i) >= depth[lca]) {
                min = Math.min(min, minCost[v][i]);
                v = parent[v][i];
            }
        }

        return min;
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