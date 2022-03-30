package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.CommandScheduler
import com.asiankoala.koawalib.command.commands.GoToPointCommand
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.asiankoala.koawalib.util.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class HutaoTeleOp : CommandOpMode() {
    private lateinit var hutao: Hutao

    override fun mInit() {
        Logger.verboseLogging()
        hutao = Hutao()
        hutao.drive.setStartPose(Pose(0.0, 0.0, 90.0.radians))
        hutao.drive.setDefaultCommand(MecanumDriveCommand(
                hutao.drive,
                driver.leftStick,
                driver.rightStick.xInverted,
                1.0, 1.0, 1.0,
                xScalar = 0.7, yScalar = 0.7, rScalar = 0.7,
        ))

        driver.rightTrigger.onPress(
                GoToPointCommand(hutao.drive,
                        Pose(36.0, 24.0),
                        2.0, 2.0.radians,
                        maxMoveSpeed = 0.8,
                        maxTurnSpeed = 0.8,
                )
                        .pauseFor(2.0)
                        .andThen(GoToPointCommand(hutao.drive,
                                Pose(24.0, 36.0),
                                2.0,
                                2.0.radians,
                                stop = true,
                                maxMoveSpeed = 0.8,
                                maxTurnSpeed = 0.8,
                                isHeadingLocked = true,
                                headingLockAngle = 90.0.radians
                        ))
                        .pauseFor(2.0)
                        .andThen(GoToPointCommand(hutao.drive,
                                Pose(),
                                2.0,
                                2.0.radians,
                                stop = true,
                                maxMoveSpeed = 0.8,
                                maxTurnSpeed = 0.8,
                                isHeadingLocked = true,
                                headingLockAngle = 0.0
                        ))
        )
    }

    override fun mLoop() {
        hutao.imu.periodic()
        Logger.addTelemetryData("driver powers", hutao.drive.powers)
        Logger.addTelemetryData("position", hutao.drive.position)
    }
}