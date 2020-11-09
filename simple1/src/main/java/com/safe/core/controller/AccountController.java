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
	 * ��ѯ�����˺� ����ӿڣ��޹���
	 * 
	 * @Title: allAccount
	 * @param page
	 * @param account
	 * @return
	 * @return: ResultBean<Account>
	 * @author mgg
	 * @date 2020��4��30��
	 */
	@RequestMapping("/all")
	@ResponseBody
	public ResultBean<Account> allAccount(Page<Account> page, Account account) {
		ResultBean<Account> b = new ResultBean<Account>();
		// ���÷�ҳ������Parameters:pageNum ҳ��pageSize ÿҳ��ʾ����count �Ƿ����count��ѯ
		page = PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		List<Account> result = accountService.selectAll(account);
		// ʹ��PageInfo��װ��ѯ�����ֻ��Ҫ��pageInfo����ҳ��Ϳ���
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
	 * ��¼��֤ �����¼
	 * 
	 * @Title: login
	 * @param username
	 * @param password
	 * @return
	 * @return: String
	 * @author mgg
	 * @date 2019��12��12��
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest req, String username, String password, String vcode) {
		HttpSession session = req.getSession();
		// ת����Сд��ĸ
		vcode = vcode.toLowerCase();
		String v = (String) session.getAttribute("_code");// �����Զ�ȡһ�κ����֤����գ�����ÿ�ε�¼�������ȡ��֤��;
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
	 * �˳��������session
	 * 
	 * @Title: loginOut
	 * @param req
	 * @return
	 * @return: String
	 * @author mgg
	 * @date 2020��1��7��
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String loginOut(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("userInfo")!=null){
			BaseUserInfo userInfo =(BaseUserInfo) session.getAttribute("userInfo");
			// ��������û��󣬸���map,�滻map sessionid
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
	 * ��ȡ��֤�루Gif�汾��
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
			 * gif��ʽ������֤�� ���ߣ�λ����
			 */
			Captcha captcha = new GifCaptcha(146, 33, 4);
			// ���
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			// ����Session
			session.setAttribute("_code", captcha.text().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("��ȡ��֤���쳣��" + e.getMessage());
		}
	}
/**
 * �û�id��ȡ�˺�
* @Title: selectByUserId 
* @param userId
* @return
* @return: Account 
* @author mgg
* @date 2020��6��10��
 */
	@RequestMapping("/user/{userId}")
	@ResponseBody
	public Account selectByUserId(@PathVariable Integer userId) {
		return accountService.selectByUserId(userId);
	}
	/**
	 * ��Ȩ�޵����ɶ�ά��
	* @Title: getQRCode 
	* @param response
	* @param request
	* @param text
	* @param path
	* @return: void 
	* @author mgg
	* @date 2020��6��11��
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
			// ���
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("��ȡ�쳣��" + e.getMessage());
		}
	}
	/**
	 * ͼƬ���ö�ά�룬 δ����
	* @Title: makeQRCode 
	* @param response
	* @param request
	* @param text
	* @param path
	* @param files
	* @throws IOException
	* @return: void 
	* @author mgg
	* @date 2020��6��11��
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
	 * �û�ע��
	 * @param account
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Account register(Account account) {
		account.setCreatetime(new Date());
		
		//�˺�ע��ɹ�����
			if(account.getUser().getPhone()==null) {
				account.getUser().setPhone(account.getMobile());
			}
			if(account.getUser().getEmail()==null) {
				account.getUser().setEmail(account.getEmail());
			}
			if(account.getUser().getName()==null) {
				account.getUser().setName(account.getNickname());
			}
			//Ĭ����͵���֯
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
