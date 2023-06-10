/*
 * Created on 2023-06-10 ( 09:58:21 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

/**
 * JPA entity class for "Favorites"
 *
 * @author Telosys
 *
 */
@Data
@Entity
@Table(name="favorites", schema="dbo", catalog="HDWatch" )
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="accountId", nullable=false)
    private Integer    accountid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="favorites")
    private List<Favoritedetails> listOfFavoritedetails ; 

    @ManyToOne
    @JoinColumn(name="accountId", referencedColumnName="id", insertable=false, updatable=false)
    private Accounts   accounts ; 

}
