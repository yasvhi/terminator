package com.qualcomm.ftcrobotcontroller.opmodes.customCode;


import com.qualcomm.robotcore.hardware.DcMotor;

public class Robot {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Robot(DcMotor leftMotor, DcMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
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
    
    public void rotate(double deg, double speed) {
        // This will be smarter and rotate exact degrees with motor encoders.
        if(deg < 0) {
            motorLeft.setPower(-speed);
            motorRight.setPower(speed);
        }
        if(deg > 0) {
            motorLeft.setPower(speed);
            motorRight.setPower(-speed);
        }
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
