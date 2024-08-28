package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Account;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//ここで参照されているものはすべて、対応するパッケージを pom.xml に自動的にダウンロードします。
//springにここはlogin司令塔を伝える、サービス層のメソッドを使用したい
@Controller
public class BlogListController {

	// Sessionが使えるように宣言、sessionに既にloginデータを入れました(session.setAttribute("loginAccountInfo",
	// account);),
	// session中のloginの情報を使いたい、ページを移動する時accountがnullかどうかを確認をする
	@Autowired
	private HttpSession session;
	// BlogServices層のメソッドと変数を使用するために
	@Autowired
	// BlogServiceをインスタンスし、BlogServiceのすべてのデータをblogServiceに入れる
	// blogServiceを利用してBlogServiceの変数とメソッドを呼び出す
	private BlogService blogservice;

	// 商品一覧画面を表示する
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// セッションからログインしている人の情報を取得,すべてのログイン情報はこのキー値（loginAccountInfo）に保存されます。
		// (Account)这个系统去判断是否你是要从account中取值
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もし、account==null ログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/login";
		 // そうでない場合
		} else {
			// 商品の情報を取得する,Javaのサービス層メソッドの戻り値リスト参照と同じです。
			// すべての製品情報はブログリストに掲載されています
			List<Blog> blogList = blogservice.selectAllBlog(account.getAccountId());
			// Webページにブログ情報を載せる
			model.addAttribute("blogList", blogList);
			// これは、ログインが成功したことを証明するために Web ページにその人の名前を表示するだけです。
			model.addAttribute("accountName", account.getAccountName());
			// ログインしている人の名前の情報を画面に渡して商品一覧のhtmlを表示する
			return "blog_list.html";
		}
	}

}
