package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    //成员变量的设定
	//account_idの設定
	@Id//有id的时候就需要写
	@GeneratedValue(strategy = GenerationType.AUTO)//自动增加数值
	private Long accountId;

	// account_name
	private String accountName;

	// account_email
	private String accountEmail;

	// password
	private String password;

	//空のコンストラクタ
	public Account() {
	}
   //値を入れるコンストラクタ、在前面的地方id已经在放入自动增加的值
	public Account(String accountName, String accountEmail, String password) {
		this.accountName = accountName;
		this.accountEmail = accountEmail;
		this.password = password;
	}
	//get,set的方法设定
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
