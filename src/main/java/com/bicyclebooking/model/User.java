package com.bicyclebooking.model;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "nvarchar(50) not null")
    private String fullName;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 10)
    private String phone;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(columnDefinition = "int check(balance >= 0) default(0)")
    private int balance;
    @Column(columnDefinition = "int check(point >= 0) default(0)")
    private int point;
}
