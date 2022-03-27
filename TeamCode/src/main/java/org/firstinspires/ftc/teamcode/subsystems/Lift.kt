package org.firstinspires.ftc.teamcode.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Lift(private val leftMotor: KMotorEx, private val rightMotor: KMotorEx) : DeviceSubsystem() {
    fun setLiftPosition(targetPosition: Double, targetVelocity: Double, targetAccel: Double) {
        leftMotor.setPIDTarget(targetPosition, targetVelocity, targetAccel)
        rightMotor.setSpeed(leftMotor.power)
    }

    val isAtPosition: Boolean
        get() = leftMotor.isAtTarget()

    override fun periodic() {
        leftMotor.update()
        rightMotor.update()
    }
}