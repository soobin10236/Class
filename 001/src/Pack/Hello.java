package Pack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
//ex)
class Animal{

}

class Tiger extends Animal{
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
		System.out.println(1000);
	}
}
*/

/*
//ex66) Thread 생성

class ConnectThread extends Thread{
	
	//field
//	Button btn1;
	//constructor
//	public ConnectThread(Button btn) {
//		super();
//		this.btn1 = btn;
//	}
	
//	method
//	@Override
//	public void run() {
//		System.out.println(2);
//		Platform.runLater(()->{
////			btn1.setText("호랑이");	//btn1이 누구인지 아직 알지 못함.
//			//btn1을 전달하면 받아줘야함 -> 생성자로 받아주면 됨
//			btn1.setText("호랑이");
//		});
//	}
	
//	인수 전달이 여러개라면 어떻게 할것인가? Hello 클래스를 받아
	
	//field
	Hello hello;
	
	//constructor
	public ConnectThread(Hello hello) {
		super();
		this.hello = hello;
	}
	
//	method
	@Override
	public void run() {
		System.out.println(2);
		Platform.runLater(()->{
//			btn1.setText("호랑이");	//btn1이 누구인지 아직 알지 못함.
			//btn1을 전달하면 받아줘야함 -> 생성자로 받아주면 됨
			hello.btn1.setText("호랑이");
		});
	}
	
}

//class Combo extends Thread{
//	
//	//field
//	Hello hello;
//	
//	//constructor
//	Combo(Hello hello){
//		this.hello = hello;
//	}
//	
//	//method
//	@Override
//	public void run() {
//		System.out.println("Combo");
//		Platform.runLater(()->{
//			hello.btn1.setText("Combo");
//		});
//	}
//}

public class Hello extends Application
{
	// 실행순서 2
	Button btn1 = new Button("테스트1");
	Button btn2 = new Button("테스트2");
//	ComboBox<String> comboBox = new ComboBox<String>();

	@Override
	public void start(Stage arg0) throws Exception {
		// 선두작업
		VBox root = new VBox(); // 버츄얼 -> 세로로 형성, 호라이즌 -> 가로로 형성
		root.setPrefSize(400, 300); // 기본 layout 가로,세로 잡는것
		root.setSpacing(15);

		// ---------------------------------------------
		// 실제 코딩을 작성하면 되는 부분이다.
		// 1.컨트롤을 배치
//		Button btn1 = new Button("Test");
//		Button btn2 = new Button("Test");
//		전역변수로 btn을 만들어줘야 넘길수 있게됨.
		
		// 2. 컨트롤에 대한 이벤트 처리
		btn1.setOnAction((AE)->{
			new Thread() {
				public void run() {
					System.out.println(1);
//					Thread 안에서 그냥 컨트롤 조작하면 안됨
//					btn2.setText("호랑이");
				
//					runLater를 활용하면 가능함.
					Platform.runLater(()->{
						btn2.setText("호랑이");
					});
				}
			}.start();
			//Thread 안에 코드가 늘어지면 클래스 빼서 만드는게 보기 좋다 -> ConnectThread
		});

		btn2.setOnAction((AE)->{
//			Thread t = new ConnectThread();
//			t.start();
//			new ConnectThread(btn1).start(); //hello받을때
			new ConnectThread(Hello.this).start();
		});
		
//		comboBox.setOnAction((AE)->{
//			new Combo(Hello.this).start();
//		});


//		root.getChildren().addAll(btn1, btn2, comboBox);
		root.getChildren().addAll(btn1, btn2);
		// ---------------------------------------------

		// 마무리작업
		Scene scene = new Scene(root);
		arg0.setTitle("Server");// Title 설정
		arg0.setScene(scene); // UI구성 장면을 Scene으로 등록
		arg0.show(); // 등록한 Scene을 보여줌

		// 실행순서 3
	}
	
//	main
	public static void main(String[] args) 
	{
//		실행순서 1
		launch();
//		실행순서 4
		System.out.println("Exit");
	}
}
*/
/*
//ex65) Button Event
//	스레드 안에서 컨트롤 자체를 수정, 변경하면 안된다.
//	그런 일이 발생하면 run later로 접근해라..(나중에 하세요)
public class Hello extends Application {
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("실행순서 2");

		// 선두작업
		VBox root = new VBox(); // 버츄얼 -> 세로로 형성, 호라이즌 -> 가로로 형성
		root.setPrefSize(400, 300); // 기본 layout 가로,세로 잡는것
		root.setSpacing(15);

		// ---------------------------------------------
		// 실제 코딩을 작성하면 되는 부분이다.
		// 1.컨트롤을 배치
		Button btn1 = new Button("Test");
		Button btn2 = new Button("Test");
		
		// 2. 컨트롤에 대한 이벤트 처리
		btn1.setOnAction((ActionEvent)->{
			btn2.setText("호랑이");
		});
		
		btn2.setOnAction((AE)->{
//			btn1.setVisible(false);	//아예 없어짐
			btn1.setDisable(true);	//보이지만 못쓰게함
		});
		
		
		root.getChildren().addAll(btn1, btn2);
		// ---------------------------------------------

		// 마무리작업
		Scene scene = new Scene(root);
		arg0.setTitle("Server");// Title 설정
		arg0.setScene(scene); // UI구성 장면을 Scene으로 등록
		arg0.show(); // 등록한 Scene을 보여줌

		// System.out.println("실행순서 3");

	}

	public static void main(String[] args) {
		// System.out.println("실행순서 1");
		launch(); // launcher 에서 블로킹 잡힌다. 런쳐 종료하면 나옴
		// System.out.println("실행순서 4");
		System.out.println("Exit");
	}
}
*/

//ex64) JavaFX 이벤트(람다식)
//import는 javafx. 로 시작해야함
//	스레드 안에서 컨트롤 자체를 수정, 변경하면 안된다.
//	그런 일이 발생하면 run later로 접근해라..(나중에 하세요)
public class Hello extends Application {
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("실행순서 2");

		// 선두작업
		VBox root = new VBox(); // 버츄얼 -> 세로로 형성, 호라이즌 -> 가로로 형성
		root.setPrefSize(400, 300); // 기본 layout 가로,세로 잡는것
		root.setSpacing(15);

		// ---------------------------------------------
		// 실제 코딩을 작성하면 되는 부분이다.
		// 1.컨트롤을 배치
		Button btn1 = new Button("Test");
		Button btn2 = new Button("Test");
		
		// 2. 컨트롤에 대한 이벤트 처리
		btn1.setOnAction(new EventHandler<ActionEvent>() {
//		인터페이스를 익명객체로 만드는 부분이 어색하지 않아야한다.	
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("호랑이");
			}
		});
		
		btn2.setOnAction((ActionEvent)->{
			System.out.println("사자");
		});
//		btn2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				System.out.println("사자");
//			}
//		});
		//TextArea
		TextArea textArea = new TextArea();
		
		//TextField
		
//		TextField textField = new TextField();
//		//TextField에 커서 놓고 Enter치면 이벤트 발생
//		textField.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				System.out.println("코끼리");
//				String str = textField.getText();	
//				//입력한 내용 String으로 받아서 textArea로 보내야함 + 줄바꿈
//				textArea.appendText(str + "\n");
//				//입력하고나면 textField는 비워져야함
//				textField.setText("");
//			}
//		});
		
		TextField textField = new TextField();
		//람다식
		//람다식 안에 인수는 이름은 상관없긴함.
		textField.setOnAction((ActionEvent)->{
			String str = textField.getText();	
			textArea.appendText(str + "\n");
			textField.setText("");
		});
		
//		root.getChildren().addAll(btn1, btn2);
		root.getChildren().addAll(btn1, textArea, textField);
		// ---------------------------------------------

		// 마무리작업
		Scene scene = new Scene(root);
		arg0.setTitle("Server");// Title 설정
		arg0.setScene(scene); // UI구성 장면을 Scene으로 등록
		arg0.show(); // 등록한 Scene을 보여줌

		// System.out.println("실행순서 3");

	}

	public static void main(String[] args) {
		// System.out.println("실행순서 1");
		launch(); // launcher 에서 블로킹 잡힌다. 런쳐 종료하면 나옴
		// System.out.println("실행순서 4");
		System.out.println("Exit");
	}
}

/*
//ex63) JavaFX 환경설정
//		import는 javafx. 로 시작해야함
public class Hello extends Application{
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("실행순서 2");
		
		//선두작업
		VBox root = new VBox();	//버츄얼 -> 세로로 형성, 호라이즌 -> 가로로 형성
		root.setPrefSize(400, 300); //기본 layout 가로,세로 잡는것
		
	//---------------------------------------------
		//실제 코딩을 작성하면 되는 부분이다.
		//1.컨트롤을 배치
		//	방법 1
//		Button btn1 = new Button("Test");
//		root.getChildren().add(btn1);
		
//		Button btn2 = new Button("Test");
//		root.getChildren().add(btn2);
		//	방법 2
		Button btn1 = new Button("Test");
		Button btn2 = new Button("Test");

		root.getChildren().addAll(btn1, btn2);
		
		//2. 컨트롤에 대한 이벤트
	//---------------------------------------------
		
		//마무리작업
		Scene scene = new Scene(root);
		arg0.setTitle("Server");//Title 설정
		arg0.setScene(scene);	//UI구성 장면을 Scene으로 등록
		arg0.show();			//등록한 Scene을 보여줌
		
//		System.out.println("실행순서 3");
		
	}
	
	public static void main(String[] args) 
	{
//		System.out.println("실행순서 1");
		launch(); //launcher 에서 블로킹 잡힌다. 런쳐 종료하면 나옴
//		System.out.println("실행순서 4");
		System.out.println("Exit");
	}
}
*/
/*
//ex62-5) Thread 익명 객체
public class Hello 
{
	public static void main(String[] args) 
	{
//		Thread t = new Thread() {
//			@Override
//			public void run() {
//				System.out.println(1);
//			}
//		};
//		t.start();	//스레드 발동
		
//		위 아래 모두 같다. 스레드는 발동만 하면 됨.
		new Thread() {
			public void run() {
				System.out.println(2);
			};
		}.start();
	}
}
*/
/*
//62-4) Thread 예제3

class Tiger extends Thread{
//	Thread 만들면 무조건 run 정의
	@Override
	public void run() {
		System.out.println("Tiger start");
//		타이거가 묶이기 전에 라이언을 만듬 -> 라이언도 묶임
		Thread t = new Lion();
		t.start();
		
		Scanner sc = new Scanner(System.in);
		sc.nextInt();//블로킹 함수..-> 키가 입력될때까지 내려오지 않음

//		최종적으로 메인, 타이거, 라이언 모두 묶여서 대기하게 됨
//		숫자 입력하면 순차적으로 풀리는데 순서는 알 수 없음.
		System.out.println("Tiger end");
	}
}

class Lion extends Thread{
//	Thread 만들면 무조건 run 정의
	@Override
	public void run() {
		System.out.println("Lion start");

		Scanner sc = new Scanner(System.in);
		sc.nextInt();//블로킹 함수..-> 키가 입력될때까지 내려오지 않음
//		제어권을 넘겼기 때문에 엔터를 쳤음에도 end가 안나오는 경우가 있음..

		System.out.println("Lion end");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		System.out.println("main start");
		
//		main 묶이기 전에 타이거불러서 같이 묶임
		Thread t = new Tiger();
		t.start();
		
		Scanner sc = new Scanner(System.in);
		sc.nextInt();//블로킹 함수..-> 키가 입력될때까지 내려오지 않음

		System.out.println("main end");
	}
}
*/

