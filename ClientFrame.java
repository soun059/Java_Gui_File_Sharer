import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;


class ClientFrame extends JFrame{
    public ClientFrame(String title)
    {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(1000,200);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xpos = (dim.width/2) - (this.getWidth()/2);
        int ypos = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(xpos, ypos);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JButton receive = new RoundCornerButton("Receive",new Dimension(100,30));
        JPanel parent = new JPanel();
        //parent.setBackground(Color.WHITE);
        Box child1 = Box.createHorizontalBox();
        Box child2 = Box.createHorizontalBox();
        Box child5 = Box.createHorizontalBox();
        JPanel child3 = new JPanel();
        JPanel child4 = new JPanel();
        JLabel ip_name = new JLabel("Enter the server IP Address:");
        JTextArea ip = new JTextArea();
        ip.setColumns(10);
        ip.setRows(1);
        JLabel file_name = new JLabel("Enter the filename:");
        JTextArea filename = new JTextArea();
        filename.setColumns(10);
        filename.setRows(1);
        child1.add(ip_name);
        child1.add(ip);
        JLabel port_name = new JLabel("Enter the server Port Number:");
        JTextArea port = new JTextArea();
        JTextArea received_view = new JTextArea();
        received_view.setSize(200,300);
        received_view.setEditable(false);
        JScrollPane scroll = new JScrollPane(received_view,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        port.setColumns(10);
        port.setRows(1);
        child2.add(port_name);
        child2.add(port);
        child3.add(receive);
        child4.setLayout(new FlowLayout(FlowLayout.LEFT,70,20));
        child4.add(received_view);
        child4.add(scroll);
        child5.add(file_name);
        child5.add(filename);
        parent.add(child1);
        parent.add(child2);
        parent.add(child5);
        parent.add(child3);
        parent.add(child4);
        this.add(parent);
        receive.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ip.getText() != "" || port.getText() != "" || filename.getText() !="")
                    ClientFrame.this.dispose();
                received_view.setText(null);
                received_view.append("Receiving File\n");
                 try{
                     received_view.append("Keep Patience...Creating Socket\n");
                     String ipo = ip.getText();
                     int porto = Integer.parseInt(port.getText());
                     String path = System.getProperty("user.dir")+"\\"+filename.getText();
                     System.out.println("Ip:"+ipo+" Port:"+porto+" Path:"+path);
                     Socket sk = new Socket(ipo,porto);
                     received_view.append("Connected to the server\n");
                     FileOutputStream fs = new FileOutputStream(path);
                     DataOutputStream bos = new DataOutputStream(fs);
                     InputStream is = sk.getInputStream();
                     int count;
                     byte b[] = new byte[100];
                     while((count = is.read(b))!=-1){
                         bos.write(b,0,count);
                         System.out.println(count);
                     }
                    received_view.append("File received successfully\n");
                    received_view.append("Enjoy Your Share\n");
                     bos.close();
                     fs.close();
                     is.close();
                     sk.close();
                     }
                     catch(Exception e1)
                     {
                         System.out.println(e1.toString());
                         ClientFrame.this.dispose();
                     }
                 }
        });
    }
}