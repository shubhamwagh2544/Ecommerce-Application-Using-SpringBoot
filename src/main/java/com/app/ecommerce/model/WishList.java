package com.app.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wishlist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wishlist_seq"
    )
    @SequenceGenerator(
            name = "wishlist_seq",
            sequenceName = "wishlist_seq",
            allocationSize = 1
    )
    private long wishlist_id;

    @OneToOne(
            targetEntity = User.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private User user;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    private Product product;

    public WishList(User user, Product product) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.product = product;
    }
}
