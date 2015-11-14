package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class Teleop extends OpMode {
    private DcMotor Right;
    private DcMotor Left;
    //private DcMotor backRight;
    //private DcMotor backLeft;

    @Override
    public void init() {
        Right = hardwareMap.dcMotor.get("Right");
        Left = hardwareMap.dcMotor.get("Left");
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

        if(gamepad1.right_trigger > 0.8 || gamepad1.left_trigger > 0.8) {
            toggled = !toggled;
        }
        telemetry.addData("Inverted Controls", (toggled ? "On":"Off"));
        if(toggled) {
            leftY = gamepad1.left_stick_y;
            rightY = gamepad1.right_stick_y;
        }

        telemetry.addData("Power Factor", .25);
        Right.setPower(rightY * .25);
        Left.setPower(leftY * .25);
    }
}
