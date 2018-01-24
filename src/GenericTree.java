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

    protected GenericNode<T> root;

    public GenericTree() {
        super();
    }

    public GenericNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(GenericNode<T> root) {
        this.root = root;
    }

    public int getNumberOfNodes() {
        int result = 0;

        if(root != null) {
            result = root.getDeepChildrenCount() + 1; //1 for the root!
        }

        return result;
    }

    public GenericNode<T> find(T value) {
        GenericNode<T> result = null;

        if(root != null) {
            result = root.find(value);
        }

        return result;
    }

    public boolean exists(T value) {
        return (find(value) != null);
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public String toString() {
        String result = "";

        if(root != null) {
            result += root.toString();

        }

        return result;
    }
}

