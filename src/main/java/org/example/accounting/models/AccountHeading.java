package org.example.accounting.models;

import org.example.accounting.enums.AccountHeadingType;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "t_accountHeading")
@Where(  clause = "c_enable <> 0")
public class AccountHeading  {



    @Id
    @GeneratedValue
    private long id;


    @Column(name = "c_enable")
    private boolean enable;

    public AccountHeading() {
        this.enable = true;
    }

    @Column(name = "c_type")
    @Enumerated(EnumType.STRING)
    private AccountHeadingType type;

    @Column(name = "c_code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountHeadingType getType() {
        return type;
    }

    public void setType(AccountHeadingType type) {
        this.type = type;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


}
