package com.example.MiniProjectGroup5.model;

import com.example.MiniProjectGroup5.enums.CommunityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseAuditClass{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String level;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private CommunityType community;

    public Employee(String name, String level, String email, CommunityType community)
    {
        this.name = name;
        this.level = level;
        this.email = email;
        this.community = community;
    }

}
