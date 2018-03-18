public class Entry {

    String name;
    declType type;
    String value;

    public Entry (String name, declType type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Entry (String name, declType type) {
        this.name = name;
        this.type = type;
        value = null;
    }

    public void print(){
        if (type == declType.FUNC) return;

        StringBuilder sb = new StringBuilder();
        sb.append("name ");
        sb.append(name);
        sb.append(" type ");
        sb.append(type.name());
        if (!(value == null)){
            sb.append(" value ");
            sb.append(value);
        }
        System.out.println(sb.toString());
    }

}