/*
//62-3) Thread 예제2

public class Hello 
{
	public static void main(String[] args) 
	{
		System.out.println(1);
//		키보드로부터 입력받을 수 있는 Scanner
		Scanner sc = new Scanner(System.in);
		sc.nextInt();//블로킹 함수..-> 키가 입력될때까지 내려오지 않음
//		Thread 는 debug 창을 열고 쓴다. debug창에서 프로세스 끝내주면 안돌아감.
//		콘솔창에 숫자를 입력하면 블로킹이 풀린다.
		System.out.println(2);
	}
}
*/
/*
//62-2) Thread 예제1

class Tiger extends Thread{
	
	//method
	public void run() { //2
//		System.out.println(2);
		//반복문
		for (int i = 0; i < 10; i++) {
			System.out.println("AA" + i);
			try {Thread.sleep(0);} catch (Exception e) {e.printStackTrace();}
		}
		System.out.println("종료");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Thread t = new Tiger();
		//start를 실행했는데 run이 실행됨..-> 안에 run이 있어서 재정의된것이 실행.
		t.start(); //1	
//		System.out.println(1);
		//반복문
		for (int i = 0; i < 10; i++) {
			System.out.println("BB" + i);
			try {Thread.sleep(0);} catch (Exception e) {e.printStackTrace();}
		}
		System.out.println("main Exit");
	}
}
*/
/*
//ex62-1) Thread 개념
class A{
	
	//method
	public void start() {
		System.out.println("Start Call");
		run();
	}
	
	void run() {
		System.out.println("A run(); Call");
	}
}

class B extends A{
	
	//method
	@Override
	void run() {
		System.out.println("스레드 시작");
		System.out.println("B run(); Call");
		System.out.println("스레드 종료");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		A a = new B();
		a.start();
	}
}
*/
/*
//ex61-2) 제네릭 타입 사용
class A<T>{
	
	//field
	private T data;

	//method
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}


public class Hello 
{
	public static void main(String[] args) 
	{
		A<Integer> a = new A<Integer>();
		a.setData(100);
		System.out.println(a.getData());
		
		A<String> b = new A<String>();
		b.setData("호랑이");
		System.out.println(b.getData());
		
		A<Float> c = new A<Float>();
		c.setData(1.234f);
		System.out.println(c.getData());
//		작성에 있어서 편한것은 있지만 코드로서는 내부적으로 생성해서 돌아가므로 속도면에서 차이는 없다.
//		작성할때 생산성의 측면에서 쓰는것임.
		
//		모든 타입 받아주기 위해서 List 같은 것들은 제네릭 타입으로 작성함.
		LinkedList<Integer> list = new LinkedList<Integer>();
	}
}
*/
/*
//ex61-1) 제네릭타입의 필요성
class A{
	
	//field
	private int data;

	//method
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}

class B{
	
	//field
	private String data;

	//method
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}

class C{
	
	//field
	private float data;

	//method
	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
//		코드의 내용은 똑같은데 사용되는 타입이 다를때..
//		사용자 정의 타입까지 합치면 클래스 무한대로 만들어야함.
		A a = new A();
		a.setData(100);
		System.out.println(a.getData());
		
		B b = new B();
		b.setData("호랑이");
		System.out.println(b.getData());
		
		C c = new C();
		c.setData(1.33f);
		System.out.println(c.getData());
	}
}
*/
/*
//ex60-3) Decorator 패턴 활용

class 커피{
	//field
	int 가격 = 30;
	
	//method
	int 계산() {
		System.out.println("커피 주문 +30$");
		return 가격;
	}
}

class 설탕 extends 커피{

	//field
	int 가격 = 10;
	커피 a;	//사라질 객체를 백업
	
	//constructor
	설탕(커피 a){
		this.a = a;
	}
	
	//method
	@Override
	int 계산() {
		System.out.println("설탕 추가 +10$");
		return 가격 + a.계산();
	}
}

class 프림 extends 커피{

	//field
	int 가격 = 5;
	커피 a;	//사라질 객체를 백업
	
	//constructor
	프림(커피 a){
		this.a = a;
	}
	
	//method
	@Override
	int 계산() {
		System.out.println("프림 추가 +5$");
		return 가격 + a.계산();
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
//		커피 한잔만 주문한 고객
		커피 a = new 커피();
		System.out.println("a : " + a.계산() + "$");
		System.out.println("-------------------");
//		커피에 설탕 두번 추가
		커피 b = new 커피();
//		설탕 1번 추가
		b = new 설탕(b);
//		설탕 2번 추가
		b = new 설탕(b);
		System.out.println("b : " + b.계산() + "$");
		System.out.println("-------------------");
//		커피에 설탕 1개 프림 2개 추가	
		커피 c = new 커피();
//		프림 1번 추가
		c = new 프림(c);
//		설탕 1번 추가
		c = new 설탕(c);
//		프림 2번 추가
		c = new 프림(c);
		System.out.println("c : " + c.계산() + "$");
		System.out.println("-------------------");	
	}
}
*/
/*
//ex60-2)

class A{
	//field
	int num = 10;
	
	//method
	int run() {
		System.out.println("A run");
		return num;
	}
}

class B extends A{

	//field
	int num = 20;
	A a;	//사라질 객체를 백업
	
	//constructor
	B(A a){
		this.a = a;
	}
	
	//method
	@Override
	int run() {
		System.out.println("B run");
		return num + a.run();	//20 + 10 = 30이 리턴 돼야함
	}
}

class C extends A{

	//field
	int num = 30;
	A a;	//사라질 객체를 백업
	
	//constructor
	C(A a){
		this.a = a;
	}
	
	//method
	@Override
	int run() {
		System.out.println("C run");
		return num + a.run();	//20 + 10 = 30이 리턴 돼야함
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		A a = new A();
//		a = new B(); //문제 발생하지는 않지만 이전 객체가 없어짐
		a = new B(a); //사라지기 직전에 생성자 인수 전달로 멤버 변수에 사라질려고 하는 놈을 백업
		System.out.println(a.run());
		System.out.println("----------------------------");
		a = new C(a);	//현재 A B 모두 갖고 있는 객체를 다시 백업 받는것임
		System.out.println(a.run());
	}
}
*/
/*
//ex60-1) Decorator 패턴
// * 객체가 객체를 감싸는 형태

class AA{}
class BB extends AA{}
class CC extends AA{}

public class Hello 
{
	public static void main(String[] args) 
	{
		AA a1 = new BB();    
		System.out.println(a1.hashCode());
		a1 = new CC();
		System.out.println(a1.hashCode());
//		이전의 a1 객체는 사라지고 새로운 a1객체가 생긴것임
		
		AA a2 = a1;
		System.out.println(a2.hashCode());
//		이전 객체를 백업했다고 생각해라.
		
		a1 = new BB();
		System.out.println(a1.hashCode());
	}
}
*/
/*
//ex59) FileReader
public class Hello 
{
	public static void main(String[] args) 
	{
		try {
//			UTF-8
//			Reader r = new FileReader("test02.txt", StandardCharsets.UTF_8);

//			ANSI
			Reader r = new FileReader("test02.txt");
			
			while (true) {
				int data = r.read();
//				맨 마지막에 도착 -> data의 값이 -1이 넘어옴
				if(data == -1) {
					break;
				}
				System.out.print((char)data);
			}
//			한 자씩 읽어들이면 속도 차이 많이난다..한번에 잡아올수있는 방법 찾아봐라..
			r.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(1000);
	}
}
*/
/*
//ex58-2) 자바 I/O + 이중 for문
public class Hello 
{
	public static void main(String[] args) 
	{
//		1. Writer 객체 생성
		try {
			Writer w = new FileWriter("test01.txt");
//			3. O, X 가 번갈아 가며 체스판 형태로 나오는 모양 파일에 출력
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 10; j++) {
//					if ((i + j) % 2 == 0) {
//						w.write("O ");
//					}
//					else {
//						w.write("X ");
//					}
//					삼항 연산으로 작성
					w.write((i+j)%2 == 0? "O ": "X ");
				}
				w.write("\n");
			}
			
//			2. 열면 먼저 닫아주고 중간에 작성
			w.close();
			System.out.println("종료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
/*
//ex58-1) 자바 I/O
public class Hello 
{
	public static void main(String[] args) 
	{
		try {
			Writer w = new FileWriter("test01.txt");

			w.write("apple");
			w.write("\n\n");
			w.write("무궁화");
			
			w.close();
//			종료되었는지 메시지 띄워서 확인해라
			System.out.println("파일 입출력 종료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
/*
//ex58) 자바 I/O
public class Hello 
{
	public static void main(String[] args) 
	{
		try {
//			상속 : Object - Writer - OutputStreamWriter - FileWriter
			Writer w = new FileWriter("test01.txt");
//			file I/O는 사인펜과 같다..현재 사인펜 뚜껑을 연 상태
//			마지막에는 꼭 뚜껑을 닫아야함. 안그러면 운영체제에 문제생김.
//			열고나면 닫는 작업부터 먼저 하고 중간에 작업해라. -> .close();
			
			w.write("apple");
//			확인하는 법
//			프로젝트 001 -> alt + enter -> Location
//			test01 메모장 파일 확인
			
			w.close();
//			종료되었는지 메시지 띄워서 확인해라
			System.out.println("파일 입출력 종료");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
/*
//ex57) 자바 파일 입출력/ 이중 for문
//		자바에서 파일입출력이 큰 비중은 아니지만 할 수도 있다.

public class Hello 
{
	public static void main(String[] args) 
	{
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print("[" + i + " " + j + "]");
			}
			System.out.println();
		}
		System.out.println("---------------------");
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if((i + j)%2 == 0) {
					System.out.print("O ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}
*/

