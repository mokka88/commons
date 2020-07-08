package org.mokka88.commons.flow;

/**
 * This is an improved State Design Pattern.
 * It's meant for representing business workflows in an elegant manner.
 * The next/fork methods are defined in the subinterfaces: {@link LinearFlowComponent} and {@link ForkFlowComponent}.
 * @param <T>
 */
public interface FlowComponent<T> {
    /**
     * Starts execution of the whole flow chain from the beginning.
     *
     * @param data
     */
    default void begin(T data) {
        first().executeCurrent(data);
    }

    /**
     * Executes current flow component
     *
     * @param data
     */
    void executeCurrent(T data);

    /**
     * Retrieves first flow component in the chain
     *
     * @return
     */
    FlowComponent<T> first();

    /**
     * Sets the link towards the previous flow component in the chain.
     *
     * @param prev
     */
    void setPrev(FlowComponent<T> prev);
}
