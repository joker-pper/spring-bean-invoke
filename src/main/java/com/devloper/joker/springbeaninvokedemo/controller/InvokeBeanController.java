package com.devloper.joker.springbeaninvokedemo.controller;


import com.alibaba.fastjson.JSONObject;
import com.devloper.joker.beaninvoke.controller.ControllerTemplate;
import com.devloper.joker.beaninvoke.controller.ResponseResult;
import com.devloper.joker.beaninvoke.model.InvokeEntity;
import com.devloper.joker.beaninvoke.resolver.InvokeParameterResolver;
import com.devloper.joker.springbeaninvokedemo.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Controller
@RequestMapping("/api/dev/invoke")
public class InvokeBeanController extends ControllerTemplate {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 定义无法处理的类型的参数值的转换
     */
    private InvokeParameterResolver parameterResolver = new InvokeParameterResolver() {
        @Override
        public boolean supportType(Type type) {
            List<Type> typeList = Arrays.asList(Page.class);
            return typeList.contains(type);
        }

        @Override
        public Object convert(Object val, Type type, Class beanClass, Object bean, Method method, int index) {
            JSONObject jsonObject = JSONObject.parseObject(val.toString());
            if (type.equals(Page.class)) {
                Page page = new Page();
                page.setPageIndex(jsonObject.getIntValue("page"));
                page.setPageNumber(jsonObject.getIntValue("num"));
                return page;
            } else {
               /* if (PageRequest.class.equals(type) || Pageable.class.equals(type)) {
                    return PageRequest.of(jsonObject.getIntValue("page"), jsonObject.getIntValue("size"));
                }*/
            }
            return null;
        }
    };

    @Override
    public InvokeParameterResolver parameterResolver() {
        return parameterResolver;
    }

    /**
     * 通过名称找到对应的bean实例
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    @CrossOrigin
    @PostMapping("/result")
    @ResponseBody
    @Override
    public ResponseResult getInvokeResult(@RequestBody InvokeEntity model) {
        return super.getInvokeResult(model);
    }

    @CrossOrigin
    @GetMapping("/method/entitys")
    @ResponseBody
    @Override
    public ResponseResult getInvokeMethodEntitys(String beanName, boolean isByClass) {
        return super.getInvokeMethodEntitys(beanName, isByClass);
    }
}
