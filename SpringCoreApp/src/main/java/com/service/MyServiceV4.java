package com.service;

import com.dao.MyDao;
import com.mapper.TestMapper;
import com.utility.TestUtility;
import org.springframework.stereotype.Service;

@Service
public class MyServiceV4 {

    // This is called Injection inside method
    // This method depends upon 3 references
    // testUtility, testMapper, myDao
    public void test(MyDao myDao,
                     TestUtility testUtility,
                     TestMapper testMapper) {
        testUtility.test();
        testMapper.test();
        myDao.test();
    }
}
