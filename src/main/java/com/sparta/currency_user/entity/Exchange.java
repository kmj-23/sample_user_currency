package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name="exchange")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amountInKrw;

    @Column(nullable = false)
    private BigDecimal amountAfterExchange;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;




    public Exchange(BigDecimal amountInKrw, BigDecimal amountAfterExchange, String status, Currency currency, User user) {
        this.amountInKrw = amountInKrw;
        this.amountAfterExchange = amountAfterExchange;
        this.status = status;
        this.currency = currency;
        this.user = user;
    }

    public Exchange() {}


}
