/*
 * Created on 2023-06-16 ( 14:09:19 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "SaleEvents"
 *
 * @author Telosys
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sale_events", schema="dbo", catalog="HDWatch" )
public class SaleEvents implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="name", nullable=false, length=255)
    private String     name ;

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", nullable=false)
    private Date       startDate ;

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", nullable=false)
    private Date       endDate ;

    @Column(name="price_sale", nullable=false)
    private Integer    priceSale ;


    //--- ENTITY LINKS ( RELATIONSHIP )

}
