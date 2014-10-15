
package com.shanlan.user.application.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shanlan.user.application.UserBlogApplication;
import com.shanlan.user.application.dto.UserBlogDTO;
import com.shanlan.user.core.domain.UserBlog;

@Named
@Transactional
public class UserBlogApplicationImpl implements UserBlogApplication {


	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserBlogDTO getUserBlog(Integer id) {
		UserBlog userBlog = UserBlog.load(UserBlog.class, id);
		UserBlogDTO userBlogDTO = new UserBlogDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(userBlogDTO, userBlog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userBlogDTO.setId((Integer)userBlog.getId());
		return userBlogDTO;
	}
	
	public UserBlogDTO saveUserBlog(UserBlogDTO userBlogDTO) {
		UserBlog userBlog = new UserBlog();
		try {
        	BeanUtils.copyProperties(userBlog, userBlogDTO);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		userBlog.save();
		userBlogDTO.setId((Integer)userBlog.getId());
		return userBlogDTO;
	}
	
	public void updateUserBlog(UserBlogDTO userBlogDTO) {
		UserBlog userBlog = UserBlog.get(UserBlog.class, userBlogDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(userBlog, userBlogDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeUserBlog(Integer id) {
		this.removeUserBlogs(new Integer[] { id });
	}
	
	public void removeUserBlogs(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			UserBlog userBlog = UserBlog.load(UserBlog.class, ids[i]);
			userBlog.remove();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserBlogDTO> findAllUserBlog() {
		List<UserBlogDTO> list = new ArrayList<UserBlogDTO>();
		List<UserBlog> all = UserBlog.findAll(UserBlog.class);
		for (UserBlog userBlog : all) {
			UserBlogDTO userBlogDTO = new UserBlogDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(userBlogDTO, userBlog);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(userBlogDTO);
		}
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<UserBlogDTO> pageQueryUserBlog(UserBlogDTO queryVo, int currentPage, int pageSize) {
		List<UserBlogDTO> result = new ArrayList<UserBlogDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _userBlog from UserBlog _userBlog   where 1=1 ");
	
	
	   	if (queryVo.getCreator() != null && !"".equals(queryVo.getCreator())) {
	   		jpql.append(" and _userBlog.creator like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getCreator()));
	   	}		
	
	   	if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
	   		jpql.append(" and _userBlog.title like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
	   	}		
	
	   	if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
	   		jpql.append(" and _userBlog.content like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getContent()));
	   	}		
	
	
	   	if (queryVo.getViewTimes() != null) {
	   		jpql.append(" and _userBlog.viewTimes=?");
	   		conditionVals.add(queryVo.getViewTimes());
	   	}	
	
	
	   	if (queryVo.getStatus() != null) {
		   	jpql.append(" and _userBlog.status is ?");
		   	conditionVals.add(queryVo.getStatus());
	   	}	
        Page<UserBlog> pages = getQueryChannelService().createJpqlQuery(jpql.toString()).setParameters(conditionVals).setPage(currentPage, pageSize).pagedList();
        for (UserBlog userBlog : pages.getData()) {
            UserBlogDTO userBlogDTO = new UserBlogDTO();
            
             // 将domain转成VO
            try {
            	BeanUtils.copyProperties(userBlogDTO, userBlog);
            } catch (Exception e) {
            	e.printStackTrace();
            } 
            
                                                                                                                           result.add(userBlogDTO);
        }
        return new Page<UserBlogDTO>(pages.getStart(), pages.getResultCount(), pageSize, result);
	}
	
	
}
