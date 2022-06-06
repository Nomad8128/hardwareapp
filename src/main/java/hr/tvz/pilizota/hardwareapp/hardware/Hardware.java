package hr.tvz.pilizota.hardwareapp.hardware;

import hr.tvz.pilizota.hardwareapp.review.Review;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private BigDecimal price;
    private Type type;
    private Integer amount;

    @ManyToMany(targetEntity = Review.class)
    @JoinTable(
            name = "hardware_review",
            joinColumns = { @JoinColumn(name = "hardware_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    private List<Review> reviews;

    public Hardware() {
    }
    public Hardware(String name, String code, BigDecimal price, Type type, Integer amount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }
    public static enum Type {
        CPU, GPU, MBO, RAM, STORAGE, OTHER
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hardware)) return false;
        Hardware hardware = (Hardware) o;
        return code.equals(hardware.code);
    }

    @Override
    public String toString() {
        return name + " - " + amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
