package blog.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
	//blog_idの設定
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long profileId;
//	profile_id
//	profile_image
//	profile_message
//	account_id
	private String profileImage;
	private String profileMessage;
	private Long accountId;
	
	// 空のコンストラクタ
	public Profile() {
	}
//	値を入れるコンストラクタ,id前面已经设定成自动增加了
	public Profile(String profileImage, String profileMessage, Long accountId) {
		this.profileImage = profileImage;
		this.profileMessage = profileMessage;
		this.accountId = accountId;
	}
	//get,set方法
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public String getProfileMessage() {
		return profileMessage;
	}
	public void setProfileMessage(String profileMessage) {
		this.profileMessage = profileMessage;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
}
