package com.qualcomm.ftcrobotcontroller.opmodes.customcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class TeleOp extends OpMode {
  private DcMotor Right;
  private DcMotor Left;
  private Servo mainRaise;
  private Servo climberR;
  //private Servo servoM;
  //private DcMotor tapeMeasure;
  //private DcMotor hook;

  @Override
  public void init() {
    Right = hardwareMap.dcMotor.get("Right");
    Left = hardwareMap.dcMotor.get("Left");
    //tapeMeasure = hardwareMap.dcMotor.get("TapeMeasure");
    //hook = hardwareMap.dcMotor.get("Hook");
    mainRaise = hardwareMap.servo.get("MainRaise");
    climberR = hardwareMap.servo.get("ClimberR");
    Left.setDirection(DcMotor.Direction.REVERSE);
    //backRight.setDirection(DcMotor.Direction.REVERSE);
    //servoM.setPosition(0.6);
    mainRaise.setPosition(0.5);
    climberR.setPosition(0.3);
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

    /*if(gamepad2.right_trigger > 0.25) {
      tapeMeasure.setPower(gamepad2.right_trigger);
    }

    if(gamepad2.left_trigger > 0.25) {
      hook.setPower(gamepad2.left_trigger);
    }
*/
    if(gamepad1.right_trigger > 0.25)
      factor = gamepad1.right_trigger;

    telemetry.addData("Power Factor", factor);
    //telemetry.addData("ServoR", servoPositionR);
    //telemetry.addData("ServoL", servoPositionL);
    Right.setPower(rightY * factor);
    Left.setPower(leftY * factor);

    if(gamepad2.left_bumper == true) {
      mainRaise.setPosition(1);
    }
    if(gamepad2.right_bumper == true) {
      mainRaise.setPosition(0.5);
    }

    if(gamepad2.a == true) {
      climberR.setPosition(0.03);
    }
    if(gamepad2.x == true) {
      climberR.setPosition(0.3);
    }
  }
}
