package main.Data.CommodityData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.CommodityDataService.GoodsDataService;
import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class GoodsData implements GoodsDataService{

	public GoodsData() {
	}
	
	@Override
	public boolean add(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean delete(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean modify(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			if(po.getAmounts()<po.getAlertAmounts())
				try {
					sentAlertMessage(po);
				} catch (ClientException e) {
					e.printStackTrace();
				}
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<GoodsPO> find(String keyword, String type) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<GoodsPO> goods = null;
		String hql = "from GoodsPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<GoodsPO> results = query.list();
			goods = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return goods;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<GoodsPO> goods = null;
		
		try {
			tx = session.beginTransaction();

			for(int i=0; i<list.size(); i++){
				ReciptGoodsPO po = list.get(i);
				String hql = "from GoodsPO where " + "id" + " like '%" + po.getGoodsID() + "%'";
				Query query = session.createQuery(hql);
				List<GoodsPO> results = query.list();
				goods = new ArrayList<>(results);
				if(!goods.isEmpty()){
					GoodsPO goodspo = goods.get(0);
					int tmp = (int) (goodspo.getAmounts()-po.getAmounts());
					
					if(type.equals("JHTHD")&&lebal.equals("Unchecked")){
						
					}
					else if(type.equals("XSD")&&lebal.equals("Unchecked")){
						
					}
					else if(type.equals("JHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(goodspo.getAmounts()+po.getAmounts());
						goodspo.setRecentBid(po.getBid());
						double avgValue = (goodspo.getAvgValue()*goodspo.getAmounts()+po.getBid()*po.getAmounts()) / (goodspo.getAmounts()+po.getAmounts());
						goodspo.setAvgValue(avgValue);
					}
					else if(type.equals("XSTHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(goodspo.getAmounts()+po.getAmounts());
					}
					else if(type.equals("XSD")&&lebal.equals("Checked")){
						goodspo.setAmounts(tmp>0 ? tmp : 0);
						goodspo.setRecentBid(po.getBid());
					}
					else if(type.equals("JHTHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(tmp>0 ? tmp : 0);
						double avgValue = (goodspo.getAvgValue()*goodspo.getAmounts()+po.getBid()*po.getAmounts()) / (goodspo.getAmounts()+po.getAmounts());
						goodspo.setAvgValue(avgValue);
					}

					modify(goodspo);
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ResultMessage.SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int newID(String string){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		int ret = 0;
		String hql = "from GoodsPO where catagory ='" + string +"'";
		GoodsPO po = null;
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<GoodsPO> results = query.list();
			if(!results.isEmpty()){
				po = results.get(results.size()-1);
				String s = po.getID().substring(7);
				ret = Integer.parseInt(s);
			}else{
				ret = 0;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return ret+1;
	}
	
	@Override
	public void sentAlertMessage(GoodsPO po) throws ClientException{
		//���ó�ʱʱ��-�����е���
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//��ʼ��ascClient��Ҫ�ļ�������
		final String product = "Dysmsapi";//����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
		final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
		//�滻�����AK
		final String accessKeyId = "LTAIQjJDgdUWe46y";//���accessKeyId,�ο����ĵ�����2
		final String accessKeySecret = "UsqadsMvBRfeBPzgdsocmGEOhIf3sN";//���accessKeySecret���ο����ĵ�����2
		final String phoneNum = "18795957252";
		//��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //��װ�������
		 SendSmsRequest request = new SendSmsRequest();
		 //ʹ��post�ύ
		 request.setMethod(MethodType.POST);
		 //����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ
		 request.setPhoneNumbers(phoneNum);
		 //����:����ǩ��-���ڶ��ſ���̨���ҵ�
		 request.setSignName("�ƾ߽�����ϵͳ");
		 //����:����ģ��-���ڶ��ſ���̨���ҵ�
		 request.setTemplateCode("SMS_119925785");
		 //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
		 //������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
		 request.setTemplateParam("{\"gName\":\""+po.getName()+"\", \"gID\":\""+po.getID()+"\", \"num\":\""+po.getAmounts()+"\", \"alertNum\":\""+po.getAlertAmounts()+"\"}");
		 //��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
		 //request.setSmsUpExtendCode("90997");
		 //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
		 request.setOutId("yourOutId");
		//����ʧ���������ClientException�쳣
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			System.out.println("����֪ͨ�ɹ�");
		}
	}
}
