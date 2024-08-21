package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

@Repository//表明下面代码是DAO层
public interface AccountDao extends JpaRepository<Account, Long> {
	//尖括号里面内容第一个是为了和Entity链接写的是Entity类名，
	 //第二个是Entity中主键的类型

	  //保存処理と更新処理　insertとupdate
	Account save(Account account);

	//为了避免同一个邮件多次注册
	//SELECT * FROM account WHERE account_email=?
	//用途：管理者の登録処理するときに、同じメールアドレスがあったらば登録させないようにする
	//登録したい人、メールアドレスを存在しないはず。一行でも取得できない
	Account findByAccountEmail(String accountEmail);
	
	//SELECT * FROM account WHERE account_email =? AND password=?
	//用途：ログイン処理に使用。入力したメールアドレスとパスワードが一致してるときだけデータを取得
	// 检测输入的时候，密码和用户名可以不可以对应上
	Account findByAccountEmailAndPassword(String accountEmail,String password);
	
}
