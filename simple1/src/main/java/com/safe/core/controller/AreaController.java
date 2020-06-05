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
import com.safe.core.beans.Area;
import com.safe.core.beans.Company;
import com.safe.core.service.AreaService;
@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService areaService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Area> allPosition(Page<Area> page, Area area){
		ResultBean<Area> b = new ResultBean<Area>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Area> result = areaService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/area/{id}")
	@ResponseBody
	public Area findOne(@PathVariable Integer id){
		return areaService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return areaService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Area updateOne(Area area){
		return areaService.update(area);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Area createOne(Area area){
		return areaService.insert(area);
	}
}
