package com.safe.core.controller;

import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.safe.core.base.bean.ResultBean;
import com.safe.core.beans.Account;
import com.safe.core.beans.User;
import com.safe.core.filter.SessionListener;
import com.safe.core.service.AccountService;
import com.safe.core.service.UserService;
import com.safe.core.utils.BaseUserInfo;
import com.safe.core.utils.Captcha;
import com.safe.core.utils.GifCaptcha;
import com.safe.core.utils.QRCodeUtil;
import com.safe.core.utils.SessionUtils;



@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	/**
	 * 查询所有账号 特殊接口，无过滤
	 * 
	 * @Title: allAccount
	 * @param page
	 * @param account
	 * @return
	 * @return: ResultBean<Account>
	 * @author mgg
	 * @date 2020年4月30日
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Account> allAccount(Page<Account> page, Account account) {
		ResultBean<Account> b = new ResultBean<Account>();
		// 设置分页条件，Parameters:pageNum 页码pageSize 每页显示数量count 是否进行count查询
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Account> result = accountService.selectAll(account);
		// 使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
		b.setData(result);
		b.setCount(page.getTotal());
		return b;
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public Account oneAccount(@PathVariable Integer id) {
		return accountService.select(id);
	}

	@RequestMapping("/delete/{id}")
	@ResponseBody
	public Boolean deleteAccount(@PathVariable Integer id) {
		return accountService.delete(id);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Account updateAccount(Account account) {
		return accountService.update(account);
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Account createAccount(Account account) {
		return accountService.insert(account);
	}

	/**
	 * 登录验证 单点登录
	 * 
	 * @Title: login
	 * @param username
	 * @param password
	 * @return
	 * @return: String
	 * @author mgg
	 * @date 2019年12月12日
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest req, String username, String password, String vcode) {
		HttpSession session = req.getSession();
		// 转化成小写字母
		vcode = vcode.toLowerCase();
		String v = (String) session.getAttribute("_code");// 还可以读取一次后把验证码清空，这样每次登录都必须获取验证码;
		System.out.println(vcode + ":" + v);
		Account account = accountService.login(username, password);
		if (account != null) {
			BaseUserInfo userInfo=new BaseUserInfo();
			userInfo.setId(account.getId());
			userInfo.setName(account.getUsername());
			List<Integer> roleIds=accountService.getAllRoleIds(account.getId());
			userInfo.setRoleIds(roleIds);
			if (account.getUserId() != null) {
				User user = userService.select(account.getUserId());
				if (user != null) {
					userInfo.setUserId(user.getId());
					userInfo.setOrgId(user.getOrgId());
					userInfo.setPostId(user.getPostId());
					userInfo.setUsername(user.getName());
				}
			}
			session.setAttribute("userInfo", userInfo);
			SessionUtils.sessionHandlerByCacheMap(session);
			return "ok";
		}
		return "fail";
	}

	/**
	 * 退出清除缓存session
	 * 
	 * @Title: loginOut
	 * @param req
	 * @return
	 * @return: String
	 * @author mgg
	 * @date 2020年1月7日
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String loginOut(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("userInfo")!=null){
			BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
			// 清除在线用户后，更新map,替换map sessionid
			SessionListener.sessionContext.getSessionMap().remove(userInfo.getName());
			session.removeAttribute("userInfo");
			session.invalidate();
		}
		return "ok";
	}

	@RequestMapping("/myInfo")
	@ResponseBody
	public Account myInfo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
		if (userInfo != null) {
			Integer accountId = userInfo.getId();
			Account account = accountService.select(accountId);
			return account;
		} else {
			return null;
		}
	}

	/**
	 * 获取验证码（Gif版本）
	 * 
	 * @param response
	 */
	@RequestMapping(value = "getGifCode", method = RequestMethod.GET)
	public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码 宽，高，位数。
			 */
			Captcha captcha = new GifCaptcha(146, 33, 4);
			// 输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			// 存入Session
			session.setAttribute("_code", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取验证码异常：" + e.getMessage());
		}
	}
/**
 * 用户id获取账号
* @Title: selectByUserId 
* @param userId
* @return
* @return: Account 
* @author mgg
* @date 2020年6月10日
 */
	@RequestMapping("/user/{userId}")
	@ResponseBody
	public Account selectByUserId(@PathVariable Integer userId) {
		return accountService.selectByUserId(userId);
	}
	/**
	 * 非权限的生成二维码
	* @Title: getQRCode 
	* @param response
	* @param request
	* @param text
	* @param path
	* @return: void 
	* @author mgg
	* @date 2020年6月11日
	 */
	@RequestMapping("/qrcode")
	@ResponseBody
	public void getQRCode(HttpServletResponse response, HttpServletRequest request,String text,String path) {
		try {
			if(text==null||StringUtils.isBlank(text)){
				 text = "https://www.baidu.com/";
			}
			if(path==null||StringUtils.isBlank(path)){
				path=this.getClass().getResource("/").getPath()+"/fei2e.png";
			}
			System.out.println(text);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			QRCodeUtil.out(response.getOutputStream(), text,path);
			// 输出
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取异常：" + e.getMessage());
		}
	}
	/**
	 * 图片设置二维码， 未开发
	* @Title: makeQRCode 
	* @param response
	* @param request
	* @param text
	* @param path
	* @param files
	* @throws IOException
	* @return: void 
	* @author mgg
	* @date 2020年6月11日
	*      //https://www.yiibai.com/spring_mvc/spring-mvc-file-upload-tutorial.html
	 */
	@RequestMapping("/QR")
	@ResponseBody
	public String  makeQRCode(HttpServletResponse response, HttpServletRequest request,String text,MultipartFile file)  {
		if(text==null||StringUtils.isBlank(text)){
			 text = "https://www.baidu.com/";
		}
		try{
	            	String name = file.getOriginalFilename();
	            	if(name!=null){
	            		System.out.println("Client File Name = " + name);
	            		Image src = ImageIO.read(file.getInputStream());
	            		response.setHeader("Pragma", "No-cache");
	        			response.setHeader("Cache-Control", "no-cache");
	        			response.setDateHeader("Expires", 0);
		            	QRCodeUtil.outStream(response.getOutputStream(), text,src);
	            	}else{
	            		return "error";
	            	}
	            
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
		return "true";
	}
	/**
	 * 用户注册
	 * @param account
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Account register(Account account) {
		account.setCreatetime(new Date());
		
		//账号注册成功继续
			if(account.getUser().getPhone()==null) {
				account.getUser().setPhone(account.getMobile());
			}
			if(account.getUser().getEmail()==null) {
				account.getUser().setEmail(account.getEmail());
			}
			if(account.getUser().getName()==null) {
				account.getUser().setName(account.getNickname());
			}
			//默认最低的组织
			if(account.getUser().getOrgId()==null) {
				account.getUser().setOrgId(4);
			}
			User user1=userService.insert(account.getUser());
			if(user1!=null) {
				account.setUserId(user1.getId());
			}
		accountService.insert(account);
		return account;
	}
}
