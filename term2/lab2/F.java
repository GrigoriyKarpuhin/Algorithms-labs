import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class F {

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

    public TreapPair split(TreapNode root, int key) {
        if (root == null) {
            return new TreapPair(null, null);
        }
        if (root.data <= key) {
            TreapPair t = split(root.right, key);
            root.right = t.first;
            recalc(root);
            return new TreapPair(root, t.second);
        } else {
            TreapPair t = split(root.left, key);
            root.left = t.second;
            recalc(root);
            return new TreapPair(t.first, root);
        }
    }

    public TreapNode merge(TreapNode left, TreapNode right) {
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

    public TreapNode insert(TreapNode root, int key) {
        TreapPair t = split(root, key);
        TreapNode node = new TreapNode(key);
        return merge(merge(t.first, node), t.second);
    }

    public TreapNode delete(TreapNode root, int key) {
        TreapPair t1 = split(root, key);
        TreapPair t2 = split(t1.first, key - 1);
        return merge(t2.first, t1.second);
    }

    public static int kMax(TreapNode root, int k) {
        int rightSize = 0;
        if (root.right != null) {
            rightSize = root.right.size;
        }
        if (rightSize >= k) {
            return kMax(root.right, k);
        } else if (rightSize + 1 == k) {
            return root.data;
        } else {
            return kMax(root.left, k - rightSize - 1);
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

    public static void print(TreapNode root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.data + " ");
            print(root.right);
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        F tree = new F();
        TreapNode root = null;
        int n;
        try {
            n = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        StringTokenizer st;
        for (int i = 0; i < n; ++i) {
            try {
                st = new StringTokenizer(br.readLine());
                int operation = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                switch (operation) {
                    case 1 -> root = tree.insert(root, value);
                    case -1 -> root = tree.delete(root, value);
                    case 0 -> System.out.println(kMax(root, value));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                return;
            }
        }
    }
}