package com.atguigu.sale.bean;

public class OBJECT_T_MALL_SKU {

	private int id;
	private int shp_id;
	private int kc;
	private double jg;
	private String sku_mch;
	private int sku_xl;
	private String kc_dz;

	private T_MALL_PRODUCT spu;

	private T_MALL_TRADE_MARK tm;

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

	

	public String getKc_dz() {
		return kc_dz;
	}

	public void setKc_dz(String kc_dz) {
		this.kc_dz = kc_dz;
	}

	public T_MALL_PRODUCT getSpu() {
		return spu;
	}

	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}

	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}

	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}

}
