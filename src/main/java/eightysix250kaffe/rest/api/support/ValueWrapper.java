package eightysix250kaffe.rest.api.support;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Objects;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

public abstract class ValueWrapper<T> implements Serializable {
    protected final T value;

    protected ValueWrapper(T value) {
        this.value = checkNotNull(value, this.getClass().getSimpleName() + " value cannot be null");

        if (value instanceof String) {
            checkArgument(!isNullOrEmpty((String) value), this.getClass().getSimpleName() + " cannot be null or empty");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), value);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ValueWrapper) {
            ValueWrapper that = (ValueWrapper) object;
            return Objects.equal(this.value, that.value);
        }
        return false;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

}
