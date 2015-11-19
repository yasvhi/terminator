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
  int distance = 5;
  double counts;
  final static int ERROR_THRESHOLD = 10;

  @Override
  public void etSetup() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    right.setDirection(DcMotor.Direction.REVERSE);

    //this.counts = getCountsForDistance(distance);

    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    runDistance();
    /*telemetry.addData("Reset Encoders", "Done");
    right.setTargetPosition((int) counts);
    left.setTargetPosition((int) counts);

    right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    telemetry.addData("Running to Target", "Started");

    telemetry.addData("Motor Target", counts);
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());

    right.setPower(0.25);
    left.setPower(0.25);
    telemetry.addData("Power Set", "true");
*/
    //telemetry.addData("Motor Target", COUNTS);
    //telemetry.addData("Left Position", left.getCurrentPosition());
    //telemetry.addData("Right Position", right.getCurrentPosition());
  }
  
  @Override
  public void etLoop() throws InterruptedException {
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());
    telemetry.addData("Right Motor Power", Double.toString(right.getPower()));
    telemetry.addData("Left Motor Power", Double.toString(left.getPower()));
    telemetry.addData("Motor Target", counts);

    //int error = Math.abs(right.getCurrentPosition()) - (int) COUNTS;

    if(!hasArrived(5))
      return;
    telemetry.addData("hasArrived 5", "true");
    right.setPower(0);
    left.setPower(0);
    stopRobot();
    this.distance = 10;
    distance = 10;
    runDistance();
    if (hasArrived(10)) {
      telemetry.addData("hasArrived 10", "true");
      right.setPower(0);
      left.setPower(0);
      stopRobot();
      telemetry.addData("Robot Stopped", "true");
      etBreakLoop();

    }
  }

  private boolean hasArrived(int dist) {
    double cts = getCountsForDistance(dist);
    int rightErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    int leftErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    if (leftErrorMargin < ERROR_THRESHOLD && rightErrorMargin < ERROR_THRESHOLD)
      return true;
    return false;

  }

  private void stopRobot() throws InterruptedException {
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();
  }

  private double getCountsForDistance(int distance) {
    double rotations = distance / CIRCUMFERENCE;
    return ENCODER_CPR * rotations * GEAR_RATIO;
  }

  private void runDistance() {
    this.counts = getCountsForDistance(distance);

    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    telemetry.addData("Reset Encoders", "Done");
    right.setTargetPosition((int) counts);
    left.setTargetPosition((int) counts);

    right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    telemetry.addData("Running to Target", "Started");

    telemetry.addData("Motor Target", counts);
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());

    right.setPower(0.25);
    left.setPower(0.25);
    telemetry.addData("Power Set", "true");
  }
}
