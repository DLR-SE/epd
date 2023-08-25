package de.emir.tuml.ucore.runtime;

public interface IDisposable {
    /**
     * Dispose the resource, the operation should be idempotent.
     */
    void dispose();

    /**
     * Returns true if this resource has been disposed.
     * 
     * @return true if this resource has been disposed
     */
    boolean isDisposed();

}
