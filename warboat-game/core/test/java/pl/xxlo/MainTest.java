package pl.xxlo;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import pl.xxlo.*;

public class MainTest {
    @Before
    public void prepare(){
        Physics.setCenter(200f, 200f);
    }

    @Test
    public void goodAngelShouldBeCountedForSreen() {
        //Phisics Physics = new Phisics(200f, 200f);
        float angel = Physics.angleToScreen(10f);
        assertTrue("Plain +",angel == 80f);
        angel = Physics.angleToScreen(0f);
        assertTrue("Plain -",angel == 90f);
        angel = Physics.angleToScreen(90f);
        assertTrue("Plain -",angel == 0f);
        angel = Physics.angleToScreen(180f);
        assertTrue("Big -",angel == -90f);
        angel = Physics.angleToScreen(200f);
        assertTrue("Big +",angel == -110f);
        angel = Physics.angleToScreen(100f);
        assertTrue("Big +",angel == -10f);
        angel = Physics.angleToScreen(270f);
        assertTrue("Big +",angel == 180f);
        angel = Physics.angleToScreen(280f);
        assertTrue("Big +",angel == 170f);
        angel = Physics.angleToScreen(350f);
        assertTrue("Big +",angel == 100f);
        angel = Physics.angleToScreen(315f);
        assertTrue("Big +",angel == 135f);
    }


    @Test
    public void  shouldCountProperAngelForWeaponAfterClick45(){
        float angle;
        //Phisics Physics = new Phisics(100f, 100f);
        //quaters of catesians axis in center with parameters
        // screen  angel where up id 0 angle, x, y as left up corner
        angle = Physics.angleClicked( 400, 0 );
        assertEquals("1 quarter", angle, 135.0f, 0.5f);
        angle = Physics.angleClicked( 400, 400 );
        assertEquals("2 quarter", angle, 225.0f, 0.5f);
        angle = Physics.angleClicked( 0, 400 );
        assertEquals("3 quarter", angle, 315.0f, 0.5f);
        angle = Physics.angleClicked( 0, 0 );
        assertEquals("4 quarter", angle, 45.0f, 0.5f);
    }

    @Test
    public void  shouldCountProperAngelForWeaponAfterClickStrightAngle(){
        //quaters of catesians axis in center with parameters
        // screen  angel where up id 0 angle, x, y as left up corner
        float angle = Physics.angleClicked( 0, 200 );
        assertEquals("1 quarter", angle, 0.0f, 0.5f);
        angle = Physics.angleClicked(200, 0 );
        assertEquals("2 quarter", angle, 90.0f, 0.5f);
        angle = Physics.angleClicked(400, 200 );
        assertEquals("3 quarter", angle, 180.0f, 0.5f);
        angle = Physics.angleClicked( 200, 400 );
        assertEquals("4 quarter", angle, 270.0f, 0.5f);
    }

    @Test
    public void  shouldCountProperAngelForWeaponAfterClick60(){
        float angle;
        //quaters of catesians axis in center with parameters
        // screen  angel where up id 0 angle, x, y as left up corner
        ////!!!!!!!!!!!!!!!! not implemented count x adn y for 60 degree
        angle = Physics.angleClicked(84, 0 );
        assertEquals("1 quarter", angle, 60.0f, 1f);
        angle = Physics.angleClicked(300, 142 );
        assertEquals("2 quarter", angle, 150.0f, 1f);
        angle = Physics.angleClicked(258, 300 );
        assertEquals("3 quarter", angle, 240.0f, 1f);
        angle = Physics.angleClicked( 100, 258 );
        assertEquals("4 quarter", angle, 330.0f, 0.5f);
    }
}