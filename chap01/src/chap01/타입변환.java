package chap01;
/*
 * 데이터 탕비을 다른 타입으로 변환
 * 강제형변환
 * 자동형변환
 * 
 * 
 * String <--> 기본데이터형 (int, double)
 * 
 */
public class 타입변환 {

	public static void main(String[] args) {
		//자동
		int a = 100;
		long b = a;
		
		//강제
		long c = 100;
		int d = (int)c;
		
		//자동
		double e = c;
		
		//강제
		double f = 5.2;
		long g = (long)f;
		System.out.println("g = " + g);
		
		double h = (double)10/3; //3.0으로 나눠도 가능
		System.out.println("h = " + h);
		
		System.out.println("결과 = " + (10 + 20));
		System.out.println(10 + 20 + " = 결과");

		
		//int(기본타입) -> string
		int i = 100;
		String j = String.valueOf(i);
		
		//string -> int
		String k = "100";
		short l = Short.parseShort(k);
		
		
	}

}
