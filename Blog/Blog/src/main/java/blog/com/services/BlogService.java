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
}
