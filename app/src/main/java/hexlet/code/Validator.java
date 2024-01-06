package hexlet.code;


import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }

//    public static void main(String[] args) {
//        Validator v = new Validator();
//        MapSchema actual = v.map();
//        Map<String, BaseSchema> schemas = new HashMap<>();
//        schemas.put("name", v.string().required());
//        schemas.put("age", v.number().positive());
//        actual.shape(schemas);
//        Map<String, Object> human1 = new HashMap<>();
//        human1.put("name", "Kolya");
//        human1.put("age", 100);
//        System.out.println(actual.isValid(human1)); // true
//
//        Map<String, Object> human2 = new HashMap<>();
//        human2.put("name", "Maya");
//        human2.put("age", null);
//        System.out.println(actual.isValid(human2)); // true
//
//        Map<String, Object> human3 = new HashMap<>();
//        human3.put("name", "");
//        human3.put("age", null);
//        System.out.println(actual.isValid(human3)); // false
//
//        Map<String, Object> human4 = new HashMap<>();
//        human4.put("name", "Valya");
//        human4.put("age", -5); //false
//        System.out.println(actual.isValid(human4));
//    }



}
