package org.firstinspires.ftc.teamcode.commands

import com.asiankoala.koawalib.command.commands.CommandBase
import org.firstinspires.ftc.teamcode.subsystems.Lift

class LiftCommands {
    class LiftSetPositionCommand(
            private val lift: Lift,
            private val targetPosition: Double,
            private val targetVelocity: Double,
            private val targetAcceleration: Double) : CommandBase() {

        override fun init() {
            lift.setLiftPosition(targetPosition, targetVelocity, targetAcceleration)
        }

        override fun execute() {}

        override val isFinished: Boolean
            get() = lift.isAtPosition

        init {
            addRequirements(lift)
        }
    }
}