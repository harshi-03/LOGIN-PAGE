# LOGIN-PAGE
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MiniApp extends JFrame implements ActionListener{
    String userName=null,password=null;
    JPasswordField p,t2,t3;
    JButton b,b1,b2;
    JTextField t,t1;
    Checkbox cb,cb1;
    JLabel l1,l2,l3,l4,x,y,z,xy;
    JFrame f3=new JFrame("Registration");
    JFrame f2=new JFrame("Page After Logging in");
    MiniApp(){
        cb=new Checkbox("Show Password");
        cb.setBounds(100,105,150,28);
        cb1=new Checkbox("Show Password");
        cb1.setBounds(100,152,150,28);
        b=new JButton("Login");
        b.setBounds(100,145,90,25);
        b1=new JButton("Register");
        b1.setBounds(203,145,90,25);
        b2=new JButton("Submit");
        b2.setBounds(150,193,90,25);
        l1=new JLabel("Username");
        l1.setBounds(100, 8, 70, 20);
        l2=new JLabel("Password");
        l2.setBounds(100, 55, 70, 20);
        l3=new JLabel();
        l3.setBounds(150, 200, 400, 50);
        l4=new JLabel();
        x=new JLabel("Username");
        x.setBounds(100, 8, 70, 20);//Register Username
        y=new JLabel("Password");
        y.setBounds(100, 55, 70, 20);//Register Password
        z=new JLabel("Confirm Password");
        z.setBounds(100,102,150,20);//Confirm Register Password
        xy=new JLabel();
        p=new JPasswordField();
        p.setBounds(100, 75, 193, 28);//Login Password Field
        t=new JTextField();
        t.setBounds(100,27,193,28);//Login Text Field
        t1=new JTextField();
        t1.setBounds(100,27,193,28);
        t2=new JPasswordField();
        t2.setBounds(100, 75, 193, 28);
        t3=new JPasswordField();
        t3.setBounds(100,123,193,28);
        add(cb);
        add(l1);
        add(l2);
        add(p);
        add(t);
        add(b);
        add(b1);
        b2.addActionListener(this);
        f3.add(b2);
        f3.add(x);
        f3.add(y);
        f3.add(z);
        f3.add(t1);
        f3.add(t2);
        f3.add(t3);
        f3.add(cb1);
        f3.add(xy);
        b1.addActionListener(this);
        add(l4);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        cb1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                t2.setEchoChar((char) 0);
                t3.setEchoChar((char)0);
            }
            else {
                t2.setEchoChar('*');
                t3.setEchoChar('*');
            }
        });
        cb.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED)
                p.setEchoChar((char) 0);
            else
                p.setEchoChar('*');
        });
        b.addActionListener(this);
        f2.setBounds(450,270,400,400);
        f2.setLayout(null);
        f2.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                f2.dispose();
            }
        });
        f3.setBounds(450,270,400,400);
        f3.setLayout(null);
        f3.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                f3.dispose();
            }
        });
        setTitle("Login");
        setBounds(450,270,400,300);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] Args){
        new MiniApp();
    }
    public void actionPerformed(ActionEvent a){
        if((a.getActionCommand()).equals("Login")){
            l4.setText("");
            userName=t.getText();
            password=String.valueOf(p.getPassword());
			int k=0;
            try {
                k = check(userName,password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(k==0) {
                l4.setBounds(105,185,400,50);
                l4.setText("<html><pre>You haven't registered yet.</pre></html>");
            }
            else{
                if (k==2) {
                    f2.add(l3,BorderLayout.CENTER);
				    l3.setBounds(150,150,200,50);
                    t.setText("");p.setText("");
                    dispose();
                    l3.setText("Welcome "+userName+"");
                    f2.setVisible(true);
                }
                else {
                    l4.setBounds(65, 190, 400, 50);
                    l4.setText("<html><pre>\tWrong User Credentials.\nPlease enter correct user credentials.</pre></html>");
					t.setText("");
					p.setText("");
                }
            }
        }
        if((a.getActionCommand()).equals("Register")){
            l4.setText("");
            f3.setVisible(true);
			t.setText("");
			p.setText("");
			t1.setText("");
			t2.setText("");
			t3.setText("");

        }
        if((a.getActionCommand()).equals("Submit")){
			cb1.setState(false);
            l4.setText("");
            userName=t1.getText();password=String.valueOf(t2.getPassword());
            String cpassword=String.valueOf(t3.getPassword());
			int u=check(userName,password);
            int s=0,n=0,c=0;
            for(int i=0;i<password.length();i++){
                if(Character.isDigit(password.charAt(i)))
                    n++;
                if(Character.isUpperCase(password.charAt(i)))
                    c++;
                if(Character.isDigit(password.charAt(i))&&!Character.isLetter(password.charAt(i))&&!Character.isWhitespace(password.charAt(i)))
                    s++;
            }
            if((password.length()<9)||(n<1)||(c<1)||(s<1)){
                xy.setBounds(25,230,400,50);
                xy.setText("<html><pre>Password length must be more than 9 characters\nIt must be combination of alphabets(capital,small),\nnumber & special characters.</pre></html>");
                t2.setText("");t3.setText("");
            }
			else if(u==1){
				xy.setBounds(125,230,400,50);
				xy.setText("<html><pre>Username already exists</pre></html>");
				t1.setText("");t2.setText("");t3.setText("");
			}
            else if(password.equals(cpassword) && userName!=null) {
                store(userName,password);
				t1.setText("");
				t2.setText("");
				t3.setText("");
				f3.dispose();
            }
            else{
                xy.setBounds(125,230,400,50);
                xy.setText("<html><pre>Passwords do not match</pre></html>");
                t2.setText("");t3.setText("");
            }
        }
    }
    public int check(String u,String pass)  {
        FileReader fr;
        int f=0;
        BufferedReader br;
        try{
            fr=new FileReader("LoginData.txt");
            br=new BufferedReader(fr);
            String str;
            while((str=br.readLine())!=null){
				String decipher = new String(Base64.getDecoder().decode(str));
                String[] s=decipher.split("\\s");
                if(u.equals(s[0])){
                    f++;
                    if(pass.equals(s[1]))
                        f++;
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return f;
    }
    public void store(String str1,String str2){
        FileWriter fw;
        BufferedWriter bw;
        try{
            fw=new FileWriter("LoginData.txt",true);
            bw=new BufferedWriter(fw);
            String str;
            bw.newLine();
            str=str1+" "+str2;
            bw.write(Base64.getEncoder().encodeToString(str.getBytes()));
            bw.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
