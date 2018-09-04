package test;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;

public class FaceTest {
    //����APPID/AK/SK
    public static final String APP_ID = "10611221";
    public static final String API_KEY = "0CSee5SLiazykbR9hSHKg9mj";
    public static final String SECRET_KEY = "dPXIfgNXBrCZvnNmsCMPpaGXnenuRHA1";
    
    public void register(AipFace client, String uid, String userInfo, String groupId, String imagePath) {
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("action_type", "replace");

        // ����Ϊ����ͼƬ·��
        String image = imagePath;
        JSONObject res = client.addUser(uid, userInfo, groupId, image, options);
        System.out.println(res.toString(2));

//        // ����Ϊ����ͼƬ����������
//        byte[] file = readImageFile(image);
//        res = client.addUser(file, uid, userInfo, groupId, options);
//        System.out.println(res.toString(2));
    }
    
    public void faceLogin(AipFace client, String uid, String groupId, String imagePath) {
        // �����ѡ�������ýӿ�
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("top_num", "3");
        options.put("ext_fields", "faceliveness");

        // ����Ϊ����ͼƬ·��
        String image = imagePath;
        JSONObject res = client.verifyUser(uid, groupId, image, options);
        String temp = res.optQuery("/result").toString();
        double similarity = Double.valueOf(temp.substring(1, temp.length()-1));
        System.out.println(similarity);

//        // ����Ϊ����ͼƬ����������
//        byte[] file = readImageFile(image);
//        res = client.verifyUser(file, uid, groupId, options);
//        System.out.println(res.toString(2));
    }

    public static void main(String[] args) {
    	//��ʼ������
    	FaceTest sample = new FaceTest();
    	
        // ��ʼ��һ��AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // ��ѡ�������������Ӳ���
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

//        // ��ѡ�����ô����������ַ, http��socket��ѡһ�����߾�������
//        client.setHttpProxy("proxy_host", proxy_port);  // ����http����
//        client.setSocketProxy("proxy_host", proxy_port);  // ����socket����
//
//        // ���ýӿ�
//        String path = "E://test.jpg";
//        JSONObject res = client.detect(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
        
        //�����ļ�·��
        String localImagePath = "E://test03.jpg";
        //�û�id�������֡���ĸ���»������
        String uid = "KC_000001";
        //�û���Ϣ����
        String userInfo = "test : a StockManager staff zyb...";
        /* �����Ҫ��һ��uidע�ᵽ���group�£�group_id��Ҫ�ö�����ŷָ���
         * ÿ��group_id��������Ϊ48��Ӣ���ַ���
         * group���赥��������ע���û�ʱ����Զ�����group��*/
        String groupId = "StockManager";
        //String groupId = "Manager,FinancialStaff,StockManager,SalesMan";
        sample.register(client, uid, userInfo, groupId, localImagePath);
        //sample.faceLogin(client, uid, groupId, localImagePath);
    }
}
