/*
 * Created on 2023-06-16 ( 14:09:19 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Roles"
 *
 * @author Telosys
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles", schema="dbo", catalog="HDWatch" )
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="id", nullable=false, length= 3)
    private String    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="role", nullable=false, length=50)
    private String     role ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="roles")
    private List<Roledetails> listOfRoledetails ; 

}
