package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
@Autowired
private BlogService blogService;

@Autowired
private HttpSession session;

//削除処理
@PostMapping("/blog/delete")
public String blogDelete(Long blogId) {
	//// セッションからログインしている人の情報をaccountという変数に格納
	Account account = (Account) session.getAttribute("loginAccountInfo");
	// もしaccount==null ログイン画面にリダイレクトする
	if (account == null) {
		return "redirect:/login";
	} else {
		//もし、deleteBlogの結果がtrueだったら、
		if(blogService.deleteBlog(blogId)) {
			//商品の一覧ページにリダイレクトする
			return "redirect:/blog/list";
		}else {
			//そうでない場合
			//編集画面にリダイレクトする
			return "redirect:/blog/edit"+blogId;
		}
		
		
	}
}
}
