import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import javax.swing.tree.*;



class ServerFrame extends JFrame{
    public ServerFrame(String title)
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
        JButton send = new RoundCornerButton("Send",new Dimension(100,30));
        JPanel parent = new JPanel();
        //parent.setBackground(Color.WHITE);
        Box child1 = Box.createHorizontalBox();
        Box child2 = Box.createHorizontalBox();
        Box child5 = Box.createHorizontalBox();
        JPanel child3 = new JPanel();
        JPanel child4 = new JPanel();
        JLabel file_name = new JLabel("Enter the filepath:");
        JTextArea filename = new JTextArea();
        filename.setColumns(30);
        filename.setRows(1);
        child1.add(file_name);
        child1.add(filename);
        JLabel port_name = new JLabel("Enter the Port Number:");
        JTextArea port = new JTextArea();
        JTextArea received_view = new JTextArea();
        received_view.setSize(200,300);
        received_view.setEditable(false);
        port.setColumns(20);
        port.setRows(1);
        child2.add(port_name);
        child2.add(port);
        child3.add(send);
        child4.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        child4.add(received_view);
        parent.add(child1);
        parent.add(child2);
        parent.add(child3);
        parent.add(child4);
        this.add(parent);
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(port.getText() == "" || filename.getText() !="" )
                    ServerFrame.this.dispose();
                received_view.setText(null);
                System.out.println("send is pressed");
                received_view.append("Sending File\n");
                try{
                    int porto = Integer.parseInt(port.getText());
                    ServerSocket sk = new ServerSocket(porto);
                    received_view.append("Waiting for Client\n");
                    Socket ss = sk.accept();
                    String path = filename.getText();
                    received_view.append("Client has been received\n");
                    File file = new File(path);
                    FileInputStream fs = new FileInputStream(file);
                    System.out.println(path);
                    DataInputStream bis = new DataInputStream(fs);
                    //int packets = (int)Math.ceil(((int)file.length())/1024);
                    //System.out.println(packets);
                    OutputStream os = ss.getOutputStream();
                    int cp;
                    byte[] b = new byte[100];
                    while((cp=bis.read(b))!=-1)
                    {
                        System.out.println("Sent: length:"+cp);
                        os.write(b,0,cp);
                        os.flush();
                    }
                    os.close();
                    bis.close();
                    fs.close();
                    ss.close();
                    sk.close();
                    }
                    catch(Exception e1)
                    {
                        System.out.println(e1.toString());
                        ServerFrame.this.dispose();
                    }
                 }
        });
    }
}