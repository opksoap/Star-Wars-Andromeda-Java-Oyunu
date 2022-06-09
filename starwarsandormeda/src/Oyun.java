import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
   
    private int x;                                                                                                                       
    private int y;                                                              

    public Ates(int x, int y) {
        this.x = x;                                                             
        this.y = y;                                                             
    }

    public int getX() {                                                         
        return x;
    }

    public void setX(int x) {                                                   
        this.x = x;
    }

    public int getY() {                                                         
        return y;
    }

    public void setY(int y) {                                                   
        this.y = y;
    }

}
class dusmanates{
    private int x;
    private int y;
    
    public dusmanates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {                                                         
        return x;
    }

    public void setX(int x) {                                                   
        this.x = x;
    }

    public int getY() {                                                         
        return y;
    }

    public void setY(int y) {                                                   
        this.y = y;
    }   
}

public class Oyun extends JPanel implements KeyListener,ActionListener{         // extends -> KeyLÄ°stener kÃ¼tÃ¼phanesindeki parametreleri panel iÃ§ine yazÄ±lmasa bile panel iÃ§inde Ã§alÄ±ÅŸtÄ±rmak iÃ§in kullanÄ±lÄ±r aynÄ±sÄ± ActionListener iÃ§inde geÃ§erlidir.
                                                                                //implements -> aÅŸaÄŸÄ±da kullandÄ±ÄŸÄ±mÄ±z parametrelerin deÄŸerlerini tekrardan yazar
    Timer timer = new Timer(1,this);                                            // oyunun saniyedeki akÄ±ÅŸ hÄ±zÄ±
    
    private int gecen_sure = 0;
    private int harcanan_ates = 0;
    
    private BufferedImage uzayg;                                                
    private BufferedImage resim1;
    private BufferedImage resim2;
    private BufferedImage dusman;
    
    static int backgroundY1=10;
    static int backgroundY2=10000;
    static int backgroundSpeed=5;
    
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    private ArrayList<dusmanates> datesler = new ArrayList<dusmanates>();
    
    private int dusmanatesdirY = 10;
    private int atesdirY =10;                                                   
    
    private int dusmanY= 0;
    private int dusmandirY = 10;
    private int dusmanX = 1865;                                                       
    private int dusmandirX = 10;   
    
    private int uzayGemisiY = 1000;
    private int dirUzayY = 10;
    private int uzayGemisiX = 0;                                                
    private int dirUzayX = 10;                                                  
   
    
    private final HashSet<Integer> pressedKeys = new HashSet<>();
                                              
    
    public boolean kontrolEt(){
        for(Ates ates : new ArrayList<>(atesler)){                                               
           if(new Rectangle(ates.getX(),ates.getY(),7,15).intersects(new Rectangle(dusmanX,dusmanY,dusman.getWidth(),dusman.getHeight()))){
                return true;    
            }
        }
        for(dusmanates dusmanatess : new ArrayList<>(datesler)){                                               
           if(new Rectangle(dusmanatess.getX(),dusmanatess.getY(),7,15).intersects(new Rectangle(uzayGemisiX,uzayGemisiY,uzayg.getWidth(),uzayg.getHeight()))){
                return true;    
            }
        }
        return false; 
    }
    
