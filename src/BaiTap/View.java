package BaiTap;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tkdn;
	private JPasswordField mkdn;
	private JTextField tkdk;
	private JPasswordField mkdk;
	private Welcome w;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 561, 497);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(223, 28, 109, 32);
		panel_1.add(lblNewLabel);
		
		JLabel lblTiKhon = new JLabel("Tài khoản");
		lblTiKhon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTiKhon.setBounds(33, 122, 109, 32);
		panel_1.add(lblTiKhon);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtKhu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMtKhu.setBounds(33, 250, 109, 32);
		panel_1.add(lblMtKhu);
		
		tkdn = new JTextField();
		tkdn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tkdn.setBounds(242, 122, 219, 38);
		panel_1.add(tkdn);
		tkdn.setColumns(10);
		
		mkdn = new JPasswordField();
		mkdn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mkdn.setBounds(242, 246, 219, 38);
		panel_1.add(mkdn);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tk = tkdn.getText();
				String mk = encrypt(mkdn.getText()) ;
				String ten = null;
				String matkhau = null;
				if (tk.equals("")||mk.equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				else {
				try {
					Connection con = getConnection();
					String sql="select * from users where username=? and pass=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, tk);
					pst.setString(2, mk);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {	
						ten = rs.getString(1);
						matkhau = rs.getString(2);
					}
					con.commit();
					closeConnection(con);
					
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
				if (tk.equals(ten)&&mk.equals(matkhau)) 
					w = new Welcome();
				else {
					JOptionPane.showMessageDialog(null, "Thông tin đăng nhập không chính xác");
				}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(211, 339, 138, 38);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Đăng kí tài khoản mới");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(187, 410, 190, 20);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblngK = new JLabel("Đăng kí");
		lblngK.setHorizontalAlignment(SwingConstants.CENTER);
		lblngK.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblngK.setBounds(225, 30, 109, 32);
		panel.add(lblngK);
		
		JLabel lblTiKhon_1 = new JLabel("Tài khoản");
		lblTiKhon_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiKhon_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTiKhon_1.setBounds(35, 124, 109, 32);
		panel.add(lblTiKhon_1);
		
		tkdk = new JTextField();
		tkdk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tkdk.setColumns(10);
		tkdk.setBounds(244, 124, 219, 38);
		panel.add(tkdk);
		
		JLabel lblMtKhu_1 = new JLabel("Mật khẩu");
		lblMtKhu_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtKhu_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMtKhu_1.setBounds(35, 252, 109, 32);
		panel.add(lblMtKhu_1);
		
		mkdk = new JPasswordField();
		mkdk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mkdk.setBounds(244, 248, 219, 38);
		panel.add(mkdk);
		
		JButton btnngK = new JButton("Đăng kí");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tk = tkdk.getText();
				String mk = encrypt(mkdk.getText());
				if (mk.equals("")||tk.equals(""))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin");
				else {
					try {
						Connection con = getConnection();
						String sql="insert into users values (?,?)";
						PreparedStatement pst = con.prepareStatement(sql);
						pst.setString(1, tk);
						pst.setString(2, mk);
						int check = pst.executeUpdate();
						con.commit();
						closeConnection(con);
					} catch (SQLException x) {
						// TODO Auto-generated catch block
						x.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Đăng kí thành công");
					tkdk.setText("");
					mkdk.setText("");
				}
			}
		});
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnngK.setBounds(213, 341, 138, 38);
		panel.add(btnngK);
		
		JLabel lblNewLabel_1_1 = new JLabel("Trở lại đăng nhập");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(189, 412, 190, 20);
		panel.add(lblNewLabel_1_1);
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url ="jdbc:sqlserver://DESKTOP-KHNLTRA:1433;databaseName=BaiTap";
			String user ="sa";
			String pass ="123456789";
			con = DriverManager.getConnection(url,user,pass);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void closeConnection(Connection con ) {
		try {
			if (con!=null) {
				con.close();
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
	}
	 public static String encrypt(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] inputBytes = input.getBytes();
	            byte[] hashBytes = md.digest(inputBytes);
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : hashBytes) {
	                String hex = Integer.toHexString(0xFF & b);
	                if (hex.length() == 1) {
	                    hexString.append('0');
	                }
	                hexString.append(hex);
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
