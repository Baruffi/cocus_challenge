package com.cocus.challenge.bahamas.interfaces;

public interface IStorageService<T> {

    public T get(Long id);

    public void save(T target);
}
