import java.util.*;

//Welcome to the tank class!

public class Tank
{
	double health;
	double dmg;
	double dmg2;
	double crit;
	double pRange;
	double aggro;
	Random random = new Random();
	
    /**
    The constructor initializes the main parameters when an instance of this class is created 
    which allows for the ability to easily change the values without having to access the Tank 
    class itself*/
    
	public Tank (double h, double d, double a, double p, double c)
	{
		health = h;
		dmg = d;
		aggro = a;
		pRange = p;
		crit = c;
	}
	
	public void tankDeath()
	{
		health = 0;
		dmg = 0;
		pRange = 0;
		crit = 0;
		dmg2 = 0;
		aggro = 0;
	}
	
	public double getTankHealth ()
	{
		return health;
	}
	
	public void setTankHealth(double h)
	{
		health = h;
	}
	
	public double tankStrike()
	{
		double r = random.nextDouble();
		dmg2 = dmg + ((2*pRange)/100) * (r - 0.5) * dmg;
		if (r < (crit/100))
		{
			dmg2 = 2*dmg2;
			aggro = aggro*1.5;
		}
		aggro = aggro * 1.3;
		return dmg2;
	}
	
	public double getAggro()
	{
		return aggro;
	}
}
