package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//このクラスはentity層
@Entity
public class Blog {
	// blog_idの設定
	// iｄの定義する書く必要があります
	@Id
	// 自動的id値を増加する
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long blogId;

	// blog_title
	private String blogTitle;

	// category_name
	private String categoryName;

	// blog_image
	private String blogImage;

	// article
	private String article;

	// account_idの設定
	private Long accountId;

	// 空のコンストラクタ、オブジェクトを生成する時に自動で必ず呼び出される機能
	public Blog() {
	}

	// 値を入れるコンストラクタ、前のところ（）GeneratedValue(strategy =
	// GenerationType.AUTO)）にidに自動的値を増加します、そこで定義する必要がないです。
	// 値がなければ、呼び出すことができません
	public Blog(String blogTitle, String categoryName, String blogImage, String article, Long accountId) {
		this.blogTitle = blogTitle;
		this.categoryName = categoryName;
		this.blogImage = blogImage;
		this.article = article;
		this.accountId = accountId;
	}

	// get,setメソッド
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}
