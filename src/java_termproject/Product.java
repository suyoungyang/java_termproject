package java_termproject;
/*
 * 작성자 : 양수영
 * 작성 날짜 : 2019년05월01일
 * 파일명 : Product.java
 * 버젼 : ver.1.0
 * 설명 : 상품을 클래스화
 * 
 * 수정날짜 : 2019년05월08일
 * 수정자 : 양수영
 * 버젼 : ver.1.1
 */

public class Product {
	public String Pname;
	public int Pnum;
	public int Pprice;
	public boolean Ppromotion;
	public int Plest;
	
	public void ProductM(String Pname,int Pnum,int Pprice,boolean Ppromotion,
			int Plest) {
		this.Pname=Pname;
		this.Pnum=Pnum;
		this.Pprice=Pprice;
		this.Ppromotion=Ppromotion;
		this.Plest=Plest;
	}
}