package main.BussinessLogic.PromotionBL;

import java.util.ArrayList;

import main.PO.*;
import main.utility.PromotionResult;

public interface PromotionService {
	
	/**
	 * ͬ���ṩ�Զ�������ʵĴ������Է���
	 * @param reciptGoodsList
	 * @param customer
	 * @return
	 */
	public PromotionResult getAppropriatePromotion(ArrayList<ReciptGoodsPO> reciptGoodsList, CustomerPO customer);
	
}
