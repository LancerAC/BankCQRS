package com.example.common.Repository;

import com.example.common.domain.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByNumberAndDate(String number, String date);

    Optional<Card> findByNumberAndDateAndCvv(String number, String date, String cvv);

    Optional<Card> findByNumberAndDate(
            String number,
            String date
    );

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = """
            INSERT INTO cards_transactions
            VALUES (:cardId, :transactionId)
            """, nativeQuery = true)
    void addTransaction(
            @Param("cardId") String cardId,
            @Param("transactionId") String transactionId
    );
}
