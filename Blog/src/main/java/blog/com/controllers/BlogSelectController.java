package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogSelectController {
	@Autowired
	private BlogService blogService;

	// ログインしていない人が直接当サイトにアクセスすることを防ぐため、ユーザーが正常にログインしたかどうかをセッションを利用して検出しています。
	@Autowired
	private HttpSession session;
	
	// 画面を表示する
		@GetMapping("/blog/select")
		// Web ページにコンテンツを表示するには、モデルを使用する必要があります
		public String getBlogRegister(Model model) {
			// セッションからログインしている人の情報をaccountという変数に格納
			Account account = (Account) session.getAttribute("loginAccountInfo");
			// もしaccount==null ログイン画面にリダイレクトする
			if (account == null) {
				return "redirect:/login";
			 // そうでない場合
			}else {
				return "blog_select.html";
			}
}
	
		@GetMapping("/blog/search")
		public String postBlogRegister(@RequestParam String keyword, Model model) {
			// セッションからログインしている人の情報をaccountという変数に格納
			Account account = (Account) session.getAttribute("loginAccountInfo");
	        // もしaccount==null ログイン画面にリダイレクトする
			if (account == null) {
				// もし、同じファイルの名前がなかったら保存、商品の一覧画面にリダイレクトする
				return "redirect:/login";
				
			} else {
				// selectBlogTitleから検索結果を取得
				
				// modelに値を設定してhtmlに渡す
				
//				// ブログ名の重複を避けるために、サービス層は Dao を呼び出す必要があります。
//				if (blogService.selectBlogTitle(keyword)) {
//					// 編集を終わったらブログ一覧画面を返す
//					return "redirect:/blog/list";
//					// そうでない場合は、商品登録画面にとどまります
//				} else {
					return "blog_register.html";
//				}
			}
		}
		}
