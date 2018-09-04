package main.DataService.AccountDataService;

import main.PO.AccountPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface AccountDataService {
	/**
	 * ��ȡ�ڳ���Ϣ
	 * @return AccountPO
	 */
	public AccountPO get();
	
	/**
	 * �����ڳ���Ϣ
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage update(AccountPO po);
	
	/**
	 * �����ڳ�����
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage set(AccountPO po);
}
