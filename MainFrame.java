import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


public class MainFrame extends JFrame{
    int flag = -1;
    public MainFrame(String title)
    {
        super(title);
        setLayout(new BorderLayout());
        this.setSize(300,120);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        int xpos = (dim.width/2) - (this.getWidth()/2);
        int ypos = (dim.height/2) - (this.getHeight()/2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(xpos, ypos);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JButton server = new RoundCornerButton("Server",new Dimension(90,40));
        JButton client = new RoundCornerButton("Client",new Dimension(90,40));
        server.setToolTipText("Create a Server and send data");
        client.setToolTipText("Create a Client and receive data");
        JPanel parent = new JPanel();
        parent.setBackground(new Color(242,154,26));
        JPanel child1 = new JPanel();
        child1.setBackground(new Color(242,154,26));
        JPanel child2 = new JPanel();
        child2.setBackground(new Color(242,154,26));
        child1.setLayout(new FlowLayout(FlowLayout.CENTER));
        child1.add(server);
        child1.add(client);
        JLabel jl = new JLabel("Created by soun059");
        child2.add(jl);
        parent.add(child1);
        parent.add(child2);
        this.add(parent);
        
        server.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainFrame.this.flag==-1){
                    ServerFrame fs = new ServerFrame("Server");
                    fs.setVisible(true);
                    fs.setSize(430,430);
                    MainFrame.this.flag=1;
                    fs.addWindowListener(new WindowAdapter(){
                        public void windowClosed(WindowEvent e) {
                            MainFrame.this.flag=-1;
                        }
                    });
            }
            }
        });
        client.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MainFrame.this.flag==-1){
                ClientFrame fs = new ClientFrame("Client");
                    fs.setVisible(true);
                    fs.setSize(300,300);
                    MainFrame.this.flag=1;
                    fs.addWindowListener(new WindowAdapter(){
                        public void windowClosed(WindowEvent e) {
                            MainFrame.this.flag=-1;
                        }
                    });
            }
        }
            });
    }
    }
