package main.BussinessLogic.BankAccountBL;

import java.util.ArrayList;

import main.VO.BankAccountVO;

public interface BankAccountService {
	
	/**
	 * ͬ���ṩ�����˻�ģ�����ҷ���
	 * @param part
	 * @return
	 */
	public ArrayList<BankAccountVO> getBankAccount(String part);
}
