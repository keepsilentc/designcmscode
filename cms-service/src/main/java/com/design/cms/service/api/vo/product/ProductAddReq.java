package com.design.cms.service.api.vo.product;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;

@Getter
@Setter
@ToString
public class ProductAddReq {
	/**
	 * 系列id
	 */
	@NotNull
	private Long themeId;
	/**
	 * 类别id
	 */
	@NotNull
	private Long cateGoryId;
	/**
	 * 设计师id
	 */
	@NotNull
	private Long designerId;
	@NotBlank
	private String designerName;
	@NotBlank
	private String countryId;
	/**
	 * 品牌id
	 */
	@NotBlank
	private String brandId;
	/**
	 * 产品编号
	 */
	@NotBlank
	private String productNo;
	/**
	 * 单价
	 */
	@NotNull
	private BigDecimal price;
	/**
	 * 原价
	 */
	private BigDecimal originPrice;
	/**
	 * 产品状态
	 * 10-现货
	 * 20-预售
	 */
	@NotNull
	private Integer state;
	/**
	 * 是否新品
	 * 0-否
	 * 1-是
	 */
	@NotNull
	private Integer isNew;
	/**
	 * 是否为代表作
	 * 0-否
	 * 1-是
	 */
	@NotNull
	private Integer isRepresentative;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 产品名称
	 */
	@NotBlank
	private String productName;
	/**
	 * 编辑笔记
	 */
	private String productDescribe;
	/**
	 * 单品细节
	 */
	private String productDetail;
	/**
	 * 尺码指导
	 */
	private String sizeDescribe;
	@NotNull
	private Integer isEnable;
	
	/**
	 * 预售开始时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date preSellStartTime;
	/**
	 * 预售结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date preSellEndTime;

}
