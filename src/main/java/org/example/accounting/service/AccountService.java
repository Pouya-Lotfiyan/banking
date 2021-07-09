package org.example.accounting.service;


import org.example.accounting.Repository.AccountRepository;
import org.example.accounting.enums.AccountHeadingType;
import org.example.accounting.models.Account;
import org.example.accounting.models.AccountHeading;
import org.example.accounting.models.DocumentItem;
import org.example.base.BaseService;
import org.example.utils.CodeGenerator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService implements BaseService<Account> {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account insertOne(Account account) {
        validatePostOrThrowException(account);
        boolean codeNotValid = true;
        long accountNumber=0;
        List<Account> accounts;
        CodeGenerator codeGenerator = new CodeGenerator();
        while (codeNotValid){
            accountNumber = codeGenerator.generateCode(10);
            accounts = this.accountRepository.findByAccountNumber(accountNumber);
            if (accounts.isEmpty()) {
                codeNotValid = false;
            }
        }
        account.setAccountNumber(String.valueOf(accountNumber));
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> getAll() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account get(long id) {
        Account account = this.accountRepository.findById(id);
        if(account == null){
            throw new NotFoundException("Account with id:["+id+"] dose not exist");
        }
        return account;
    }

    @Override
    public Account remove(long id) {
        Account account = this.accountRepository.findById(id);
        if(account == null) {
            throw new NotFoundException("Account with id:["+id+"] dose not exist");
        }
        return this.accountRepository.deleteOne(account);
    }

    @Override
    public Account update(long id, Account account) {
        Account realAccount = this.accountRepository.findById(id);
        if(realAccount == null){
            throw new NotFoundException("Account with id:["+id+"] dose not exist");
        }

        validateUpdateOrThrowException(id, account);

        if(account.getBranchCode() != null) realAccount.setBranchCode(account.getBranchCode());
        if(account.getAccountHeading() != null) realAccount.setAccountHeading(account.getAccountHeading());
        if(account.getAccountNumber() != null) realAccount.setAccountNumber(account.getAccountNumber());
        return this.accountRepository.updateOne(realAccount);
    }



    private void validatePostOrThrowException(Account account){
        if(account.getRemainingAmount().compareTo(BigDecimal.ZERO) < 0 ) {
            throw new BadRequestException("remaining amount cant be  negative");
        }
        if(account.getAccountNumber().trim().length() != 10){
            throw new BadRequestException("AccountNumber must contain 10 digits");
        }
        if(account.getBranchCode().trim().length() != 4){
            throw new BadRequestException("branchCode  must contain 4 digits");
        }
    }
    private void validateUpdateOrThrowException(long id, Account account){
        if(account.getRemainingAmount() != null){
            throw new BadRequestException("can not update RemainingAmount");
        }
        if(account.getId() != id){
            throw new BadRequestException("id is not match with account you requested");
        }
        validatePostOrThrowException(account);
    }

    private boolean hasRemaining(Account account, BigDecimal amount) {

        AccountHeadingType accountHeadingType = account.getAccountHeading().getType();
        boolean hasRemaining = true;
        if(
                (accountHeadingType.equals(AccountHeadingType.CREDITOR_ACCOUNT)
                        && amount.compareTo(BigDecimal.ZERO) < 0
                )
                        ||
                        (accountHeadingType.equals(AccountHeadingType.DEBTOR_ACCOUNT)
                                && amount.compareTo(BigDecimal.ZERO) > 0
                        )
        ){

           hasRemaining =  account.getRemainingAmount().compareTo(amount) > 0;

        }

        return hasRemaining;

    }

    public Session transferMoney(List<DocumentItem> documentItems) {
            documentItems.stream().forEach(documentItem -> {
                Account account = this.accountRepository.findById(documentItem.getAccount().getId());
                if(account == null) {
                    throw new NotFoundException("Account with id:["+documentItem.getAccount().getId()+"] dose not exist");
                }
                if(!hasRemaining(account, documentItem.getAmount())){
                    throw  new BadRequestException("account with id:["+account.getId()+"] has not Enough remaining ");
                }

                // TODO

            });
    }
}
