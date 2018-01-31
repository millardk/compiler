import org.antlr.v4.runtime.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver{

    public static void main(String[] args) throws Exception {

        Map <Integer,String> typeMap = makeTypenameMap();

        String testcase = "./tests/test2.little";
        CharStream cs = CharStreams.fromFileName(testcase);
        LittleLexer lexer = new LittleLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        for(int i = 0; i < tokens.size()-1; i++){
            Token tok = tokens.get(i);
            System.out.println("Token Type: " + typeMap.get(tok.getType()));
            System.out.println("Value: " + tok.getText());

        }
    }

    private static Map<Integer, String> makeTypenameMap(){
        HashMap<Integer,String> ret = new HashMap<>();
        try {
            Scanner in = new Scanner(new File("./gen/LittleLexer.tokens"));
            while (in.hasNextLine()){
                String[] line = in.nextLine().split("=");
                ret.put(Integer.valueOf(line[1]),line[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

}