package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Account;

//DAO 層は、データベースに関連する操作を処理します。
//このinterfaceはDao層を表示するspringに
@Repository

//ENTITY層を呼び出すために、かっこの中にAccountはEntity層のクラス名
//LongはEntity層の主キーのデータ型
public interface AccountDao extends JpaRepository<Account, Long> {

	// 保存処理と更新処理 insertとupdate
	Account save(Account account);

	// 为同じメールアドレスを何回登録することを避けたいために
	// SELECT * FROM account WHERE account_email=?
	// 用途：管理者の登録処理するときに、同じメールアドレスがあったらば登録させないようにする
	// 登録したい人、メールアドレスを存在しないはず。一行でも取得できない
	Account findByAccountEmail(String accountEmail);

	// SELECT * FROM account WHERE account_email =? AND password=?
	// 用途：ログイン処理に使用。入力したメールアドレスとパスワードが一致してるときだけデータを取得
	// データを取得出来たら、このメールアドレスとパスワードが正しいですので、ログインができます
	// データを取得できない場合にはnull、そうすればそのままログイン画面に残します
	Account findByAccountEmailAndPassword(String accountEmail, String password);

}
