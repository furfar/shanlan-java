
package com.shanlan.photo.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.photo.application.dto.PhotoCollectionDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PhotoCollectionApplication {

    public PhotoCollectionDTO getPhotoCollection(Integer id);

    public PhotoCollectionDTO savePhotoCollection(PhotoCollectionDTO photoCollection);

    public void updatePhotoCollection(PhotoCollectionDTO photoCollection);

    public void removePhotoCollection(Integer id);

    public void removePhotoCollections(Integer[] ids);

    public List<PhotoCollectionDTO> findAllPhotoCollection();

    public Page<PhotoCollectionDTO> pageQueryPhotoCollection(PhotoCollectionDTO photoCollection, int currentPage, int pageSize);

    public Page<PhotoCollectionDTO> pageQueryPhotoCollection(PhotoCollectionDTO photoCollection, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles);


    List<PhotoCollectionDTO> listPhotoCollections(String userName)
            throws Exception;

    boolean updatePhotoCount(Integer photoCollectionId);
}

