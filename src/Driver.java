import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Driver{

    public static void main(String[] args) throws Exception {
        String testcase = "./tests/step3/test12.little";

        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LittleParser parser = new LittleParser(tokens);

        MyListener l = new MyListener();
        new ParseTreeWalker().walk(l, parser.program());

        l.scope.printTree();

    }

}