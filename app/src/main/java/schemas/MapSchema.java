package schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> schemas;

    public MapSchema() {
        this.schemas = new HashMap<>();
    }

    public void shape(Map<String, BaseSchema> schema) {
        this.schemas.putAll(schema);
    }

    public MapSchema sizeof(int limit) {
        addRule("sizeof",
                x -> Objects.isNull(x) || ((Map<?, ?>) x).size() == limit);
        return this;
    }

    public MapSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean isValid(Object o) {
        if (!(o instanceof Map<?, ?>) && !(Objects.isNull(o))) {
            return false;
        }
        if (!schemas.isEmpty()) {
            return ((Map<?, ?>) o).entrySet().stream()
                    .allMatch(x -> schemas.get(x.getKey()).isValid(x.getValue()));
        }
        return super.isValid(o);
    }
}