/*
//ex56) GOF - singleton
//		특정 클래스의 객체가 오직 한 개만 존재하도록 보장한다
//		한개는 만들 수 있게 하고 그 이후는 막아야 함.
class Tiger{
	
	//field
	private static Tiger instance = null;
	private int num;

	//constructor
	private Tiger() {
		
	}
//	생성자를 private으로 처리하면 외부에서 만들 수가 없어짐
	
	//method
//	함수 안에서 만든다. 이 함수를 한번만 호출되게 한다. but 객체가 없으니 함수 호출을 못함
//	-> 해결하기 위해서 static 붙이면 객체생성 없이도 호출 가능.
	static Tiger getInstance() {	//static 함수 안에서는 static 변수만 쓸 수 있음
		if(instance == null) {
			System.out.println(1);
			instance = new Tiger();
		}
		return instance;
	}
	
	public void m1() {
		System.out.println(1000);
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
//		Tiger t1 = new Tiger();
//		Tiger t2 = new Tiger();
		
		Tiger t1 = Tiger.getInstance();	//객체가 생성됨
		Tiger t2 = Tiger.getInstance();	//null일때만 객체를 생성시키므로 다시 불러도 객체 생성 X
		
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
//		같은 객체를 다른 이름으로 쓰고 있는 것 뿐이다.
		System.out.println("----------------------------");
		t1.setNum(1234);
		System.out.println(t1.getNum());
		System.out.println(t2.getNum());
	}
}
*/
/*
//ex55) getter/setter

class Tiger {
	
	//field
	private String name;
	private int age;
	
	//constructor
	
	//method
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
		System.out.println(1000);
	}
}
*/
/*
//ex54) 접근 제한자, getter/setter
class Tiger{
	
	//field
	private int a;	//private은 클래스 내부에서만 사용 가능
	public int b;	//생략돼있을때는 default -> 앞에 쓰면 에러남
	private int num;
	
	//constructor
	
	//method
	//private -> 읽기전용, 쓰기전용 등 여러 조합으로 할 수 있는거다.
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return this.num;
	}
	
	private void m1() {	//외부에서 호출하는 메서드 아니고 내부 구성 요소로서 작동
		
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Tiger t = new Tiger();
		t.b = 10;
//		t.a = 10; private 은 불가
		
		t.setNum(123);
		System.out.println(t.getNum());
			
	}
}
*/
/*
//ex53) 람다함수 응용

interface Test1 {
	// method
	void m1();
}

// 인수전달 위해서 Test2
interface Test2 {
	// method
	int m1();
}

class Tiger {

	// method
	void m2(Test1 t) {
		t.m1();
	}

	void m3(Test2 t) {
		System.out.println(t.m1());
	}
}

public class Hello {
	public static void main(String[] args) {
		// ex1)
		Test1 t1 = () -> {
			System.out.println(1);
		};

		Tiger tiger = new Tiger();
		tiger.m2(t1);

		// 실제 사용 모습
		tiger.m2(() -> {
			System.out.println(2);
		});

		// m3 실제 사용 (단줄 리턴)
		// tiger.m3(()->{
		// return 100;
		// });
		// 단줄 리턴의 경우 생략 코드
		tiger.m3(() -> 123);

		LinkedList<Integer> mm = new LinkedList<Integer>();
		// Predicate도 인터페이스이고 안에는 test 함수 한개만 갖고 있다
		// 위에서 만든 람다 식 연습한것과 똑같다.
		// mm.removeIf(new Predicate<Integer>() {
		// @Override
		// public boolean test(Integer t) {
		// TODO Auto-generated method stub
		// return false; //조건 걸리는 곳
		// }
		// });
		// 고치면 이렇게 된다. 이름 아무렇게나 넣어도 가능
//		생략하기 전
//		mm.removeIf((example)->{
//			return example % 2 == 0;
//		});
		
		mm.removeIf((lion) -> lion % 2 == 0);
	}
}
*/
/*
//ex52) 람다 함수
//	자바에서 람다는 무조건 인터페이스와 관련
//	람다는 인터페이스 안에 구현해야할 함수가 1개밖에 없을때를 가정하고 사용하는 것

interface Test1 {

	// method
	void m1();
}

// 인수전달 위해서 Test2
interface Test2 {

	// method
	void m1(int num);
}

// int를 return 하기 위해서 Test3
interface Test3 {

	// method
	int m1();
}

//return + 인수전달
interface Test4 {

	// method
	String m1(int num, String str);
}

//	implements받으면 되지만 익명 객체로 만들면 된다.

public class Hello {
	public static void main(String[] args) {
		// ex1) 익명객체
		Test1 t1 = new Test1() {
			@Override
			public void m1() {
				System.out.println(1);
			}
		};
		t1.m1();

		// ex2) 람다 기본
		Test1 t2 = () -> { // 함수 이름조차 필요없다..
			System.out.println(2);
		};
		t2.m1();

		// ex3) 람다 인수 전달
		// Test1 인터페이스에는 m1이 인수전달이 안되고있기 때문에 안됨
		// Test2 인터페이스에 m1 인수전달 가능하게 만듬
		Test2 t3 = (n) -> {// 인수전달 타입 쓸 필요없음. 이름 같을필요도 없음
			System.out.println(n);
		};
		t3.m1(3);

		// ex4) 리턴 타입 있을때
		Test3 t4 = () -> {
			System.out.println("int return");
			return 100;
		};
		System.out.println(t4.m1());

		// ex5) 리턴 타입 + 인수전달 있을때
		Test4 t5 = (n, s) -> {
			System.out.println("String return + 인수 (int + String)");
			return n + s;
		};
		System.out.println(t5.m1(10, "str"));

		// ex6) 람다에서 return있을때 단줄 코드이면 {} 생략가능 -> return 도 생략
		// () -> return 이라고 볼 수 있어야함.
		// Test3 t6 = ()-> {
		// return 100;
		// };
		Test3 t6 = () -> 100;
		System.out.println(t6.m1());
	}
}
*/
/*
// 	사용자 정의 객체인 Student 를 key 로 하고 점수를 value 로 저장하는 HashMap 사용방법
//	학번과 이름이 동일한 Student를 동등 키로 간주하기 위해 HashCode(), equals() 재정의 필요
class Student{
	
	//field
	int sNum;
	String name;
	
	//constructor
	public Student(int sNum, String name) {
		super();
		this.sNum = sNum;
		this.name = name;
	}
	
	//method
	
	void m1() {
		Object obj = null;
		System.out.println(obj instanceof Student);
	}
	@Override
	public boolean equals(Object obj) {	//학번과 이름이 동일한 경우 true 리턴

		if (obj instanceof Student) {
			Student student = (Student)obj;
			return (sNum == student.sNum) && name.equals(student.name);
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
//		학번과 이름이 같다면 동일한 값을 리턴
		return sNum + name.hashCode();
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		HashMap<Student, Integer> map = new HashMap<Student, Integer>();
		
		map.put(new Student(1, "홍길동"),95);
		map.put(new Student(1, "홍길동"),90);
		map.put(new Student(2, "홍길동"),95);
		map.put(new Student(2, "김갑돌"),95);
		map.put(new Student(2, "김갑돌"),80);
		
		Set<Student> keySet2 = map.keySet();
		Iterator<Student> keyIterator = keySet2.iterator();
		while (keyIterator.hasNext()) {
			Student key = (Student) keyIterator.next();
			Integer value = map.get(key);
			
			System.out.println("\t" + key.sNum + "번 " +key.name + " : " + value);
		}
		System.out.println("총 Entry 수 : " + map.size());
	}
}
*/
/*
//	HashMap 사용법
//	Map컬랙션은 키,값 으로 구성된 Entry 객체를 저장하는 구조. (키, 값 모두 객체)
//	키는 중복 저장 불가, 값은 중복 저장 가능(기존 키와 동일한 키 저장하면 기존 값 없어지고 새 값 저장)
//	HashMap의 키로 사용할 객체는 hashCode()와 equals()를 재정의해서 동등 객체가 될 조건을 정해야함.
public class Hello 
{
	public static void main(String[] args) 
	{
		//Map 컬랙션 생성
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//객체 저장
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);	//홍길동 키가 같기 때문에 마지막에 저장한 값으로 대치
		System.out.println("총 Entry 수 : " + map.size());
		
		//객체 찾기
		System.out.println("\t홍길동 : " + map.get("홍길동"));
		System.out.println();
		
		//객체를 하나씩 처리
		Set<String> keySet = map.keySet();	//keySet 얻기(모든 키를 set객체에 담아 리턴)
		Iterator<String> keyIterator = keySet.iterator();	//반복자
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			Integer value = map.get(key);
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		
		//객체 삭제
		map.remove("홍길동");	//key 로 Map.Entry(키와 값) 제거
		System.out.println("총 Entry 수 : " + map.size());
		
		//객체를 하나씩 처리
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet(); //Map.EntrySet 얻기
		Iterator<Map.Entry<String, Integer>> entryIterator = entrySet.iterator();
		
		while (entryIterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) entryIterator
					.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			
			System.out.println("\t" + key + " : " + value);
		}
		System.out.println();
		
		//객체 전체 삭제
		map.clear();
		System.out.println("총 Entry 수 : " + map.size());
	}
}
*/
/*
// 오버라이드를 이용한 Set 중복 저장 불가능하게 만들기
class Member{
	
	//field
	public String name;
	public int age;
	
	//constructor
	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {	//name 과 age 같으면 true를 리턴

		if (obj instanceof Member) {
			Member member = (Member)obj;
			return member.name.equals(name) && (member.age == age);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {	//name과 age 같으면 동일한 hashCode를 리턴
		return name.hashCode() + age;
	}
//	일단 이렇게 안하면 값이 같아도 다른 객체기때문에 Set인에 저장이 되어버린다
//	그래서 메서드를 오버라이드 해서 이름이랑 나이가 같으면 객체가 달라도 중복으로 잡아버릴수 있게된다.
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
		HashSet<Member> set = new HashSet<Member>();
		
		set.add(new Member("홍길동", 30));
		set.add(new Member("홍길동", 30));
		
		System.out.println("총 객체수 : " + set.size());
	}
}
*/
/*
//	HashSet
public class Hello 
{
	public static void main(String[] args) 
	{
		HashSet<String> set = new HashSet<String>();
		
		set.add("Java");
		set.add("JDBC");
		set.add("Servlet/JSP");
		set.add("Java");	//Set은 중복 저장 불가
		set.add("iBATIS");
		
		int size = set.size();
		System.out.println("총 객체수: " + size);
		
//		반복자 얻기
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) { //객체수만큼 루핑
			String element = (String) iterator.next();
			System.out.println("\t" + element);
		}
		
		set.remove("JDBC");
		set.remove("iBATIS");
		
		System.out.println("총 객체수: " + set.size());
		
		Iterator<String>iterator2 = set.iterator();
		while (iterator2.hasNext()) {
			String element = (String) iterator2.next();
			System.out.println("\t" + element);
		}
		
		set.clear(); //모든 객체를 제거하고 비움
		if (set.isEmpty()) {
			System.out.println("비어있음.");
		}
	}
}
*/
/*
//Vector
class Board{
	//field
	String subject;
	String content;
	String writer;
	
	//constructor
	Board(String subject, String content, String writer){
		this.subject = subject;
		this.content = content;
		this.writer = writer;
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Vector<Board> boardList = new Vector<Board>();
		
		boardList.add(new Board("제목1","내용1","글쓴이1"));
		boardList.add(new Board("제목2","내용2","글쓴이2"));
		boardList.add(new Board("제목3","내용3","글쓴이3"));
		boardList.add(new Board("제목4","내용4","글쓴이4"));
		boardList.add(new Board("제목5","내용5","글쓴이5"));
		
		boardList.remove(2); //3번삭제 -> 총 4개로 줄여짐
		boardList.remove(3); //4번삭제 -> 5가 삭제됨
		
		for (Board board : boardList) {
			System.out.println(board.subject + "\t" + board.content + "\t"
								+ board.writer);
		}
	}
}
*/
/*
// ArrayList
public class Hello 
{
	public static void main(String[] args) 
	{
		List<String> list = new ArrayList<String>();
		
		list.add("Java");
		list.add("JDBC");
		list.add("Servlet/JSP");
		list.add(2,"Database");
		list.add("iBATIS");
		
		int size = list.size();
		System.out.println("총 객체 수 : " + size);
		System.out.println("");
		
		String skill = list.get(2);
		System.out.println("2 : " + skill);
		System.out.println("");
		
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + " : " + str);
		}
		System.out.println("");
		
		list.remove(2);
		list.remove(2);
		list.remove("iBATIS");
		
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println(i + " : " + str);
		}
		System.out.println("");
		
//		고정된 객체로 구성된 asList
		List<String> list1 = Arrays.asList("홍길동", "김용권", "감자바");
		for (String name : list1) {
			System.out.println(name);
		}
	}
}
*/
/*
//ex51) LinkedList 검색
public class Hello {
	public static void main(String[] args) {
		LinkedList<Integer> mm = new LinkedList<Integer>();

		mm.add(33);
		mm.add(44);
		mm.add(77);
		for (int i = 0; i < 10; i++) {
			mm.add(i*2);
		}
		mm.add(101);
		mm.add(102);
		mm.add(103);
		
		System.out.println(mm);
		
		//ex1) 고전적 방법
		for(int i = 0; i < mm.size();) {
			int num = mm.get(i);
			if (num % 2 == 0) {
				mm.remove(i);
			} else {
				i++;
			}
		}
		System.out.println(mm);
		
		
		//ex2) iterator 사용
		for (Iterator<Integer> it = mm.iterator(); it.hasNext();) {
//			값을 끄집어 냄.
			Integer num = it.next();
			
			
			if (num % 2 == 0) {
				it.remove();
			}
		}
		System.out.println(mm);
		
		
		
		//ex3) removeIf 사용 (인터페이스 상속받아서 구현)
//		removeIf(Predicate<?super Integer>) 
//		removeIf(Tiger<>)
//		Predicate -> interface -> 내가 함수 구현
		
//		익명객체
		mm.removeIf(new Predicate<Integer>() {
			@Override
			public boolean test(Integer num) {
				// TODO Auto-generated method stub
//				return 반환하고싶은 조건문
				return num % 2 == 0;
			}
		});
		System.out.println(mm);
		
		
		//ex4) removeIf 사용 (람다 사용)->코드를 최대한 가볍게 사용하려고
		mm.removeIf( num -> num % 2 == 0);
		System.out.println(mm);
		
		System.out.println(mm);

		for (int i = 0; i < 10; i++) {
			mm.add(i * 11);
		}
		int size = mm.size();
		for (int i = 0; i < size; i++) {
			int num = mm.get(i);
			// System.out.println(num);
			// if (num == 44) {
			// System.out.println(i + "번째 찾았다!");
			// break;
			// }
			if (num == 55) {
				// System.out.println(i);
				mm.remove(i);
				break;
			}
			if (i == (size - 1)) {
				System.out.println("Not Found");
			}
		}
		System.out.println(mm);

	}
}
*/
/*
//ex50) Collection - LinkedList
// CRUD를 활용할 수 있는 각각의 장단을 가진 8가지 알고리즘 -> Collection
//	-> LinkedList 라이브러리
//		제네릭 - <>
public class Hello 
{
	public static void main(String[] args) { 
	
		LinkedList<Integer> mm = new LinkedList<Integer>();
//		CreateRUD
		mm.add(10);
		System.out.println(mm.size());
		for(int i = 0; i < 10; i++) {
			mm.add(i*(10 + 1));
		}
//		 1. CReadUD
		System.out.println(mm);
		
//		2.
		int size = mm.size();
		for (int i = 0; i < size; i++) {
			int data = mm.get(i);
			System.out.print(data + " ");
		}	System.out.println();
		
//		3. x, item, data, value
		for(Integer x : mm) {
			System.out.print(x + " ");
		} System.out.println();
		
//		CRUpdateD
		mm.set(5, 999);
		System.out.println(mm);
		
//		CRUDelete
		mm.remove(5);
		System.out.println(mm);
	}
	
}
*/
/*
//ex49-1) 비트연산자, 시프트연산자
public class Hello 
{
	public static void main(String[] args) 
	{
		
		char c6 = 0x00dc;
		char mask = 0x0080;
		
		String[] arr = new String[] {
				"선풍기","냉장고","가스레인지","전자레인지",
				"전기장판","에어컨","에어프라이어","보일러"
				};
		
		for(int i = 0; i < arr.length; i++, mask >>= 1) {
			
			String str = ((c6 & mask) == mask ? arr[i] + "on" : arr[i] + "off");
//			String str = ((c6 & mask) == 0 ? arr[i] + "off" : arr[i] + "on");

//			System.out.println(Integer.toBinaryString(c6));
//			System.out.println(Integer.toBinaryString((c6 & mask)));
		
			System.out.println(str);
		}
	}
}
*/
/*
//ex49) 비트연산자, 시프트연산자

public class Hello 
{

	static String hexaToBinary(int n) {
		String str = Integer.toBinaryString(n);

		while(str.length() < 32) {
			str = "0" + str;
		}

		StringBuffer sb = new StringBuffer(str);	//내용은 str과 같지만 타입 바뀜

		for (int i = 0; i < 7; i++) {
			sb.insert((7-i)*4, " ");
		}
		return sb.toString(); //return으로 보내주는게 훨씬 유연하게 쓸 수 있음.
	}

	public static void main(String[] args) {
		/*
//		2진법 4자리씩 끊어서 32자리로 보여줘라
//		11 1011 0001 0010 1100 1101 0101 1001
		int a = 0x3b12cd5a;
		int b = 0x000ff00;

//		cd 는 10진수로 얼마인가
		System.out.println("2진법 a : " + hexaToBinary(a));
		System.out.println("2진법 b : " + hexaToBinary(b));
//		& 연산자 -> 두개 비교해서 모두 1인 자리만 1로 표시.
		System.out.println("2진법 a & b : " + hexaToBinary(a&b));
		System.out.println("10진법 a & b : " + (a&b)); //걸러진 cd00의 결과		
//		시프트 연산자로 cd00 확인
		System.out.println("2진법 (a & b) >> 8 : " + hexaToBinary((a&b) >> 8));
		System.out.println("10진법 (a & b) >> 8 : " + ((a&b) >> 8)); //걸러진 cd00의 결과		

//		b1이 얼마인지 10진수로 나타내기
		int f = 0x0ff00000;
		System.out.println("b1 : " + ((a&f)>>20));


//		비트연산 비교
//		int c = 0b00000000000000001111100000000000;
		int c = 0x0000f800;
		System.out.println("2진법 a : " + hexaToBinary(a));
		System.out.println("test c: " + hexaToBinary(c));

//		int d = 0b00000000000000000000011111100000;
		int d = 0x00000550;
		System.out.println("2진법 a : " + hexaToBinary(a));
		System.out.println("test d: " + hexaToBinary(d));


//		int e = 0b00000000000000000000000000011111;
		int e = 0x0000001a;
		System.out.println("2진법 a : " + hexaToBinary(a));
		System.out.println("test e: " + hexaToBinary(e));


		System.out.println((a&c)>>11);
		System.out.println((a&d)>>5);
		System.out.println((a&e));
		System.out.println("-----------------------");
		System.out.println((((a)<<16))>>>27);
		System.out.println((((a)<<21))>>>26);
		System.out.println((((a)<<20))>>>27);
		 
		int a = 0x0000cd5a;
		int b = 0x77000000;

		int c1 = 25;

		int d1 = 42;
		int e1 = 26;

		System.out.println("-----------------------");
		System.out.println(Integer.toHexString(a));
		System.out.println(hexaToBinary(a));
		System.out.println(hexaToBinary((c1<<27)>>>16));
		System.out.println(hexaToBinary((d1<<26)>>>21));
		System.out.println(hexaToBinary(e1));
		//		System.out.println(hexaToBinary(a|c1));
		System.out.println(Integer.toHexString(((c1<<27)>>>16)|((d1<<26)>>>21)|e1));

		System.out.println("-----------------------");
		System.out.println(Integer.toHexString(a));


		//		System.out.println(Integer.toBinaryString(c6));
		//		System.out.println(x);
		char c6 = 0x00dc;
		String x = Integer.toBinaryString(c6);
		System.out.println(hexaToBinary(c6));

		System.out.println("--------------------");
		int mask = 0x0080;
		System.out.println(Integer.toBinaryString(c6));
		System.out.println(Integer.toBinaryString(mask));
		
		System.out.println("--------------------");
		String[] arr = new String[] {"선풍기","냉장고","가스레인지","전자레인지","전기장판"
				,"에어컨","에어프라이어","보일러"};

		for(int i = 0; i < arr.length; i++) {
			int xx = Integer.parseInt(x.substring(i, (i+1)));
			String product = arr[i];
			
			
			String yn = (xx == 1)?"on":"off"; 

			System.out.println(product +" "+ yn);
		}

		
		for(int i = 0; i < arr.length; i++) {
			
			if((c6 & mask) == 0) {
				System.out.println(arr[i] + "off");
			}
			else {
				System.out.println(arr[i] + "on");
			}
			mask = mask>>1;
		}	
		
	}
	
}
*/
/*
//ex48) 2진법 32자리 나타내기

public class Hello 
{
	static String hexaToBinary(int n) {
		String str = Integer.toBinaryString(n);
//		System.out.println(str);
//		System.out.println(str.length());
		
//		몇번 반복일지 모르니 while써라(32자리 까지)
		while(str.length() < 32) {
			str = "0" + str;
//			총 32자리가 될 때 까지 앞에 0을 붙여서 총 32자리로 보여주게됨
		}
//		System.out.println(str);
		
//		String은 갱신불가, StringBuffer는 갱신 가능
		
		StringBuffer sb = new StringBuffer(str);	//내용은 str과 같지만 타입 바뀜
//		System.out.println(sb.length());
		
//		앞에서부터 데이터 넣으면 한자리씩 밀려서 어려워짐. -> 뒤에서 부터 넣으면 계산 쉽다.
//		sb.insert(28, " "); //몇번째에 무슨 문자열 넣을거?
//		sb.insert(24, " ");
		
		for (int i = 0; i < 7; i++) {
//			System.out.println((7-i)*4);
			sb.insert((7-i)*4, " ");
		}

//		System.out.println(sb);
		return sb.toString(); //return으로 보내주는게 훨씬 유연하게 쓸 수 있음.
	}
	
	public static void main(String[] args) 
	{
//		int a = 1234;
//		int b = 0x1234;
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(Integer.toBinaryString(a));
//		System.out.println(Integer.toBinaryString(b));
		
//		2진법 4자리씩 끊어서 32자리로 보여줘라
//		11 1011 0001 0010 1100 1101 0101 1001
		int a = 0x3b12cd59;
		int b = 991087961;
//		System.out.println(a);
//		if(a == b) {
//			System.out.println(1);
//		}
		
		System.out.println("2진법 a : " + hexaToBinary(a));
		System.out.println("2진법 b : " + hexaToBinary(b));
	}
}
*/
/*
//ex47) String

public class Hello 
{
	public static void main(String[] args) 
	{
		String str = "무궁화꽃이피었습니다.";
		//1. length();
		System.out.println(str.length());	// 0부터 시작
		
		//2. charAt(int index);
		System.out.println(str.charAt(3));
		
		//3. indexOf();
		System.out.println(str.indexOf("화꽃이"));
		System.out.println(str.indexOf("수"));	//없는 글자 -> -1출력

		int n = str.indexOf("화");
		if (n != -1) {
			System.out.println(n + "번째에서 찾음.");
		} else {
			System.out.println("찾지 못함.");
		}
		
		//4. replace();
		System.out.println(str.replace("꽃이", "나무가"));
		
		//주의!!
//		System.out.println(str);
//		str ="대한민국";
//		System.out.println(str);
//		String -> 데이터 수정 불가. 없애고 새로 만든것임.
		
		//5. subString(slicing)
		System.out.println(str.substring(5));	//5~끝
		System.out.println(str.substring(0,3));	//0~3번앞
		System.out.println(str.substring(3,7)); // 3~7번앞
		
		//6. trim();
		String str2 = "   App   le   ";
		System.out.print(str2.trim());	//앞뒤 공백만 제거
		System.out.println("호랑이");
		
		//7. toUpperCase(), toLowerCase()
		String str3 = "APplEs";
		System.out.println(str3.toUpperCase());
		System.out.println(str3.toLowerCase());
		
		//8. 문자열로 변환 <-> byte로 변환 (network에서 사용할 코드)
//		데이터 받았을때
		byte[] data = new byte[] {65, 'B', 'C', 'D'};
		
		String str4 = new String(data,0,data.length);
		System.out.println(str4);
		
//		데이터 전송할때
		String str5 = "Banana";
		byte[] data1 = str5.getBytes();
		for(byte value: data1) {
			System.out.println((char)value);
		}
	}
}
*/
/*
//ex46-3) try/catch/finally

class Tiger{
	
	//method
	void m1(int a, int b) {
		if(a>b) {
			System.out.println(1);
		}
		else {
//			System.out.println(2); 이것도 가능하지만 코드 많으면 효율성 저하됨.
			return;
		}
		System.out.println(2);
	}
//	두개의 코드가 다르다.
//	m1 -> else이면 바로 리턴
//	m2 -> else라도 finally는 실행됨
	void m2(int a, int b) {
		
		try {
			if(a>b) {
				System.out.println(1);
			}
			else {
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println(2);
		}
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		try {
			int a = 10 / 0;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
//		exception 이 발생 하든 안하는 finally는 작동해야함 -> 밖에 쓰면 안되냐?
		
		Tiger t1 = new Tiger();
		t1.m1(5, 10);
		System.out.println("------------------------------");
		t1.m2(5, 10);
	}
}
*/
/*
//ex46-2) UnhandleException 사용

public class Hello 
{
	public static void main(String[] args) 
	{
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			try {Thread.sleep(2000);} catch (Exception e) {e.printStackTrace();}
		}		
	}
}
*/
/*
//ex46-1) 예외 떠넘기기
 class Tiger{
	
	//method
	void m1() {
		System.out.println(1);
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception 발생");
		}
	}
	
	void m2 ()throws Exception{
			System.out.println(2);
			throw new Exception();
		}
}


public class Hello 
{
	public static void main(String[] args) 
	{
		Tiger t1 = new Tiger();
		t1.m1();
		
		try {
			t1.m2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
/*

//ex46) Exception -> try/catch
public class Hello {
	public static void main(String[] args) 
	{
		System.out.println(1);
		try {
			int a = 10 / 0; //ArithmeticException 발생
		} catch (Exception e) {
//			System.out.println("catch");
			e.printStackTrace();
		}
//		try/catch 사용하므로써 예외가 발생하더라도 프로그램이 중단되지 않고 진행할 수 있다.
		System.out.println(2);
		
		
		int[] arr = {1,2,3};
		try {
			arr[3] = 10; //ArrayIndexOutOfBoundsException
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(3);
		
		String s = "무궁화 꽃이 피었습니다.";
		System.out.println(s.length());
		s = null;
		try {
			System.out.println(s.length()); //NullPointerException
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(4);
	}
}
*/
/*
//ex45-4) 문제점 -> 메서드 인수로 전달하는게 아니라 생성자로 전달해서 해결할 수 있음.

class Baduk{
	
	//field
	AI ai;
	
	//constructor
	
	Baduk(AI ai){
		System.out.println("대국을 시작합니다.");
		this.ai = ai;
	}
	
	//method
	void play() {
		ai.play();
	}
	
	void stop() {
		ai.stop();
	}
//AI 인터페이스를 받아서 메서드 새로 안만들어도 됨
}

interface AI{
	
	void play();
	void stop();
}
//	google에서 제작
class AlphaGo implements AI{
	
	//method
	@Override
	public void play() {
		System.out.println("인공지능은 AlphaGo입니다.");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("AlphaGo가 점수계산을 합니다.");
	}
	
}

//	amazon에서 제작
class BetaGo implements AI{
	
	//method
	@Override
	public void play() {
		System.out.println("인공지능은 BetaGo입니다.");
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("BetaGo가 점수계산을 합니다.");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		AI ai = new AlphaGo();
		
		Baduk baduk1 = new Baduk(ai);		
		baduk1.play();
		baduk1.stop();
		
		ai = new BetaGo();
		Baduk baduk2 = new Baduk(ai);
		baduk2.play();
		baduk2.stop();
	}
}
*/
/*
//ex45-3) 문제점 -> 인터페이스를 활용하여 이전 문제 해결했지만 경기 종료같은 새로운 함수 나오면
// 					문제 발생

class Baduk{
	
	//constructor
	Baduk(){
		System.out.println("대국을 시작합니다.");
	}
	
	//method
	void play(AI a) {
		a.play();
	}
//AI 인터페이스를 받아서 메서드 새로 안만들어도 됨
}

interface AI{
	
	void play();
}
//	google에서 제작
class AlphaGo implements AI{
	
	//method
	@Override
	public void play() {
		System.out.println("인공지능은 AlphaGo입니다.");
	}
}

//	amazon에서 제작
class BetaGo implements AI{
	
	//method
	@Override
	public void play() {
		System.out.println("인공지능은 BetaGo입니다.");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Baduk baduk = new Baduk();
		
		AI ai1 = new AlphaGo();
		baduk.play(ai1);
		
		AI ai2 = new BetaGo();
		baduk.play(ai2);
	}
}
*/
/*
//ex45-2) 문제점 -> 인공지능이 새로 나올때 마다 함수를 새로 정의해야함.

class Baduk{
	
	//constructor
	Baduk(){
		System.out.println("대국을 시작합니다.");
	}
	
	//method
	void play(AlphaGo a) {
		a.play();
	}
	
	void play(BetaGo b) {
		b.play();
	}
//  만들어질때 마다 새로 메서드 정의해줘야하는 문제점 발생
}

//	google에서 제작
class AlphaGo {
	
	//method
	void play() {
		System.out.println("인공지능은 AlphaGo입니다.");
	}
}

//	amazon에서 제작
class BetaGo {
	
	//method
	void play() {
		System.out.println("인공지능은 BetaGo입니다.");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Baduk baduk = new Baduk();
		
		baduk.play(new AlphaGo());
		baduk.play(new BetaGo());
	}
}
*/
/*
//ex45-1) 문제점 -> 인공지능이 바뀐다면 어떻게 할 것인가.

class Baduk{
	
	//constructor
	Baduk(){
		System.out.println("대국을 시작합니다.");
	}
	
	//method
	void play() {
		System.out.println("인공지능은 알파고입니다.");
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Baduk baduk = new Baduk();
		baduk.play();
	}
}
*/
/*
//ex44-2) 익명클래스, 익명객체
interface Animal{

	void m1();
}

class Zoo{
	
	void sound(Animal t) {
		t.m1();
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Animal t1 = new Animal() {
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println(1);
			}
		};
		
		Zoo t2 = new Zoo();
		t2.sound(new Animal() {
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println(2);
			}
		});
	}
}
*/

