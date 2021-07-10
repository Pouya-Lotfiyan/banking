package org.example.accounting.models;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "t_document")
public class Document {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "c_enable")
    private boolean enable;

    @Column(name = "c_branchCode")
    private String branchCode;

    @Column(name = "c_documentNumber")
    private String documentNumber;

    @Column(name = "c_createdTime")
    @CreationTimestamp
    private Timestamp createdTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "document",
                cascade = CascadeType.ALL
    )
    private List<DocumentItem> documentItems;

    public String getBranchCode() {
        return branchCode;
    }

    public Document() {
        this.enable = true;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public List<DocumentItem> getDocumentItems() {
        return documentItems;
    }

    public void setDocumentItems(List<DocumentItem> documentItems) {
        this.documentItems = documentItems;
    }
}
