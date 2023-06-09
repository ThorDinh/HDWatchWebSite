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
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity(name="accounts")
public class Accounts implements Serializable {

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
    @Column(nullable=false, length=50)
    private String username;
    @Column(nullable=false, length=1)
    private boolean activated;
    @Column(nullable=false, length=50)
    private String email;
    @Column(nullable=false, length=50)
    private String fullname;
    @Column(nullable=false, length=50)
    private String password;
    @Column(nullable=false, length=50)
    private String photo;
    @OneToMany(mappedBy="accounts")
    private Set<Carts> carts;
    @OneToMany(mappedBy="accounts")
    private Set<Favorites> favorites;
    @OneToMany(mappedBy="accounts")
    private Set<Orders> orders;
    @OneToMany(mappedBy="accounts")
    private Set<Roledetails> roledetails;

    /** Default constructor. */
    public Accounts() {
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
     * Access method for username.
     *
     * @return the current value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for username.
     *
     * @param aUsername the new value for username
     */
    public void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * Access method for activated.
     *
     * @return true if and only if activated is currently true
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Setter method for activated.
     *
     * @param aActivated the new value for activated
     */
    public void setActivated(boolean aActivated) {
        activated = aActivated;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for fullname.
     *
     * @return the current value of fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * Setter method for fullname.
     *
     * @param aFullname the new value for fullname
     */
    public void setFullname(String aFullname) {
        fullname = aFullname;
    }

    /**
     * Access method for password.
     *
     * @return the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for password.
     *
     * @param aPassword the new value for password
     */
    public void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * Access method for photo.
     *
     * @return the current value of photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Setter method for photo.
     *
     * @param aPhoto the new value for photo
     */
    public void setPhoto(String aPhoto) {
        photo = aPhoto;
    }

    /**
     * Access method for carts.
     *
     * @return the current value of carts
     */
    public Set<Carts> getCarts() {
        return carts;
    }

    /**
     * Setter method for carts.
     *
     * @param aCarts the new value for carts
     */
    public void setCarts(Set<Carts> aCarts) {
        carts = aCarts;
    }

    /**
     * Access method for favorites.
     *
     * @return the current value of favorites
     */
    public Set<Favorites> getFavorites() {
        return favorites;
    }

    /**
     * Setter method for favorites.
     *
     * @param aFavorites the new value for favorites
     */
    public void setFavorites(Set<Favorites> aFavorites) {
        favorites = aFavorites;
    }

    /**
     * Access method for orders.
     *
     * @return the current value of orders
     */
    public Set<Orders> getOrders() {
        return orders;
    }

    /**
     * Setter method for orders.
     *
     * @param aOrders the new value for orders
     */
    public void setOrders(Set<Orders> aOrders) {
        orders = aOrders;
    }

    /**
     * Access method for roledetails.
     *
     * @return the current value of roledetails
     */
    public Set<Roledetails> getRoledetails() {
        return roledetails;
    }

    /**
     * Setter method for roledetails.
     *
     * @param aRoledetails the new value for roledetails
     */
    public void setRoledetails(Set<Roledetails> aRoledetails) {
        roledetails = aRoledetails;
    }

    /**
     * Compares the key for this instance with another Accounts.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Accounts and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Accounts)) {
            return false;
        }
        Accounts that = (Accounts) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Accounts.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Accounts)) return false;
        return this.equalKeys(other) && ((Accounts)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Accounts |");
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
