public class Entity {
    Type type;
    String id;
    String litVal;

    Entity(Type type, String id) {
        this.type = type;
        this.id = id;
        litVal = null;
    }

    Entity(String id, String lit) {
        type = Type.STRING;
        this.id = id;
        this.litVal = lit;
    }
}
