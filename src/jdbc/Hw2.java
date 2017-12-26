package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hw2 {
	private String username="mjskks94@naver.com";
	private String password="Myh941021";
	private static Connection dbTest;
	
	Hw2(){
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
	
	public void execute_query1() throws SQLException{	
		String sqlStr1 = "select maker, model, price from product natural join pc where cd = '8x' and ram >= 24";
		PreparedStatement stmt1 = dbTest.prepareStatement(sqlStr1);
		ResultSet rs1 = stmt1.executeQuery();
		System.out.println("MAKER       MODEL    PRICE");
		System.out.println("=====          =====      =====");
		while(rs1.next()){
			System.out.println(rs1.getString("MAKER")+" "+rs1.getInt("MODEL")+"          "+rs1.getInt("PRICE"));
		}
		stmt1.close();
		rs1.close();
	}
	public void execute_query2() throws SQLException{	
		String sqlStr2 = "select sum(price) from laptop natural join product where screen > 11 and (maker = 'D' or maker = 'G')";
		PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
		ResultSet rs2 = stmt2.executeQuery();
		System.out.println(" ");
		System.out.println("SUM(PRICE)");
		System.out.println("==========");
		while(rs2.next()){
			System.out.println(rs2.getInt("SUM(PRICE)"));
		}
		stmt2.close();
		rs2.close();
	}
	public void execute_query3() throws SQLException{	
		String sqlStr3 = "select count(model) from ((select model from pc where hd > 2.4) union (select model from laptop where speed > 130))";
		PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
		ResultSet rs3 = stmt3.executeQuery();
		System.out.println(" ");
		System.out.println("COUNT(MODEL)");
		System.out.println("=============");
		while(rs3.next()){
			System.out.println(rs3.getInt("COUNT(MODEL)"));
		}
		stmt3.close();
		rs3.close();
	}
	public void execute_query4() throws SQLException{	
		String sqlStr4 = "select model, price from pc where cd = '8x' and speed > some (select speed from laptop)";
		PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
		ResultSet rs4 = stmt4.executeQuery();
		System.out.println(" ");
		System.out.println("MODEL   PRICE");
		System.out.println("======  ======");
		while(rs4.next()){
			System.out.println(rs4.getInt("MODEL")+"         "+rs4.getInt("PRICE"));
		}
		stmt4.close();
		rs4.close();
	}
	public void execute_query5() throws SQLException{	
		String sqlStr5 = "select maker,speed from product natural join laptop where hd >= 1";
		PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
		ResultSet rs5 = stmt5.executeQuery();
		System.out.println(" ");
		System.out.println("MAKER		   SPEED");
		System.out.println("===========  ======");
		while(rs5.next()){
			System.out.println(rs5.getString("MAKER")+"         "+rs5.getInt("SPEED"));
		}
		stmt5.close();
		rs5.close();
	}
	public void execute_query6() throws SQLException{	
		String sqlStr6 = "select model from ((select model from laptop where speed > (select speed from laptop where model = '2005')) "
				+ "union (select model from pc where speed > (select speed from laptop where model = '2005')))";
		PreparedStatement stmt6 = dbTest.prepareStatement(sqlStr6);
		ResultSet rs6 = stmt6.executeQuery();
		System.out.println(" ");
		System.out.println("MODEL");
		System.out.println("=======");
		while(rs6.next()){
			System.out.println(rs6.getInt("MODEL"));
		}
		stmt6.close();
		rs6.close();
	}
	public void execute_query7() throws SQLException{	
		String sqlStr7 = "select r.model, price from printer r, product p where r.model = p.model and p.maker = 'D' and color = 'true'";
		PreparedStatement stmt7 = dbTest.prepareStatement(sqlStr7);
		ResultSet rs7 = stmt7.executeQuery();
		System.out.println(" ");
		System.out.println("MODEL  PRICE");
		System.out.println("====== ======");
		while(rs7.next()){
			System.out.println(rs7.getInt("MODEL")+"       "+rs7.getInt("PRICE"));
		}
		stmt7.close();
		rs7.close();
	}
	public static void main(String[] args) {
		Hw2 t1 = new Hw2();
		try{
		t1.execute_query1();
		t1.execute_query2();
		t1.execute_query3();
		t1.execute_query4();
		t1.execute_query5();
		t1.execute_query6();
		t1.execute_query7();
		dbTest.close();
		}catch(SQLException e) {
		e.printStackTrace();
		System.out.println("SQLException:"+e);
		}
	}
}