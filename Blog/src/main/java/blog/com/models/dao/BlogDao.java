package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;

//DAO 層は、データベースに関連する操作を処理します。
//このinterfaceはDao層を表示するspringに
@Repository // 对应的是entity层中的blog、BlogDaoはEntity層の中Blogを対応している
@Transactional // import用jakarta，エーラを避けるために、deleteきーがある時に使えます

public interface BlogDao extends JpaRepository<Blog, Long> {
	// 保存処理と更新処理 insertとupdate,商品の登録処理
	Blog save(Blog blog);

	// SELECT * FROM account
	// 用途：ブログの一覧を表示させるときに使用
	// import选择java.uti
	List<Blog> findByAccountId(Long accountId);

	// SELECT * FROM blog WHERE blogTitle=?
	// 同じブログの登録チェックに使用（同じ商品名が登録されないようになうにするチェックに使用
	Blog findByBlogTitle(String blogTitle);

	// SELECT * FROM blog WHERE blog_id=?
	// 用途：編集画面を表示する際に使用
	// Id是唯一的，为了指定对于指定的页面更改
	Blog findByBlogId(Long blogId);

	// DELETE FROM blog WHERE blog_id=?
	// 用途：削除使用します
	void deleteByBlogId(Long blogId);
	
	// 特定のタイトルを部分一致で検索し、大小文字を区別しない形で検索
    Blog findByBlogTitleContainingIgnoreCase(String blogtitle);

}
