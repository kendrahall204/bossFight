import java.util.*;

//Welcome to the Healer class!

public class Healer 
{
	double health;
	double dmg;
	double dmg2;
	double crit;
	double smallHeal;
	double lgHeal;
	double aggro;
	double pRange;
	Random random = new Random();
	
    /**
    The constructor initializes the main parameters when an instance of this class is created 
    which allows for the ability to easily change the values without having to access the Healer 
    class itself*/
    
	public Healer (double h, double d, double sH, double lH, double a, double p, double c)
	{
		health = h;
		dmg = d;
		smallHeal = sH;
		lgHeal = lH;
		aggro = a;
		pRange = p;
		crit = c;
	}
	
	public void healerDeath ()
	{
		health = 0;
		dmg = 0;
		smallHeal = 0;
		dmg2 = 0;
		lgHeal = 0;
		pRange = 0;
		crit = 0;
		aggro = 0;
	}
	
	public double getHealerHealth()
	{
		return health;
	}
	
	public void setHealerHealth(double h)
	{
		health = h;
	}

	/**
	Calculations for damage done here , aggro is increased for each strike with 
	a larger increase for a crit strike */
	
	public double healerStrike()
	{
		double r = random.nextDouble();
		dmg2 = dmg + ((2*pRange)/100) * (r - 0.5) * dmg;
		if (r < (crit/100))
		{
			dmg2 = 2*dmg2;
			aggro = aggro * 1.4;
		}
		aggro = aggro * 1.2;
		return dmg2;
	}
	
	//We increase the aggro for a small heal
	public double smallHeal()
	{
		aggro = aggro * 1.1;
		return smallHeal;
	}
	
	//We increase the aggro by a slightly higher factor for a large heal
	public double lgHeal()
	{
		aggro = aggro * 1.2;
		return lgHeal;
	}
	
	public double getAggro()
	{
		return aggro;
	}
	
	
}
