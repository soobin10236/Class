package Pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketOption;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class mainClient extends Application{

	Socket cs = null;
	TextArea textArea;
	
	//Ŭ���̾�Ʈ ���α׷� ���� �޼���
//startClient();
	public void startClient(String IP, int port) {
		//���� ���α׷��� �ٸ��� �������� ������ ���ôٹ������� ���ܳ��� �����Ƿ� �ܼ� ������ ��ü ����
		new Thread() {
			@Override
			public void run() {
				try {
					//���� �ʱ�ȭ
					cs = new Socket(IP, port);
					//�޽��� ���޹ޱ� ���� receive �޼��� ȣ��
					receive();
				} catch (Exception e) {
					e.printStackTrace();
					//���� �߻�
					if(!cs.isClosed()) {
						//stopClient ȣ���Ͽ� Ŭ���̾�Ʈ ����
						stopClient();
						System.out.println("���� ���ӿ� �����Ͽ����ϴ�.");
						//���α׷� ����
						Platform.exit();
					}
				}
			}	
		}.start();
	}
//startClient();
	
	//Ŭ���̾�Ʈ ���� �޼���
//stopClient();
	public void stopClient() {
		try {
			//������ ���� ���¶��
			if(cs != null && !cs.isClosed()) {
				cs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//stopClient();
	
	//�����κ��� �޽����� �޴� �޼���
	//��� ���޹��� ���� ���� ������ �����ش�.
//receive();
	public void receive() {
		while (true) {
			try {
				//���� �����κ��� �޽����� ���޹��� �� �ֵ���.
				InputStream inputStream = cs.getInputStream();
				//512byte��ŭ ���ۿ� ��Ƽ� ��� ���� ���� ��.
				byte[] buffer = new byte[512];
				//read�Լ��� ������ �Է� �޴´�.
				int length = inputStream.read(buffer);
				//������ �Է¹޴� ���� ������ �߻��ϸ� IOException�� �߻���Ų��.
				if (length == -1) {throw new IOException();}
				//message�� ���ۿ� �ִ� ������ ���
				String message = new String(buffer,0,length);
				//ȭ�鿡 ���
				Platform.runLater(()->{
					//textArea�� GUI ��� �� �ϳ��ν� ȭ�鿡 ������ִ� ���
					textArea.appendText(message);
				});
			} catch (Exception e) {
				//������ �߻������ÿ��� stopClient ȣ�� �� break;
				stopClient();
				break;
			}
		}
	}
//receive();

	//������ �޽����� �����ϴ� �޼���
//send();
	public void send(String message) {
		// ���⼭�� �޽����� ������ �� �����带 ����ϴµ�
		// ������ �޽����� �����ϱ� ���� ������ 1��,
		// ������ ���� �޽����� ���۹ޱ� ���� ������ 1��
		// �� 2���� �����尡 ���� �ٸ� ������ ���Եȴ�.
		new Thread() {
			@Override
			public void run() {
				OutputStream outputStream;
				try {
					outputStream = cs.getOutputStream();
					//�������� �ϴ� ���� UTF-8�� ���ڵ�
					//�������� ���޹��� �� UTF-8�� ���ڵ� �� ���� �޵��� �صξ��� ����.
					byte[] buffer = message.getBytes();
					//�޽��� ����
					outputStream.write(buffer);
					//�޽��� ������ ���� �˸�
					outputStream.flush();
				} catch (IOException e) {
					//���� �߻��ϸ�
					stopClient();
				}
			}
		}.start();
	}
//send();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// System.out.println("������� 2");

		// �����۾�
		BorderPane root = new BorderPane(); // ����� -> ���η� ����, ȣ������ -> ���η� ����
		root.setPadding(new Insets(5));
		
		//BorderPane ���� �ϳ��� ���̾ƿ��� �� �־��ֱ� ���� ��.
		HBox hbox = new HBox();
		//����
		hbox.setSpacing(5);
		
		//����� �̸��� �� �� �ִ� �ؽ�Ʈ ����
		TextField userName = new TextField();
		userName.setPrefWidth(150); //�ʺ�
		userName.setPromptText("�г����� �Է��ϼ���.");
		
		//HBox ���ο� TextField�� �׻� ��� �ǵ���.
		HBox.setHgrow(userName, Priority.ALWAYS);
//IP		
		TextField IPText = new TextField("218.154.207.127");
		TextField portText = new TextField("5001");
		portText.setPrefWidth(80);
		
		//hbox �� 3���� �ؽ�Ʈ�ڽ� �߰�
		hbox.getChildren().addAll(userName,IPText,portText);
		
		//hbox�� borderPane ��ܿ� ��ġ
		root.setTop(hbox);
		
		textArea = new TextArea();
		//������ ������ �� ������ ����
		textArea.setEditable(false);
		//���̾ƿ�(borderPane)�� �߰��� ��ġ
		root.setCenter(textArea);
		
		TextField input = new TextField();
		input.setPrefWidth(Double.MAX_VALUE);
		input.setDisable(true);	//�����ϱ� ������ �޽����� ������ �� ���Ը����.
		
		input.setOnAction((AE)->{
			//������ �޽����� ���� �� �� �ֵ���.
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");	//���������� ����ĭ �ʱ�ȭ
			input.requestFocus();	//�ٽ� ���� �� �ֵ��� ����ĭ���� ��Ŀ��
		});
		
		Button sendBtn = new Button("������");
		sendBtn.setDisable(true); //�����ϱ� ������ ����� �� ����
		
		//��ư�� ������ �̺�Ʈ�� �߻��ϸ� �����͸� ����
		sendBtn.setOnAction((AE)->{
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");	//���������� ����ĭ �ʱ�ȭ
			input.requestFocus();	//�ٽ� ���� �� �ֵ��� ����ĭ���� ��Ŀ��
		});
		
		Button connectBtn = new Button("�����ϱ�");
		connectBtn.setOnAction((AE)->{
			//��ư�� ���� "�����ϱ�"�� Ŭ���ϸ�
			if(connectBtn.getText().equals("�����ϱ�")) {
				int port = 5001;
				
				//��Ʈ �Է�ĭ�� ����ִ� �ؽ�Ʈ ������ �������·� ��ȯ�ؼ� �ٽ� ���� �� �ֵ���.
				//����ڰ� ��Ʈ��ȣ ���� �����ϰ�.
				port = Integer.parseInt(portText.getText());
				
				//Ư���� IP�ּҿ� ��� port��ȣ�� ������ �� �ֵ���.
				startClient(IPText.getText(), port);
				
				//runLater�Լ��� ���������� ȭ�鿡 ���õ� ������ ��µ� �� �ֵ���..
				Platform.runLater(()->{
					textArea.appendText("[����]\n");
				});
				//�����ϰ� ��ư�̸� ����
				connectBtn.setText("�����ϱ�");
				//�����ϰ� ��� �����ϵ��� ����
				input.setDisable(false);
				sendBtn.setDisable(false);
				input.requestFocus();	//��Ŀ��
			} else {
				//�����ϱⰡ �ƴ� �����ϱ⸦ ���� ���
				stopClient();
				Platform.runLater(()->{
					textArea.setText("[����]\n");
				});
				//�����ϰ� ��ư�̸� ����
				connectBtn.setText("�����ϱ�");
				//�����ϰ� ��� �Ұ����ϵ��� ����
				input.setDisable(true);
				sendBtn.setDisable(true);
				
			}
		});
		
		//������ ������ ������ ���� ���� ����
		BorderPane pane = new BorderPane();
		pane.setLeft(connectBtn);
		pane.setCenter(input);
		pane.setRight(sendBtn);
		
		root.setBottom(pane);
		
		Scene scene = new Scene(root,600,600);
		primaryStage.setTitle("[ä�� Ŭ���̾�Ʈ]");
		primaryStage.setScene(scene);  // UI���� ����� Scene���� ���
		//ȭ��ݱ� ��ư ������ stopClient() ����
		primaryStage.setOnCloseRequest((AE)->{
			stopClient();
		});
		primaryStage.show(); // ����� Scene�� ������
		// ---------------------------------------------
	}

	public static void main(String[] args) {
		// System.out.println("������� 1");
		launch(args); // launcher ���� ���ŷ ������. ���� �����ϸ� ����
		// System.out.println("������� 4");
		System.out.println("Exit");
	}

}
