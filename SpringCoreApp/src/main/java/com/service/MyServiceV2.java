package com.service;
// I am going to use @Autowired annotation to inject references
// Do Remember: The best way to inject references in Constructor (check MyService)

import com.dao.MyDao;
import com.mapper.TestMapper;
import com.utility.TestUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServiceV2 {

    @Autowired
    private MyDao myDao;
    @Autowired
    private TestUtility testUtility;
    @Autowired
    private TestMapper testMapper;

    public void test(){
        testUtility.test();
        testMapper.test();
        myDao.test();
    }
}
