package com.yhh.mybatis.controller;

import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:59
 * @Version: 1.0
 * @Desc:
 */
@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    // 商品查询
    @RequestMapping("/queryItems")  //暂时没有接受参数
    public ModelAndView queryItems() throws Exception {

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
        // modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
        modelAndView.setViewName("items/itemsList");

        return modelAndView;

    }
}
