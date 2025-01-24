package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;

public class LEDSubsystem {

    private AddressableLED led;
    private AddressableLEDBuffer ledBuffer;
    private final LEDPattern green = LEDPattern.solid(Color.kGreen);

    public LEDSubsystem() {
        // Changed the LED strip to PWM 2
        led = new AddressableLED(2);
        ledBuffer = new AddressableLEDBuffer(60); // Number of LEDs on the LED strip
    }

    //@Override
    public void periodic() {
        green.applyTo(ledBuffer);
        led.setData(ledBuffer);
        led.start(); // Start the LED control
        System.out.println("Set LED Color green");
    }
    /*public void setColor(int red, int green, int blue) {
        // This method sets all LEDs to 1 colour

        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, red, green, blue); // Set color for each LED
        }
        led.setData(ledBuffer);
    }*/
}
