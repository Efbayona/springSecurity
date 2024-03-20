package com.cursos.springsecurity.auth.user.entity;

import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "refresh_token", schema = "main")
public class RefreshToken extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "refresh_token_id")
    private UUID refreshTokenId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_name", nullable = false)
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "expired", nullable = false)
    private LocalDateTime expired;

    public RefreshToken() {
    }

    public RefreshToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expired = LocalDateTime.now();
    }

    public static RefreshToken create(User user) {
        return new RefreshToken(user);
    }
}
