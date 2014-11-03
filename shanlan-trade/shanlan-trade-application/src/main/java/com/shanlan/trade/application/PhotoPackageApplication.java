
package com.shanlan.trade.application;

import java.util.List;

import com.shanlan.trade.application.dto.GoodsDTO;
import com.shanlan.trade.application.dto.PhotoPackageDTO;
import org.dayatang.querychannel.Page;

public interface PhotoPackageApplication {

    public PhotoPackageDTO getPhotoPackage(Integer id);

    public PhotoPackageDTO savePhotoPackage(PhotoPackageDTO photoPackages);

    public void updatePhotoPackage(PhotoPackageDTO photoPackages);

    public void removePhotoPackage(Integer id);

    public void removePhotoPackages(Integer[] ids);

    public List<PhotoPackageDTO> findAllPhotoPackages();

    public Page<PhotoPackageDTO> pageQueryPhotoPackages(PhotoPackageDTO photoPackages, int currentPage, int pageSize);

    List<PhotoPackageDTO> listPackages(String userName, String photoType) throws Exception;


    Page<GoodsDTO> pageQueryPhotoPackages(GoodsDTO photoPackagesDTO, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles);
}