/*
//ex44-1) 익명클래스, 익명객체
interface Animal{

	void m1();
}

class Tiger implements Animal{

	@Override
	public void m1() {
		// TODO Auto-generated method stub
		System.out.println(1);
	}
	
	
}

class Zoo{
	
	//method
	void m1(Animal t) {
		t.m1();
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
//		Tiger t1 = new Tiger();
//		t1.m1();
//
//		Animal t2 = new Tiger();
//		Animal t2 = new Animal(); //interface 는 객체생성할 수 없음
//
//		익명클래스(객체를 만들지 않고 인터페이스를 바로 메서드 만들어서 쓴다)
//		Animal t3 = new Animal() {
//			@Override
//			public void m1() {
//				// TODO Auto-generated method stub
//				System.out.println(2);
//			}
//		};
//
//		익명 객체(객체가 없는놈을 사용함)
//		new Animal() {
//			@Override
//			public void m1() {
//				// TODO Auto-generated method stub
//				System.out.println(3);
//			}
//		}.m1();

		//익명 클래스
		Animal t3 = new Animal() {
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println("익명 클래스");
			}
		};
		
		//익명 객체
		new Animal() {
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println("익명 객체");
			}
		}.m1();
		
		Zoo t4 = new Zoo();
		
		t4.m1(t3);
	}
}
*/
/*
//ex43-5) this 사용법4
//		다른 생성자 불러올 수 있음
class Tiger {
	
	//field
	
	//constructor
	Tiger(){
		System.out.println(1);
	}
	
	Tiger(int a){
//		인수 0개인 생성자 콜
		this();
		System.out.println(a);
	}
	
	Tiger(int a, int b){
		this(a+b);
//		생성자 콜하게됨 -> 인수 1개인 생성자 콜
		System.out.println(a + " - " + b);
	}
	
	//method
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Tiger t1 = new Tiger(10, 20);
	}
}
*/
/*
//ex43-4) this 사용법
// 전달받은쪽에서 나의 정보가 필요할 때 인수로 this를 전달한다.

class Tiger{
	
	//method 
	void m1() {
		Lion t = new Lion();
				if(t.marry(this)) { 
					System.out.println("감사합니다");
				}
				else{
					System.out.println("싫어요");
				}  // 정보의 판단을 하려면 this를 받아야함
				
	}
	
	int m2() {
		System.out.println("제가 가진 재산은 ");
		
		return 50;
	}
	
	String m3() {
		
		return "온순";
	}
}

class Lion{
	
	boolean marry(Tiger t) {
		if(t.m2() >= 80) {
			return true;			
		}
		else {
			
			String s = t.m3();
			
			switch (s) {
			case "온순":
				
				return true;
				
			case "난폭":
				
				return false;
			}
			
		}
		return false;
	}
}

public class Hello {
	public static void main(String[] args) 
	{
		Tiger t = new Tiger();
		t.m1();
		
	}
}
*/
/*
//ex43-3) this 사용법 2 (메서드 호출을 체이닝 해서 사용)
class Animal{

}

class Tiger extends Animal{
	
	
	//method
	Tiger m1() {
//		return new Tiger();
		
//		Tiger t = new Tiger();
//		return t;
		System.out.println(1);
		
		return this;
	}
	
	Tiger m2() {
		System.out.println(2);
		
		return this;
	}
	
	void m3() {
		System.out.println(3);
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{

		Tiger t1 = new Tiger();
		Tiger t2 = t1.m1();
		
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());

//		return this를 활용해서 내부의 메서드를 연속해서 부르는 것을 체이닝 이라고 한다.
//		t2.m2().m1().m2().m3();
//		t1.m1().m2();
		
//		체이닝을 통해 코드의 난입을 방지할 수 있음
		t1.m1().m2().m3();
		
		System.out.println("-------------------");
//		코드의 난입이 일어날 수 있게됨
		t1.m1();
		t1.m2();
		System.out.println("호랑이");
		t1.m3();
		
	}
}
*/
/*
//ex43-2) this의 4가지 용법
class Animal{

}

class Tiger extends Animal{

	//field
	int a, b;
	int size;
	
//	public Tiger(int size) {
//		super();
//		this.size = size;
//	}

	//constructor
	//	default 생성자의 원형(생략된 부분 포함한 버전)
	//	Tiger this;
	//	Tiger(Tiger t){
	//		this = t;
	//	}

	Tiger(int a, int b){
//		this 용법 1번 : 필드와 인수의 이름 같을때
		//		생성자의 목적 -> 멤버 변수의 초기화.
//				x = x;
//				y = y;
		
		this.a = a; //field x = 인수 x
		this.b = b;
	}
	
	//method
	public void show() {
		System.out.println("a = " + this.a + ", b = " + this.b);
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Tiger t = new Tiger(10, 20);
		int num = 10;
//		num = num;	//자기대입.. -> 아무 쓸모없는 코드다.
		
		t.show();	
	}
}
*/

