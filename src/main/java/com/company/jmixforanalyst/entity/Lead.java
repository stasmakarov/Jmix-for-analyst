package com.company.jmixforanalyst.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;

@JmixEntity
@Table(name = "JAL_LEAD")
@Entity(name = "jal_Lead")
public class Lead {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private Integer id;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REQUEST")
    @Lob
    private String request;

    @Column(name = "SIZE_")
    private String size;

    @Column(name = "BUDGET")
    private Long budget;

    @Column(name = "PERFORMER")
    private String performer;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
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
    @DependsOnProperties({"id", "customer", "performer"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s %s",
                datatypeFormatter.formatInteger(id),
                metadataTools.format(customer),
                metadataTools.format(performer));
    }
}