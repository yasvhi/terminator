/*
  Copyright (c) 2015 Edina Terminators Robotics
  http://etrobotics.org/
  GitHub: https://github.com/EdinaTerminators/
  
  Developed by Richik SC and ...
*/
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class ETeleOp extends OpMode {
  
  DcMotor motorRight;
  DcMotor motorLeft;
  
  public ETeleOp() {
    
    
    
  }
  
  @Override // Overrides superclass OpMode method init
  public void init() {
    
    motorRight = hardwareMap.dcMotor.get("motor_2"); // Change to whatever motor name given
    motorLeft = hardwareMap.dcMotor.get("motor_1"); //  ''
    motorLeft.setDirection(DcMotor.Direction.REVERSE);
    
  }
  
  @Override // 26
  public void loop() {
    
    /*
      Gamepad sticks:
      Throttle / Y direction:
        Full up = -1
        Full down = 1
      Reversing needed, because forward as positive value makes sense.
      Steering / X direction:
        Full left = -1
        Full right = 1
    */
    
    float throttle = -gamepad1.left_stick_y;
    float steering = gampead1.left_stick_x;
    float right = throttle - direction;
    float left = throttle + direction;
    
  }
  
  @Override // 26
  public void stop() {
    
  }
  
  
}