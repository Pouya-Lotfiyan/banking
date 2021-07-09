package org.example.accounting.controller;

import org.example.accounting.models.Document;
import org.example.accounting.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Path("document")
public class DocumentController {


    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Document postDocument(Document document){
        return this.documentService.submitDocument(document);
    }


}
