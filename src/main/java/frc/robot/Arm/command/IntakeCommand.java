package frc.robot.Arm.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Arm.Intake;

public class IntakeCommand extends CommandBase
{

    private Intake INTAKE;

    public IntakeCommand(Intake intake)
    {
        INTAKE = intake;
    }

    @Override
    public void initialize() 
    {
        
    }
    @Override
    public void execute()
    {

            INTAKE.startIntake();
  
        
    }

    @Override
    public void end(boolean inturrupted)
    {
        INTAKE.stopIntake();

    }



}

