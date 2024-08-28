package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.entity.Account;

//サービス層は永続層メソッドを使用してサービスを作成します
//コンソールに特定の実装メソッドを提供します
//springにこちらはaccountサービス層を表示する
@Service
public class AccountService {

    //Dao層のメソッドと変数を呼び出すために
	@Autowired

	// AccountDaoをインスタンス作成、AccountDaoのすべてのデータをaccountDaoに入れる
	// accountDaoを利用してDaoの変数とメソッドを呼び出す
	private AccountDao accountDao;

	// 保存処理（登録処理）
	// (String accountName, String accountEmail, String password)
	// はAccountEntity中値を入れるコンストラクタ順番と値を一致しないといけない
	public boolean createAccount(String accountName, String accountEmail, String password) {
		// もし、findByAccountEmail==nullだったら登録処理をします
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			// ここにAccountDao中的save方法Account save(Account account)を呼び出し;
			// ここに(accountName,accountEmail,password)の値と順番はAccountEntityの値を一致しないといけない
			// saveメソッドを使用して登録処理をする
			accountDao.save(new Account(accountName, accountEmail, password));
			// 保存が出来たらtrue
			return true;
			// どうでない場合、保存処理失敗 false
		} else {
			return false;
		}
	}

	// ログイン処理
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		// loginCheckメソッドを呼び出してその結果をaccountという変数に格納
		// もし、emailとpasswordがfindByAccountEmailAndPasswordを使用して存在しなかった場合==nullの場合、
		if (account == null) {
			// その場合は、存在しないnullであることをコントローラークラスに知らせる
			return null;
			// そうでない場合ログインしている人の情報をコントローラークラスに渡す
		} else {
			return account;
		}

	}
}
