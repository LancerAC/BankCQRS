package org.example.bankcqrs.Service;

public interface CommandService<T> {

    void create(T object);
}
