package frc.robot.Arm.command;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Arm.Arm;
import frc.robot.Arm.NamedPose;

public class ArmCommand extends CommandBase
{
    private Arm arm;
    private ArmPose pose;

    private ArmCommand(Arm s_Arm, ArmPose pose)
    {
       
        this.arm = s_Arm;
        this.pose = pose;
        addRequirements(s_Arm);
    }

    @Override
    public void initialize()    
    {
       arm.adoptPose(pose);
    }

    @Override
    public void execute()
    {
       
    }

    @Override
    public void end(boolean inturrupted)
    {
        if(! inturrupted)
        {
        System.out.println("posed "+ inturrupted);
        }
    }

    @Override
    public boolean isFinished()
    {
        
        return arm.isAtPose(pose);
    }


    public static Command PlotPathAndSchedule(NamedPose dest, Arm arm, PowerDistribution pd)
    {
        CommandScheduler.getInstance().schedule(new InstantCommand(()->pd.setSwitchableChannel(false)));
        return ArmCommand.PlotPathAndSchedule(dest, arm.getCurrentPose(), arm);
    }


    public static Command PlotPathAndSchedule(NamedPose dest, Arm arm)
    {
        return ArmCommand.PlotPathAndSchedule(dest, arm.getCurrentPose(), arm);
    }

    public static Command PlotPathAndSchedule( NamedPose dest, ArmPose from, Arm arm)
    {
      
        Command  c = PlotPath(dest, from, arm);
        CommandScheduler.getInstance().schedule( c);
        return Commands.sequence(c);
    }

    public static Command PlotPath(NamedPose dest, ArmPose from, Arm arm)
    {
        System.out.println("Plotting path FROM: "+from);
        ArmPose to = arm.getPoseList().getArmPose(dest);

        ArrayList<Command> sequence = new ArrayList<Command>();


        sequence.add(new ArmCommand(arm,to));


        Command[] toReturn = new Command[sequence.size()];
        for(int i=0; i<sequence.size();i++ )
        {
            toReturn[i]=sequence.get(i);
        }
        
        
        Command c = Commands.sequence(toReturn);
        return c;
    }
}