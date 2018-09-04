package main.BussinessLogic.CommodityBL;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import main.BussinessLogic.ManifestBL.ManifestBL;
import main.PO.CommodityReciptPO;
import main.PO.GoodsPO;
import main.PO.ManifestPO;
import main.PO.ReciptGoodsPO;
import main.VO.CommodityInfoVO;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;
/**
 * 
 * @author �����
 *
 */
public class CommodityBL implements CommodityService{
	
	CommodityReciptBL reciptBL;
	GoodsBL goodsBL;
	
	public CommodityBL() {
		reciptBL = new CommodityReciptBL();
		goodsBL = new GoodsBL();
	}

	//���鿴��Ӧʵ�ַ���
	public CommodityInfoVO viewCommodity(String startTime, String endTime){

		ArrayList<CommodityReciptVO> commodityReciptVOs = getCheckedCommodityRecipt(startTime, endTime, "", "");
		ArrayList<ManifestPO> manifests = new ManifestBL().findManifestByTime(startTime, endTime);
		CommodityInfoVO retVo = new CommodityInfoVO();
	
		int commodityInAmounts = 0;
		int commodityOutAmounts= 0;
		int saleAmounts = 0;
		int goodsInAmounts = 0;
		double commodityInSumValue = 0;
		double commodityOutSumValue = 0;
		double saleAmountsSumValue = 0;
		double goodsInAmountsSumValue = 0;
		
		//�ֱ�Ը������б��Ӧ
		HashMap<String, ReciptGoodsVO> commodityInMap = new HashMap<>();
		HashMap<String, ReciptGoodsVO> commodityOutMap = new HashMap<>();
		HashMap<String, ReciptGoodsVO> saleMap = new HashMap<>();
		HashMap<String, ReciptGoodsVO> goodsInMap = new HashMap<>();
		
		if(!commodityReciptVOs.isEmpty()){
			for(CommodityReciptVO vo:commodityReciptVOs){
				if(vo.getType().equals("BY")){
					String goodsID = vo.getGoodsID();
					ReciptGoodsVO goodsVO = null;
					if(commodityInMap.containsKey(goodsID)){
						goodsVO = commodityInMap.get(goodsID);
						goodsVO.setAmounts(goodsVO.getAmounts()+vo.getChangedNumbers());
					}
					else{
						goodsVO = new ReciptGoodsVO(findGoodsByID(vo.getGoodsID()));
						goodsVO.setAmounts(vo.getChangedNumbers());
						commodityInMap.put(goodsID, goodsVO);					
					}
					commodityInAmounts += vo.getChangedNumbers();
					commodityInSumValue += vo.getChangedNumbers()*commodityInMap.get(goodsID).getBid();
					goodsVO.setSum(goodsVO.getSum()+goodsVO.getBid()*vo.getChangedNumbers());
				}
				else if(vo.getType().equals("BS")||vo.getType().equals("ZS")){
					String goodsID = vo.getGoodsID();
					ReciptGoodsVO goodsVO = null;
					if(commodityOutMap.containsKey(goodsID)){
						goodsVO = commodityOutMap.get(goodsID);
						goodsVO.setAmounts(goodsVO.getAmounts()+vo.getChangedNumbers());
					}
					else{
						goodsVO = new ReciptGoodsVO(findGoodsByID(vo.getGoodsID()));
						goodsVO.setAmounts(vo.getChangedNumbers());
						commodityOutMap.put(goodsID, goodsVO);					
					}
					commodityOutAmounts += vo.getChangedNumbers();
					commodityOutSumValue += vo.getChangedNumbers()*commodityInMap.get(goodsID).getBid();
					goodsVO.setSum(goodsVO.getSum()+goodsVO.getBid()*vo.getChangedNumbers());
				}
			}
		}	
		
		if(!manifests.isEmpty()){
			for(ManifestPO vo : manifests){
				ArrayList<ReciptGoodsPO> goodslist = vo.getGoodsList();
				if(vo.getType().equals("JHD")||vo.getType().equals("XSTHD")){
					for(int i=0; i<goodslist.size(); i++){
						ReciptGoodsPO good = goodslist.get(i);
						if(commodityInMap.containsKey(good.getGoodsID())){
							ReciptGoodsVO goodsVO = commodityInMap.get(good.getGoodsID());
							goodsVO.setAmounts(goodsVO.getAmounts()+good.getAmounts());
							goodsVO.setSum(goodsVO.getSum()+good.getAmounts()*good.getBid());
						}
						else{
							commodityInMap.put(good.getGoodsID(), new ReciptGoodsVO(good));					
						}
						commodityInAmounts += good.getAmounts();
						commodityInSumValue += good.getAmounts()*good.getBid();
					}
				}
				else if(vo.getType().equals("XSD")||vo.getType().equals("JHTHD")){
					for(int i=0; i<goodslist.size(); i++){
						ReciptGoodsPO good = goodslist.get(i);
						if(commodityOutMap.containsKey(good.getGoodsID())){
							ReciptGoodsVO goodsVO = commodityOutMap.get(good.getGoodsID());
							goodsVO.setAmounts(goodsVO.getAmounts()+good.getAmounts());
							goodsVO.setSum(goodsVO.getSum()+good.getAmounts()*good.getBid());
						}
						else{
							commodityOutMap.put(good.getGoodsID(), new ReciptGoodsVO(good));					
						}
						commodityOutAmounts += good.getAmounts();
						commodityOutSumValue += good.getAmounts()*good.getBid();

					}
				}
			}
		}
		
		if(!manifests.isEmpty()){
			for(ManifestPO vo : manifests){
				ArrayList<ReciptGoodsPO> goodslist = vo.getGoodsList();
				if(vo.getType().equals("XSD")){
					for(int i=0; i<goodslist.size(); i++){
						ReciptGoodsPO good = goodslist.get(i);
						if(saleMap.containsKey(good.getGoodsID())){
							ReciptGoodsVO goodsVO = saleMap.get(good.getGoodsID());
							goodsVO.setAmounts(goodsVO.getAmounts()+good.getAmounts());
							goodsVO.setSum(goodsVO.getSum()+good.getAmounts()*good.getBid());
						}
						else{
							saleMap.put(good.getGoodsID(), new ReciptGoodsVO(good));					
						}
						saleAmounts += good.getAmounts();
						saleAmountsSumValue += good.getAmounts()*good.getBid();
					}
				}
				else if(vo.getType().equals("JHD")){
					for(int i=0; i<goodslist.size(); i++){
						ReciptGoodsPO good = goodslist.get(i);
						if(goodsInMap.containsKey(good.getGoodsID())){
							ReciptGoodsVO goodsVO = goodsInMap.get(good.getGoodsID());
							goodsVO.setAmounts(goodsVO.getAmounts()+good.getAmounts());
							goodsVO.setSum(goodsVO.getSum()+good.getAmounts()*good.getBid());
						}
						else{
							goodsInMap.put(good.getGoodsID(), new ReciptGoodsVO(good));					
						}
						goodsInAmounts += good.getAmounts();
						goodsInAmountsSumValue += good.getAmounts()*good.getBid();

					}
				}
			}
		}
		
		//ΪretVO�������
		ArrayList<ReciptGoodsVO> commodityInList = new ArrayList<>(commodityInMap.values());
		ArrayList<ReciptGoodsVO> commodityOutList = new ArrayList<>(commodityOutMap.values());
		ArrayList<ReciptGoodsVO> saleList = new ArrayList<>(saleMap.values());
		ArrayList<ReciptGoodsVO> goodsInList = new ArrayList<>(goodsInMap.values());
		
		retVo.setCommodityInList(commodityInList);
		retVo.setCommodityOutList(commodityOutList);
		retVo.setSaleList(saleList);
		retVo.setGoodsInList(goodsInList);
		
		retVo.setCommodityInAmounts(commodityInAmounts);
		retVo.setCommodityOutAmouts(commodityOutAmounts);
		retVo.setSaleAmounts(saleAmounts);
		retVo.setGoodsInAmounts(goodsInAmounts);
		
		retVo.setCommodityInSumValue(commodityInSumValue);
		retVo.setCommodityOutSumValue(commodityOutSumValue);
		retVo.setSaleAmountsSumValue(saleAmountsSumValue);
		retVo.setGoodsInAmountsSumValue(goodsInAmountsSumValue);
		
		return retVo;
	}
	
