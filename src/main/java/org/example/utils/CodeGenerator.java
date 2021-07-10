package org.example.utils;

import org.example.accounting.Repository.AccountHeadingRepository;
import org.example.accounting.models.AccountHeading;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CodeGenerator{

    public Long generateCode(int digit) {
        Random random = new Random();
        double pow = Math.pow(Double.valueOf(10), Double.valueOf(digit - 1));
        int randomCode = (int) ( pow + random.nextInt((int) pow));
        return (long) randomCode;
    }

    public String generateBigCode(int digit){
        if(digit <= 10) return String.valueOf(generateBigCode(digit));
        String code = "";
        while (digit > 10){
            digit  -= 10;
            code += generateCode(10);
        }
        code += generateCode(digit);
        return code;
    }

}
