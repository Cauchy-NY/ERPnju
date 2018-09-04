package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.RecordBL.RecordBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.InfoVO;
import main.utility.DocumentsType;

public class RecordBLTest {
	
	RecordBL bl = new RecordBL();
	
	@Before
	public void init() {
		HibernateHelper.initHibernateHelper();
		System.out.println("init");
	}
	
	@Test
	public void test01() {
		InfoVO vo = bl.getSalesHistoryList("20180101", "20180113", DocumentsType.FINANCIAL_DOCUMENTS, "��", "�����");
		assertEquals(0, vo.getReciptList().size());
	}
	
	@Test
	public void test02() {
		InfoVO vo = bl.getSalesHistoryList("20180101", "20180113", DocumentsType.FINANCIAL_DOCUMENTS, "��", "�����");
		assertEquals(1, vo.getCashBillList().size());
	}

}
