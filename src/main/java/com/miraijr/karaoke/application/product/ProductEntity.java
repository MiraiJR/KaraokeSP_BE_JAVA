package com.miraijr.karaoke.application.product;

import com.miraijr.karaoke.application.group_product.GroupProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String code;

    @OneToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}
    )
    @JoinColumn(name = "group_product_id")
    private GroupProductEntity groupProduct;

    @Column(
            nullable = false
    )
    private Float price;

    public ProductEntity() {
    }

    public ProductEntity(String name, String code, Float price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public GroupProductEntity getGroupProduct() {
        return groupProduct;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", groupProduct=" + groupProduct +
                ", price=" + price +
                '}';
    }
}
