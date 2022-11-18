package com.example.MiniProjectGroup5.model;

import com.example.MiniProjectGroup5.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String username;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserType type;


    public User(String username, String password, List<SimpleGrantedAuthority> authority) {

    }
}
