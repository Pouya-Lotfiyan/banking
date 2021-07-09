package org.example.accounting.controller;

import org.example.accounting.models.AccountHeading;
import org.example.accounting.service.AccountHeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("account-headings")
@Controller
public class AccountHeadingController {


    private AccountHeadingService accountHeadingService;

    @Autowired
    public AccountHeadingController(AccountHeadingService accountHeadingService){
        this.accountHeadingService = accountHeadingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AccountHeading> getAllAccountHeadings() {
        return  accountHeadingService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountHeading getAccountHeading(@PathParam("id") long id) {
            return this.accountHeadingService.get(id);
    }


    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountHeading updateAccountHeading(@PathParam("id") long id, AccountHeading accountHeading){
        return this.accountHeadingService.update(id, accountHeading);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AccountHeading insertAccountHeading(AccountHeading accountHeading){
        System.out.println(accountHeading.getType());
        return this.accountHeadingService.insertOne(accountHeading);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountHeading deleteAccountHeading(@PathParam("id") long id){
        return this.accountHeadingService.remove(id);
    }


}
