package com.service;

import com.dao.MyDao;
import com.mapper.TestMapper;
import com.utility.TestUtility;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    // reach out to dao
    // MyDao myDao = new MyDao(); //POJO - self managed
    private final MyDao myDao; //managed by spring
    private final TestUtility testUtility;
    private final TestMapper testMapper;

    public MyService(MyDao myDao, TestUtility testUtility, TestMapper testMapper) {
        this.myDao = myDao;
        this.testUtility = testUtility;
        this.testMapper = testMapper;
    }

    public void test(){
         testUtility.test();
         testMapper.test();
         myDao.test();
    }
}
