package org.mokka88.commons.flow;

public interface ForkFlowComponent<T> extends FlowComponent<T> {
    ForkFlowComponent<T> fork(Object key, FlowComponent<T> flowComponent);
}
