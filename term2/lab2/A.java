import java.util.Scanner;

public class A {
    public static class Node {
        int data;
        Node right;
        Node left;

        public Node(int data) {
            this.data = data;
            this.right = null;
            this.left = null;
        }
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
        return root;
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
        return root;
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
        A tree = new A();
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