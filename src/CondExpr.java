public class CondExpr extends Expr {

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
