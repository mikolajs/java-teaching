package pl.edu.osp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.edu.osp.data.ShopingList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataJsonTest {

	@Test
	public void properParseJSON() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ShopingList sl = objectMapper.readValue(new File("goods.json"), ShopingList.class);
		assertEquals(sl.getMail(), "myemail@my.io");
		assertEquals(sl.getTimestamp(), 1523131885762L);
		String[] goods = new String[] { "chleb", "mleko", "masło", "mydło", "zielone pomidory", "żółta łódź podwodna" };
		assertArrayEquals(sl.getShopingList(), goods);
	}
	

}
