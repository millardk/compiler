import java.util.ArrayList;
import java.util.List;

class AST_Node{

    Code getCode(){
        return null;
    }
}

class Program extends AST_Node{
    SymbolTable table;
    VarDeclList globals;
    List<FuncDecl> funcs;

    Code getCode(){
        Code code = new Code();
        for(FuncDecl f : funcs)
            code.insert(f.getCode());
        return code;
    }
}

class VarDeclList extends AST_Node{
    List<VarDecl> decls = new ArrayList<>();
}

class VarDecl extends AST_Node {
    Var var;
    VarDecl(Var var){
        this.var = var;
    }
}

class FuncDecl extends AST_Node {
    SymbolTable table = null;
    String id = null;
    Type retType = null;
    List<Var> params = null;
    VarDeclList decl = null;
    StmtList stmts = null;

    @Override
    Code getCode() {
        Code code = new Code();
        code.append(new Atom(IR.LABEL, id));
        code.append(new Atom(IR.LINK));
        code.append(stmts.getCode());
        return code;
    }
}

class Expr extends AST_Node {
    Code getCode(){
        return new Code();
    }
}

class ExprList extends Expr {
    List<Expr> exprs;

}

class CallExpr extends Expr {
    FuncDecl func;
    ExprList arg;

//    @Override
//    Code getCode() {
//        Code code = new Code();
//        for(int i = 0; i < arg.exprs.size(); i++){
//            code.append(arg.exprs.get(i).getCode());
//            Atom a = new Atom();
//            a.ins = code.isInt ? IR.STOREI : IR.STOREF;
//            a.op1 = code.result;
//            a.op2 = func.params.get(i).l_val;
//
//        }
//
//        code.append(new Atom(IR.JUMP, func.id));
//        return code;
//    }
}

class VarExpr extends Expr {
    Var var;

    @Override
    Code getCode() {
        Code code = new Code();
        code.result = var.l_val;
        code.isInt = (var.type == Type.INT);
        return code;
    }
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

    OpType op;
    IR ins;
    Expr left;
    Expr right;

    IR getIns(boolean isIntOp){
        switch(op){
            case ADD: return isIntOp ? IR.ADDI : IR.ADDF;
            case SUB: return isIntOp ? IR.SUBI : IR.SUBF;
            case MUL: return isIntOp ? IR.MULTI : IR.MULTF;
            case DIV: return isIntOp ? IR.DIVI : IR.DIVF;
            default : return IR.ERROR;
        }
    }

    Code getCode(){
        Code leftCode = left.getCode();
        Code rightCode = right.getCode();
        Atom a = new Atom();
        a.ins = getIns(leftCode.isInt);
        a.op1 = leftCode.result;
        a.op2 = rightCode.result;
        String resLoc = SymbolTable.makeTemp();
        a.op3 = resLoc;
        Code ret = leftCode.append(rightCode).append(a);
        ret.result = resLoc;
        return ret;
    }

}

class Int_Lit extends Expr {
    String value;

    @Override
    Code getCode() {
        Code code = new Code();
        String resLoc = SymbolTable.makeTemp();
        Atom a = new Atom();
        a.ins = IR.STOREI;
        a.op1 = value;
        a.op2 = resLoc;
        code.append(a);
        code.isInt = true;
        code.result = resLoc;
        return code;
    }
}

class Float_Lit extends Expr {
    String value;

    @Override
    Code getCode() {
        Code code = new Code();
        String resLoc = SymbolTable.makeTemp();
        Atom a = new Atom();
        a.ins = IR.STOREF;
        a.op1 = value;
        a.op2 = resLoc;
        code.append(a);
        code.isInt = false;
        code.result = resLoc;
        return code;
    }
}

class CondExpr extends AST_Node {

