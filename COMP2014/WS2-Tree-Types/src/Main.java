import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.Iterator;

public class Main {

    public static void main( String[] args ) {
        ITree<String> treeA = Tree.createTreeA();
        ITree<Integer> treeB = Tree.createTreeB();
        ITree<Character> treeC = Tree.createTreeC();
        ITree<Character> treeD = Tree.createTreeD();
        ITree<Integer> treeE = Tree.createTreeE();

        System.out.println("Binary Tree?");
        System.out.println( "A: " + isBinary( treeA ) );
        System.out.println( "B: " + isBinary( treeB ) );
        System.out.println( "C: " + isBinary( treeC ) );
        System.out.println( "D: " + isBinary( treeD ) );
        System.out.println( "E: " + isBinary( treeE ) );

        System.out.println("Proper Binary Tree?");
        System.out.println( "A: " + isProperBinary( treeA ) );
        System.out.println( "B: " + isProperBinary( treeB ) );
        System.out.println( "C: " + isProperBinary( treeC ) );
        System.out.println( "D: " + isProperBinary( treeD ) );
        System.out.println( "E: " + isProperBinary( treeE ) );

        System.out.println("Perfect Binary Tree?");
        System.out.println( "A: " + isPerfectBinary( treeA ) );
        System.out.println( "B: " + isPerfectBinary( treeB ) );
        System.out.println( "C: " + isPerfectBinary( treeC ) );
        System.out.println( "D: " + isPerfectBinary( treeD ) );
        System.out.println( "E: " + isPerfectBinary( treeE ) );
    }

    public static <T> boolean isBinary( ITree<T> tree ) {
       return testBinary(tree, tree.root());
    }

    public static <T> boolean isProperBinary( ITree<T> tree ) {
        return testProperBinary(tree, tree.root());
    }

    public static <T> boolean isPerfectBinary(ITree<T> tree){
        if (isProperBinary(tree)){
            return testPerfectBinary(tree, tree.root());
        }else{
            return false;
        }
    }

    public static <T> int countChildren(ITree<T> tree, IPosition<T> node){
        if (tree.isExternal(node)){
            return 0;
        }
        int count = 0;
        Iterator<IPosition<T>> children = tree.children(node);
        while (children.hasNext()){
            children.next();
            count++;
        }
        return count;
    }

    public static <T> boolean testBinary(ITree<T> tree, IPosition<T> node){
        if (countChildren(tree, node) > 2){
            return false;
        }else{
            boolean result = true;
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                boolean currentResult = testBinary(tree, children.next());
                if (!currentResult){
                    result = false;
                }
            }
            return result;
        }
    }

    public static <T> boolean testProperBinary(ITree<T> tree, IPosition<T> node){
        int countChildren = countChildren(tree, node);
        if (countChildren > 2 || countChildren == 1){
            return false;
        } else if (countChildren == 0) {
            return true;
        } else{
            boolean result = true;
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                boolean currentResult = testProperBinary(tree, children.next());
                if (!currentResult){
                    result = false;
                }
            }
            return result;
        }
    }

    public static <T> int getHeight(ITree<T> tree, IPosition<T> node){
        if (tree.isExternal(node)){
            return 0;
        }else{
            int maxHeight = 0;
            Iterator<IPosition<T>> children = tree.children(node);
            while (children.hasNext()){
                int currentHeight = getHeight(tree, children.next()) + 1;
                if (currentHeight > maxHeight){
                    maxHeight = currentHeight;
                }
            }
            return maxHeight;
        }
    }

    public static <T> boolean testPerfectBinary(ITree<T> tree, IPosition<T> node){
        if (tree.isExternal(node)){
            return true;
        }else{
            Iterator<IPosition<T>> children = tree.children(node);
            IPosition<T> leftNode = children.next();
            IPosition<T> rightNode = children.next();
            int height = getHeight(tree, leftNode);
            int currentHeight = getHeight(tree, rightNode);
            if (height != currentHeight){
                return false;
            }

            boolean leftSide = testPerfectBinary(tree, leftNode);
            boolean rightSide = testPerfectBinary(tree, rightNode);
            return leftSide && rightSide;
        }
    }
}
