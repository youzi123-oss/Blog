package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AccountDao;
import blog.com.models.dao.ProfileDao;

@Service // 这里是bloglist服务层
public class ProfileService {
	 @Autowired//为了使用Dao层中的方法和变量
	 private ProfileDao profileDao;//实例化AccountDao对象，把类的信息都放入了accountDao里面
	//可以使用accountDao来调用Dao的方法
	 
	 
	 
}
