package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        addRule("instanceof", t -> t instanceof Integer || Objects.isNull(t));
    }

    public NumberSchema range(int from, int to) {
        addRule("range",
                t -> Objects.isNull(t) || (((Integer) t) >= from && ((Integer) t) <= to));
        return this;
    }

    public NumberSchema positive() {
        addRule("positive", t -> Objects.isNull(t) || ((Integer) t) > 0);
        return this;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean isValid(Object o) {
        return super.isValid(o);
    }

}
