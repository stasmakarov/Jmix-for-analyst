package com.company.jmixforanalyst.entity;

import io.jmix.bpm.entity.ContentStorage;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "JAL_STORM_DATA", indexes = {
        @Index(name = "IDX_JAL_STORM_DATA_PARENT", columnList = "PARENT_ID")
})
@Entity(name = "jal_StormData")
public class StormData {
    @JmixGeneratedValue
    @Column(name = "ID_", nullable = false)
    @Id
    private UUID id_;

    @Column(name = "ID")
    private String id;

    @Column(name = "TEAM_FOLDER_NAME")
    private String teamFolderName;

    @Column(name = "IS_PUBLIC")
    private Boolean isPublic;

    @Column(name = "VERSION_NUMBER")
    private Integer versionNumber;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @InstanceName
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "TYPE_")
    private String type;

    @JoinColumn(name = "PARENT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private ContentStorage parent;

    public ContentStorage getParent() {
        return parent;
    }

    public void setParent(ContentStorage parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getTeamFolderName() {
        return teamFolderName;
    }

    public void setTeamFolderName(String teamFolderName) {
        this.teamFolderName = teamFolderName;
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