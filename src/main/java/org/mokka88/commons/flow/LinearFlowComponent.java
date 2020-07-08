package org.mokka88.commons.flow;

public interface LinearFlowComponent<T> extends FlowComponent<T> {
    <C extends FlowComponent<T>> C next(C next);
}
