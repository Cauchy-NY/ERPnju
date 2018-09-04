package main.DataService.BankAccountDataService;

import java.util.ArrayList;

import main.PO.BankAccountPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface BankAccountDataService {
	/**
	 * ��������˻�
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage add (BankAccountPO po);

	/**
	 * ����idɾ�������˻�
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage delete (String id);

	/**
	 * ���������˻���Ϣ
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage modify (BankAccountPO po);
	
	/**
	 * ����id���������˻�
	 * @param bankAccountID
	 * @return BankAccountPO
	 */
	public BankAccountPO find (String bankAccountID);
	
	/**
	 * ��ȡ�����˻��б�
	 * @return ArrayList<BankAccountPO>
	 */
	public ArrayList<BankAccountPO> getList();
	
	/**
	 * ���ݲ�����Ϣģ������
	 * @param part
	 * @return ArrayList<BankAccountPO>
	 */
	public ArrayList<BankAccountPO> getList(String part);
	
}
