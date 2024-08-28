package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import blog.com.models.entity.Account;
import blog.com.services.AccountService;

import jakarta.servlet.http.HttpSession;

//springにここはlogin司令塔を伝える
@Controller
public class AccountLoginController {

	// Service層のメソッドと変数を呼び出すために
	@Autowired

	// AccountServiceをインスタンス作成、AccountServiceのすべてのデータをaccountServiceに入れる
	// accountServiceを利用してAccountServiceの変数とメソッドを呼び出す
	private AccountService accountService;

	// Sessionが使えるように宣言
	@Autowired
	private HttpSession session;

	// ログイン画面を表示

	@GetMapping("/login")
	public String getAccountLogin(Model model) {
		model.addAttribute("error", false);
		return "login.html";
	}

	// ログイン処理，データを渡す
	@PostMapping("/login/process")

	// サービス層のメソッドを使用して、パスワードと電子メール名が一致しているかどうかを検出するサービス層のログイン ページを実装したいと考えています。
	// loginCheckメソッドを呼び出しその結果をaccountという変数に格納
	//メールアドレスとパスワードの名前はlogin.htmlの中の変数
	public ModelAndView loginCheck(@RequestParam String accountEmail, @RequestParam String password,ModelAndView mav) {
		// AccountLoginController司令塔はAccountServiceのメソッドを実装する
		// accountServiceの中にAccountServiceのすべてのメソッドを入れました、accountServiceを利用してAccountServiceの方法を呼び出し
		// loginCheckはパスワードと電子メール名が一致しているかどうかを検出する方法です
		Account account = accountService.loginCheck(accountEmail, password);
		// もし、account==nullログイン情報に保存
		if (account == null) {
			// accountはパスワードとメールアドレスを一致しないので、間違える。そのままログイン画面に残します
			mav.addObject("error",true);
			mav.setViewName("login.html");
			return mav;
			// そうでない場合は、sessionにログイン情報に保存
		} else {
			// account 変数には、ログイン情報 (loginAccountInfo サーバーの名前)
			// が保持されるようになりました。アカウントは値をloginAccountInfoに与えます
			// これは、アカウントがセッションサーバーに保存されたことを意味します。値をログインしたいときに気軽に呼び出すことができます。
			session.setAttribute("loginAccountInfo", account);
			// blog一覧画面にリダイレクトする/blog/list
		
			mav.setViewName("blog_list.html");
			return mav;

		}
	}

}
