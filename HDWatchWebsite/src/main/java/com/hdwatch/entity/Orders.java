// Generated with g9.

package com.hdwatch.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

@Entity(name="orders")
public class Orders implements Serializable {

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
    private String address;
    @Column(nullable=false)
    private LocalDate craeteDate;
    @Column(nullable=false, length=50)
    private String status;
    @OneToMany(mappedBy="orders")
    private Set<Orderdetails> orderdetails;
    @ManyToOne(optional=false)
    @JoinColumn(name="accountId", nullable=false)
    private Accounts accounts;

    /** Default constructor. */
    public Orders() {
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
     * Access method for address.
     *
     * @return the current value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter method for address.
     *
     * @param aAddress the new value for address
     */
    public void setAddress(String aAddress) {
        address = aAddress;
    }

    /**
     * Access method for craeteDate.
     *
     * @return the current value of craeteDate
     */
    public LocalDate getCraeteDate() {
        return craeteDate;
    }

    /**
     * Setter method for craeteDate.
     *
     * @param aCraeteDate the new value for craeteDate
     */
    public void setCraeteDate(LocalDate aCraeteDate) {
        craeteDate = aCraeteDate;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for orderdetails.
     *
     * @return the current value of orderdetails
     */
    public Set<Orderdetails> getOrderdetails() {
        return orderdetails;
    }

    /**
     * Setter method for orderdetails.
     *
     * @param aOrderdetails the new value for orderdetails
     */
    public void setOrderdetails(Set<Orderdetails> aOrderdetails) {
        orderdetails = aOrderdetails;
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
     * Compares the key for this instance with another Orders.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Orders and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Orders)) {
            return false;
        }
        Orders that = (Orders) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Orders.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Orders)) return false;
        return this.equalKeys(other) && ((Orders)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Orders |");
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
