package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.UserBLService.UserBLService;
import main.VO.UserVO;
import main.utility.UserType;

public class UserBLService_Driver {
	
	public void drive(UserBLService userBLService) throws RemoteException {
		UserVO vo = new UserVO();
		UserType result = userBLService.register(vo);
		if(result == UserType.ALREADY_EXIT)
			System.out.println("�ͻ��Ѵ���");
		else if(result == UserType.FINANCIAL_STAFF)
			System.out.println("�ɹ�ע�������Ա�˺�");
		else if(result == UserType.MANAGER)
			System.out.println("�ɹ�ע���ܾ����˺�");
		else if(result == UserType.SALES_MAN)
			System.out.println("�ɹ�ע��������Ա�˺�");
		else if(result == UserType.STOCK_MANAGER)
			System.out.println("�ɹ�ע���������Ա�˺�");
	}

}
