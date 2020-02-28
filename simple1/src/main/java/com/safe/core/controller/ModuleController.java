package com.safe.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safe.core.beans.Account;
import com.safe.core.beans.Authority;
import com.safe.core.beans.Module;
import com.safe.core.beans.ResultBean;
import com.safe.core.beans.User;
import com.safe.core.service.ModuleService;

@Controller
@RequestMapping("/module")
public class ModuleController {
	@Autowired
	private ModuleService moduleService;
	@RequestMapping(value="/all")
	@ResponseBody
	public ResultBean<Module> allModule(Page<Module> page,Module module){
		ResultBean<Module> b=new ResultBean<Module>();
		 //���÷�ҳ������Parameters:pageNum ҳ��pageSize ÿҳ��ʾ����count �Ƿ����count��ѯ
		page=PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
	    List<Module> result = moduleService.selectAll(module);
	    //ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���
      //PageInfo<Module> pageInfo = new PageInfo<>(result,5);
	    b.setData(result);
	    b.setCount(page.getTotal());
		return b;
	}
	@RequestMapping("/auth/{id}")
	@ResponseBody
	public Module findOne(@PathVariable Integer id){
		return moduleService.selectByPrimaryKey(id);
	}
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteOne(@PathVariable Integer id){
		return moduleService.deleteByPrimaryKey(id);
	}
	@RequestMapping("/update")
	@ResponseBody
	public Module updateOne(Module module){
		return moduleService.update(module);
	}
	@RequestMapping("/insert")
	@ResponseBody
	public Module createOne(Module module){
		return moduleService.insert(module);
	}
}
