package blog.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

//springにこちらはblogサービス層を表示する
@Service
public class BlogService {

	// Dao層のメソッドと変数を呼び出すために
	@Autowired
	// BlogDaoをインスタンス作成、BlogDaoのすべてのデータを blogDaoに入れる
	// blogDaoを利用してDaoの変数とメソッドを呼び出す
	private BlogDao blogDao;

	// blog一覧のチェック
	// 複数の値が返されるため、リストが使用されます。
	// メソッドの戻り値はブログ blog が存在するかどうか、パラメータは id です。
	public List<Blog> selectAllBlog(Long accountId) {
		// もしaccount==null 戻り値としてnull,これで、セッションにはすべてのログイン情報が含まれます。まず、IDが存在するかどうかを確認します
		if (accountId == null) {
			return null;
		} else {
		    //目的は、ユーザー A に自分の Web サイト A へのアクセスのみを許可し、ユーザー B には Web サイト A へのアクセスを許可しないことです。
            //Web ページに同時にアクセスできるのは 1 人のユーザーだけであることを理解する
			// accountId は人ずつ一個しかないであり、各人が持つ ID は 1 つだけであり
            //ID を通じて実現できます。 findByall は、すべてのデータを取得するための dao レイヤー メソッドを参照します。
			// findByAccountId(accountId) は、この ID のみがこのユーザーへのアクセスを許可されることを意味します。
			// 服务层是引用了dao层的方法，所以到也需要变化List<Blog>findByAccountId(Long accountId);
			// findALL内容をコントローラークラスに渡す，ログインして、すべての製品情報をコントローラに与えることができます。
			return blogDao.findByAccountId(accountId);// 引用了dao层的方法去得到所有数据
		}
	}
	
   // 商品の登録処理チェック
	public boolean checkBlogTitle(String blogTitle, String categoryName, String blogImage, String article,
			Long accountId) {
		// 空の場合は、重複したブログ名がないことが証明され、新しいブログを作成できます。
		// もし、blogTitle==nullだったら、保存処理、true。
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			// dao のメソッドを呼び出してオブジェクトを保存します
			blogDao.save(new Blog(blogTitle, categoryName, blogImage, article, accountId));
			return true;
		} else {
			// そうでない場合、false
			return false;
		}
	}

	// 編集画面を表示するときのチャック
	public Blog blogEaitCheck(Long blogId) {
		// もし、productId == null
		if (blogId == null) {
			return null;
		} else {
			// そうでない場合は,findByProductIdの情報をコントローラークラスに渡す
			return blogDao.findByBlogId(blogId);
		}
	}

	// 更新処理のチェックの
	public boolean blogUpdate(Long blogId, String blogTitle, String categoryName, String blogImage, String article,
			Long accountId) {
		// もし、blogId==nullだったら、更新処理はしない
		if (blogId == null) {
			// false
			return false;
			// そうでない場合は、更新処理をする
			// コントローラークラスからもらった、blogIdを使って編集する前の、データを取得
			// 変更するべきところだけ、セッターを使用してデータを更新をする
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setCategoryName(categoryName);
			blog.setBlogImage(blogImage);
			blog.setArticle(article);
            blog.setAccountId(accountId);
            blogDao.save(blog);
			// trueを返す
			return true;
		}
	}

	// 削除のチェック
	public boolean deleteBlog(Long blogId) {
		// もし、コントローラーからもらったblogIdがnullであれば
		if (blogId == null) {
			// 削除できないこと false
			return false;
		// そうでない場合
		} else {
			// deleteByBlogIdを使用してブログを削除
			blogDao.deleteByBlogId(blogId);
			// true
			return true;
		}
	}
	
	// select
		public Blog selectBlogTitle(String keyword) {
			return blogDao.findByBlogTitle(keyword);
		}
}
