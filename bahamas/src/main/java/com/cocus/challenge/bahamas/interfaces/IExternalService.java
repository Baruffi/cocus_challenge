package com.cocus.challenge.bahamas.interfaces;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;

public interface IExternalService<T> {

    public ExternalServiceCallResolution call(T argument);
}
