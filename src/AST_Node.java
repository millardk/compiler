import java.util.List;

public class AST_Node {

    AST_Node parent;

//    public abstract void ad AST_Node n);
//    public abstract Code getCode();
}

class Program extends AST_Node {
    List<Decl> decls;
    List<FuncDecl> f_decls;
}

class Decl extends AST_Node {
    Entity e;
}

abstract class Expr extends AST_Node {

}

class FuncCall extends Expr {
    Entity def;
    List<Expr> args;
}

class OpExpr extends Expr {

    enum NumOp {
        MUL,
        DIV,
        ADD,
        SUB
    }

    Expr left = null;
    Expr right = null;
    NumOp op = null;

}

class Number extends Expr {
    boolean isInt;
    String value;
}

class StringLit extends Expr {
    String lit;
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

class FuncDecl extends AST_Node {
    String id;
    Type ret;
    List<Entity> params;

    List<Decl> decls;
    List<Stmt> stmts;
}

abstract class Stmt extends AST_Node {

}

class WriteStmt extends Stmt {
    List<Entity> params;
}

class ReadStmt extends Stmt {
    List<Entity> params;
}

class WhileStmt extends Stmt {
    CondExpr cond;
    List<Decl> decls;
    List<Stmt> stmts;
}

class IfStmt {

    CondExpr condition;
    List<Stmt> body_then;
    List<Stmt> body_else;

}

class AssignStmt extends AST_Node {
    Entity e;
    Expr expr;

}





