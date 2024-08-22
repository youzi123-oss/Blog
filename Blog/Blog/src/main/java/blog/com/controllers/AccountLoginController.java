package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;

import jakarta.servlet.http.HttpSession;

@Controller // 告诉我们这里是控制台
public class AccountLoginController {

	@Autowired // 想要使用服务层中的方法去实现服务层登陆页面检测密码和邮件名是否一致
	private AccountService accountService;// 进行对服务层实例化

	@Autowired // Sessionが使えるように宣言
	private HttpSession session;

//ログイン画面を表示
	@GetMapping("/login")
	public String getAccountLogin() {
		return "login.html";
	}

//ログイン処理
	@PostMapping("/login/process")
	public String postAccountLogin(@RequestParam String accountEmail, @RequestParam String password) {
		// loginCheckメソッドを呼び出しその結果をadminという変数に格納
		// 控制台现在是实现在服务层上的login的方法
		// もし、admin==nullログイン情報に保存
		// そうでない場合は、sessionにログイン情報に保存
		// blog一覧画面にリダイレクトする/blog/list

		// Account是entity层，这个层是装数据的。调用服务层的数据放入其中
		Account account = accountService.loginCheck(accountEmail, password);
		if (account == null) {
			return "login.html";
		} else {
// account变数现在拥有着login的情报，loginAccountInfo服务器的名字。account再把值给了loginAccountInfo
//意味着把account的现在存到了session的服务器里面了，想要登录的值的时候可以随便调用
			session.setAttribute("loginAccountInfo", account);

			return "redirect:/blog/list";
//		
//ServletContext application = session.getServletContext();
//String sessionId = (String) application.getAttribute(account.getAccountName());
//
//if(sessionId!=null && !"" .equals(sessionId)) {
//	return "errors";
//}else {
//	application.setAttribute(account.getAccountName(), session.getId());
//}
//ServletContext application =session.getServletContext();
//application.removeAttribute(account.getAccountName());
}
}
}

