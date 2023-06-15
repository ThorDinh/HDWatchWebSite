/*
 * Created on 2023-06-15 ( 14:26:30 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.hdwatch.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA entity class for "Productimages"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="productimages", schema="dbo", catalog="HDWatch" )
public class Productimages implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="name", nullable=false, length=2147483647)
    private String     name ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="productimages")
    private List<Productimagedetail> listOfProductimagedetail ; 


    /**
     * Constructor
     */
    public Productimages() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setName( String name ) {
        this.name = name ;
    }
    public String getName() {
        return this.name;
    }

    //--- GETTERS FOR LINKS
    public List<Productimagedetail> getListOfProductimagedetail() {
        return this.listOfProductimagedetail;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(name);
        return sb.toString(); 
    } 

}
