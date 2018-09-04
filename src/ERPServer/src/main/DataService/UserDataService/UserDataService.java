package main.DataService.UserDataService;

import java.util.ArrayList;

import main.PO.UserPO;

/**
 * @author Cauchy������
 */
public interface UserDataService {
	/**
	 * ��ѯ�û��˺ţ����ţ��Ƿ��Ѵ���
	 * @param jobNum
	 * @return boolean
	 */
	public boolean findUser(String jobNum);
	
	/**
	 * ���ݹ��Ż�ȡ�û���Ϣ
	 * @param jobNum
	 * @return UserPO
	 */
	public UserPO getUser(String jobNum);
	
	/**
	 * ��ȡ�û��б�
	 * @return ArrayList<UserPO>
	 */
	public ArrayList<UserPO> getUserList();
	
	/**
	 * ������û�
	 * @param po
	 * @return boolean
	 */
	public boolean addNewUser(UserPO po);
	
	/**
	 * �����û���Ϣ
	 * @param po
	 * @return boolean
	 */
	public boolean modifyUser(UserPO po);
	
	/**
	 * ɾ���û�
	 * @param po
	 * @return boolean
	 */
	public boolean deleteUser(UserPO po);
	
}
