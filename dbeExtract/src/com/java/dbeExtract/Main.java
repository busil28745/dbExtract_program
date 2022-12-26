package com.java.dbeExtract;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

	private JFrame frame;
	private JTextField dbHost;
	private JTextField dbPort;
	private JTextField dbUserName;
	private JTextField dbPassword;
	private JTextField dbDb;
	private JTextField dbSchema;
	private JTextField hostName;
	private JTextField dbUser;
	private JTextField writerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("데이터베이스 추출 프로그램");
		frame.getContentPane().setFont(new Font("굴림", Font.BOLD, 17));
		frame.setBounds(100, 100, 636, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);

		JLabel lblNewLabel = new JLabel("데이터베이스 추출 프로그램");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 598, 42);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("데이터베이스 종류 : ");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(118, 60, 186, 21);
		frame.getContentPane().add(lblNewLabel_4);

		dbHost = new JTextField();
		dbHost.setFont(new Font("굴림", Font.PLAIN, 12));
		dbHost.setToolTipText("HOST주소(IP주소)");
		dbHost.setBounds(308, 130, 140, 21);
		frame.getContentPane().add(dbHost);
		dbHost.setColumns(10);

		JLabel dbHostLabel = new JLabel("HOST주소 (IP주소) : ");
		dbHostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbHostLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbHostLabel.setBounds(183, 130, 121, 15);
		frame.getContentPane().add(dbHostLabel);

		JLabel dbPortLabel = new JLabel("PORT번호 : ");
		dbPortLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPortLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbPortLabel.setBounds(183, 160, 121, 15);
		frame.getContentPane().add(dbPortLabel);

		dbPort = new JTextField();
		dbPort.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPort.setToolTipText("PORT번호");
		dbPort.setBounds(308, 160, 140, 21);
		frame.getContentPane().add(dbPort);
		dbPort.setColumns(10);

		JLabel dbUserNameLabel = new JLabel("USERNAME : ");
		dbUserNameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUserNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbUserNameLabel.setBounds(183, 190, 121, 15);
		frame.getContentPane().add(dbUserNameLabel);

		dbUserName = new JTextField();
		dbUserName.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUserName.setToolTipText("USERNAME");
		dbUserName.setBounds(308, 190, 140, 21);
		frame.getContentPane().add(dbUserName);
		dbUserName.setColumns(10);

		JLabel dbPasswordLabel = new JLabel("PASSWORD : ");
		dbPasswordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbPasswordLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPasswordLabel.setBounds(183, 220, 121, 15);
		frame.getContentPane().add(dbPasswordLabel);

		dbPassword = new JTextField();
		dbPassword.setFont(new Font("굴림", Font.PLAIN, 12));
		dbPassword.setToolTipText("PASSWORD");
		dbPassword.setBounds(308, 220, 140, 21);
		frame.getContentPane().add(dbPassword);
		dbPassword.setColumns(10);

		JLabel dbDbLabel = new JLabel("DB명 : ");
		dbDbLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbDbLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbDbLabel.setBounds(183, 250, 121, 15);
		frame.getContentPane().add(dbDbLabel);

		dbDb = new JTextField();
		dbDb.setFont(new Font("굴림", Font.PLAIN, 12));
		dbDb.setToolTipText("DB명");
		dbDb.setBounds(308, 250, 140, 21);
		frame.getContentPane().add(dbDb);
		dbDb.setColumns(10);

		JLabel dbSchemaLabel = new JLabel("Schema명 : ");
		dbSchemaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbSchemaLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbSchemaLabel.setBounds(183, 280, 121, 15);
		frame.getContentPane().add(dbSchemaLabel);

		dbSchema = new JTextField();
		dbSchema.setFont(new Font("굴림", Font.PLAIN, 12));
		dbSchema.setToolTipText("Schema명");
		dbSchema.setBounds(308, 280, 140, 21);
		frame.getContentPane().add(dbSchema);
		dbSchema.setColumns(10);

		JLabel hostNameLabel = new JLabel("HOST명 : ");
		hostNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hostNameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		hostNameLabel.setBounds(183, 310, 121, 15);
		frame.getContentPane().add(hostNameLabel);

		hostName = new JTextField();
		hostName.setToolTipText("테이블 인덱스 정의서에 쓰일 HOST명");
		hostName.setFont(new Font("굴림", Font.PLAIN, 12));
		hostName.setBounds(308, 310, 140, 21);
		frame.getContentPane().add(hostName);
		hostName.setColumns(10);

		JLabel dbUserLabel = new JLabel("DB_USER명 : ");
		dbUserLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUserLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbUserLabel.setBounds(183, 340, 121, 15);
		frame.getContentPane().add(dbUserLabel);

		dbUser = new JTextField();
		dbUser.setToolTipText("테이블 인덱스 정의서에 쓰일 DB_USER명");
		dbUser.setFont(new Font("굴림", Font.PLAIN, 12));
		dbUser.setBounds(308, 340, 140, 21);
		frame.getContentPane().add(dbUser);
		dbUser.setColumns(10);

		writerName = new JTextField();
		writerName.setText("BUSIL");
		writerName.setToolTipText("작성자 이름");
		writerName.setFont(new Font("굴림", Font.PLAIN, 12));
		writerName.setBounds(308, 370, 140, 21);
		frame.getContentPane().add(writerName);
		writerName.setColumns(10);

		JLabel nameLabel = new JLabel("작성자 : ");
		nameLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setBounds(183, 370, 121, 15);
		frame.getContentPane().add(nameLabel);

		JRadioButton postgresqlBtn = new JRadioButton("postgresql");
		postgresqlBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		postgresqlBtn.setBounds(308, 101, 140, 21);
		frame.getContentPane().add(postgresqlBtn);
		postgresqlBtn.setActionCommand("postgresql");

		JRadioButton mariadbBtn = new JRadioButton("mariadb");
		mariadbBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		mariadbBtn.setBounds(308, 81, 140, 21);
		frame.getContentPane().add(mariadbBtn);
		mariadbBtn.setActionCommand("mariadb");

		JRadioButton mysqlBtn = new JRadioButton("mysql");
		mysqlBtn.setSelected(true);
		mysqlBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		mysqlBtn.setBounds(308, 60, 140, 21);
		frame.getContentPane().add(mysqlBtn);
		mysqlBtn.setActionCommand("mysql");

		ButtonGroup group = new ButtonGroup();
		group.add(postgresqlBtn);
		group.add(mariadbBtn);
		group.add(mysqlBtn);

		JLabel copyright = new JLabel("Copyright 2022. abacus. All rights reserved.");
		copyright.setHorizontalAlignment(SwingConstants.CENTER);
		copyright.setBounds(12, 432, 598, 15);
		frame.getContentPane().add(copyright);

		//버튼 실행
		JButton execute = new JButton("실행");
		execute.setFont(new Font("굴림", Font.PLAIN, 12));
		execute.setBounds(210, 401, 94, 21);
		frame.getContentPane().add(execute);
		execute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String DB_TYPE = null;
				if (postgresqlBtn.isSelected()) {
					DB_TYPE = group.getSelection().getActionCommand();
				}
				if (mariadbBtn.isSelected()) {
					DB_TYPE = group.getSelection().getActionCommand();
				}
				if (mysqlBtn.isSelected()) {
					DB_TYPE = group.getSelection().getActionCommand();
				}
				String DB_HOST = dbHost.getText();
				String DB_PORT = dbPort.getText();
				String DB_USERNAME = dbUserName.getText();
				String DB_PASSWORD = dbPassword.getText();
				String DBDB = dbDb.getText();
				String SCHEMA = dbSchema.getText();
				String hostNames = hostName.getText();
				String DB_USER = dbUser.getText();
				String name = writerName.getText();
				//String location = fileLocation.getText();

//				System.out.println(DB_HOST);
//				System.out.println(DB_PORT);
//				System.out.println(DB_USERNAME);
//				System.out.println(DB_PASSWORD);
//				System.out.println(SCHEMA);
//				System.out.println(DB_TYPE);
				String path = System.getProperty("user.dir");
				ConnectionDb test = new ConnectionDb(); //오류 발생시 어케 되는가?
				HashMap<String, ArrayList<ArrayList<String>>> dbMap = new HashMap<>();
				dbMap = test.connectDB(DB_TYPE, DB_HOST, DB_PORT, DB_USERNAME, DB_PASSWORD, SCHEMA, DBDB);
				System.out.println(dbMap.toString());
				Excel excel = new Excel(); //오류 발생시 어케 되는가?
				excel.excelFile(dbMap, name, hostNames, DB_USER, path);
				System.exit(0);
			}
		});

		JButton exit = new JButton("종료");
		exit.setFont(new Font("굴림", Font.PLAIN, 12));
		exit.setBounds(308, 401, 94, 21);
		frame.getContentPane().add(exit);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}

}
