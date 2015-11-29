/*
 * Copyright (c) 2015 Edina Terminators Robotics
 *
 * This software is distributed under the MIT License. The license text can be read in full at /LICENSE.txt
 * Authored by:
 * Luke Langefels <https://eku952@github.com>
 * Richik Sinha Choudhury <https://richiksc@github.com>
 */

package com.qualcomm.ftcrobotcontroller.opmodes.customcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class AutonomousRed extends ETBaseOpMode {
  private DcMotor right;
  private DcMotor left;

  final static int ENCODER_CPR = 1440;    //encoder counts per revolution
  final static double GEAR_RATIO = 1;     //gear ratio
  final static double WHEEL_DIAMETER = 2.625;     //diameter of wheel
  final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
  //int distance = 5;
  final static int ERROR_THRESHOLD = 10;


  private static boolean isTargetSet = false;
  private static double counts = 0;
//  private static boolean isTurnTargetSet = false;

  @Override
  public void etInit() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    left.setDirection(DcMotor.Direction.REVERSE);
    /*waitOneFullHardwareCycle();
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();*/
  }

  @Override
  public void etLoop() throws InterruptedException {

    telemetry.addData("stage", stage);

    switch (stage) {
      case STAGE_MOVE_FIRST:
      {
        counts = getCountsForDistance(MOVE_FIRST_DISTANCE);
        telemetry.addData("stage 20", counts);

        setTarget(MOVE_POWER);
        if(hasArrived(counts))
          stage = STAGE_REVERSE_FIRST;
        break;
      }

      case STAGE_REVERSE_FIRST: {
        // goes -6 dst.
        counts = getCountsForDistance(REVERSE_FIRST_DISTANCE);
        setTarget(MOVE_POWER * -1);
        if(hasArrived(counts)) {
          telemetry.addData("hasArrived Reverse", "true");
          stage = STAGE_TURN_FIRST;
        }
        break;
      }

      case STAGE_TURN_FIRST: {
        // turns right
        turn();
        stage = STAGE_MOVE_SECOND;
        break;
      }

      case STAGE_MOVE_SECOND : {
        counts = getCountsForDistance(MOVE_SECOND_DISTANCE);
        setTarget(MOVE_POWER);
        if(hasArrived(counts))
          stage = STAGE_STOP;
        break;
      }

      case STAGE_STOP: {
        stopRobot();
        break;
      }
    }
  }

  private void turn() throws InterruptedException {
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();
    right.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    left.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    right.setPower(-0.25);
    left.setPower(0.25);
    telemetry.addData("Started Turning leftSetPower0, power:", left.getPower());
    sleep(750);

  }

  private boolean hasArrived(double cts) {
    //telemetry.addData("cts", cts);
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());
    telemetry.addData("cts", cts);
    double absCts = Math.abs(cts);


    int rightErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) absCts);
    int leftErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) absCts);
    if (leftErrorMargin < ERROR_THRESHOLD && rightErrorMargin < ERROR_THRESHOLD) {
      isTargetSet = false;
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

  private double getCountsForDistance(double distance) {
    double rotations = distance / CIRCUMFERENCE;
    return ENCODER_CPR * rotations * GEAR_RATIO;
  }

  private synchronized void  setTarget(double power) throws InterruptedException {
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
    right.setPower(power);
    left.setPower(power);
    isTargetSet = true;
    //return cts;
  }

}
