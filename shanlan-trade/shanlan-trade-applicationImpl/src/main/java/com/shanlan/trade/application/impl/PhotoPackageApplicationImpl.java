package com.shanlan.trade.application.impl;

import com.shanlan.common.util.DateUtil;
import com.shanlan.photo.core.domain.RePhotoType;
import com.shanlan.trade.application.PhotoPackageApplication;
import com.shanlan.trade.application.dto.GoodsDTO;
import com.shanlan.trade.application.dto.PhotoPackageDTO;
import com.shanlan.trade.core.domain.Goods;
import com.shanlan.trade.core.domain.PhotoPackage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@Transactional
public class PhotoPackageApplicationImpl implements PhotoPackageApplication {

    private static final Logger logger = LoggerFactory.getLogger(PhotoPackageApplicationImpl.class);

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(
                    QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PhotoPackageDTO getPhotoPackage(Integer id) {
        Goods goods = Goods.get(Goods.class, id);
        PhotoPackage photoPackage = PhotoPackage.get(goods.getId());
        PhotoPackageDTO photoPackagesDTO = new PhotoPackageDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(photoPackagesDTO, goods);
            BeanUtils.copyProperties(photoPackagesDTO, photoPackage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        photoPackagesDTO.setId(photoPackage.getId());
        return photoPackagesDTO;
    }

    public PhotoPackageDTO savePhotoPackage(PhotoPackageDTO photoPackageDTO) {
        PhotoPackage photoPackage = new PhotoPackage();
        Goods goods = new Goods();
        try {
            BeanUtils.copyProperties(goods, photoPackageDTO);
            BeanUtils.copyProperties(photoPackage, photoPackageDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        goods.save();
        photoPackage.setGoodsId(goods.getId());
        photoPackage.save();
        photoPackageDTO.setId(photoPackage.getId());
        return photoPackageDTO;
    }

    public void updatePhotoPackage(PhotoPackageDTO photoPackagesDTO) {
        photoPackagesDTO.setUpdatedAt(DateUtil.getNow(DateUtil.format_yyyyMMdd_HHmmss));
        Goods goods = Goods.get(Goods.class, photoPackagesDTO.getId());
        PhotoPackage photoPackages = PhotoPackage.get(PhotoPackage.class,
                goods.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(goods, photoPackagesDTO);
            BeanUtils.copyProperties(photoPackages, photoPackagesDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void removePhotoPackage(Integer id) {
        this.removePhotoPackages(new Integer[]{id});
    }

    @Override
    public void removePhotoPackages(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            PhotoPackage photoPackages = PhotoPackage.load(PhotoPackage.class,
                    ids[i]);
            photoPackages.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoPackageDTO> findAllPhotoPackages() {
        List<PhotoPackageDTO> list = new ArrayList<PhotoPackageDTO>();
        List<PhotoPackage> all = PhotoPackage.findAll(PhotoPackage.class);
        for (PhotoPackage photoPackages : all) {
            PhotoPackageDTO photoPackagesDTO = new PhotoPackageDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            list.add(photoPackagesDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PhotoPackageDTO> pageQueryPhotoPackages(
            PhotoPackageDTO queryVo, int currentPage, int pageSize) {
        List<PhotoPackageDTO> result = new ArrayList<PhotoPackageDTO>();
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

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _photoPackages.other like ?");
            conditionVals
                    .add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<PhotoPackage> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString()).setParameters(conditionVals)
                .setPage(currentPage, pageSize).pagedList();
        for (PhotoPackage photoPackages : pages.getData()) {
            PhotoPackageDTO photoPackagesDTO = new PhotoPackageDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            result.add(photoPackagesDTO);
        }
        return new Page<PhotoPackageDTO>(pages.getStart(),
                pages.getResultCount(), pageSize, result);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoPackageDTO> listPackages(String userName, String photoType)
            throws Exception {
        List<PhotoPackageDTO> photoPackagesDTOs = new ArrayList<PhotoPackageDTO>();

        if (StringUtils.isNotBlank(userName)
                && StringUtils.isNotBlank(photoType)) {
            List<PhotoPackage> photoPackagesList = PhotoPackage.list(userName);
            Map<Integer, RePhotoType> reIdRePhotoTypeMap = RePhotoType
                    .getMap(photoType);
            for (PhotoPackage photoPackages : photoPackagesList) {
                Integer id = photoPackages.getId();
                RePhotoType rePhotoType = reIdRePhotoTypeMap.get(id);
                if (rePhotoType != null
                        && photoType.equals(rePhotoType.getTypeName())) {
                    PhotoPackageDTO photoPackagesDTO = new PhotoPackageDTO();
                    BeanUtils.copyProperties(photoPackagesDTO, photoPackages);
                    photoPackagesDTOs.add(photoPackagesDTO);
                }
            }
        }
        return photoPackagesDTOs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<GoodsDTO> pageQueryPhotoPackages(GoodsDTO queryVo,
                                                 int currentPage, int pageSize, String userName, boolean isSuper,
                                                 List<String> roles) {
        List<GoodsDTO> result = new ArrayList<GoodsDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder(
                "select _goods from Goods _goods   where 1=1 ");

        jpql.append(" and _goods.type = ?");
        conditionVals.add(Goods.Type.PHOTO_PACKAGE.name());

        if (isSuper || roles.contains("Admin")) {
            if (queryVo.getCreator() != null
                    && !"".equals(queryVo.getCreator())) {
                jpql.append(" and _goods.creator like ?");
                conditionVals.add(MessageFormat.format("%{0}%",
                        queryVo.getCreator()));
            }
        } else {
            jpql.append(" and _goods.creator = ?");
            conditionVals.add(userName);
        }

        if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
            jpql.append(" and _goods.name like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
        }

        if (queryVo.getDescription() != null
                && !"".equals(queryVo.getDescription())) {
            jpql.append(" and _goods.description like ?");
            conditionVals.add(MessageFormat.format("%{0}%",
                    queryVo.getDescription()));
        }

        if (queryVo.getStatus() != null && !"".equals(queryVo.getStatus())) {
            jpql.append(" and _goods.status like ?");
            conditionVals
                    .add(MessageFormat.format("%{0}%", queryVo.getStatus()));
        }

        if (queryVo.getAddress() != null && !"".equals(queryVo.getAddress())) {
            jpql.append(" and _goods.address like ?");
            conditionVals.add(MessageFormat.format("%{0}%",
                    queryVo.getAddress()));
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _goods.other like ?");
            conditionVals
                    .add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<Goods> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString()).setParameters(conditionVals)
                .setPage(currentPage, pageSize).pagedList();
        for (Goods goods : pages.getData()) {
            GoodsDTO goodsDTO = new GoodsDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(goodsDTO, goods);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            result.add(goodsDTO);
        }
        return new Page<GoodsDTO>(pages.getStart(), pages.getResultCount(),
                pageSize, result);
    }

}
