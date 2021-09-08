package Pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//class Client Start
class Client{
	//field
	Socket ss;
	
	//constructor
	//매개변수를 통해 넘어오는 것을 이용해서 소켓을 초기화하는 생성자
	Client(Socket ss){
		this.ss = ss;
		receive();
	}
	
	//method
	//클라이언트로부터 메시지를 전달받는 메서드
//receive();
	public void receive() {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				//클라이언트로부터 반복적으로 내용을 전달받을 수 있도록한다.
				try{
					while(true) {
						
							//내용을 받을 InputStream 생성
							InputStream in = ss.getInputStream();
							byte[] buffer = new byte[512];
							int length = in.read(buffer);
							
							//메시지를 읽을때 오류 발생 체크
							if(length == -1) throw new IOException();
							System.out.println("[메시지 수신 성공]" + ss.getRemoteSocketAddress() +
												 ";" + Thread.currentThread().getName());
												//Thread 고유한 정보 출력
							//buffer에서 전달받은 내용을 message에 담아 출력
							String message = new String(buffer,0,length,"UTF-8");
							
							//전달받을 뿐만 아니라, 다른 클라이언트에게도 보낸다.
							for (Client client : Server.clients) {
								client.send(message);
							}
					}
				} catch(Exception e){
					//오류 발생 처리
					//중첩 형식으로 try-catch구문 많이 사용함
					try {
						System.out.println("[메시지 수신 오류]" + ss.getRemoteSocketAddress() +
								 ";" + Thread.currentThread().getName());
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
			
		};
		// Thread를 안정적으로 관리하기 위해서 Thread를 ThreadPool에 등록해준다.
		Server.threadPool.submit(thread);
		
	}
//receive();
	
//send();
	//클라이언트에게 메시지를 전송하는 메서드
	public void send(String message) {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				//클라이언트에게내용을 보낼 수 있도록한다.
				try{
					//메시지를 보낼때는 OutStream 이용
					OutputStream out = ss.getOutputStream();
					byte[] buffer = message.getBytes();

					//오류발생 없으면 버퍼에 담긴 내용을 서버에서 클라이언트로 전송
					out.write(buffer);
					//반드시 flush를 해줘야 여기까지 전송해줬다는 것을 알려줄 수 있음
					out.flush();

				} catch(Exception e){
					//오류 발생 처리
					//중첩 형식으로 try-catch구문 많이 사용함
					try {
						System.out.println("[메시지 송신 오류]" + ss.getRemoteSocketAddress() +
								": " + Thread.currentThread().getName());
						//오류 발생하면 Server에 있는 모든 클라이언트를 담아둔 배열에서 현재 존재하는 배열을 지워줌
						//오류가 발생해서 해당 클라이언트가 서버로부터 접속이 끊겼으니까 
						//당연히 서버내에서도 해당 클라이언트가 접속이 끊겼다는 정보를 처리.
						//즉, 클라이언트에서 오류가 생긴 클라이언트를 제거해준다.
						Server.clients.remove(Client.this);
						ss.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
			
		};
		// Thread를 안정적으로 관리하기 위해서 Thread를 ThreadPool에 등록해준다.
		Server.threadPool.submit(thread);
	}
//send();
	
}
//class Client End

//class Server Start
public class Server extends Application{
	
	//ThreadPool을 통해서 다양한 클라이언트가 접속했을때, Thread들을 효과적으로 관리할 수 있기 위한 것.
	//즉, 여러개의 스레드들을 효율적으로 관리하기 위해 사용하는 대표적인 라이브러리
	//ThreadPool로 Thread를 처리하게 되면 기본적인 Thread숫자에 제한을 두기 때문에
	//갑작스럽게 클라이언트 숫자가 폭증하더라도 Thread의 숫자에는 제한이 있기 때문에 서버의 성능저하를
	//방지할 수 있다.
	//즉, 한정된 자원을 이용해서 안정적으로 서버를 운용하기 위한 기법이 ThreadPool 기법.
	public static ExecutorService threadPool;
	public static Vector<Client> clients = new Vector<Client>();
	
	ServerSocket mss = null;
	TextArea textArea;
	//서버를 구동시켜서 클라이언트의 연결을 기다리는 메서드.
	// 인수 : 어떤 IP로, 어떤 포트를 열어서 클라이언트와 통신을 할 것인가.
	public void startServer(String IP, int port) {
		try {
			//소켓 객체 활성화
			mss = new ServerSocket();
			//바인드를 통해 서버에 클라이언트의 접속을 기다리게함
			mss.bind(new InetSocketAddress(IP,port));
		} catch (Exception e) {
			e.printStackTrace();
			//만약, 서버 소켓이 닫혀있는 상태가 아니라면
			if(!mss.isClosed()) {
				stopServer();	//서버 종료
			}
			return;
		}
		
		//여기까지 왔다면 오류 발생하지 않고 서버가 소켓을 열고 접속을 기다리고 있는 상태임.
		//클라이언트가 접속할 때까지 계속 기다리는 쓰레드
		
		//스레드를 만들어서 하나의 클라이언트가 접속할 때까지 계속 기다린다.
		Runnable thread = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try{
						//새로운 클라이언트가 접속할 수 있도록.
						Socket ss = mss.accept();
						//클라이언트가 접속했다면 클라이언트 배열에 새롭게 추가
						clients.add(new Client(ss));
						System.out.println("[클라이언트 접속]" + ss.getRemoteSocketAddress() +
								": " + Thread.currentThread().getName());
					} catch(Exception e) {
						//오류 발생시 서버 중지시키고 break;
						if(!mss.isClosed()) {
							stopServer();
						}
						break;
					}
					
				}
			}
		};
		//스레드 풀 초기화
		threadPool = Executors.newCachedThreadPool();
		//그다음 스레드풀에 현재 클라이언트를 기다리는 스레드를 담을 수 있도록 처리.
		threadPool.submit(thread);
		
	}
