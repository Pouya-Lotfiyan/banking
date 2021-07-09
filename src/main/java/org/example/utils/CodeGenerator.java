package org.example.utils;

import org.example.accounting.Repository.AccountHeadingRepository;
import org.example.accounting.models.AccountHeading;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CodeGenerator{

    public Long generateCode(int digit) {
        Random random = new Random();
        int randomCode = (int) (Math.pow(Double.valueOf(10), Double.valueOf(digit-1)) + random.nextInt(10000));
        return Long.valueOf(randomCode);
    }
}
