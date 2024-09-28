package com.myorg.adapter.out.postgres.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", schema = "public")
public class UsersEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "_uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name", nullable = false)
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "status", nullable = false,columnDefinition = "users_status_enum")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private UsersStatusEnum status;

    @Column(name = "created_dt", nullable = false)
    private LocalDateTime createdDt;
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDt;
}
