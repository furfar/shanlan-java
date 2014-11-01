
package com.shanlan.user.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.shanlan.user.core.domain.UserDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.user.application.PhotographerApplication;
import com.shanlan.user.application.dto.PhotographerDTO;
import com.shanlan.user.core.domain.Photographer;

@Named
@Transactional
public class PhotographerApplicationImpl implements PhotographerApplication {


    private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService() {
        if (queryChannel == null) {
            queryChannel = InstanceFactory.getInstance(QueryChannelService.class, "queryChannel");
        }
        return queryChannel;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PhotographerDTO getPhotographer(Integer id) {
        UserDetail userDetail=UserDetail.get(UserDetail.class,id);
        Photographer photographer = Photographer.get(userDetail.getUserName());
        PhotographerDTO photographerDTO = new PhotographerDTO();
        // 将domain转成VO
        try {
            BeanUtils.copyProperties(photographerDTO, photographer);
            BeanUtils.copyProperties(photographerDTO,userDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photographerDTO.setId((Integer) photographer.getId());
        return photographerDTO;
    }

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
//    public PhotographerDTO getPhotographer(String userName) {
//        Photographer photographer = Photographer.get(userName);
//        UserDetail userDetail = UserDetail.get(photographer.getUserName());
//        PhotographerDTO photographerDTO = new PhotographerDTO();
//        // 将domain转成VO
//        try {
//            BeanUtils.copyProperties(photographerDTO, photographer);
//            BeanUtils.copyProperties(photographerDTO, userDetail);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        photographerDTO.setId((Integer) photographer.getId());
//        return photographerDTO;
//    }


    public PhotographerDTO savePhotographer(PhotographerDTO photographerDTO) {
        Photographer photographer = new Photographer();
        try {
            BeanUtils.copyProperties(photographer, photographerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        photographer.save();
        photographerDTO.setId((Integer) photographer.getId());
        return photographerDTO;
    }

    public void updatePhotographer(PhotographerDTO photographerDTO) {
        Photographer photographer = Photographer.get(Photographer.class, photographerDTO.getId());
        // 设置要更新的值
        try {
            BeanUtils.copyProperties(photographer, photographerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePhotographer(Integer id) {
        this.removePhotographers(new Integer[]{id});
    }

    public void removePhotographers(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Photographer photographer = Photographer.load(Photographer.class, ids[i]);
            photographer.remove();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PhotographerDTO> findAllPhotographer() {
        List<PhotographerDTO> list = new ArrayList<PhotographerDTO>();
        List<Photographer> all = Photographer.findAll(Photographer.class);
        for (Photographer photographer : all) {
            PhotographerDTO photographerDTO = new PhotographerDTO();
            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photographerDTO, photographer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(photographerDTO);
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PhotographerDTO> pageQueryPhotographer(PhotographerDTO queryVo, int currentPage, int pageSize) {
        List<PhotographerDTO> result = new ArrayList<PhotographerDTO>();
        List<Object> conditionVals = new ArrayList<Object>();
        StringBuilder jpql = new StringBuilder("select _photographer from Photographer _photographer   where 1=1 ");


        if (queryVo.getUserName() != null && !"".equals(queryVo.getUserName())) {
            jpql.append(" and _photographer.userName like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUserName()));
        }
        if (queryVo.getEvaluationRank() != null) {
            jpql.append(" and _photographer.evaluationRank=?");
            conditionVals.add(queryVo.getEvaluationRank());
        }

        if (queryVo.getEvaluationTimes() != null) {
            jpql.append(" and _photographer.evaluationTimes=?");
            conditionVals.add(queryVo.getEvaluationTimes());
        }


        if (queryVo.getServiceStatus() != null) {
            jpql.append(" and _photographer.serviceStatus is ?");
            conditionVals.add(queryVo.getServiceStatus());
        }

        if (queryVo.getOther() != null && !"".equals(queryVo.getOther())) {
            jpql.append(" and _photographer.other like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getOther()));
        }

        if (queryVo.getVerifyType() != null && !"".equals(queryVo.getVerifyType())) {
            jpql.append(" and _photographer.verifyType like ?");
            conditionVals.add(MessageFormat.format("%{0}%", queryVo.getVerifyType()));
        }
        Page<Photographer> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (Photographer photographer : pages.getData()) {
            PhotographerDTO photographerDTO = new PhotographerDTO();

            // 将domain转成VO
            try {
                BeanUtils.copyProperties(photographerDTO, photographer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            result.add(photographerDTO);
        }
        return new Page<PhotographerDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
    }


}