/*
//ex43-1) this
class Animal{

}

class Tiger extends Animal{
	
	//constructor
//	Tiger this;
//	Tiger(Tiger t, int a, int b){
//		this = t;
//	}
//  생성자에 생략돼있는 코드다. 생략돼있는 형태가 아래다.
//	this라는 타이거 변수가 존재하는데 안보여주고있는것임.
	
	Tiger(int a, int b){
//		System.out.println(this);
//		this 는 원래 존재하므로 이렇게 가능
		System.out.println(this.hashCode());
	}

	//method
	public void m1(int a, String b, Tiger c) {
		System.out.println(1);
	}
	
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
//		Tiger t1 = new Tiger(10, 20);
//		t1 -> t, 10 -> a, 20 -> b 로 넘어간다
//		t2 -> t, 30 -> a, 40 -> b 로 넘어간다
//		a,b 가 따로 넘어간다는 말은 t1, t2도 따로 t로 넘어간다.
//		생략이 돼있기 때문에..
		Tiger t1 = new Tiger(10, 20);
		Tiger t2 = new Tiger(30, 40);
		
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
//		생성자에서 hashcode 똑같음 -> 이 개념 잡아야 this 이해할 수 있음.
//		t1.m1(1,"1",t1);
	}
}
*/

/*
//ex42) final 사용법
final class Animal{

	//field
	final int NUM = 10;
//	final 붙으면 앞으로 이 값은 변경할 수가 없음. -> 불변의 수를 만들고 싶을때 사용
//	final 변수는 대문자로 적는게 관례
	
	//constructor
	
	//method
	final void M1() {
		
	}
//	메서드에 final 붙으면 Override 더이상 불가능함.
}

//class Tiger extends Animal{ //final 클래스는 상속 불가능
class Tiger{
	
//	@Override
//	void M1() { 
//		Cannot override the final method from Animal
//	}
	
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Animal t1 = new Animal();
//		t1.NUM = 20; //final 붙어있어서 값을 변경하는 코드 자체가 성립이 안됨.
		System.out.println(Math.PI);
		
		String s1 = null;
	}
}
*/

/*
//ex 41) 다중상속(인터페이스 이용)
class A{
	void m1() {
		System.out.println(1);
	}
}

interface B{
	void m2(); //함수 원형(프로토타입)이라고 한다. 코드를 제외한 제일 첫부분
}

interface C{
	void m3();
}

//클래스 D는 부모를 ABC가지고 싶다(다중)
class D extends A implements B,C{

	@Override
	public void m2() {
		// TODO Auto-generated method stub
		System.out.println(2);
	}
	
	@Override
	public void m3() {
		// TODO Auto-generated method stub
		System.out.println(3);
	}
}

@FunctionalInterface //인터페이스에서 함수원형을 한개만 강요할때 위 어노테이션 사용
interface E{
	void m1();
	//void m2(); //두개쓰면 오류남
}

public class Hello 
{
	public static void main(String[] args) 
	{
		D t1 = new D();
		t1.m1();
		t1.m2();
		t1.m3();
		
		Thread t2 = null;//습관적으로 null을 넣어라(Thread)할땐
		
		String s = null;
		
		StringBuffer sf = null;
		
	}
}
*/

/*
//ex40) 인터페이스 오버라이딩
interface 한국은행{
	void 입금();
	void 출금();
	void 이체();
	void 대출();
}

class 국민은행 implements 한국은행{	
	@Override
	public void 대출() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void 이체() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void 입금() {
		// TODO Auto-generated method stub
		System.out.println("이자율은 3% 입니다.");
	}
	
	@Override
	public void 출금() {
		// TODO Auto-generated method stub
	}
}

class 우리은행 implements 한국은행{
	@Override
	public void 입금() {
		// TODO Auto-generated method stub
		System.out.println("이자율은 13% 입니다.");
	}

	@Override
	public void 출금() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void 이체() {
		// TODO Auto-generated method stub
	}

	@Override
	public void 대출() {
		// TODO Auto-generated method stub
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		국민은행 t1 = new 국민은행();
		우리은행 t2 = new 우리은행();
		
		t1.입금();
		t2.입금();
	}
}
*/


