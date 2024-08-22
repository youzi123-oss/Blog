package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;
//服务层使用永久层的方法创建服务
//为控制台提供具体实现方法

@Service//向下面表示这个是服务层
public class AccountService {
 @Autowired//为了使用Dao层中的方法和变量
 private AccountDao accountDao;//实例化AccountDao对象，把类的信息都放入了accountDao里面
//可以使用accountDao来调用Dao的方法
 
 //保存処理（登録処理）
 //もし、findByAccountEmail==nullだったら登録処理をします
 //saveメソッドを使用して登録処理をする
 //保存が出来たらtrue
 //どうでない場合、保存処理失敗　false
 public boolean createAccount(String accountName, String accountEmail, String password) //这里输入的值要与entity中Accountde値を入れるコンストラクタ顺序和值都一样
 {
	 if(accountDao.findByAccountEmail(accountEmail)==null) {
		 accountDao.save(new Account(accountName,accountEmail,password));//这里引用了AccountDao中的save方法Account save(Account account);把其实例化
		 ////这里输入的值要与entity中Accountde値を入れるコンストラクタ顺序和值都一样
		 return true;
	 }else {
		 return false;
	 }
 }
	 //ログイン処理
	 //もし、emailとpasswordがfindByAccountEmailAndPasswordを使用して存在しなかった場合==nullの場合、
	 //その場合は、存在しないnullであることをコントローラークラスに知らせる
	 //そうでない場合ログインしている人の情報をコントローラークラスに渡す
	 
	 public Account loginCheck(String accountEmail, String password) {
		 Account account=accountDao.findByAccountEmailAndPassword(accountEmail, password);
		//loginCheckメソッドを呼び出してその結果をadminという変数に格納
		 if(account==null) {
			 return null;
		 }else {
			 return account;
		 }
	 
 }
}
