package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {

    public NumberSchema range(int from, int to) {
        addRule("range",
                x -> Objects.isNull(x) || (((Integer) x) >= from && ((Integer) x) <= to));
        return this;
    }

    public NumberSchema positive() {
        addRule("positive", x -> Objects.isNull(x) || ((Integer) x) > 0);
        return this;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    @Override
    public boolean isValid(Object o) {
        if (!(o instanceof Integer) && !(Objects.isNull(o))) {
            return false;
        }
        return super.isValid(o);
    }

}
