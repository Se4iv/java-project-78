package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Map<String, Predicate<Object>> rules = new HashMap<>();

    protected void addRule(String ruleName, Predicate<Object> rule) {
        rules.put(ruleName, rule);
    }

    protected BaseSchema required() {
        addRule("required", t -> !(Objects.isNull(t) || t.toString().isEmpty()));
        return this;
    }
    protected boolean isValid(Object object) {
        return rules.values().stream().allMatch(x -> x.test(object));
    }
}
