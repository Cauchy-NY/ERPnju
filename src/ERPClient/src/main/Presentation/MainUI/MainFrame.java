package main.Presentation.MainUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.RMI.RemoteHelper;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * �������ڣ��ṩ�˶Խ����һЩ�����������رգ���С������ת��
 * @author ��Ԭ������ΰ
 * @version 1.0
 * @date 2017/11/27
 */
public class MainFrame extends Application {
	public static final double WIDTH = 1280;
	public static final double HEIGHT = 720;
	
	private static Scene scene;
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		RemoteHelper.initRMI();

		stage = primaryStage;
		
		stage.initStyle(StageStyle.UNDECORATED);//�趨�����ޱ߿�
		stage.setResizable(false);
		stage.setTitle("ERPnju");

		Parent loginUI = LoginUI.init();
		scene = new Scene(loginUI, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * ����Scene��root
	 * @param Ҫ������Ϊroot�����
	 */
	public static void setSceneRoot(Parent s){
		scene.setRoot(s);
	}
	
	/**
	 * ������С���ķ�����ʹ����������С��
	 */
	public static void minimize(){
		stage.setIconified(true);
	}
}
