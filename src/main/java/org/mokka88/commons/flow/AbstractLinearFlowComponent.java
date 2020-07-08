package org.mokka88.commons.flow;

public abstract class AbstractLinearFlowComponent<T> extends AbstractFlowComponent<T> implements LinearFlowComponent<T> {
    private FlowComponent<T> next;

    protected FlowComponent<T> nextFlowComponent() {
        return next;
    }

    @Override
    public <C extends FlowComponent<T>> C next(C next) {
        this.next = next;
        next.setPrev(this);

        return next;
    }
}
