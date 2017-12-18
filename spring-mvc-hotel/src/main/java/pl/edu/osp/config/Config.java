package pl.edu.osp.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.osp.api.*;
import pl.edu.osp.impl.*;

@Configuration
public class Config {
	@Bean
	public IRoom room() {
		ArrayList<RoomOption> rooms = new ArrayList<RoomOption>();
		rooms.add(new RoomOption( 2, 40));
		rooms.add(new RoomOption(3, 45));
		rooms.add(new RoomOption(2, 40));
		return new Room(1, "Jaśminowy", rooms);
	}
}
