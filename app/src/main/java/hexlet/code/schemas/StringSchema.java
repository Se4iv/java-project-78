package hexlet.code.schemas;


import java.util.Objects;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        addRule("instanceof", t -> t instanceof String || Objects.isNull(t));
    }

    public StringSchema minLength(int minLength) {
        super.addRule("minLength", t ->
                Objects.isNull(t) || ((String) t).length() >= minLength);
        return this;
    }

    public StringSchema contains(String text) {
        super.addRule("contains", t ->
                Objects.isNull(t) || ((String) t).contains(text));
        return this;
    }

    public StringSchema required() {
        super.required();
        return this;
    }
}
