package com.wyps.ui;

import com.wyps.entity.User;
import com.wyps.entity.tools.DBUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends JFrame implements ActionListener, MouseListener {

    private JPanel jp = new JPanel();// 创建用来存放空间的容器
    private JLabel jl1 = new JLabel("用    户    名");
    private JLabel jl2 = new JLabel("密         码");
    private JLabel jl3 = new JLabel("");// 正在登陆提示标签
    private JLabel registerLR = new JLabel("注册账号");
    private JTextField jtf = new JTextField();
    private JPasswordField jpwf = new JPasswordField();
    private DBUtil db = new DBUtil();
    // 创建组
    private ButtonGroup bg = new ButtonGroup();
    // 创建操作按钮
    private JButton jb1 = new JButton("登    陆");
    private JButton jb2 = new JButton("重    置");

    // 构造器
    public Login() {
        this.addListener();
        initialFrame();// 初始化界面
    }

    public void addListener() {
        this.jb1.addActionListener(this);// 为登陆按钮注册监听器
        this.jb2.addActionListener(this);// 为重置按钮注册监听器
        this.jtf.addActionListener(this);// 为用户名文本框注册监听器
        this.jpwf.addActionListener(this);// 为用户名密码框注册监听器
    }

    public void initialFrame() {
        // 设为空布局
        jp.setLayout(null);
        // 将控件添加到容器相应位置

        this.jl1.setBounds(30, 20, 110, 25);
        this.jp.add(jl1);
        this.jtf.setBounds(120, 20, 130, 25);
        this.jp.add(jtf);
        this.jl2.setBounds(30, 60, 110, 25);
        this.jp.add(jl2);
        this.jpwf.setBounds(120, 60, 130, 25);
        this.jpwf.setEchoChar('*');
        this.jp.add(jpwf);
        this.jb1.setBounds(35, 110, 100, 30);
        this.jp.add(jb1);
        this.jb2.setBounds(150, 110, 100, 30);
        this.jp.add(jb2);
        jl3.setForeground(Color.red);
        this.jl3.setBounds(40, 145, 150, 25);
        registerLR.setForeground(Color.BLUE);
        registerLR.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLR.setBounds(200, 150, 80, 25);
        registerLR.addMouseListener(this);
        this.jp.add(jl3);
        this.jp.add(registerLR);
        this.add(jp);
        // 设置窗口的标题、大小、位置以及可见性
        this.setTitle("登陆");
        Image image = new ImageIcon(this.getClass().getResource(
                "/asset/ico.jpg")).getImage();
        this.setIconImage(image);
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;
        int w = 300;// 本窗体宽度
        int h = 220;// 本窗体高度
        this.setBounds(centerX - w / 2, centerY - h / 2 - 100, w, h);// 设置窗体出现在屏幕中央
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }

    // 实现ActionListener接口中的方法
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jb1) {// 按下登陆按钮
            User u = new User();
            u.setUsername(jtf.getText());
            u.setPassword(jpwf.getText());
            jl3.setText("正在登录....");
            u = this.db.login(u);
            if (u.isLogSuc()) {
                new MainFrame(u);
                this.dispose();
            } else {
                jl3.setText("用户名密码错误");
            }
        } else if (e.getSource() == this.jb2) {// 按下重置按钮,清空输入信息
            this.jtf.setText("");
            this.jpwf.setText("");
        } else if (e.getSource() == jtf) {// 当输入用户名并回车时
            this.jpwf.requestFocus(true);
        } else if (e.getSource() == jpwf) {// 当输入密码并回车时
            this.jb1.requestFocus(true);
        }
    }

    public static void main(String args[]) {
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(windows);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 创建登陆窗体对象
        Login login = new Login();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.dispose();
        new Register().setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
