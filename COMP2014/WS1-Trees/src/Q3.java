import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.Iterator;

public class Q3 {
   public static void main( String[] args ) {
      ITree<String> tree = Tree.createTreeC();

      // write your code to answer Question 3 here...
      System.out.println("a) What is stored in the root node?");

      System.out.println("b) What are stored in the internal nodes?");

      System.out.println("c) How many descendants does the node that stores 'cs016/' have?");

      System.out.println("d) How many ancestors does the node that stores 'cs016/' have?");

      System.out.println("e) What are the siblings of the node that stores 'homeworks/'?");

      System.out.println("f) Which nodes are in the subtree rooted at the node that stores 'projects/'?");

      System.out.println("g) What is the depth of the node that stores 'papers/'?");

      System.out.println("h) What is the height of the tree?");
   }
}
