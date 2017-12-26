package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {
	private String username="mjskks94@naver.com";
	private String password="Myh941021";
	private static Connection dbTest;
	
	Test2(){
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
		String sqlStr = "select table_name from user_tables";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		System.out.println("TABLE_NAME");
		System.out.println("=========");
		while(rs.next()){
		System.out.println(rs.getString("table_name"));
		}
		rs.close();
		stmt.close();
	}
	public void execute_query2() throws SQLException{
		String sqlStr2 = "select avg(speed) from pc";
		PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
		ResultSet rs2 = stmt2.executeQuery();
		System.out.println("\n");
		System.out.println("AVG(SPEED)");
		System.out.println("=========");
		while(rs2.next()){
		System.out.println(rs2.getDouble("avg(speed)"));
		}
		rs2.close();
		stmt2.close();
	}
	private void execute_query3() throws SQLException{
		String sqlStr3 = "UPDATE pc SET price = price - 100  WHERE price > 2000";
		PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
		stmt3.executeUpdate();
		stmt3.close();
		}
	private void execute_query4() throws SQLException{
		String sqlStr4 = "SELECT model, speed, hd FROM pc WHERE price < 1600";
		PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
		ResultSet rs4 = stmt4.executeQuery();
		System.out.println("MODEL SPEED HD");
		System.out.println("===== ==== ==");
		while(rs4.next()){
		System.out.println(rs4.getInt("MODEL")+"   "
		+rs4.getInt("SPEED")+"   "+rs4.getDouble("HD"));
		}
		rs4.close();
		stmt4.close();
		}
	private void execute_query5() throws SQLException{
		String sqlStr5 = "SELECT model, speed, hd FROM pc WHERE (cd='6x' or cd='8x') and (price < 2000)";
		PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
		ResultSet rs5 = stmt5.executeQuery();
		System.out.println("MODEL SPEED HD");
		System.out.println("=====   ====    ==");
		while(rs5.next()){
		System.out.println(rs5.getInt("MODEL")+"     "
		+rs5.getInt("SPEED")+"      "+rs5.getDouble("HD"));
		}
		rs5.close();
		stmt5.close();
		}
	private void execute_query6() throws SQLException{
		String sqlStr6 = "SELECT maker, speed FROM product, laptop" +
		" WHERE hd >= 1.0 and product.model=laptop.model";
		PreparedStatement stmt6 = dbTest.prepareStatement(sqlStr6);
		ResultSet rs6 = stmt6.executeQuery();
		System.out.println("MAKER SPEED");
		System.out.println("===== ==== ");
		while(rs6.next()){
		System.out.println(rs6.getString("MAKER")+" "+rs6.getInt("SPEED"));
		}
		rs6.close();
		stmt6.close();
	}
	public static void main(String[] args) {
		Test2 t1 = new Test2();
		try{
		t1.execute_query1();
		t1.execute_query2();
		t1.execute_query4();
		t1.execute_query5();
		dbTest.close();
		}catch(SQLException e) {
		e.printStackTrace();
		System.out.println("SQLException:"+e);
		}
	}
}