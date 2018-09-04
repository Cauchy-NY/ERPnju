package main.DataService.ReciptDataService;

import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.PaymentOrderPO;
import main.PO.ReciptPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy������
 */
public interface ReciptDataService {
	
	/**
	 * ����տ
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addCollectionOrder (CollectionOrderPO po);

	/**
	 * ��Ӹ��
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addPaymentOrder (PaymentOrderPO po);

	/**
	 * ����ֽ��õ�
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addCashBill (CashBillPO po);
	
	/**
	 * �����տ
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updateCollectionOrder (CollectionOrderPO po);

	/**
	 * ���¸��
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updatePaymentOrder (PaymentOrderPO po);

	/**
	 * �����ֽ��õ�
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updateCashBill (CashBillPO po);
	
	/**
	 * ����id��ȡһ���տ
	 * @param id
	 * @return CollectionOrderPO
	 */
	public CollectionOrderPO getACollectionOrder(String id);
	
	/**
	 * ����id��ȡһ�����
	 * @param id
	 * @return PaymentOrderPO
	 */
	public PaymentOrderPO getAPaymentOrder(String id);

	/**
	 * ����id��ȡһ���ֽ��õ�
	 * @param id
	 * @return CashBillPO
	 */
	public CashBillPO getACashBill(String id);
	
	/**
	 * �������ͣ��ݸ塢������δ�����������տ
	 * @param cmd
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getCollectionOrderPOList(String cmd);
	
	/**
	 * �������ͣ��ݸ塢������δ���������Ҹ��
	 * @param cmd
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getPaymentOrderPOList(String cmd);
	
	/**
	 * �������ͣ��ݸ塢������δ�����������ֽ��õ�
	 * @param cmd
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getCashBillPOList(String cmd);
	
	/**
	 * ��������ɸѡ����
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<ReciptPO>
	 */
	public ArrayList<ReciptPO> getCheckedReciptList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ��������ɸѡ�տ
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getCheckedCollectionOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ��������ɸѡ���
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getCheckedPaymentOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ��������ɸѡ�ֽ��õ�
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getCheckedCashBillList(String startTime, String endTime, 
			String customerName, String operator);

	/**
	 * ����id��ȡһ������
	 * @param id
	 * @return ReciptPO
	 */ 
	public ReciptPO getOne (String id);
	
	/**
	 * �Զ���ȡ��һ���µ�ϵͳ�Զ�����id
	 * @param type
	 * @return int
	 */
	public int newId(String type);
	
	/**
	 * ��ȡ���еĵ���
	 * @return ArrayList<ReciptPO>
	 */
	public ArrayList<ReciptPO> getAllRecipts();

}
