package org.example.accounting.service;


import org.example.accounting.models.DocumentItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.ws.rs.BadRequestException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DocumentItemService {


    private AccountService accountService;

    @Autowired
    public DocumentItemService(AccountService accountService){
        this.accountService = accountService;
    }


    public void checkAlignment(List<DocumentItem> documentItems){
        if(documentItems.get(0).getAmount().compareTo(BigDecimal.ZERO) >= 0
                || documentItems.get(documentItems.size() - 1).getAmount().compareTo(BigDecimal.ZERO) <= 0
        ){
            throw new BadRequestException("first document item has to be negative and last one hes to be positive");
        }

        double sum = documentItems.stream()
                .mapToDouble(documentItem -> documentItem.getAmount().doubleValue())
                .sum();

        if(sum != 0 ){
            throw new BadRequestException("sum of all amounts must be zero");
        }
    }



}
