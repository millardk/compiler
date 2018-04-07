import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

class AST_Builder {

    SymbolTable table = null;

    Program create(LittleParser.ProgramContext ctx){
        Program p = new Program();
        LittleParser.Pgm_bodyContext body = ctx.pgm_body();
        table = new SymbolTable();
        p.globals = create(body.decl());
        p.funcs = create(body.func_declarations());
        return p;
    }

    VarDeclList create(LittleParser.DeclContext ctx) {
        VarDeclList vars = new VarDeclList();
        while(ctx != null) {
            if (ctx.var_decl() != null) {
                Type type = Type.getType(ctx.var_decl().var_type().getText());
                List<String> ids = new ArrayList<>();
                vars.vars.add(new Var(type,ctx.var_decl().id_list().id().IDENTIFIER().getText()));

                LittleParser.Id_tailContext tail = ctx.var_decl().id_list().id_tail();
                while (tail.id() != null){
                    vars.vars.add(new Var(type,tail.id().getText()));
                    tail = tail.id_tail();
                }

            } else if (ctx.string_decl() != null){
                LittleParser.String_declContext strCtx = ctx.string_decl();
                Var e = new Var(strCtx.id().getText(), strCtx.str().STRINGLITERAL().getText());
                vars.vars.add(e);
            }
            ctx = ctx.decl();
        }

        for(Var v : vars.vars)
            table.add(v);

        return vars;
    }

    List<FuncDecl> create(LittleParser.Func_declarationsContext ctx){
         List<FuncDecl> list = new ArrayList<>();

         while(ctx.func_decl() != null){

         }
        return null;
    }

    FuncDecl create(LittleParser.Func_declContext ctx){
        table = new SymbolTable(table);

        FuncDecl func = new FuncDecl();
        func.id = ctx.id().getText();
        func.retType = Type.getType(ctx.any_type().var_type().getText());
        func.params = getParams(ctx.param_decl_list());
        for(Var v : func.params)
            table.add(v);

        func.stmts = create(ctx.func_body().stmt_list());
        SymbolTable.add(func);

        table = table.parent;
        return func;
    }

    StmtList create(LittleParser.Stmt_listContext ctx){
        StmtList stmts = new StmtList();
        while (ctx.stmt() != null){
            stmts.stmts.add(create(ctx.stmt()));
            ctx = ctx.stmt_list();
        }
        return stmts;
    }

    Stmt create(LittleParser.StmtContext ctx) {
        assert(ctx != null);

        if (ctx.base_stmt() != null){
        // BASE STATEMENT
            return createBase(ctx.base_stmt());

        } else if (ctx.if_stmt() != null){
        // IF STATEMENT
            IfStmt stmt = new IfStmt();
            table = new SymbolTable(table);
            stmt.table = table;
            stmt.condition = create(ctx.if_stmt().cond());
            stmt.body_then = create(ctx.if_stmt().stmt_list());
            stmt.body_else = create(ctx.if_stmt().else_part());

            table = table.parent;
            return stmt;
        } else {
        // WHILE STATEMENT
            WhileStmt stmt = new WhileStmt();
            table = new SymbolTable();
            stmt.table = table;
            stmt.cond = create(ctx.while_stmt().cond());
            stmt.decls = create(ctx.while_stmt().decl());
            stmt.stmts = create(ctx.while_stmt().stmt_list());

            table = table.parent;
            return new WhileStmt();
        }
    }

    ElsePart create(LittleParser.Else_partContext ctx){

    }

    CondExpr create(LittleParser.CondContext ctx){

    }

    Stmt createBase(LittleParser.Base_stmtContext ctx){
        if (ctx.assign_stmt() != null){
            AssignStmt stmt = new AssignStmt();
            stmt.var = table.get(ctx.assign_stmt().assign_expr().id().getText());
            stmt.expr = create(ctx.assign_stmt().assign_expr().expr());
        }  else if(ctx.return_stmt() != null) {

        } else if(ctx.read_stmt() != null) {

        } else {
        //write statement here

        }

    }

    Expr create(LittleParser.ExprContext ctx){

    }

    List<Var> getParams(LittleParser.Param_decl_listContext ctx){
        List<Var> retList = new ArrayList<>();
        if (ctx.param_decl() != null) {
            Var e = new Var(
                    Type.getType(ctx.param_decl().var_type().getText()),
                    ctx.param_decl().id().getText());


        }
        while(ctx.param_decl() != null)



    }

}
