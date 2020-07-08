package org.mokka88.commons.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Default implementation of {@link ForkFlowComponent}
 *
 * @author mokka88
 * @param <T>
 */
public class Fork<T> extends AbstractFlowComponent<T> implements ForkFlowComponent<T> {
    protected final Map<Object, FlowComponent<T>> componentMap = new HashMap<>();
    protected final Function<T, Object> forkMapping;

    public Fork(Function<T, Object> forkMapping) {
        this.forkMapping = forkMapping;
    }

    public ForkFlowComponent<T> fork(Object key, FlowComponent<T> flow) {
        flow.setPrev(this);
        componentMap.put(key, flow);

        return this;
    }

    @Override
    protected FlowComponent<T> nextFlowComponent() {
        return componentMap.get(forkMapping.apply(data));
    }

    @Override
    protected void businessLogic() {
        // Most probably not needed in a fork
    }
}
