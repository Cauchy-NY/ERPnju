package main.Presentation.MainUI;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;
import main.VO.UserVO;

public class MainUIController {
	
	protected static String username = null;		//��¼��ǰ��¼���û�
	
	protected static UserVO user = null;            //��¼��ǰ��¼�û���������Ϣ
	
	@FXML
	/**
	 * �˳���ť�ļ���
	 */
	public void exit(){
		//��ʾ�Ƿ�ȷ���˳�
		
		System.exit(0);
	}
	
	/**
	 * ��С����ť�ļ���
	 */
	@FXML
	public void minimize(){
		MainFrame.minimize();
	}

	@FXML
	public void buttonEnterEffect(Event e){
		Button toEffect = (Button) e.getSource();
		toEffect.setStyle("-fx-background-color: rgba(0,0,0, 0.15, 0.5, 0.5)");
	}
	
	@FXML
	public void buttonPressEffect(Event e){
		Button toEffect = (Button) e.getSource();
		toEffect.setStyle("-fx-background-color: rgba(0,0,0, 0.35, 0.5, 0.5)");
	}

	@FXML
	public void buttonExitEffect(Event e){
		Button toEffect = (Button) e.getSource();
		toEffect.setStyle("-fx-background-color: null");
	}
	
	@FXML
	public void buttonEnterEffect1(Event e) {
		Button toEffect = (Button) e.getSource();
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(toEffect.translateYProperty(), -10);
		KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
//		toEffect.setTranslateY(-10);
//		buttonEnterEffect(e);
	}
	
	@FXML
	public void buttonExitEffect1(Event e) {
		Button toEffect = (Button) e.getSource();
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(toEffect.translateYProperty(), 10);
		KeyFrame kf = new KeyFrame(Duration.millis(100), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
//		buttonExitEffect(e);
	}
}
