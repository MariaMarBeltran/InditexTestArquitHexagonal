package mar.inditex.prueba.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_list")
    private Long priceList;

    @Column(name = "brand_id")
    private Integer brandId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "curr")
    private String curr;

    @Column(name = "imagenurl")
    private String imagenurl;

    @Column(name = "urltraje")
    private String urltraje;

    @Column(name = "descripcionproducto")
    private String descripcionproducto;

    @Column(name = "producto")
    private String producto;

    public Prices() {
    }

    public Prices(Long priceList, Integer brandId, Date startDate, Date endDate, Integer productId,
                  Integer priority, BigDecimal price, String curr, String imagenurl, String urltraje,
                  String descripcionproducto, String producto) {
        this.priceList = priceList;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
        this.imagenurl = imagenurl;
        this.urltraje = urltraje;
        this.descripcionproducto = descripcionproducto;
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "priceList=" + priceList +
                ", brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", productId=" + productId +
                ", priority=" + priority +
                ", price=" + price +
                ", curr='" + curr + '\'' +
                ", imagenUrl='" + imagenurl + '\'' +
                '}';
    }

    public Prices(Long priceList) {
        this.priceList = priceList;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getImagenurl() {
        return imagenurl;
    }

    public void setImagenurl(String imagenurl) {
        this.imagenurl = imagenurl;
    }

    public String getUrltraje() {
        return urltraje;
    }

    public void setUrltraje(String urltraje) {
        this.urltraje = urltraje;
    }

    public String getDescripcionproducto() {
        return descripcionproducto;
    }

    public void setDescripcionproducto(String descripcionproducto) {
        this.descripcionproducto = descripcionproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
