package com.company.jmixforanalyst.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@JmixEntity
@Table(name = "JAL_LEAD", indexes = {
        @Index(name = "IDX_JAL_LEAD_PRODUCT", columnList = "PRODUCT_ID")
})
@Entity(name = "jal_Lead")
public class Lead {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "DATE_")
    private LocalDateTime date;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "REQUEST")
    @Lob
    private String request;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "SIZE_")
    private Integer size;

    @Column(name = "BUDGET")
    private Long budget;

    @Column(name = "STATUS")
    private Integer status;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status == null ? null : Status.fromId(status);
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public CompanySize getSize() {
        return size == null ? null : CompanySize.fromId(size);
    }

    public void setSize(CompanySize size) {
        this.size = size == null ? null : size.getId();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"id", "product", "date"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s %s",
                datatypeFormatter.formatInteger(id),
                metadataTools.format(product),
                datatypeFormatter.formatLocalDateTime(date));
    }
}