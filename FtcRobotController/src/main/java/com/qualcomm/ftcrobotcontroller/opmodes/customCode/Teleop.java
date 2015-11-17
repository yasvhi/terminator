package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class Teleop extends OpMode {
    private DcMotor Right;
    private DcMotor Left;
    private Servo servoR;
    private Servo servoL;
    //private DcMotor backRight;
    //private DcMotor backLeft;

    @Override
    public void init() {
        Right = hardwareMap.dcMotor.get("Right");
        Left = hardwareMap.dcMotor.get("Left");
        servoR = hardwareMap.servo.get("ServoR");
        servoL = hardwareMap.servo.get("ServoL");
        //backRight = hardwareMap.dcMotor.get("Back_Right");
        //backLeft = hardwareMap.dcMotor.get("Back_Left");

        Right.setDirection(DcMotor.Direction.REVERSE);
        //backRight.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        boolean toggled = false;
        double factor = 0.25;
        double servoPosition = 0;

        if(gamepad1.right_bumper || gamepad1.left_bumper) {
            toggled = true;
        }
        telemetry.addData("Inverted Controls", (toggled ? "On":"Off"));
        if(toggled) {
            leftY = gamepad1.left_stick_y;
            rightY = gamepad1.right_stick_y;
        }

        if(gamepad1.right_trigger > 0.25)
            factor = gamepad1.right_trigger;

        telemetry.addData("Power Factor", factor);
        telemetry.addData("Servo", servoPosition);
        Right.setPower(rightY * factor);
        Left.setPower(leftY * factor);

        if(gamepad1.b) {
          if(gamepad1.dpad_up) {
            servoPosition++;
          }
          else if(gamepad1.dpad_down) {
            servoPosition--;
          }
          servoR.setPosition(servoPosition);
        }
        if(gamepad1.x) {
          if ((gamepad1.dpad_up)) {
            servoPosition++;
          }
          else if(gamepad1.dpad_down) {
            servoPosition--;
          }
          servoL.setPosition(servoPosition);
        }
    }
}