    IR inverse(boolean isInt){
        switch(type){
            case "!=": return isInt ? IR.EQI : IR.EQ;
            case "<=": return isInt ? IR.GTI : IR.GT;
            case ">=": return isInt ? IR.LTI :IR.LT;
            case "<": return isInt ? IR.GEI :IR.GE;
            case ">": return isInt ? IR.LEI :IR.LE;
            case "==": return isInt ? IR.NEI :IR.NE;
            default : return IR.ERROR;
        }
    }

    String type = null;
    Expr left = null;
    Expr right = null;

    Code getCode(String label){
        Code lCode = left.getCode();
        String op1 = lCode.result;
        Code rCode = right.getCode();
        String op2 = rCode.result;
        Atom a = new Atom(inverse(lCode.isInt), op1, op2, label);
        return lCode.append(rCode).append(a);
    }

}

class StmtList extends  AST_Node {
    List<Stmt> stmts = new ArrayList<>();

    @Override
    Code getCode() {
        Code code = new Code();
        for(Stmt s : stmts)
            code.append(s.getCode());
        return code;
    }
}

abstract class Stmt extends AST_Node { }

class ReadWriteStmt extends Stmt {
    boolean isRead = false;
    List<Var> args = new ArrayList<>();

    @Override
    Code getCode() {
        Code code = new Code();
        for(Var v : args){
            Atom a = new Atom();
            a.ins = getINS(v.type);
            a.op1 = v.l_val;
            code.append(a);
        }
        return code;
    }

    IR getINS(Type type){
        if (type == Type.STRING)
            return IR.WRITES;
        else if(type == Type.INT)
            return isRead ? IR.READI : IR.WRITEI;
        else
            return isRead ? IR.READF : IR.WRITEF;

    }


}

class WhileStmt extends Stmt {

    static int count = 0;

    SymbolTable table;
    CondExpr cond = null;
    VarDeclList decls = null;
    StmtList stmts = null;

    @Override
    Code getCode() {
        int myNum = count++;

        Code code = new Code();
        String out = "OUTLOOP"+myNum;
        String start = "LOOP"+myNum;
        code.append(new Atom(IR.LABEL, out));
        code.insert(new Atom(IR.JUMP, start));
        code.insert(stmts.getCode());
        code.insert(cond.getCode(out));
        code.insert(new Atom(IR.LABEL, start));
        return code;
    }

}

class IfStmt extends Stmt{
    SymbolTable table;
    CondExpr cond = null;
    StmtList body_then = null;
    ElsePart body_else = null;

    static int count = 0;

    @Override
    Code getCode() {
        int myNum = count++;
        Code code = new Code();
        String outLabel = "OUTIF"+myNum;
        code.append(new Atom(IR.LABEL, outLabel));
        if(body_else == null){
            code.insert(body_then.getCode());
            code.insert(cond.getCode(outLabel));
        } else {
            String elseLabel = "ELSE"+myNum;
            code.insert(body_else.getCode());
            code.insert(new Atom(IR.LABEL, elseLabel));
            code.insert(new Atom(IR.JUMP, outLabel));
            code.insert(body_then.getCode());
            code.insert(cond.getCode(elseLabel));
        }
        return code;
    }
}

class ElsePart extends Stmt {
    SymbolTable table;
    VarDeclList vars = null;
    StmtList stmts = null;

    Code getCode(){
        return stmts.getCode();
    }

}

class AssignStmt extends Stmt {
    Var var = null;
    Expr expr = null;

    Code getCode(){
        Code code = expr.getCode();
        Atom a = new Atom();
        a.ins = code.isInt ? IR.STOREI : IR.STOREF;
        a.op1 = code.result;
        a.op2 = var.l_val;
        code.append(a);
        code.result = var.l_val;
        return code.append(a);
    }


}

class ReturnStmt extends Stmt {
    Expr expr = null;

    Code getCode(){
        Code code = expr.getCode();
        String resLoc = code.result;
        boolean isInt = code.isInt;
        code.append(new Atom(IR.RET, code.result));
        code.result = resLoc;
        code.isInt = isInt;
        return code;
    }
}






