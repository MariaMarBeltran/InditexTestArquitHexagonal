package mar.inditex.prueba.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer price_list;

    @Column(name = "brand_id")
    private Integer brand_id;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "curr")
    private String curr;

    public Prices() {   }

    public Prices(Integer price_list, Integer brand_id, LocalDateTime start_date, LocalDateTime end_date,
                  Integer product_id, Integer priority, BigDecimal price, String curr) {
        super();
        this.price_list = price_list;
        this.brand_id = brand_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.product_id = product_id;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }


    public Integer getPrice_list() {
        return price_list;
    }

    public void setPrice_list(Integer price_list) {
        this.price_list = price_list;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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


}
