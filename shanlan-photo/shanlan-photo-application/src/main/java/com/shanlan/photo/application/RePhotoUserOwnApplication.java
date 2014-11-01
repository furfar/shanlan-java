
package com.shanlan.photo.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.photo.application.dto.RePhotoUserOwnDTO;

public interface RePhotoUserOwnApplication {

    public RePhotoUserOwnDTO getRePhotoUserOwn(Integer id);

    public RePhotoUserOwnDTO saveRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwn);

    public void updateRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwn);

    public void removeRePhotoUserOwn(Integer id);

    public void removeRePhotoUserOwns(Integer[] ids);

    public List<RePhotoUserOwnDTO> findAllRePhotoUserOwn();

    public Page<RePhotoUserOwnDTO> pageQueryRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwn, int currentPage, int pageSize);


    Page<RePhotoUserOwnDTO> pageQueryRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwnDTO, int page, int pagesize, String userName, boolean isSuper, List<String> roles);
}

