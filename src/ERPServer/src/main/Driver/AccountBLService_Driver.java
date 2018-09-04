package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.VO.AccountVO;
import main.utility.ResultMessage;

public class AccountBLService_Driver {
	
	public void drive(AccountBLService accountBLService) throws RemoteException {
		AccountVO vo = new AccountVO();
		ResultMessage result = accountBLService.set(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("�½��ڳ���Ϣ�ɹ�");
		else
			System.out.println("�½��ڳ���Ϣʧ��");
		result = accountBLService.update(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("�����ڳ���Ϣ�ɹ�");
		else
			System.out.println("�����ڳ���Ϣʧ��");
	}

}
