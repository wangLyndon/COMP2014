import dsa.iface.IPosition;
import dsa.impl.AbstractBinaryTree;

public class ProperLinkedBinaryTree<T> extends AbstractBinaryTree<T> {

	/**
	 * Constructor - create an empty tree
	 */
	public ProperLinkedBinaryTree() {
		// Part 1
		root = newPosition(null, null);
		size = 1;
	}

	/**
	 * Expand an external node - Store a value in the external node - Create two
	 * external nodes as children, making {@code n} an internal node
	 *
	 * @param p
	 *            The position (node) to expand. An exception will be thrown if it is not
	 *            external.
	 * @param e
	 *            The element to be stored in position {@code p}.
	 */
	public void expandExternal(IPosition<T> p, T e) {
		// Part 2
		if (isInternal(p)){
			throw new RuntimeException("You need to enter a external one");
		}
		BTPosition btPosition = (BTPosition) p;
		AbstractBinaryTree<T>.BTPosition leftChildren = newPosition(null, btPosition);
		AbstractBinaryTree<T>.BTPosition rightChildren = newPosition(null, btPosition);

		btPosition.left = leftChildren;
		btPosition.right = rightChildren;

		btPosition.element = e;

		size = size + 2;
	}

	/**
	 * Remove a node from the tree
	 *
	 * @param p
	 *            The position (node )to be removed. An exception will be thrown if it cannot
	 *            be removed (i.e. if it has two internal children).
	 * @return The element that was stored in the removed position.
	 */
	public T remove(IPosition<T> p) {
		// Part 3
		BTPosition btPosition = (BTPosition) p;
		if (isInternal(left(btPosition)) && isInternal(right(btPosition))){
			throw new RuntimeException("Can not remove a node with two internal children");
		}

		BTPosition replacement;
		if (isExternal(left(btPosition))){
			replacement = (BTPosition) right(btPosition);
		} else if (isExternal(right(btPosition))) {
			replacement = (BTPosition) left(btPosition);
		}else{
			replacement = null;
		}

		if (isRoot(btPosition)){
			if (replacement != null){
				replacement.parent = null;
			}
			root = replacement;
		}else{
			BTPosition parent = (BTPosition) parent(btPosition);
			if (btPosition == left(parent)){
				parent.left = replacement;
			}else{
				parent.right = replacement;
			}
			if (replacement != null){
				replacement.parent = null;
			}
		}

		size--;
		return btPosition.element(); // <-- this is just so the class compiles: remove it from your code
	}
}