/*
//ex39) 추상클래스
abstract class 대장장이{
	void 칼() {
		System.out.println("칼을 생성중");
	}
	
	abstract void 방패();
}

class 나그네1 extends 대장장이{
	void 방패() {
		System.out.println("나그네1  - 방패 생성");
	}
}

class 나그네2 extends 대장장이{
	@Override //부모가 가진 함수이름과 자식이 가진 함수이름이 동일 = 오버라이딩(상속과 관련)
	void 방패() {
		
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		나그네1 t1 = new 나그네1();		
		
		t1.방패();
		t1.칼();
		
		나그네2 t2 = new 나그네2();
		t2.칼();
	}
}
*/

/*
//ex39) 인터페이스
//abstract class Animal{
//	abstract void m1();
//}

interface Animal{ //interface가 더 유용한 선택지를 가짐
	abstract void m1();
}

class Tiger implements Animal{
	public void m1() { //정의해줄때 public이 있어야한다.
		System.out.println(2);
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Tiger t = new Tiger();
		t.m1();
		
		//Animal t2 = new Animal(); //abstract로 인해 코드 미완성 -> 객체 생성 불가
	}
}
*/


/*
//ex38 - 다형성


class Animal{
	void cry() {
		System.out.println("...");
	}
}

class Dog extends Animal{
	void cry() {
		System.out.println("멍멍");
	}
}

class Cat extends Animal{
	void cry() {
		System.out.println("야옹");
	}
}

class Snake extends Animal{
	
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Random rn = new Random();	
		
		Animal t0 = new Dog();
		Animal t1 = new Cat();
		Animal t2 = new Snake();
		
		for(int i=0; i<10; i++)
		{
			int num = rn.nextInt(3);
			
			switch (num) 
			{
				case 0:
					t0.cry();
					break;
					
				case 1:
					t1.cry();
					break;
					
				case 2:
					t2.cry();
					break;
			}
		}
		
		System.out.println("---------------------");
		
		
		Animal[] t3 = new Animal[] {new Dog(), new Cat(), new Snake()};
		
		for(int i=0; i<10; i++)
		{	
			t3[rn.nextInt(3)].cry();
		}
	}
}
*/

/*
//ex37 - 랜덤 숫자 생성기
public class Hello 
{
	public static void main(String[] args) 
	{
		Random rn = new Random();
		
		for(int i=0; i<10; i++)
		{
			//int num = rn.nextInt(2);
			System.out.println(rn.nextInt(10));
//			10 이하 int로 나타내라
		}
	}
}
*/

/*
//ex36 - 배열
public class Hello 
{
	public static void main(String[] args) 
	{
		int a = 10;
		
		int[] arr = new int[5];
		int[] brr = new int[] {1,2,3,4,5};
		int[] crr = {10,20,30,40,50};
		
		System.out.println(arr.length);
		
		for (int i = 0; i < crr.length; i++) {
			System.out.print(brr[i] + " - ");
		}
		System.out.println();
		//변수중 x를 많이씀 , data, value 
		for (int x : crr) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		//유효범위 : 0 ~ 배열길이 -1		
		crr[4] = 200;
		System.out.println(crr[4]);
		
		int num = 3;
		arr[num] = 500;
		arr[num*3 - 5] = 400;
		
		arr[2] = 3;
		arr[arr[2]] = 999;
		
		System.out.println(arr[2] + " " + arr[3]);
		
		arr[arr[3]-998] = 888;
		System.out.println(arr[1]);
	
		System.out.println("---------------");
		
		boolean[] drr = {true, false, true, false};
		
		for (boolean x : drr) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		System.out.println("---------------");

		String[] ss = new String[4];
		ss[0] = "호랑이";
		ss[1] = "강아지";
		ss[2] = "고양이";
		ss[3] = "곰";
		
		// String ss[2] = new String("독수리");
		
		for(String aaa : ss)
		{
			System.out.println(aaa);
		}
		
		String[] st = new String[] {"진달래", "민들레", "장미"};
		String[] su = new String[] {new String("진달래"), "민들레", "장미"};
	}
}
*/

/*
//ex35) 캐스팅을 통한 메서드 활용
class Animal{
	void cry() {
		System.out.println("부모 메서드...");
	}
}

class Dog extends Animal{
	void cry() {
		System.out.println("멍멍");
	}
}

class Cat extends Animal{
	void cry() {
		System.out.println("야옹");
	}
}

class Snake extends Animal{
//	void cry() {
//		System.out.println("쉭쉭");
//	}
}

class Zoo{
	//void sound(Dog t) 
	//{
	//	t.cry();
	//}
	
	//void sound(Cat c)
	//{
	//	c.cry();
	//}
	
	//void sound(Snake s)
	//{
	//	s.cry();
	//}
	
	void sound(Animal a1)
	{
		a1.cry();
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Animal t1 = new Dog();
		Animal t2 = new Cat();
		Animal t3 = new Snake();
		
		t1.cry();
		t2.cry();
		t3.cry();
		
		Dog t4 = new Dog();
		Cat t5 = new Cat();
		Snake t6 = new Snake();
		
		t4.cry();
		t5.cry();
		t6.cry();
		
		System.out.println("-----------------");
		
		Zoo t7 = new Zoo();
		
		t7.sound(t4);
		t7.sound(t5);
		t7.sound(t6);	
	}
}
*/

/*
//ex34) 캐스팅
class Animal{
	void m1() {
		System.out.println(1);
	}
	
	void m3() {
		System.out.println(31);
	}
}

class Tiger extends Animal{
	void m2() {
		System.out.println(2);
	}
	
	void m3() {
		System.out.println(32);
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Animal t1 = new Animal(); //case1) 부모 - 부모 
		Tiger t2 = new Tiger();   //case2) 자식 - 자식
		Animal t3 = new Tiger();  //case3) 부모 - 자식(UpCasting)
		//Tiger t4  = new Animal(); //case4) 자식 - 부모(DownCasting - 에러)
		
		//자녀에게서 함수를 먼저 찾고 없으면 부모를 호출함(자녀 먼저 호출)
		t3.m3();
	}
}
*/

/*
//ex33) super
class Animal{
	Animal(){
		System.out.println("부모생성자 Call");
	}
	
	Animal(int n){
		System.out.println("super 사용 : " + n);
	}
}

class Tiger extends Animal{
	Tiger(){
		//현재 라인에 코드가 한줄 생략돼있다.( super(); )
		//super(); ->부모의 생성자를 Call 시킴
		System.out.println("자식생성자 Call");
	}
	
	Tiger(int num){
		super(2*num);
		System.out.println(num);
	}
	
	Tiger(int a, int b)
	{
		super(a*b);
		System.out.println("3번째 생성자");
		//super(); 코드의 제일 첫줄에 넣어야한다.
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		//Animal t1 = new Animal();
		Tiger t1 = new Tiger();
		System.out.println("--------------------");
		Tiger t2 = new Tiger(100);
		System.out.println("--------------------");
		Tiger t3 = new Tiger(3,4);
		
	}
}
*/


/*
//ex32
class Animal{
	void m2() {
		System.out.println(2);
	}
	
	void m3() {
		System.out.println("Animal - m3");
	}
}

class Tiger extends Animal{
	void m1() {
		System.out.println(1);
	}
	
	void m3() {
		System.out.println("Tiger - m3");
	}
	
	void m4() {
		m3();
		super.m3();
		System.out.println(4);
	}
}

public class Hello 
{
	public static void main(String[] args) 
	{
		Animal t1 = new Animal();
		Tiger t2 = new Tiger();
		
		t2.m1();
		t2.m2();
		t2.m3();
		
		t2.m4();
	}
}
*/
/*-------------------------------------------------------*/
/*
//ex31) Math클래스 메서드 정리
public class Hello {
	public static void main(String[] args) {

//		절대값
		System.out.println(Math.abs(-10));
//		올림
		System.out.println(Math.ceil(-10.1));
		System.out.println(Math.ceil(5.1));
//		내림
		System.out.println(Math.floor(-10.1));
		System.out.println(Math.floor(5.1));
//		최대값
		System.out.println(Math.max(-10,5));
//		최소값
		System.out.println(Math.min(-10,5));
//		랜덤값
		System.out.println(Math.random());
//		가까운 정수의 실수값
		System.out.println(Math.rint(1.3));
		System.out.println(Math.rint(1.6));
//		반올림값
		System.out.println(Math.round(2.4));
		System.out.println(Math.round(2.6));
		
	}
}
*/
/*
//ex30) public static void main -> entry point(진입점)

class Tiger{
	//field
	
	//constructor
	
	//method
	static void myMain() {
		Tiger t = new Tiger();
		t.m1(); //객체 생성이 되었기 때문에 쓸 수 있다.
//		m1(); //static이 아니라서 쓸 수 없다.
	}
	
	void m1() {
		
	}
}

public class Hello {
	
	static int num = 10;
	int num1 = 10;
	
	Hello(){
		
	}
	void m1() {
	}
	
	static void m2() {
		System.out.println("m2 call");
	}
	
	public static void main(String[] args) {
		System.out.println("1000");
//		static 함수 안에서는 객체 생성 안하고는 반드시 static 멤버만 사용 가능하다.
		
		num = 10;
//		num1 = 20;
		
//		m1();
		m2();
		

		System.out.println("m1 call");
		Hello h = new Hello();
		h.m1();	//객체 생성 했으므로 static이든 아니든 다 쓸 수 있다.
		h.m2();
		
//		Hello.m1(); //static 아니기 때문에 객체가 생성 되어야 사용 가능
		Hello.m2();	//static 이므로 객체 생성 없이 사용 가능
	}
//	public static void main -> static 이기 때문에 객체 생성 없이도 부를 수 있음.
//	static 제거하면 운영체제가 메서드를 호출할 수가 없음.
	
}
*/
/*
//ex29) static

//static 사용이유 -> 모든 객체가 공통으로 쓸 전역변수를 쓰고 싶을때
// 메서드에 static 쓰는 이유 -> 실행 속도와 관련 된 메서드 -> 수학과 관련되어있는 메서드들..

//static int num2;
// static 이라는 놈은 컴파일되면 클래스 밖으로 튀어나와서 고정되는 놈이라고 보면 됨(관제탑 같은놈)
// static은 객체 생성 안하고도 바로 호출 가능함.
class Tiger{
	
	//field
	int num1;
	static int num2 = 100; // static은 초기값을 주는게 원칙

	//constructor
	
	//method
	static void m1() {
		System.out.println("static 메서드 call");
	}
}

public class Hello {
	public static void main(String[] args) {
		System.out.println(Tiger.num2);
		
		Tiger t1 = new Tiger();
		Tiger t2 = new Tiger();

		t2.num2 = 300;
		System.out.println(t1.num2);
//		관제탑은 하나이므로 클래스안에서 static은 값이 다 같아진다
//		원칙은 객체.static 가 아니라 클래스이름.static 으로 써야한다.
		
		Tiger.m1();
		t1.m1(); //문법은 성립하지만 class이름으로 static 불러라
		
// example) static메서드 쓰는종류
		System.out.println(Math.abs(-2.5413));
//		-> abs메서드는 static으로 잡혀있다. -> 속도를 높여보려고 시도한 결과다.
	}
}
*/


