
package com.shanlan.user.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.shanlan.common.constant.ConstantString;
import com.shanlan.common.exception.business.ParameterInvalidException;
import com.shanlan.common.util.ImageUploadUtil;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.service.PhotoService;
import com.shanlan.user.application.dto.UserBaseDTO;
import com.shanlan.user.core.domain.UserBase;
import com.shanlan.user.core.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.user.application.UserDetailApplication;
import com.shanlan.user.application.dto.UserDetailDTO;
import com.shanlan.user.core.domain.UserDetail;

@Named
@Transactional
public class UserDetailApplicationImpl implements UserDetailApplication {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailApplicationImpl.class);

    @Inject
    private StringRedisTemplate stringRedisTemplate;

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
            logger.error(e.getMessage(), e);
        }
        userDetail.save();
        userDetailDTO.setId((Integer) userDetail.getId());
        return userDetailDTO;
    }


    @Override
    public boolean saveDefaultUser(String userName, String email) {

        UserDetail userDetail = new UserDetail(userName,
                userName, email,
                UserDetail.Type.COMMON.name(), UserDetail.Gender.SECRECY.ordinal());
        userDetail.save();
        Integer newUserDetailId = userDetail.getId();
        if (0 != newUserDetailId) {
            return true;
        }
        return false;
    }

    public void updateUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = UserDetail.get(UserDetail.class, userDetailDTO.getId());
        // 设置要更新的值
        try {
            String newEmail = userDetailDTO.getEmail();
            if (newEmail != null && !newEmail.equals(userDetail.getEmail())) {//如果更新了Email,需要同步更新KS_IDENTITY表中的Email
                UserBase userBase = UserBase.getByUserName(userDetail.getUserName());
                userBase.setEmail(newEmail);
                userBase.save();
            }
            BeanUtils.copyProperties(userDetail, userDetailDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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


    public boolean register(UserBaseDTO userBaseDTO) throws Exception {
        UserBase userBase = new UserBase();
        BeanUtils.copyProperties(userBase, userBaseDTO);
        return UserService.register(userBase);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetailDTO isLogin(String sessionId) throws Exception {
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        UserDetail userDetail = UserDetail.getFromCache(sessionId);
        if (userDetail == null) {
            throw new ParameterInvalidException("该用户尚未登录，请先登录");
        }
        BeanUtils.copyProperties(userDetailDTO, userDetail);
        return userDetailDTO;
    }


    public String handleAvatar(int x, int y, int srcShowWidth, int srcShowHeight, String userName, String sessionId) throws Exception {

        UserDetail userDetail = UserDetail.get(userName);

        String srcImageFilePath = userDetail.getPhotoPath();

        srcImageFilePath = srcImageFilePath != null ? srcImageFilePath.replace(ImageUploadUtil.IMAGE_SIZE_PLACEHOLDER, "") : "";

        String completeSrcImageFilePath = ImageUploadUtil.getImageBasePath() + srcImageFilePath;

        String storeFilePath = PhotoService.handleAvatar(completeSrcImageFilePath, x, y, srcShowWidth, srcShowHeight);

        if (StringUtils.isNotBlank(storeFilePath)) {
            logger.info(storeFilePath);
            Photo.updateFilePath(userDetail.getPhotoId(), storeFilePath);
            //前面一步成功后再更新用户数据库和缓存
            userDetail.setPhotoPath(storeFilePath);
            UserService.updateDateBaseAndCache(ConstantString.REDIS_KEY_PREFIX_SESSION + sessionId, userDetail);
            return storeFilePath;
        }
        return null;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetailDTO login(String userAccount, String password, String sessionId) throws Exception {
        UserDetail userDetail = UserService.login(userAccount, password);
        if (userDetail != null) {
//            UserDetail.saveInCache(ConstantString.REDIS_KEY_PREFIX_SESSION + sessionId, userDetail);
            UserDetailDTO userDetailDTO = new UserDetailDTO();
            BeanUtils.copyProperties(userDetailDTO, userDetail);
            return userDetailDTO;
        }
        return null;
    }


}
