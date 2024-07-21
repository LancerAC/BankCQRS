package com.example.common.Events;
import com.example.common.domain.aggregate.Aggregate;

public interface Event {

    void apply(Aggregate aggregate);
}
