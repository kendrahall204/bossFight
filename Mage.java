import java.util.*;

// Welcome to the Mage class!

public class Mage
{
	double health;
	double dmg;
	double number;
	double aggro;
	double dmg2;
	double crit;
	double pRange;
	Random random = new Random();
	
    /**
    The constructor initializes the main parameters when an instance of this class is created 
    which allows for the ability to easily change the values without having to access the Mage 
    class itself*/
       
	public Mage (double h, double d, double a, double p, double c)
	{
		health = h;
		dmg = d;
		number++;
		aggro = a;
		pRange = p;
		crit = c;
	}
	
	public void mageDeath ()
	{
		health = 0;
		dmg = 0;
		pRange = 0;
		crit = 0;
		dmg2 = 0;
		aggro = 0;
	}
	
	public double getMageHealth()
	{
		return health;
	}
	
	public void setMageHealth(double h)
	{
		health = h;
	}
	
	/**
	Calculations for damage done here , aggro is increased for each strike with 
	a larger increase for a crit strike */
	
	public double mageStrike()
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
	
	public double getAggro()
	{
		return aggro;
	}
}
