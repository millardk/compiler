import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Driver{

    public static void main(String[] args) throws Exception {
        String testcase = "./tests/step3/test9.little";

        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LittleParser parser = new LittleParser(tokens);
        ParseTree tree = parser.program();

        LittleTestVisitor test = new LittleTestVisitor();
        test.visit(tree);

    }

}