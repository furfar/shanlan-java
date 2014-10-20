package com.shanlan.trade.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shanlan.common.util.JPQLUtil;
import com.shanlan.common.util.SQLUtil;
import org.apache.commons.lang3.StringUtils;
import org.openkoala.koala.commons.domain.KoalaLegacyEntity;

/**
 * Auto Generated Entity
 *
 * @author Koala
 */
@Entity
@Table(name = "trade_comment")
public class TradeComment extends KoalaLegacyEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "toi_id")
    private int toiId;

    /**
     * 评价方用户名
     */
    @Column(name = "rater")
    private String rater;

    /**
     * 被评价方用户名
     */
    @Column(name = "ratee")
    private String ratee;

    @Column(name = "score")
    private Integer score;

    @Column(name = "content")
    private String content;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "type")
    private String type;

    @Column(name = "crated_at")
    private Date cratedAt;

    public TradeComment() {

    }

    public TradeComment(int id, int toiId, String rater, String ratee, Integer score,
                        String content, String explanation, String type, Date cratedAt) {
        super();
        this.id = id;
        this.toiId = toiId;
        this.rater = rater;
        this.ratee = ratee;
        this.score = score;
        this.content = content;
        this.explanation = explanation;
        this.type = type;
        this.cratedAt = cratedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getToiId() {
        return toiId;
    }

    public void setToiId(int toiId) {
        this.toiId = toiId;
    }

    /**
     * 获取评价方用户名
     *
     * @return
     */
    public String getRater() {
        return rater;
    }

    public void setRater(String rater) {
        this.rater = rater;
    }

    /**
     * 获取被评价方用户名
     *
     * @return
     */
    public String getRatee() {
        return ratee;
    }

    public void setRatee(String ratee) {
        this.rater = ratee;
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

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(Date cratedAt) {
        this.cratedAt = cratedAt;
    }

    public Integer getId() {
        return id;
    }

    public boolean existed() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean notExisted() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean existed(String propertyName, Object propertyValue) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String[] businessKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    public static List<TradeComment> list(List<Integer> orderItemIds) {
        List<TradeComment> tradeComments = new ArrayList<TradeComment>();
        String jpql = JPQLUtil.selectByIdIn(TradeComment.class, orderItemIds);
        if (StringUtils.isNotBlank(jpql)) {
            tradeComments = getRepository().createJpqlQuery(
                    jpql).list();
        }

        return tradeComments;
    }
}