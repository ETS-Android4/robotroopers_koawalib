package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.control.PIDExController
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.DisableFeedforward
import com.asiankoala.koawalib.control.OpenLoopController
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import org.firstinspires.ftc.teamcode.subsystems.Drive
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Lift

class Robot {
    private val flMotor = KMotor("frontLeft")
    private val blMotor = KMotor("backLeft")
    private val frMotor = KMotor("frontRight")
    private val brMotor = KMotor("backRight")
    private val intakeMotor = KMotor("intake")
    private val liftLeftMotor = KMotorEx("liftLeft", PIDExController(
            PIDFConfig(
                    0.0,
                    0.0,
                    0.0,
                    DisableFeedforward(),
                    0.0,
                    0.0,
                    0.0
            )))

    private val liftRightMotor = KMotorEx("liftRight", OpenLoopController())

    val drive = Drive(flMotor, blMotor, frMotor, brMotor)
    val intake = Intake(intakeMotor, IntakeConfig(1.0))
    val lift = Lift(liftLeftMotor, liftRightMotor)
}