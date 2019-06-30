package com.yhh.mybatis.controller;



import java.util.ArrayList;
import java.util.List;

import com.yhh.mybatis.entity.Items;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 17:55
 * @Version: 1.0
 * @Desc: 注解开发Handler
 */
@Controller     //使用Controller标识 它是一个控制器       使用@Component不行
public class ItemsController {


    //http://localhost:8088/springmvc_yq_03/queryItems.action  访问时还要加.action

    @RequestMapping("/queryItems")   //后面可以加.action
    public ModelAndView queryItems()throws Exception{

        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = new ArrayList<Items>();

        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        //返回ModelAndView
        ModelAndView modelAndView =  new ModelAndView();
        //相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        //下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");

        //上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀(在spring.xml中配了前缀，后缀)
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    //这个类没有继承 也没有实现接口，所以这里可以添加
    //定义其它的方法
    //商品添加
    //商品修改
}
