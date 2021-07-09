package org.example.accounting.controller;


import org.example.accounting.models.Account;
import org.example.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("accounts")
@Controller
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts(){
        return this.accountService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("id") long id){
        return this.accountService.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Account postAccount(Account account){
        return this.accountService.insertOne(account);
    }


    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account putAccount(@PathParam("id") long id, Account account){
        return this.accountService.update(id, account);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account deleteAccount(@PathParam("id")  long id){
        return this.accountService.remove(id);
    }


}
