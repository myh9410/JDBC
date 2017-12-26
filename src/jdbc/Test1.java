package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test1 {
	private String username="mjskks94@naver.com";
	private String password="Myh941021";
	private static Connection dbTest;
	
	Test1(){
		connectDB();
	}
	
	private void connectDB(){
		try {
		// JDBC Driver Loading
		Class.forName("oracle.jdbc.OracleDriver");
		dbTest = DriverManager.getConnection("jdbc:oracle:thin:"+
		"@localhost:1521:XE","root","941021");
		} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("SQLException:"+e);
		} catch (Exception e) {
		System.out.println("Exception : "+e);
		}
		}
	
	public void execute_query() throws SQLException{
		String sqlStr1 = "SELECT avg(speed) from pc";
		PreparedStatement stmt1 = dbTest.prepareStatement(sqlStr1);
		ResultSet rs1 = stmt1.executeQuery();
		String sqlStr2 = "SELECT price from pc where price >= 2000";
		PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
		ResultSet rs2 = stmt2.executeQuery();
		String sqlStr3 = "SELECT model, speed, hd from pc where (cd = '8x' or cd = '6x') and price < 2000";
		PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
		ResultSet rs3 = stmt3.executeQuery();
		System.out.println("����1. pc�� ��� �ӷ��� ���϶�.");
		while(rs1.next()){
			System.out.println("avg(speed) : " + rs1.getFloat("AVG(SPEED)"));
		}
		System.out.println("����2. pc���� price�� 2000�̻��� ������ ���϶�.");
		while(rs2.next()){
			System.out.println("price : " + rs2.getInt("PRICE"));
		}
		System.out.println("����3. 6����̳� 8����� CD�� ������ ������ ������ $2000�̸��� PC���� �𵨹�ȣ, �ӵ�, �ϵ��ũ �뷮�� ���϶�.");
		while(rs3.next()){
			System.out.println("model : " + rs3.getInt("MODEL") +" | "+ "speed : " + rs3.getInt("SPEED") + " | "+"hd : " + rs3.getFloat("HD"));
		}
		rs3.close();
		stmt3.close();
		rs2.close();
		stmt2.close();
		rs1.close();
		stmt1.close();
		}
	
	public static void main(String[] args) {
		Test1 t1 = new Test1();
		try{
		t1.execute_query();
		dbTest.close();
		}catch(SQLException e) {
		e.printStackTrace();
		System.out.println("SQLException:"+e);
		}
	}
}