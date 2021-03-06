package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WhileTest1 {

	public static void main(String[] args) throws FileNotFoundException{
	
		//while1();
		//for1();
		//for2();
		//gugudan1();
		//gugu();
		//findMax(); //최댓값
		//findMin(); //최솟값
		//findMin2(); //최솟값2
		findMinMax();
	}
	
	//1부터 10까지 반복
	public static void while1() {
		int count = 1;
		do {
			System.out.println(count);
			count++;
		} while(count<=10);
		}

	//1부터 10까지 반복 : for
	public static void for1() {
		for (int i=1; i<=10; i=i+2) {
			System.out.println(i + "반복");
		}
	}	

	//10부터 1까지 반복 : for
	public static void for2() {
		for (int i =10; i>=1 ; i--) {
			System.out.println(i + "반복");
		}
	}

	// 구구단 5단 출력 : for
	public static void gugudan1() {
		Scanner scan = new Scanner(System.in);
		int dan = scan.nextInt();
		
		for (int i =1; i<=9; i++ ) {
			System.out.printf("%d * %d = %d\n",dan,i,dan*i);
		}
	}

	// 구구 출력 
	public static void gugu() {
		for (int i = 1; i<=9; i++) {
			for (int j =1; j<=9; j++) {
				System.out.printf("%d * %d = %d\n",i,j,i*j);
			}
			System.out.println();
		}
	}

	// 최댓값
	public static void findMax() throws FileNotFoundException {
		// 10번을 반복 -for
		// 숫자를 입력 -scanner
		// 입력 수가 60보다 크면 출력 -if
		
		Scanner scan = new Scanner(new File("score.txt")); //파일 입력값을 자동으로 가져오는것
		int max = 0; //최대값을 구하려면 처음은 0으로 지정해줘야함 
		
		for (int i=0; i<10; i++) {
			int num = scan.nextInt(); // 숫자를 출력
			if (num > max) {
				max=num;
			}
		}
		System.out.println("최댓값=" + max);
	}

	// 최솟값
	public static void findMin() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("score.txt"));
		int min = 100; //최솟값은 가장큰수로 100점
		
		for (int i=0; i<10; i++) { //10번 반복 하니까 i를 10으로 잡은거임
			int num = scan.nextInt();
			if(num < min) {
				min = num;
			}
		}
		System.out.println("최솟값=" + min);
	}
	
	// 최솟값
		public static void findMin2() throws FileNotFoundException {
			Scanner scan = new Scanner(new File("score.txt"));
			int min = scan.nextInt(); // 최대값이 100인지 모를때 처음 수를 입력할거임
			
			for (int i=0; i<9; i++) { //10번 반복 하니까 i를 9로 잡은거거 첫번째 수는 위에서 입력했으니까
				int num = scan.nextInt();
				if(num < min) {
					min = num;
				}
			}
			System.out.println("최솟값=" + min);
		}

	// 최대,최소값	
		public static void findMinMax() throws FileNotFoundException {
			Scanner scan = new Scanner(new File("score.txt"));
			int min = scan.nextInt();
			int max = min; //min에서 불러온걸 가져오면 됨
			
			for (int i=0; i<9; i++) {
				int num = scan.nextInt();
				if(num > max ) {
					max = num;
				}
				if (num < min) {
					min = num;
				}
			}
			System.out.println("최솟값=" + min);
			System.out.println("최댓값=" + max);
}
}
