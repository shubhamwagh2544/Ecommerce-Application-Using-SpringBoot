package com.app.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationToken {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_seq"
    )
    @SequenceGenerator(
            name = "token_seq",
            sequenceName = "token_seq",
            allocationSize = 1
    )
    private long tokenId;

    @Column(
            name = "token",
            nullable = false,
            columnDefinition = "TEXT",
            updatable = false
    )
    private String token;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @OneToOne(
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(
                    name = "user_token_fk"
            )
    )
    private User user;

    public AuthenticationToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }
}
