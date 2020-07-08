package org.mokka88.commons.flow;

public interface FlowComponent<T> {
    default void begin(T data) {
        first().executeCurrent(data);
    }

    void executeCurrent(T data);
    FlowComponent<T> first();
    void setPrev(FlowComponent<T> prev);
}
