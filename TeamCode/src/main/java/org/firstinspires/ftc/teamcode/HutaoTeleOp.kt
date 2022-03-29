package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.CommandScheduler
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
        hutao = Hutao()
        hutao.drive.setDefaultCommand(MecanumDriveCommand(
                hutao.drive,
                driver.leftStick,
                driver.rightStick,
                0.7, 0.7, 0.7,
                xScalar = 0.7, yScalar = 0.7, rScalar = 0.7,
        ))

        driver.rightTrigger.onPress(GoToPointCommand(Pose(24.0, 24.0, 45.0.radians), 2.0, hutao.drive))
    }

    override fun mLoop() {
        hutao.imu.periodic()
        Logger.addTelemetryData("driver powers", hutao.drive.powers)
    }
}