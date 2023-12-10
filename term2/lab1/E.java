import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] tree1 = new int[4 * n];
        int[] tree2 = new int[4 * n];
        for (int i = 0; i < n; i++) {
            arr1[i] = scan.nextInt();
            arr2[i] = arr1[i];
            if (i % 2 == 0) {
                arr2[i] *= -1;
            } else {
                arr1[i] *= -1;
            }
        }
        build(tree1, arr1, 0, 0, n - 1);
        build(tree2, arr2, 0, 0, n - 1);
        scan.nextInt();
        while (scan.hasNext()) {
            int operation = scan.nextInt();
            int i = scan.nextInt() - 1;
            int j = scan.nextInt();
            if (operation == 1) {
                j--;
                if (i % 2 == 0) {
                    System.out.println(query(tree1, 0, i, j, 0, n - 1));
                } else {
                    System.out.println(query(tree2, 0, i, j, 0, n - 1));
                }
            } else {
                if (i % 2 == 0) {
                    update(tree1, 0, 0, n - 1, i, j);
                    update(tree2, 0, 0, n - 1, i, -j);
                } else {
                    update(tree1, 0, 0, n - 1, i, -j);
                    update(tree2, 0, 0, n - 1, i, j);
                }
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
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
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
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public static long query(int[] tree, int node, int l, int r, int l0, int r0) {
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