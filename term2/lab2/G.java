import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class G {
    public static class TreapNode {
        int data;
        int priority;
        TreapNode left, right;
        int size;
        private static final Random random = new Random();

        TreapNode(int data) {
            this.data = data;
            this.priority = random.nextInt();
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    public static class TreapPair {
        TreapNode first;
        TreapNode second;

        TreapPair(TreapNode first, TreapNode second) {
            this.first = first;
            this.second = second;
        }
    }
    public static void recalc(TreapNode root) {
        if (root != null) {
            root.size = 1;
            if (root.left != null) {
                root.size += root.left.size;
            }
            if (root.right != null) {
                root.size += root.right.size;
            }
        }
    }
    public static TreapPair split(TreapNode root, int idx) {
        if (root == null) {
            return new TreapPair(null, null);
        }
        int curIdx = 1;
        if (root.left != null) {
            curIdx += root.left.size;
        }
        if (curIdx <= idx) {
            TreapPair t = split(root.right, idx - curIdx);
            root.right = t.first;
            recalc(root);
            return new TreapPair(root, t.second);
        } else {
            TreapPair t = split(root.left, idx);
            root.left = t.second;
            recalc(root);
            return new TreapPair(t.first, root);
        }
    }

    public static TreapNode merge(TreapNode left, TreapNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.priority > right.priority) {
            left.right = merge(left.right, right);
            recalc(left);
            return left;
        } else {
            right.left = merge(left, right.left);
            recalc(right);
            return right;
        }
    }

    public static void toStart(TreapNode root, int l, int r) {
        TreapPair t1 = split(root, r);
        TreapPair t2 = split(t1.first, l - 1);
        root = merge(t2.second, merge(t2.first, t1.second));
    }

    public static void print(TreapNode root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.data + " ");
            print(root.right);
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            TreapNode root = null;
            for (int i = 1; i <= n; i++) {
                root = merge(root, new TreapNode(i));
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                toStart(root, l, r);
            }
            print(root);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}