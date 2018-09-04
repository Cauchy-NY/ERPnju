package main.BussinessLogic.CommodityBL;

import java.util.ArrayList;

import main.BussinessLogic.ManifestBL.ManifestBL;
import main.BussinessLogic.ManifestBL.ManifestService;
import main.Data.CommodityData.GoodsData;
import main.DataService.CommodityDataService.GoodsDataService;
import main.PO.GoodsPO;
import main.PO.ManifestPO;
import main.PO.ReciptGoodsPO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
/**
 * 
 * @author �����
 *
 */
public class GoodsBL {

	GoodsDataService service = new GoodsData();


	//��Ʒ����
	public ResultMessage goodsAdd(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		String ID = new GoodsCatagoryBL().getCatagoryID(po.getCatagory());
		ID += "-" + String.format("%04d", service.newID(po.getCatagory()));
		po.setID(ID);
		
		//TODO ͬ��&ͬ�ͺ���Ʒ�������
		if(service.add(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}	

	//��Ʒɾ��
	public ResultMessage goodsDelete(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		if(service.delete(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}

	//��Ʒ�޸�
	public ResultMessage goodsModify(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		if(service.modify(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}

	//������Ʒ��ͨ�����ͺ͹ؼ��֣�����goodsVO
	public ArrayList<GoodsVO> goodsFind(String keyword, String type){
		ArrayList<GoodsPO> list = service.find(keyword, type);
		ArrayList<GoodsVO> retlist = new ArrayList<>();
		for(GoodsPO po : list){
			retlist.add(new GoodsVO(po));
		}
		
		return retlist;
	}
	
	//������Ʒ��ͨ�����ͺ͹ؼ��֣�����goodsPO
	public ArrayList<GoodsPO> find(String keyword, String type){
		return service.find(keyword, type);
	}

	//������Ʒ���
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list,String type, String lebal) {
		return service.updateCommodity(list, type, lebal);
	}

	//�õ���һ����ƷID
	public String getNextGoodsId(String category) {
		String ID = new GoodsCatagoryBL().getCatagoryID(category);
		ID += "-" + String.format("%04d", service.newID(category));
		return ID;
	}
	
	//�ж���Ʒ�Ƿ���ɾ��
	public boolean couldBeDeleted(String goodID){
		ManifestService service = new ManifestBL();
		ArrayList<ManifestPO> unchecked = service.getCheckedManifest();
		
		for(int i=0;i<unchecked.size();i++){
			ManifestPO po = unchecked.get(i);
			for(int j=0;j<po.getGoodsList().size();j++){
				ReciptGoodsPO goodsPO = po.getGoodsList().get(j);
				if(goodsPO.getGoodsID().equals(goodID))
					return false;
			}
		}
		
		return true;
	}

}
