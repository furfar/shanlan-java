
package com.shanlan.shanlanphoto.application.impl.photo;

import java.util.List;
import java.util.ArrayList;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import com.shanlan.shanlanphoto.application.dto.*;
import com.shanlan.shanlanphoto.application.photo.PhotoApplication;
import com.shanlan.common.domain.photo.*;

@Named
@Transactional
public class PhotoApplicationImpl implements PhotoApplication {

    private static final Logger logger = LoggerFactory.getLogger(PhotoApplicationImpl.class);
    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
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
            logger.error(e.getMessage(), e);
        }
        photoDTO.setId((Integer) photo.getId());
        return photoDTO;
    }

    public PhotoDTO savePhoto(PhotoDTO photoDTO) {
        photoDTO.setUploadedAt(new DateTime(2014, 10, 3, 11, 10, 10).toDate());
        Photo photo = new Photo();
        try {
            BeanUtils.copyProperties(photo, photoDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
                logger.error(e.getMessage(), e);
            }
            list.add(photoDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PhotoDTO> pageQueryPhoto(PhotoDTO queryVo, int currentPage, int pageSize) {
        List<PhotoDTO> result = new ArrayList<PhotoDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _photo from Photo _photo   where 1=1 ");


        if (queryVo.getFileName() != null && !"".equals(queryVo.getFileName())) {
            jpql.append(" and _photo.fileName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFileName()));
        }

        if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
            jpql.append(" and _photo.title like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
        }

        if (queryVo.getFilePath() != null && !"".equals(queryVo.getFilePath())) {
            jpql.append(" and _photo.filePath like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFilePath()));
        }
        if (queryVo.getSize() != null) {
            jpql.append(" and _photo.size=?");
            conditionVals.add(queryVo.getSize());
        }


        if (queryVo.getCreator() != null && !"".equals(queryVo.getCreator())) {
            jpql.append(" and _photo.creator like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreator()));
        }


        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _photo.other like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<Photo> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (Photo photo : pages.getData()) {
            PhotoDTO photoDTO = new PhotoDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photoDTO, photo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            result.add(photoDTO);
        }
        return new Page<PhotoDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }


}
