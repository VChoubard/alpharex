import lejos.nxt.*;

import java.io.File;
//import java.lang.reflect.Array;

public class Main {

    TouchSensor touchSensor1 = new TouchSensor(SensorPort.S1);
    TouchSensor touchSensor2 = new TouchSensor(SensorPort.S2);
    UltrasonicSensor ultraSensor = new UltrasonicSensor(SensorPort.S4);
    int[] colors = {ColorSensor.Color.RED, ColorSensor.Color.GREEN, ColorSensor.Color.BLUE, ColorSensor.Color.YELLOW};
    ColorSensor colorSensor = new ColorSensor(SensorPort.S3);



    public static void main(String[] args) throws InterruptedException {
        System.out.println("Press Enter to start");
        Button.ENTER.waitForPress();
        LCD.clear();
        Main main = new Main();

        Button.ESCAPE.addButtonListener(new ButtonListener() {
            public void buttonPressed(Button b) {
                System.exit(0);
            }

            public void buttonReleased(Button button) {

            }


        });
        main.go();
    }



    public void init(){
        Motor.B.setSpeed(180);
        Motor.C.setSpeed(180);
        Motor.A.setSpeed(180);

        Motor.B.forward();
	    do {
            Thread.yield();
	    } while (!touchSensor1.isPressed());
        Motor.B.stop();

        Motor.C.forward();
	    do {
            Thread.yield();
	    } while (!touchSensor2.isPressed());
        Motor.C.stop();
        Motor.C.rotate(180);
        Sound.playSample(new File("hola.wav"), 100);
    }

    public void go() {
        init();
        do {
            Thread.yield();
        } while (ultraSensor.getDistance() > 60);
        Motor.B.forward();
        Motor.C.forward();

        do{
            Thread.yield();
        }while (ultraSensor.getDistance() > 20);
        do {
            Thread.yield();
        } while (!touchSensor1.isPressed());
        Motor.B.stop();
        do {
            Thread.yield();
        } while (!touchSensor2.isPressed());
        Motor.C.stop();

        Motor.A.rotateTo(180);

        while(Button.ESCAPE.isUp()){
            do{
                System.out.println(colorSensor.getColorID());
                Thread.yield();
            }while(colorSensor.getColorID() ==  ColorSensor.Color.BLACK);

            if(colorSensor.getColorID() == ColorSensor.Color.RED){
                System.out.println("Yeah!!");
                Motor.B.rotateTo(180);
                Motor.C.rotateTo(180);
                Motor.B.rotateTo(-180);
                Motor.C.rotateTo(-180);
                Motor.B.rotateTo(180);
                Motor.C.rotateTo(180);
                Motor.B.rotateTo(-180);
                Motor.C.rotateTo(-180);
            } else {
                Motor.A.rotateTo(-360);
                Motor.A.rotateTo(180);
            }

        };





    }
}


//        Motor.A.setSpeed(720);
//        Motor.B.forward();
//        Motor.C.forward();
//        UltrasonicSensor sensor = new UltrasonicSensor(SensorPort.S3);
//        TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
//        TouchSensor touchSensor2 = new TouchSensor(SensorPort.S2);
//        ColorSensor colorSensor = new ColorSensor(SensorPort.S4);
//
//        while(Button.ESCAPE.isUp()) {
////            int colorId = colorSensor.getColorID();
////            System.out.println(colorId == ColorSensor.Color.RED);
////            if (colorId == ColorSensor.Color.RED) {
////                Motor.A.rotate(360);
////            }
//            float range = sensor.getRange();
//            Thread.sleep(20);
//            if (range < 50) {
//                Motor.B.setSpeed(360);
//                Motor.C.setSpeed(360);
//                Motor.C.backward();
//            } else if (touchSensor.isPressed() || touchSensor2.isPressed()) {
//                //Go backward
//                Motor.B.setSpeed(360);
//                Motor.C.setSpeed(360);
//                Motor.C.backward();
//                Motor.B.backward();
//                Thread.sleep(1000);
//
//                //Advice
//                Motor.C.stop();
//                Motor.B.stop();
//                colorSensor.setFloodlight(ColorSensor.Color.RED);
//                Sound.buzz();
//
//                //Shoot
//                Motor.A.rotate(360);
//                colorSensor.setFloodlight(ColorSensor.Color.NONE);
//
//                //Turn
//                Motor.C.backward();
//                Motor.B.forward();
//                Thread.sleep(1000);
//            } else {
//                Motor.B.setSpeed(900);
//                Motor.C.setSpeed(900);
//                Motor.C.forward();
//            }
//
//        }
//    }
//}