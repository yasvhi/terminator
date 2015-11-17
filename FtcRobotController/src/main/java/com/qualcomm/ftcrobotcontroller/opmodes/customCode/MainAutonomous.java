package com.qualcomm.ftcrobotcontroller.opmodes.customCode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by eku952 on 11/16/2015.
 */
public class MainAutonomous extends ETBaseOpMode {
  private DcMotor right;
  private DcMotor left;
  Movement movement1 = new Movement(5, right, left);
  Movement movement2 = new Movement(10, right, left);

  @Override
  public void etInit() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    right.setDirection(DcMotor.Direction.REVERSE);
  }

  private void turnRight() throws InterruptedException {
    right.setPower(-5);
    left.setPower(5);
    wait(1000);
  }

  @Override
  public void etSetup() throws InterruptedException {
    movement1.setup();
    movement1.loop();
    turnRight();
    movement2.setup();
    movement2.loop();
  }
}
