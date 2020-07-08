package org.mokka88.commons.flow;

public abstract class AbstractFlowComponent<T> implements FlowComponent<T> {
    protected T data;
    private FlowComponent<T> prev;

    @Override
    public void executeCurrent(T data) {
        this.data = data;

        implementation();

        executeNext();
    }

    protected void executeNext() {
        FlowComponent<T> next = nextFlowComponent();

        if (next != null) {
            next.executeCurrent(data);
        }
    }

    protected abstract FlowComponent<T> nextFlowComponent();

    protected abstract void implementation();

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