//stopServer();
	//서버의 작동을 중지시키는 메서드
	public void stopServer() {
		//stopServer는 서버를 완전히 중지시키는 것이기 때문에
		//모든 클라이언트에 대한 정보를 끊어준다.
		try {
			//현재 작동중인 모든 소켓 닫기
			Iterator<Client> iterator = clients.iterator();
			//iterator로 하나씩 접근
			while(iterator.hasNext()) {
				Client client = iterator.next();
				//닫는다.
				client.ss.close();
				//iterator에서도 닫아준 소켓 제거
				iterator.remove();
			}
			//서버 소켓 객체 닫기
			if(mss != null && !mss.isClosed()) {
				mss.close();
			}
			//스레드 풀 닫기
			if(threadPool != null && !threadPool.isShutdown()) {
				//shutdown으로 자원 할당 해제
				threadPool.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//stopServer();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// System.out.println("실행순서 2");
		// 선두작업
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));
		
		
		textArea = new TextArea();
		//text를 출력만 하고 채울수는 없도록 false
		textArea.setEditable(false);
		textArea.setFont(new Font("나눔고딕", 10));
		//레이아웃에서 중간에 위치
		root.setCenter(textArea);
		
		//서버의 작동을 시작할 버튼 생성
		Button toggleBtn = new Button("Server Open");
		toggleBtn.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setMargin(toggleBtn, new Insets(1,0,0,0));
		//레이아웃에서 아래에 위치
		root.setBottom(toggleBtn);
		
		//자기 자신의 컴퓨터 주소
//IP		
		String IP ="218.154.207.127";
		int port = 5001;
		
		//버튼 이벤트 처리
		toggleBtn.setOnAction((AE)->{
			//버튼이 현재 "Server Open"를 클릭하면
			if(toggleBtn.getText().equals("Server Open")) {
				startServer(IP,port);
				//runLater써러 GUI요소를 출력할 수 있도록 해야한다.
				Platform.runLater(()->{
					String message = String.format("[서버 시작]\n", IP, port);
					textArea.appendText(message);
					toggleBtn.setText("Server Close");
				});
			} else {
				//Server Close 누를 경우
				stopServer();
				Platform.runLater(()->{
					String message = String.format("[서버 종료]\n", IP, port);
					textArea.appendText(message);
					toggleBtn.setText("Server Open");
				});
			}
		});
		// 마무리작업
		Scene scene = new Scene(root,400,300);
		primaryStage.setTitle("[채팅 서버]");// Title 설정
		//종료버튼 누른다면 stopServer함수 호출
		primaryStage.setOnCloseRequest((AE)->stopServer());
		primaryStage.setScene(scene); // UI구성 장면을 Scene으로 등록
		primaryStage.show(); // 등록한 Scene을 보여줌
		// System.out.println("실행순서 3");
	}

	public static void main(String[] args) {
		// System.out.println("실행순서 1");
		launch(args); // launcher 에서 블로킹 잡힌다. 런쳐 종료하면 나옴
		// System.out.println("실행순서 4");
		System.out.println("Exit");
	}

}
