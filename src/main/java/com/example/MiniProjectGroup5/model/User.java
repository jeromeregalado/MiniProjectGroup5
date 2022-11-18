package com.example.MiniProjectGroup5.model;


import com.example.MiniProjectGroup5.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseAuditClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    private String password;

    @Column
    private String username;

    @Enumerated(value = EnumType.STRING)
    private UserType type;


}
