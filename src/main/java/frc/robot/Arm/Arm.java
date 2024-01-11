package frc.robot.Arm;


import frc.robot.Constants;
import frc.robot.Arm.command.ArmPose;
import frc.robot.Arm.command.BasicPose;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase
{

    private CANSparkMax armMaster;
    private CANSparkMax armSlave;

    
    public Arm()
    {

       

        //Initialize master motor
        armMaster = new CANSparkMax(Constants.Arm.armMasterMoter, MotorType.kBrushless);
        armMaster.setIdleMode(ArmConfig.armMotorConfig.idleMode);

       
        //Configure PID
        SparkMaxPIDController controller = armMaster.getPIDController();
        controller.setP(ArmConfig.armMotorConfig.pValue, 0);
        controller.setI(ArmConfig.armMotorConfig.iValue, 0);
        controller.setD(ArmConfig.armMotorConfig.dValue, 0);
        controller.setFF(ArmConfig.armMotorConfig.ffValue, 0);
        controller.setOutputRange(-ArmConfig.armMotorConfig.maxPower, ArmConfig.armMotorConfig.maxPower);

        //Initialize slave motor
        armSlave = new CANSparkMax(Constants.Arm.armSlaveMotor, MotorType.kBrushless);
        armSlave.follow(armMaster, true);

        CANSparkMax[] motors = {armMaster, armSlave};

        initializeMotors(motors);

    }

    

    @Override
    public void periodic()
    {
        // despite the lack of roundness, this is very sensual. Yes more!;
       
    }

    private void initializeMotors(CANSparkMax[] motors)
    {
        //Do this for each motor in motors[] array
        for(CANSparkMax motor: motors)
        {
            //Set current Limit
            motor.setSmartCurrentLimit(40,20 );

            
            
        }


        //Set ramp rates
        armMaster.setClosedLoopRampRate(ArmConfig.armMotorConfig.rampRate);
        armMaster.setOpenLoopRampRate(ArmConfig.armMotorConfig.rampRate);


        for(CANSparkMax motor : motors)
        {
            //Burn flash - *IMPORTANT*
            motor.burnFlash();
        }
        
    }

    public void climbUp()
    {
        armMaster.set(ArmConfig.armMotorConfig.forwardSpeed);
    }


    public void climbDown()
    {
        armMaster.set(ArmConfig.armMotorConfig.reverseSpeed);
    }


    private boolean isWithin(double a, double b, double within)
    {
        return Math.abs(a-b)<within;
    }


    public void stop()
    {
        armMaster.set(0);
    }

}