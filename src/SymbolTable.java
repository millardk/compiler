import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SymbolTable {

    static HashMap<String, FuncDecl> funcMap = new HashMap<>();
    // block symbol tables cannot share references
    boolean isBlock = true;

    HashMap<String, Var> varMap = new HashMap<>();
    List<Var> varlist = new ArrayList<>();

    SymbolTable parent = null;

    SymbolTable(SymbolTable parent, boolean isBlock){
        this.parent = parent;
        this.isBlock = isBlock;
    }

    static void add(FuncDecl f) {
        if(funcMap.containsKey(f.id)) {
            System.err.println("Function Declaration error "+f.id);
        } else {
            funcMap.put(f.id,f);
        }
    }

    void add(Var e){

        boolean err = false;
        if(varMap.containsKey(e.id))
            err = true;

        SymbolTable cur = this;
        while(!err && cur != null) {
            if (!cur.isBlock && cur.varMap.containsKey(e.id)) {
                err = true;
            }
            cur = cur.parent;
        }

        if(err) {
            System.err.println("Var Declaration error " + e.id);
        } else {
            varMap.put(e.id, e);
            varlist.add(e);
        }
    }

    Var getVarRef(String id){
        Var ret = varMap.get(id);

        if(ret != null){
            return ret;
        } else {
            SymbolTable cur = parent;
            while(cur != null){
                ret = cur.varMap.get(id);
                if(!cur.isBlock && ret != null)
                    return ret;

                cur = cur.parent;
            }
            return null;
        }

    }

    FuncDecl getFuncRef(String id){
        return funcMap.get(id);
    }


}


