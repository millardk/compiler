import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Driver implements ParseTreeListener{

    boolean error = false;

    public static void main(String[] args) throws Exception {

        String testcase = "./test2s/test.little";
        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        LittleParser parser = new LittleParser(tokens);
        try {
            parser.program();
        } catch(Exception e){
        }

    }


    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {
        error = true;
    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}