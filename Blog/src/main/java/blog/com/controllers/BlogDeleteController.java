package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Account;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

//springにここはlogin司令塔を伝える
@Controller
public class BlogDeleteController {
//BlogServices層のメソッドと変数を使用するために
	@Autowired
//BlogServiceをインスタンスし、BlogServiceのすべてのデータをblogServiceに入れる
//blogServiceを利用してBlogServiceの変数とメソッドを呼び出す
	private BlogService blogService;

	// Sessionが使えるように宣言、sessionに既にloginデータを入れました(session.setAttribute("loginAccountInfo",
	// account);),
	// session中のloginの情報を使いたい
	@Autowired
	private HttpSession session;

	// 削除処理
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからログインしている人の情報をaccountという変数を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする、まずはログイン成功ができるかどうか
		if (account == null) {
			return "redirect:/login";
		} else {
			// もし、deleteBlogの結果がtrueだったら、blogServiceのdeleteメソッドを使用する
			if (blogService.deleteBlog(blogId)) {
				// 商品の一覧ページにリダイレクトする
				return "redirect:/blog/list";
			} else {
				// そうでない場合（ログインが成功しますけど、blogがないので削除したい対象がありません）
				// 編集画面にリダイレクトする、新しいblogを作成する
				return "redirect:/blog/edit" + blogId;
			}

		}
	}
}
