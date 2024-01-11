package frc.robot.Arm.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Arm.Arm;
import frc.robot.Arm.Intake;

public class ClimbDown extends CommandBase
{

    private Arm ARM;

    public ClimbDown(Arm arm)
    {
        ARM = arm;
    }

    @Override
    public void initialize() 
    {
        
    }
    @Override
    public void execute()
    {

            ARM.climbDown();
  
        
    }

    @Override
    public void end(boolean inturrupted)
    {
        ARM.stop();

    }



}

