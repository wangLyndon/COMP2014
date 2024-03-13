import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.Iterator;

public class Q2 {
   public static void main( String[] args ) {
      ITree<Character> tree = Tree.createTreeB();

      // write your code to answer Question 2 here...
      System.out.println("a) What is the height of the tree?:");

      System.out.println("b) What is the depth of the node that stores D?: ");

      System.out.println("c) List the elements stored in the children of the node that stores B.");

      System.out.println("d) List the elements stored in any siblings of the node that stores D.");

      System.out.println("e) List the elements that are stored at external nodes.: ");

      System.out.println("f) What is the parent of the node that stores A?");

      System.out.println("g) List the ancestors of the node that stores E.");

      System.out.println("h) What is the size of the tree?");

   }
}
