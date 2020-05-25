package com.safe.core.service;

import java.util.List;

import com.safe.core.beans.Menu;
import com.safe.core.beans.Organization;

public interface MenuService {

	List<Menu> selectAll();

	Menu select(Integer id);

	Boolean delete(Integer id);

	Menu update(Menu org);

	Menu insert(Menu org);

}
