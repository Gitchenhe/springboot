package com.chenhe.controller;

import com.chenhe.test.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhe on 2017/12/26.
 */
@Controller
public class IndexController extends AbstractController {

    @Autowired
    @Qualifier("initTestEntity")
    private TestEntity testEntity;

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public String index(){
        logger.info("实体类为空?{}",(testEntity == null));
        if (testEntity != null){
            System.out.println(testEntity.getId());
        }
        return "index";
    }

    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("LOGIN_PAGE");
        return modelAndView;
    }



    /**
     * Template method. Subclasses must implement this.
     * The contract is the same as for {@code handleRequest}.
     *
     * @param request
     * @param response
     * @see #handleRequest
     */
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView("LOGIN_PAGE");
        return modelAndView;
    }
}
