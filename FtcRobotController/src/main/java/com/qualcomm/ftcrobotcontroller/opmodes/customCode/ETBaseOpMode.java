package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ETBaseOpMode extends LinearOpMode {
  private boolean loopBreaker;
  public void etInit() throws InterruptedException {
    
  }
  public void etSetup() throws InterruptedException {
    
  }
  public void etLoop() throws InterruptedException {
    
  }
  public void etCleanup() throws InterruptedException {

  }

  @Override
  public void runOpMode()  throws InterruptedException {
    etInit();
    waitForStart();
    etSetup();
    while(opModeIsActive()) {
      etLoop();
      waitForNextHardwareCycle();
    }
    etCleanup();
  }
}