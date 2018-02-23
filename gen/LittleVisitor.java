// Generated from /Users/keegan/IdeaProjects/Compiler/Little.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LittleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LittleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LittleParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LittleParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(LittleParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#pgm_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPgm_body(LittleParser.Pgm_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(LittleParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#string_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_decl(LittleParser.String_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(LittleParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#var_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_decl(LittleParser.Var_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#var_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_type(LittleParser.Var_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#any_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny_type(LittleParser.Any_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#id_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_list(LittleParser.Id_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#id_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId_tail(LittleParser.Id_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#param_decl_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl_list(LittleParser.Param_decl_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#param_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl(LittleParser.Param_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#param_decl_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_decl_tail(LittleParser.Param_decl_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#func_declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_declarations(LittleParser.Func_declarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#func_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_decl(LittleParser.Func_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#func_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_body(LittleParser.Func_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#stmt_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt_list(LittleParser.Stmt_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(LittleParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#base_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBase_stmt(LittleParser.Base_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#assign_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stmt(LittleParser.Assign_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#assign_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_expr(LittleParser.Assign_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#read_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead_stmt(LittleParser.Read_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#write_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite_stmt(LittleParser.Write_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#return_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_stmt(LittleParser.Return_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(LittleParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#expr_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_prefix(LittleParser.Expr_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#ei}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEi(LittleParser.EiContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#etail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEtail(LittleParser.EtailContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(LittleParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#factor_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor_prefix(LittleParser.Factor_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#ai}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAi(LittleParser.AiContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#atail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtail(LittleParser.AtailContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#postfix_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfix_expr(LittleParser.Postfix_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#call_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_expr(LittleParser.Call_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(LittleParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#expr_list_tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list_tail(LittleParser.Expr_list_tailContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(LittleParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#addop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddop(LittleParser.AddopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#mulop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulop(LittleParser.MulopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(LittleParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#else_part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_part(LittleParser.Else_partContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(LittleParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#compop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompop(LittleParser.CompopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LittleParser#while_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stmt(LittleParser.While_stmtContext ctx);
}