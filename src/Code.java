class Code {

    Atom start = null;
    Atom end = null;

    void insert(Code code){
        if (start == null){
            assert(end == null);
            start = code.start;
            end = code.end;
        } else if (end == null){
            assert(false);
        } else {
            code.end.next = start;
            start = code.start;
        }
    }

    void add(Atom a){
        end.next = a;
        end = a;
    }

    boolean isInt(){
        return end.isInt;
    }

    boolean isFloat(){
        return !end.isInt;
    }

    String getResult(){
        return end.result;
    }

}

class Atom {

    Atom next = null;
    IR ins = null;
    String op1 = null;
    String op2 = null;
    String op3 = null;
    boolean isInt = false;
    String result;


    Atom (IR ins, String op1, String op2, String op3){
        this.ins = ins;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    Atom (IR ins, String op1, String op2){
        this.ins = ins;
        this.op1 = op1;
        this.op2 = op2;

    }


}

enum IR{
    ADDI,
    SUBI,
    MULTI,
    DIVI,
    ADDF,
    SUBF,
    MULTF,
    DIVF,
    STOREI,
    STOREF,
    READI,
    READF,
    WRITEI,
    WRITEF,
    WRITES,
    LABEL,
    JUMP,
    GT,
    GE,
    LT,
    LE,
    NE,
    EQ,
}
