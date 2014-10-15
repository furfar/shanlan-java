
package com.shanlan.user.application;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;

public interface UserBlogApplication {

	public UserBlogDTO getUserBlog(Integer id);
	
	public UserBlogDTO saveUserBlog(UserBlogDTO userBlog);
	
	public void updateUserBlog(UserBlogDTO userBlog);
	
	public void removeUserBlog(Integer id);
	
	public void removeUserBlogs(Integer[] ids);
	
	public List<UserBlogDTO> findAllUserBlog();
	
	public Page<UserBlogDTO> pageQueryUserBlog(UserBlogDTO userBlog, int currentPage, int pageSize);
	

}

