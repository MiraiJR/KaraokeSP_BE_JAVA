package com.miraijr.karaoke.application.product.types;

import com.miraijr.karaoke.application.product.DTOs.ProductDTO;

public class ProductDetail {
    private Integer id;
    private String name;
    private String code;
    private Long price;
    private String group;

    public ProductDetail() {
    }

    public ProductDetail(Integer id, String name, String code, Long price, String group) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
