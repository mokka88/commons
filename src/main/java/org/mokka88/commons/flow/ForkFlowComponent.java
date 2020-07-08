package org.mokka88.commons.flow;

/**
 * Multiple alternative flow components may be defined as next component to be executed.
 * The next component is chosen based upon a mapping.
 *
 * @author mokka88
 * @param <T>
 */
public interface ForkFlowComponent<T> extends FlowComponent<T> {
    /**
     * Defines mapping for switch-case-like branches
     *
     * @param key
     * @param flowComponent
     * @return
     */
    ForkFlowComponent<T> fork(Object key, FlowComponent<T> flowComponent);
}
