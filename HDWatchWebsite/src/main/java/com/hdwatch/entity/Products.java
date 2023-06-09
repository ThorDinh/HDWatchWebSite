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

@Entity(name="products")
public class Products implements Serializable {

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
    @Column(nullable=false)
    private String name;
    @Column(nullable=false, precision=53)
    private double price;
    @Column(nullable=false, precision=53)
    private double oldPrice;
    @Column(nullable=false, length=1)
    private boolean available;
    @Column(nullable=false)
    private LocalDate createDate;
    @Column(nullable=false, precision=10)
    private int stock;
    private String description;
    @Column(length=50)
    private String movement;
    @Column(length=50)
    private String watchLable;
    @Column(length=50)
    private String engine;
    @Column(precision=53)
    private double caseSize;
    @Column(precision=53)
    private double caseThickness;
    @Column(length=50)
    private String caseMaterial;
    @Column(length=50)
    private String caseShape;
    @Column(length=50)
    private String caseBack;
    @Column(length=50)
    private String bandMaterial;
    @Column(length=50)
    private String bandType;
    @Column(length=50)
    private String bandColor;
    @Column(precision=53)
    private double bandLength;
    @Column(precision=53)
    private double bandWidth;
    @Column(length=50)
    private String clasp;
    @Column(length=50)
    private String bezelColor;
    @Column(length=50)
    private String bezelMaterial;
    @Column(length=50)
    private String crown;
    @Column(precision=53)
    private double waterResistance;
    @Column(length=50)
    private String functions;
    @OneToMany(mappedBy="products")
    private Set<Cartdetails> cartdetails;
    @OneToMany(mappedBy="products")
    private Set<Favoritedetails> favoritedetails;
    @OneToMany(mappedBy="products")
    private Set<Orderdetails> orderdetails;
    @OneToMany(mappedBy="products")
    private Set<Productimages> productimages;
    @ManyToOne(optional=false)
    @JoinColumn(name="brandId", nullable=false)
    private Brands brands;
    @ManyToOne(optional=false)
    @JoinColumn(name="categoryId", nullable=false)
    private Categories categories;

