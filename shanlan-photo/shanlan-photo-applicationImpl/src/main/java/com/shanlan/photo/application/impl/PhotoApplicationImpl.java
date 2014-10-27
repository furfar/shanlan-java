package com.shanlan.photo.application.impl;

import java.io.File;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;

import javax.inject.Named;

import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.EntityUtil;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.trade.core.domain.ReTradePhoto;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.RePhotoUserOwn;

@Named
@Transactional
public class PhotoApplicationImpl implements PhotoApplication {

    private static final Logger logger = LoggerFactory.getLogger(PhotoApplicationImpl.class);

    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(
                    QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PhotoDTO getPhoto(Integer id) {
        Photo photo = Photo.load(Photo.class, id);
        PhotoDTO photoDTO = new PhotoDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(photoDTO, photo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photoDTO.setId((Integer) photo.getId());
        return photoDTO;
    }

    public PhotoDTO savePhoto(PhotoDTO photoDTO) {
        Photo photo = new Photo();
        try {
            BeanUtils.copyProperties(photo, photoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photo.save();
        photoDTO.setId((Integer) photo.getId());
        return photoDTO;
    }

    public void updatePhoto(PhotoDTO photoDTO) {
        Photo photo = Photo.get(Photo.class, photoDTO.getId());

        UserBase userBase = new UserBase("anem", "pass", "emailsdf");
        try {
            UserService.register(userBase);
        } catch (RequestParameterException e) {
            e.printStackTrace();
        }

        // 设置要更新的值
        try {
            BeanUtils.copyProperties(photo, photoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePhoto(Integer id) {
        this.removePhotos(new Integer[]{id});
    }

    public void removePhotos(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Photo photo = Photo.load(Photo.class, ids[i]);
            photo.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoDTO> findAllPhoto() {
        List<PhotoDTO> list = new ArrayList<PhotoDTO>();
        List<Photo> all = Photo.findAll(Photo.class);
        for (Photo photo : all) {
            PhotoDTO photoDTO = new PhotoDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoDTO, photo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(photoDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PhotoDTO> pageQueryPhoto(PhotoDTO queryVo, int currentPage,
                                         int pageSize) {
        List<PhotoDTO> result = new ArrayList<PhotoDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder(
                "select _photo from Photo _photo   where 1=1 ");

        if (queryVo.getFilePath() != null && !"".equals(queryVo.getFilePath())) {
            jpql.append(" and _photo.filePath like ?");
            conditionVals.add(MessageFormat.format("%{0}%",
                    queryVo.getFilePath()));
        }

        if (queryVo.getSize() != null) {
            jpql.append(" and _photo.size=?");
            conditionVals.add(queryVo.getSize());
        }

        if (queryVo.getLikeCount() != null) {
            jpql.append(" and _photo.likeCount=?");
            conditionVals.add(queryVo.getLikeCount());
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _photo.other like ?");
            conditionVals
                    .add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<Photo> pages = getQueryChannelService()
                .createJpqlQuery(jpql.toString()).setParameters(conditionVals)
                .setPage(currentPage, pageSize).pagedList();
        for (Photo photo : pages.getData()) {
            PhotoDTO photoDTO = new PhotoDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoDTO, photo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(photoDTO);
        }
        return new Page<PhotoDTO>(pages.getStart(), pages.getResultCount(),
                pageSize, result);
    }

    @Override
    public List<PhotoDTO> listTradePhotos(int tradePhotoCollectionId) throws Exception {
        List<PhotoDTO> tradePhotoDTOs = new ArrayList<PhotoDTO>();
        if (tradePhotoCollectionId > 0) {
            List<ReTradePhoto> reTradePhotoList = ReTradePhoto.listByTpcIds(Collections.singletonList(tradePhotoCollectionId));
            List<Integer> reTradePhotoIdList = EntityUtil.getIds(reTradePhotoList);
            List<RePhotoUserOwn> rePhotoUserOwns = RePhotoUserOwn.listPublic(reTradePhotoIdList);
            List<Integer> photoIds = RePhotoUserOwn.listPhotoIds(rePhotoUserOwns);
            List<Photo> photos = Photo.list(photoIds);
            for (Photo photo : photos) {
                PhotoDTO photoDTO = new PhotoDTO();
                BeanUtils.copyProperties(photoDTO, photo);
                tradePhotoDTOs.add(photoDTO);
            }
        }
        return tradePhotoDTOs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotoDTO> listByMd5(List<String> imageMd5s) throws Exception {
        List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();
        List<Photo> photos = Photo.listByMd5(imageMd5s);
        if (photos != null && photos.size() > 0) {
            for (Photo photo : photos) {
                PhotoDTO photoDTO = new PhotoDTO();
                BeanUtils.copyProperties(photoDTO, photo);
                photoDTOs.add(photoDTO);
            }
        }
        return photoDTOs;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, PhotoDTO> getMd5AndSelfMap(List<String> imageMd5s) throws Exception {
        Map<String, PhotoDTO> md5AndPhotoDTOMap = new HashMap<String, PhotoDTO>();
        Map<String, Photo> md5AndSelfMap = Photo.getMd5AndSelfMap(imageMd5s);
        for (Map.Entry<String, Photo> entry : md5AndSelfMap.entrySet()) {
            PhotoDTO photoDTO = new PhotoDTO();
            BeanUtils.copyProperties(photoDTO, entry.getValue());
            md5AndPhotoDTOMap.put(entry.getKey(), photoDTO);
        }
        return md5AndPhotoDTOMap;
    }





}
