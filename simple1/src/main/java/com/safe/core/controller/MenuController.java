package com.safe.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.Menu;
import com.safe.core.service.MenuService;
@Controller
@RequestMapping("/menu")
public class MenuController {
		@Autowired
		private MenuService menuService;
		@RequestMapping("/all")
		@ResponseBody
		public ResultBean<Menu> all(Page<Menu> page ,Menu menu){
			ResultBean<Menu> b = new ResultBean<Menu>();
			page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
			List<Menu> result = menuService.selectAll();
			b.setData(result);
			b.setCount(page.getTotal());
			return b;
		}
		@RequestMapping("/delete/{id}")
		@ResponseBody
		public Boolean deleteOne(@PathVariable Integer id){
			return menuService.delete(id);
		}
		@RequestMapping("/view/{id}")
		@ResponseBody
		public Menu view(@PathVariable Integer id){
			return menuService.select(id);
		}
		@RequestMapping("/update")
		@ResponseBody
		public Menu updateOne(Menu org){
			return menuService.update(org);
		}
		@RequestMapping("/insert")
		@ResponseBody
		public Menu createOne(Menu org){
			return menuService.insert(org);
		}
}