    /** Default constructor. */
    public Products() {
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
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for price.
     *
     * @return the current value of price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method for price.
     *
     * @param aPrice the new value for price
     */
    public void setPrice(double aPrice) {
        price = aPrice;
    }

    /**
     * Access method for oldPrice.
     *
     * @return the current value of oldPrice
     */
    public double getOldPrice() {
        return oldPrice;
    }

    /**
     * Setter method for oldPrice.
     *
     * @param aOldPrice the new value for oldPrice
     */
    public void setOldPrice(double aOldPrice) {
        oldPrice = aOldPrice;
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
     * Access method for createDate.
     *
     * @return the current value of createDate
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * Setter method for createDate.
     *
     * @param aCreateDate the new value for createDate
     */
    public void setCreateDate(LocalDate aCreateDate) {
        createDate = aCreateDate;
    }

    /**
     * Access method for stock.
     *
     * @return the current value of stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter method for stock.
     *
     * @param aStock the new value for stock
     */
    public void setStock(int aStock) {
        stock = aStock;
    }

    /**
     * Access method for description.
     *
     * @return the current value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for description.
     *
     * @param aDescription the new value for description
     */
    public void setDescription(String aDescription) {
        description = aDescription;
    }

    /**
     * Access method for movement.
     *
     * @return the current value of movement
     */
    public String getMovement() {
        return movement;
    }

    /**
     * Setter method for movement.
     *
     * @param aMovement the new value for movement
     */
    public void setMovement(String aMovement) {
        movement = aMovement;
    }

    /**
     * Access method for watchLable.
     *
     * @return the current value of watchLable
     */
    public String getWatchLable() {
        return watchLable;
    }

    /**
     * Setter method for watchLable.
     *
     * @param aWatchLable the new value for watchLable
     */
    public void setWatchLable(String aWatchLable) {
        watchLable = aWatchLable;
    }

    /**
     * Access method for engine.
     *
     * @return the current value of engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Setter method for engine.
     *
     * @param aEngine the new value for engine
     */
    public void setEngine(String aEngine) {
        engine = aEngine;
    }

    /**
     * Access method for caseSize.
     *
     * @return the current value of caseSize
     */
    public double getCaseSize() {
        return caseSize;
    }

    /**
     * Setter method for caseSize.
     *
     * @param aCaseSize the new value for caseSize
     */
    public void setCaseSize(double aCaseSize) {
        caseSize = aCaseSize;
    }

    /**
     * Access method for caseThickness.
     *
     * @return the current value of caseThickness
     */
    public double getCaseThickness() {
        return caseThickness;
    }

    /**
     * Setter method for caseThickness.
     *
     * @param aCaseThickness the new value for caseThickness
     */
    public void setCaseThickness(double aCaseThickness) {
        caseThickness = aCaseThickness;
    }

    /**
     * Access method for caseMaterial.
     *
     * @return the current value of caseMaterial
     */
    public String getCaseMaterial() {
        return caseMaterial;
    }

    /**
     * Setter method for caseMaterial.
     *
     * @param aCaseMaterial the new value for caseMaterial
     */
    public void setCaseMaterial(String aCaseMaterial) {
        caseMaterial = aCaseMaterial;
    }

    /**
     * Access method for caseShape.
     *
     * @return the current value of caseShape
     */
    public String getCaseShape() {
        return caseShape;
    }

    /**
     * Setter method for caseShape.
     *
     * @param aCaseShape the new value for caseShape
     */
    public void setCaseShape(String aCaseShape) {
        caseShape = aCaseShape;
    }

    /**
     * Access method for caseBack.
     *
     * @return the current value of caseBack
     */
    public String getCaseBack() {
        return caseBack;
    }

    /**
     * Setter method for caseBack.
     *
     * @param aCaseBack the new value for caseBack
     */
    public void setCaseBack(String aCaseBack) {
        caseBack = aCaseBack;
    }

    /**
     * Access method for bandMaterial.
     *
     * @return the current value of bandMaterial
     */
    public String getBandMaterial() {
        return bandMaterial;
    }

    /**
     * Setter method for bandMaterial.
     *
     * @param aBandMaterial the new value for bandMaterial
     */
    public void setBandMaterial(String aBandMaterial) {
        bandMaterial = aBandMaterial;
    }

    /**
     * Access method for bandType.
     *
     * @return the current value of bandType
     */
    public String getBandType() {
        return bandType;
    }

    /**
     * Setter method for bandType.
     *
     * @param aBandType the new value for bandType
     */
    public void setBandType(String aBandType) {
        bandType = aBandType;
    }

    /**
     * Access method for bandColor.
     *
     * @return the current value of bandColor
     */
    public String getBandColor() {
        return bandColor;
    }

    /**
     * Setter method for bandColor.
     *
     * @param aBandColor the new value for bandColor
     */
    public void setBandColor(String aBandColor) {
        bandColor = aBandColor;
    }

    /**
     * Access method for bandLength.
     *
     * @return the current value of bandLength
     */
    public double getBandLength() {
        return bandLength;
    }

    /**
     * Setter method for bandLength.
     *
     * @param aBandLength the new value for bandLength
     */
    public void setBandLength(double aBandLength) {
        bandLength = aBandLength;
    }

    /**
     * Access method for bandWidth.
     *
     * @return the current value of bandWidth
     */
    public double getBandWidth() {
        return bandWidth;
    }

    /**
     * Setter method for bandWidth.
     *
     * @param aBandWidth the new value for bandWidth
     */
    public void setBandWidth(double aBandWidth) {
        bandWidth = aBandWidth;
    }

    /**
     * Access method for clasp.
     *
     * @return the current value of clasp
     */
    public String getClasp() {
        return clasp;
    }

    /**
     * Setter method for clasp.
     *
     * @param aClasp the new value for clasp
     */
    public void setClasp(String aClasp) {
        clasp = aClasp;
    }

    /**
     * Access method for bezelColor.
     *
     * @return the current value of bezelColor
     */
    public String getBezelColor() {
        return bezelColor;
    }

    /**
     * Setter method for bezelColor.
     *
     * @param aBezelColor the new value for bezelColor
     */
    public void setBezelColor(String aBezelColor) {
        bezelColor = aBezelColor;
    }

    /**
     * Access method for bezelMaterial.
     *
     * @return the current value of bezelMaterial
     */
    public String getBezelMaterial() {
        return bezelMaterial;
    }

    /**
     * Setter method for bezelMaterial.
     *
     * @param aBezelMaterial the new value for bezelMaterial
     */
    public void setBezelMaterial(String aBezelMaterial) {
        bezelMaterial = aBezelMaterial;
    }

    /**
     * Access method for crown.
     *
     * @return the current value of crown
     */
    public String getCrown() {
        return crown;
    }

    /**
     * Setter method for crown.
     *
     * @param aCrown the new value for crown
     */
    public void setCrown(String aCrown) {
        crown = aCrown;
    }

    /**
     * Access method for waterResistance.
     *
     * @return the current value of waterResistance
     */
    public double getWaterResistance() {
        return waterResistance;
    }

    /**
     * Setter method for waterResistance.
     *
     * @param aWaterResistance the new value for waterResistance
     */
    public void setWaterResistance(double aWaterResistance) {
        waterResistance = aWaterResistance;
    }

    /**
     * Access method for functions.
     *
     * @return the current value of functions
     */
    public String getFunctions() {
        return functions;
    }

    /**
     * Setter method for functions.
     *
     * @param aFunctions the new value for functions
     */
    public void setFunctions(String aFunctions) {
        functions = aFunctions;
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
     * Access method for favoritedetails.
     *
     * @return the current value of favoritedetails
     */
    public Set<Favoritedetails> getFavoritedetails() {
        return favoritedetails;
    }

    /**
     * Setter method for favoritedetails.
     *
     * @param aFavoritedetails the new value for favoritedetails
     */
    public void setFavoritedetails(Set<Favoritedetails> aFavoritedetails) {
        favoritedetails = aFavoritedetails;
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
     * Access method for productimages.
     *
     * @return the current value of productimages
     */
    public Set<Productimages> getProductimages() {
        return productimages;
    }

    /**
     * Setter method for productimages.
     *
     * @param aProductimages the new value for productimages
     */
    public void setProductimages(Set<Productimages> aProductimages) {
        productimages = aProductimages;
    }

    /**
     * Access method for brands.
     *
     * @return the current value of brands
     */
    public Brands getBrands() {
        return brands;
    }

    /**
     * Setter method for brands.
     *
     * @param aBrands the new value for brands
     */
    public void setBrands(Brands aBrands) {
        brands = aBrands;
    }

    /**
     * Access method for categories.
     *
     * @return the current value of categories
     */
    public Categories getCategories() {
        return categories;
    }

    /**
     * Setter method for categories.
     *
     * @param aCategories the new value for categories
     */
    public void setCategories(Categories aCategories) {
        categories = aCategories;
    }

    /**
     * Compares the key for this instance with another Products.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Products and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Products)) {
            return false;
        }
        Products that = (Products) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Products.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Products)) return false;
        return this.equalKeys(other) && ((Products)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Products |");
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
