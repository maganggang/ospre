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
import com.safe.core.beans.Area;
import com.safe.core.beans.Position;
import com.safe.core.service.PositionService;

@Controller
@RequestMapping("/position")
public class PositionController {
	@Autowired
	private PositionService positionService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Position> allPosition(Page<Position> page, Position position){
		ResultBean<Position> b = new ResultBean<Position>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Position> result = positionService.selectAll();
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/view/{id}")
	@ResponseBody
	public Position findOne(@PathVariable Integer id){
		return positionService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return positionService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Position updateOne(Position position){
		return positionService.update(position);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Position createOne(Position position){
		return positionService.insert(position);
	}
}
