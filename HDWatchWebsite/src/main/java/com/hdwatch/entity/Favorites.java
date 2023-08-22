/*
 * Created on 2023-06-16 ( 14:09:18 )
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
 * JPA entity class for "Favorites"
 *
 * @author Telosys
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorites", schema = "dbo", catalog = "HDWatch")
public class Favorites implements Serializable {

	private static final long serialVersionUID = 1L;

	// --- ENTITY PRIMARY KEY
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	// --- ENTITY DATA FIELDS
	@Column(name = "account_id", nullable = false, length = 50)
	private String accountId;

	// --- ENTITY LINKS ( RELATIONSHIP )
	@JsonIgnore
	@OneToMany(mappedBy = "favorites")
	private List<Favoritedetails> listOfFavoritedetails;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "username", insertable = false, updatable = false)
	private Accounts accounts;

}
