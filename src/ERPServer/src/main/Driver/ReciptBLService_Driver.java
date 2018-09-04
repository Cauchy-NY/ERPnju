package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentOrderVO;
import main.utility.ResultMessage;

public class ReciptBLService_Driver {
	
	public void drive(ReciptBLService recipBLService) throws RemoteException {
		CollectionOrderVO vo1 = new CollectionOrderVO();
		ResultMessage result = recipBLService.setCollection(vo1);
		if(result == ResultMessage.SUCCESS)
			System.out.println("�����տ�ɹ�");
		else
			System.out.println("�����տʧ��");
		
		PaymentOrderVO vo2 = new PaymentOrderVO();
		result = recipBLService.setPaymentOrder(vo2);
		if(result == ResultMessage.SUCCESS)
			System.out.println("���ø���ɹ�");
		else
			System.out.println("���ø��ʧ��");
		
		CashBillVO vo3 = new CashBillVO();
		result = recipBLService.setCashBill(vo3);
		if(result == ResultMessage.SUCCESS)
			System.out.println("�����ֽ���õ��ɹ�");
		else
			System.out.println("�����ֽ���õ�ʧ��");
	}

}
