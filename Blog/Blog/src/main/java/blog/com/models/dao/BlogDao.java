package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository // 对应的是entity层中的blog
@Transactional//import用jakarta，整个实在有删除键时候使用，防止发生error

public interface BlogDao extends JpaRepository<Blog, Long> {
   //保存処理と更新処理　insertとupdate,商品の登録処理
	Blog save(Blog blog);
	
	//SELECT * FROM products
	//用途：商品の一覧を表示させるときに使用
	//import选择java.uti
	List<Blog>findByAccountId(Long accountId);
	
	//SELECT * FROM blog WHERE blogTitle=?
	//同じ商品の登録チェックに使用（同じ商品名が登録されないようになうにするチェックに使用
	Blog findByBlogTitle(String blogTitle);
	
	//SELECT * FROM blog WHERE blog_id=?
	//用途：編集画面を表示する際に使用
	//Id是唯一的，为了指定对于指定的页面更改
	Blog findByBlogId(Long blogId);
	
	//DELETE FROM blog WHERE blog_id=?
	//用途：削除使用します
	void deleteByBlogId(Long blogId);
	
	
	}
