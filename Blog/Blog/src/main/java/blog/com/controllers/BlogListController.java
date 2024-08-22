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
//这里的引用东西全部在pom.xml里面自动下载对应的包

@Controller//表明下面是控制台。
public class BlogListController {

//登录的情报全部存在了session上面，在这里想要去调用session里面的信息
	@Autowired
	private  HttpSession session;
	
	@Autowired//需要使用blogservices的信息,调用服务层的方法来具体实现
	private BlogService blogservice;
	
	//商品一覧画面を表示する
    @GetMapping("/blog/list")
    // 从网页给你信息时候，用model去接收。想要在页面显示的同时把之前输入的名字也显示在页面上
    //model一定引用spring的包
	public String getBlogList(Model model) {
		//セッションからログインしている人の情報を取得,登录的信息全部存在了这个key值里面了loginAccountInfo
    	//(Account)这个系统去判断是否你是要从account中取值
    	 Account account = (Account) session.getAttribute("loginAccountInfo");
	
    	 
    	 //もし、admin==null ログイン画面にリダイレクトする
    	 //そうでない場合
    	 //ログインしている人の名前の情報を画面に渡して商品一覧のhtmlを表示する
    	 if(account == null) {
    		 return "redirect:/login";
    	 }else {
    		 //商品の情報を取得する,和服务层的方法的返回值一样.list引用了java
    		 //商品情报全部给了blogList
    		 List<Blog> blogList = blogservice.selectAllBlog(account.getAccountId());
    		 
    		 //把blog信息给网页
    		model.addAttribute("blogList",blogList);	 
    		 //这里只是把人名给网页证明登录成功
    		 model.addAttribute("accountName",account.getAccountName());
    		 return "blog_list.html";
    	 }}
	
	
	
	
}
