package com.company.clinic.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.BaseIntegerIdEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@MetaClass(name = "clinic_Drug")
@NamePattern("%s|name")
public class Drug extends BaseIntegerIdEntity {
    private static final long serialVersionUID = 9146787197421344896L;

    @NotNull
    @MetaProperty(mandatory = true)
    private String name;

    @NotNull
    @MetaProperty(mandatory = true)
    @PositiveOrZero
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}