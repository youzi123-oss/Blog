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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//springにここはlogin司令塔を伝える、サービス層のメソッドを使用したい
@Controller
public class BlogEditController {
	// BlogServices層のメソッドと変数を使用するために
	@Autowired
	// BlogServiceをインスタンスし、BlogServiceのすべてのデータをblogServiceに入れる
	// blogServiceを利用してBlogServiceの変数とメソッドを呼び出す
	private BlogService blogService;
	// Sessionが使えるように宣言、sessionに既にloginデータを入れました(session.setAttribute("loginAccountInfo",
	// account);),
	// session中のloginの情報を使いたい
	@Autowired
	private HttpSession session;

	// 編集画面を表示する
	// 編集処理
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEdit(@PathVariable Long blogId, Model model) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/login";
			// そうでない場合は、編集画面を表示させる情報を変数に格納 products
		} else {
			Blog blog = blogService.blogEaitCheck(blogId);
			// もし、products == nullだったら、商品の一覧ページにリダイレクトする
			if (blog == null) {
				return "redirect:/blog/list";
				// そうでない場合は、編集画面に編集する内容を渡す
			} else {
				// 編集するデータをhtmlに渡して表示する
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				// 編集画面を表示
				return "blog_edit.html";
			}

		}
	}

	// 更新処理（変更する時の前の情報を残しますために、前の入力した情報とblog編集画面同時に表示する）
	@PostMapping("/blog/edit")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article, @RequestParam Long blogId) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする
		// ファイルの保存
		if (account == null) {
			return "redirect:/login";
			// そうでない場合は、
			/*
			 * 現在の日時情報を元に、ファイル名を作成しています。simpleDateFormatクラスを使用して、日時のフォーマットを指定しています
			 * 具体的には、"yyyy-MM-dd-mm-ss"の形式でフォーマットされた文字列を取得している
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
			 */
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-HH-dd-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// BlogServiceの中でpublic boolean blogUpdate(Long blogId,String blogTitle, String
			// categoryName, String blogImage, String article, Long accountId)
			// 一致します
			// もし、blogUpdateの結果がtrueの場合は、商品の一覧にリダイレクトする
			if (blogService.blogUpdate(blogId, blogTitle, categoryName, fileName, article, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				// そうでない場合は、、商品編集画面にリダイレクトする
				return "redirect:/blog/edit" + blogId;
			}
		}

	}
}
