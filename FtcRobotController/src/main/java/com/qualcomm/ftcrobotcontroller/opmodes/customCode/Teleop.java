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
        servoR.setPosition(0);
        servoL.setPosition(0);
        servoR.setDirection(Servo.Direction.REVERSE);
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
        //double servoPositionR = 0;
        //double servoPositionL = 0;

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
        //telemetry.addData("ServoR", servoPositionR);
        //telemetry.addData("ServoL", servoPositionL);
        Right.setPower(rightY * factor);
        Left.setPower(leftY * factor);

        if(gamepad1.b) {
          servoR.setPosition(0);
        }
        if(gamepad1.x) {
          servoR.setPosition(-0.3);
        }
        if(gamepad1.y) {
          servoL.setPosition(0);
        }
        if(gamepad1.a) {
          servoL.setPosition(-0.3);
        }
    }
}
