import java.util.List;

public class FuncDef extends Node {

    enum ReturnType {
        VOID,
        FLOAT,
        INT
    }

    String id;
    ReturnType ret_type;
    List<VarExpr> params;
    StmtList stmtList;

}
