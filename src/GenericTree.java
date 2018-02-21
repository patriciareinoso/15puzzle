import java.util.*;

/**
 * Generic class for a Tree representation.
 * 
 * @author Oscar Guillen
 */

/** 
 * Generic Tree class that represents a tree.
 * It has an attribute corresponding to the tree root.
 * 
 */
public class GenericTree<T> {

    /**
     * Generic node as root
     */
    protected GenericNode<T> root;

    /**
     * Empty Constructor of a generic Tree.
     */
    public GenericTree() {
        super();
    }

    /**
     * Getter of the root
     * @return Generic node which is the tree root
     */
    public GenericNode<T> getRoot() {
        return this.root;
    }

    /**
     * Setter of the root
     * @param new root as generic node
     */
    public void setRoot(GenericNode<T> root) {
        this.root = root;
    }

    /**
     * Count number of nodes inside the tree
     * @return number of nodes.
     */
    public int getNumberOfNodes() {
        int result = 0;

        if(root != null) {
            result = root.getDeepChildrenCount() + 1; //1 for the root!
        }

        return result;
    }

    /**
     * Search node with a value inside the tree.
     * @param value to be searched
     * @return the node if it is found, null otherwise
     */
    public GenericNode<T> find(T value) {
        GenericNode<T> result = null;

        if(root != null) {
            result = root.find(value);
        }

        return result;
    }

    /**
     * Indicate if a value matches with one value of a node inside the tree.
     * @param value to be searched
     * @return true if it is found, false otherwise
     */
    public boolean exists(T value) {
        return (find(value) != null);
    }

    /**
     * Indicate if the tree is empty.
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * String representation of a tree
     * @return string representation
     */
    public String toString() {
        String result = "";

        if(root != null) {
            result += root.toString();

        }

        return result;
    }
}

