
//Student Name: Issaq Al-Ahmed

package as7b;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameExt extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Tf_Msg1;
	private JTextField Tf_Msg2;
	private JTextField Tf_Msg3;
	private JTextField Tf_Count;
	private JTextField Tf_File;
	private JTextArea JpcTA;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameExt frame = new JFrameExt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public JFrameExt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel JPN = new JPanel();
		JPN.setBackground(Color.CYAN);
		contentPane.add(JPN, BorderLayout.NORTH);
		
		JLabel Msg1 = new JLabel("Msg1");
		JPN.add(Msg1);
		
		Tf_Msg1 = new JTextField();
		JPN.add(Tf_Msg1);
		Tf_Msg1.setColumns(10);
		
		JLabel Msg2 = new JLabel("Msg2");
		JPN.add(Msg2);
		
		Tf_Msg2 = new JTextField();
		JPN.add(Tf_Msg2);
		Tf_Msg2.setColumns(10);
		
		JLabel Msg3 = new JLabel("Msg3");
		JPN.add(Msg3);
		
		Tf_Msg3 = new JTextField();
		JPN.add(Tf_Msg3);
		Tf_Msg3.setColumns(10);
		
		JLabel Count = new JLabel("Count");
		JPN.add(Count);
		
		Tf_Count = new JTextField();
		JPN.add(Tf_Count);
		Tf_Count.setColumns(10);
		
		JLabel File = new JLabel("File Name");
		JPN.add(File);
		
		Tf_File = new JTextField();
		JPN.add(Tf_File);
		Tf_File.setColumns(10);
		
		JPanel JPC = new JPanel();
		JPC.setBorder(new EmptyBorder(30, 50, 30, 50));
		JPC.setBackground(Color.YELLOW);
		contentPane.add(JPC, BorderLayout.CENTER);
		JPC.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JPC.add(scrollPane, BorderLayout.CENTER);
		
		JpcTA = new JTextArea();
		JpcTA.setRows(5);
		JpcTA.setColumns(20);
		scrollPane.setViewportView(JpcTA);
		DefaultCaret caret = (DefaultCaret)JpcTA.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JPanel JPS = new JPanel();
		JPS.setBackground(Color.MAGENTA);
		contentPane.add(JPS, BorderLayout.SOUTH);
		
		JButton BtnNoSync = new JButton("No Sync");
		BtnNoSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) 
			{
				// create runnable objects
				NoSyncRunnable r1 = new NoSyncRunnable(Tf_Msg1.getText(), 10, Tf_File.getText(), JpcTA);
				NoSyncRunnable r2 = new NoSyncRunnable(Tf_Msg2.getText(), 10, Tf_File.getText(), JpcTA);
				NoSyncRunnable r3 = new NoSyncRunnable(Tf_Msg3.getText(), 10, Tf_File.getText(), JpcTA);
				
				//create Thread managers
				Thread t1 = new Thread(r1);
				Thread t2 = new Thread(r2);
				Thread t3 = new Thread(r3);
				
				//start threads
				t1.start();
				t2.start();
				t3.start();
					
			}
		});
		JPS.add(BtnNoSync);
		
		JButton BtnCompSync = new JButton("Comp Sync");
		BtnCompSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Object syncObject = new Object();
				// create runnable objects
				CompSyncRunnable r2 = new CompSyncRunnable(Tf_Msg1.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				CompSyncRunnable r1 = new CompSyncRunnable(Tf_Msg2.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				CompSyncRunnable r3 = new CompSyncRunnable(Tf_Msg3.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				
				//create Thread managers
				Thread t1 = new Thread(r1);
				Thread t2 = new Thread(r2);
				Thread t3 = new Thread(r3);
				
				//start threads
				t1.start();
				t2.start();
				t3.start();
				
			}
		});
		JPS.add(BtnCompSync);
		
		JButton BtnCoopSync = new JButton("Coop Sync");
		BtnCoopSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Object syncObject = new Object();
				// create runnable objects
				CoopSyncRunnable r2 = new CoopSyncRunnable(Tf_Msg1.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				CoopSyncRunnable r1 = new CoopSyncRunnable(Tf_Msg2.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				CoopSyncRunnable r3 = new CoopSyncRunnable(Tf_Msg3.getText(), 10, Tf_File.getText(), syncObject, JpcTA);
				
				//create Thread managers
				Thread t1 = new Thread(r1);
				Thread t2 = new Thread(r2);
				Thread t3 = new Thread(r3);
				
				//start threads
				t1.start();
				t2.start();
				t3.start();
			}
		});
		JPS.add(BtnCoopSync);
	}

}
