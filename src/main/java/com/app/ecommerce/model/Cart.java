package com.app.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_seq"
    )
    @SequenceGenerator(
            name = "cart_seq",
            sequenceName = "cart_seq",
            allocationSize = 1
    )
    private long cartId;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId",
            foreignKey = @ForeignKey(
                    name = "product_cart_fk"
            )
    )
    private Product product;

    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(
                    name = "user_cart_fk"
            )
    )
    private User user;

    @Column(
            name = "quantity",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private long quantity;

    public Cart(Product product, User user, long quantity) {
        this.createdAt = LocalDateTime.now();
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }
}
