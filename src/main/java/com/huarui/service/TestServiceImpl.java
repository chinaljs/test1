package com.huarui.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/25.
 */
@Service
public class TestServiceImpl implements TestService {

    private Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    @Override
    public String test01(){
        log.debug("service test01 start");
        return "test01 success";
    }
}
