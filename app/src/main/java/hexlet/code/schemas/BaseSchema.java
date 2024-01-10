package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    protected Map<String, Predicate<Object>> rules = new TreeMap<>();

    /**
     * Add rules to map of rules.
     * @param ruleName
     * @param rule
     */
    protected void addRule(String ruleName, Predicate<Object> rule) {
        rules.put(ruleName, rule);
    }

    /**
     * Makes rule to check if object is null or empty.
     * @return BaseSchema
     */
    protected BaseSchema required() {
        addRule("required", t -> !(Objects.isNull(t) || t.toString().isEmpty()));
        return this;
    }

    /**
     * Checks if all rules are true.
     * @param object
     * @return boolean
     */
    protected boolean isValid(Object object) {
        return rules.values().stream().allMatch(x -> x.test(object));
    }
}
