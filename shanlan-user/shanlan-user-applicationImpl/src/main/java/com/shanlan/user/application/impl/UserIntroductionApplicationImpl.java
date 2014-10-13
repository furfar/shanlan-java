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

import com.shanlan.user.application.UserIntroductionApplication;
import com.shanlan.user.application.dto.UserIntroductionDTO;
import com.shanlan.user.core.domain.UserIntroduction;

@Named
@Transactional
public class UserIntroductionApplicationImpl implements
		UserIntroductionApplication {

	private QueryChannelService queryChannel;

	private QueryChannelService getQueryChannelService() {
		if (queryChannel == null) {
			queryChannel = InstanceFactory.getInstance(
					QueryChannelService.class, "queryChannel");
		}
		return queryChannel;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public UserIntroductionDTO getUserIntroduction(Integer id) {
		UserIntroduction userIntroduction = UserIntroduction.load(
				UserIntroduction.class, id);
		UserIntroductionDTO userIntroductionDTO = new UserIntroductionDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(userIntroductionDTO, userIntroduction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userIntroductionDTO.setId((Integer) userIntroduction.getId());
		return userIntroductionDTO;
	}

	public UserIntroductionDTO saveUserIntroduction(
			UserIntroductionDTO userIntroductionDTO) {
		UserIntroduction userIntroduction = new UserIntroduction();
		try {
			BeanUtils.copyProperties(userIntroduction, userIntroductionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userIntroduction.save();
		userIntroductionDTO.setId((Integer) userIntroduction.getId());
		return userIntroductionDTO;
	}

	public void updateUserIntroduction(UserIntroductionDTO userIntroductionDTO) {
		UserIntroduction userIntroduction = UserIntroduction.get(
				UserIntroduction.class, userIntroductionDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(userIntroduction, userIntroductionDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeUserIntroduction(Integer id) {
		this.removeUserIntroductions(new Integer[] { id });
	}

	public void removeUserIntroductions(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			UserIntroduction userIntroduction = UserIntroduction.load(
					UserIntroduction.class, ids[i]);
			userIntroduction.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserIntroductionDTO> findAllUserIntroduction() {
		List<UserIntroductionDTO> list = new ArrayList<UserIntroductionDTO>();
		List<UserIntroduction> all = UserIntroduction
				.findAll(UserIntroduction.class);
		for (UserIntroduction userIntroduction : all) {
			UserIntroductionDTO userIntroductionDTO = new UserIntroductionDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(userIntroductionDTO, userIntroduction);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(userIntroductionDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<UserIntroductionDTO> pageQueryUserIntroduction(
			UserIntroductionDTO queryVo, int currentPage, int pageSize) {
		List<UserIntroductionDTO> result = new ArrayList<UserIntroductionDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				"select _userIntroduction from UserIntroduction _userIntroduction   where 1=1 ");

		if (queryVo.getUserName() != null && !"".equals(queryVo.getUserName())) {
			jpql.append(" and _userIntroduction.userName like ?");
			conditionVals.add(MessageFormat.format("%{0}%",
					queryVo.getUserName()));
		}

		if (queryVo.getTitle() != null && !"".equals(queryVo.getTitle())) {
			jpql.append(" and _userIntroduction.title like ?");
			conditionVals
					.add(MessageFormat.format("%{0}%", queryVo.getTitle()));
		}

		if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
			jpql.append(" and _userIntroduction.content like ?");
			conditionVals.add(MessageFormat.format("%{0}%",
					queryVo.getContent()));
		}
		if (queryVo.getSequence() != null) {
			jpql.append(" and _userIntroduction.sequence=?");
			conditionVals.add(queryVo.getSequence());
		}

		Page<UserIntroduction> pages = getQueryChannelService()
				.createJpqlQuery(jpql.toString()).setParameters(conditionVals)
				.setPage(currentPage, pageSize).pagedList();
		for (UserIntroduction userIntroduction : pages.getData()) {
			UserIntroductionDTO userIntroductionDTO = new UserIntroductionDTO();

			// 将domain转成VO
			try {
				BeanUtils.copyProperties(userIntroductionDTO, userIntroduction);
			} catch (Exception e) {
				e.printStackTrace();
			}

			result.add(userIntroductionDTO);
		}
		return new Page<UserIntroductionDTO>(pages.getStart(),
				pages.getResultCount(), pageSize, result);
	}

}
