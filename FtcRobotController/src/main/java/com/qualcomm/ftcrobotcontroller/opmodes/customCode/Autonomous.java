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
  int distance = 20;
  double counts;
  final static int ERROR_THRESHOLD = 10;

  private static boolean isTargetSet = false;

  @Override
  public void etSetup() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    right.setDirection(DcMotor.Direction.REVERSE);

  }



  @Override
  public void etLoop() throws InterruptedException {
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());
    telemetry.addData("Right Motor Power", Double.toString(right.getPower()));
    telemetry.addData("Left Motor Power", Double.toString(left.getPower()));
    telemetry.addData("Motor Target", counts);



    // go 20 and then stop
      // setTarget for 20

    setTarget(20);
    // goToTarget()
    goToTarget();
    // loop until 20 is reached
    if(!hasArrived(20))
      return;
    telemetry.addData("hasArrived 20", "true");

    // go to 10 and then stop
    setTarget(10);
    // goToTarget()
    goToTarget();
    // loop until 20 is reached
    if(!hasArrived(10))
      return;
    telemetry.addData("hasArrived 10", "true");
    stopRobot();


  }

  private boolean hasArrived(int dist) {
    double cts = getCountsForDistance(dist);
    int rightErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    int leftErrorMargin = Math.abs(Math.abs(right.getCurrentPosition()) - (int) cts);
    if (leftErrorMargin < ERROR_THRESHOLD && rightErrorMargin < ERROR_THRESHOLD) {
      isTargetSet = false;
      return true;
    }
    return false;

  }

  private void stopRobot() throws InterruptedException {
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    etBreakLoop();
  }

  private double getCountsForDistance(int distance) {
    double rotations = distance / CIRCUMFERENCE;
    return ENCODER_CPR * rotations * GEAR_RATIO;
  }

  private void setTarget(int distance) {
    if (isTargetSet) {
      return;
    }
    double counts = getCountsForDistance(distance);
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    telemetry.addData("Reset Encoders", "Done");

    right.setTargetPosition((int) counts);
    left.setTargetPosition((int) counts);
    right.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    left.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
    isTargetSet = true;
  }

  private void goToTarget() {

    telemetry.addData("Running to Target", "Started");

    telemetry.addData("Motor Target", counts);
    telemetry.addData("Left Position", left.getCurrentPosition());
    telemetry.addData("Right Position", right.getCurrentPosition());

    right.setPower(0.25);
    left.setPower(0.25);
    telemetry.addData("Power Set", "true");
  }
}
