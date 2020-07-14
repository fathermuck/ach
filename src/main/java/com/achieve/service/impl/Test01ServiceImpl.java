package com.achieve.service.impl;

import com.achieve.service.ITestService;
import org.springframework.stereotype.Service;

@Service
public class Test01ServiceImpl implements ITestService {
    @Override
    public String getTest() {
        return "Test01";
    }

    @Override
    public void out() {
        System.out.println("Test01");
    }
}
