/*
 * Created on 2023-06-16 ( 14:09:18 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Accounts"
 *
 * @author Telosys
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="accounts", schema="dbo", catalog="HDWatch" )
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @NotBlank(message = "Vui lòng nhập tài khoản")
    @Id
    @Column(name="username", nullable=false, length=50)
    private String     username ;

    //--- ENTITY DATA FIELDS 
    @Column(name="activated", nullable=false)
    private Boolean    activated ;
    
    @NotBlank(message = "Vui lòng nhập Email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email không hợp lệ")
    @Column(name="email", nullable=false, length=50)
    private String     email ;
    
    @NotBlank(message = "Vui lòng nhập họ và tên")
    @Column(name="fullname", nullable=false, length=50)
    private String     fullname ;
    
    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Column(name="password", nullable=false)
    private String     password ;

    @Column(name="google", length=2147483647)
    private String     google ;

    @Column(name="facebook", length=2147483647)
    private String     facebook ;
    
    // Xác nhận mật khẩu
    @Transient
    public boolean isPasswordConfirmed(String confirmPassword) {
        return password != null && password.equals(confirmPassword);
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="accounts")
    private List<Favorites> listOfFavorites ; 
    
    @JsonIgnore
    @OneToMany(mappedBy="accounts")
    private List<Orders> listOfOrders ; 
    
    @JsonIgnore
    @OneToMany(mappedBy="accounts", fetch = FetchType.EAGER)
    private List<Roledetails> listOfRoledetails ; 

    @JsonIgnore
    @OneToMany(mappedBy="accounts")
    private List<Carts> listOfCarts ; 

}
