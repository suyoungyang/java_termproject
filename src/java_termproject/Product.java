package java_termproject;

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