package main.BussinessLogic.CommodityBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.Data.CommodityData.CommodityReciptData;
import main.Data.CommodityData.GoodsData;
import main.DataService.CommodityDataService.CommodityReciptDataService;
import main.PO.CommodityReciptPO;
import main.PO.GoodsPO;
import main.VO.CommodityReciptVO;
import main.utility.ResultMessage;
/**
 * 
 * @author �����
 *
 */
public class CommodityReciptBL{
	
	CommodityReciptDataService service;
	
	public CommodityReciptBL(){
		service = new CommodityReciptData();
	}

	//���������͵�
	public ResultMessage setGiftList(CommodityReciptVO vo) {
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po); 
	}

	//�����汨�絥
	public ResultMessage setOverflowList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}

	//�����汨��
	public ResultMessage setDamageList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}

	//���汨����
	public ResultMessage setWarningList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}
	
	//ΪVO��Ӵ���ʱ��
	public void setDate(CommodityReciptVO vo){
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		vo.setCreateDate(date);
	}
	
	//�õ�û�������Ŀ�浥��
	public ArrayList<CommodityReciptPO> getUncheckedCommodityRecipt(){
		
		return service.find("Unchecked", "state");

	}
	
	
	 //��浥��û�пͻ����Ͳ���Ա������������Ϊ���� �򲻷��ؿ�浥���� 
	public ArrayList<CommodityReciptVO> getCheckedCommodityRecipt(String startTime, String endTime, String customerName,
			String operator){
		ArrayList<CommodityReciptPO> list = service.find(startTime, endTime, customerName, operator);
		ArrayList<CommodityReciptVO> voList = new ArrayList<>();
		
		if(customerName.equals("")&&operator.equals("")){
			if(list!=null && !list.isEmpty()){
				for(CommodityReciptPO tmp : list){
					voList.add(new CommodityReciptVO(tmp));
				}
			}
		}
		return voList;
	}
	
	//�����浥��
	public ResultMessage saveCommodityRecipt(ArrayList<CommodityReciptPO> list){
		
		if(list!=null && !list.isEmpty()){
			for(CommodityReciptPO po : list){
				if(po.getState().equals("Checked")){
					checkSingleRecipt(po);
				}
			}
		}
		
		return service.modify(list);	
		
	}

	//�õ��������ĵ���
	public ArrayList<CommodityReciptPO> getcheckedCommodityRecipt() {
		
		return service.find("Checked", "state");
	}

	//����������浥��
	private void checkSingleRecipt(CommodityReciptPO po){
		GoodsBL bl = new GoodsBL();
		ArrayList<GoodsPO> list = null;
		int changedNum = po.getChangedNumbers();
		
		if(po.getType().equals("BS")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				int setNum = (int) (goods.getAmounts() - changedNum);
				goods.setAmounts(setNum>0 ? setNum : 0);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("BY")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				goods.setAmounts(goods.getAmounts() + changedNum);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("ZS")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				int setNum = (int) (goods.getAmounts() - changedNum);
				goods.setAmounts(setNum>0 ? setNum : 0);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("BJ")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				goods.setAlertAmounts(changedNum);
				new GoodsData().modify(goods);
			}
		}
	}
}
