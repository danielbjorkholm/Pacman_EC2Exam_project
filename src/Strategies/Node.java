package Strategies;


import Model.Field;


import java.util.HashSet;
import java.util.Set;

public class Node {

    Node mParent;

    Set<Node> mChildren = new HashSet<>();

    Field mField;

    public Node(Field field){
        mField = field;
    }

    public Node(Field field, Set<Node> children) {
        // kalder overliggende Contructor
        this(field);
        mChildren = children;

    }



    public Node getParent() {
        return mParent;
    }

    public Set<Node> getChildren() {
        return mChildren;
    }

    public Field getField() {
        return mField;
    }
}
