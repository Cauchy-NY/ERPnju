package main.BussinessLogic.UserBL;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

import main.Data.UserData.UserData;
import main.DataService.UserDataService.UserDataService;
import main.PO.UserPO;
import main.VO.UserVO;
import main.utility.FaceConnectionNum;
import main.utility.UserType;

public class UserBL {
	
	UserDataService uds = new UserData();

	public UserType login(String jobNum, String password) {
		if(uds.findUser(jobNum)) {
			UserPO po = uds.getUser(jobNum);
			String rightPassword = decode(po.getPassword());
			if(password.equals(rightPassword)) {
				if(po.getType().equals("FinancialStaff")) {
					return UserType.FINANCIAL_STAFF;
				}
				else if(po.getType().equals("Manager")) {
					return UserType.MANAGER;
				}
				else if(po.getType().equals("SalesMan")) {
					return UserType.SALES_MAN;
				}
				else {
					return UserType.STOCK_MANAGER;
				}
			}
			else {
				return UserType.PASSWORD_WRONG;
			}
		}
		else {
			return UserType.NOT_FOUND;
		}
	}
	
	public UserType faceLogin(String jobNum, String imagePath) {
		if(uds.findUser(jobNum)) {
	        // ��ʼ��һ��AipFace
	        AipFace client = new AipFace(FaceConnectionNum.APP_ID, FaceConnectionNum.API_KEY, FaceConnectionNum.SECRET_KEY);
	
	        // ��ѡ�������������Ӳ���
	        client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);
	        
	        //�������Ϊ�ĸ���
	        String groupId = "Manager,FinancialStaff,StockManager,SalesMan";
	        
	        //�ж��Ƿ�������ˣ����У�ȥ���ݿ��в����乤��
	        if(!sendLoginMessage(client, jobNum, groupId, imagePath)) {
	        	return UserType.PASSWORD_WRONG;
	        }
	        else {
	        	String type = uds.getUser(jobNum).getType();
	        	if(type.equals("FinancialStaff")) {
					return UserType.FINANCIAL_STAFF;
				}
				else if(type.equals("Manager")) {
					return UserType.MANAGER;
				}
				else if(type.equals("SalesMan")) {
					return UserType.SALES_MAN;
				}
				else {
					return UserType.STOCK_MANAGER;
				}
	        }
		}
		else {
			return UserType.NOT_FOUND;
		}
	}
	
	private boolean sendLoginMessage(AipFace client, String uid, String groupId, String imagePath) {
		// �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("ext_fields", "faceliveness");
        
        //�������ƶ���ֵ��������Ϊ80%����
        double loginSimilarity = 80;

        // ����Ϊ����ͼƬ·��
        String image = imagePath;
        JSONObject res = client.verifyUser(uid, groupId, image, options);
        String temp = res.optQuery("/result").toString();
        double similarity = Double.valueOf(temp.substring(1, temp.length()-1));
        
        if(similarity > loginSimilarity)
        	return true;
        else 
        	return false;
	}
	
	public UserType register(UserPO po) {
		if(uds.findUser(po.getJobNum())) {
			return UserType.ALREADY_EXIT;
		}
		else {
			po.setPassword(encryperment(po.getPassword()));
			uds.addNewUser(po);
			return UserType.REGISTER_SUCCESS;
		}
	}

	public UserType setFaceImage(String jobNum, String password, String imagePath) {
		if(uds.findUser(jobNum)) {
			UserPO po = uds.getUser(jobNum);
			if(decode(po.getPassword()) == password) {
				// ��ʼ��һ��AipFace
		        AipFace client = new AipFace(FaceConnectionNum.APP_ID, FaceConnectionNum.API_KEY, FaceConnectionNum.SECRET_KEY);
		
		        // ��ѡ�������������Ӳ���
		        client.setConnectionTimeoutInMillis(2000);
		        client.setSocketTimeoutInMillis(60000);
		        
		        //�������Ϊ��ӦԱ������
		        String groupId = "";
		        String type = po.getType();
		        
		        groupId = type;
		        
		        //���ÿͻ���Ϣ
		        String userInfo = po.getJobNum() + "_" + po.getName() + "_" + po.getSex() + "_" + po.getAge();
		        
		        faceRegister(client, jobNum, userInfo, groupId, imagePath);
		        
		        if(type.equals("FinancialStaff")) {
					return UserType.FINANCIAL_STAFF;
				}
				else if(type.equals("Manager")) {
					return UserType.MANAGER;
				}
				else if(type.equals("SalesMan")) {
					return UserType.SALES_MAN;
				}
				else {
					return UserType.STOCK_MANAGER;
				}
			}
			else {
				return UserType.PASSWORD_WRONG;
			}
		}
		else {
			return UserType.NOT_FOUND;
		}
	}
	
	public void faceRegister(AipFace client, String uid, String userInfo, String groupId, String imagePath) {
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        // ����Ϊ����ͼƬ·��
        String image = imagePath;
        JSONObject res = client.addUser(uid, userInfo, groupId, image, options);
        System.out.println(res.toString(2));
	}

	public UserVO getUser(String jobNum) {
		return new UserVO(uds.getUser(jobNum));
	}
	
	public ArrayList<UserVO> getUserList() {
		ArrayList<UserPO> poList = uds.getUserList();
		ArrayList<UserVO> result = new ArrayList<UserVO>();
		for(int i = 0; i < poList.size(); i++) {
			result.add(new UserVO(poList.get(i)));
		}
		return result;
	}

	public boolean modifyUser(UserPO po) {
		return uds.modifyUser(po);
	}

	public boolean deleteUser(UserPO po) {
		return uds.deleteUser(po);
	}
	
	private String encryperment(String password) {
		return password;
	}
	
	private String decode(String password) {
		return password;
	}
	
}
