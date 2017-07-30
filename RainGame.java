import java.awt.Color;
import java.util.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;

public class RainGame extends GraphicsProgram {
   private static int SIZE = 5;
	public void run(){
		
MediaTools.loadAudioClip("CS106A/rain.mp3");
	ArrayList<GOval> drops = new ArrayList<GOval>();
	int ticks = 0;
	GOval rain = makeRains();
	drops.add(rain);
	
    while(true){
	for(int i = 0; i < drops.size(); i++){
		rain = drops.get(i);
		rain.move(0, 2);
	}
	pause(500);
	
	if(ticks % 5 == 0){
	rain = makeRains();
	drops.add(rain);
	}
	ticks++;
	
	
    }
	
	
	
		
	}
	
	
	public GOval makeRains(){
		int xCoordinates = RandomGenerator.getInstance().nextInt(0,getWidth());
       GOval rains = new GOval(xCoordinates,0 , SIZE, SIZE);
       rains.setFilled(true);
       rains.setFillColor(Color.BLUE);
       add(rains);
       return rains;
	}
	
	

}
