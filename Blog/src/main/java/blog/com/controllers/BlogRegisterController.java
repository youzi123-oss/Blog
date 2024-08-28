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

//springにここは司令塔を伝える
@Controller
public class BlogRegisterController {
	// Service層のメソッドと変数を呼び出すために
	@Autowired
	private BlogService blogService;

	// ログインしていない人が直接当サイトにアクセスすることを防ぐため、ユーザーが正常にログインしたかどうかをセッションを利用して検出しています。
	@Autowired
	private HttpSession session;

	// 画面を表示する
	@GetMapping("/blog/register")
	// Web ページにコンテンツを表示するには、モデルを使用する必要があります
	public String getBlogRegister(Model model) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/login";
			// そうでない場合は、ログインしている人の名前を画面を表示する
		} else {
			model.addAttribute("accountName", account.getAccountName());
			// 商品登録のhtmlを表示させる
			return "blog_register.html";
		}
	}

	// blogの登録処理
	// ユーザーがログインしたときに blogimg に写真を保存するための新しい blogimg フォルダーを作成します。
	@PostMapping("/blog/register/process")
	public String postBlogRegister(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
        // もしaccount==null ログイン画面にリダイレクトする
		if (account == null) {
			// もし、同じファイルの名前がなかったら保存、商品の一覧画面にリダイレクトする
			return "redirect:/login";
			// そうでない場合は、画像のファイル名を取得、画像のアップロード、保存処理
		} else {
			// ファイルの名前を取得
			// 同じ名前の写真はありますが、まったく同じアップロード時刻の写真はありません。写真を区別するために、写真の前に時間を追加します。
			/*
			 * 現在の日時情報を元に、ファイル名を作成しています。simpleDateFormatクラスを使用して、日時のフォーマットを指定しています
			 * 具体的には、"yyyy-MM-dd-mm-ss"の形式でフォーマットされた文字列を取得している
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
			 */
			// new Date は参照時に uti を参照します
			// 日付付きのファイル名
			String fileName = new SimpleDateFormat("yyyy-MM-HH-dd-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			// ファイルの保存作業,出现错误的化点击按钮选择try-catch
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ブログ名の重複を避けるために、サービス層は Dao を呼び出す必要があります。
			if (blogService.checkBlogTitle(blogTitle, categoryName, fileName, article, account.getAccountId())) {
				// 編集を終わったらブログ一覧画面を返す
				return "redirect:/blog/list";
				// そうでない場合は、商品登録画面にとどまります
			} else {
				return "blog_register.html";
			}
		}
	}
}
