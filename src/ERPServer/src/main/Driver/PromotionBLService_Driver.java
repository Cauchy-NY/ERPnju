package main.Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.VO.PromotionVO;
import main.utility.ResultMessage;

public class PromotionBLService_Driver {
	
	public void drive(PromotionBLService promotionBLService) throws RemoteException {
		PromotionVO vo = new PromotionVO();
		ResultMessage result = promotionBLService.setPromotion(vo, "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("��Ӵ������Գɹ�");
		else 
			System.out.println("��Ӵ�������ʧ��");
		
		vo = new PromotionVO();
		result = promotionBLService.modifyPromotion(vo, "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("�޸Ĵ������Գɹ�");
		else 
			System.out.println("�޸Ĵ�������ʧ��");
		
		result = promotionBLService.deletePromotions(new ArrayList<String>(), "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("ɾ���������Գɹ�");
		else 
			System.out.println("ɾ����������ʧ��");
	}

}
