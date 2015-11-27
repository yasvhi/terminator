/*
 * Copyright (c) 2015 Edina Terminators Robotics.
 * This software is distributed under the MIT License. The license text can be read in full at /LICENSE.txt
 * Authored by:
 * Luke Langefels <eku952>
 * Richik Sinha Choudhury <richiksc>
 */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.customcode.*;
import com.qualcomm.ftcrobotcontroller.opmodes.customcode.AutonomousBlue;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */

    manager.register("NullOp", NullOp.class);

    //manager.register("MatrixK9TeleOp", MatrixK9TeleOp.class);
    manager.register("K9TeleOp", K9TeleOp.class);
    manager.register("K9Line", K9Line.class);
    manager.register ("PushBotAuto", PushBotAuto.class);
    manager.register ("PushBotManual", PushBotManual.class);
    manager.register("Climbing Pros", Teleop.class);
    // manager.register("Movement", Movement.class);
    manager.register("Autonomous [Red]", AutonomousRed.class);
    manager.register("Autonomous [Blue]", AutonomousBlue.class);

    /*
     * Uncomment any of the following lines if you want to register an op mode.
     */
    //manager.register("MR Gyro Test", MRGyroTest.class);

    //manager.register("AdafruitRGBExample", AdafruitRGBExample.class);
    //manager.register("ColorSensorDriver", ColorSensorDriver.class);

    //manager.register("IrSeekerOp", IrSeekerOp.class);
    //manager.register("CompassCalibration", CompassCalibration.class);
    //manager.register("I2cAddressChangeExample", LinearI2cAddressChange.class);


    //manager.register("NxtTeleOp", NxtTeleOp.class);

    //manager.register("LinearK9TeleOp", LinearK9TeleOp.class);
    //manager.register("LinearIrExample", LinearIrExample.class);


    //manager.register ("PushBotManual1", PushBotManual1.class);
    //manager.register ("PushBotAutoSensors", PushBotAutoSensors.class);
    //manager.register ("PushBotIrEvent", PushBotIrEvent.class);

    //manager.register ("PushBotManualSensors", PushBotManualSensors.class);
    //manager.register ("PushBotOdsDetectEvent", PushBotOdsDetectEvent.class);
    //manager.register ("PushBotOdsFollowEvent", PushBotOdsFollowEvent.class);
    //manager.register ("PushBotTouchEvent", PushBotTouchEvent.class);

    //manager.register("PushBotDriveTouch", PushBotDriveTouch.java);
    //manager.register("PushBotIrSeek", PushBotIrSeek.java);
    //manager.register("PushBotSquare", PushBotSquare.java);
  }
}
