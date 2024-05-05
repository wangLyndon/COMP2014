package dsa.impl;

import dsa.iface.IBinaryTree;
import dsa.iface.IEntry;
import dsa.iface.ISortedMap;

public class Main {
	public static void main(String[] args) {
		ISortedMap<Integer,String> bst = new BinarySearchTreeMap<>();

		bst.put(23, "twenty three" );
		bst.put(12, "twelve" );
		bst.put(44, "forty four" );
		bst.put(13, "thirteen" );
		bst.put(1,  "one" );
		bst.put(7,  "seven" );
		bst.put(22, "twenty two");
		bst.put(55, "fifty five" );
		bst.put(43, "forty three" );
		bst.put(18, "eighteen" );
		
		System.out.println("FIRST TREE------------------------------------------------------");
		TreePrinter.printTree( (IBinaryTree<IEntry<Integer,String>>) bst );
		
		bst.remove(1);
		
		System.out.println("SECOND TREE------------------------------------------------------");
		TreePrinter.printTree( (IBinaryTree<IEntry<Integer,String>>) bst );
		
		bst.remove(23);
		
		System.out.println("THIRD TREE-------------------------------------------------------");
		TreePrinter.printTree( (IBinaryTree<IEntry<Integer,String>>) bst );

	}
}
