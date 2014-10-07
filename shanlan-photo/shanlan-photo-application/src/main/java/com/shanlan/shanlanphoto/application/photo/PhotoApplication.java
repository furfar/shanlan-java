
package com.shanlan.shanlanphoto.application.photo;

import java.util.List;
import org.dayatang.querychannel.Page;
import com.shanlan.shanlanphoto.application.dto.*;

public interface PhotoApplication {

	public PhotoDTO getPhoto(Integer id);
	
	public PhotoDTO savePhoto(PhotoDTO photo);
	
	public void updatePhoto(PhotoDTO photo);
	
	public void removePhoto(Integer id);
	
	public void removePhotos(Integer[] ids);
	
	public List<PhotoDTO> findAllPhoto();
	
	public Page<PhotoDTO> pageQueryPhoto(PhotoDTO photo, int currentPage, int pageSize);
	

}

