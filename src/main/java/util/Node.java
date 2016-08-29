package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author johan
 * @since 2016-08-29.
 */
public class Node<T> {
    private T value;
    private Node<T> parent;
    private LinkedList<Node<T>> kids = new LinkedList<>();

    public Node(Node<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    public List<Node<T>> getKids() {return kids;}
    public T getValue() { return value; }

    public boolean getHasParent() { return parent != null; }

    public boolean isFirstChild() {
        return parent != null && parent.kids.peekFirst() == this;
    }

    public boolean isLastChild() {
        return parent != null && parent.kids.peekLast() == this;
    }

    public List<Node> getLastChildLineage() {
        Node node = this;
        List<Node> lineage = new ArrayList<>();
        while(node.isLastChild()) {
            lineage.add(node);
            node = node.parent;
        }
        return lineage;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
