package com.app.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkout")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Checkout {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "checkout_seq"
    )
    @SequenceGenerator(
            name = "checkout_seq",
            sequenceName = "checkout_seq",
            allocationSize = 1
    )
    private long id;

    @OneToOne(
            targetEntity = Cart.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "cart_id",
            referencedColumnName = "cartId",
            foreignKey = @ForeignKey(
                    name = "cart_checkout_fk"
            )
    )
    private Cart cart;

    @Column(
            name = "checkout_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime checkoutAt;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(
                    name = "user_checkout_fk"
            )
    )
    private User user;

    public Checkout(Cart cart, User user) {
        this.cart = cart;
        this.checkoutAt = LocalDateTime.now();
        this.user = user;
    }
}
