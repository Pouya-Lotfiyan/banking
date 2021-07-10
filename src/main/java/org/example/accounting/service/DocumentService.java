package org.example.accounting.service;

import org.example.accounting.Repository.DocumentRepository;
import org.example.accounting.models.Document;
import org.example.utils.CodeGenerator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;

@Service
public class DocumentService {


    private DocumentRepository documentRepository;
    private DocumentItemService documentItemService;
    private AccountService accountService;

    @Autowired
    public DocumentService(DocumentRepository documentRepository,
                           DocumentItemService documentItemService,
                           AccountService accountService
    ){
        this.documentRepository = documentRepository;
        this.documentItemService = documentItemService;
        this.accountService = accountService;
    }


    public Document submitDocument(Document document) {
        if(document.getBranchCode() == null) {
            throw new BadRequestException("branch code should be defined");
        }
        this.documentItemService.checkAlignment(document.getDocumentItems());
        generateDocumentNumber(document);
        Session transferMoneySession;
        transferMoneySession = this.accountService.transferMoney(document.getDocumentItems());
        return this.documentRepository.save(transferMoneySession, document);
    }


    private void generateDocumentNumber(Document document){
        boolean codeNotValid = true;
        String documentNumber="-1";
        List<Document> documents;
        CodeGenerator codeGenerator = new CodeGenerator();
        while (codeNotValid){
            documentNumber = codeGenerator.generateBigCode(12);
            documents = this.documentRepository.findByDocumentNumber(documentNumber);
            if (documents.isEmpty()) {
                codeNotValid = false;
            }
        }
        document.setDocumentNumber(String.valueOf(documentNumber));
    }




}
