import java.util.*;

public class TinyProgram {

    private List<TinyIns> insList = new ArrayList<>();

    public void print() {
        for (TinyIns ins : insList)
            System.out.println(ins);
    }

    public TinyProgram(Code code, List<Var> vars) {
        new TinyConverter().convertProg(code, vars);
    }

    private class TinyConverter {

        private HashMap<String,String> regMap = new HashMap<>();
        int regNum = 0;
//        private HashMap<String, String> labelMap = new HashMap<>();
//        int labelNum = 1;

        void convertProg(Code code, List<Var> vars) {
            for (Var var : vars) {
                TinyIns ins = new TinyIns();
                ins.op1 = var.id;
                if (var.type == Type.STRING) {
                    ins.type = "str";
                    ins.op2 = var.litVal;
                } else {
                    ins.type = "var";
                }

                insList.add(ins);
            }
            Atom atom = code.start;
            while (atom != null) {
                if (atom.ins.ordinal() <= 9) {
                    TinyIns ins = convert(atom);
                    if (ins != null)
                        insList.add(ins);
                } else {
                    insList.addAll(convertList(atom));
                }
                atom = atom.next;
            }

            insList.add(new TinyIns("sys", "halt"));
//            insList.add(new TinyIns("end"));
//            labelMap.put("main", "main");
            convSymbols();
        }

        TinyIns convert(Atom a) {
            switch (a.ins) {
                case READI: return new TinyIns("sys", "readi", a.op1);
                case READF: return new TinyIns("sys", "readr", a.op1);
                case WRITEI: return new TinyIns("sys", "writei", a.op1);
                case WRITEF: return new TinyIns("sys", "writer", a.op1);
                case WRITES: return new TinyIns("sys", "writes", a.op1);
                case LABEL: return new TinyIns("label", a.op1.toLowerCase());
                case JUMP: return new TinyIns("jmp", a.op1.toLowerCase());
                case LINK: ; return null;
                case RET: ; return null;
                case ERROR: ;
                default : return new TinyIns("this shouldn't be here.");
            }
        }

        List<TinyIns> convertList(Atom a) {
            List<TinyIns> list = new LinkedList<>();
            String ins = null;
                switch (a.ins) {
                case STOREI: ;
                case STOREF: ins = "move"; break;
                case ADDI: ins = "addi"; break;
                case SUBI: ins = "subi"; break;
                case MULTI: ins = "muli"; break;
                case DIVI: ins = "divi"; break;
                case ADDF: ins = "addr"; break;
                case SUBF: ins = "subr"; break;
                case MULTF: ins = "mulr"; break;
                case DIVF: ins = "divr"; break;
                case GT: ins = "jgt"; break;
                case GE: ins = "jge"; break;
                case LT: ins = "jlt"; break;
                case LE: ins = "jle"; break;
                case NE: ins = "jne"; break;
                case EQ: ins = "jeq"; break;
                case GTI: ins = "jgt"; break;
                case GEI: ins = "jge"; break;
                case LTI: ins = "jlt"; break;
                case LEI: ins = "jle"; break;
                case NEI: ins = "jne"; break;
                case EQI: ins = "jeq"; break;
            }

            if (a.ins.ordinal() <= 9) {
                System.out.println("WTF");
                return list;
            }

            if (a.ins == IR.STOREI || a.ins == IR.STOREF) {
                // STORE COMMANDS
                if (isTemp(a.op2) || isTemp(a.op1)) {
                    list.add(new TinyIns(ins, a.op1, a.op2));
                } else {
                    String resultReg = SymbolTable.makeTemp();
                    list.add(new TinyIns(ins, a.op1, resultReg));
                    list.add(new TinyIns(ins, resultReg, a.op2));
                }
            } else if (a.ins.ordinal() <= 19) {
                // Arithmetic instructions
                list.add(new TinyIns("move", a.op1, a.op3));
                list.add(new TinyIns(ins, a.op2, a.op3));

            } else {
                // COMPARE COMMANDS
                String ins2 = (a.ins.ordinal() <= 25) ? "cmpr" : "cmpi";
                list.add(new TinyIns(ins2, a.op1, a.op2));
                list.add(new TinyIns(ins, a.op3.toLowerCase()));
            }
            return list;
        }
//
//        private void convSymbols(){
//            for(TinyIns ins : insList) {
//                if(ins.type.equals("label")) {
//                    if(!labelMap.containsKey(ins.op1))
//                        labelMap.put(ins.op1, "label"+labelNum++);
//                    ins.op1 = labelMap.get(ins.op1);
//                    continue;
//                }
//                if(ins.op1 != null)
//                    ins.op1 = convTemp(ins.op1);
//                if(ins.op2 != null)
//                    ins.op2 = convTemp(ins.op2);
//            }
//            for(TinyIns ins : insList)
//                if(ins.type.charAt(0) == 'j')
//                    ins.op1 = labelMap.get(ins.op1);
//
//        }

        private void convSymbols(){
            for(TinyIns ins : insList) {
                if (ins.op1 != null)
                    ins.op1 = convTemp(ins.op1);
                if (ins.op2 != null)
                    ins.op2 = convTemp(ins.op2);
            }
        }

        private boolean isTemp(String op){
            assert(op != null);
            return op.charAt(0) == '$';
        }

        private String convTemp(String op){
            if (isTemp(op)){
                if(!regMap.containsKey(op))
                    regMap.put(op, "r"+regNum++);
                return regMap.get(op);
            } else
                return op;
        }


        //        // TODO scan the IR to recognize globals
//        private Set<String> getGlobals(Code code){
//            HashSet<String> globals = new HashSet<>();
//            Atom atom = code.start;
//            while(atom != null){
//                atom = atom.next;
//            }
//
//            return globals;
//        }

    }


}

class TinyIns {

    String type = null;
    String op1 = null;
    String op2 = null;

    TinyIns(){}

    TinyIns(String type){
        this.type = type;
    }

    TinyIns(String type, String op1){
        this.type = type;
        this.op1 = op1;
    }

    TinyIns(String type, String op1, String op2){
        this.type = type;
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        String str = type;
        if (op1 == null)
            return str;
        if (op2 == null)
            return str+" "+op1;
        return str+" "+op1+" "+op2;

    }

}
