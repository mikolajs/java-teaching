package pl.edu.osp.Hotel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pl.edu.osp.api.IRoom;
import pl.edu.osp.config.Config;
import pl.edu.osp.impl.Room;

public class App {

    public static void main(String[] args) {
        
    	AnnotationConfigApplicationContext context = 
    			new AnnotationConfigApplicationContext(Config.class);
    	IRoom room = context.getBean(Room.class);
    	System.out.println(room.getRoomOptions());
    }

}
