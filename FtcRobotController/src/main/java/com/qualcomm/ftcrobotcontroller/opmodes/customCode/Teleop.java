package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Teleop extends OpMode {
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    @Override
    public void init() {
        frontRight = hardwareMap.dcMotor.get("Front_Right");
        frontLeft = hardwareMap.dcMotor.get("Front_Left");
        backRight = hardwareMap.dcMotor.get("Back_Right");
        backLeft = hardwareMap.dcMotor.get("Back_Left");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.left_stick_y;

        if(leftY > 0) {
            frontLeft.setPower(leftY);
            backLeft.setPower(leftY);
        }
        if(rightY > 0) {
            frontRight.setPower(rightY);
            backRight.setPower(rightY);
        }
    }
}
