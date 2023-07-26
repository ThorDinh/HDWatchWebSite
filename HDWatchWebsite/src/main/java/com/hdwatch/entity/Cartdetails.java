/*
 * Created on 2023-06-16 ( 14:09:18 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Cartdetails"
 *
 * @author Telosys
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cartdetails", schema="dbo", catalog="HDWatch" )
public class Cartdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="cart_id", nullable=false)
    private Integer    cartId ;

    @Column(name="product_id", nullable=false)
    private Integer    productId ;

    @Column(name="quantity", nullable=false)
    private Integer    quantity ;

    @Column(name="price", nullable=false)
    private Double     price ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="cart_id", referencedColumnName="id", insertable=false, updatable=false)
    private Carts      carts ; 

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName="id", insertable=false, updatable=false)
    private Products   products ; 

}
