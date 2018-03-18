import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Scope {

    String name;
    Scope parent;
    List<Scope> children;
    List<Entry> entries;
    HashMap<String, Entry> table;


    public Scope(String name, Scope parent){
        this.name = name;
        this.parent = parent;

        children = new LinkedList<>();
        entries = new LinkedList<>();
        table = new HashMap<>();
    }

    public boolean addEntry(Entry e){
        Scope cur = this;
        while (cur != null) {
            if (cur.table.containsKey(e.name)) {
                System.out.println("DECLARATION ERROR " + e.name);
                return false;
            }
            cur = cur.parent;
        }
        entries.add(e);
        table.put(e.name, e);
        return true;
    }

    public void printTree(){
        System.out.println("Symbol table "+name);
        for (Entry e : entries){
            e.print();
        }
        for (Scope s : children){
            s.printTree();
        }
    }
}

