package com.company.jmixforanalyst.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity(name = "jal_StormDiagramDto")
public class StormDiagramDto {
    @JmixGeneratedValue
    @JmixId
    private UUID id_;

    private String id;

    private String teamFolderName;

    private Integer versionNumber;

    @InstanceName
    private String name;

    private String status;

    private String author;

    private String updatedBy;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String type;

    private String description;

    private String tags;

    public String getTeamFolderName() {
        return teamFolderName;
    }

    public void setTeamFolderName(String teamFolderName) {
        this.teamFolderName = teamFolderName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getId_() {
        return id_;
    }

    public void setId_(UUID id_) {
        this.id_ = id_;
    }
}