/*
//ex28) 메서드, 클래스 사용법

class Car{
	
	//field
	private int fuel = 100;
	private String name = "무명";
	
	//constructor
	Car(){
		System.out.println("기본 call");
	}
	
	Car(String n, int f){
		System.out.println("인수전달 call");
		this.fuel = f;
		this.name = n;
	}
	
	//method
	public void move() {
		System.out.println(this.name + "자동차가 달린다");
		fuel -= 30;
		if(fuel <= 0) {
			fuel = 0;
		}
	}
	
	public void stop() {
		System.out.println(this.name + "자동차가 멈춘다.");
		fuel -= 10;
	}
	
	public void inject() {
		System.out.println(this.name + "자동차에 연료 주입.");
		
		fuel += 100;
		
	} 

	public void inject(int f) {
		System.out.println(this.name + "자동차에 연료 주입.");
		
		fuel += f;
	} 
	
	public void show() {
		System.out.println(this.name + "자동차의 연료가 " + fuel + "만큼 남았습니다.");		
	}
	
}

public class Hello {
	public static void main(String[] args) {
		Car car1 = new Car();
		Car car2 = new Car("꼬마", 200);
		
		car1.move();
		car2.move();
		
		car1.stop();
		car1.inject();
		
		car1.show();
		car2.show();	
	}
}
*/

/*
//ex27) 기본 생성자, 생성자

class Tiger{

	//field

	//constructor
	//	생성자를 만들면 기본 생성자는 안만들기 때문에 쓸수 없음
	Tiger(int a, int b){

	}
	//	기본 생성자 만들어줘야 쓸 수 있게 됨 -> 웬만하면 기본 생성자 만들어주라.
	Tiger(){

	}
	
	//method
}	

public class Hello {
	public static void main(String[] args) {
		
//		Tiger t1 = new Tiger();
		Tiger t2 = new Tiger(10, 20);
		Tiger t1 = new Tiger();
		
	}
}
*/

/*
//ex26) 생성자
// 1. 생성자는 메서드다.
// 2. 생성자 메서드 이름은 클래스 이름과 반드시 동일해야 한다.
// 3. 생성자 메서드는 리턴값을 가질 수 없다. -> void 조차도 생략
// 4. 생성자 메서드도 오버로딩(메서드 재정의)이 가능하다
// 5. 생성자 메서드는 객체가 인스턴스화 될 때 단 한번 호출 된다.(임의로 호출 불가능)
// 6. 생성자 메서드의 사용 목적은 멤버 메서드를 초기화 시키는 목적으로 사용된다.
// 7. 생성자 메서드를 작성하지 않을 시에는 자동으로 default 생성자가 만들어진다.

class Tiger{
	//field
	int age;
	String name;
	
	//constructor
	public Tiger(){
		System.out.println("기본 생성자가 호출되었다.");
		this.age = 20;
		this.name = "홍길동";
	}
	
	public Tiger(int Age, String Name){
		System.out.println("오버로딩 생성자가 호출되었다.");
		this.age = Age;
		this.name = Name;		
	}
	
	//method
	public void showInfo() {
		System.out.println(name + " : " +age);
		System.out.println("");
	}
}

public class Hello {
	public static void main(String[] args) {
		Tiger t1 = new Tiger();
		Tiger t2 = new Tiger();
		
		Tiger t3 = new Tiger(100,"독수리");
		
		Tiger t4 = new Tiger(200, "앵무새");
		
		t1.showInfo();
		t2.showInfo();
		t3.showInfo();
		t4.showInfo();
	}
}
*/
/*
//ex25) 숫자 <-> 문자
public class Hello {
	public static void main(String[] args) {
		int a = 100;
		System.out.println(a + 100);
//		숫자 -> 문자열
		String s1 = Integer.toString(a);
		String s2 = "" + a;
		System.out.println(s1+100);
		System.out.println(s2+100);
		
//		문자열 -> 숫자
		String s3 = "123";
		System.out.println(s3 + 100);
		
		int b = Integer.parseInt(s3);
		System.out.println(b + 100);
		
	}
}
*/
/*
//ex24) method overload(메서드 재정의)
class Tiger{
	
	//method
//	1. 인수 갯수가 다르면 오버로딩(메서드 재정의) 가능
	public void method01() {
		System.out.println(1);
	}

	public void method01(int a) {
		System.out.println(a);
	}

	public void method01(int a, int b) {
		System.out.println(a + b);
	}
	
//	2. 인수 타입이 다르면 오버로딩(메서드 재정의) 가능
	public void method01(String str) {
		System.out.println(str);
	}
	
	public void method01(float f) {
		System.out.println(f);
	}

	public int getAreaRect(int width, int height) {
		return width * height;
	}
	
	public int getAreaRect(int length) {
		return length * length;
	}
	
}

public class Hello {
	public static void main(String[] args) {
		Tiger t1 = new Tiger();
		t1.method01();
		t1.method01(10);
		t1.method01(10, 5);
		t1.method01("String");
		t1.method01(3.14f);
		
		System.out.println(t1.getAreaRect(10, 8));
		System.out.println(t1.getAreaRect(6));
	}
}
*/

/*
//ex23) continue, break
public class Hello {
	public static void main(String[] args) {
		for (int i = 0; i < 10 ; i++) {
			if (i < 3) {
				continue; //continue 만나면 바로 i++로 넘어감
			}
			System.out.println(i);
			if (i == 7) {
				break; //가장 가까이 있는 반복문 탈출
			}
		}
		System.out.println("호랑이");
		
		int num = 0;
		while (true) {
			
			num++;
			if (num % 2 != 0) {
			System.out.println("호랑이" + num);
				continue;
			}
			if (num == 10) {
				System.out.println("코끼리" + num);
				break;
			}
		}
	}
}
*/
/*
//ex22) method return 키워드

class Tiger{
	
	//method
	public int m1(int a, int b) {
		
		if (a > b) {
			return 100;
		}
		else {
			System.out.println("호랑이");
		}
		return 200;
	}
	
	public void m2() {
		System.out.println(1);
		return; //return 값은 없음 여기서 중단하게됨
//		System.out.println(2); //return 밑에는 코드 쓸 수 없음
	}
	
	public void m3(int num) {
		for (int i = 0; i < 10; i++) {
			if (i == num) {
				System.out.println(i + " 찾았다.");
				return; //찾았기때문에 for문 더 이상 돌 필요가 없음
			}
		}
		System.out.println("데이터 찾지 못했음");
	}
}

public class Hello {
	public static void main(String[] args) {
		
		Tiger t1 = new Tiger();
		t1.m1(5, 10);
		t1.m3(11);
	}
}
*/
/*
//ex21) 메서드 리턴값 타입

class Tiger{
	
	//method
	public String m1() {
		return "독수리";
	}
	
	public Lion m2() {

//		return new Lion();
		Lion l1 = new Lion();
		
		l1.show();
		
		return l1;
	}
	
	public Lion m3(Lion a) {
		return a;
	}
}

class Lion{
	
	//method
	public void show() {
		System.out.println(1000);
	}
}

public class Hello {
	public static void main(String[] args) {

		Tiger t1 = new Tiger();
		String str = t1.m1();
		System.out.println(str);
		
		Lion t2 = t1.m2();
		t2.show();
		
		Lion t3 = t1.m3(new Lion());
		t3.show();
		
		t1.m3(new Lion()).show();
	}
}
*/
/*
// ex20) 메서드, 인수 타입
class Tiger{
	//field
	
	//constructor
	
	//method
//	주의 public void m1(int a,b) 은 불가함
	public void m1(int a, int b) {
		System.out.println(a + ", "+ b);
		System.out.println(a * b + a);
	}
	
	public void m2(int a, char b, boolean c, float d, String e) {
		System.out.println(a + " " + b + " " + c + " " + d + " " + e);
	}
	
	public void m3(Lion a) {
		System.out.println(a.hashCode());
		a.sound();
	}
}

class Lion{
	
	//method
	public void sound() {
		System.out.println("어흥");
	}
}

public class Hello {
	public static void main(String[] args) {
		String s1 = "호랑이";
		System.out.println(s1.length());
		System.out.println(s1);
		
		Tiger t1 = new Tiger();
		t1.m1(3, 4);
		t1.m2(10, '한', true, 3.14f, "호랑이");

		
		Lion l1 = new Lion();
//		System.out.println(l1.hashCode());
		t1.m3(l1);
		
// 		Lion a = new Lion();
		t1.m3(new Lion());
		t1.m3(new Lion());
	}
}
*/

