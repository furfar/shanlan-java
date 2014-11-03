
package com.shanlan.trade.application;

import java.util.List;

import org.dayatang.querychannel.Page;

import com.shanlan.trade.application.dto.PhotoPackagesDTO;

public interface PhotoPackageApplication {

    public PhotoPackagesDTO getPhotoPackages(Integer id);

    public PhotoPackagesDTO savePhotoPackages(PhotoPackagesDTO photoPackages);

    public void updatePhotoPackages(PhotoPackagesDTO photoPackages);

    public void removePhotoPackage(Integer id);

    public void removePhotoPackages(Integer[] ids);

    public List<PhotoPackagesDTO> findAllPhotoPackages();

    public Page<PhotoPackagesDTO> pageQueryPhotoPackages(PhotoPackagesDTO photoPackages, int currentPage, int pageSize);

    List<PhotoPackagesDTO> listPackages(String userName, String photoType) throws Exception;


    Page<PhotoPackagesDTO> pageQueryPhotoPackages(PhotoPackagesDTO photoPackagesDTO, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles);
}

