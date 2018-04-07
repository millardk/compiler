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
        VarDeclList decls = new VarDeclList();
        while(ctx != null) {
            if (ctx.var_decl() != null) {
                Type type = Type.getType(ctx.var_decl().var_type().getText());
                List<String> ids = create(ctx.var_decl().id_list());
                for(String id : ids) {
                    Var v = new Var(type, id);
                    table.add(v);
                    decls.vars.add(v);
                }
            } else if (ctx.string_decl() != null){
                LittleParser.String_declContext strCtx = ctx.string_decl();
                Var e = new Var(strCtx.id().getText(), strCtx.str().STRINGLITERAL().getText());
                decls.vars.add(e);
            }
            ctx = ctx.decl();
        }

        return decls;
    }


    List<String> create(LittleParser.Id_listContext ctx){
        List<String> ids = new ArrayList<>();
        ids.add(ctx.id().getText());

        LittleParser.Id_tailContext tail = ctx.id_tail();
        while (tail.id() != null){
            ids.add(tail.id().getText());
            tail = tail.id_tail();
        }
        return ids;
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
        func.params = create(ctx.param_decl_list());
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
        if (ctx.stmt_list() == null)
            return null;
        else {
            ElsePart e = new ElsePart();
            table = new SymbolTable(table);
            e.table = table;
            e.vars = create(ctx.decl());
            e.stmts = create(ctx.stmt_list());

            table = table.parent;
            return e;
        }
    }

    CondExpr create(LittleParser.CondContext ctx){
        CondExpr cond = new CondExpr();
        cond.type = CondExpr.CondType.getType(
                ctx.compop().getText());
        cond.left = create(ctx.expr(0));
        cond.right = create(ctx.expr(1));
        return cond;
    }

    Stmt createBase(LittleParser.Base_stmtContext ctx){
        if (ctx.assign_stmt() != null){
            AssignStmt stmt = new AssignStmt();
            stmt.var = table.get(ctx.assign_stmt().assign_expr().id().getText());
            stmt.expr = create(ctx.assign_stmt().assign_expr().expr());
            return stmt;

        }  else if(ctx.return_stmt() != null) {
            ReturnStmt stmt = new ReturnStmt();
            stmt.expr = create(ctx.return_stmt().expr());
            return stmt;

        } else if(ctx.read_stmt() != null) {
            ReadWriteStmt read = new ReadWriteStmt();
            read.isRead = true;
            read.args = new ArrayList<>();
            List<String> ids = create(ctx.read_stmt().id_list());
            for(String id : ids)
                read.args.add(table.get(id));
            return read;

        } else {
        //write statement here
            ReadWriteStmt write = new ReadWriteStmt();
            write.isRead = false;
            write.args = new ArrayList<>();
            List<String> ids = create(ctx.write_stmt().id_list());
            for(String id : ids)
                write.args.add(table.get(id));
            return write;
        }
    }

    Expr create(LittleParser.ExprContext ctx){
        if(ctx.expr_prefix() == null)
    }

    List<Var> create(LittleParser.Param_decl_listContext ctx){
        List<Var> retList = new ArrayList<>();
        if (ctx.param_decl() != null) {
            Var e = new Var(
                    Type.getType(ctx.param_decl().var_type().getText()),
                    ctx.param_decl().id().getText());


        }
        while(ctx.param_decl() != null)
    }

}
