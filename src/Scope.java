import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Scope {

    String name;
    HashMap<String, Entity> map = new HashMap<>();
    List<Entity> list = new LinkedList<>();
    Scope parent = null;
    List<Scope> children = new LinkedList<>();

    Scope(String name){
        this.name = name;
    }

    Scope(String name, Scope parent) {
        this.name = name;
        parent.children.add(this);
    }

    void add(Entity e){
        if (map.containsKey(e.id))
            System.err.println("Declaration error "+ e.id);
        else {
            map.put(e.id, e);
            list.add(e);
        }
    }

}
