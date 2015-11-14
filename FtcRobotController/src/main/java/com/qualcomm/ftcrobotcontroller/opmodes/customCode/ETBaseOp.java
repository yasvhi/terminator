package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ETBaseOpMode extends LinearOpMode {
  public void etInit() throws InterruptedException {
    
  }
  public void etSetup() throws InterruptedException {
    
  }
  public void etLoop() throws InterruptedException {
    
  }
  @Override
  public void runOpMode()  throws InterruptedException {
    etInit();
    waitForStart();
    etSetup();
    while(opModeIsActive()) {
      etLoop();
    }
  }
}