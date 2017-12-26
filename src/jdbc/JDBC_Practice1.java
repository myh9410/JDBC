package jdbc;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.*;

public class JDBC_Practice1 implements ActionListener{
    private static Connection dbTest;
    private String username;
    private String password;
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();

    private JLabel idLabel = new JLabel("아이디");
    private JLabel pwdLabel = new JLabel("비밀번호");
    private JTextField idInput = new JTextField();
    private JPasswordField pwdInput = new JPasswordField();
    private JButton loginButton = new JButton("로그인");
    private JTextArea check_area = new JTextArea();
    private JComboBox<String> check_box = new JComboBox<String>();
    private JComboBox<String> buy_box = new JComboBox<String>();
    private JTextField model_field = new JTextField();
    private JButton buyButton = new JButton("구매");

    public JDBC_Practice1(){
        panel.setLayout(null);

        idLabel.setBounds(20, 10, 60, 30);
        pwdLabel.setBounds(20, 50, 60, 30);
        idInput.setBounds(100, 10, 80, 30);
        pwdInput.setBounds(100, 50, 80, 30);
        loginButton.setBounds(200, 25, 80, 35);
        loginButton.addActionListener(this);

        panel.add(idLabel);
        panel.add(pwdLabel);
        panel.add(idInput);
        panel.add(pwdInput);
        panel.add(loginButton);

        frame.add(panel);

        frame.setTitle("JDBC Practice 1");
        frame.setSize(320, 130);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //PCstore GUI 및 Event처리
    private void PCstore(){
        frame.setVisible(false);
        frame = new JFrame();
        panel = new JPanel();

        panel.setFont(new Font("필기체", 1, 12));
        panel.setBorder(new TitledBorder("조회"));
        panel.setBounds(380,80,490,280);
        panel.setLayout(null);

        check_box.addItem("PC");
        check_box.addItem("Laptop");
        check_box.addItem("Printer");

        for(int i=1;i<=10;i++) buy_box.addItem(String.valueOf(i));

        check_area.setBorder(new LineBorder(Color.gray, 2));
        check_area.setEditable(false);

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(check_area);

        check_box.setBounds(20,40,70,30);
        model_field.setBounds(100,40,80,30);
        buy_box.setBounds(200,40,50,30);
        buyButton.setBounds(300,40,70,30);
        scroll.setBounds(10,80,360,170);

        //Checkbox로 테이블명 선택 시 테이블 값 출력
        check_box.addActionListener(this);

        //구매버튼 클릭 시 구매작업 수행
        buyButton.addActionListener(this);

        panel.add(check_box);
        panel.add(model_field);
        panel.add(buy_box);
        panel.add(buyButton);
        panel.add(scroll);

        frame.add(panel);

        frame.setTitle("PC Store");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == loginButton){
            username = idInput.getText();
            password = new String(pwdInput.getPassword());

            connectDB();
            PCstore();
        }
        //Checkbox로 테이블명 선택 시 테이블 값 출력
        else if(e.getSource() == check_box){
            try{
                showTable();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }
        //구매버튼 클릭 시 구매작업 수행
        else if(e.getSource() == buyButton){
            try{
                insertTransaction();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    private void insertTransaction() throws SQLException{
        String specification = "";
        String modelname = model_field.getText();
        String count = (String)buy_box.getSelectedItem();
        String Tablename = ((String) check_box.getSelectedItem()).toUpperCase();
        int price=0;
        
        try{
        	JOptionPane.showMessageDialog(null, (String)"모델 "+modelname+" 을/를 "+count+" 개 구매했습니다.");
        	String sqlStr = "select price from "+Tablename+" where model = "+modelname;
    		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			price = rs.getInt("price");
    		}
    		sqlStr = "insert into transaction values ( TNUMBER.nextval , " + modelname + " ," + count + " ," + price + ")";
    		stmt = dbTest.prepareStatement(sqlStr);
    		stmt.executeQuery();
    		rs.close();
    		stmt.close();
            /*
                fill out this area

            */
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, (String)"구매를 할 수 없습니다","메시지",2);
        }


    }

    private void showTable() throws SQLException{
        String specification = "";

        String sqlStr = "select count(column_name) num from cols where table_name = '"
                + ((String) check_box.getSelectedItem()).toUpperCase() +"'";
        PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
        ResultSet rs = stmt.executeQuery();

        rs.next();
        int number = rs.getInt("num");
        String[] tables = new String[number];

        sqlStr = "select column_name from cols where table_name = '"
                + ((String)check_box.getSelectedItem()).toUpperCase()+"'";

        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for(number = 0; rs.next();number++){
            tables[number] = rs.getString("column_name");
            specification+=tables[number]+'\t';
        }
        for(specification+="\n";number>0;number--){
            specification += "--------------------";
        }
        specification += "\n";

        sqlStr = "select * from "+(String)check_box.getSelectedItem();
        stmt = dbTest.prepareStatement(sqlStr);
        rs = stmt.executeQuery();

        for(number=0;rs.next();number++){
            for(int i=0;i<tables.length;i++){
                specification+=rs.getString(tables[i])+'\t';
            }
            specification+="\n";
        }
        check_area.setText(specification);
        rs.close();
        stmt.close();
    }
    private void connectDB(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
            System.out.println("데이터베이스접속 성공 - id: "+username);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("패패");
        }
        catch(Exception e){
            System.out.println("Exception:"+e);
        }
    }

    public static void main(String[] argv) {
        new JDBC_Practice1();
    }
}