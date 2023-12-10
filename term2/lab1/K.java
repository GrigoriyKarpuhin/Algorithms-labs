import java.io.*;

public class K {

    static int[][] matrix0 = new int[][]{{1, 0}, {0, 1}};
    static int rMod;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("crypto.in"));
            String[] s = reader.readLine().split(" ");
            rMod = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            int m = Integer.parseInt(s[2]);
            int[][][] arr = new int[n][2][2];
            int[][][] tree = new int[4 * n][2][2];
            for (int i = 0; i < n; i++) {
                if (i > 0) {
                    reader.readLine();
                }
                s = reader.readLine().split(" ");
                arr[i][0][0] = Integer.parseInt(s[0]);
                arr[i][0][1] = Integer.parseInt(s[1]);
                s = reader.readLine().split(" ");
                arr[i][1][0] = Integer.parseInt(s[0]);
                arr[i][1][1] = Integer.parseInt(s[1]);
            }
            reader.readLine();
            build(tree, arr, 0, 0, n - 1);
            FileWriter writer = new FileWriter("crypto.out");
            for (int k = 0; k < m; k++) {
                s = reader.readLine().split(" ");
                int i = Integer.parseInt(s[0]) - 1;
                int j = Integer.parseInt(s[1]) - 1;
                int[][] a = query(tree, 0, i, j, 0, n - 1);
                writer.write(a[0][0] + " " + a[0][1] + System.lineSeparator());
                writer.write(a[1][0] + " " + a[1][1] + System.lineSeparator());
                writer.write(System.lineSeparator());
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


    public static void build(int[][][] tree, int[][][] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
        } else {
            int mid = l + (r - l) / 2;
            build(tree, arr, 2 * node + 1, l, mid);
            build(tree, arr, 2 * node + 2, mid + 1, r);
            tree[node] = multMatrix(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public static int[][] query(int[][][] tree, int node, int l, int r, int l0, int r0) {
        if (r < l0 || r0 < l) {
            return matrix0;
        }
        if (l <= l0 && r0 <= r) {
            return tree[node];
        }
        int m0 = l0 + (r0 - l0) / 2;
        return multMatrix(query(tree, 2 * node + 1, l, r, l0, m0), query(tree, 2 * node + 2, l, r, m0 + 1, r0));
    }

    public static int[][] multMatrix(int[][] a, int[][] b) {
        return new int[][]{{(a[0][0] * b[0][0] + a[0][1] * b[1][0]) % rMod, (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % rMod},
                {(a[1][0] * b[0][0] + a[1][1] * b[1][0]) % rMod, (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % rMod}};
    }
}