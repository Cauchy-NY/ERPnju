package main.BussinessLogic.ReciptBL;

import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.PaymentOrderPO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

public interface ReciptService {
	/**
	 * ͬ���ṩ����δ�����տ����
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getUncheckedCollectionOrder();
	
	/**
	 * ͬ���ṩ�����տ����
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCollectionOrder(ArrayList<CollectionOrderPO> list);
	
	/**
	 * ͬ���ṩ����δ�����������
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getUncheckedPaymentOrder();
	
	/**
	 * ͬ���ṩ���渶�����
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage savePaymentOrder(ArrayList<PaymentOrderPO> list);
	
	/**
	 * ͬ���ṩ����δ�����ֽ��õ�����
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getUncheckedCashBillPO();
	
	/**
	 * ͬ���ṩ�����ֽ��õ�����
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCashBillPO(ArrayList<CashBillPO> list);
	
	/**
	 * ͬ���ṩ��ȡ���������ݷ���
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<ReciptVO>
	 */
	public ArrayList<ReciptVO> getCheckedReciptList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ͬ���ṩ��ȡ�������������
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CollectionOrderVO>
	 */
	public ArrayList<CollectionOrderVO> getCheckedCollectionOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ͬ���ṩ��ȡ�������������
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<PaymentOrderVO>
	 */
	public ArrayList<PaymentOrderVO> getCheckedPaymentOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ͬ���ṩ��ȡ�������ֽ��õ�����
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CashBillVO>
	 */
	public ArrayList<CashBillVO> getCheckedCashBillList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * ���ú��
	 * @param type
	 * @param id
	 * @return boolean
	 */
	public boolean setRedDashed(String type, String id);
}
