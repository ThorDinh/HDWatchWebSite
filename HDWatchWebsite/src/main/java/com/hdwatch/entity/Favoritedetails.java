// Generated with g9.

package com.hdwatch.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity(name="favoritedetails")
public class Favoritedetails implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name="LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(length=1)
    private boolean available;
    @ManyToOne(optional=false)
    @JoinColumn(name="favoriteId", nullable=false)
    private Favorites favorites;
    @ManyToOne(optional=false)
    @JoinColumn(name="productId", nullable=false)
    private Products products;

    /** Default constructor. */
    public Favoritedetails() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for available.
     *
     * @return true if and only if available is currently true
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Setter method for available.
     *
     * @param aAvailable the new value for available
     */
    public void setAvailable(boolean aAvailable) {
        available = aAvailable;
    }

    /**
     * Access method for favorites.
     *
     * @return the current value of favorites
     */
    public Favorites getFavorites() {
        return favorites;
    }

    /**
     * Setter method for favorites.
     *
     * @param aFavorites the new value for favorites
     */
    public void setFavorites(Favorites aFavorites) {
        favorites = aFavorites;
    }

    /**
     * Access method for products.
     *
     * @return the current value of products
     */
    public Products getProducts() {
        return products;
    }

    /**
     * Setter method for products.
     *
     * @param aProducts the new value for products
     */
    public void setProducts(Products aProducts) {
        products = aProducts;
    }

    /**
     * Compares the key for this instance with another Favoritedetails.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Favoritedetails and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Favoritedetails)) {
            return false;
        }
        Favoritedetails that = (Favoritedetails) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Favoritedetails.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Favoritedetails)) return false;
        return this.equalKeys(other) && ((Favoritedetails)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = getId();
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[Favoritedetails |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
