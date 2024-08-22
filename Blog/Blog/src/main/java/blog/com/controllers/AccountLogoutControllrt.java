package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller//表明下面是控制台
public class AccountLogoutControllrt {

	@Autowired//需要使用session中登录信息
	private HttpSession session;
	
	//ログアウト画面
	@GetMapping("/account/logout")
	public String AccountLogout() {
		//セッションの無効化
		session.invalidate();
		return "redirect:/login";
	}
    
}
