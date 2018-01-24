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

    protected T value;
    protected ArrayList<GenericNode<T>> children;
    protected GenericNode<T> parent;

    public GenericNode() {
        super();
        children = new ArrayList<GenericNode<T>>();
    }

    public GenericNode(T value) {
        this();
        setValue(value);
    }

    public GenericNode<T> getParent() {
        return this.parent;
    }

    public ArrayList<GenericNode<T>> getChildren() {
        return this.children;
    }

    public int getChildrenCount() {
        return getChildren().size();
    }

    public int getDeepChildrenCount() {
        int nodes = this.getChildrenCount();

        for(GenericNode<T> child : this.getChildren()) {
            nodes += child.getDeepChildrenCount();
        }

        return nodes;
    }

    public void setChildren(ArrayList<GenericNode<T>> children) {
        for(GenericNode<T> child : children) {
           child.parent = this;
        }

        this.children = children;
    }

    public void addChild(GenericNode<T> child) {
        child.parent = this;
        children.add(child);
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return getChildren().size() == 0;
    }

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

