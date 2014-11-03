package com.shanlan.photo.application.impl;

import com.shanlan.common.constant.ConstantNumber;
import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.common.util.DateUtil;
import com.shanlan.common.util.EncryptUtil;
import com.shanlan.common.util.EntityUtil;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.application.PhotoApplication;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.RePhotoCollectionPhoto;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import com.shanlan.photo.core.service.PhotoService;
import com.shanlan.trade.core.domain.ReTradePhoto;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.text.MessageFormat;
import java.util.*;

@Named
@Transactional
public class PhotoApplicationImpl implements PhotoApplication {

    private static final Logger logger = LoggerFactory
            .getLogger(PhotoApplicationImpl.class);

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
    public List<PhotoDTO> listTradePhotos(int tradePhotoCollectionId)
            throws Exception {
        List<PhotoDTO> tradePhotoDTOs = new ArrayList<PhotoDTO>();
        if (tradePhotoCollectionId > 0) {
            List<ReTradePhoto> reTradePhotoList = ReTradePhoto
                    .listByTpcIds(Collections
                            .singletonList(tradePhotoCollectionId));
            List<Integer> reTradePhotoIdList = EntityUtil
                    .getIds(reTradePhotoList);
            List<RePhotoUserOwn> rePhotoUserOwns = RePhotoUserOwn
                    .listPublic(reTradePhotoIdList);
            List<Integer> photoIds = RePhotoUserOwn
                    .listPhotoIds(rePhotoUserOwns);
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
    public Map<String, PhotoDTO> getMd5AndSelfMap(List<String> imageMd5s)
            throws Exception {
        Map<String, PhotoDTO> md5AndPhotoDTOMap = new HashMap<String, PhotoDTO>();
        Map<String, Photo> md5AndSelfMap = Photo.getMd5AndSelfMap(imageMd5s);
        for (Map.Entry<String, Photo> entry : md5AndSelfMap.entrySet()) {
            PhotoDTO photoDTO = new PhotoDTO();
            BeanUtils.copyProperties(photoDTO, entry.getValue());
            md5AndPhotoDTOMap.put(entry.getKey(), photoDTO);
        }
        return md5AndPhotoDTOMap;
    }

    @Override
    public String uploadPhoto(String originalFileName, byte[] bytes,
                              String contentType, String userName, Integer photoCollectionId,
                              long fileSize) throws Exception {
        Integer photoId = null;
        String photoPath = "";
        String imageMd5 = EncryptUtil.getMD5DigestInBytes(bytes);
        // 通过md5值查询该图片是否已经上传过，如果已经上传过，则直接返回存储路径。
        Map<String, Photo> md5AndSelfMap = Photo.getMd5AndSelfMap(Collections
                .singletonList(imageMd5));
        Photo photo = md5AndSelfMap.get(imageMd5);
        if (photo != null) {
            photoId = photo.getId();
            photoPath = photo.getFilePath();
            logger.info("Find a exist Photo id:" + photoId + " PhotoPath:" + photoPath);
        } else {
            // 上传原始文件
            String originalStorePath = ImageUploadUtil.saveSelfUploadImage(
                    originalFileName, bytes);
            String extensionName = FilenameUtils.getExtension(originalFileName);

            // 按文件大小压缩图片质量（不压缩图片大小）
            PhotoService.compressImageByFileSize(originalStorePath, fileSize);

            // 生成一个600*600的缩略图
            Integer destLength = 600;
            String thumbnailFilePath = ImageUploadUtil
                    .appendImageThumbnailPostfix(originalStorePath,
                            extensionName, destLength, destLength);
            PhotoService.createSquareThumbnail(originalStorePath,
                    thumbnailFilePath, destLength);

            // 将原始存储地址加一个占位符
            photoPath = ImageUploadUtil.appendImageSizePlaceHolder(
                    originalStorePath,
                    FilenameUtils.getExtension(originalFileName));
            photoPath = ImageUploadUtil.removeImageBasePath(photoPath);
            Photo newPhoto = new Photo(photoPath, imageMd5);
            newPhoto.save();
            photoId = newPhoto.getId();
        }

        // 往re_photo_user_own表中添加记录
        RePhotoUserOwn rePhotoUserOwn = new RePhotoUserOwn();
        rePhotoUserOwn.setPhotoId(photoId);
        rePhotoUserOwn.setPhotoPath(photoPath);
        rePhotoUserOwn.setUserName(userName);
        rePhotoUserOwn.setCreatedAt(DateUtil
                .getNow(DateUtil.format_yyyyMMdd_HHmmss));
        rePhotoUserOwn.setVisibility(RePhotoUserOwn.Visibility.PRIVATE
                .ordinal());
        rePhotoUserOwn.save();

        // 关联re_photo_user_own表与re_photo_collection_photo表
        RePhotoCollectionPhoto rePhotoCollectionPhoto = new RePhotoCollectionPhoto(
                rePhotoUserOwn.getId(), photoCollectionId);
        rePhotoCollectionPhoto.save();

        return photoPath;
    }

}
