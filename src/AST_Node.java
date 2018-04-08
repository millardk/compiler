import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class AST_Node{

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.add(this);
        return ret;
    }

    @Override
    public String toString() {
        return getClass().toString();
    }

}

class Program extends AST_Node{
    SymbolTable table;
    VarDeclList globals;
    List<FuncDecl> funcs;

    @Override
    List<AST_Node> getChildren() {
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(globals.getChildren());
        for(FuncDecl f : funcs)
            ret.addAll(f.getChildren());
        ret.add(this);
        return ret;
    }
}

class VarDeclList extends AST_Node{
    List<VarDecl> decls = new ArrayList<>();

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(decls);
        return ret;
    }
}

class VarDecl extends AST_Node {
    Var var;
    VarDecl(Var var){
        this.var = var;
    }
}

class FuncDecl extends AST_Node {
    SymbolTable table;
    String id;
    Type retType = null;
    List<Var> params = null;
    VarDeclList decl = null;
    StmtList stmts = null;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        if (decl != null)
            ret.addAll(decl.getChildren());
        if (stmts != null)
            ret.addAll(stmts.getChildren());
        ret.add(this);
        return ret;
    }

}

class Expr extends AST_Node {

}

class ExprList extends Expr {
    List<Expr> exprs;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        for(Expr e : exprs)
            ret.addAll(e.getChildren());
        ret.add(this);
        return ret;
    }
}

class CallExpr extends Expr {
    FuncDecl func;
    ExprList args;

}

class VarExpr extends Expr {
    Var var;
}

class BinExpr extends Expr {

    enum OpType {
        ADD,
        SUB,
        MUL,
        DIV,
        NULL;

        static OpType getType(String s){
            switch (s.charAt(0)){
                case '+' : return ADD;
                case '-' : return SUB;
                case '*' : return MUL;
                case '/' : return DIV;
                default : return NULL;
            }

        }

    }

    Expr left;
    Expr right;
    OpType op;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(left.getChildren());
        ret.addAll(right.getChildren());
        ret.add(this);
        return ret;
    }
}

class Int_Lit extends Expr {
    int value;
}

class Float_Lit extends Expr {
    String value;
}

class CondExpr extends AST_Node {

    enum CondType {
        NE,
        LE,
        GE,
        LT,
        GT;

        static CondType getType(String s){
            switch(s){
                case "!=": return NE;
                case "<=": return LE;
                case ">=": return GE;
                case "<": return LT;
                case ">": return GT;
                default : return NE;
            }
        }
    }

    CondType type;
    Expr left;
    Expr right;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(left.getChildren());
        ret.addAll(right.getChildren());
        ret.add(this);
        return ret;
    }

}

class StmtList extends  AST_Node {
    List<Stmt> stmts = new ArrayList<>();

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        for(Stmt s : stmts)
            ret.addAll(s.getChildren());
        return ret;
    }

}

abstract class Stmt extends AST_Node {

}

class ReadWriteStmt extends Stmt {
    boolean isRead;
    List<Var> args;

}

class WhileStmt extends Stmt {
    SymbolTable table;
    CondExpr cond = null;
    VarDeclList decls = null;
    StmtList stmts = null;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(decls.getChildren());
        ret.addAll(stmts.getChildren());
        ret.add(this);
        ret.addAll(cond.getChildren());
        return ret;
    }
}

class IfStmt extends Stmt{
    SymbolTable table;
    CondExpr cond = null;
    StmtList body_then = null;
    ElsePart body_else = null;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(body_then.getChildren());
        if (body_else != null)
                ret.addAll(body_else.getChildren());
        ret.add(this);
        ret.addAll(cond.getChildren());
        return ret;
    }
}

class ElsePart extends Stmt {
    SymbolTable table;
    VarDeclList vars = null;
    StmtList stmts = null;

    List<AST_Node> getChildren(){
        List<AST_Node> ret = new LinkedList<>();
        ret.addAll(vars.getChildren());
        ret.addAll(stmts.getChildren());
        ret.add(this);
        return ret;
    }
}

class AssignStmt extends Stmt {
    Var var;
    Expr expr;

    List<AST_Node> getChildren(){
        List<AST_Node> ret  = new LinkedList<>();
        ret.add(var);
        ret.addAll(expr.getChildren());
        ret.add(this);
        return ret;
    }
}

class ReturnStmt extends Stmt {
    Expr expr;

    List<AST_Node> getChildren(){
        List<AST_Node> ret  = new LinkedList<>();
        ret.addAll(expr.getChildren());
        ret.add(this);
        return ret;
    }

}

class Var extends AST_Node{
    Type type;
    String id;
    String litVal;

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






