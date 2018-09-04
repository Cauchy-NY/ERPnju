package main.BussinessLogic.BankAccountBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

public class BankAccountBLController extends UnicastRemoteObject implements BankAccountBLService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankAccountBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	BankAccountBL bl = new BankAccountBL();
	LogBL logBl = new LogBL();

	@Override
	public ResultMessage add(BankAccountVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "�����һ�������˻�");
		return bl.add(vo);
	}

	@Override
	public ResultMessage delete(String id, String operator) throws RemoteException {
		logBl.createLog(operator, "ɾ����һ�������˻�");
		return bl.delete(id);
	}

	@Override
	public ResultMessage modify(BankAccountVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "������һ�������˻�");
		return bl.modify(vo);
	}

	@Override
	public BankAccountVO find(String id) throws RemoteException {
		return bl.find(id);
	}
	
	@Override
	public ArrayList<BankAccountVO> partFind(String partId) throws RemoteException {
		return bl.getBankAccount(partId);
	}

	@Override
	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException {
		return bl.getBankAccountList();
	}

}
