import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class AST_Node{

    static BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(System.out));
//    public abstract void ad AST_Node n);
//    public abstract Code getCode();
    void print() throws java.io.IOException{ }
}

class Program extends AST_Node{
    SymbolTable table;
    VarDeclList globals;
    List<FuncDecl> funcs;

    void print() throws  java.io.IOException{
        bf.write("Global Vars."+"\n");
        globals.print();
        bf.write("Functions:"+'\n');
        for(FuncDecl f : funcs)
            f.print();
        bf.flush();
    }

}

class VarDeclList extends AST_Node{
    List<Var> vars = new ArrayList<>();

    void print() throws java.io.IOException{
        for (Var var : vars) {
            bf.write(var+"\n");
        }
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

    @Override
    public String toString() {
        if(type == Type.STRING)
            return type+" "+id+" "+litVal;
        else
            return type+" "+id;
    }
}

class FuncDecl extends AST_Node {
    SymbolTable table;
    String id;
    Type retType;
    List<Var> params;
    VarDeclList decl;
    StmtList stmts;

    void print() throws java.io.IOException{
        bf.write(retType+" "+id+"(");
        for(int i = 0; i < params.size(); i++)
            if (i != params.size()-1)
                bf.write(params.get(i)+",");
        bf.write(") "+'\n');
        decl.print();
        stmts.print();
        bf.write('\n');
    }
}

class Expr extends AST_Node {
}

class ExprList extends Expr {
    List<Expr> exprs;
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

}

class StmtList extends  AST_Node {
    List<Stmt> stmts = new ArrayList<>();
}

abstract class Stmt extends AST_Node {

}

class ReadWriteStmt extends Stmt {
    boolean isRead;
    List<Var> args;
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





