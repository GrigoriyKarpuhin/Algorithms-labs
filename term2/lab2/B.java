import java.util.Scanner;

public class B {
    public static class Node {
        int data;
        Node right;
        Node left;
        int height;

        public Node(int data) {
            this.data = data;
            this.right = null;
            this.left = null;
            this.height = 1;
        }
    }

    private int getHeight(Node root) {
        return root == null ? 0 : root.height;
    }

    private int getBalance(Node root) {
        return root == null ? 0 : getHeight(root.right) - getHeight(root.left);
    }

    private void fixHeight(Node root) {
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private Node rotateRight(Node root) {
        Node q = root.left;
        root.left = q.right;
        q.right = root;
        fixHeight(root);
        fixHeight(q);
        return q;
    }

    private Node rotateLeft(Node root) {
        Node p = root.right;
        root.right = p.left;
        p.left = root;
        fixHeight(root);
        fixHeight(p);
        return p;
    }

    private Node rebalancing(Node root) {
        fixHeight(root);
        int balance = getBalance(root);
        if (balance > 1) {
            if (getHeight(root.right.right) <= getHeight(root.right.left)) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        } else if (balance < -1) {
            if (getHeight(root.left.left) <= getHeight(root.left.right)) {
                root.left = rotateLeft(root.left);
            }
            root = rotateRight(root);
        }
        return root;
    }

    public Node insert(Node root, int value) {
        if (exists(root, value)) {
            return root;
        }
        if (root == null) {
            root = new Node(value);
        } else if (value < root.data) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return rebalancing(root);
    }

    public Node find(Node root, int value) {
        if (root == null) {
            return null;
        } else if (value == root.data) {
            return root;
        } else if (value < root.data) {
            return find(root.left, value);
        } else {
            return find(root.right, value);
        }
    }

    public boolean exists(Node root, int value) {
        return find(root, value) != null;
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (value < root.data) {
            root.left = delete(root.left, value);
        } else if (value > root.data) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = delete(root.right, minNode.data);
        }
        return rebalancing(root);
    }

    private Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public Object next(Node root, int x) {
        if (root == null) {
            return "none";
        }
        Node current = root;
        Node res = null;
        while (current != null) {
            if (current.data > x) {
                res = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (res == null) {
            return "none";
        }
        return res;
    }

    public Object prev(Node root, int x) {
        if (root == null) {
            return "none";
        }
        Node current = root;
        Node res = null;
        while (current != null) {
            if (current.data < x) {
                res = current;
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (res == null) {
            return "none";
        }
        return res;
    }

    public static void print(Node root) {
        if (root != null) {
            print(root.left);
            System.out.print(root.data + " ");
            print(root.right);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        B tree = new B();
        Node root = null;
        while (scan.hasNext()) {
            String command = scan.next();
            int value = scan.nextInt();
            switch (command) {
                case "insert" -> root = tree.insert(root, value);
                case "delete" -> root = tree.delete(root, value);
                case "exists" -> System.out.println(tree.exists(root, value));
                case "next" -> {
                    Object next = tree.next(root, value);
                    if (next instanceof String) {
                        System.out.println(next);
                    } else {
                        System.out.println(((Node) next).data);
                    }
                }
                case "prev" -> {
                    Object prev = tree.prev(root, value);
                    if (prev instanceof String) {
                        System.out.println(prev);
                    } else {
                        System.out.println(((Node) prev).data);
                    }
                }
            }
        }
    }
}