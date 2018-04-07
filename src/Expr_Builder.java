public class Expr_Builder extends LittleBaseListener{


    Expr root = null;

    @Override
    public void enterExpr_list(LittleParser.Expr_listContext ctx) {
        if(root == null)
            root = new ExprList();
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
//        if (base == null)


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
