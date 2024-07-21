package com.example.common.Repository;

import com.example.common.Events.AbstractEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<AbstractEvent, Long> {
}
