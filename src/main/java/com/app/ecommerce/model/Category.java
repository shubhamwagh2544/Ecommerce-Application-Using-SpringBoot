package com.app.ecommerce.model;

import lombok.*;

import javax.persistence.*;

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

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

}
