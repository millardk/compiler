import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class SymbolTable {

    static HashMap<String, FuncDecl> funcMap = new HashMap<>();

    HashMap<String, Var> varMap = new HashMap<>();
    List<Var> varlist = new ArrayList<>();

    SymbolTable parent = null;

    SymbolTable(){}

    SymbolTable(SymbolTable parent){
        this.parent = parent;
    }

    static void add(FuncDecl f) {
        if(funcMap.containsKey(f.id)) {
            System.err.println("Function Declaration error "+f.id);
        } else {
            funcMap.put(f.id,f);
        }
    }

    void add(Var e){
        SymbolTable cur = this;
        boolean err = false;

        while(!err && cur != null) {
            if (varMap.containsKey(e.id)) {
                System.err.println("Var Declaration error " + e.id);
                err = true;
            }
            cur = cur.parent;
        }
        if (!err) {
            varMap.put(e.id, e);
            varlist.add(e);
        }
    }

    Var get(String id){
        SymbolTable cur = this;
        while (cur != null) {
            Var v = cur.varMap.get(id);
            if (v != null)
                return v;
            cur = cur.parent;
        }
        return null;
    }


}


