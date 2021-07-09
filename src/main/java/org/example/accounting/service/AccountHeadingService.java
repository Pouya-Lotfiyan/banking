package org.example.accounting.service;

import org.example.accounting.Repository.AccountHeadingRepository;
import org.example.accounting.models.AccountHeading;
import org.example.base.BaseService;
import org.example.utils.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.List;


@Service
public class AccountHeadingService implements BaseService<AccountHeading> {

    private final AccountHeadingRepository accountHeadingRepository;

    @Autowired
    public AccountHeadingService(AccountHeadingRepository accountHeadingRepository){
        this.accountHeadingRepository = accountHeadingRepository;
    }


    public List<AccountHeading> getAll() {

        return this.accountHeadingRepository.findAll();

    }


    public AccountHeading insertOne(AccountHeading accountHeading) {
        accountHeading.setEnable(true);
        boolean codeNotValid = true;
        long code=0;
        List<AccountHeading> accountHeadings;
        CodeGenerator codeGenerator = new CodeGenerator();
        while (codeNotValid){
            code = codeGenerator.generateCode(5);
            accountHeadings = this.accountHeadingRepository.findByCode(code);
            if (accountHeadings.isEmpty()) {
                codeNotValid = false;
            }
        }
        accountHeading.setCode(String.valueOf(code));
        return this.accountHeadingRepository.save(accountHeading);
    }

    public AccountHeading get(long id) {
        AccountHeading accountHeading = this.accountHeadingRepository.findById(id);
        if (accountHeading == null){
            throw new NotFoundException("accountHeading with id:["+id+"] not found");
        }
        return accountHeading;
    }


    public AccountHeading update(long id, AccountHeading accountHeading) {

        if(this.accountHeadingRepository.findById(id) == null ){
            throw new NotFoundException("accountHeading with id:["+id+"] not found");
        }
        if(id != accountHeading.getId()) {
            throw new BadRequestException("id not match with accountHeadingId that you requested");

        }
        return this.accountHeadingRepository.updateOne(accountHeading);
    }

    public AccountHeading remove(long id) {
        AccountHeading accountHeading = this.accountHeadingRepository.findById(id);
        if(accountHeading == null){
            throw new NotFoundException("accountHeading with id:["+id+"] not found");
        }
       return this.accountHeadingRepository.deleteOne(accountHeading);

    }
}
