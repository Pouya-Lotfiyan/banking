package org.example.accounting.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "t_account")
@Where(  clause = "c_enable <> 0")
public class Account {


    @Id
    @GeneratedValue
    private long id;

    @Column(name = "c_enable")
    private boolean enable;

    @Column(name = "c_accountNumber")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_accountHeading")
    private AccountHeading accountHeading;

    @Column(name = "c_branchCode")
    private String branchCode;

    @Column(name = "c_remainingAmount")
    @Min(value = 0, message = "amount value can not be negative")
    private BigDecimal remainingAmount;

    @Column(name = "c_opentAccountTime")
    @CreationTimestamp
    private Timestamp opentAccountTime;


    public Account(){
        this.enable = true;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountHeading getAccountHeading() {
        return accountHeading;
    }

    public void setAccountHeading(AccountHeading accountHeading) {
        this.accountHeading = accountHeading;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Timestamp getOpentAccountTime() {
        return opentAccountTime;
    }

    public void setOpentAccountTime(Timestamp opentAccountTime) {
        this.opentAccountTime = opentAccountTime;
    }


    @Override
    public boolean equals(Object o) {
        return this.id ==  ( (Account) o).getId();
    }
}
