
package com.shanlan.user.application;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.shanlan.user.application.dto.*;

public interface PhotographerApplication {

	public PhotographerDTO getPhotographer(Integer id);
	
	public PhotographerDTO savePhotographer(PhotographerDTO photographer);
	
	public void updatePhotographer(PhotographerDTO photographer);
	
	public void removePhotographer(Integer id);
	
	public void removePhotographers(Integer[] ids);
	
	public List<PhotographerDTO> findAllPhotographer();
	
	public Page<PhotographerDTO> pageQueryPhotographer(PhotographerDTO photographer, int currentPage, int pageSize);

    public Page<UserDetailDTO> pageQueryPhotographer(UserDetailDTO queryVo, int currentPage,
                                             int pageSize, String userName, boolean isSuper, List<String> roles);
}

