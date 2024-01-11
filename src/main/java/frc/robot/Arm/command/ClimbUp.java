package frc.robot.Arm.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Arm.Arm;
import frc.robot.Arm.Intake;

public class ClimbUp extends CommandBase
{

    private Arm ARM;

    public ClimbUp(Arm arm)
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

            ARM.climbUp();
  
        
    }

    @Override
    public void end(boolean inturrupted)
    {
        ARM.stop();

    }



}

