package pl.xxlo;

import com.badlogic.gdx.math.MathUtils;

public class Physics {
    static private float cX = 0f;
    static private float cY = 0f;


    public static void setCenter(float centerX, float centerY){
        cX = centerX;
        cY = centerY;
    }
    /* transform game angle left screen is 0
    * to libGDX rotate screen up is 0 left is +90 right -90
    * @param angle game angle positive 0 360 degree
    */
    public static float angleToScreen(float alpha) {
        float angle;
        if(alpha < 270f) angle = 90f - alpha;
        else angle = 450f - alpha;
        return angle;
    }
    /* count angle in game angle of clicked mouse
    *  @param centerX
     */
    public static float angleClicked(int x, int y) {
        float dx = (float) x - cX;
        float dy = (float) y - cY;
        float alpha = 180f + MathUtils.atan2(dy, dx) * 57.295779f ;
        //double alpha = (Math.atan2((double) dy, (double) dx) * (180.0 / Math.PI));
        if (alpha >= 360f ) return (alpha -  360f );
        else return alpha;
    }

    public  float testAtang(float dy, float dx) {
        return 180f + MathUtils.atan2(dy, dx) * 57.295779f ;
    }

    public static  void testSinDeg(){
        System.out.println(String.format("SIN 0 %f, 45 %f,  90 %f, 135 %f, 225 %f, 315 %f ",
                MathUtils.sinDeg(0f), MathUtils.sinDeg(45f),
                MathUtils.sinDeg(90f), MathUtils.sinDeg(135f),
                MathUtils.sinDeg(225f),MathUtils.sinDeg(315f))
        );

    }

}
