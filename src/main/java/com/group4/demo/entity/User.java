package com.group4.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
@MappedSuperclass

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            joinColumns =@JoinColumn(name = "user_id",referencedColumnName = "userId"),
//            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
//    )
//    private Set roles;
    private String  role;

}
