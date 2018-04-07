public enum Type {
    VOID,
    FUNC,
    INT,
    FLOAT,
    STRING;

    static Type getType(String s){
        switch (s){
            case "FLOAT": return FLOAT;
            case "INT": return INT;
            case "STRING": return STRING;
            case "FUNCTION": return FUNC;
            default : return VOID;
        }
    }
}
