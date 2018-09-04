package main.BussinessLogicService.UserBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.UserVO;
import main.utility.UserType;

/**
 * @author Cauchy������
 */
public interface UserBLService extends Remote {
	/**
	 * ��ͨ��½����
	 * @param jobNum
	 * @param password
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType login(String jobNum, String password) throws RemoteException;
	
	/**
	 * ����ʶ���½����
	 * @param jobNum
	 * @param imagePath
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType faceLogin(String jobNum, String imagePath) throws RemoteException;
	
	/**
	 * ����ע�᷽��
	 * @param jobNum
	 * @param password
	 * @param imagePath
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType setFaceImage(String jobNum, String password, String imagePath) throws RemoteException;
	
	/**
	 * ��ͨע�᷽��
	 * @param vo
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType register(UserVO vo) throws RemoteException;
	
	/**
	 * ���ݹ��Ż�ȡ�û���Ϣ
	 * @param jobNum
	 * @return UserVO
	 * @throws RemoteException
	 */
	public UserVO getUser(String jobNum) throws RemoteException;
	
	/**
	 * ��ȡ�û��б�
	 * @return ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException;
	
	/**
	 * �޸Ŀͻ���Ϣ
	 * @param vo
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean modifyUser(UserVO vo) throws RemoteException;
	
	/**
	 * ɾ���ͻ�
	 * @param vo
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deleteUser(UserVO vo) throws RemoteException;

}
