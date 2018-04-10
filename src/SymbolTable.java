import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class SymbolTable {

    static HashMap<String, FuncDecl> funcMap = new HashMap<>();
    static int tempCount = 1;

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

    void add(Var var){

        boolean err = false;
        if(varMap.containsKey(var.id))
            err = true;

        SymbolTable cur = this;
        while(!err && cur != null) {
            if (!cur.isBlock && cur.varMap.containsKey(var.id)) {
                err = true;
            }
            cur = cur.parent;
        }

        if(err) {
            System.err.println("Var Declaration error " + var.id);
        } else {
            varMap.put(var.id, var);
            varlist.add(var);
            if(isGlobal(var.id))
                var.l_val = var.id;
            else
                var.l_val = makeTemp();

        }
    }

    boolean isGlobal(String id){
        SymbolTable cur = this;
        while(cur.parent != null)
            cur = cur.parent;

        return cur.varMap.containsKey(id);
    }

    Var getVarRef(String id){

        Var ret = varMap.get(id);
        if(ret != null)
            return ret;

        SymbolTable cur = parent;
        while(cur != null){
            ret = cur.varMap.get(id);
            if(!cur.isBlock)
                if(ret != null)
                    return ret;

            cur = cur.parent;
        }
        return null;


    }

    FuncDecl getFuncRef(String id){
        return funcMap.get(id);
    }

    static String makeTemp(){
        return "$T"+tempCount++;
    }

}

class Var {
    String l_val = null;
    Type type = null;
    String id = null;
    String litVal = null;

    Var(Type type, String id) {
        this.type = type;
        this.id = id;
        litVal = null;
    }

    Var(String id, String lit) {
        type = Type.STRING;
        this.id = id;
        this.litVal = lit;
    }

}
