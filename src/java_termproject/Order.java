package java_termproject;

/*
 * 작성자 : 양수영
 * 작성 날짜 : 2019년05월01일
 * 파일명 : Order.java
 * 버젼 : ver.1.0
 * 설명 : 주문을 저장하기 위한 Order클래스
 * 
 * 수정날짜 : 2019년05월08일
 * 수정자 : 양수영
 * 버젼 : ver.1.1
 */

public class Order {
	public String product;
	public int ea;
	public boolean promotion;
	public boolean membership;
	public double sum; //계산에 쓸 합계
	
	public double sumM(int ea,int price,boolean promotion,boolean membership) {
	double promotion_per=0.9;
	double membership_per=0.7;
		if(promotion==true) {
			if(membership==true) {
				sum=ea*price*promotion_per*membership_per;
			}else {
				sum=ea*price*promotion_per;
			}
		}else {
			if(membership==true) {
				sum=ea*price*membership_per;
			}else {
				sum=ea*price;
			}
		}
	return sum;
	}

	public void p_menu() {
		System.out.println("-----------------------------------------");
		System.out.println("|\t\tMENU\t\t\t|");
		System.out.println("|\t1.주문\t\t5.재고리스트\t|");
		System.out.println("|\t2.주문리스트\t6.정산\t\t|");
		System.out.println("|\t3.취소\t\t7.종료\t\t|");
		System.out.println("|\t4.제품찾기\t\t\t\t|");
		System.out.println("-----------------------------------------");
		System.out.println("\"해당 menu의 번호를 입력하세요.\"");
	}
}