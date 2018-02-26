import org.antlr.v4.runtime.*;

public class Driver{

    public static void main(String[] args) throws Exception {


        String testcase = "./tests/test6.little";
        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        LittleParser parser = new LittleParser(tokens);

        boolean failed = false;
        try {
            parser.program();
        } catch(RecognitionException e){
            System.out.println(e.getOffendingToken().getText());
            failed = true;
        }
        if (failed) {
            System.out.println("Not accepted");
        } else {
            System.out.println("Accepted");
        }
    }

}