package org.example.accounting.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_documentItem")
public class DocumentItem {


    @Id
    @GeneratedValue
    private long id;

    @Column(name = "c_enable")
    private boolean enable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "fk_document")
    private Document document;

    @Column(name = "c_amount")
    private BigDecimal amount;

    public DocumentItem() {
        this.enable = true;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