/*
//ex19) 메서드
class Tiger {
	//field
	
	//constructor
	
	//method

//	함수 사용의 구성
//return 값   인수전달
//	x		 x
//	x		 O
//	O		 x
//	O		 O
	
	public void merry() {
		System.out.println("merry 멍");
	}
	
	public void happy(int num) {
		
		for(int i = 0; i < num; i ++) {
			System.out.println("happy 멍");
		}
	}
	
	public int turtle() {
		
		System.out.println("turtle call");
		
		return 3;
	}
	
	public int fish(int num) {
		System.out.println("fish call");
		
		int n = num * 3;
		return n;
	}
	
}

public class Hello {
	public static void main(String[] args) {
		Tiger t = new Tiger();
//		t.merry();
		for (int i = 0; i < 3; i++) {
			t.merry();
		}
		
		System.out.println("------------------------");
		t.happy(5);
	
		System.out.println("------------------------");
		int num = t.turtle();
		
		System.out.println(num);
		System.out.println(t.turtle());
				
		System.out.println("------------------------");
		
		int fishNum = t.fish(3);
		System.out.println(fishNum);
		System.out.println(t.fish(5));
		
	}
}
*/
/*
//ex18) 클래스 인스턴스화, 참조

class Tiger {
	//field
	int a = 10, b = 20, c = 30, d = 40;
	//constructor
	
	//method
}


public class Hello {
	public static void main(String[] args) {

		Tiger t1 = new Tiger();
		Tiger t2 = new Tiger();
		
		t1.a = 100;
		t1.c = t1.a + 30;
		System.out.println(t1.a);
		System.out.println(t1.b);
		System.out.println(t1.c);
		System.out.println(t1.d);
		
		System.out.println(t1.c);
		System.out.println(t2.c);
		
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
		
		Tiger t3;
		t3 = t2;
		System.out.println(t3.hashCode());
//		t2, t3 변수는 서로 참조한다 -> 메모리를 공유한다.
		t3.a = 999;
		System.out.println(t3.a);
		System.out.println(t2.a);
		
	}
}
*/
/*
//ex17) 전치증감, 후치증감
public class Hello {
	public static void main(String[] args) {
		int a = 10, b, c, d;
		b = a++;
		System.out.println(a + " - " + b);
		
		c = 10;
		d = ++c;
		System.out.println(c + " - " + d);
		
		int a1 = 10, a2 = 10;
		System.out.println(a1++ + " - " + ++a2);
		System.out.println(a1 + " - " + a2);
	}
}
*/
/*
//ex16) 리터럴, cast
public class Hello {
	public static void main(String[] args) {
		int  a     =      10   ; 
	// 타입 변수명 대입연산자  리터럴 세미콜론
	
		int b = 20;
		short c = 30;
//		대입 연산자는 두 타입이 같아야함
//		캐스팅을 통해 타입 맞춰줘야함.
		b = (int)c;
		c = (short)a;
		
		int num = 0;		
		num = num + 3;
		num += 3;
		
		int t1 = 1;
		int t2 = +1;
//		자리수 맞출때나 +1 로 쓴다
		int t3 = -1;
		int t4 = +1;
		
		int k = 10;
		k += 3;
		System.out.println(k);
		k = +3;
		System.out.println(k);
//		헷갈리지않게 주의해라
		
		int d = 0;
		d++;
		System.out.println(d);
		++d;
		System.out.println(d);
		d = d + 1;
		System.out.println(d);
		d += 1;
		System.out.println(d);
	}
}
*/
/*
//ex15) 콜라츠 추측(우박수)
// 알고리즘 거치면 모든 수는 1로 귀결됨
public class Hello {
	public static void main(String[] args) {
//		예제1
		int n = 11;
		
		while(true) {
			
//			if (n%2 == 0) {
//				n = n/2;
//			} else {
//				n = n*3 + 1;
//			}

//			삼항연산 처리
			n = (n%2 == 0) ? n/2 : n*3 + 1; 
			
			System.out.println(n);
			
			if(n == 1) {
				System.out.println("탈출 직전");
				break;
			}
		}

//		결국 반복횟수를 모른다는 것이 중요..
		int n2 = 1234567;
		int sum = 0;
		while(true) {
			int r = n2%10;
			n2 = n2/10;
			
			sum += r;
			if(r == 0) {
				System.out.println("탈출");
				break;
			}
		}
		System.out.println(sum);
	}
}
*/
/*
//ex14) while문
public class Hello {
	public static void main(String[] args) {
//		같은 반복문이지만 for문은 반복횟수 알 때, while문은 반복횟수 모를 때 사용
		int num = 5;
//		while문 무한loop 조심해라.
//		while(true) {
//			System.out.print(num + "");
//			num++;
//		}
		
		int n = 0;
		while(num > n) {
			n++;
			System.out.println(n);
		}
		System.out.println("호랑이");
//		코드는 성립되나 반복횟수를 알면 for문으로 쓰면 된다.
		
		int a = 0;
		while (true) {
			System.out.println(a);
			if (a == 5) {
				System.out.println("탈출 직전");
				break;
			}
			a++;
		}
//		이 코드도 사실은 반복횟수 알고있기때문에 좋은코드 아님
	}	
}
*/
/*
//ex13) 합산 프로그램
public class Hello {
	public static void main(String[] args) {
		
		int sum = 0;
		for (int i = 1; i <= 10 ; i++) {
			sum += i;
//			System.out.println("i = " + i);
//			System.out.println("sum = " + sum);
		}
		
		System.out.println("sum = " + sum);
		
		for (int i = 1; i <= 8; i++) {
			System.out.println("---- " + (i+1) + "단 ----");
			
			for (int j = 1; j <= 9; j++) {
				int multiple = (i+1) * j;
				System.out.println((i+1) + " * " + j + " = " + multiple);
			}
			
			System.out.println("");
		}
	}
}
*/
/*
//ex12) for문
public class Hello {
	public static void main(String[] args) {
//		반복문 실행
//		1. 변수 초기화
//		{2. 조건
//		3. 실행
//		4. 증감
//		5. 조건}
		int num = 5;
		num = num + 3;
		
		System.out.println(num);
		num = num + 1;
		System.out.println(num);
		num++;
		System.out.println(num);
		num--;
		System.out.println(num);
		++num;
		System.out.println(num);
		
		for (int i = 0; i < num; i++) {
			System.out.println("호랑이" + i);
		}
		System.out.println(1000);
		
//		잘못된 for문의 예
//		1. int i = 0 or 1이어야한다.
//		2. 부등호 부호 바꾸지 마라
//		3. i++, ++i 아닌 경우
//		-> 코드를 억지로 짜고있는 경우다..몇번 반복하는지 한눈에 들어와야함
//		제어는 for문 안에서 제어하면 됨.
//		for (int i = 5; i > 4; i = i+3) {}
		
//		for문 사용법 3가지 형식
		
		for (int i = 0; i < 5; i++) {
			//for문 5번 돈다
		}
		
		for (int i = 1; i <= 5; i++) {
			//for문 5번 돈다
		}
		
		int n = 5;
		for (int i = -n; i <= n; i++) {
			//좌표값 제어할때 씀
			//for문 11번 돈다 (5*2 + 1)
		}
		
	}
}
*/
/*
//ex11) switch문
public class Hello {
	public static void main(String[] args) {
		
//		1. case 뒤에는 변수가 올 수 없다.
//		2. switch 안에는 변수가 올 수 있다.
//		3. defalut break 생략 가능
//		4. 의도적인 경우 break 생략 가능. break 없으면 그대로 진행해버림
//		5. case문에서 실행되는 문장이 한 문장 이상인 경우 일반적으로 {}(블록처리) 한다.
		int num = 10;
		switch (num) {
//		case num:
		case 10:
			System.out.println(1);
//			break;
		case 20:{
			System.out.println(2);
			System.out.println(100);
		}break;
		case 30:
			System.out.println(3);
			break;

		default:
			System.out.println(4);
			break;
		}
		
		// 4) if - else if (switch문으로 바뀔수 있다면 바꿔라)
		
				int jumsoo = 88;
				int q = jumsoo/10;
				
				switch (q) {
				case 9:
					System.out.println("A");
					break;
				case 8:
					System.out.println("B");
					break;
				case 7:
					System.out.println("C");
					break;
				case 6:
					System.out.println("D");
					break;
				default:
					break;
				}				
				
				if(q == 9) {
					System.out.println("A");
				} else if(q == 8) {
					System.out.println("B");
				} else if(q == 7) {
					System.out.println("C");
				} else if(q == 6) {
					System.out.println("D");
				} else {
					System.out.println("E");
				}
				
//			왜 else if 일때 바꾸는게 좋은가? 프로그램 실행속도가 안정적이게됨
//			else if 문은 참이될때까지 여러번 물어보면서 실행
//			but switch문은 해당 분기로 바로 넘어가기때문에 실행속도가 평균적으로 안정적이게됨
		
	}
}
*/

/*
//ex10) 제어문
// if, for, switch, while
public class Hello {
	public static void main(String[] args) {
		// 1) if
		System.out.println(1);

		if (true) {
			System.out.println(2);
			System.out.println(4);
		}
		;
		System.out.println(3);

		// 2) if - else
		if (false) {
			System.out.println(5);
		} else {
			System.out.println(6);
		}
		// 3) 삼항 연산자 (if - else -> 삼항 연산자로 바꿔라)

		int a = 10, b = 5;
		int c;

		if (a > b) {
			c = 7;
		} else {
			c = 8;
		}
		System.out.println(c);

//		삼항 연산의 조건 1. if - else 2. 받는 변수가 같아야함
		

		c = a > b ? 7 : 8;

		System.out.println(c);
		// 아래 위 식은 완벽하게 같다.
		// 이렇게 같은 결과를 더 간단한 코드로 나타낼 수 있는 것을 슈가코드라고 한다.

		if (a > b) {
			System.out.println("호랑이");
		} else {
			System.out.println("코끼리");
		}

		// 삼항연산자
		System.out.println(a > b ? "호랑이" : "코끼리");

		// 4) if - else if (switch문으로 바뀔수 있다면 바꿔라)
		
		int num = 88;
		
		if(num >= 90) {
			System.out.println("A");
		} else if(num >=80) {
			System.out.println("B");
		} else if(num >= 70) {
			System.out.println("C");
		} else if(num >= 60) {
			System.out.println("D");
		} else {
			System.out.println("E");
		}
	}
}
*/

/*
//ex9) 변수를 사용한 연산자 연습 & 연산자 우선순위
public class Hello {
	public static void main(String[] args) {
		int a = 17, b = 3;
		
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		
		a = 30;
		b = 4; // b = 0 이 되면 불가능
		
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		
		System.out.println(a>b);
		System.out.println(a>=b);
		System.out.println(a<b);
		System.out.println(a<=b);
		System.out.println(a==b);
		System.out.println(a!=b);

		System.out.println(a>b*15); //산술연산 -> 비교연산
		System.out.println(a>b*15 || a-10>b); //산술연산 -> 비교연산 -> 논리연산
	}
}
*/
/*
// ex8) swap 프로그램
public class Hello {
	public static void main(String[] args) {
	
		//		교환(swap)
		int aa = 88, bb = 99, temp;

		System.out.println("aa = " + aa + ", bb = " + bb);
		
		temp = aa; //temp = 88
		aa = bb; // aa = 99
		bb = temp; // bb = 88
		
		System.out.println("aa = " + aa + ", bb = " + bb);
		System.out.println("-------------------------------");
		
		
		int cc = 100, dd = 200;
		System.out.println("cc = " + cc + ", dd = " + dd);

		int tt = cc;
		cc = dd;
		dd = tt;
		
		System.out.println("cc = " + cc + ", dd = " + dd);
	}
} 
 */

/*
//ex7) 변수 설정, 출력 응용
public class Hello {
	public static void main(String[] args) {
		
		int a,b,c,d; //컴퓨터야 정수형 숫자 저장할 4byte 내놔라 이름은 a로 짓고.
		a = 1;
		b = 2;
		c = 3;
		d = 4;
		
		System.out.println(a + "-" + b + "-" + c + "-" + d);
	
		int e = 10;
		int g = 20, h,j;
		h = 30;
		j = 40;
		System.out.println(e + "-" + g + "-" + h + "-" + j);
		
		int a10;
		a10 = 10;
		System.out.println(a10);
		a10 = 20;
		System.out.println(a10);
	}
}
*/
/*
//ex6) 변수 타입
public class Hello {
	public static void main(String[] args) {
		byte apple; //1byte 메모리 할당해라 이름은 apple로 지었다
		apple = 100;
		apple = -128;
//		apple = 128; -128 <= byte <= 127
		System.out.println(apple);
		
		System.out.println(Byte.MAX_VALUE);
		System.out.println(Byte.MIN_VALUE);
		
		short banana; //short -> 2bytes
		banana = 30000;
		
		System.out.println(banana);
		System.out.println(Short.MAX_VALUE);
		System.out.println(Short.MIN_VALUE);
		
		int orange;//int -> 4bytes
		orange = 2000000000;
		
		System.out.println(orange);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		Long melon; //Long -> 8bytes
		melon = 2000000000000L;
		
		System.out.println(melon);
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
		
		float tiger; //float -> 4bytes
		tiger = 3.12f;
		
		System.out.println(tiger);
		
		double lion; // double -> 8bytes
		lion = 3.141592;
		
		System.out.println(lion);
		
		char cat; //char -> 4bytes(양수)
		cat = '한';
//		cat = 1234; 사용안함
		
		System.out.println(cat);
//		System.out.println((int)Character.MAX_VALUE);
//		System.out.println((int)Character.MIN_VALUE);
		
		boolean dog; //boolean -> 1byte
		dog = true;
		
		boolean pig;
		pig = false;
		
		System.out.println(dog);
		System.out.println(pig);
	}
}
*/

/*
//ex5)
public class Hello {
	public static void main(String[] args) {
		System.out.println(10);//숫자를 출력한다
		System.out.println('한');//문자 출력
		System.out.println("호랑이");//문자열 출력
		System.out.println("1000");
		
		System.out.println(10+20); //숫자 + 숫자
		System.out.println(10 + "호랑이"); //숫자 + 문자열(문자열 연결 -> 문자열)
		System.out.println("호랑이" + 10); //문자열 + 숫자(문자열 연결 -> 문자열)
		System.out.println("호랑이"+"코끼리"); // 문자열 + 문자열(문자열)
		
		System.out.println(10+"호랑이"+20); //(숫자 + 문자열) -> 문자열 + 숫자
		System.out.println(10+20+"호랑이"); //(숫자 + 숫자 -> 숫자 + 문자열
		System.out.println("호랑이" + 10 + 20); //(문자열 + 숫자) -> 문자열 + 숫자
		
		System.out.println(10 + " " + 20); //숫자 2개 한 라인에 출력
	}
}
*/
/*
//ex4) 논리 연산
public class Hello {
	public static void main(String[] args) {
		
		System.out.println(false || false);
		System.out.println(true || false);
		System.out.println(false || true);
		System.out.println(true || true);

		System.out.println(false&& false);
		System.out.println(true &&false);
		System.out.println(false&& true);
		System.out.println(true &&true);
		
		System.out.println(false||false ||true);
		System.out.println(true &&true&&false);
		
		
//		System.out.println(false || false && true);
		System.out.println(false || (false && true)); //섞여서 쓸때는 괄호 해줘라
		
		System.out.println(3>2 || false || 6<=3);
		
		System.out.println(!true);
		System.out.println(!(3>2));
	}
}
*/

/*
//ex3) 관계 연산(비교 연산)
public class Hello {
	public static void main(String[] args) {
		
		System.out.println(5>1);
		System.out.println(5>=6);
		System.out.println(5<6);
		System.out.println(5<=6);
		System.out.println(5==6);
		System.out.println(5!=6);
		
		System.out.println("a"!="a");
		System.out.println("a"!="b");
	}
}
*/

/*
//ex2) 산술 연산자
public class Hello {
	public static void main(String[] args) {
		System.out.println(20+3);
		System.out.println(20-3);
		System.out.println(20*3);
		System.out.println(20/3);
		System.out.println(20%3);//나머지 구함
		System.out.println(3+2*4);//연산자 우선순위
		System.out.println((3+2)*4);//연산자 우선순위 조정
	}
}
*/
/*
//ex1) 문자열 출력
public class Hello {
	public static void main(String[] args) {
		System.out.println("Hello Java1");
	}
}
*/ 


