package com.atguigu.sale.bean;

import java.util.Date;
import java.util.List;

public class OBJECT_T_MALL_ORDER_AND_INFO {

	private int id;
	private String shjr;
	private double zje;
	private int jdh;
	private int yh_id;
	private Date yjsdshj;
	private Date chjshj;
	private int dzh_id;
	private String dzh_mch;

	private List<T_MALL_ORDER_INFO> order_info_list;

	
	public String getShjr() {
		return shjr;
	}

	public void setShjr(String shjr) {
		this.shjr = shjr;
	}

	public double getZje() {
		return zje;
	}

	public void setZje(double zje) {
		this.zje = zje;
	}

	public int getJdh() {
		return jdh;
	}

	public void setJdh(int jdh) {
		this.jdh = jdh;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public Date getYjsdshj() {
		return yjsdshj;
	}

	public void setYjsdshj(Date yjsdshj) {
		this.yjsdshj = yjsdshj;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public int getDzh_id() {
		return dzh_id;
	}

	public void setDzh_id(int dzh_id) {
		this.dzh_id = dzh_id;
	}

	public String getDzh_mch() {
		return dzh_mch;
	}

	public void setDzh_mch(String dzh_mch) {
		this.dzh_mch = dzh_mch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<T_MALL_ORDER_INFO> getOrder_info_list() {
		return order_info_list;
	}

	public void setOrder_info_list(List<T_MALL_ORDER_INFO> order_info_list) {
		this.order_info_list = order_info_list;
	}

}
