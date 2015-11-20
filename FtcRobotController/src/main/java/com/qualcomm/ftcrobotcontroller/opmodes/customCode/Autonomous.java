package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Autonomous extends ETBaseOpMode {
  private DcMotor right;
  private DcMotor left;

  final static int ENCODER_CPR = 1440;    //encoder counts per revolution
  final static double GEAR_RATIO = 1;     //gear ratio
  final static double WHEEL_DIAMETER = 2.625;     //diameter of wheel
  final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
  //int distance = 5;
  final static int ERROR_THRESHOLD = 10;
  final static int STAGE_MOVE_20 = 20;
  final static int STAGE_MOVE_10 = 10;
  final static int STAGE_TURN_LEFT = 90; // case 90 :)
  final static int STAGE_STOP = 100;

  private static boolean isTargetSet = false;
  private static double counts = 0;

  private static int stage;

  @Override
  public void etInit() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    right.setDirection(DcMotor.Direction.REVERSE);
  }

  @Override
  public void etSetup() throws InterruptedException {
    stage = STAGE_MOVE_20;

  }


  @Override
  public void etLoop() throws InterruptedException {
    /*
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());
    telemetry.addData("Right Motor Power", Double.toString(right.getPower()));
    telemetry.addData("Left Motor Power", Double.toString(left.getPower()));
*/

    telemetry.addData("stage", stage);

    switch (stage) {
      case STAGE_MOVE_20 :
      {
        counts = getCountsForDistance(20);
        telemetry.addData("stage 20", counts);

        setTarget();
        if(hasArrived(counts))
          stage = STAGE_TURN_LEFT;
        break;
      }
      case STAGE_MOVE_10: {
        counts = getCountsForDistance(10);

        setTarget();
        if(hasArrived(counts))
          stage = STAGE_STOP;
        break;

      }

      case STAGE_TURN_LEFT:{
        right.setPower(0.25);
        left.setPower(-0.25);
        sleep(3000);
        right.setPower(0);
        left.setPower(0);
        stage = STAGE_MOVE_10;
        break;
      }

      case STAGE_STOP: {
        stopRobot();
        break;
      }
    }
  }

  private boolean hasArrived(double cts) {
    //telemetry.addData("cts", cts);
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());
    telemetry.addData("cts", cts);

    int rightErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    int leftErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    if (leftErrorMargin < ERROR_THRESHOLD && rightErrorMargin < ERROR_THRESHOLD) {
      isTargetSet = false;
      telemetry.addData("hasArrived", cts);
      return true;
    }
    return false;

  }

  private void stopRobot() throws InterruptedException {
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();
    etBreakLoop();
  }

  private double getCountsForDistance(int distance) {
    double rotations = distance / CIRCUMFERENCE;
    return ENCODER_CPR * rotations * GEAR_RATIO;
  }

  private synchronized void  setTarget() throws InterruptedException {
    if(isTargetSet)
      return;

    //telemetry.addData("setTarget ", distance);

    //double cts = getCountsForDistance(distance);
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();
    telemetry.addData("Reset Encoders", "Done");

    right.setTargetPosition((int) counts);
    left.setTargetPosition((int) counts);
    right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    right.setPower(0.25);
    left.setPower(0.25);
    isTargetSet = true;
    //return cts;
  }

}
