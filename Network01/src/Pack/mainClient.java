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
	
	//클라이언트 프로그램 동작 메서드
//startClient();
	public void startClient(String IP, int port) {
		//서버 프로그램과 다르게 여러개의 스레드 동시다발적으로 생겨나지 않으므로 단순 스레드 객체 생성
		new Thread() {
			@Override
			public void run() {
				try {
					//소켓 초기화
					cs = new Socket(IP, port);
					//메시지 전달받기 위해 receive 메서드 호출
					receive();
				} catch (Exception e) {
					e.printStackTrace();
					//오류 발생
					if(!cs.isClosed()) {
						//stopClient 호출하여 클라이언트 종료
						stopClient();
						System.out.println("서버 접속에 실패하였습니다.");
						//프로그램 종료
						Platform.exit();
					}
				}
			}	
		}.start();
	}
//startClient();
	
	//클라이언트 종료 메서드
//stopClient();
	public void stopClient() {
		try {
			//소켓이 열린 상태라면
			if(cs != null && !cs.isClosed()) {
				cs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//stopClient();
	
	//서버로부터 메시지를 받는 메서드
	//계속 전달받이 위해 무한 루프를 돌려준다.
//receive();
	public void receive() {
		while (true) {
			try {
				//현재 서버로부터 메시지를 전달받을 수 있도록.
				InputStream inputStream = cs.getInputStream();
				//512byte만큼 버퍼에 담아서 계속 전달 받을 것.
				byte[] buffer = new byte[512];
				//read함수로 실제로 입력 받는다.
				int length = inputStream.read(buffer);
				//내용을 입력받는 동안 오류가 발생하면 IOException을 발생시킨다.
				if (length == -1) {throw new IOException();}
				//message에 버퍼에 있는 정보를 담기
				String message = new String(buffer,0,length);
				//화면에 출력
				Platform.runLater(()->{
					//textArea는 GUI 요소 중 하나로써 화면에 출력해주는 요소
					textArea.appendText(message);
				});
			} catch (Exception e) {
				//오류가 발생했을시에는 stopClient 호출 후 break;
				stopClient();
				break;
			}
		}
	}
//receive();

	//서버로 메시지를 전송하는 메서드
//send();
	public void send(String message) {
		// 여기서도 메시지를 전송할 때 스레드를 사용하는데
		// 서버로 메시지를 전송하기 위한 스레드 1개,
		// 서버로 부터 메시지를 전송받기 위한 스레드 1개
		// 총 2개의 스레드가 각각 다른 역할을 갖게된다.
		new Thread() {
			@Override
			public void run() {
				OutputStream outputStream;
				try {
					outputStream = cs.getOutputStream();
					//보내고자 하는 것을 UTF-8로 인코딩
					//서버에서 전달받을 때 UTF-8로 인코딩 된 것을 받도록 해두었기 때문.
					byte[] buffer = message.getBytes();
					//메시지 전송
					outputStream.write(buffer);
					//메시지 전송의 끝을 알림
					outputStream.flush();
				} catch (IOException e) {
					//오류 발생하면
					stopClient();
				}
			}
		}.start();
	}
//send();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// System.out.println("실행순서 2");

		// 선두작업
		BorderPane root = new BorderPane(); // 버츄얼 -> 세로로 형성, 호라이즌 -> 가로로 형성
		root.setPadding(new Insets(5));
		
		//BorderPane 위에 하나의 레이아웃을 더 넣어주기 위한 것.
		HBox hbox = new HBox();
		//여백
		hbox.setSpacing(5);
		
		//사용자 이름이 들어갈 수 있는 텍스트 공간
		TextField userName = new TextField();
		userName.setPrefWidth(150); //너비
		userName.setPromptText("닉네임을 입력하세요.");
		
		//HBox 내부에 TextField가 항상 출력 되도록.
		HBox.setHgrow(userName, Priority.ALWAYS);
//IP		
		TextField IPText = new TextField("218.154.207.127");
		TextField portText = new TextField("5001");
		portText.setPrefWidth(80);
		
		//hbox 에 3개의 텍스트박스 추가
		hbox.getChildren().addAll(userName,IPText,portText);
		
		//hbox가 borderPane 상단에 위치
		root.setTop(hbox);
		
		textArea = new TextArea();
		//내용을 수정할 수 없도록 설정
		textArea.setEditable(false);
		//레이아웃(borderPane)의 중간에 위치
		root.setCenter(textArea);
		
		TextField input = new TextField();
		input.setPrefWidth(Double.MAX_VALUE);
		input.setDisable(true);	//접속하기 전에는 메시지를 전송할 수 없게만든다.
		
		input.setOnAction((AE)->{
			//서버로 메시지를 전달 할 수 있도록.
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");	//전송했으니 전송칸 초기화
			input.requestFocus();	//다시 보낼 수 있도록 전송칸으로 포커싱
		});
		
		Button sendBtn = new Button("보내기");
		sendBtn.setDisable(true); //접속하기 전에는 사용할 수 없음
		
		//버튼을 누르는 이벤트가 발생하면 데이터를 전송
		sendBtn.setOnAction((AE)->{
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");	//전송했으니 전송칸 초기화
			input.requestFocus();	//다시 보낼 수 있도록 전송칸으로 포커싱
		});
		
		Button connectBtn = new Button("접속하기");
		connectBtn.setOnAction((AE)->{
			//버튼이 현재 "접속하기"를 클릭하면
			if(connectBtn.getText().equals("접속하기")) {
				int port = 5001;
				
				//포트 입력칸에 들어있는 텍스트 내용을 정수형태로 변환해서 다시 담을 수 있도록.
				//사용자가 포트번호 설정 가능하게.
				port = Integer.parseInt(portText.getText());
				
				//특정한 IP주소에 어떠한 port번호로 접속할 수 있도록.
				startClient(IPText.getText(), port);
				
				//runLater함수로 실질적으로 화면에 관련된 내용이 출력될 수 있도록..
				Platform.runLater(()->{
					textArea.appendText("[접속]\n");
				});
				//접속하고 버튼이름 변경
				connectBtn.setText("종료하기");
				//접속하고 사용 가능하도록 변경
				input.setDisable(false);
				sendBtn.setDisable(false);
				input.requestFocus();	//포커싱
			} else {
				//접속하기가 아닌 종료하기를 누를 경우
				stopClient();
				Platform.runLater(()->{
					textArea.setText("[퇴장]\n");
				});
				//종료하고 버튼이름 변경
				connectBtn.setText("접속하기");
				//종료하고 사용 불가능하도록 변경
				input.setDisable(true);
				sendBtn.setDisable(true);
				
			}
		});
		
		//위에서 설정한 내용을 넣을 공간 생성
		BorderPane pane = new BorderPane();
		pane.setLeft(connectBtn);
		pane.setCenter(input);
		pane.setRight(sendBtn);
		
		root.setBottom(pane);
		
		Scene scene = new Scene(root,600,600);
		primaryStage.setTitle("[채팅 클라이언트]");
		primaryStage.setScene(scene);  // UI구성 장면을 Scene으로 등록
		//화면닫기 버튼 누르면 stopClient() 실행
		primaryStage.setOnCloseRequest((AE)->{
			stopClient();
		});
		primaryStage.show(); // 등록한 Scene을 보여줌
		// ---------------------------------------------
	}

	public static void main(String[] args) {
		// System.out.println("실행순서 1");
		launch(args); // launcher 에서 블로킹 잡힌다. 런쳐 종료하면 나옴
		// System.out.println("실행순서 4");
		System.out.println("Exit");
	}

}
