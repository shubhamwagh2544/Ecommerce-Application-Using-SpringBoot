package com.app.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String productName;

    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "DOUBLE PRECISION"
    )
    private double price;

    @Column(
            name = "is_available",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private boolean isAvailable;

    @Column(
            name = "date_bought",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime dateBought;

    @JsonIgnore
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "category_id_fk",
            referencedColumnName = "categoryId",
            foreignKey = @ForeignKey(
                    name = "category_product_fk"
            )
    )
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
