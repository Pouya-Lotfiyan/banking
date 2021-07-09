package org.example.config;

import org.example.accounting.controller.AccountController;
import org.example.accounting.controller.AccountHeadingController;
import org.glassfish.jersey.server.ResourceConfig;


public class Application extends ResourceConfig {

    public Application(){
        register(AccountHeadingController.class);
        register(AccountController.class);
    }

}
