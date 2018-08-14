package com.atguigu.sale.bean;

import java.util.List;

public class OBJECT_SKU_DETAIL {

	private int id;
	private int shp_id;
	private int kc;
	private double jg;
	private String sku_mch;
	private int sku_xl;
	private String dz;

	private List<OBJECT_ATTR_VALUE_NAME> list_attr_value_name;

	private List<T_MALL_PRODUCT_IMAGE> list_image;

	private T_MALL_PRODUCT spu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public int getKc() {
		return kc;
	}

	public void setKc(int kc) {
		this.kc = kc;
	}

	public double getJg() {
		return jg;
	}

	public void setJg(double jg) {
		this.jg = jg;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public int getSku_xl() {
		return sku_xl;
	}

	public void setSku_xl(int sku_xl) {
		this.sku_xl = sku_xl;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public List<OBJECT_ATTR_VALUE_NAME> getList_attr_value_name() {
		return list_attr_value_name;
	}

	public void setList_attr_value_name(List<OBJECT_ATTR_VALUE_NAME> list_attr_value_name) {
		this.list_attr_value_name = list_attr_value_name;
	}

	public List<T_MALL_PRODUCT_IMAGE> getList_image() {
		return list_image;
	}

	public void setList_image(List<T_MALL_PRODUCT_IMAGE> list_image) {
		this.list_image = list_image;
	}

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

}
