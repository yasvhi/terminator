package com.qualcomm.ftcrobotcontroller.opmodes.customcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ETBaseOpMode extends LinearOpMode {

  public static final double DIST_FACTOR = 3.4;
  public static final double MOVE_FIRST_DISTANCE = 100 / DIST_FACTOR;
  public static final double MOVE_POWER = 0.25;
  public static final double MOVE_SECOND_DISTANCE = 24 / DIST_FACTOR;
  public static final double REVERSE_FIRST_DISTANCE = -27 / DIST_FACTOR;

  protected static int stage;
  private boolean loopBreaker;


  //CONTROL STAGES
  final static int STAGE_MOVE_FIRST = 1;
  final static int STAGE_MOVE_SECOND = 2;
  final static int STAGE_TURN_FIRST = 3;
  final static int STAGE_TURN_SECOND = 4;
  final static int STAGE_REVERSE_FIRST = 5;
  final static int STAGE_STOP = 100;

  public void etInit() throws InterruptedException {

    
  }
  public void etSetup() throws InterruptedException {
    stage = STAGE_MOVE_FIRST;
    loopBreaker = false;
  }
  public void etLoop() throws InterruptedException {
    
  }
  public void etCleanup()  throws InterruptedException {

  }

  public void etBreakLoop() throws InterruptedException {
    loopBreaker = true;
  }

  @Override
  public void runOpMode()  throws InterruptedException {
    etInit();
    waitForStart();
    etSetup();
    //int i = 0;
    while(opModeIsActive()) {
      //telemetry.addData("etLoop ", i++);
      etLoop();
      if(loopBreaker) {
        break;
      }
      waitOneFullHardwareCycle();
    }
    etCleanup();
    //this.stop();
  }
}