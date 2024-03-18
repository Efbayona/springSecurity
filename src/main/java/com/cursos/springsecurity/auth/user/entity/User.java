package com.cursos.springsecurity.auth.user.entity;

import com.cursos.springsecurity.auth.role.entity.Role;
import com.cursos.springsecurity.auth.user.status.UserStatus;
import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "main")
public class User extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false, length = 38, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @Column(name = "administrator")
    private boolean administrator;

//    @Column(name = "last_password_updated_at")
//    private LocalDateTime lastPasswordUpdatedAt;
//
//    @Column(name = "code_verification", length = 6)
//    private String codeVerification;
//
//    @Column(name = "code_verification_created_at")
//    private LocalDateTime codeVerificationCreatedAt;
//
//    @Column(name = "last_login")
//    private LocalDate lastLogin;
//
//    @Column(name = "login_attempts")
//    private Integer loginAttempts;
//
//    @Column(name = "login_attempts_mfa")
//    private Integer loginAttemptsMfa;
//
//    @Column(name = "mfa_is_email")
//    private boolean mfaIsEmail;
//
//    @Column(name = "secret_key")
//    private String secretKey;
//
//    @Column(name = "lock_date")
//    private LocalDateTime lockDate;
//
//    @Column(name = "qr_authenticator", columnDefinition = "TEXT")
//    private String qrAuthenticator;
//
//    @Column(name = "profile_image", columnDefinition = "TEXT")
//    private String profileImage;
//
//    @Column(name = "logged_with")
//    private String loggedWith;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role", schema = "main",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role_id"}, name = "uc_user_role"))
    private List<Role> roles;

    public User(String name, String password, boolean administrator) {
        this.name = name;
        this.status = UserStatus.ACTIVE;
        this.password = password;
//        this.loginAttempts = 0;
//        this.loginAttemptsMfa = 0;
//        this.mfaIsEmail = true;
//        this.secretKey = secretKey;
//        this.qrAuthenticator = qrAuthenticator;
        this.administrator = administrator;
//        this.profileImage = profileImage;
    }

    public User() {

    }

    public static User create(String name, String password, boolean administrator) {
        return new User(
                name,
                password,
                administrator
        );
    }

}
