package com.safe.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.StaticApplicationContext;

import com.safe.core.base.bean.ListMapVo;
import com.safe.core.beans.Menu;

public class TreeUtils {
private static boolean isLastChildern(List<ListMapVo>  list,Map<String,Object> map,String key,String parentKey){
	if(list!=null&&list.size()>0&&map.get(parentKey)!=null){
		for(Map<String,Object> temp : list){
			if(map.get(key).equals(temp.get(parentKey))){
				return false;
			}
		}
	}else{
		return false;
	}
	return true;
	}
	public static   List<ListMapVo> toTree(List<ListMapVo> list,String key,String parentKey){
		List<Map<String,Object>> newList=new ArrayList<Map<String,Object>>();
		if(list!=null&&list.size()>0){
			for(Map<String,Object> temp : list){
				if(isLastChildern(list, temp, key, parentKey)){
					//putToParent
					for(Map<String,Object> tempParent : list){
						if(temp.get(parentKey).equals(tempParent.get(key))){
							
							if(tempParent.get("children")!=null){
								List<ListMapVo> oldChildren=(List<ListMapVo>) tempParent.get("children");
								oldChildren.add((ListMapVo) temp);
							}else{
								List<ListMapVo> childreLlist=new ArrayList<ListMapVo>();
								childreLlist.add((ListMapVo) temp);
								tempParent.put("children", childreLlist);
							}
							list.remove(temp);
							return toTree(list, key, parentKey);
						}
					}
				}
			}
			
		}
		return list;
	}
	public static List<Menu> MenutoTree( List<Menu> menus){
		List<Menu> old=new ArrayList<>();
		old.addAll(menus);
		for(int i=0,j=old.size();i<j;i++){
		Menu removedMenu=menus.remove(0);
		Menu parentMenu=contaionsInList(removedMenu,menus);
		if(parentMenu!=null){
			if(parentMenu.getChildren()!=null&&parentMenu.getChildren().size()>0){
				parentMenu.getChildren().add(removedMenu);
			}else{
				List<Menu> childernList=new ArrayList<>();
				childernList.add(removedMenu);
				parentMenu.setChildren(childernList);
			}
			return MenutoTree( menus);
		}else{
			menus.add(removedMenu);
		}
		}
		return menus;
	}
	/**
	 * 我与数组仍然有父子关联 
	* @Title: contaionsInList 
	* @param removedMenu
	* @param menus
	* @return
	* @return: boolean 
	* @author mgg
	* @date 2020年5月25日
	 */
private static Menu contaionsInList(Menu removedMenu, List<Menu> menus) {
	Iterator<Menu> it = menus.iterator();
	while(it.hasNext()){
		Menu temp=it.next();
		if(temp.getId().equals(removedMenu.getParentId())){
			return temp;
		}
	}
		return null;
	}
public static void main(String[] args) {
/*	List<ListMapVo> newList=new ArrayList<ListMapVo>();
	ListMapVo m1=new ListMapVo();
	m1.put("id", 1);
	m1.put("parentId", null);
	newList.add( m1);
	
	ListMapVo m2=new ListMapVo();
	m2.put("id", 2);
	m2.put("parentId", null);
	newList.add(m2);
	
	ListMapVo m3=new ListMapVo();
	m3.put("id", 3);
	m3.put("parentId", 1);
	newList.add(m3);
	
	ListMapVo m4=new ListMapVo();
	m4.put("id", 4);
	m4.put("parentId", 3);
	newList.add(m4);
	System.out.println(newList);
	List l1=toTree(newList, "id", "parentId");
	System.out.println(l1);*/
	List<Menu> newList=new ArrayList<Menu>();
	Menu m1=new Menu();
	m1.setId(1);
	newList.add( m1);
	
	Menu m2=new Menu();
	m2.setId(2);
	newList.add(m2);
	
	Menu m3=new Menu();
	m3.setId(3);
	m3.setParentId(1);
	newList.add(m3);
	
	Menu m4=new Menu();
	m4.setId(4);
	m4.setParentId(3);
	newList.add(m4);
	System.out.println(newList);
	List l1=MenutoTree(newList);
	System.out.println(l1);
}
}
