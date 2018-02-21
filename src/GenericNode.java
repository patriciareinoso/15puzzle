import java.util.ArrayList;

/**
 * Generic class for a Tree Node representation.
 * 
 * @author Oscar Guillen
 */

/** 
 * Generic Tree Node class that represents a node of a tree.
 * It has an attribute corresponding to a value, an attribute that
 * corresponding to its children and an attribute corresponding to its parent.
 * 
 */
public class GenericNode<T> {

    /**
     * Generic value of the node.
     */
    protected T value;
    /**
     * Array of nodes which represent the children of the actual node.
     */
    protected ArrayList<GenericNode<T>> children;
    /**
     * Generic node that represent the parent of the actual node.
     */
    protected GenericNode<T> parent;

    /**
     * Empty Constructor of a generic Tree Node.
     */
    public GenericNode() {
        super();
        children = new ArrayList<GenericNode<T>>();
    }

    /**
     * Constructor of a generic Tree Node.
     * @param value of the generic value
     */
    public GenericNode(T value) {
        this();
        setValue(value);
    }

    /**
     * Getter of the parent
     * @return Parent generic node 
     */
    public GenericNode<T> getParent() {
        return this.parent;
    }

    /**
     * Getter of children
     * @return ArrayList of children generic nodes 
     */
    public ArrayList<GenericNode<T>> getChildren() {
        return this.children;
    }

    /**
     * Count number of children
     * @return Number of children 
     */
    public int getChildrenCount() {
        return getChildren().size();
    }

    /**
     * Count number of offsprings
     * @return Number of offsprings 
     */
    public int getDeepChildrenCount() {
        int nodes = this.getChildrenCount();

        for(GenericNode<T> child : this.getChildren()) {
            nodes += child.getDeepChildrenCount();
        }

        return nodes;
    }

    /**
     * Setter of the children
     * @param ArrayList of new generic children nodes 
     */
    public void setChildren(ArrayList<GenericNode<T>> children) {
        for(GenericNode<T> child : children) {
           child.parent = this;
        }

        this.children = children;
    }

    /**
     * Add a child to the children array
     * @param New generic node child
     */
    public void addChild(GenericNode<T> child) {
        child.parent = this;
        children.add(child);
    }

    /**
     * Getter of the value
     * @return Value of the node 
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Setter of the value
     * @param new value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Indicate if the actual node is a leaf. It means that it has not
     * children.
     * @return true if it is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return getChildren().size() == 0;
    }

    /**
     * Search a value into the offsprings of the actual node. 
     * @param value to be searched
     * @return Generic node if it is found, null otherwise. 
     */
    public GenericNode<T> find(T value) {
        GenericNode<T> result = null;

        if (this.getValue().equals(value))
            result = this;
        else {
            if (!this.isLeaf()) {
                for (GenericNode<T> child : this.getChildren()) {
                    if (result != null)
                        break;
                    result = child.find(value);
                }
            }
        }

        return result;
    }

    /**
     * Indicates if the node has any ancestor with the value received.
     * @param value to be searched
     * @return true if it is found, false otherwise
     */
    public boolean isAncestor(T value) {
        boolean result = false;
        if (getValue().equals(value))
            return true;
        else {
            for (GenericNode<T> child : this.getChildren()) {
                result = result || child.isAncestor(value);
                if (result)
                    return true;
            }
        }
        return result;

    }

    public String toString() {
        return getValue().toString();
    }

    /**
     * Override equal method that indicate whether the node is equal to other
     * object. 2 nodes are equal if their values are equal.
     * 
     * @param obj the object the tile is compared to.
     * @return true is the object is considered equal. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
           return true;
        }
        if (obj == null) {
           return false;
        }
        if (getClass() != obj.getClass()) {
           return false;
        }
        GenericNode<T> node = (GenericNode<T>) obj;

        return this.getValue().equals(node.getValue());
    }
}

