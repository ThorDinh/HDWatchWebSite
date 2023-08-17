/*
 * Created on 2023-06-16 ( 14:09:18 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Products"
 *
 * @author Telosys
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products", schema="dbo", catalog="HDWatch" )
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="name", nullable=false, length=2147483647)
    private String     name ;

    @Column(name="price", nullable=false)
    private Double     price ;

    @Column(name="old_price", nullable=false)
    private Double     oldprice ;

    @Column(name="available", nullable=false)
    private Boolean    available ;

    @Temporal(TemporalType.DATE)
    @Column(name="createDate", nullable=false)
    private Date       createdate ;

    @Column(name="brandId", nullable=false)
    private Integer    brandid ;

    @Column(name="categoryId", nullable=false)
    private Integer    categoryid ;

    @Column(name="stock", nullable=false)
    private Integer    stock ;

    @Column(name="description", length=2147483647)
    private String     description ;
    
    @Column(name="productImages", length=2147483647)
    private String     productimages ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="brandId", referencedColumnName="id", insertable=false, updatable=false)
    private Brands     brands ; 
    
    @JsonIgnore
    @OneToMany(mappedBy="products")
    private List<Cartdetails> listOfCartdetails ; 

    @ManyToOne
    @JoinColumn(name="categoryId", referencedColumnName="id", insertable=false, updatable=false)
    private Categories categories ; 
    
    @JsonIgnore
    @OneToMany(mappedBy="products")
    private List<Favoritedetails> listOfFavoritedetails ; 
    
    @JsonIgnore
    @OneToMany(mappedBy="products")
    private List<Orderdetails> listOfOrderdetails ; 
    
    public void deleteImage(String imageToDelete) {
        if (productimages != null) {
            List<String> imagesList = Arrays.asList(productimages.split(","));
            if (imagesList.contains(imageToDelete)) {
                imagesList.remove(imageToDelete);
                productimages = String.join(",", imagesList);
            }
        }
    }
}
