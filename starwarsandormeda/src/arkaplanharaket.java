import java.util.Timer;
import java.util.TimerTask;

public class arkaplanharaket {
    
    Timer hareket;
    
    public arkaplanharaket(){
    
        hareket = new Timer();
        
        hareket.scheduleAtFixedRate(new TimerTask(){

                @Override
            public void run() {
                
                if(Oyun.backgroundY1<1920){
                    Oyun.backgroundY1+=2;
                }
                else{
                    Oyun.backgroundY1=-1920;
                }
                if(Oyun.backgroundY2<1920){
                    Oyun.backgroundY2+=2;
                }
                else{
                    Oyun.backgroundY2=-1920;
                }
            }
        } ,0,Oyun.backgroundSpeed);
    }
}
