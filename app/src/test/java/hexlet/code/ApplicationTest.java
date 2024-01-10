package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

public class ApplicationTest {
    private final Validator validator = new Validator();

    @Test
    public void testStringSchema() {
        StringSchema actual = validator.string();
        assertThat(actual.isValid("")).isEqualTo(true);
        assertThat(actual.isValid(null)).isEqualTo(true);

        actual.required();
        assertThat(actual.isValid(null)).isEqualTo(false);
        assertThat(actual.isValid("")).isEqualTo(false);
        assertThat(actual.isValid(5)).isEqualTo(false);
        assertThat(actual.isValid('4')).isEqualTo(false);
        assertThat(actual.isValid("hello")).isEqualTo(true);

        actual.minLength(6);
        assertThat(actual.isValid("hello")).isEqualTo(false);
        assertThat(actual.isValid("doremifasol")).isEqualTo(true);

        actual.minLength(4);
        assertThat(actual.isValid("hello")).isEqualTo(true);

        actual.contains("he");
        assertThat(actual.isValid("hello")).isEqualTo(true);

        actual.contains("hello");
        assertThat(actual.isValid("hello")).isEqualTo(true);
        assertThat(actual.isValid("hell")).isEqualTo(false);

        actual.contains("hellofriend");
        assertThat(actual.isValid("hello")).isEqualTo(false);
    }

    @Test
    public void testNumberSchema() {
        NumberSchema actual = validator.number();
        assertThat(actual.isValid(null)).isEqualTo(true);
        assertThat(actual.isValid("")).isEqualTo(false);
        assertThat(actual.isValid("test")).isEqualTo(false);

        actual.required();
        assertThat(actual.isValid(null)).isEqualTo(false);
        assertThat(actual.isValid("test")).isEqualTo(false);
        assertThat(actual.isValid(-4)).isEqualTo(true);

        actual.positive();
        assertThat(actual.isValid(-4)).isEqualTo(false);
        assertThat(actual.isValid(0)).isEqualTo(false);

        actual.range(3, 9);
        assertThat(actual.isValid(2)).isEqualTo(false);
        assertThat(actual.isValid(10)).isEqualTo(false);
        assertThat(actual.isValid(3)).isEqualTo(true);
        assertThat(actual.isValid(9)).isEqualTo(true);

        actual.range(4, 9);
        assertThat(actual.isValid(3)).isEqualTo(false);

    }

    @Test
    public void testMapSchema() {
        MapSchema actual = validator.map();
        assertThat(actual.isValid(null)).isEqualTo(true);
        assertThat(actual.isValid("")).isEqualTo(false);
        assertThat(actual.isValid(4)).isEqualTo(false);

        actual.required();
        assertThat(actual.isValid(null)).isEqualTo(false);

        Map<Object, Object> map = new HashMap<>();
        assertThat(actual.isValid(map)).isEqualTo(true);
        map.put("hey", 4);
        assertThat(actual.isValid(map)).isEqualTo(true);

        actual.sizeof(2);
        assertThat(actual.isValid(map)).isEqualTo(false);
        map.put(4, 5);
        assertThat(actual.isValid(map)).isEqualTo(true);

        actual.sizeof(4);
        assertThat(actual.isValid(map)).isEqualTo(false);
    }
    @Test
    public void testShapeMapSchema() {
        MapSchema actual = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

        actual.shape(schemas);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(actual.isValid(human1)).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(actual.isValid(human2)).isEqualTo(true);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(actual.isValid(human3)).isEqualTo(false);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertThat(actual.isValid(human4)).isEqualTo(false);
    }

    @Test
    public void testShapeMapSchema2() {
        MapSchema actual = validator.map();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("digits", validator.number().required());
        schemas.put("map", validator.map().sizeof(2));

        actual.shape(schemas);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("digits", null);
        map1.put("map", new HashMap<>());
        assertThat(actual.isValid(map1)).isEqualTo(false);

        Map<String, Object> map2 = new HashMap<>();
        Map<Object, Object> testmap = new HashMap<>();
        testmap.put(4, 4);
        testmap.put(5, 5);
        map2.put("digits", 4);
        map2.put("map", testmap);
        assertThat(actual.isValid(map2)).isEqualTo(true);
        testmap.put(6, 6);
        assertThat(actual.isValid(map2)).isEqualTo(false);

    }
}
