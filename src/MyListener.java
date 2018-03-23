import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class MyListener extends LittleBaseListener {

    Scope scope = null;
    int counter = 1;
    declType type = null;
    Queue<String> names = new ArrayDeque<>();
    String lit = null;

//    @Override public void enterProgram(LittleParser.ProgramContext ctx) { }
//
//    @Override public void exitProgram(LittleParser.ProgramContext ctx) { }
//
//    @Override public void enterId(LittleParser.IdContext ctx) { }
//
//    @Override public void exitId(LittleParser.IdContext ctx) { }

    @Override public void enterPgm_body(LittleParser.Pgm_bodyContext ctx) {
        scope = new Scope("GLOBAL", null);
    }

//    @Override public void exitPgm_body(LittleParser.Pgm_bodyContext ctx) {
//    }
//
//    @Override public void enterDecl(LittleParser.DeclContext ctx) { }
//
//    @Override public void exitDecl(LittleParser.DeclContext ctx) { }
//
//    @Override public void enterString_decl(LittleParser.String_declContext ctx) { }

    @Override public void exitString_decl(LittleParser.String_declContext ctx) {
        Entry e = new Entry(ctx.id().getText(), declType.STRING, lit);
        scope.addEntry(e);
    }
//
//    @Override public void enterStr(LittleParser.StrContext ctx) { }

    @Override public void exitStr(LittleParser.StrContext ctx) {
        lit = ctx.STRINGLITERAL().getText();
    }
//
//    @Override public void enterVar_decl(LittleParser.Var_declContext ctx) { }

    @Override public void exitVar_decl(LittleParser.Var_declContext ctx) {
        while (names.size() > 0){
            Entry e = new Entry(names.poll(), type);
            scope.addEntry(e);
        }
    }

//    @Override public void enterVar_type(LittleParser.Var_typeContext ctx) { }

    @Override public void exitVar_type(LittleParser.Var_typeContext ctx) {
        if(ctx.getText().equals("FLOAT")){
            type = declType.FLOAT;
        } else {
            type = declType.INT;
        }
    }
//
//    @Override public void enterAny_type(LittleParser.Any_typeContext ctx) { }
//
//    @Override public void exitAny_type(LittleParser.Any_typeContext ctx) { }
//
    @Override public void enterId_list(LittleParser.Id_listContext ctx) {
        names.add(ctx.id().IDENTIFIER().getText());
    }
//
//    @Override public void exitId_list(LittleParser.Id_listContext ctx) { }
//
    @Override public void enterId_tail(LittleParser.Id_tailContext ctx) {
        if (!ctx.getText().isEmpty())
            names.add(ctx.id().IDENTIFIER().getText());
    }
//
//    @Override public void exitId_tail(LittleParser.Id_tailContext ctx) { }
//
//    @Override public void enterParam_decl_list(LittleParser.Param_decl_listContext ctx) { }
//
//    @Override public void exitParam_decl_list(LittleParser.Param_decl_listContext ctx) { }
//
//    @Override public void enterParam_decl(LittleParser.Param_declContext ctx) { }

    @Override public void exitParam_decl(LittleParser.Param_declContext ctx) {
        Entry e = new Entry(ctx.id().getText(), type);
        scope.addEntry(e);
    }
//
//    @Override public void enterParam_decl_tail(LittleParser.Param_decl_tailContext ctx) { }
//
//    @Override public void exitParam_decl_tail(LittleParser.Param_decl_tailContext ctx) { }
//
//    @Override public void enterFunc_declarations(LittleParser.Func_declarationsContext ctx) { }
//
//    @Override public void exitFunc_declarations(LittleParser.Func_declarationsContext ctx) { }

    @Override public void enterFunc_decl(LittleParser.Func_declContext ctx) {
        String funcName = ctx.id().IDENTIFIER().getText();
        Entry e = new Entry(funcName, declType.FUNC);
        scope.addEntry(e);
        Scope s = new Scope(funcName, scope);
        scope.children.add(s);
        scope = s;
    }

    @Override public void exitFunc_decl(LittleParser.Func_declContext ctx) {
        scope = scope.parent;
    }
//
//    @Override public void enterFunc_body(LittleParser.Func_bodyContext ctx) {
//        System.out.println("scope name"+scope.name);
//        while(!names.isEmpty()){
//            System.out.println(names.poll());
//        }
//    }
//
//    @Override public void exitFunc_body(LittleParser.Func_bodyContext ctx) { }
//
//    @Override public void enterStmt_list(LittleParser.Stmt_listContext ctx) { }
//
//    @Override public void exitStmt_list(LittleParser.Stmt_listContext ctx) { }
//
//    @Override public void enterStmt(LittleParser.StmtContext ctx) { }
//
//    @Override public void exitStmt(LittleParser.StmtContext ctx) { }
//
//    @Override public void enterBase_stmt(LittleParser.Base_stmtContext ctx) { }
//
//    @Override public void exitBase_stmt(LittleParser.Base_stmtContext ctx) { }
//
//    @Override public void enterAssign_stmt(LittleParser.Assign_stmtContext ctx) { }
//
//    @Override public void exitAssign_stmt(LittleParser.Assign_stmtContext ctx) { }
//
//    @Override public void enterAssign_expr(LittleParser.Assign_exprContext ctx) { }
//
//    @Override public void exitAssign_expr(LittleParser.Assign_exprContext ctx) { }
//
//    @Override public void enterRead_stmt(LittleParser.Read_stmtContext ctx) { }
//
//    @Override public void exitRead_stmt(LittleParser.Read_stmtContext ctx) { }
//
//    @Override public void enterWrite_stmt(LittleParser.Write_stmtContext ctx) { }
//
//    @Override public void exitWrite_stmt(LittleParser.Write_stmtContext ctx) { }
//
//    @Override public void enterReturn_stmt(LittleParser.Return_stmtContext ctx) { }
//
//    @Override public void exitReturn_stmt(LittleParser.Return_stmtContext ctx) { }
//
//    @Override public void enterExpr(LittleParser.ExprContext ctx) { }
//
//    @Override public void exitExpr(LittleParser.ExprContext ctx) { }
//
//    @Override public void enterExpr_prefix(LittleParser.Expr_prefixContext ctx) { }
//
//    @Override public void exitExpr_prefix(LittleParser.Expr_prefixContext ctx) { }
//
//    @Override public void enterFactor(LittleParser.FactorContext ctx) { }
//
//    @Override public void exitFactor(LittleParser.FactorContext ctx) { }
//
//    @Override public void enterFactor_prefix(LittleParser.Factor_prefixContext ctx) { }
//
//    @Override public void exitFactor_prefix(LittleParser.Factor_prefixContext ctx) { }
//
//    @Override public void enterPostfix_expr(LittleParser.Postfix_exprContext ctx) { }
//
//    @Override public void exitPostfix_expr(LittleParser.Postfix_exprContext ctx) { }
//
//    @Override public void enterCall_expr(LittleParser.Call_exprContext ctx) { }
//
//    @Override public void exitCall_expr(LittleParser.Call_exprContext ctx) { }
//
//    @Override public void enterExpr_list(LittleParser.Expr_listContext ctx) { }
//
//    @Override public void exitExpr_list(LittleParser.Expr_listContext ctx) { }
//
//    @Override public void enterExpr_list_tail(LittleParser.Expr_list_tailContext ctx) { }
//
//    @Override public void exitExpr_list_tail(LittleParser.Expr_list_tailContext ctx) { }
//
//    @Override public void enterPrimary(LittleParser.PrimaryContext ctx) { }
//
//    @Override public void exitPrimary(LittleParser.PrimaryContext ctx) { }
//
//    @Override public void enterAddop(LittleParser.AddopContext ctx) { }
//
//    @Override public void exitAddop(LittleParser.AddopContext ctx) { }
//
//    @Override public void enterMulop(LittleParser.MulopContext ctx) { }
//
//    @Override public void exitMulop(LittleParser.MulopContext ctx) { }

    @Override public void enterIf_stmt(LittleParser.If_stmtContext ctx) {
        Scope s = new Scope(String.valueOf(counter++), scope);
        scope.children.add(s);
        scope = s;
    }

    @Override public void exitIf_stmt(LittleParser.If_stmtContext ctx) {
        scope = scope.parent;
    }

    @Override public void enterElse_part(LittleParser.Else_partContext ctx) {
        Scope s = new Scope(String.valueOf(counter++), scope);
        scope.children.add(s);
        scope = s;
    }

    @Override public void exitElse_part(LittleParser.Else_partContext ctx) {
        scope = scope.parent;
    }
//
//    @Override public void enterCond(LittleParser.CondContext ctx) { }
//
//    @Override public void exitCond(LittleParser.CondContext ctx) { }
//
//    @Override public void enterCompop(LittleParser.CompopContext ctx) { }
//
//    @Override public void exitCompop(LittleParser.CompopContext ctx) { }

    @Override public void enterWhile_stmt(LittleParser.While_stmtContext ctx) {
        Scope s = new Scope(String.valueOf(counter++), scope);
        scope.children.add(s);
        scope = s;
    }

    @Override public void exitWhile_stmt(LittleParser.While_stmtContext ctx) {
        scope = scope.parent;
    }


}