    public Oyun() {
        
        setFocusable(true);
      
        try {
            uzayg = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
                resim1 = ImageIO.read(new File("rsc/uzayboslugu.png"));
                resim2 = ImageIO.read(new File("rsc/uzayboslugu.png"));  
        } catch(IOException e){  e.printStackTrace(); }
        try{
            dusman = ImageIO.read(new FileImageInputStream(new File("dusman.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer.start();
  
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure += 5;
        
        g.drawImage(resim1,0,backgroundY1,resim1.getWidth(),resim1.getHeight(),this);
        g.drawImage(resim2,0,backgroundY2,resim2.getWidth(),resim2.getHeight(),this);
        g.drawImage(uzayg,uzayGemisiX,uzayGemisiY,uzayg.getWidth(),uzayg.getHeight(),this);
        g.drawImage(dusman,dusmanX,dusmanY,dusman.getWidth(),dusman.getHeight(), this);
        
        for (dusmanates dusmanatess : new ArrayList<>(datesler)){
            if(dusmanatess.getY()>1080){
                datesler.remove(dusmanatess);
            }
        }
   
        g.setColor(Color.GREEN);
        for (dusmanates dusmanatess : new ArrayList<>(datesler)){
            
            g.fillRect(dusmanatess.getX(), dusmanatess.getY(), 7, 15);  
        }
        
        for (Ates ates : new ArrayList<>(atesler)){
            if(ates.getY()<0){
                atesler.remove(ates);
            }
        }
   
        g.setColor(Color.YELLOW);
        for (Ates ates : new ArrayList<>(atesler)){
            
            g.fillRect(ates.getX(), ates.getY(), 7, 15);  
        }
        if(kontrolEt()){
            timer.stop();
            String message = "Kazandınız...\n"+
                    " Harcanan Ateş : " + harcanan_ates +
                    " Geçen süre : "+ gecen_sure/300 +" Saniye";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        pressedKeys.add(e.getKeyCode());
        Point offset = new Point();
        
            if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                int c = it.next();
                        if(c == KeyEvent.VK_UP){
            if(uzayGemisiY <= 0){
                uzayGemisiY = 0;
            }
            else{
                uzayGemisiY -= dirUzayY;
            }
            }
        if(c == KeyEvent.VK_DOWN){
            if(uzayGemisiY >= 1000){
                uzayGemisiY = 1000;
            }
            else{
                uzayGemisiY += dirUzayY;
            }
            }
        
        if(c == KeyEvent.VK_LEFT){
            if(uzayGemisiX <= 0){
                uzayGemisiX = 0;
            }
            else{
                uzayGemisiX -= dirUzayX;
                
            }
            
        }
        else if(c == KeyEvent.VK_RIGHT){
            if(uzayGemisiX >=1820){ 
                uzayGemisiX = 1820; 
                
            }
            else{
                uzayGemisiX += dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_SPACE) {
                atesler.add(new Ates(uzayGemisiX,uzayGemisiY+40));
                atesler.add(new Ates(uzayGemisiX+40,uzayGemisiY+40));
                
                harcanan_ates +=2; 
                } 

        if(c == KeyEvent.VK_W){
            if(dusmanY <= 0){
                dusmanY = 0;
            }
            else{
                dusmanY -= dusmandirY;
            }
            }
        if(c == KeyEvent.VK_S){
            if(dusmanY >= 1000){
                dusmanY = 1000;
            }
            else{
                dusmanY += dusmandirY;
            }
            }
        
        if(c == KeyEvent.VK_A){
            if(dusmanX <= 0){
                dusmanX = 0;
            }
            else{
                dusmanX -= dusmandirX;
            }
            
        }
        else if(c == KeyEvent.VK_D){
            if(dusmanX >=1820){ 
                dusmanX = 1820; 
                
            }
            else{
                dusmanX += dusmandirX;
            }
        }
        else if(c == KeyEvent.VK_X) {
                datesler.add(new dusmanates(dusmanX,dusmanY+40));
                datesler.add(new dusmanates(dusmanX+40,dusmanY+40));
                
                harcanan_ates +=2; 
                } 
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (dusmanates dusmanatess : new ArrayList<>(datesler)){
            dusmanatess.setY(dusmanatess.getY()+dusmanatesdirY);
        }
        for (dusmanates dusmanatess : new ArrayList<>(datesler)){
            dusmanatess.setY(dusmanatess.getY()+dusmanatesdirY);
        }
        for (Ates ates : new ArrayList<>(atesler)){
            ates.setY(ates.getY()-atesdirY);
        }
        for (Ates ates : new ArrayList<>(atesler)){
            ates.setY(ates.getY()-atesdirY);
        }
        
        repaint(); 
    } 
}