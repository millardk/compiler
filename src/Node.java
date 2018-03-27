import java.util.List;

public abstract class Node {

    Node parent;
    Scope scope;

    public abstract void addNode(Node n);
    public abstract Code getCode();
}

