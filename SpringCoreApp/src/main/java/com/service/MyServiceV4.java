package com.service;

import com.dao.MyDao;
import com.mapper.TestMapper;
import com.utility.TestUtility;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class MyServiceV4 {

    // This is called Injection inside method
    // This method depends upon 3 references
    // testUtility, testMapper, myDao

    private final Clock clock;

    public MyServiceV4(Clock clock) {
        this.clock = clock;
    }

    public void test(MyDao myDao,
                     TestUtility testUtility,
                     TestMapper testMapper) {
        testUtility.test();
        testMapper.test();
        myDao.test();
    }

    public void getTime(){
        System.out.println("Time is " + clock.instant());
    }
}
