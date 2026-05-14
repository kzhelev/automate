package com.linxs.automate.models;

import com.linxs.automate.errors.ApplicationException;

public interface Communicator<T extends AbstractRequest, E extends AbstractCommunicatorResult<?,?>> {

    public E getData(T request) throws ApplicationException;
}
