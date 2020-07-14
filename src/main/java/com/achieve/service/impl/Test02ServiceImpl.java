package com.achieve.service.impl;

import com.achieve.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class Test02ServiceImpl implements ITestService {
    @Override
    public String getTest() {
        return "Test02";
    }


    @Override
    public void out() {
        System.out.println("Test02");
    }
}
