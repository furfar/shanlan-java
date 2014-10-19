package com.shanlan.trade.application.impl;

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

import com.shanlan.common.util.DateUtil;
import com.shanlan.common.util.JsonUtil;
import com.shanlan.photo.application.dto.PhotoDTO;
import com.shanlan.trade.application.TradeCommentApplication;
import com.shanlan.trade.application.dto.FrontTradeCommentDTO;
import com.shanlan.trade.application.dto.TradeCommentDTO;
import com.shanlan.trade.core.domain.TradeComment;

@Named
@Transactional
public class TradeCommentApplicationImpl implements TradeCommentApplication {

	private QueryChannelService queryChannel;

	private QueryChannelService getQueryChannelService() {
		if (queryChannel == null) {
			queryChannel = InstanceFactory.getInstance(
					QueryChannelService.class, "queryChannel");
		}
		return queryChannel;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TradeCommentDTO getTradeComment(Integer id) {
		TradeComment tradeComment = TradeComment.load(TradeComment.class, id);
		TradeCommentDTO tradeCommentDTO = new TradeCommentDTO();
		// 将domain转成VO
		try {
			BeanUtils.copyProperties(tradeCommentDTO, tradeComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tradeCommentDTO.setId((Integer) tradeComment.getId());
		return tradeCommentDTO;
	}

	public TradeCommentDTO saveTradeComment(TradeCommentDTO tradeCommentDTO) {
		TradeComment tradeComment = new TradeComment();
		try {
			BeanUtils.copyProperties(tradeComment, tradeCommentDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tradeComment.save();
		tradeCommentDTO.setId((Integer) tradeComment.getId());
		return tradeCommentDTO;
	}

	public void updateTradeComment(TradeCommentDTO tradeCommentDTO) {
		TradeComment tradeComment = TradeComment.get(TradeComment.class,
				tradeCommentDTO.getId());
		// 设置要更新的值
		try {
			BeanUtils.copyProperties(tradeComment, tradeCommentDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeTradeComment(Integer id) {
		this.removeTradeComments(new Integer[] { id });
	}

	public void removeTradeComments(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			TradeComment tradeComment = TradeComment.load(TradeComment.class,
					ids[i]);
			tradeComment.remove();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TradeCommentDTO> findAllTradeComment() {
		List<TradeCommentDTO> list = new ArrayList<TradeCommentDTO>();
		List<TradeComment> all = TradeComment.findAll(TradeComment.class);
		for (TradeComment tradeComment : all) {
			TradeCommentDTO tradeCommentDTO = new TradeCommentDTO();
			// 将domain转成VO
			try {
				BeanUtils.copyProperties(tradeCommentDTO, tradeComment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.add(tradeCommentDTO);
		}
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<TradeCommentDTO> pageQueryTradeComment(TradeCommentDTO queryVo,
			int currentPage, int pageSize) {
		List<TradeCommentDTO> result = new ArrayList<TradeCommentDTO>();
		List<Object> conditionVals = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder(
				"select _tradeComment from TradeComment _tradeComment   where 1=1 ");

		if (queryVo.getOrderId() != null) {
			jpql.append(" and _tradeComment.orderId=?");
			conditionVals.add(queryVo.getOrderId());
		}

		if (queryVo.getRater() != null && !"".equals(queryVo.getRater())) {
			jpql.append(" and _tradeComment.rater like ?");
			conditionVals
					.add(MessageFormat.format("%{0}%", queryVo.getRater()));
		}

		if (queryVo.getRatee() != null && !"".equals(queryVo.getRatee())) {
			jpql.append(" and _tradeComment.ratee like ?");
			conditionVals
					.add(MessageFormat.format("%{0}%", queryVo.getRatee()));
		}

		if (queryVo.getContent() != null && !"".equals(queryVo.getContent())) {
			jpql.append(" and _tradeComment.content like ?");
			conditionVals.add(MessageFormat.format("%{0}%",
					queryVo.getContent()));
		}

		if (queryVo.getExplanation() != null
				&& !"".equals(queryVo.getExplanation())) {
			jpql.append(" and _tradeComment.explanation like ?");
			conditionVals.add(MessageFormat.format("%{0}%",
					queryVo.getExplanation()));
		}

		if (queryVo.getType() != null && !"".equals(queryVo.getType())) {
			jpql.append(" and _tradeComment.type like ?");
			conditionVals.add(MessageFormat.format("%{0}%", queryVo.getType()));
		}

		if (queryVo.getCratedAt() != null) {
			jpql.append(" and _tradeComment.cratedAt between ? and ? ");
			conditionVals.add(queryVo.getCratedAt());
			conditionVals.add(queryVo.getCratedAtEnd());
		}
		Page<TradeComment> pages = getQueryChannelService()
				.createJpqlQuery(jpql.toString()).setParameters(conditionVals)
				.setPage(currentPage, pageSize).pagedList();
		for (TradeComment tradeComment : pages.getData()) {
			TradeCommentDTO tradeCommentDTO = new TradeCommentDTO();

			// 将domain转成VO
			try {
				BeanUtils.copyProperties(tradeCommentDTO, tradeComment);
			} catch (Exception e) {
				e.printStackTrace();
			}

			result.add(tradeCommentDTO);
		}
		return new Page<TradeCommentDTO>(pages.getStart(),
				pages.getResultCount(), pageSize, result);
	}

	@Override
	public Page<FrontTradeCommentDTO> pageTradeComment(String sellerUserName,
			int currentPage, int pageSize) {
		Page<FrontTradeCommentDTO> frontTradeComments = new Page<FrontTradeCommentDTO>(
				0, 20, 2, new ArrayList<FrontTradeCommentDTO>());
		return frontTradeComments;
	}

	public static void main(String[] args) {

		List<PhotoDTO> photoDTOs = new ArrayList<PhotoDTO>();
		PhotoDTO photoDTO1 = new PhotoDTO(1,
				"http//static.jspass.com/static/css/profile/images/trade-1-0.png");
		PhotoDTO photoDTO2 = new PhotoDTO(2,
				"http//static.jspass.com/static/css/profile/images/trade-2-0.png");
		photoDTOs.add(photoDTO1);
		photoDTOs.add(photoDTO2);

		FrontTradeCommentDTO frontTradeCommentDTO = new FrontTradeCommentDTO();
		frontTradeCommentDTO.setContent("每次情感泛滥的时候都会语无伦次，这次也是，不知道从何说起了。");
		frontTradeCommentDTO.setScore(4.5f);
		frontTradeCommentDTO.setNickName("晴天");
		frontTradeCommentDTO.setCreatedAt(DateUtil.getNow());
		frontTradeCommentDTO.setPrice(1000f);
		frontTradeCommentDTO.setPackageName("胶片写真A");
		frontTradeCommentDTO.setTradePhotoCollectionId(1);
		frontTradeCommentDTO
				.setPhotoPath("http//static.jspass.com/static/css/profile/images/trade-portrait1.png");
		frontTradeCommentDTO.setPhotos(photoDTOs);

		List<FrontTradeCommentDTO> frontTradeCommentDTOs = new ArrayList<FrontTradeCommentDTO>();
		frontTradeCommentDTOs.add(frontTradeCommentDTO);

		Page<FrontTradeCommentDTO> frontTradeComments = new Page<FrontTradeCommentDTO>(
				0, 20, 10, frontTradeCommentDTOs);
		System.out.println(JsonUtil.toJson(frontTradeComments));
	}

}
