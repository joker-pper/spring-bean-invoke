package com.devloper.joker.springbeaninvokedemo.service;

import com.alibaba.fastjson.JSONObject;
import com.devloper.joker.springbeaninvokedemo.model.Order;
import com.devloper.joker.springbeaninvokedemo.model.Page;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HandlerService {

    public int add(int a, int b) {
        return a + b;
    }

    public void showOrder(Order order1, Order order2, Order... orders) {
        System.out.println("show order1 : " + JSONObject.toJSONString(order1));
        System.out.println("show order2 : " + JSONObject.toJSONString(order2));
    }

    public Page convertPage(Page page) {
        return page;
    }

    public static String getNow() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
