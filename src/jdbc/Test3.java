package jdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Test3 {
	public static JLabel table1[] = new JLabel[20];
	public static JLabel table2[] = new JLabel[20];
	private static JPanel cur_panel = new JPanel(new GridLayout(4, 5, 30, 37));
	private static JFrame frame = new JFrame();
	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			table1[i] = new JLabel("10" + i);
			if (i == 10) {
				table1[i] = new JLabel("1" + i);
			}
			// 20�� �󺧵��� �׵θ��� ���� ���� ���̵��� border ����
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
			table2[i].setFont(new Font("����", Font.BOLD, 23));
			table2[i].setBorder(new LineBorder(Color.black)); // ��� ���� �׵θ��� ����
																// ���� ���̰� �߰�
			table2[i].setOpaque(true); // ���� ������ ���̰� ���ִ� ��
			table2[i].setBackground(Color.WHITE); // ������ ������� ����
			table2[i].setHorizontalAlignment(JLabel.CENTER); // �󺧾��� ȣ�� ���ڸ� ���
																// ����
			cur_panel.add(table2[i]); // �гο� ���� �������
	}
	for (int i =1; i<11;i++){
		if (table1[i].getText().equals("101") || table2[i].getText().equals("101")){					
			table1[i].setBackground(Color.BLACK);
			table2[i].setBackground(Color.BLACK);
		}
	}
	frame.add(cur_panel);
	frame.setSize(400, 350);
	frame.setVisible(true);
		}
	}