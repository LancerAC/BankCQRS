package com.example.common.domain.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "cards")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbstractModel {

    private String number;

    private String date;

    private String cvv;

    @OneToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id")
    )
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    public Card(Account account) {
        this.account = account;
    }
}
