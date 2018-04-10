class Code {

    Atom start = null;
    Atom end = null;
    String result = null;
    boolean isInt = true;

    Code insert(Code code){
        if(code == null )
            return this;

        if (start == null){
            start = code.start;
            end = code.end;
        } else {
            code.end.next = start;
            start = code.start;
        }
        return this;
    }

    Code insert(Atom a){
        if(a == null )
            return this;

        if (start == null){
            start = a;
            end = a;
        } else {
            a.next = start;
            start = a;
        }
        return this;
    }

    Code append(Code code){
        if (code == null)
            return this;

        if (start == null) {
            start = code.start;
            end = code.end;
        } else if(code.start != null){
            end.next = code.start;
            end = code.end;
        }
        result = code.result;
        isInt = code.isInt;
        return this;
    }


    Code append(Atom a){
        if(a == null)
            return this;

        if (start == null) {
            start = a;
            end = a;
        } else {
            end.next = a;
            end = a;
        }
        return this;
    }

    void print(){
        System.out.println(";IR code");
        Atom cur = start;
        while(cur != null){
            System.out.println(';'+cur.toString());
            cur = cur.next;
        }
        System.out.println(";tiny code");
    }

}

class Atom {

    Atom next = null;
    IR ins = null;
    String op1 = null;
    String op2 = null;
    String op3 = null;

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

    Atom (IR ins, String op1){
        this.ins = ins;
        this.op1 = op1;
    }

    Atom (IR ins){
        this.ins = ins;
    }

    Atom (){}


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(ins);
        if (op1 == null)
            return sb.toString();
        sb.append(" ");
        sb.append(op1);
        if (op2 == null)
            return sb.toString();
        sb.append(" ");
        sb.append(op2);
        if (op3 == null)
            return sb.toString();
        sb.append(" ");
        sb.append(op3);

        return sb.toString();
    }
}

enum IR{
    READI,
    READF,
    WRITEI,
    WRITEF,
    WRITES,
    LABEL,
    JUMP,
    LINK,
    RET,
    ERROR,
    STOREI,
    STOREF,
    ADDI,
    SUBI,
    MULTI,
    DIVI,
    ADDF,
    SUBF,
    MULTF,
    DIVF,
    GT,
    GE,
    LT,
    LE,
    NE,
    EQ,
    GTI,
    GEI,
    LTI,
    LEI,
    NEI,
    EQI,

}
