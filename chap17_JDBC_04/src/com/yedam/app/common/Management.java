package com.yedam.app.common;

import java.util.Scanner;

import com.yedam.app.deal.ReceivingGoodsDAO;
import com.yedam.app.product.ProductDAO;
import com.yedam.app.product.ProductInfoManagement;

public class Management {
	
	//필드
	protected Scanner sc = new Scanner(System.in);
	protected ProductDAO pDAO = ProductDAO.getInstance();
	protected ReceivingGoodsDAO rDAO = ReceivingGoodsDAO.getInstance();
	protected TakeOutGoodsDAO tDAO = TakeOutGoodsDAO.getInstance();
	
	//생성자 --상속클래스가 있을경우 기존 생성자를 사용하지 말고 run 메소드를 사용해라
	public void run() { 
		while(true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo ==1) {
				//제품정보 관리
				new ProductInfoManagement();
			}else if(menuNo ==2) {
				//제품관리
				new ProductStockManagement();
			}else if(menuNo ==9) {
				//프로그램 종료
				exit();
				break;
			}else {
				//입력오류
				showInputError();
			}
		}
	}
	
	//메소드
	protected void menuPrint() {
		System.out.println("===========================");
		System.out.println("1.제품정보관리 2.재품재고관리 9.종료");
		System.out.println("===========================");
	}
	
	protected int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
			
		}catch(NumberFormatException e){
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}
	
	protected void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	protected void showInputError () {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
	
	
}
