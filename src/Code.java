import java.util.LinkedList;

public class Code {

    LinkedList<Atom> atomList;

    class Atom {
        String ins;
        String a1;
        String a2;
        String a3;
    }

    void join(Code code){
        atomList.addAll(code.atomList);
    }
}
