package com.app.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq"
    )
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    private long productId;

    @Column(
            name = "product_name",
            nullable = false
    )
    private String productName;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "price",
            nullable = false
    )
    private double price;

    @Column(
            name = "is_available",
            nullable = false
    )
    private boolean isAvailable;

    @Column(
            name = "date_bought",
            nullable = false
    )
    private LocalDateTime dateBought;

    @OneToOne
//    @JoinColumn(
//            name = "category_id",
//            referencedColumnName = "categoryId",
//            nullable = false,
//            foreignKey = @ForeignKey(
//                    name = "category_product_foreign_key"
//            )
//    )
    private Category category;

    public Product(String productName,
                   String description,
                   double price,
                   boolean isAvailable) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.dateBought = LocalDateTime.now();
    }
}
