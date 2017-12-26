package jdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.*;

//���̺��� insert�� delete�� ���ٶ�����(���� ���/����,��� ��) commit;����� ����� �Էµ˴ϴ�.
//create table ������ ����ִ� �޸����� �������� sql command line�� �Է������ �մϴ�. 
public class TermP implements ActionListener {
	public void readFile(File textile) throws SQLException {
		Scanner scanner = null;
		int indexcount = 0;
		int check = 0;
		try {
			scanner = new Scanner(textile);
			while (scanner.hasNextLine()) {
				check++;
				String test = scanner.nextLine().toString();
				try {
					if (check == 1) {
						while (indexcount < 5) {
							test = scanner.nextLine().toString();
							StringTokenizer token = new StringTokenizer(test, "\t");
							while (token.hasMoreTokens()) {
								String guestName = token.nextToken();
								String guestGender = token.nextToken();
								String guestAddress = token.nextToken();
								String guestPhone = token.nextToken();
								String sqlStr = "insert into customer values ('" + guestName + "', '" + guestGender
										+ "' ,'" + guestAddress + "' ,'" + guestPhone + "')";
								PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
								ResultSet rs = stmt.executeQuery();
								rs.close();
								stmt.close();
							}
							indexcount++;
						}
						indexcount = 0;
					} else if (check == 2) {
						while (indexcount < 5) {
							test = scanner.nextLine().toString();
							StringTokenizer token = new StringTokenizer(test, "\t");
							while (token.hasMoreTokens()) {
								String staffName = token.nextToken();
								String staffGender = token.nextToken();
								String staffAddress = token.nextToken();
								String staffPhone = token.nextToken();
								String sqlStr = "insert into staff values ('" + staffName + "', '" + staffGender
										+ "' ,'" + staffAddress + "' ,'" + staffPhone + "')";
								PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
								ResultSet rs = stmt.executeQuery();
								rs.close();
								stmt.close();
							}
							indexcount++;
						}
						indexcount = 0;
					} else if (check == 3) {
						while (indexcount < 20) {
							test = scanner.nextLine().toString();
							StringTokenizer token = new StringTokenizer(test, "\t");
							while (token.hasMoreTokens()) {
								int roomNo = Integer.parseInt(token.nextToken());
								int roomPeople = Integer.parseInt(token.nextToken());
								String roomType = token.nextToken();
								String sqlStr = "insert into room values (" + roomNo + ", " + roomPeople + " ,'"
										+ roomType + "')";
								PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
								ResultSet rs = stmt.executeQuery();
								rs.close();
								stmt.close();
							}
							indexcount++;
						}
						indexcount = 0;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "���� �б⿡ �����߽��ϴ�. check==1,2,3: �ٽ� �õ��� �ּ���");
					// e.printStackTrace();
				}
			} // while(scanner.hasNextLine())
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "���� �б⿡ �����߽��ϴ�. ����notfound: �ٽ� �õ��� �ּ���");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���� �б⿡ �����߽��ϴ�. ���ϾƷ� exception: �ٽ� �õ��� �ּ���");
		} finally {
			scanner.close();
		}
	}

	private static Connection dbTest;
	private Border loweredborder = BorderFactory.createLoweredBevelBorder();
	TitledBorder titledborder = BorderFactory.createTitledBorder(loweredborder, "title");
	private JPanel login_panel = new JPanel();
	private String username;
	private String password;
	private JLabel idLabel = new JLabel("���̵�");
	private JLabel pwdLabel = new JLabel("��й�ȣ");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("�α���");

	private JFileChooser jfc = new JFileChooser();
	private JMenuBar jmb;
	private JMenuItem menuopen;
	private JFrame frame = new JFrame();
	private JFrame login_frame = new JFrame();
	private JFrame emp_frame = new JFrame();
	private JFrame cust_frame = new JFrame();
	private JPanel reserve_panel = new JPanel();
	private JPanel res_panel = new JPanel(new GridLayout(5, 2, 30, 20));
	private JPanel current_panel = new JPanel();
	private JPanel cur_panel = new JPanel(new GridLayout(4, 5, 30, 37));
	private JPanel enroll_panel = new JPanel();
	private JLabel title = new JLabel("ȣ�� ���� �ý���", JLabel.CENTER);
	private JLabel reserve = new JLabel("��������", JLabel.CENTER);
	private JLabel todate = new JLabel();
	private JLabel res_customer = new JLabel("����");
	private JTextField res_cust_Input = new JTextField();
	private JLabel res_checkin = new JLabel("üũ��(YYYYMMDD)");
	private JTextField res_checkin_Input = new JTextField();
	private JLabel res_day = new JLabel("��");
	private JComboBox<Integer> res_day_box = new JComboBox<Integer>();
	private JLabel res_room = new JLabel("����");
	private JComboBox<String> res_room_box = new JComboBox<String>(); // db����
																		// room
																		// ��������
	private JButton res_enroll = new JButton("���� ���/����");
	private JButton res_cancel = new JButton("���� ���");

	private JLabel cur_reserve = new JLabel("���� ���� ��Ȳ", JLabel.CENTER);
	private JLabel table1[] = new JLabel[20];
	private JLabel table2[] = new JLabel[20];

	private JLabel check = new JLabel("���/��ȸ", JLabel.CENTER);
	private JTabbedPane tabPanel;
	private JComboBox<String> check_room_box = new JComboBox<String>();
	private JLabel check_guestname, check_empname, check_room;
	private JPanel searchRegi_P, guestTab, roomTab, empTab;
	private TextField check_empname_Input;
	private TextField check_guestname_Input;
	private JButton check_register, check_search, check_empRegister, check_empSearch;
	private JTextArea guest_textarea, emp_textarea, room_textarea;
	// �� ȸ������ �г�
	private JPanel cust_panel = new JPanel();
	private JLabel customer = new JLabel("����");
	private JLabel cust_gender = new JLabel("����");
	private JLabel cust_address = new JLabel("�ּ�");
	private JLabel cust_call_Number = new JLabel("����ó");
	private JTextField cust_Input = new JTextField();
	private JTextField cust_call_Input = new JTextField();
	private JComboBox<String> cust_gender_box = new JComboBox<String>();
	private JComboBox<String> cust_address_box = new JComboBox<String>();
	private JButton enroll_cust = new JButton("���Խ�û");
	private JButton cust_cancel = new JButton("���");
	// ���� ��� �г�
	private JPanel emp_panel = new JPanel();
	private JLabel employee = new JLabel("������");
	private JLabel emp_p_customer = new JLabel("����");
	private JLabel emp_p_gender = new JLabel("����");
	private JLabel emp_p_address = new JLabel("�ּ�");
	private JLabel emp_p_call_Number = new JLabel("����ó");
	private JTextField emp_p_Input = new JTextField();
	private JComboBox<String> emp_p_gender_box = new JComboBox<String>();
	private JComboBox<String> emp_p_address_box = new JComboBox<String>();
	private JButton emp_p_enroll_cust = new JButton("���Խ�û");
	private JButton emp_p_cancel = new JButton("���");
	private JTextField emp_p_call_Input = new JTextField();
	PreparedStatement stmt;
	ResultSet rs;
	// dbtable�� ����� �Լ�
	public void createTable() throws SQLException {
		String customerQuery = "CREATE TABLE customer (\n" + "c_name       varchar(10) not null,\n"
				+ "c_gender          varchar(10)  not null, \n" + "c_address     varchar(10)  not null, \n"
				+ "c_phone       number not null, \n" + "primary key(c_phone) \n" + ")";

		String managerQuery = "CREATE TABLE staff (\n" + "s_name       varchar(10) not null,\n"
				+ "s_gender          varchar(10)  not null, \n" + "s_address     varchar(10)  not null, \n"
				+ "s_phone       varchar(10) not null, \n" + "primary key(s_phone) \n" + ")";
		String roomQuery = "CREATE TABLE room (\n" + "roomno       number not null,\n"
				+ "person	number not null,\n" + "type       varchar(10) not null,\n" + "primary key(roomno) \n" + ")";
		String reservationQuery = "CREATE TABLE reservation (\n" + "id       number		not null,\n"
				+ "c_name      varchar(10) 	not null,\n" + "checkin	date		not null,\n"
				+ "checkout		date 	not null,\n" + "term		number		not null, \n"
				+ "roomno		number       not null, \n" + "s_name   varchar(10)  not null, \n"
				+ "primary key(id) \n"+ ")";
		String sql = "";
		// for������ table ������ ���� table�̸�, query �迭�� ����
		try {
			String[] tablename = { "customer", "staff", "room", "reservation" };
			String[] query = { customerQuery, managerQuery, roomQuery, reservationQuery };
			for (int i = 0; i < tablename.length; i++) {
				// db�� �̹� table�� ������ �Ǿ��ִ����� check, ���ٸ� ����, �ִٸ� �������� ����
				if (dbtableexist(tablename[i]) == false) {
					// System.out.println(tablename[i]);
					sql = query[i];
					stmt = dbTest.prepareStatement(sql);
					rs = stmt.executeQuery();
					dbTest.commit();
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "���̺������ ������ �߻��߽��ϴ� : " + e);
			dbTest.rollback();
		} finally {
			stmt.close();
			rs.close();
		}
	}

	// createTable �Լ� ����� DB�� table�� ���� ������ �˷��ִ� �Լ�
	public boolean dbtableexist(String tablename) throws SQLException {
		String sql = "Select TABLE_NAME from user_tables where table_name='" + tablename.toUpperCase() + "' ";
		boolean existTable = false;
		stmt = dbTest.prepareStatement(sql);
		rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getString("table_name").equals(tablename.toUpperCase())) {
				existTable = true; // db�� �̹� ���̺� ����� true : table ������ �ʿ䰡 ����
			}
		}
		stmt.close();
		rs.close();
		return existTable;
	}

	public TermP() {
		login_panel.setLayout(null);

		idLabel.setBounds(20, 10, 60, 30);
		pwdLabel.setBounds(20, 50, 60, 30);
		idInput.setBounds(100, 10, 80, 30);
		pwdInput.setBounds(100, 50, 80, 30);
		loginButton.setBounds(200, 25, 80, 35);
		loginButton.addActionListener(this);

		login_panel.add(idLabel);
		login_panel.add(pwdLabel);
		login_panel.add(idInput);
		login_panel.add(pwdInput);
		login_panel.add(loginButton);

		login_frame.add(login_panel);

		login_frame.setTitle("ȣ�ڽý��� �α���");
		login_frame.setSize(320, 130);
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login_frame.setVisible(true);
	}

	private void hotel() throws SQLException {
		createTable();
		login_frame.setVisible(false);
		frame.setLayout(null);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = "(" + sdf.format(date) + ")";
		todate.setText(today);
		todate.setBounds(950, 130, 150, 20);
		todate.setFont(new Font("����", Font.BOLD, 20));
		frame.add(todate);
		// ��� �޴��� ����
		jmb = new JMenuBar();
		JMenu jmFile = new JMenu("file"); // �޴���
		menuopen = new JMenuItem("Open"); // �޴��� �����޴� ����
		menuopen.addActionListener(this);
		jmFile.add(menuopen);
		jmb.add(jmFile); // �����޴��� �޴��ٿ� �߰�
		frame.setTitle("ȣ�ڰ����ý���");
		frame.setJMenuBar(jmb);
		frame.setSize(1150, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		title.setFont(new Font("����", Font.BOLD, 25)); // �۾�ü
		title.setBounds(300, 50, 500, 50); // label ��ġ
		title.setBorder(new LineBorder(Color.black)); // label �׵θ�����
		frame.add(title);
		reserve.setFont(new Font("����", Font.BOLD, 20));
		reserve.setBounds(50, 130, 150, 20);
		frame.add(reserve);
		cur_reserve.setFont(new Font("����", Font.BOLD, 20));
		cur_reserve.setBounds(600, 130, 150, 20);
		frame.add(cur_reserve);
		check.setFont(new Font("����", Font.BOLD, 20));
		check.setBounds(30, 420, 150, 20);
		frame.add(check);
		res_panel.setBounds(100, 100, 250, 250);
		res_day_box.addItem(1);
		res_day_box.addItem(2);
		res_day_box.addItem(3);
		res_day_box.addItem(4);
		res_day_box.addItem(5);
		res_day_box.addItem(6);
		res_day_box.addItem(7);
		res_day_box.addItem(8);
		res_day_box.addItem(9);
		res_day_box.addItem(10);
		res_panel.add(res_customer);
		res_panel.add(res_cust_Input);
		res_panel.add(res_checkin);
		res_panel.add(res_checkin_Input);
		res_panel.add(res_day);
		res_panel.add(res_day_box);
		res_panel.add(res_room);
		res_panel.add(res_room_box);
		res_panel.add(res_enroll);
		res_panel.add(res_cancel);
		res_enroll.addActionListener(this);
		res_cancel.addActionListener(this);
		reserve_panel.add(res_panel);
		reserve_panel.setBounds(80, 160, 450, 250);
		reserve_panel.setBorder(loweredborder);
		frame.add(reserve_panel);
		frame.add(current_panel);
		frame.add(enroll_panel);
		// ���� ���� ��Ȳ panel
		current_panel.setBounds(620, 160, 450, 250);
		current_panel.setBorder(loweredborder);
		current_panel.add(cur_panel);
		for (int i = 1; i < 11; i++) {
			table1[i] = new JLabel("10" + i);
			if (i == 10) {
				table1[i] = new JLabel("1" + i);
			}
			// 20�� �󺧵��� �׵θ��� ���� ���� ���̵��� border ����
			res_room_box.addItem(table1[i].getText());
			check_room_box.addItem(table1[i].getText());
			table1[i].setFont(new Font("����", Font.BOLD, 23));
			table1[i].setBorder(new LineBorder(Color.black)); // ��� ���� �׵θ��� ����
																// ���� ���̰� �߰�
			table1[i].setOpaque(true); // ���� ������ ���̰� ���ִ� ��
			table1[i].setBackground(Color.WHITE); // ������ ������� ����
			table1[i].setHorizontalAlignment(JLabel.CENTER); // �󺧾��� ȣ�� ���ڸ� ���
																// ����
			cur_panel.add(table1[i]); // �гο� ���� �������
		}
		for (int i = 1; i < 11; i++) {
			table2[i] = new JLabel("20" + i);
			if (i == 10) {
				table2[i] = new JLabel("2" + i);
			}
			// 20�� �󺧵��� �׵θ��� ���� ���� ���̵��� border ����
			res_room_box.addItem(table2[i].getText());
			table2[i].setFont(new Font("����", Font.BOLD, 23));
			check_room_box.addItem(table2[i].getText());
			table2[i].setBorder(new LineBorder(Color.black)); // ��� ���� �׵θ��� ����
																// ���� ���̰� �߰�
			table2[i].setOpaque(true); // ���� ������ ���̰� ���ִ� ��
			table2[i].setBackground(Color.WHITE); // ������ ������� ����
			table2[i].setHorizontalAlignment(JLabel.CENTER); // �󺧾��� ȣ�� ���ڸ� ���
																// ����
			cur_panel.add(table2[i]); // �гο� ���� �������

		}
		////////////////////////////////////////////////////////////////////////////////////////////
		// ���/��ȸ panel
		searchRegi_P = new JPanel();
		searchRegi_P.setLayout(null);

		tabPanel = new JTabbedPane();
		tabPanel.setBounds(30, 30, 1000, 300);
		// ��tab
		guestTab = new JPanel();
		guestTab.setBorder(new LineBorder(Color.black));
		guestTab.setLayout(null);

		check_guestname = new JLabel("����");
		check_guestname.setFont(new Font("����", Font.BOLD, 15));
		check_guestname.setBounds(20, 50, 100, 50);
		// ���̸��Է¶�
		check_guestname_Input = new TextField(10);
		check_guestname_Input.setBounds(150, 60, 150, 30);
		// ���Թ�ư
		check_register = new JButton("ȸ������");
		check_register.addActionListener(this);
		check_register.setBounds(40, 150, 100, 30);
		check_register.setFont(new Font("����", Font.PLAIN, 15));
		// ��ȸ��ư
		check_search = new JButton("��ȸ");
		check_search.addActionListener(this);
		check_search.setFont(new Font("����", Font.PLAIN, 15));
		check_search.setBounds(190, 150, 100, 30);
		// ���â
		guest_textarea = new JTextArea(10, 20);
		guest_textarea.setBounds(500, 20, 450, 230);
		guest_textarea.setBorder(new LineBorder(Color.black));
		guest_textarea.setEditable(false);

		guestTab.add(check_guestname);
		guestTab.add(check_guestname_Input);
		guestTab.add(check_register);
		guestTab.add(check_search);
		guestTab.add(guest_textarea);

		// ����tab
		roomTab = new JPanel();
		roomTab.setLayout(null);
		roomTab.setBorder(new LineBorder(Color.black));

		check_room = new JLabel("����");
		check_room.setFont(new Font("����", Font.BOLD, 15));
		check_room.setBounds(20, 50, 100, 50);

		check_room_box.setBounds(150, 60, 100, 30);
		check_room_box.addActionListener(this);

		room_textarea = new JTextArea(10, 20);
		room_textarea.setBounds(500, 20, 450, 230);
		room_textarea.setBorder(new LineBorder(Color.black));
		room_textarea.setEditable(false);

		roomTab.add(check_room);
		roomTab.add(check_room_box);
		roomTab.add(room_textarea);

		// ���� tab
		empTab = new JPanel();
		empTab.setLayout(null);
		empTab.setBorder(new LineBorder(Color.black));

		check_empname = new JLabel("������");
		check_empname.setFont(new Font("����", Font.BOLD, 15));
		check_empname.setBounds(20, 50, 100, 50);
		// ���̸��Է¶�
		check_empname_Input = new TextField(10);
		check_empname_Input.setBounds(150, 60, 150, 30);
		// ���Թ�ư
		check_empRegister = new JButton("�������");
		check_empRegister.addActionListener(this);
		check_empRegister.setBounds(40, 150, 100, 30);
		check_empRegister.setFont(new Font("����", Font.PLAIN, 15));
		// ��ȸ��ư
		check_empSearch = new JButton("��ȸ");
		check_empSearch.addActionListener(this);
		check_empSearch.setFont(new Font("����", Font.PLAIN, 15));
		check_empSearch.setBounds(190, 150, 100, 30);
		// ���â
		emp_textarea = new JTextArea(10, 20);
		emp_textarea.setBounds(500, 20, 450, 230);
		emp_textarea.setBorder(new LineBorder(Color.black));
		emp_textarea.setEditable(false);

		empTab.add(check_empname);
		empTab.add(check_empname_Input);
		empTab.add(check_empRegister);
		empTab.add(check_empSearch);
		empTab.add(emp_textarea);

		tabPanel.add("��", guestTab);
		tabPanel.add("����", roomTab);
		tabPanel.add("����", empTab);

		searchRegi_P.add(tabPanel);
		searchRegi_P.setBounds(50, 450, 1050, 350);// ���/��ȸ �׵θ�
		searchRegi_P.setBorder(loweredborder);

		frame.add(title); // title label �߰�
		///// frame.add(frame_currentDateLabel);//����ð� label ��
		frame.add(searchRegi_P);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			username = idInput.getText();
			password = new String(pwdInput.getPassword());

			connectDB();
			try {
				hotel();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == menuopen) {
			if (jfc.showOpenDialog(jmb) == JFileChooser.APPROVE_OPTION) {
				try {
					File SelectedFile = jfc.getSelectedFile();
					try {
						readFile(SelectedFile);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "������ �о���̴� ���� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���.");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�޴����� ����");
				}
			}
		} else if (e.getSource() == emp_p_cancel) {
			emp_frame.setVisible(false);
		} else if (e.getSource() == cust_cancel) {
			cust_frame.setVisible(false);
		} else if (e.getSource() == check_register) {
			cust_panel.setLayout(null);
			cust_frame.setTitle("ȸ������");
			cust_frame.setSize(400, 400);
			customer.setBounds(70, 50, 100, 30);
			cust_Input.setBounds(170, 50, 130, 30);
			cust_gender.setBounds(70, 100, 80, 30);
			cust_gender_box.setBounds(170, 100, 130, 30);
			cust_gender_box.addItem("��");
			cust_gender_box.addItem("��");
			cust_address.setBounds(70, 150, 80, 30);
			cust_address_box.setBounds(170, 150, 130, 30);
			cust_address_box.addItem("����");
			cust_address_box.addItem("���");
			cust_address_box.addItem("����");
			cust_call_Number.setBounds(70, 200, 60, 30);
			cust_call_Input.setBounds(170, 200, 130, 30);
			enroll_cust.setBounds(70, 260, 100, 35);
			enroll_cust.addActionListener(this);
			cust_cancel.setBounds(210, 260, 100, 35);
			cust_cancel.addActionListener(this);
			cust_panel.add(enroll_cust);
			cust_panel.add(customer);
			cust_panel.add(cust_Input);
			cust_panel.add(cust_call_Input);
			cust_panel.add(cust_call_Number);
			cust_panel.add(cust_gender);
			cust_panel.add(cust_address);
			cust_panel.add(cust_gender_box);
			cust_panel.add(cust_address_box);
			cust_panel.add(cust_cancel);
			cust_frame.add(cust_panel);
			cust_frame.setVisible(true);
		} else if (e.getSource() == check_empRegister) {
			emp_panel.setLayout(null);
			emp_frame.setTitle("ȸ������");
			emp_frame.setSize(400, 400);
			employee.setBounds(70, 50, 100, 30);
			emp_p_Input.setBounds(170, 50, 130, 30);
			emp_p_gender.setBounds(70, 100, 80, 30);
			emp_p_gender_box.setBounds(170, 100, 130, 30);
			emp_p_gender_box.addItem("��");
			emp_p_gender_box.addItem("��");
			emp_p_address.setBounds(70, 150, 80, 30);
			emp_p_address_box.setBounds(170, 150, 130, 30);
			emp_p_address_box.addItem("����");
			emp_p_address_box.addItem("���");
			emp_p_address_box.addItem("����");
			emp_p_call_Number.setBounds(70, 200, 60, 30);
			emp_p_call_Input.setBounds(170, 200, 130, 30);
			emp_p_enroll_cust.setBounds(70, 260, 100, 35);
			emp_p_enroll_cust.addActionListener(this);
			emp_p_cancel.setBounds(210, 260, 100, 35);
			emp_p_cancel.addActionListener(this);
			emp_panel.add(employee);
			emp_panel.add(emp_p_Input);
			emp_panel.add(emp_p_call_Input);
			emp_panel.add(emp_p_customer);
			emp_panel.add(emp_p_call_Number);
			emp_panel.add(emp_p_gender);
			emp_panel.add(emp_p_address);
			emp_panel.add(emp_p_gender_box);
			emp_panel.add(emp_p_address_box);
			emp_panel.add(emp_p_enroll_cust);
			emp_panel.add(emp_p_cancel);
			emp_frame.add(emp_panel);
			emp_frame.setVisible(true);
		} else if (e.getSource() == emp_p_enroll_cust) {
			try {
				String empname = emp_p_Input.getText();
				String empgender = ((String) emp_p_gender_box.getSelectedItem());
				String empaddress = ((String) emp_p_address_box.getSelectedItem());
				String empcall = emp_p_call_Input.getText();
				String sqlStr = "insert into staff values ('" + empname + "', '" + empgender + "' ,'" + empaddress
						+ "' ,'" + empcall + "')";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				dbTest.commit();
				rs.close();
				stmt.close();
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, (String) "��������� �� �� �����ϴ�.", "�޽���", 2);
				// x.printStackTrace();
			}
		} else if (e.getSource() == enroll_cust) {
			try {
				String custname = cust_Input.getText();
				String custgender = ((String) cust_gender_box.getSelectedItem());
				String custaddress = ((String) cust_address_box.getSelectedItem());
				String custcall = cust_call_Input.getText();
				String sqlStr = "insert into customer values ('" + custname + "', '" + custgender + "' ,'" + custaddress
						+ "' ,'" + custcall + "')";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				dbTest.commit();
				rs.close();
				stmt.close();
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, (String) "������� �� �� �����ϴ�.", "�޽���", 2);
				// x.printStackTrace();
			}
		} else if (e.getSource() == check_search) { // �� ��ȸ��ư
			try {
				int countT = 0;
				guest_textarea.setText(null);
				String custgender = "";
				String custaddress = "";
				String custcall = "";
				String custname = check_guestname_Input.getText();
				String sqlStr = "select C_NAME,C_GENDER,C_ADDRESS,C_PHONE from customer where (C_NAME = '" + custname
						+ "')";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					custgender = rs.getString("C_GENDER");
					custaddress = rs.getString("C_ADDRESS");
					custcall = rs.getString("C_PHONE");
				}
				rs.close();
				stmt.close();
				String sqlStr2 = "select sum(term) from reservation where c_name = '" + custname + "'";
				PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					countT = rs2.getInt("sum(term)");
				}
				rs2.close();
				stmt2.close();
				String maxckout = "";
				String sqlStr3 = "select max(checkout) from reservation where c_name = '" + custname + "'";
				PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
				ResultSet rs3 = stmt3.executeQuery();
				while (rs3.next()) {
					maxckout = rs3.getString("max(checkout)");
					if (maxckout == null) {
						maxckout = "";
					}
				}
				rs3.close();
				stmt3.close();
				String maxstf = "";
				int ctcust = 0;
				String sqlStr4 = "select * from (select s_name,c_name,count(s_name) from reservation where c_name = '"
						+ custname
						+ "' group by c_name,s_name having count(s_name) > 0 order by count(s_name) desc) where rownum = 1";
				PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
				ResultSet rs4 = stmt4.executeQuery();
				while (rs4.next()) {
					maxstf = rs4.getString("S_NAME");
					ctcust = rs4.getInt("COUNT(S_NAME)");
				}
				rs4.close();
				stmt4.close();
				guest_textarea.append(" ���� : " + custname + " \n ���� : " + custgender + "\n �ּ� : " + custaddress
						+ "\n ����ó : " + custcall + " \n �� �����Ⱓ : " + countT + "\n �ֱ� ������ : " + maxckout
						+ "\n ������������(�ִ�) : " + maxstf + "\t (" + ctcust + ")ȸ");
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, (String) "���/��ȸpanel textarea����", "�޽���", 2);
				x.printStackTrace();
			}
		} else if (e.getSource() == check_empSearch) { // ���� ��ȸ��ư
			try {
				emp_textarea.setText(null);
				String empgender = "";
				String empaddress = "";
				String empcall = "";
				String empname = check_empname_Input.getText();
				String sqlStr = "select S_NAME,S_GENDER,S_ADDRESS,S_PHONE from staff where (S_NAME = '" + empname
						+ "')";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					empgender = rs.getString("S_GENDER");
					empaddress = rs.getString("S_ADDRESS");
					empcall = rs.getString("S_PHONE");
				}
				rs.close();
				stmt.close();
				String maxcust = "";
				int ctstff = 0;
				String sqlStr2 = "select * from (select s_name,c_name,count(c_name) from reservation where s_name = '"
						+ empname
						+ "' group by c_name,s_name having count(c_name) > 0 order by count(c_name) desc) where rownum = 1";
				PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					maxcust = rs2.getString("C_NAME");
					ctstff = rs2.getInt("COUNT(C_NAME)");
				}
				rs2.close();
				stmt2.close();
				String sqlStr3 = "select * from (select s_name,count(roomno),roomno from reservation where s_name = '"
						+ empname
						+ "' group by s_name,roomno having count(roomno) > 0 order by count(roomno) desc) where rownum = 1";
				PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
				ResultSet rs3 = stmt3.executeQuery();
				String maxroom = "";
				int ctroom = 0;
				while (rs3.next()) {
					maxroom = rs3.getString("roomno");
					ctroom = rs3.getInt("count(roomno)");
				}
				rs3.close();
				stmt3.close();
				emp_textarea.append(" ������ : " + empname + " \n ���� : " + empgender + "\n �ּ� : " + empaddress
						+ "\n ����ó : " + empcall + " \n ���� ��(�ִ�) : " + maxcust + "\t(" + ctstff + ")ȸ"
						+ "\n ��������(�ִ�) : " + maxroom + "\t(" + ctroom + ")ȸ");
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, (String) "���/��ȸpanel textarea����", "�޽���", 2);
				x.printStackTrace();
			}
		} else if (e.getSource() == check_room_box) { // ���� ��ȸ üũ�ڽ�
			try {
				room_textarea.setText(null);
				String roomno = check_room_box.getSelectedItem() + "";
				String person = "";
				String type = "";
				String sqlStr2 = "select ROOMNO,PERSON,TYPE from room where ROOMNO = '" + roomno + "'";
				PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
				ResultSet rs2 = stmt2.executeQuery();
				while (rs2.next()) {
					person = rs2.getString("PERSON");
					type = rs2.getString("TYPE");
				}
				rs2.close();
				stmt2.close();
				String sqlStr3 = "select CHECKIN,CHECKOUT from reservation where roomno = '" + roomno + "'";
				PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
				ResultSet rs3 = stmt3.executeQuery();
				String tempci = "";
				String tempco = "";
				String status = "";
				while (rs3.next()) {
					tempci = rs3.getString("CHECKIN");
					tempco = rs3.getString("CHECKOUT");
					try {
						Date tod = new Date();
						SimpleDateFormat sdf6 = new SimpleDateFormat("yyyyMMdd");
						String todate = sdf6.format(tod);
						Date today = sdf6.parse(todate);
						Date tmpci = new Date();
						Date tmpco = new Date();
						SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						tmpci = sdf4.parse(tempci);
						tmpco = sdf4.parse(tempco);
						System.out.println(today);
						System.out.println(tmpci);
						System.out.println(tmpco);
						if (tmpci.compareTo(today) <= 0 && today.compareTo(tmpco) == -1) {
							status = "������";
						} else {
							status = "�������";
						}
					} catch (Exception x) {
						x.printStackTrace();
					}
				}
				rs3.close();
				stmt3.close();
				String sqlStr4 = "select * from (select s_name,count(roomno),roomno from reservation where roomno = "
						+ roomno
						+ " group by s_name,roomno having count(roomno) > 0 order by count(roomno) desc) where rownum = 1";
				PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
				ResultSet rs4 = stmt4.executeQuery();
				String sn = "";
				int strm = 0;
				while (rs4.next()) {
					sn = rs4.getString("s_name");
					strm = rs4.getInt("count(roomno)");
				}
				rs4.close();
				stmt4.close();
				String sqlStr5 = "select * from (select c_name,count(roomno),roomno from reservation where roomno = "
						+ roomno
						+ "group by c_name,roomno having count(roomno) > 0 order by count(roomno) desc) where rownum = 1";
				PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
				ResultSet rs5 = stmt5.executeQuery();
				String cn = "";
				int ctrm = 0;
				while (rs5.next()) {
					cn = rs5.getString("c_name");
					ctrm = rs5.getInt("count(roomno)");
				}
				rs5.close();
				stmt5.close();
				room_textarea.append(" ���ȣ : " + roomno + " \n �����ο� : " + person + "\n Ÿ�� : " + type + "\n ���� : "
						+ status + " \n ������(�ִ�) : " + cn + "\t (" + ctrm + ")ȸ" + "\n ������������(�ִ�) : " + sn + "\t ("
						+ strm + ")ȸ");
			} catch (SQLException x) {
				JOptionPane.showMessageDialog(null, (String) "���/��ȸpanel textarea����", "�޽���", 2);
				x.printStackTrace();
			}
		} else if (e.getSource() == res_enroll) {
			try {

				String custName = res_cust_Input.getText();
				String oldcheckin = res_checkin_Input.getText();
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
				Date checkIn = sdf2.parse(oldcheckin); // �ؽ�Ʈ�ڽ��� �Է��� ��¥
				String newcheckIn = sdf2.format(checkIn);
				String term = res_day_box.getSelectedItem() + "";
				String roomno = res_room_box.getSelectedItem() + "";
				Calendar inDate = Calendar.getInstance();
				inDate.setTime(checkIn);
				inDate.add(Calendar.DATE, Integer.parseInt(term));
				Date oldcheckOut = inDate.getTime();
				SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
				String newcheckOut = sdf3.format(oldcheckOut);
				String staff_cus = "";
				String sqlStr = "select s_name from staff order by DBMS_RANDOM.RANDOM";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					staff_cus = rs.getString("s_name");
				}
				rs.close();
				stmt.close();
				String sqlStr2 = "select c_name from customer where c_name = '" + custName + "'";
				PreparedStatement stmt2 = dbTest.prepareStatement(sqlStr2);
				ResultSet rs2 = stmt2.executeQuery();
				if (rs2.next() == false) {// ��ϵ� �̸����� Ȯ��
					JOptionPane.showMessageDialog(null, (String) "���� ȸ������� ���ּ���!", "�޽���", 2);
				} else {// db�� ��ϵ� �̸��϶�
					String check_cust_name = "";
					String c_checkin = "";
					String c_checkout = "";
					String c_checkterm = "";
					String c_roomno = "";
					String sqlStr3 = "select C_NAME from reservation where C_NAME = '" + custName + "'";
					PreparedStatement stmt3 = dbTest.prepareStatement(sqlStr3);
					ResultSet rs3 = stmt3.executeQuery();
					while (rs3.next()) {
						check_cust_name = rs3.getString("C_NAME");
					}
					if (check_cust_name == "") {// reservation ���̺� �ȿ� �̸� ������!
						String sqlStr4 = "insert into reservation values(res_seq.nextval,'" + custName + "','"
								+ newcheckIn + "','" + newcheckOut + "','" + term + "','" + roomno + "','" + staff_cus
								+ "')";
						PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
						ResultSet rs4 = stmt4.executeQuery();
						dbTest.commit();
						rs4.close();
						stmt4.close();
						Date tod = new Date();
						SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
						String todate = sdf6.format(tod);
						Date today = sdf6.parse(todate);
						String temproom = "";
						String tempin = "";
						String tempout = "";
						Date tempckin = new Date();
						Date tempckout = new Date();
						for (int i = 1; i < 11; i++) {
							String t1 = table1[i].getText();
							String t2 = table2[i].getText();
							String sqlStr5 = "select ROOMNO,CHECKIN,CHECKOUT from reservation";
							PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
							ResultSet rs5 = stmt5.executeQuery();
							while (rs5.next()) {
								temproom = rs5.getString("ROOMNO");
								tempin = rs5.getString("CHECKIN");
								tempckin = sdf6.parse(tempin);
								tempout = rs5.getString("CHECKOUT");
								tempckout = sdf6.parse(tempout);
								if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
										&& (temproom.equals(t1))) {
									table1[i].setBackground(Color.yellow);
									break;
								} else if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
										&& (temproom.equals(t2))) {
									table2[i].setBackground(Color.yellow);
									break;
								} else {
									table1[i].setBackground(Color.white);
									table2[i].setBackground(Color.white);
								}
							}
						}
					} else {// reservation ���̺� �ȿ� �̸� �ִ� ���!
						String sqlStr4 = "select C_NAME,CHECKIN,CHECKOUT,TERM,ROOMNO from reservation where C_NAME = '"
								+ custName + "'";
						PreparedStatement stmt4 = dbTest.prepareStatement(sqlStr4);
						ResultSet rs4 = stmt4.executeQuery();
						while (rs4.next()) {
							check_cust_name = rs4.getString("C_NAME");
							c_checkin = rs4.getString("CHECKIN");
							c_checkout = rs4.getString("CHECKOUT");
							c_checkterm = rs4.getString("TERM");
							c_roomno = rs4.getString("ROOMNO");
							SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date comp_in = sdf4.parse(c_checkin);// DB�� check-in
																	// >
																	// compareTo��
							SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date comp_out = sdf5.parse(c_checkout);// DB��
																	// checkout
																	// >
																	// compareTo��
							String upcheckIn = sdf2.format(comp_in); // yyyyMMdd
							String upcheckOut = sdf2.format(comp_out);
							// checkIn > a(�Է��Ѱ�-üũ��) oldcheckOut > b(�Է��Ѱ�-üũ�ƿ�)
							// comp_in > c(db��) comp_out > d(db��)
							if ((checkIn.compareTo(comp_in) >= 0 && checkIn.compareTo(comp_out) <= 0
									|| comp_in.compareTo(checkIn) >= 0 && comp_in.compareTo(oldcheckOut) <= 0)) {
								// �Ⱓ�ߺ��� ��
								JOptionPane.showMessageDialog(null, (String) "���೯¥�� ���ļ� ������ �����մϴ�.", "�޽���", 2);
								String sqlStr5 = "insert into reservation values(res_seq.nextval,'" + check_cust_name
										+ "','" + newcheckIn + "','" + newcheckOut + "','" + term + "','" + roomno
										+ "','" + staff_cus + "')";
								PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
								ResultSet rs5 = stmt5.executeQuery();
								rs5.close();
								stmt5.close();
								dbTest.commit();
								String sqlStr6 = "delete from reservation where C_NAME = '" + check_cust_name
										+ "' and CHECKIN = '" + upcheckIn + "' and CHECKOUT = '" + upcheckOut + "'";
								PreparedStatement stmt6 = dbTest.prepareStatement(sqlStr6);
								ResultSet rs6 = stmt6.executeQuery();
								rs6.close();
								stmt6.close();
								dbTest.commit();
								String sqlStr7 = "delete from reservation where (ROWID > (select min(ROWID) from reservation where checkin = '"
										+ newcheckIn + "') and checkin = '" + newcheckIn + "')";
								PreparedStatement stmt7 = dbTest.prepareStatement(sqlStr7);
								ResultSet rs7 = stmt7.executeQuery();
								dbTest.commit();
								rs7.close();
								stmt7.close();
								dbTest.commit();
								// ���̺� �� �����ϱ�
								Date tod = new Date();
								SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
								String todate = sdf6.format(tod);
								Date today = sdf6.parse(todate);
								String temproom = "";
								String tempin = "";
								String tempout = "";
								Date tempckin = new Date();
								Date tempckout = new Date();
								for (int i = 1; i < 11; i++) {
									String t1 = table1[i].getText();
									String t2 = table2[i].getText();
									String sqlStr8 = "select ROOMNO,CHECKIN,CHECKOUT from reservation";
									PreparedStatement stmt8 = dbTest.prepareStatement(sqlStr8);
									ResultSet rs8 = stmt8.executeQuery();
									while (rs8.next()) {
										temproom = rs8.getString("ROOMNO");
										tempin = rs8.getString("CHECKIN");
										tempckin = sdf6.parse(tempin);
										tempout = rs8.getString("CHECKOUT");
										tempckout = sdf6.parse(tempout);
										if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
												&& (temproom.equals(t1))) {
											table1[i].setBackground(Color.yellow);
											break;
										} else if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
												&& (temproom.equals(t2))) {
											table2[i].setBackground(Color.yellow);
											break;
										} else {
											table1[i].setBackground(Color.white);
											table2[i].setBackground(Color.white);
										}
									}
								}
							} else if ((oldcheckOut.compareTo(comp_in) == -1 && checkIn.compareTo(comp_in) == -1)
									|| (oldcheckOut.compareTo(comp_out) == 1 && checkIn.compareTo(comp_out) == 1)) {
								String sqlStr5 = "insert into reservation values(res_seq.nextval,'" + check_cust_name
										+ "','" + newcheckIn + "','" + newcheckOut + "'," + term + "," + roomno + ",'"
										+ staff_cus + "')";
								dbTest.commit();
								PreparedStatement stmt5 = dbTest.prepareStatement(sqlStr5);
								ResultSet rs5 = stmt5.executeQuery();
								String sqlStr6 = "delete from reservation where (ROWID > (select min(ROWID) from reservation where checkin = '"
										+ newcheckIn + "') and checkin = '" + newcheckIn + "')";
								PreparedStatement stmt6 = dbTest.prepareStatement(sqlStr6);
								ResultSet rs6 = stmt6.executeQuery();
								dbTest.commit();
								rs6.close();
								stmt6.close();
								rs5.close();
								stmt5.close();
								Date tod = new Date();
								SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
								String todate = sdf6.format(tod);
								Date today = sdf6.parse(todate);
								String temproom = "";
								String tempin = "";
								String tempout = "";
								Date tempckin = new Date();
								Date tempckout = new Date();
								for (int i = 1; i < 11; i++) {
									String t1 = table1[i].getText();
									String t2 = table2[i].getText();
									String sqlStr7 = "select ROOMNO,CHECKIN,CHECKOUT from reservation";
									PreparedStatement stmt7 = dbTest.prepareStatement(sqlStr7);
									ResultSet rs7 = stmt7.executeQuery();
									while (rs7.next()) {
										temproom = rs7.getString("ROOMNO");
										tempin = rs7.getString("CHECKIN");
										tempckin = sdf6.parse(tempin);
										tempout = rs7.getString("CHECKOUT");
										tempckout = sdf6.parse(tempout);
										if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
												&& (temproom.equals(t1))) {
											table1[i].setBackground(Color.yellow);
											break;
										} else if ((tempckin.compareTo(today) <= 0 && today.compareTo(tempckout) == -1)
												&& (temproom.equals(t2))) {
											table2[i].setBackground(Color.yellow);
											break;
										} else {
											table1[i].setBackground(Color.white);
											table2[i].setBackground(Color.white);
										}
									}
								}
							}
						}
					}
					rs3.close();
					stmt3.close();
				}
				rs2.close();
				stmt2.close();
			} catch (SQLException | ParseException x) {
				JOptionPane.showMessageDialog(null, (String) "���� ���/���� ����", "�޽���", 2);
				x.printStackTrace();
			}
		} else if (e.getSource() == res_cancel) {
			try {
				String custName = res_cust_Input.getText();
				String oldcheckin = res_checkin_Input.getText();
				String term = res_day_box.getSelectedItem() + "";
				String roomno = res_room_box.getSelectedItem() + "";
				String sqlStr = "delete from reservation where C_NAME = '" + custName + "' and CHECKIN = '" + oldcheckin
						+ "' and roomno = '" + roomno + "'";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
			} catch (Exception x) {
				x.printStackTrace();
			}
		}
	}

	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
			System.out.println("�����ͺ��̽����� ���� - id: " + username);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����");
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	public static void main(String[] argv) {
		new TermP();
	}
}