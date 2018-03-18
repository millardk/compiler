import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Driver{

    public static void main(String[] args) throws Exception {
        String testcase = args[0];
        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        LittleParser parser = new LittleParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        System.setErr(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
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