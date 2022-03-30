package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.intake.KIntake
import com.asiankoala.koawalib.subsystem.odometry.OdoConfig
import com.asiankoala.koawalib.subsystem.odometry.ThreeWheelOdometry
import com.asiankoala.koawalib.subsystem.odometry.TwoWheelOdometry
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder

class Hutao {
    private val fl = KMotor("FL").brake.reverse
    private val bl = KMotor("BL").brake.reverse
    private val fr = KMotor("FR").brake
    private val br = KMotor("BR").brake

    private val odoLeft = bl.zero().reverseEncoder
    private val odoAux = br.zero().reverseEncoder
    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)

    val drive = KMecanumOdoDrive(fl, bl, fr, br, TwoWheelOdometry(OdoConfig(
            1892.3724, 1.857, 1.0, odoLeft, odoLeft, odoAux
    ), imu), false)
}