package com.yhh.mybatis.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yhh.mybatis.entity.Items;
import org.springframework.web.HttpRequestHandler;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 17:46
 * @Version: 1.0
 * @Desc: 实现HttpRequestHandler接口的 处理器
 */
public class ItemsController22 implements HttpRequestHandler {

    //返回void
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //调用service查找 数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = new ArrayList<Items>();
        //向list中填充静态数据

        Items items_1 = new Items();
        items_1.setName("惠普笔记本");
        items_1.setPrice(600f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("华为手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);
        //设置模型数据
        request.setAttribute("itemsList", itemsList);
        //设置转发的视图1  ---------
        //request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);

        //设置转发的视图2  ----------
        //使用此方法可以通过修改response，设置响应的数据格式，比如响应json数据
        String json = "{\"name\":\"reiz\"}";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);


    }
}
