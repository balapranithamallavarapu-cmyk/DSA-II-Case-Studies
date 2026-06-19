
class Node {
    int id;
    String name;
    Node left, right;
    int height;

    Node(int id, String name) {
        this.id = id;
        this.name = name;
        height = 1;
    }
}

class AVLTree {

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int id, String name) {
        if (node == null) return new Node(id, name);

        if (id < node.id)
            node.left = insert(node.left, id, name);
        else if (id > node.id)
            node.right = insert(node.right, id, name);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.id)
            return rightRotate(node);

        if (balance < -1 && id > node.right.id)
            return leftRotate(node);

        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.id + " -> " + node.name);
            inorder(node.right);
        }
    }
}

public class RetailMind {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Node root = null;

        root = tree.insert(root, 101, "Milk");
        root = tree.insert(root, 50, "Bread");
        root = tree.insert(root, 150, "Rice");
        root = tree.insert(root, 25, "Butter");

        System.out.println("Inventory:");
        tree.inorder(root);
    }
}
    

