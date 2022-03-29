package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.command.commands.CommandBase
import com.asiankoala.koawalib.math.Point
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.math.wrap
import com.asiankoala.koawalib.path.PurePursuitController
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import kotlin.math.absoluteValue

class GoToPointCommand(private val target: Pose, private val tolerance: Double, private val drive: KMecanumOdoDrive) : CommandBase() {

    override fun execute() {
        val ret = PurePursuitController.goToPosition(
                drive.position,
                target,
                isHeadingLocked = true,
                headingLockAngle = target.heading
        )
        drive.powers = ret
    }

    override fun end(interrupted: Boolean) {
        drive.powers = Pose()
    }

    override val isFinished: Boolean
        get() = drive.position.dist(Point(24.0, 24.0)) < tolerance && (target.heading - drive.position.heading).wrap.absoluteValue < 2.0.radians

    init {
        addRequirements(drive)
    }
}