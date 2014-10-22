
package com.shanlan.user.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.shanlan.common.exception.sub.business.RequestParameterException;
import com.shanlan.user.application.dto.UserBaseDTO;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import com.shanlan.user.core.domain.UserDetail;

@Named
@Transactional
public class UserDetailApplicationImpl implements UserDetailApplication {


    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetailDTO getUser(Integer id) {
        UserDetail userDetail = UserDetail.load(UserDetail.class, id);
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(userDetailDTO, userDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDetailDTO.setId((Integer) userDetail.getId());
        return userDetailDTO;
    }

    public UserDetailDTO saveUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        try {
            BeanUtils.copyProperties(userDetail, userDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDetail.save();
        userDetailDTO.setId((Integer) userDetail.getId());
        return userDetailDTO;
    }

    public void updateUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = UserDetail.get(UserDetail.class, userDetailDTO.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(userDetail, userDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUser(Integer id) {
        this.removeUsers(new Integer[]{id});
    }

    public void removeUsers(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            UserDetail userDetail = UserDetail.load(UserDetail.class, ids[i]);
            userDetail.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<UserDetailDTO> findAllUser() {
        List<UserDetailDTO> list = new ArrayList<UserDetailDTO>();
        List<UserDetail> all = UserDetail.findAll(UserDetail.class);
        for (UserDetail userDetail : all) {
            UserDetailDTO userDetailDTO = new UserDetailDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(userDetailDTO, userDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(userDetailDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<UserDetailDTO> pageQueryUser(UserDetailDTO queryVo, int currentPage, int pageSize) {
        List<UserDetailDTO> result = new ArrayList<UserDetailDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _user from UserDetail _user   where 1=1 ");


        if (queryVo.getUserName() != null && !"".equals(queryVo.getUserName())) {
            jpql.append(" and _user.userName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUserName()));
        }


        if (queryVo.getRealName() != null && !"".equals(queryVo.getRealName())) {
            jpql.append(" and _user.realName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getRealName()));
        }
        if (queryVo.getPhotoId() != null) {
            jpql.append(" and _user.photoId=?");
            conditionVals.add(queryVo.getPhotoId());
        }


        if (queryVo.getPhotoPath() != null && !"".equals(queryVo.getPhotoPath())) {
            jpql.append(" and _user.photoPath like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPhotoPath()));
        }
        if (queryVo.getCityId() != null) {
            jpql.append(" and _user.cityId=?");
            conditionVals.add(queryVo.getCityId());
        }


        if (queryVo.getMobile() != null && !"".equals(queryVo.getMobile())) {
            jpql.append(" and _user.mobile like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMobile()));
        }

        if (queryVo.getQq() != null && !"".equals(queryVo.getQq())) {
            jpql.append(" and _user.qq like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getQq()));
        }

        if (queryVo.getWebchart() != null && !"".equals(queryVo.getWebchart())) {
            jpql.append(" and _user.webchart like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getWebchart()));
        }

        if (queryVo.getAlipay() != null && !"".equals(queryVo.getAlipay())) {
            jpql.append(" and _user.alipay like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAlipay()));
        }
        if (queryVo.getTradeTimes() != null) {
            jpql.append(" and _user.tradeTimes=?");
            conditionVals.add(queryVo.getTradeTimes());
        }

        if (queryVo.getActiveness() != null) {
            jpql.append(" and _user.activeness=?");
            conditionVals.add(queryVo.getActiveness());
        }

        if (queryVo.getPhotoCount() != null) {
            jpql.append(" and _user.photoCount=?");
            conditionVals.add(queryVo.getPhotoCount());
        }


        if (queryVo.getType() != null && !"".equals(queryVo.getType())) {
            jpql.append(" and _user.type like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getType()));
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _user.other like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }
        Page<UserDetail> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (UserDetail userDetail : pages.getData()) {
            UserDetailDTO userDetailDTO = new UserDetailDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(userDetailDTO, userDetail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(userDetailDTO);
        }
        return new Page<UserDetailDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }


    public boolean  register(UserBaseDTO userBaseDTO) throws Exception {
        UserBase userBase=new UserBase();
        BeanUtils.copyProperties(userBase,userBaseDTO);
        return UserService.register(userBase);
    }
}
