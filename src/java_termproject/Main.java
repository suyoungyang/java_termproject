package java_termproject;

import java.text.*;
import java.util.*;
/*
 * 작성자 : 양수영
 * 작성 날짜 : 2019년05월01일
 * 파일명 : Main.java
 * 버젼 : ver.1.0
 * 설명 : PosSystem의 기초인 주문받기와 취소, 리스트 출력, 재고관리 및 시제정산하는 프로그램
 * 
 * 수정날짜 : 2019년05월08일
 * 수정자 : 양수영
 * 버젼 : ver.1.1
 */
public class Main {
	static double tax=0.1;//부가세

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		Product[] list=new Product[20]; //모든 주문을 받을 list
		Order order=new Order(); //Order클래스 불러오기
		List<Object[][]> arr=new LinkedList<Object[][]>();
		DecimalFormat allsum=new DecimalFormat("#,###");
		arr.add(null);
		
		double pre=100000;
		int count=0;//arr<Object[][]>의 Count
		//예외처리
		try {
		//상품정보입력
		for(int i=0;i<list.length;i++) {
			list[i]=new Product();
		}
		list[0].ProductM("sunchip", 1, 1500, false, 20);
		list[1].ProductM("pocach", 2, 1200, false, 20);
		list[2].ProductM("homrun", 3, 2000, true, 20);
		list[3].ProductM("oreo", 4, 1800, false, 20);
		list[4].ProductM("jjanggu", 5, 1200, false, 20);
		list[5].ProductM("bbaro", 6, 1000, true, 20);
		list[6].ProductM("ogamja", 7, 1000, true, 20);
		list[7].ProductM("yogurt",8, 2500, false, 20);
		list[8].ProductM("oyes", 9, 2800, false, 20);
		list[9].ProductM("wehas", 10, 700, false, 20);
		
		list[10].ProductM("poca", 11, 1200, false, 20);
		list[11].ProductM("coke", 12, 1200, false, 20);
		list[12].ProductM("cider", 13, 1000, false, 20);
		list[13].ProductM("pepsi", 14, 1000, false, 20);
		list[14].ProductM("lemon", 15, 1800, true, 20);
		list[15].ProductM("plain", 16, 1800, true, 20);
		list[16].ProductM("hotsix", 17, 1500, false, 20);
		list[17].ProductM("milkiss", 18, 1200, false, 20);
		list[18].ProductM("peers", 19, 1800, false, 20);
		list[19].ProductM("2pro", 20, 1800, true, 20);
		
		//출력및 입력
		while(true) {
			Object[][] or=new Object[10][9]; 
			order.p_menu();
			int i=input.nextInt();
			if(!(i>0&&i<8)) {
				System.out.println("\"잘못 입력하셨습니다.\"");
				System.out.println("\"새로 시작하세요.\"");
				System.exit(1);
			}
			
			//주문입력받는 배열
			String tmp; 
			switch(i) {
			
			case 1: //주문
				boolean check=false;
				int OrderCount=0;//or[][]배열의 Count
				
				for(int t=0;t<or.length;) {
				//주문받기
				for( OrderCount=0;OrderCount<or.length;) {
					check=false;
					System.out.print("상품명 : ");
					String name=input.next();
					int k=0;
					
					for(k=0;k<list.length;k++) {
						if(list[k].Pname.toString().equals(name)) {
							or[OrderCount][0]=name;
							check=true;
							OrderCount++;
						}else {
							continue;
						}
					}if(check==false) {
						System.out.println("\"상품이 없습니다.\"");
						System.out.println("\"다시 주문 해주세요.\"");
						System.out.println();
						break;
					}
					System.out.print("수  량 : ");
					int num=input.nextInt();
					for(k=0;k<list.length;k++) {
						if(list[k].Pname.toString().equals(name)) {
							if(num<0||num>list[k].Plest) {
								if(num>list[k].Plest) {
									System.out.println("\"재고가 부족합니다.  "+list[k].Pname+" 재고량 : "+list[k].Plest+" ea\"");
									System.out.println("\"현재 주문은 반영되지 않습니다.  'c'버튼을 눌러 계속해주세요.\"");
									or[OrderCount-1][1]=null;
									or[OrderCount-1][0]=null;
									System.out.println();
									OrderCount--;
									break;
								}
								break;
							}else {
								or[OrderCount-1][1]=num;
							}
						}else {
							continue;
						}
					}
					System.out.println("\"주문이 완료 되면 \"q\"버튼, 계속하시려면 \"c\"버튼을 누르세요.\"");
					tmp=input.next();
					System.out.println();
					 if("q".equals(tmp)||"Q".equals(tmp)) {
						Date date=new Date();
						SimpleDateFormat time=new SimpleDateFormat("a hh:mm:ss");
						for(i=0;i<=OrderCount;i++) {
							or[i][6]=time.format(date);
						}
						
						//멤버십사용여부확인
						System.out.println("\"멤버십 할인카드를 확인해 주세요. (Y/N)\"");
						tmp=input.next();
						if("Y".equals(tmp)||"y".equals(tmp)) {
							for(i=0;or[i][1]!=null;i++) {
								or[i][2]=true;//i<or.length
							}
						}else if("N".equals(tmp)||"n".equals(tmp)) {
							for(i=0;or[i][1]!=null;i++) {
								or[i][2]=false;//i<or.length
							}
						}else	System.out.println("잘못 입력하셨습니다.");
					
						//계산
						System.out.println(">>>>>>>"+or[OrderCount][6]);
						System.out.println("제품번호\t제품명\t수량\t가격\t프로모션");
						for(k=0;k<or.length;k++) {
							for(int j=0;j<list.length;j++) {
								if(or[k][0]==null) {
								break;
								}else {
								if(or[k][0].equals(list[j].Pname)) {
									or[k][3]=list[j].Pprice;
									or[k][4]=list[j].Ppromotion;
									double sum=order.sumM((int)or[k][1], list[j].Pprice, list[j].Ppromotion, (boolean)or[k][2]);
									or[k][5]=sum;
									//계산출력
									System.out.println(list[j].Pnum+"\t"+list[j].Pname+"\t"+or[k][1]+"\t"+list[j].Pprice+"\t"+list[j].Ppromotion);	
									System.out.println("\t\t\t합 계 : "+allsum.format(or[k][5])+"원");
									System.out.println();
									continue;
									}
								}
							}
						}
						
						//재고적용
						for(k=0;k<or.length;k++) {
							for(int j=0;j<list.length;j++) {
								if(list[j].Pname.toString().equals(or[k][0])) {
									list[j].Plest=list[j].Plest-(int)or[k][1];
								}
							}
						}
						
						//현재 주문의 합계 출력
						double ReOrder=0;
						int ReNum=0;
						for(i=0;or[i][0]!=null;) {
								if(or[1][0]!=null) {
									for(int j=0;or[j][0]!=null;j++) {
										ReOrder+=(double)or[j][5];
										ReNum+=(int)or[j][1];
									}
									for(int j=0;or[j][0]!=null;j++) {
										or[j][7]=ReOrder;
										or[j][8]=ReNum;
									}
									i++;
								}else {
									ReOrder+=(double)or[i][5];
									ReNum+=(int)or[i][1];
									or[i][7]=ReOrder;
									or[i][8]=ReNum;
								}
						System.out.println("\t\t수량합계\t\t금액합계");
						System.out.println("\t\t"+or[0][8]+"\t\t"+allsum.format(or[0][7]));
						arr.add(count,or);											
						count++;
						t++;
						break;
						}
						
						break;
					}else if("c".equals(tmp)||"C".equals(tmp)) {
						continue;
					}else {
						System.out.println("\"잘못 입력하셨습니다.\"");
						System.out.println();
						OrderCount--; 
						continue;
					}
				}
					
					break;
				}
				break;
				
			case 2: //주문리스트
				System.out.println("주문번호\t주문시간\t\t수량\t프로모션\t멤버십\t합계");
				if(arr.get(0)==null) { System.out.println();continue;} //오더가 없을 경우 출력
				else {
					for(i=0;arr.get(i)!=null;i++) {
						for(int j=0;arr.get(i)[j][0]!=null;) {
							System.out.println((i+1)+"\t"+(arr.get(i)[j][6]+"\t"+arr.get(i)[j][8]+"\t"+arr.get(i)[j][4]+"\t"+arr.get(i)[j][2]+"\t"+allsum.format((double)arr.get(i)[j][7])));
							break;
						}
					}
				}
				break;
				
			case 3: //주문취소
				if(arr.get(0)==null) {System.out.println("\"아직 주문한 내역이 없습니다.\"");break;}
				else {
					System.out.println("주문번호\t주문시간\t\t수량\t프로모션\t멤버십\t합계");
					for(i=0;arr.get(i)!=null;i++) {
						for(int j=0;arr.get(i)[j][0]!=null;) {
							System.out.println((i+1)+"\t"+(arr.get(i)[j][6]+"\t"+arr.get(i)[j][8]+"\t"+arr.get(i)[j][4]+"\t"+arr.get(i)[j][2]+"\t"+allsum.format((double)arr.get(i)[j][7])));
							break;
						}
					}
					System.out.println("\"취소할 주문번호를 입력해주세요.\"");
					i=input.nextInt();
					
					//취소한 재고량 복구 시킴
					for(int j=0;j<list.length;j++) {
						for(int k=0;arr.get(i-1)[k][0]!=null;k++) {
							if(list[j].Pname.toString().equals(arr.get(i-1)[k][0].toString())) {
								list[j].Plest=list[j].Plest+(int)arr.get(i-1)[k][1];
							}
						}
					}
					arr.remove(i-1);
					System.out.println("\""+i+"번의 주문을 정상취소 하였습니다.\"");
					count--;
					System.out.println("주문번호\t주문시간\t\t수량\t프로모션\t멤버십\t합계");
					for(i=0;arr.get(i)!=null;i++) {
						for(int j=0;arr.get(i)[j][0]!=null;) {
							System.out.println((i+1)+"\t"+(arr.get(i)[j][6]+"\t"+arr.get(i)[j][8]+"\t"+arr.get(i)[j][4]+"\t"+arr.get(i)[j][2]+"\t"+allsum.format((double)arr.get(i)[j][7])));
							break;
						}
					}
				}break;
				
			case 4: //상품찾기
				System.out.println("\"찾고자 하는 상품명을 입력해주세요.\"");
				String searchN=input.next();
				check=false;
				
				for(i=0;i<list.length;i++) {
					if(list[i].Pname.toString().contains(searchN)) {
						System.out.println("상품명\t\t상품번호\t가격\t프로모션\t재고수량");
						System.out.println(list[i].Pname+"\t\t"+list[i].Pnum+"\t"+list[i].Pprice+"\t"+list[i].Ppromotion+"\t"+list[i].Plest);
						if(list[i].Plest==0) {
							System.out.println("\""+list[i].Pname+"의 재고가 없습니다.\"");
						}
						System.out.println();
						check=true;
					}
				}if(check==false) {
					System.out.println("\"해당하는 상품이 없습니다.\"");
					System.out.println();
				}
				break;
				
			case 5: //재고리스트
				System.out.println("상품명\t\t상품번호\t가격\t프로모션\t재고수량");
				for(i=0;i<list.length;i++) {
						System.out.println(list[i].Pname+"\t\t"+list[i].Pnum+"\t"+list[i].Pprice+"\t"+list[i].Ppromotion+"\t"+list[i].Plest);
				}
				System.out.println();
				break;
				
			case 6: //정산
				double tot=0;
				int totnum=0;
				if(arr.get(0)==null) {System.out.println("\"아직 주문한 내역이 없습니다.\"");break;}
				else{
					System.out.println("주문번호\t주문시간\t\t수량\t프로모션\t멤버십\t합계");
					for(i=0;arr.get(i)!=null;i++) {
						System.out.println((i+1)+"\t"+(arr.get(i)[0][6]+"\t"+arr.get(i)[0][8]+"\t"+arr.get(i)[0][4]+"\t"+arr.get(i)[0][2]+"\t"+allsum.format(arr.get(i)[0][7])));
						}
					for(int j=0;arr.get(j)!=null;j++) {
						tot+=(double)arr.get(j)[0][7];
						totnum+=(int)arr.get(j)[0][8];
						}
					System.out.println("\t\t\t수량합계\t\t\t금액합계");
					System.out.println("\t\t\t"+totnum+"\t\t\t"+allsum.format(tot));
					System.out.println();
					System.out.println("\t준비금\t\t"+allsum.format(pre)+"원");
					System.out.println("\t매출금\t\t"+allsum.format(tot)+"원");
					System.out.println("\t부가세(10%)\t"+allsum.format(tot*tax)+"원");
					System.out.println("\t영업이익\t\t"+allsum.format(pre+tot-(tot*tax))+"원");
					System.out.println("\t총금액\t\t"+allsum.format(pre+tot)+"원");
					System.out.println();
					break;
				}
				
			case 7: //종료
				System.out.println("\"영업이 완료 되었습니다. 수고하셨습니다.\"");
				System.exit(1);
				input.close();
			}
	}	
		}catch(InputMismatchException e) {
			System.out.println("\"잘못 입력하셨습니다.\"");
			System.exit(1);
		}catch(IndexOutOfBoundsException e) {
			System.out.println("\"잘못 입력하셨습니다.\"");
			System.exit(1);
		}
	}
}
