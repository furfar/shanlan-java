
package com.shanlan.photo.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.shanlan.common.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.photo.application.RePhotoUserOwnApplication;
import com.shanlan.photo.application.dto.RePhotoUserOwnDTO;
import com.shanlan.photo.core.domain.RePhotoUserOwn;

@Named
@Transactional
public class RePhotoUserOwnApplicationImpl implements RePhotoUserOwnApplication {


    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public RePhotoUserOwnDTO getRePhotoUserOwn(Integer id) {
        RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn.load(RePhotoUserOwn.class, id);
        RePhotoUserOwnDTO rePhotoUserOwnDTO = new RePhotoUserOwnDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(rePhotoUserOwnDTO, rePhotoUserOwn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rePhotoUserOwnDTO.setId((Integer) rePhotoUserOwn.getId());
        return rePhotoUserOwnDTO;
    }

    public RePhotoUserOwnDTO saveRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwnDTO) {
        RePhotoUserOwn rePhotoUserOwn = new RePhotoUserOwn();
        try {
            BeanUtils.copyProperties(rePhotoUserOwn, rePhotoUserOwnDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rePhotoUserOwn.save();
        rePhotoUserOwnDTO.setId((Integer) rePhotoUserOwn.getId());
        return rePhotoUserOwnDTO;
    }

    public void updateRePhotoUserOwn(RePhotoUserOwnDTO rePhotoUserOwnDTO) {
        rePhotoUserOwnDTO.setUpdatedAt(DateUtil.getNow(DateUtil.format_yyyyMMdd_HHmmss));
        RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn.get(RePhotoUserOwn.class, rePhotoUserOwnDTO.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(rePhotoUserOwn, rePhotoUserOwnDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeRePhotoUserOwn(Integer id) {
        this.removeRePhotoUserOwns(new Integer[]{id});
    }

    public void removeRePhotoUserOwns(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            RePhotoUserOwn rePhotoUserOwn = RePhotoUserOwn.load(RePhotoUserOwn.class, ids[i]);
            rePhotoUserOwn.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RePhotoUserOwnDTO> findAllRePhotoUserOwn() {
        List<RePhotoUserOwnDTO> list = new ArrayList<RePhotoUserOwnDTO>();
        List<RePhotoUserOwn> all = RePhotoUserOwn.findAll(RePhotoUserOwn.class);
        for (RePhotoUserOwn rePhotoUserOwn : all) {
            RePhotoUserOwnDTO rePhotoUserOwnDTO = new RePhotoUserOwnDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(rePhotoUserOwnDTO, rePhotoUserOwn);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(rePhotoUserOwnDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<RePhotoUserOwnDTO> pageQueryRePhotoUserOwn(RePhotoUserOwnDTO queryVo, int currentPage, int pageSize) {
        List<RePhotoUserOwnDTO> result = new ArrayList<RePhotoUserOwnDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _rePhotoUserOwn from RePhotoUserOwn _rePhotoUserOwn   where 1=1 ");


        if (queryVo.getUserName() != null && !"".equals(queryVo.getUserName())) {
            jpql.append(" and _rePhotoUserOwn.userName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUserName()));
        }
        if (queryVo.getPhotoId() != null) {
            jpql.append(" and _rePhotoUserOwn.photoId=?");
            conditionVals.add(queryVo.getPhotoId());
        }


        if (queryVo.getPhotoPath() != null && !"".equals(queryVo.getPhotoPath())) {
            jpql.append(" and _rePhotoUserOwn.photoPath like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPhotoPath()));
        }

        if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
            jpql.append(" and _rePhotoUserOwn.title like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
        }

        if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
            jpql.append(" and _rePhotoUserOwn.description like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
        }


        if (queryVo.getCreatedAt() != null && !"".equals(queryVo.getCreatedAt())) {
            jpql.append(" and _rePhotoUserOwn.createdAt like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreatedAt()));
        }

        if (queryVo.getUpdatedAt() != null && !"".equals(queryVo.getUpdatedAt())) {
            jpql.append(" and _rePhotoUserOwn.updatedAt like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUpdatedAt()));
        }
        Page<RePhotoUserOwn> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (RePhotoUserOwn rePhotoUserOwn : pages.getData()) {
            RePhotoUserOwnDTO rePhotoUserOwnDTO = new RePhotoUserOwnDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(rePhotoUserOwnDTO, rePhotoUserOwn);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(rePhotoUserOwnDTO);
        }
        return new Page<RePhotoUserOwnDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }

    @Override
    public Page<RePhotoUserOwnDTO> pageQueryRePhotoUserOwn(RePhotoUserOwnDTO queryVo, int currentPage, int pageSize, String userName, boolean isSuper, List<String> roles) {

        List<RePhotoUserOwnDTO> result = new ArrayList<RePhotoUserOwnDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _rePhotoUserOwn from RePhotoUserOwn _rePhotoUserOwn   where 1=1 ");

        if (isSuper || roles.contains("Admin")) {

            if (queryVo.getUserName() != null && !"".equals(queryVo.getUserName())) {
                jpql.append(" and _rePhotoUserOwn.userName like ?");
                conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUserName()));
            }
        } else {
            jpql.append(" and _rePhotoUserOwn.userName = ?");
            conditionVals.add(userName);
        }
        if (queryVo.getPhotoId() != null) {
            jpql.append(" and _rePhotoUserOwn.photoId=?");
            conditionVals.add(queryVo.getPhotoId());
        }


        if (queryVo.getPhotoPath() != null && !"".equals(queryVo.getPhotoPath())) {
            jpql.append(" and _rePhotoUserOwn.photoPath like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPhotoPath()));
        }

        if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
            jpql.append(" and _rePhotoUserOwn.title like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
        }

        if (queryVo.getDescription() != null && !"".equals(queryVo.getDescription())) {
            jpql.append(" and _rePhotoUserOwn.description like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDescription()));
        }


        if (queryVo.getCreatedAt() != null && !"".equals(queryVo.getCreatedAt())) {
            jpql.append(" and _rePhotoUserOwn.createdAt like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreatedAt()));
        }

        if (queryVo.getUpdatedAt() != null && !"".equals(queryVo.getUpdatedAt())) {
            jpql.append(" and _rePhotoUserOwn.updatedAt like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUpdatedAt()));
        }

        if (queryVo.getVisibility() != null) {
            jpql.append(" and _rePhotoUserOwn.visibility = ?");
            conditionVals.add(queryVo.getVisibility());
        }

        Page<RePhotoUserOwn> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (RePhotoUserOwn rePhotoUserOwn : pages.getData()) {
            RePhotoUserOwnDTO rePhotoUserOwnDTO = new RePhotoUserOwnDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(rePhotoUserOwnDTO, rePhotoUserOwn);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(rePhotoUserOwnDTO);
        }
        return new Page<RePhotoUserOwnDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }


}
