package main.BussinessLogicService.LogBLService;

import java.util.ArrayList;

import main.VO.LogVO;

public interface LogBLService {
	/**
	 * ͨ���ؼ��ֺ����Ͳ�����־
	 * @param keyword
	 * @param type
	 * @return ArrayList<LogVO>
	 */
	public ArrayList<LogVO> findLog(String keyword, String type);
	
	/**
	 * ͨ����ʼ�ͽ���ʱ�������־
	 * @param startTime
	 * @param endTime
	 * @return ArrayList<LogVO>
	 */
	public ArrayList<LogVO> findLogByTime(String startTime, String endTime);
}
