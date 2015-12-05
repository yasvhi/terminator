package com.qualcomm.ftcrobotcontroller.opmodes.customcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class AutonomousBlue extends ETAutonomousBase {

  /*@Override
  public void etInit() throws InterruptedException {
    right = hardwareMap.dcMotor.get("Right");
    left = hardwareMap.dcMotor.get("Left");
    left.setDirection(DcMotor.Direction.REVERSE);
    waitOneFullHardwareCycle();
    right.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    left.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    waitOneFullHardwareCycle();
  }*/

  @Override
  public void etLoop() throws InterruptedException {

    telemetry.addData("stage", stage);

    switch (stage) {
      case STAGE_MOVE_FIRST:
      {
        counts = getCountsForDistance(MOVE_FIRST_DISTANCE);
        telemetry.addData("stage 20", counts);

        setTarget(MOVE_POWER);
        if(hasArrived(counts))
          stage = STAGE_REVERSE_FIRST;
        break;
      }

      case STAGE_REVERSE_FIRST: {
        // goes -6 dst.
        counts = getCountsForDistance(REVERSE_FIRST_DISTANCE);
        setTarget(MOVE_POWER * -1);
        if(hasArrived(counts)) {
          telemetry.addData("hasArrived Reverse", "true");
          stage = STAGE_TURN_FIRST;
        }
        break;
      }

      case STAGE_TURN_FIRST: {
        // turns right
        turn(-0.25, 0.25);
        stage = STAGE_MOVE_SECOND;
        break;
      }

      case STAGE_MOVE_SECOND : {
        counts = getCountsForDistance(MOVE_SECOND_DISTANCE);
        setTarget(MOVE_POWER);
        if(hasArrived(counts))
          stage = STAGE_STOP;
        break;
      }

      case STAGE_STOP: {
        stopRobot();
        break;
      }
    }
  }
}
