package de.emir.tuml.ucore.runtime.access;

import de.emir.tuml.ucore.runtime.UObject;

public interface IOperationInvoker {

    /**
     * Invokes the specified operation of the object. If the operation has parameters, then corresponding arguments must
     * be supplied. There are no optional parameters in operations. If the operation is a void operation, then on
     * successful execution, the result of this invocation is null. Otherwise, if the operation is multi-valued, then an
     * List is returned (possibly empty). If single-valued, then an instance of the operation's type is returned, or
     * possibly null. If the invoked operation fails with an exception, then it is NOT catched.
     * 
     * @param instance
     * @param parameter
     * @return
     */
    public Object invoke(UObject instance, final Object... parameter);
}
