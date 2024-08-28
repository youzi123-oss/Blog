package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//このクラスはentity層
@Entity
public class Account {

	// account_idの設定
	// iｄの定義する時書く必要があります
	@Id
	// 自动增加数值、例えば１，２、３
	@GeneratedValue(strategy = GenerationType.AUTO)

	// メンバ変数の设定
	private Long accountId;

	// account_name
	private String accountName;

	// account_email
	private String accountEmail;

	// password
	private String password;

	// 空のコンストラクタ、オブジェクトを生成する時に自動で必ず呼び出される機能
	public Account() {
	}

	// 値を入れるコンストラクタ、前のところ（）GeneratedValue(strategy =
	// GenerationType.AUTO)）にidに自動的値を増加します、そこで定義する必要がないです。
	// 値がなければ、呼び出すことができません
	public Account(String accountName, String accountEmail, String password) {
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.password = password;
	}

	// get,setの设定
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
