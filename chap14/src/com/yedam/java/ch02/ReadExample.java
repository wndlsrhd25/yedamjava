package com.yedam.java.ch02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ReadExample {

	public static void main(String[] args) throws Exception {
		
		Reader reader = new FileReader("d:/dv/temp/test7.txt");
		
		while(true) {
			int data = reader.read();
			if(data ==-1) break;
			System.out.println((char)data); //문자타입으로 변환해야함 원래는 byte타입을 읽거든
		}

		reader.close();
		System.out.println();
		

		reader = new FileReader("d:/dv/temp/test1.db");
		
		while(true) {
			int data = reader.read();
			if(data ==-1) break;
			System.out.println(data);
		}
		
		System.out.println();
		
		 reader = new FileReader("d:/dv/temp/test8.txt");
		 
		 char[] buffer = new char[100]; //한번에 2글자 밖에 못읽어들여서 AB 출력하고 그다음에 C를 출력함 
		 
		 while(true) {
			 int readCharNum = reader.read(buffer);
			 if(readCharNum == -1) break;
			 for(int i=0;i<readCharNum; i++) {
				 System.out.println(buffer[i]);
			 }
			 System.out.println(); 
		 }
		 reader.close();
		 
		 reader = new FileReader("d:/dv/temp/test10.txt");
		 
		 /*
		  * buffer이라는 배열이 있는데 원래는 다 공백임 위에서 그 안에 ABC를 넣었음 
		  * 근데 호출할떄 배열 5에서 부터 10글자를 보여달라고 하니까
		  * 앞에 ABC(공백)(공백)ABCDE이고 
		  * test10에서 만들때 str1에 "ABCDE\n" 줄바꿈때문에 de가 밑에 출력되는것
		  */
		 int readCharNum = reader.read(buffer,5,10);
		 for(int i=0; i< (5+readCharNum); i++) {
			 System.out.print(buffer[i]);
		 }
		
	}

}
