package org.firstinspires.ftc.teamcode.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import org.firstinspires.ftc.teamcode.Robot
import org.firstinspires.ftc.teamcode.commands.IntakeCommands.IntakeTurnOnCommand
import org.firstinspires.ftc.teamcode.commands.IntakeCommands.IntakeTurnOffCommand
import org.firstinspires.ftc.teamcode.commands.IntakeCommands.IntakeTurnReverseCommand
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftSetPositionCommand

class TeleOp : CommandOpMode() {
    override fun mInit() {
        val robot = Robot()

        robot.drive.setDefaultCommand(MecanumDriveCommand(robot.drive, driver.leftStick, driver.rightStick))

        driver.rightTrigger.whenPressed(IntakeTurnOnCommand(robot.intake))
        driver.leftTrigger.whenPressed(IntakeTurnOffCommand(robot.intake))
        driver.rightBumper.whenPressed(LiftSetPositionCommand(robot.lift, 100.0, 0.0, 0.0))
        driver.leftBumper.whenPressed(LiftSetPositionCommand(robot.lift, 0.0, 0.0, 0.0))

        driver.a.whenPressed(
                IntakeTurnOnCommand(robot.intake)
                        .pauseFor(5.0) // pause sequence for 5.0 seconds
                        .andThen(
                                IntakeTurnOffCommand(robot.intake)
                                        .alongWith(LiftSetPositionCommand(robot.lift, 100.0, 0.0, 0.0))
                        ) // turn off intake and set lift position at the same time
                        .pauseFor(5.0) // pause sequence again for 5.0 seconds
                        .andThen(LiftSetPositionCommand(robot.lift, 0.0, 0.0, 0.0))
                        .cancelUpon { gunner.rightTrigger.isPressed } // cancel this command if the gunner presses right trigger
        )

        gunner.leftTrigger.onPress(IntakeTurnReverseCommand(robot.intake))
    }
}