package org.mokka88.commons.flow;

/**
 * Most common flow component.
 * A single next flow component may be defined.
 * @param <T>
 * @author mokka88
 */
public interface LinearFlowComponent<T> extends FlowComponent<T> {
    /**
     * Defines next flow component to be executed
     *
     * @param next
     * @param <C>
     * @return
     */
    <C extends FlowComponent<T>> C next(C next);
}
