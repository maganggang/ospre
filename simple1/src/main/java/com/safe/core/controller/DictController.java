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
import com.safe.core.beans.Dict;
import com.safe.core.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {
	@Autowired
	private DictService dictService;
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Dict> allDict(Page<Dict> page, Dict dict) {
		ResultBean<Dict> b = new ResultBean<Dict>();
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Dict> result = dictService.selectAll(dict);
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/view/{id}")
	@ResponseBody
	public Dict findOne(@PathVariable Integer id){
		return dictService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return dictService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Dict updateOne(Dict dict){
		return dictService.update(dict);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Dict createOne(Dict dict){
		return dictService.insert(dict);
	}
}
