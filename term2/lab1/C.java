import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] arr = new long[n];
        long[] tree = new long[4 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextLong();
        }
        build(tree, arr, 0, 0, n - 1);
        while (scan.hasNext()) {
            String operation = scan.next();
            int i = scan.nextInt() - 1;
            int j = scan.nextInt();
            if (operation.equals("sum")) {
                j--;
                System.out.println(query(tree, 0, i, j, 0, n - 1));
            } else {
                update(tree, 0, 0, n - 1, i, j);
            }
        }
    }

    public static void build(long[] tree, long[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
        } else {
            int mid = (l + r) / 2;
            build(tree, arr, 2 * node + 1, l, mid);
            build(tree, arr, 2 * node + 2, mid + 1, r);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public static void update(long[] tree, int node, int l, int r, int ind, int val) {
        if (l == r) {
            tree[node] = val;
        } else {
            int mid = (l + r) / 2;
            if (l <= ind && ind <= mid) {
                update(tree, 2 * node + 1, l, mid, ind, val);
            } else {
                update(tree, 2 * node + 2, mid + 1, r, ind, val);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public static long query(long[] tree, int node, int l, int r, int l0, int r0) {
        if (r < l0 || r0 < l) {
            return 0;
        }
        if (l <= l0 && r0 <= r) {
            return tree[node];
        }
        int m0 = (l0 + r0) / 2;
        return query(tree, 2 * node + 1, l, r, l0, m0) + query(tree, 2 * node + 2, l, r, m0 + 1, r0);
    }
}