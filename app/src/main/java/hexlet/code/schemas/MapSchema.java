package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        addRule("instanceof", t -> t instanceof Map<?, ?> || Objects.isNull(t));
    }

    public MapSchema shape(Map<String, BaseSchema> schema) {
        addRule("shape", t -> ((Map<?, ?>) t).entrySet().stream()
                .allMatch(x -> schema.get(x.getKey()).isValid(x.getValue())));
        return this;
    }

    public MapSchema sizeof(int limit) {
        addRule("sizeof",
                t -> Objects.isNull(t) || ((Map<?, ?>) t).size() == limit);
        return this;
    }

    public MapSchema required() {
        super.required();
        return this;
    }
}