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
import com.safe.core.beans.Department;
import com.safe.core.beans.Item;
import com.safe.core.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Item> allItem(Page<Item> page, Item item){
		ResultBean<Item> b = new ResultBean<Item>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Item> result = itemService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/item/{id}")
	@ResponseBody
	public Item findOne(@PathVariable Integer id){
		return itemService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return itemService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Item updateOne(Item item){
		return itemService.update(item);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Item createOne(Item item){
		return itemService.insert(item);
	}
}
