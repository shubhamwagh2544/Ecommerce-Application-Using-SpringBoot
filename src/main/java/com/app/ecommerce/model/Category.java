package com.app.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq"
    )
    @SequenceGenerator(
            name = "category_seq",
            sequenceName = "category_seq",
            allocationSize = 1
    )
    private long categoryId;

    @Column(
            name = "category_name",
            nullable = false
    )
    private String categoryName;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @OneToOne(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Product product;

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

//    public void addProduct(Product product) {
//        if (!this.products.contains(product)) this.products.add(product);
//    }
//
//    public void removeProduct(Product product) {
//        if (this.products.contains(product)) this.products.remove(product);
//    }

}
