import javax.swing.JFrame;

public class OyunEkrani{

    static int setWidth = 1920;
    static int setHeight =1080;

    public static void main(String[] args) {
        
        
        JFrame frame = new JFrame();
        
        frame.setResizable(false);
        
        frame.setFocusable(false);
       
        Oyun oyun = new Oyun();
       
        oyun.requestFocus();
       
        arkaplanharaket arkaplanhareket1 = new arkaplanharaket();
        
        oyun.requestFocus();
        
        oyun.addKeyListener(oyun);
        
       
        oyun.setFocusable(true);
        
        oyun.setFocusTraversalKeysEnabled(false);

        
        frame.add(oyun);
        
        frame.setTitle("Star Wars Andromeda");
       
        frame.setSize(setWidth,setHeight);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        frame.setResizable(false);
       
        frame.setVisible(true);
    }

}



