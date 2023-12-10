import java.util.Scanner;

import static java.util.Arrays.fill;

public class D {
    private static long[] flagSet;
    private static long[] flagAdd;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        long[] arr = new long[n];
        long[] tree = new long[4 * n];
        flagSet = new long[4 * n];
        fill(flagSet, Long.MAX_VALUE);
        flagAdd = new long[4 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextLong();
        }
        build(tree, arr, 0, 0, n - 1);
        while (scan.hasNext()) {
            String operation = scan.next();
            int i = scan.nextInt() - 1;
            int j = scan.nextInt() - 1;
            long val;
            switch (operation) {
                case "min" -> System.out.println(query(tree, 0, i, j, 0, n - 1));
                case "set" -> {
                    val = scan.nextLong();
                    massSet(tree, 0, 0, n - 1, i, j, val);
                }
                case "add" -> {
                    val = scan.nextLong();
                    massAdd(tree, 0, 0, n - 1, i, j, val);
                }
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
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }
    }

    public static long query(long[] tree, int node, int l, int r, int l0, int r0) {
        push(flagAdd, flagSet, tree, node);
        if (r < l0 || r0 < l) {
            return Long.MAX_VALUE;
        }
        if (l <= l0 && r0 <= r) {
            return tree[node];
        }
        int m0 = (l0 + r0) / 2;
        return Math.min(query(tree, 2 * node + 1, l, r, l0, m0), query(tree, 2 * node + 2, l, r, m0 + 1, r0));
    }

    private static void massAdd(long[] tree, int node, int l0, int r0, int l, int r, long val) {
        push(flagAdd, flagSet, tree, node);
        if (l <= l0 && r0 <= r) {
            tree[node] += val;
            if (flagSet[node] != Long.MAX_VALUE) {
                flagSet[node] += val;
            } else {
                flagAdd[node] += val;
            }
            return;
        }
        if (r0 < l || r < l0) {
            return;
        }
        int m0 = (l0 + r0) / 2;
        massAdd(tree, 2 * node + 1, l0, m0, l, r, val);
        massAdd(tree, 2 * node + 2, m0 + 1, r0, l, r, val);
        tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private static void massSet(long[] tree, int node, int l0, int r0, int l, int r, long val) {
        push(flagAdd, flagSet, tree, node);
        if (l <= l0 && r0 <= r) {
            tree[node] = val;
            flagSet[node] = val;
            flagAdd[node] = 0;
            return;
        }
        if (r0 < l || r < l0) {
            return;
        }
        int m0 = (l0 + r0) / 2;
        massSet(tree, 2 * node + 1, l0, m0, l, r, val);
        massSet(tree, 2 * node + 2, m0 + 1, r0, l, r, val);
        tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
    }
    private static void push(long[] flagAdd, long[] flagSet, long[] tree, int node) {
        if (flagSet[node] != Long.MAX_VALUE) {
            if (2 * node + 2 < tree.length) {
                tree[2 * node + 1] = flagSet[node];
                tree[2 * node + 2] = flagSet[node];
                flagSet[2 * node + 1] = flagSet[node];
                flagSet[2 * node + 2] = flagSet[node];
                flagAdd[2 * node + 1] = 0;
                flagAdd[2 * node + 2] = 0;
            }
        }
        if (flagAdd[node] != 0) {
            if (2 * node + 2 < tree.length) {
                if (flagSet[2 * node + 1] != Long.MAX_VALUE) {
                    flagSet[2 * node + 1] += flagAdd[node];
                } else {
                    flagAdd[2 * node + 1] += flagAdd[node];
                }
                if (flagSet[2 * node + 2] != Long.MAX_VALUE) {
                    flagSet[2 * node + 2] += flagAdd[node];
                } else {
                    flagAdd[2 * node + 2] += flagAdd[node];
                }
                tree[2 * node + 1] += flagAdd[node];
                tree[2 * node + 2] += flagAdd[node];
            }
        }
        flagAdd[node] = 0;
        flagSet[node] = Long.MAX_VALUE;
    }
}