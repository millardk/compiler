import org.antlr.v4.runtime.*;

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

        Code code = p.getCode();
        code.print();
        System.out.println("------------------TINY-CODE------------------");
        TinyProgram tiny = new TinyProgram(code, p.table.varlist);
        tiny.print();
    }

}