package com.chenhe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenhe on 2017/12/26.
 */
@ControllerAdvice
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("YYYY-MM-dd"),true));
        binder.registerCustomEditor(int.class,new PropertyEditorSupport(){
            @Override
            public String getAsText() {
                return getValue().toString();
            }
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(Integer.parseInt(text));
            }
        });
    }
}
