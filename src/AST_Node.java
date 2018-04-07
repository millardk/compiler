import java.util.ArrayList;
import java.util.List;

class AST_Node{

//    public abstract void ad AST_Node n);
//    public abstract Code getCode();
    void print(){ }
}

class Program extends AST_Node{
    VarDeclList globals;
    List<FuncDecl> funcs;

    void print() {
        System.out.println("Printing Program.");
        globals.print();
//        f.print();
    }

}

class VarDeclList extends AST_Node{
    List<Var> vars = new ArrayList<>();

    void print() {
        System.out.println("VarDecl");
        for (Var e : vars)
            e.print();
    }
}


class Var {
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

    void print(){
        if (type == Type.STRING){
            System.out.println("E: STRING "+id+" "+litVal);
        } else {
            System.out.println("E: "+type+" "+id);
        }
    }
}

class FuncDecl extends AST_Node {
    String id;
    Type retType;
    List<Var> params;
    VarDeclList decl;
    StmtList stmts;
}

abstract class Expr extends AST_Node {

}

class FuncCall extends Expr {
    Var id;
    List<Expr> args;
}

class OpExpr extends Expr {

    Expr left;
    Expr right;
    char op;
}

class Number extends Expr {
    boolean isInt;
    String value;
}

class CondExpr extends AST_Node {

    enum CondType {
        NE,
        LE,
        GE,
        LT,
        GT
    }

    CondType type;
    Expr left;
    Expr right;

}

class StmtList extends  AST_Node {
    List<Stmt> stmts = new ArrayList<>();
}

abstract class Stmt extends AST_Node {

}

class WriteStmt extends Stmt {
    List<Var> params;
}

class ReadStmt extends Stmt {
    List<Var> params;
}

class WhileStmt extends Stmt {
    SymbolTable table;
    CondExpr cond;
    VarDeclList decls;
    StmtList stmts;
}

class IfStmt extends Stmt{
    SymbolTable table;
    CondExpr condition;
    StmtList body_then;
    ElsePart body_else;
}

class ElsePart extends Stmt {
    SymbolTable table;
    VarDeclList vars;
    StmtList stmts;
}

class AssignStmt extends Stmt {
    Var var;
    Expr expr;

}

class ReturnStmt extends Stmt {
    Expr expr;

}





