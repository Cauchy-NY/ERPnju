package main.BussinessLogic.PromotionBL;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.Data.PromotionData.PromotionData;
import main.DataService.PromotionDataService.PromotionDataService;
import main.PO.CouponPO;
import main.PO.CustomerPO;
import main.PO.GoodsPO;
import main.PO.LevelPromotionPO;
import main.PO.PackagePromotionPO;
import main.PO.PromotionPO;
import main.PO.ReciptGoodsPO;
import main.PO.TotalPromotionPO;
import main.VO.GoodsVO;
import main.VO.LevelPromotionVO;
import main.VO.PackagePromotionVO;
import main.VO.PromotionVO;
import main.VO.TotalPromotionVO;
import main.utility.PromotionResult;
import main.utility.PromotionType;
import main.utility.ResultMessage;

public class PromotionBL {
	
	PromotionDataService pds = new PromotionData();

	public ResultMessage setPromotion(PromotionVO vo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  //�������ڸ�ʽ
		String date = df.format(new Date());  // new Date()Ϊ��ȡ��ǰϵͳʱ��
		
		String type = vo.getId();  //��������ʱ��ֻ�����࣬û�б��
		String id = type + "-" + date;  //�Զ����ɱ��
		vo.setId(id);
		
		//�ؼ۰�
		if(type.equals("TJB"))
			pds.insert(new PackagePromotionPO((PackagePromotionVO)vo));
		//�ܼ�
		else if(type.equals("ZJ")) 
			pds.insert(new TotalPromotionPO((TotalPromotionVO)vo));
		//����
		else if(type.equals("JB")) 
			pds.insert(new LevelPromotionPO((LevelPromotionVO)vo));
		
		return ResultMessage.SUCCESS;
	}
	
	public PromotionVO getPromotion(String promotionID) {
		
		if(pds.find(promotionID) != null) {
			String type = promotionID.split("-")[0];
			//����ȯ
			if(type.equals("TJB"))
				return new PackagePromotionVO((PackagePromotionPO)pds.find(promotionID));
			//�ܶ���Ʒ
			else if(type.equals("ZJ")) 
				return new TotalPromotionVO((TotalPromotionPO)pds.find(promotionID));
			//��Ʒ
			else if(type.equals("JB")) 
				return new LevelPromotionVO((LevelPromotionPO)pds.find(promotionID));
		}
		
		return null;
	}

	public ArrayList<PromotionVO> getPromotionList(PromotionType type) {
		ArrayList<PromotionPO> temp = pds.getPromotionList(type);
		ArrayList<PromotionVO> result = new ArrayList<PromotionVO>();
		for(int i = 0; i < temp.size(); i++) {
			PromotionPO po = temp.get(i);
			String cmd = po.getId().split("-")[0];
			if(cmd.equals("TJB")) {
				PackagePromotionVO vo = new PackagePromotionVO((PackagePromotionPO)po);
				result.add(vo);
			}
			else if(cmd.equals("ZJ")) {
				TotalPromotionVO vo = new TotalPromotionVO((TotalPromotionPO)po);
				result.add(vo);
			}
			else if(cmd.equals("JB")) {
				LevelPromotionVO vo = new LevelPromotionVO((LevelPromotionPO)po);
				result.add(vo);
			}
		}
		return result;
	}

