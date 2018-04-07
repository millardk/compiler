import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Driver{

    public static void main(String[] args) throws Exception {
        String path = "./tests/step4/test1.txt";
        System.out.println("Compiling "+path);
        CharStream cs = CharStreams.fromFileName(path);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LittleParser parser = new LittleParser(tokens);

        System.out.println("Parse done, building AST.");
        AST_Builder builder = new AST_Builder();
        Program p = builder.create(parser.program());
        try {
            p.print();
        } catch (java.io.IOException e){
            e.printStackTrace();
        }

        Code prog = p


    }

}