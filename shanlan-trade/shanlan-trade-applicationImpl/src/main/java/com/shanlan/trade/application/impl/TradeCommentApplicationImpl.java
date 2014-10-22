package com.shanlan.trade.application.impl;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.shanlan.common.util.EntityUtil;
import com.shanlan.photo.core.domain.Photo;
import com.shanlan.photo.core.domain.RePhotoUserOwn;
import com.shanlan.trade.core.domain.ReTradePhoto;
import com.shanlan.trade.core.domain.TradeOrderItem;
import com.shanlan.trade.core.domain.TradePhotoCollection;
import com.shanlan.trade.core.domain.service.TradeOrderItemService;
import com.shanlan.user.core.domain.UserDetail;
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
        this.removeTradeComments(new Integer[]{id});
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<FrontTradeCommentDTO> pageTradeComments(String sellerUserName,
                                                        int currentPage, int pageSize) {

        List<FrontTradeCommentDTO> frontTradeCommentDTOs = new ArrayList<FrontTradeCommentDTO>();


        //执行流程
        //1.根据sellerUserName从trade_order表和trade_order_item表中分页查询TradeOrderItem信息
        //2.根据TradeOrderItem里的orderItemId从trade_comment表中查出对应的交易评价数据
        //2.根据TradeOrderItem里的orderItemId从trade_photo_collection表中查出对应的交易集数据
        //3.根据trade_photo_collection表的Id信息从re_trade_photo表中查出该交易集对应的照片
        //4.根据re_trade_photo表中的upo_id从re_photo_user_own表中查出照片信息

        List<TradeOrderItem> tradeOrderItems = TradeOrderItemService.pageOrderItems(sellerUserName, currentPage, pageSize);

        Map<Integer,TradeOrderItem> idAndTradeOrderItemMap=EntityUtil.getIdAndEntityMap(tradeOrderItems);

        List<Integer> orderItemIds = EntityUtil.getIds(tradeOrderItems);


        List<TradeComment> tradeComments = TradeComment.list(orderItemIds);

        List<String> userNameList=new ArrayList<String>();
        for (TradeComment tradeComment:tradeComments){
            userNameList.add(tradeComment.getRater());
        }
        Map<String,UserDetail> userNameUserDetailMap= UserDetail.getUserNameAndSelfMap(userNameList);

        List<TradePhotoCollection> tradePhotoCollections = TradePhotoCollection.listByToiIds(orderItemIds);

        Map<Integer, Integer> toiIdAndTpcIdMap=TradePhotoCollection.getToiIdAndTpcIdMap(tradePhotoCollections);


        List<Integer> tpcIds=EntityUtil.getIds(tradePhotoCollections);

        List<ReTradePhoto> reTradePhotos = ReTradePhoto.listByTpcIds(tpcIds);
        Map<Integer,List<ReTradePhoto>> tpcIdAndReTradePhotoListMap=ReTradePhoto.getTcpIdAndSelfListMap(reTradePhotos);

        for (TradeComment tradeComment:tradeComments){

            FrontTradeCommentDTO frontTradeCommentDTO=new FrontTradeCommentDTO();

            Integer tradeOrderItemId=tradeComment.getToiId();
            Integer tradePhotoCollectionId=toiIdAndTpcIdMap.get(tradeOrderItemId);

            UserDetail userDetail=userNameUserDetailMap.get(tradeComment.getRater());
            TradeOrderItem tradeOrderItem=idAndTradeOrderItemMap.get(tradeOrderItemId);
            List<ReTradePhoto> reTradePhotoList=tpcIdAndReTradePhotoListMap.get(tradePhotoCollectionId);
            List<Integer> reTradePhotoIdList=EntityUtil.getIds(reTradePhotoList);
            List<RePhotoUserOwn> rePhotoUserOwns=RePhotoUserOwn.list(reTradePhotoIdList);
            List<PhotoDTO> photoDTOs=new ArrayList<PhotoDTO>();
            for(RePhotoUserOwn rePhotoUserOwn:rePhotoUserOwns){
                photoDTOs.add(new PhotoDTO(rePhotoUserOwn.getPhotoId(),rePhotoUserOwn.getPhotoPath()));
            }

            frontTradeCommentDTO.setNickName(userDetail.getNickName());
            frontTradeCommentDTO.setPhotoPath(userDetail.getPhotoPath());
            frontTradeCommentDTO.setContent(tradeComment.getContent());
            frontTradeCommentDTO.setCreatedAt(tradeComment.getCratedAt());
            frontTradeCommentDTO.setPackageName(tradeOrderItem.getGoodsName());
            frontTradeCommentDTO.setPhotoCount(rePhotoUserOwns.size());
            frontTradeCommentDTO.setPrice(tradeOrderItem.getPrice());
            frontTradeCommentDTO.setQuantity(tradeOrderItem.getQuantity());
            frontTradeCommentDTO.setScore(tradeComment.getScore());
            frontTradeCommentDTO.setTradePhotoCollectionId(tradePhotoCollectionId);
            frontTradeCommentDTO.setPhotos(photoDTOs);

            frontTradeCommentDTOs.add(frontTradeCommentDTO);
        }

        Page<FrontTradeCommentDTO> frontTradeComments = new Page<FrontTradeCommentDTO>(
                currentPage, 0, pageSize, frontTradeCommentDTOs);
        return frontTradeComments;
    }

}
