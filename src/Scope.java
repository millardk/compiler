import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Scope {

    String name;
    HashMap<String, Entity> entityMap = new HashMap<>();
    List<Entity> entityList = new LinkedList<>();
    Scope parent = null;
    List<Scope> children = new LinkedList<>();

    Scope(String name){
        this.name = name;
    }

}