	//ͨ��id�ҵ���
	public GoodsVO findGoodsByID(String id){
		ArrayList<GoodsPO> goodsPOs = new GoodsBL().find(id, "ID");
		GoodsVO vo = new GoodsVO("", "");
		
		if(!goodsPOs.isEmpty())
			vo = new GoodsVO(goodsPOs.get(0));
		
		return vo;
	}

	//���ؼ��ֵ�type������Ʒ
	public ArrayList<GoodsPO> getGoodsList(String keyword,String type){
		return goodsBL.find(keyword, type);
	}
	
	//����������Ʒ�б�
	public ArrayList<GoodsPO> getGoodsList(String all){
		return goodsBL.find("", "id");
	}

	@Override
	public ArrayList<CommodityReciptPO> getUncheckedCommodityRecipt() {

		return reciptBL.getUncheckedCommodityRecipt();
	}

	@Override
	public ResultMessage saveCommodityRecipt(ArrayList<CommodityReciptPO> list) {

		return reciptBL.saveCommodityRecipt(list);
	}

	@Override
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal) {

		return goodsBL.updateCommodity(list, type, lebal);
	}

	@Override
	public ArrayList<CommodityReciptVO> getCheckedCommodityRecipt(String startTime, String endTime, String customerName,
			String operator) {

		return reciptBL.getCheckedCommodityRecipt(startTime, endTime, customerName, operator);
	}

	@Override
	public ResultMessage createGiftCommodityRecipt(ArrayList<ReciptGoodsPO> giftlist) {

		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

		for(int i=0;i<giftlist.size();i++){
			ReciptGoodsPO good = giftlist.get(i);
			CommodityReciptVO vo = new CommodityReciptVO("ZS", good.getName(), good.getGoodsID(), good.getAmounts(), date, "Checked", "");
			reciptBL.setGiftList(vo);
		}
		
		return ResultMessage.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	//�����鿴��Ϣת��ΪExcel
	public ResultMessage checkCommodityInfoToExcel(String fileName, String storeAdress){
		 // ��һ��������һ��webbook����Ӧһ��Excel�ļ�    
        HSSFWorkbook wb = new HSSFWorkbook();    
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet    
        HSSFSheet sheet = wb.createSheet("��һ");    
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short    
        HSSFRow row = sheet.createRow(0);    
        
        // ��Ԫ����ʽ
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFCell cell = row.createCell((short) 0);    
        cell.setCellValue("����");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 1);    
        cell.setCellValue("�ͺ�");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 2);    
        cell.setCellValue("����");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 3);    
        cell.setCellValue("������");    
        cell.setCellStyle(cellStyle);
    
        // ���岽��д��ʵ������    
        ArrayList<GoodsVO> list = new GoodsBL().goodsFind("", "name");    
    
        for (int i = 0; i < list.size(); i++)    
        {   
        	GoodsVO vo = list.get(i);
            row = sheet.createRow((int) i + 1);  
            //������Ԫ��  
            row.createCell(0).setCellValue(vo.getName()); 
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(vo.getVersion());
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(vo.getAmounts()); 
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(vo.getAvgValue());
            row.getCell(3).setCellStyle(cellStyle);
        }    
        // �����������ļ��浽ָ��λ��    
        try    
        {    
            FileOutputStream fout = new FileOutputStream(storeAdress+"\\"+fileName);    
            wb.write(fout);    
            fout.close();  
            wb.close();
        }    
        catch (Exception e)    
        {    
            e.printStackTrace();    
        }  
	
		return ResultMessage.SUCCESS;
	}

}
