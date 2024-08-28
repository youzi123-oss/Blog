package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AccountService;

//springにここはlogin司令塔を伝える
@Controller
public class AccountRegisterController {

	// AccountServices層のメソッドと変数を使用するために
	@Autowired
	// AccountServiceをインスタンスし、AccountServiceのすべてのデータをaccountServiceに入れる
	// accountServiceを利用してAccountServiceの変数とメソッドを呼び出す
	private AccountService accountService;

	// 登録画面を表示
	@GetMapping("/register")
	public String getAccountRegister() {
		return "register.html";
	}

	// 登録処理
	@PostMapping("/register/process")

	// もし、createAccountがtrue login.htmlへ移動。
	// これは、ユーザーが登録時に重複したメールアドレスを使用して、ログインできないことを意味します。
	// ここで、サービス層ののメソッドを利用して実現する必要があります。
	// メソッドの中でデータ型を入れますけど（public String postAccountRegister(@RequestParam String
	// accountName,）、
	// データを呼びだす時にデータ型はいらないaccountService.createAccount(accountName, accountEmail,
	// password)
	public String postAccountRegister(@RequestParam String accountName, @RequestParam String accountEmail,
			@RequestParam String password) {
		if (accountService.createAccount(accountName, accountEmail, password)) {
			// ユーザーの登録時に重複する電子メールがなく、通常の登録が成功し、ユーザーがログインに飛んでいく
			return "login.html";
		} else {
			// どうでない場合、register.htmlに残します。
			return "register.html";
		}

	}
}
