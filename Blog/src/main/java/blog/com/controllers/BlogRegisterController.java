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

@Controller // 表明这里是关于bloglogin的控制台
public class BlogRegisterController {
	@Autowired // 一定要调用服务层的数据来实现方法
	private BlogService blogService;

//为了避免没有登录的人可以直接访问这个网站，所以要使用session来检测这个用户是否正常登陆了
	@Autowired
	private HttpSession session;

//这里是bloglogin界面，所以要显示bloglogin界面

	@GetMapping("/blog/register")
//要在网页上显示内容所以要用model
	public String getBlogRegister(Model model) {
		// セッションからログインしている人の情報をaccountという変数に格納
		// もしaccount==null ログイン画面にリダイレクトする
		// そうでない場合は、ログインしている人の名前を画面を表示する
		// 商品登録のhtmlを表示させる
		Account account = (Account) session.getAttribute("loginAccountInfo");
		if (account == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_register.html";
		}
	}

//blogの登録処理
// 新建一个blogimg文件夹，为了在用户登录的照片保存在blogimg中
	@PostMapping("/blog/register/process")
	public String postBlogRegister(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article) {
		// セッションからログインしている人の情報をaccountという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// もしaccount==null ログイン画面にリダイレクトする
		// そうでない場合は、画像のファイル名を取得、画像のアップロード、保存処理
		// もし、同じファイルの名前がなかったら保存、商品の一覧画面にリダイレクトする
		// そうでない場合は、商品登録画面にとどまります
		if (account == null) {
			return "redirect:/login";
		} else {
			// ファイルの名前を取得
			// 有名字相同的照片但是没有上传时间完全一样的的照片，为了区别照片在照片前面加上时间
			/*
			 * 現在の日時情報を元に、ファイル名を作成しています。simpleDateFormatクラスを使用して、日時のフォーマットを指定しています
			 * 具体的には、"yyyy-MM-dd-mm-ss"の形式でフォーマットされた文字列を取得している
			 * その後、blogImageオブジェクトから元のファイル名を取得し、フォーマットされた日時文字列と連結して、fileName変数に代入
			 */
//new Date在引用的时候引用uti
			// 带有日期的文件名做成
			String fileName = new SimpleDateFormat("yyyy-MM-HH-dd-mm-ss-").format(new Date())+ blogImage.getOriginalFilename();
			// ファイルの保存作業,出现错误的化点击按钮选择try-catch
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (blogService.checkBlogTitle(blogTitle, categoryName, fileName, article, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register.html";
			}
		}
	}
}
