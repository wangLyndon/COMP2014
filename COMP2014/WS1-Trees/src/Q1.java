import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.Iterator;

public class Q1 {
    public static void main(String[] args) {
        ITree<Character> tree = Tree.createTreeA();

        // write your code to answer Question 1 here...
        System.out.println("a) What is stored at the root of the tree?: ");
        IPosition<Character> root = tree.root();
        System.out.println(root.element());

        System.out.println("b) What are stored in the children of the root node?: ");
        Iterator<IPosition<Character>> children = tree.children(root);
        while (children.hasNext()) {
            System.out.println(children.next().element());
        }

        System.out.println("c) What is the depth of the node that stores L?: ");
        System.out.println(depth(tree, root, 'L'));

        System.out.println("d) What is the height of the tree?: ");
        System.out.println(height(tree, root));

        System.out.println("e) List the elements stored in any ancestors of the node that stores G.: ");
        ancestors(tree, root, 'G');

        System.out.println("f) List the elements stored in any descendants of the node that stores B.");
        descendants(tree, root, 'B');

        System.out.println("g) List the elements that are stored at leaf (external) nodes.: ");
        seeAllTree(tree, root);

        System.out.println("h) Is (N,L) an edge?: ");
        isEdge(tree, 'N', 'L');

        System.out.println("i) List the elements stored in the nodes that are in the path from D to N.: ");
        IPosition<Character> n = find(tree, root, 'N');
        if (n != null){
            path(tree, n, 'N', 'D');
        }
    }

    public static <T> int depth(ITree<T> tree, IPosition<T> node, T element) {
        if (node.element().equals(element)) {
            return 0; // Means find the element
        } else {
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()) {
                int depth = depth(tree, children.next(), element);
                if (depth != -1) {
                    return 1 + depth;
                }
            }
        }
        return -1;
    }

    public static <T> int height(ITree<T> tree, IPosition<T> root) {
        int maxHeight = 0;
        Iterator<IPosition<T>> children = tree.children(root);
        while (children.hasNext()) {
            int currentHeight = 1 + height(tree, children.next());
            if (maxHeight < currentHeight) {
                maxHeight = currentHeight;
            }
        }
        return maxHeight;
    }

    public static <T> void ancestors(ITree<T> tree, IPosition<T> node, T element){
        if (node.element().equals(element)){
            while (true){
                System.out.println(node.element());
                node = tree.parent(node);
                if (node == null){
                    break;
                }
            }
        }else{
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                ancestors(tree, children.next(), element);
            }
        }
    }

    public static <T> void descendants(ITree<T> tree, IPosition<T> node, T element){
        if (node.element().equals(element)){
            System.out.println(element);
            printDescendants(tree, node);
        }else{
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                descendants(tree, children.next(), element);
            }
        }
    }

    public static <T> void printDescendants(ITree<T> tree, IPosition<T> node){
        Iterator<IPosition<T>> children = tree.children(node);
        while (children.hasNext()){
            IPosition<T> next = children.next();
            System.out.println(next.element());
            printDescendants(tree, next);
        }
    }

    public static <T> void seeAllTree(ITree<T> tree, IPosition<T> node){
        if (tree.isExternal(node)){
            System.out.println(node.element());
        }else{
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                seeAllTree(tree, children.next());
            }
        }
    }

    public static <T> void isEdge(ITree<T> tree, T parent, T children){
        Iterator<IPosition<T>> positions = tree.positions();
        while (positions.hasNext()){
            IPosition<T> next = positions.next();
            if (next.element().equals(children)){
                if (tree.parent(next) != null && tree.parent(next).equals(parent)){
                    System.out.println("Yes!");
                }else{
                    System.out.println("No!");
                }
                break;
            }
        }
    }

    public static <T> IPosition<T> find(ITree<T> tree, IPosition<T> node, T element){
        if (node.element().equals(element)){
            return node;
        }else{
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                IPosition<T> tiPosition = find(tree, children.next(), element);
                if (tiPosition != null){
                    return tiPosition;
                }
            }
        }
        return null;
    }

    public static <T> void path(ITree<T> tree, IPosition<T> node, T child, T parent){
        if (node.element().equals(parent)){
            System.out.println(parent);
        }else{
            path(tree, tree.parent(node), child, parent);
            System.out.println(node.element());
        }
    }
}
