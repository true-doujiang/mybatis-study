package com.yhh.mybatis.controller;

import com.yhh.mybatis.entity.ItemsCustom;
import com.yhh.mybatis.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 19:59
 * @Version: 1.0
 * @Desc:
 */
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    // 商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);
        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    /**
     * 商品修改
     */

    //商品信息修改页面显示    返回ModelAndView方式
    //@RequestMapping("/editItems")
/*	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems()throws Exception {

		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//将商品信息放到model
		modelAndView.addObject("itemsCustom", itemsCustom);
		//商品修改页面
		modelAndView.setViewName("items/editItems");
		return modelAndView;
	}
*/
    //商品信息修改页面显示    返回String方式
    @RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
    public String editItems(Integer id,Model model)throws Exception {
        //返回String
        //调用service根据商品id查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        //通过形参中的model将model数据传到页面
        //相当于modelAndView.addObject方法
        model.addAttribute("itemsCustom", itemsCustom);
        return "items/editItems";
    }

    //还可以返回void方式
	/*
	在controller方法形参上可以定义request和response，使用request或response指定响应结果：
	1、使用request转向页面，如下：
	request.getRequestDispatcher("页面路径").forward(request, response);

	2、也可以通过response页面重定向：
	response.sendRedirect("url")

	3、也可以通过response指定响应结果，例如响应json数据如下：
	response.setCharacterEncoding("utf-8");
	response.setContentType("application/json;charset=utf-8");
	response.getWriter().write("json串");

	 */

    //商品信息修改提交
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit()throws Exception {

        //还没学习绑定数据，所以这里没有传参，请看下一个工程
        //调用service更新商品信息，页面需要将商品信息传到此方法
        //itemsService.updateItems(id, itemsCustom);

        //1、重定向到商品查询列表 ,此时    不要加根路径(/items),因为他们都在一个根域下面
        return "redirect:queryItems.action";

        //2、页面转发 地址栏不会变化
        //return "forward:queryItems.action";

        //3、return "success";
    }


}
