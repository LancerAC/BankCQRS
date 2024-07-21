package com.example.common.Service;

import java.util.UUID;

public interface QueryService<T> {

    T getById(UUID id);
}
