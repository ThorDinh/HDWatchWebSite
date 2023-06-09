// Generated with g9.

package com.hdwatch.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="carts")
public class Carts implements Serializable {

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
    @OneToMany(mappedBy="carts")
    private Set<Cartdetails> cartdetails;
    @ManyToOne(optional=false)
    @JoinColumn(name="accountId", nullable=false)
    private Accounts accounts;

    /** Default constructor. */
    public Carts() {
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
     * Access method for cartdetails.
     *
     * @return the current value of cartdetails
     */
    public Set<Cartdetails> getCartdetails() {
        return cartdetails;
    }

    /**
     * Setter method for cartdetails.
     *
     * @param aCartdetails the new value for cartdetails
     */
    public void setCartdetails(Set<Cartdetails> aCartdetails) {
        cartdetails = aCartdetails;
    }

    /**
     * Access method for accounts.
     *
     * @return the current value of accounts
     */
    public Accounts getAccounts() {
        return accounts;
    }

    /**
     * Setter method for accounts.
     *
     * @param aAccounts the new value for accounts
     */
    public void setAccounts(Accounts aAccounts) {
        accounts = aAccounts;
    }

    /**
     * Compares the key for this instance with another Carts.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Carts and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Carts)) {
            return false;
        }
        Carts that = (Carts) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Carts.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Carts)) return false;
        return this.equalKeys(other) && ((Carts)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Carts |");
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
