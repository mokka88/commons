package org.mokka88.commons.flow;

/**
 * Default implementation of {@link LinearFlowComponent}
 * The internal business logic still needs to be implemented in a subclass.
 *
 * @author mokka88
 * @param <T>
 */
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
