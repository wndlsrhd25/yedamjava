package com.yedam.java.question;

public class GreenCard implements Payment {

	//필드
	private String name; //카드명
	private String grade; //등급
	private int point; //적립포인트
	private double pointRatio; //적립율
		
	
	//생성자
	
	public GreenCard() {
		this.name = "GreenCard";
		this.grade = "GOLD";
		this.pointRatio = 0.05;
	}
	
	//메소드
	@Override
	public int offline(int price) {
		
		point = (int) (price * pointRatio);
		price = (int) (price * (1-0.01));
		
		return price;
	}

	@Override
	public int online(int price) {
		point = (int) (price * pointRatio);
		price = (int) (price * (1-0.03));
		
		return price;
	}

	@Override
	public int simple(int price) {
		point = (int) (price * pointRatio);
		price = (int) (price * (1-0.05));
		
		return price;
	}

	@Override
	public void showCardInfo() {
		System.out.println("카드명 : "+ name);
		System.out.println("적용 등급 : "+ grade);
		System.out.println("포인트 적립율 : " + pointRatio);
		System.out.println("적립 포인트 : "+ point );
		
	}

}
