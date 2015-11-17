package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Movement {
  private DcMotor right;
  private DcMotor left;

  final static int ENCODER_CPR = 1440;    //encoder counts per revolution
  final static double GEAR_RATIO = 1;     //gear ratio
  final static double WHEEL_DIAMETER = 2.625;     //diameter of wheel
  final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
  int distance;
  double counts;
  final static int ERROR_THRESHOLD = 10;

  public Movement(int distance, DcMotor right, DcMotor left) {
    this.distance = distance;
    this.right = right;
    this.left = left;
  }

  public void setup() throws InterruptedException {
    //right = hardwareMap.dcMotor.get("Right");
    //left = hardwareMap.dcMotor.get("Left");
    //right.setDirection(DcMotor.Direction.REVERSE);

    counts = getCountsForDistance(this.distance);
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    //telemetry.addData("Reset Encoders", "Done");
    right.setTargetPosition((int) counts);
    left.setTargetPosition((int) counts);

    right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    //telemetry.addData("Running to Target", "Started");

    //telemetry.addData("Motor Target", COUNTS);
    //telemetry.addData("Left Position", left.getCurrentPosition());
    //telemetry.addData("Right Position", right.getCurrentPosition());

    right.setPower(0.25);
    left.setPower(0.25);
    //telemetry.addData("Power Set", "true");

    //telemetry.addData("Motor Target", COUNTS);
    //telemetry.addData("Left Position", left.getCurrentPosition());
    //telemetry.addData("Right Position", right.getCurrentPosition());
  }


  public void loop() throws InterruptedException {
    // right.setPower(0.5);
    // left.setPower(0.5);
    //telemetry.addData("etLoop Left Position", left.getCurrentPosition());
    //telemetry.addData("etLoop Right Position", right.getCurrentPosition());
    // telemetry.addData("Right Motor Power", Double.toString(right.getPower()));
    // telemetry.addData("Right Motor Power", Double.toString(left.getPower()));
    //telemetry.addData("etLoop Motor Target", COUNTS);

    //int error = Math.abs(right.getCurrentPosition()) - (int) COUNTS;

    if (hasArrived(counts)) {
      right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
      //telemetry.addData("Stopping Robot", "Done");
      //stopRobot();
    }
  }

  private boolean hasArrived(double counts) {
    int absRight = Math.abs(right.getCurrentPosition());
    int absLeft = Math.abs(left.getCurrentPosition());
    int rigthErrorMargin = Math.abs(absRight - (int) counts);
    int leftErrorMargin = Math.abs(absLeft - (int) counts);
    //telemetry.addData("rigthErrorMargin", rigthErrorMargin);
    //telemetry.addData("leftErrorMargin", leftErrorMargin);
    //telemetry.addData("COUNTS", COUNTS);
    if(leftErrorMargin < ERROR_THRESHOLD && rigthErrorMargin < ERROR_THRESHOLD) {
      //telemetry.addData("hasArrived", "true");
      return true;
    }
    return false;

  }

  private void stopRobot() {
    //right.setPower(0);
    //left.setPower(0);
  }

  private double getCountsForDistance(int distance) {
    double rotations = distance / CIRCUMFERENCE;
    return ENCODER_CPR * rotations * GEAR_RATIO;
  }
}