	public ResultMessage deletePromotions(ArrayList<String> promotionIDs) {
		for(int i = 0; i < promotionIDs.size(); i++) {
			if(pds.find(promotionIDs.get(i)) != null)
				pds.delete(promotionIDs.get(i));
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modifyPromotion(PromotionVO vo) {
		if(pds.find(vo.getId()) != null) {
			String cmd = vo.getId().split("-")[0];
			if(cmd.equals("TJB")) {
				PackagePromotionPO po = new PackagePromotionPO((PackagePromotionVO)vo);
				pds.update(po);
			}
			else if(cmd.equals("ZJ")) {
				TotalPromotionPO po = new TotalPromotionPO((TotalPromotionVO)vo);
				pds.update(po);
			}
			else if(cmd.equals("JB")) {
				LevelPromotionPO po = new LevelPromotionPO((LevelPromotionVO)vo);
				pds.update(po);
			}
			else 
				return ResultMessage.FAIL;
			
			return ResultMessage.SUCCESS;
		}
		else
			return ResultMessage.FAIL;
	}
	
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		CommodityService cs = new CommodityBL();
		ArrayList<GoodsPO> goodsList = cs.getGoodsList(part, condition);
		ArrayList<GoodsVO> result = new ArrayList<GoodsVO>();
		for(int i = 0; i < goodsList.size(); i++) {
			GoodsVO vo = new GoodsVO(goodsList.get(i));
			result.add(vo);
		}
		return result;
	}
	
	public PromotionResult getAppropriatePromotion(ArrayList<ReciptGoodsPO> reciptGoodsList, 
			CustomerPO customer) {
		PromotionResult result = new PromotionResult();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");  //�������ڸ�ʽ
		String currentTime = df.format(new Date());  //��ȡ��ǰϵͳʱ��
		//����������
		double totalConsumption = 0;
		for(int i = 0; i < reciptGoodsList.size(); i++) {
			totalConsumption += reciptGoodsList.get(i).getSum();
		}
		//��ȡ���ʵ��ؼ۰��Ż�
		ArrayList<PackagePromotionPO> suitTjb = new ArrayList<PackagePromotionPO>();
		ArrayList<PromotionPO> tjb = pds.getPromotionList(PromotionType.PACKAGE);
		for(int i = 0; i <tjb.size(); i++) {
			if((currentTime.compareTo(tjb.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(tjb.get(i).getEndTime()) <= 0)) {
				//ƥ���ؼ۰�
				PackagePromotionPO po = (PackagePromotionPO)tjb.get(i);
				ArrayList<ReciptGoodsPO> pack = po.getGiftList();
				ArrayList<ReciptGoodsPO> temp = reciptGoodsList;
				for(int p = 0; p < pack.size(); p++) {
					for(int j = 0; j < temp.size(); j++) {
						if(temp.get(j).getGoodsID().equals(pack.get(p).getGoodsID())) {
							pack.remove(pack.get(p));
							temp.remove(temp.get(j));
							p--; j--;
							break;
						}
					}
				}
				if(pack.isEmpty()) {
					suitTjb.add(po);
				}
			}
		}
		//�����ؼ۰��Żݣ�����еĻ���
		if(suitTjb.size() != 0) {
			for(int i = 0; i < suitTjb.size(); i++) {
				totalConsumption -= suitTjb.get(i).getReducePrice();
			}
		}
		//��ȡ���ʵ��ؼ۰��Ż�
		ArrayList<TotalPromotionPO> suitZj = new ArrayList<TotalPromotionPO>();
		ArrayList<PromotionPO> zj = pds.getPromotionList(PromotionType.TOTAL);
		for(int i = 0; i <zj.size(); i++) {
			if(currentTime.compareTo(zj.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(zj.get(i).getEndTime()) <= 0) {
				TotalPromotionPO po = (TotalPromotionPO)zj.get(i);
				if(totalConsumption >= po.getTotalNumber()) {
					suitZj.add(po);
				}
			}
		}
		//�����ܼ��Żݣ�����еĻ���
		if(suitZj.size() != 0) {
			//����ѡ��Ĭ���Ż��������ģ����޶���ߵģ�
			Collections.sort(suitZj, new Comparator<TotalPromotionPO>() {  
			    @Override  
			    public int compare(TotalPromotionPO a, TotalPromotionPO b) {  
			    	if(a.getTotalNumber() > b.getTotalNumber()) return -1;
			    	else return 1;
			    }  
			});
			TotalPromotionPO fianlZj = suitZj.get(0);
			//�������ȯ
			ArrayList<CouponPO> couponList = customer.getCouponList();
			couponList.addAll(fianlZj.getCouponList());
			customer.setCouponList(couponList);
			//������Ʒ
			ArrayList<ReciptGoodsPO> giftList = result.getGiftList();
			giftList.addAll(fianlZj.getGiftList());
			result.setGiftList(giftList);
		}
		
		//��ȡ���ʵļ����Ż�
		ArrayList<LevelPromotionPO> suitJb = new ArrayList<LevelPromotionPO>();
		ArrayList<PromotionPO> jb = pds.getPromotionList(PromotionType.LEVEL);
		for(int i = 0; i <jb.size(); i++) {
			if(currentTime.compareTo(jb.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(jb.get(i).getEndTime()) <= 0) {
				LevelPromotionPO po = (LevelPromotionPO)jb.get(i);
				if(po.getLevel().equals(customer.getLevel())) {
					suitJb.add(po);
				}
			}
		}
		//�������Żݣ�����еĻ���
		if(suitJb.size() != 0) {
			//����ѡ��Ĭ���Ż��������ģ������Ӹߵ��������� �ۿۡ���Ʒ����������ȯ��������˳������
			Collections.sort(suitJb, new Comparator<LevelPromotionPO>() {  
			    @Override  
			    public int compare(LevelPromotionPO a, LevelPromotionPO b) {  
			    	if(a.getDiscount() > b.getDiscount()) return -1;
			    	else if(a.getDiscount() < b.getDiscount()) return 1;
			    	else {
			    		if(a.getGiftList().size() > b.getGiftList().size()) return -1;
			    		else if(a.getGiftList().size() < b.getGiftList().size()) return 1;
			    		else {
			    			if(a.getCouponList().size() > b.getCouponList().size()) return -1;
				    		else return 1;
			    		}
			    	}
			    }  
			});
			LevelPromotionPO fianlJb = suitJb.get(0);
			//�����ܼ۸��Ż�
			totalConsumption *= fianlJb.getDiscount();
			//�������ȯ
			ArrayList<CouponPO> couponList = customer.getCouponList();
//			couponList.addAll(fianlJb.getCouponList());
			for(int i=0;i<fianlJb.getCouponList().size();i++){
				couponList.add(fianlJb.getCouponList().get(i));
			}
			customer.setCouponList(couponList);
			//������Ʒ
			ArrayList<ReciptGoodsPO> giftList = result.getGiftList();
//			giftList.addAll(fianlJb.getGiftList());
			for(int i=0;i<fianlJb.getGiftList().size();i++){
				giftList.add(fianlJb.getGiftList().get(i));
			}
			result.setGiftList(giftList);
		}
		//����ܼۼ�������
		result.setAmount(totalConsumption);
		
		return result;
	}
	
}
