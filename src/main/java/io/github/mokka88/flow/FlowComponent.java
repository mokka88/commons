package io.github.mokka88.flow;

/**
 * It's meant for representing business workflows in an elegant manner.
 * The next/fork methods are defined in the subinterfaces: {@link LinearFlowComponent} and {@link ForkFlowComponent}.
 * @param <T>
 */
public interface FlowComponent<T> {
    /**
     * Starts execution of the whole flow chain from the beginning.
     *
     * @param context
     */
    default void begin(T context) {
        first().executeCurrent(context);
    }

    /**
     * Executes current flow component
     *
     * @param context
     */
    void executeCurrent(T context);

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
