package com.qualcomm.ftcrobotcontroller.opmodes.customCode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class LinearTutorial extends LinearOpMode{
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private TouchSensor touchSensor;
    private final int BACKUP_TIME = 1000;
    private final int TURN_TIME = 250;
    private OpticalDistanceSensor opticalDistanceSensor;

    final static int ENCODER_CPR = 1440;
    final static double GEAR_RATIO = 2;
    final static int WHEEL_DIAMITER = 4;
    final static int DISTANCE = 24;
    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMITER;
    final static double ROTATIONS = DISTANCE / CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

    @Override
    public void runOpMode() throws InterruptedException {
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        touchSensor = hardwareMap.touchSensor.get("sensor_touch");
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("sensor_EOPD");

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        leftMotor.setTargetPosition((int) COUNTS);
        rightMotor.setTargetPosition((int) COUNTS);

        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        rightMotor.setPower(1);
        leftMotor.setPower(1);

        sleep(2000);

        rightMotor.setPower(0);
        leftMotor.setPower(0);

        for (int i=0; i<4; i++) {
            rightMotor.setPower(1);
            leftMotor.setPower(1);

            sleep(3000);

            rightMotor.setPower(0);
            leftMotor.setPower(0.5);

            sleep(1500);
        }

        if (touchSensor.isPressed()) {
            rightMotor.setPower(0);
            leftMotor.setPower(0);
        } else {
            rightMotor.setPower(1);
            leftMotor.setPower(1);
        }

        telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));
    }
}
