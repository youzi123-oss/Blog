package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service // 这里是bloglist服务层
public class BlogService {
	@Autowired // 一般都需要引用Blogdao层的数据
	private BlogDao blogDao;

//blog一覧のチェック
//もしaccount==null 戻り値としてnull,现在session拥有所有的登陆情报。首先要检测这个id是否存在
//findALL内容をコントローラークラスに渡す，可以登录把商品的情报都给控制器
//用列表是因为返回的值不只有一个
//blog需要引用java的
	public List<Blog> selectAllBlog(Long accountId) {
//方法的返回值是Blog博客是否存在，参数是id
		if (accountId == null) {
			return null;
		} else {
			// 目的只允许A用户访问自己A的网站，不允许B用户访问A网站。实现网页同时只能有一个用户访问
			// accountId具有唯一性，每个人只有一个ID并且不一样。
			//所以可以通过Id来实现访问的唯一性。findByall引用了dao层的方法去得到所有数据
			//findByAccountId(accountId)的意思是只允许这个Id去访问这个用户。
			//服务层是引用了dao层的方法，所以到也需要变化List<Blog>findByAccountId(Long accountId);
			return blogDao.findByAccountId(accountId);// 引用了dao层的方法去得到所有数据

		}
	}

//避免有重复的有关blog名出现，服务层一定要调用Dao
//商品の登録処理チェック
//もし、blogTitle==nullだったら、保存処理、true。返回的值全是返回控制台
//そうでない場合、false
//括号里面参数的位置要参考entity去写
	public boolean checkBlogTitle(String blogTitle, String categoryName, String blogImage, String article,
			Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {// 当它为空的时候证明它是没有重复blog名字的，可以新建一个blog
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));// 调用dao中方法来存储对象
			return true;
		} else {
			return false;
		}
	}
	//編集画面を表示するときのチャック
	//もし、productId == null
	//そうでない場合は,findByProductIdの情報をコントローラークラスに渡す
	public Blog blogEaitCheck(Long blogId) {
	if(blogId == null) {
		return null;
	}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	//更新処理のチェックの
	//もし、blogId==nullだったら、更新処理はしない
	//false
	//そうでない場合は、更新処理をする
	//コントローラークラスからもらった、blogIdを使って編集する前の、データを取得
	//変更するべきところだけ、セッターを使用してデータを更新をする
	//trueを返す
	public boolean blogUpdate(Long blogId,String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		if(blogId == null) {
			return false;
		}else {
			Blog blog= blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setArticle(article);
			
			
			blog.setAccountId(accountId);
		
			blogDao.save(blog);
			return true;
		}
	}
	//削除のチェック
	//もし、コントローラーからもらったblogIdがnullであれば
	//削除できないこと　false
	//そうでない場合
	//deleteByBlogIdを使用してブログを削除
	//true
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
