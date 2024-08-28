package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

//springにここはlogin司令塔を伝える
@Controller
public class AccountLogoutControllrt {

	// Sessionが使えるように宣言、sessionに既にloginデータを入れました(session.setAttribute("loginAccountInfo",account);),
	// session中のloginの情報を使いたい
	@Autowired // セッションでログイン情報を使用する必要があります
	private HttpSession session;

	// ログアウト画面
	@GetMapping("/account/logout")
	public String AccountLogout() {
		// セッションの無効化
		session.invalidate();
		return "redirect:/login";
	}

}
