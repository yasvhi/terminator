package com.qualcomm.ftcrobotcontroller.opmodes.customcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class TeleOp extends OpMode {
  private DcMotor Right;
  private DcMotor Left;
  //private Servo servoR;
  //private Servo servoL;
  //private Servo servoM;
  private DcMotor tapeMeasure;
  private DcMotor hook;

  @Override
  public void init() {
    Right = hardwareMap.dcMotor.get("Right");
    Left = hardwareMap.dcMotor.get("Left");
    tapeMeasure = hardwareMap.dcMotor.get("TapeMeasure");
    hook = hardwareMap.dcMotor.get("Hook");
    /*servoR = hardwareMap.servo.get("ServoR");
    servoL = hardwareMap.servo.get("ServoL");
    servoM = hardwareMap.servo.get("ServoM");
    servoR.setPosition(0.5);
    servoL.setPosition(0.5);
    servoR.setDirection(Servo.Direction.REVERSE);
    //backRight = hardwareMap.dcMotor.get("Back_Right");
    //backLeft = hardwareMap.dcMotor.get("Back_Left");
*/
    Right.setDirection(DcMotor.Direction.REVERSE);
    //backRight.setDirection(DcMotor.Direction.REVERSE);
    //servoM.setPosition(0.6);
  }

  @Override
  public void loop() {
    float leftY = -gamepad1.left_stick_y;
    float rightY = -gamepad1.right_stick_y;
    boolean toggled = false;
    double factor = 0.25;
    //double servoPositionR = 0;
    //double servoPositionL = 0;

    if(gamepad1.right_bumper || gamepad1.left_bumper)
      toggled = true;
    telemetry.addData("Inverted Controls", (toggled ? "On":"Off"));
    if(toggled) {
        leftY = gamepad1.left_stick_y;
        rightY = gamepad1.right_stick_y;
    }

    if(gamepad2.right_trigger > 0.25) {
      tapeMeasure.setPower(gamepad2.right_trigger);
    }

    if(gamepad2.left_trigger > 0.25) {
      hook.setPower(gamepad2.left_trigger);
    }

    if(gamepad1.right_trigger > 0.25)
      factor = gamepad1.right_trigger;

    telemetry.addData("Power Factor", factor);
    //telemetry.addData("ServoR", servoPositionR);
    //telemetry.addData("ServoL", servoPositionL);
    Right.setPower(rightY * factor);
    Left.setPower(leftY * factor);

  }
}
