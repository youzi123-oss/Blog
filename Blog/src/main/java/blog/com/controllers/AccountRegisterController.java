package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

@Controller//申明这里是控制台
public class AccountRegisterController {

	@Autowired//为了使用AccountServices层中的方法和变量
	private AccountService accountService;//实例化AccountService对象，把类的信息都放入了accountService里面
	
	//登録画面を表示
	@GetMapping("/register")
	public String getAccountRegister() {
		return "register.html";
	}
	
	//登録処理
	@PostMapping("/register/process")
	//这里要使用entity规定的名字
	 //もし、createAdminがtrue  login.htmlへ移動。
//	表明用户在注册时候邮件没有重复，正常注册成功，直接返回登录界面进入页面
	//どうでない場合、register.htmlに残します。
//	说明用户在注册时候邮箱重复注册失败，无法登录
	//这里需要引用service中的方法来实现
	public String postAccountRegister(@RequestParam String accountName,
			                          @RequestParam String accountEmail,
			                          @RequestParam String password
			                          ) {
		if(accountService.createAccount(accountName, accountEmail, password)) {
			return "login.html";
		}else {
			return "register.html";
		}
		
	}
}
