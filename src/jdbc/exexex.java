package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class exexex {
	private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel employee = new JLabel("������");
    private JLabel customer = new JLabel("����");
    private JLabel gender = new JLabel("����");
    private JLabel address = new JLabel("�ּ�");
	private JLabel call_Number = new JLabel("����ó");
	private JTextField emp_Input = new JTextField();
	private JTextField cust_Input = new JTextField();
	private JTextField call_Input = new JTextField();
	private JComboBox<String> gender_box = new JComboBox<String>();
	private JComboBox<String> address_box = new JComboBox<String>();
	private JButton enroll_emp = new JButton("�������");
	private JButton enroll_cust = new JButton("���Խ�û");
	private JButton cancel = new JButton("���");
	
	public JFrame join_emp(){
		panel.setLayout(null);
		frame.setTitle("ȸ������");
   		frame.setSize(400, 400);
   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		employee.setBounds(70, 50, 100, 30);
   		emp_Input.setBounds(170,50,130,30);
   		gender.setBounds(70, 100, 80, 30);
   		gender_box.setBounds(170, 100, 130, 30);
   		address.setBounds(70, 150, 80, 30);
   		address_box.setBounds(170, 150, 130, 30);
   		call_Number.setBounds(70, 200, 60, 30);
    	call_Input.setBounds(170,200,130,30);
    	enroll_emp.setBounds(70, 260, 100, 35);
    	cancel.setBounds(210, 260, 100, 35);
   		gender_box.addItem("��"); gender_box.addItem("��");
   		address_box.addItem("����"); address_box.addItem("���"); address_box.addItem("���");
   		address_box.addItem("�泲"); address_box.addItem("����"); address_box.addItem("���");
    	address_box.addItem("�泲"); address_box.addItem("����"); address_box.addItem("����");
   		address_box.addItem("����");
   		panel.add(emp_Input); panel.add(call_Input); panel.add(employee); panel.add(call_Number);
   		panel.add(gender); panel.add(address); panel.add(gender_box); panel.add(address_box);
   		panel.add(enroll_emp); panel.add(cancel); frame.add(panel);
   		return frame;
   	}
	public exexex(){
		panel.setLayout(null);
		frame.setTitle("ȸ������");
   		frame.setSize(400, 400);
   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		customer.setBounds(70, 50, 100, 30);
   		cust_Input.setBounds(170,50,130,30);
   		gender.setBounds(70, 100, 80, 30);
   		gender_box.setBounds(170, 100, 130, 30);
   		address.setBounds(70, 150, 80, 30);
   		address_box.setBounds(170, 150, 130, 30);
   		call_Number.setBounds(70, 200, 60, 30);
    	call_Input.setBounds(170,200,130,30);
    	enroll_cust.setBounds(70, 260, 100, 35);
    	cancel.setBounds(210, 260, 100, 35);
   		panel.add(cust_Input); panel.add(call_Input); panel.add(customer); panel.add(call_Number);
   		panel.add(gender); panel.add(address); panel.add(gender_box); panel.add(address_box);
   		panel.add(enroll_cust); panel.add(cancel); frame.add(panel);
   		frame.setVisible(true);
   	}
	public static void main(String[] args) {
		//new join_emp();
		//new join_cust();
		
		new exexex();
	}
}