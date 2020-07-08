package org.mokka88.commons.flow;

/**
 * Default, abstract implementation of {@link FlowComponent}
 * Please do not extend this class, unless the flow framework needs to be extended.
 * For regular use, please extend one of it's subclasses.
 *
 * @author mokka88
 * @param <T>
 */
public abstract class AbstractFlowComponent<T> implements FlowComponent<T> {
    protected T context;
    private FlowComponent<T> prev;

    @Override
    public void executeCurrent(T context) {
        this.context = context;

        businessLogic();

        executeNext();
    }

    protected void executeNext() {
        FlowComponent<T> next = nextFlowComponent();

        if (next != null) {
            next.executeCurrent(context);
        }
    }

    protected abstract FlowComponent<T> nextFlowComponent();

    protected abstract void businessLogic();

    @Override
    public void setPrev(final FlowComponent<T> prev) {
        this.prev = prev;
    }

    @Override
    public FlowComponent<T> first() {
        if (prev == null) {
            return this;
        } else {
            return prev.first();
        }
    }
}
