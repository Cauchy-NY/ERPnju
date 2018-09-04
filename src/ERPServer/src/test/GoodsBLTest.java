package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.GoodsBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.GoodsVO;

public class GoodsBLTest {

	GoodsBL bl = new GoodsBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
	}
	
	@Test
	public void test1(){
		bl.goodsAdd(new GoodsVO("x25��", "", "x1", 50, 1, 2, 3, 4, "����2", 2.5, "00"));
	}
	
	@Test
	public void test2(){
		ArrayList<GoodsVO> list = bl.goodsFind("x25��", "name");
		list.get(0).setAlertAmounts(100);
		bl.goodsModify(list.get(0));
	}

	@Test
	public void test3(){
		ArrayList<GoodsVO> list = bl.goodsFind("x25��", "name");
		list.get(0).setAlertAmounts(100);
		bl.goodsDelete(list.get(0));
	}
	
	@Test
	public void test4(){
		bl.goodsAdd(new GoodsVO("x26��", "", "x1", 50, 1, 2, 3, 4, "����2", 2.5, "00"));
		bl.goodsAdd(new GoodsVO("x27��", "", "x1", 50, 1, 2, 3, 4, "����2", 2.5, "00"));
		bl.goodsDelete(bl.goodsFind("x27��", "name").get(0));
		bl.goodsDelete(bl.goodsFind("x26��", "name").get(0));
	}

}
