package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDSubsystem {
    private AddressableLED       led;
    private AddressableLEDBuffer ledBuffer;

    public LEDSubsystem() {
        // Initialize the LED strip on DIO 8
        led       = new AddressableLED(8);

        // Set the length of the LED strip
        ledBuffer = new AddressableLEDBuffer(60); // Number of LEDs on the LED strip

        led.setLength(ledBuffer.getLength());

        led.start(); // Start the LED control
    }

    public void setColor(int red, int green, int blue) {
        // This method sets all LEDs to 1 colour

        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, red, green, blue); // Set color for each LED
        }
        led.setData(ledBuffer);
    }
}
