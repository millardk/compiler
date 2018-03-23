import java.util.ArrayList;
import java.util.List;

public class Entry {

    String name;
    declType type;
    String value;
    boolean error;

    public Entry (String name, declType type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
        error = false;
    }

    public Entry (String name, declType type) {
        this.name = name;
        this.type = type;
        value = null;
        error = false;
    }

    public void print(){
        if (type == declType.FUNC) return;

        if (error) {
            System.out.println("DECLARATION ERROR " + name);
        } else {

            StringBuilder sb = new StringBuilder();
            sb.append("name ");
            sb.append(name);
            sb.append(" type ");
            sb.append(type.name());
            if (!(value == null)) {
                sb.append(" value ");
                sb.append(value);
            }
            System.out.println(sb.toString());
        }
    }

}