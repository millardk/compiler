import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expr_Builder extends LittleBaseListener{

    List<Expr> exprs = new ArrayList<>();
    List<Character> ops = new ArrayList<>();

    @Override
    public void enterExpr_list(LittleParser.Expr_listContext ctx) {
        exprs.add(new ExprList());
    }

    @Override
    public void exitExpr_list(LittleParser.Expr_listContext ctx) {
        super.exitExpr_list(ctx);
    }

    @Override
    public void enterExpr(LittleParser.ExprContext ctx) {
        super.enterExpr(ctx);
    }

    @Override
    public void exitExpr(LittleParser.ExprContext ctx) {
        super.exitExpr(ctx);
    }

    @Override
    public void enterExpr_prefix(LittleParser.Expr_prefixContext ctx) {
        super.enterExpr_prefix(ctx);
    }

    @Override
    public void exitExpr_prefix(LittleParser.Expr_prefixContext ctx) {
        super.exitExpr_prefix(ctx);
    }

    @Override
    public void enterCall_expr(LittleParser.Call_exprContext ctx) {
        super.enterCall_expr(ctx);
    }

    @Override
    public void exitCall_expr(LittleParser.Call_exprContext ctx) {
        super.exitCall_expr(ctx);
    }

}
