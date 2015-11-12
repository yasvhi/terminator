package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Autonomous extends LinearOpMode {
    private DcMotor right;
    private DcMotor left;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 1;     //gear ratio
    final static double WHEEL_DIAMETER = 2.625;     //diameter of wheel
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static int DISTANCE = 5;
    final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;
    final static int ERROR_THRESHOLD = 10;

    private void etSetup() throws InterruptedException {
      right = hardwareMap.dcMotor.get("Right");
      left = hardwareMap.dcMotor.get("Left");
      right.setDirection(DcMotor.Direction.REVERSE);

      right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
      left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
      telemetry.addData("Reset Encoders", "Done");
      //distance = 12;
      right.setTargetPosition((int) COUNTS);
      left.setTargetPosition((int) COUNTS);

      right.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
      left.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
      telemetry.addData("Running to Target", "Started");

      telemetry.addData("Motor Target", COUNTS);
      telemetry.addData("Left Position", left.getCurrentPosition());
      telemetry.addData("Right Position", right.getCurrentPosition());

      right.setPower(0.5);
      left.setPower(0.5);
      telemetry.addData("Setting Power", "Set");

      telemetry.addData("Motor Target", COUNTS);
      telemetry.addData("Left Position", left.getCurrentPosition());
      telemetry.addData("Right Position", right.getCurrentPosition());
    }
    
    private void etLoop() throws InterruptedException {
      telemetry.addData("Motor Target", COUNTS);
      telemetry.addData("Left Position", left.getCurrentPosition());
      telemetry.addData("Right Position", right.getCurrentPosition());
      int error = Math.abs(right.getCurrentPosition()) - (int) COUNTS;

      if (error < ERROR_THRESHOLD) {
          right.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
          left.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
          telemetry.addData("Final Reset", "Done");
          break;
      }
      //waitOneHardwareCycle();
    }
    
    @Override
    public void runOpMode() throws InterruptedException {
      
        etSetup();
        while (opModeIsActive()) { // && left.getCurrentPosition() == DISTANCE) {
        
          etLoop();
          
        }

    }
}
