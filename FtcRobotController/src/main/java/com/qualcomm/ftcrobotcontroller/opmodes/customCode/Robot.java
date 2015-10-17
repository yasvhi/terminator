package com.qualcomm.ftcrobotcontroller.opmodes.customCode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class Robot {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Robot(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void directDrive(double leftValue, double rightValue) {
        leftMotor.setPower(leftValue);
        rightMotor.setPower(rightValue);
    }
    
    public void move(double distance, double speed) {
        // This will be smarter with motor encoders.
        leftMotor.setPower(speed);
        rightmotor.setPower(speed);
    }
    
    public void rotate(double deg) {
        // This will be smarter and rotate exact degrees with motor encoders.
        double power = deg / 180;
            motorLeft.setPower(speed);
            motorRight.setPower(-speed);
    }

    public void directDrive(double leftValue, double rightValue, boolean squareInputs) {
        if(squareInputs) {
            if(leftValue >= 0.0) {
                leftValue = (leftValue * leftValue);
            } else {
                leftValue = -(leftValue * leftValue);
            }
        }
        if(rightValue >= 0.0) {
            rightValue = (rightValue * rightValue);
        } else {
            rightValue = -(rightValue * rightValue);
        }

        directDrive(leftValue, rightValue);
    }
}
