package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.TotalPromotionPO;

public class TotalPromotionVO extends PromotionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double totalNumber;    //�����ۿ۵���������
	private ArrayList<ReciptGoodsVO> giftList;  //��Ʒ�б�
	private ArrayList<CouponVO> couponList;  //����ȯ�б�
	
	public TotalPromotionVO() {}
	
	public TotalPromotionVO(TotalPromotionPO po) {
		super(po.getId(), po.getStartTime(), po.getEndTime());
		this.totalNumber = po.getTotalNumber();
		
		this.giftList = new ArrayList<ReciptGoodsVO>(); 
		for(int i = 0; i < po.getGiftList().size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(po.getGiftList().get(i));
			this.giftList.add(tempVO);
		}
		
		this.couponList = new ArrayList<CouponVO>();
		for(int i = 0; i < po.getCouponList().size(); i++) {
			CouponVO tempVO = new CouponVO(po.getCouponList().get(i));
			this.couponList.add(tempVO);
		}
	}

	public TotalPromotionVO(String startTime, String endTime, double totalNumber,
			ArrayList<GoodsVO> giftList, ArrayList<CouponVO> couponList) {
		super("ZJ", startTime, endTime);
		this.totalNumber = totalNumber;
		
		this.giftList = new ArrayList<ReciptGoodsVO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(giftList.get(i));
			this.giftList.add(tempVO);
		}
		
		this.couponList = couponList;
	}

	public double getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(double totalNumber) {
		this.totalNumber = totalNumber;
	}

	public ArrayList<ReciptGoodsVO> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<ReciptGoodsVO> giftList) {
		this.giftList = giftList;
	}

	public ArrayList<CouponVO> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<CouponVO> couponList) {
		this.couponList = couponList;
	}

}
