import org.antlr.v4.runtime.*;

public class Driver{

    public static void main(String[] args) throws Exception {


        String testcase = "./tests/test5.little";
        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        LittleParser parser = new LittleParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        boolean failed = false;
        try {
            parser.program();
        } catch(Exception e){
            //System.out.println(e.getOffendingToken().getText());
            failed = true;
        }
        if (failed) {
            System.out.println("Not accepted");
        } else {
            System.out.println("Accepted");
        }
    }

}