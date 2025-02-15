package com.example.common.Events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.common.domain.Model.ObjectConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@Entity
public abstract class AbstractEvent implements Event{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID aggregateId;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = ObjectConverter.class)
    private Object payload;

    public AbstractEvent(UUID aggregateId, EventType eventType, Object payload) {
        this.aggregateId = aggregateId;
        this.type = eventType;
        this.payload = payload;
    }
}
