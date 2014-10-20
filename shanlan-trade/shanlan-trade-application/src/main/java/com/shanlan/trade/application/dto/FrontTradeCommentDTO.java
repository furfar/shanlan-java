package com.shanlan.trade.application.dto;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openkoala.koala.springmvc.JsonDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import com.shanlan.photo.application.dto.PhotoDTO;

/**
 * 根据前台显示需要创建的关于交易评价信息的DTO
 * 
 * @author albertliu
 *
 */
public class FrontTradeCommentDTO {

	/**
	 * 用户的呢称
	 */
	private String nickName;
	/**
	 * 用户头像路径
	 */
	private String photoPath;

	/**
	 * 交易的套系名（来自goods表的name字段）
	 */
	private String packageName;

	/**
	 * 交易照片集ID（来自trade_photo_collection表的id字段）
	 */
	private Integer tradePhotoCollectionId;

	/**
	 * 交易价格（来自trade_order_item表的price字段）
	 */
	private Float price;

    /**
     * 商品的购买数量
     */
    private Integer quantity;

	/**
	 * 交易时间（来处trade_order表的cratedAt字段）
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
	private Date createdAt;

	/**
	 * 交易评价分数（来自trade_comment表的score字段）
	 */
	private Integer score;

	/**
	 * 交易评价内容（来自trade_comment表的content字段）
	 */
	private String content;

	/**
	 * 交易集照片列表
	 */
	private List<PhotoDTO> photos;

	/**
	 * 交易集照片数量
	 */
	private int photoCount;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	
	
	public Integer getTradePhotoCollectionId() {
		return tradePhotoCollectionId;
	}

	public void setTradePhotoCollectionId(Integer tradePhotoCollectionId) {
		this.tradePhotoCollectionId = tradePhotoCollectionId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<PhotoDTO> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoDTO> photos) {
		this.photos = photos;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

}
