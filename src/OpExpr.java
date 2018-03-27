public class OpExpr extends Node {

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
