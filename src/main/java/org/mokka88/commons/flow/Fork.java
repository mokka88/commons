package org.mokka88.commons.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Fork<T> extends AbstractFlowComponent<T> implements ForkFlowComponent<T> {
    protected final Map<Object, FlowComponent<T>> nextFlows = new HashMap<>();
    protected final Function<T, Object> forkMapping;

    public Fork(Function<T, Object> forkMapping) {
        this.forkMapping = forkMapping;
    }

    public ForkFlowComponent<T> fork(Object key, FlowComponent<T> flow) {
        flow.setPrev(this);
        nextFlows.put(key, flow);

        return this;
    }

    @Override
    protected FlowComponent<T> nextFlowComponent() {
        return nextFlows.get(forkMapping.apply(data));
    }

    @Override
    protected void implementation() {
        // Most probably not needed in a fork
    }
}
