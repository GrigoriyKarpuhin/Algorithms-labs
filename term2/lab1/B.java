import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];
        int[] tree = new int[4 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        build(tree, arr, 0, 0, n - 1);
        while (scan.hasNext()) {
            String operation = scan.next();
            int i = scan.nextInt() - 1;
            int j = scan.nextInt();
            if (operation.equals("min")) {
                j--;
                System.out.println(query(tree, 0, i, j, 0, n - 1));
            } else {
                update(tree, 0, 0, n - 1, i, j);
            }
        }
    }

    public static void build(int[] tree, int[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
        } else {
            int mid = (l + r) / 2;
            build(tree, arr, 2 * node + 1, l, mid);
            build(tree, arr, 2 * node + 2, mid + 1, r);
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public static void update(int[] tree, int node, int l, int r, int ind, int val) {
        if (l == r) {
            tree[node] = val;
        } else {
            int mid = (l + r) / 2;
            if (l <= ind && ind <= mid) {
                update(tree, 2 * node + 1, l, mid, ind, val);
            } else {
                update(tree, 2 * node + 2, mid + 1, r, ind, val);
            }
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public static int query(int[] tree, int node, int l, int r, int l0, int r0) {
        if (r < l0 || r0 < l) {
            return Integer.MAX_VALUE;
        }
        if (l <= l0 && r0 <= r) {
            return tree[node];
        }
        int m0 = (l0 + r0) / 2;
        return Math.min(query(tree, 2 * node + 1, l, r, l0, m0), query(tree, 2 * node + 2, l, r, m0 + 1, r0));
    }
}