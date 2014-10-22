
package com.shanlan.user.application;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;

public interface UserDetailApplication {

	public UserDetailDTO getUser(Integer id);
	
	public UserDetailDTO saveUser(UserDetailDTO user);
	
	public void updateUser(UserDetailDTO user);
	
	public void removeUser(Integer id);
	
	public void removeUsers(Integer[] ids);
	
	public List<UserDetailDTO> findAllUser();
	
	public Page<UserDetailDTO> pageQueryUser(UserDetailDTO user, int currentPage, int pageSize);


    public boolean register(UserBaseDTO userBaseDTO) throws Exception;

}

