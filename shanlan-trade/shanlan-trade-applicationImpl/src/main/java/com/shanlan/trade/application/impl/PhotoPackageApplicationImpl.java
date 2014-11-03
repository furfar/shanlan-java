package com.shanlan.trade.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.shanlan.photo.core.domain.RePhotoType;
import com.shanlan.trade.application.PhotoPackageApplication;
import com.shanlan.trade.application.dto.GoodsDTO;
import com.shanlan.trade.core.domain.Goods;
import com.shanlan.trade.core.domain.PhotoPackage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.trade.application.dto.PhotoPackagesDTO;

@Named
@Transactional
public class PhotoPackageApplicationImpl implements PhotoPackageApplication {

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(
                    QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PhotoPackagesDTO getPhotoPackages(Integer id) {
        PhotoPackage photoPackages = PhotoPackage.load(PhotoPackage.class,
                id);
        PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoPackagesDTO.setId((Integer) photoPackages.getId());
        return photoPackagesDTO;
    }

    public PhotoPackagesDTO savePhotoPackages(PhotoPackagesDTO photoPackagesDTO) {
        PhotoPackage photoPackages = new PhotoPackage();
        try {
            BeanUtils.copyProperties(photoPackages, photoPackagesDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoPackages.save();
        photoPackagesDTO.setId((Integer) photoPackages.getId());
        return photoPackagesDTO;
    }

    public void updatePhotoPackages(PhotoPackagesDTO photoPackagesDTO) {
        PhotoPackage photoPackages = PhotoPackage.get(PhotoPackage.class,
                photoPackagesDTO.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(photoPackages, photoPackagesDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePhotoPackage(Integer id) {
        this.removePhotoPackages(new Integer[]{id});
    }

    @Override
    public void removePhotoPackages(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            PhotoPackage photoPackages = PhotoPackage.load(
                    PhotoPackage.class, ids[i]);
            photoPackages.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoPackagesDTO> findAllPhotoPackages() {
        List<PhotoPackagesDTO> list = new ArrayList<PhotoPackagesDTO>();
        List<PhotoPackage> all = PhotoPackage.findAll(PhotoPackage.class);
        for (PhotoPackage photoPackages : all) {
            PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(photoPackagesDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PhotoPackagesDTO> pageQueryPhotoPackages(
            PhotoPackagesDTO queryVo, int currentPage, int pageSize) {
        List<PhotoPackagesDTO> result = new ArrayList<PhotoPackagesDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder(
                "select _photoPackages from PhotoPackages _photoPackages   where 1=1 ");

        if (queryVo.getPhotographer() != null
                && !"".equals(queryVo.getPhotographer())) {
            jpql.append(" and _photoPackages.photographer like ?");
            conditionVals.add(MessageFormat.format("%{0}%",
                    queryVo.getPhotographer()));
        }
        if (queryVo.getDays() != null) {
            jpql.append(" and _photoPackages.days=?");
            conditionVals.add(queryVo.getDays());
        }

        if (queryVo.getPhotoCount() != null) {
            jpql.append(" and _photoPackages.photoCounts=?");
            conditionVals.add(queryVo.getPhotoCount());
        }

        if (queryVo.getAlterCount() != null) {
            jpql.append(" and _photoPackages.alterCounts=?");
            conditionVals.add(queryVo.getAlterCount());
        }

        if (queryVo.getPlaceId() != null) {
            jpql.append(" and _photoPackages.placeId=?");
            conditionVals.add(queryVo.getPlaceId());
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _photoPackages.other like ?");
            conditionVals
                    .add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<PhotoPackage> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString()).setParameters(conditionVals)
                .setPage(currentPage, pageSize).pagedList();
        for (PhotoPackage photoPackages : pages.getData()) {
            PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(photoPackagesDTO);
        }
        return new Page<PhotoPackagesDTO>(pages.getStart(),
                pages.getResultCount(), pageSize, result);
    }

    @Override
    public List<PhotoPackagesDTO> listPackages(String userName, String photoType) throws Exception {
        List<PhotoPackagesDTO> photoPackagesDTOs = new ArrayList<PhotoPackagesDTO>();

        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(photoType)) {
            List<PhotoPackage> photoPackagesList = PhotoPackage.list(userName);
            Map<Integer, RePhotoType> reIdRePhotoTypeMap = RePhotoType.getMap(photoType);
            for (PhotoPackage photoPackages : photoPackagesList) {
                Integer id = photoPackages.getId();
                RePhotoType rePhotoType = reIdRePhotoTypeMap.get(id);
                if (rePhotoType != null && photoType.equals(rePhotoType.getTypeName())) {
                    PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();
                    BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
                    photoPackagesDTOs.add(photoPackagesDTO);
                }
            }
        }
        return photoPackagesDTOs;
    }

    @Override
    public Page<PhotoPackagesDTO> pageQueryPhotoPackages(PhotoPackagesDTO queryVo, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles) {
        List<PhotoPackagesDTO> result = new ArrayList<PhotoPackagesDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _goods from Goods _goods   where 1=1 ");

        jpql.append(" and _goods.type = ?");
        conditionVals.add(Goods.Type.PHOTO_PACKAGE.name());

        if (isSuper || roles.contains("Admin")) {
            if (queryVo.getCreator() != null && !"".equals(queryVo.getCreator())) {
                jpql.append(" and _goods.creator like ?");
                conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreator()));
            }
        } else {
            jpql.append(" and _goods.creator = ?");
            conditionVals.add(userName);
        }

        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _goods.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }

        if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
            jpql.append(" and _goods.description like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
        }
        if (queryVo.getPrice() != null) {
            jpql.append(" and _goods.price=?");
            conditionVals.add(queryVo.getPrice());
        }

        if (queryVo.getStatus() != null && !"".equals(queryVo.getStatus())) {
            jpql.append(" and _goods.status like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getStatus()));
        }

        if (queryVo.getCreatedAt() != null) {
            jpql.append(" and _goods.createdAt between ? and ? ");
            conditionVals.add(queryVo.getCreatedAt());
            conditionVals.add(queryVo.getCreatedAtEnd());
        }

        if (queryVo.getUpdatedAt() != null) {
            jpql.append(" and _goods.updatedAt between ? and ? ");
            conditionVals.add(queryVo.getUpdatedAt());
            conditionVals.add(queryVo.getUpdatedAtEnd());
        }

        if (queryVo.getValidDate() != null) {
            jpql.append(" and _goods.validDate between ? and ? ");
            conditionVals.add(queryVo.getValidDate());
            conditionVals.add(queryVo.getValidDateEnd());
        }

        if (queryVo.getInvalidDate() != null) {
            jpql.append(" and _goods.invalidDate between ? and ? ");
            conditionVals.add(queryVo.getInvalidDate());
            conditionVals.add(queryVo.getInvalidDateEnd());
        }

        if (queryVo.getAddress() != null && !"".equals(queryVo.getAddress())) {
            jpql.append(" and _goods.address like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAddress()));
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _goods.other like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<Goods> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (Goods goods : pages.getData()) {
            PhotoPackagesDTO photoPackagesDTO = new PhotoPackagesDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoPackagesDTO, goods);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(photoPackagesDTO);
        }
        return new Page<PhotoPackagesDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }

}
