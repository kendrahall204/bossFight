import java.util.*;
public class Boss
{
	double health;
	double dmg;
	double crit; 
	double pRange;
	double dmg2;
	Random random = new Random();
	
	/**
    The constructor initializes the main parameters when an instance of this class is created 
    which allows for the ability to easily change the values without having to access the Boss 
    class itself*/
    
	public Boss (double h, double d, double p, double c)
	{
		health = h;
		dmg = d;
		pRange = p;
		crit = c;
	}
	
	public void BossDeath()
	{
		System.out.println("Congratulations you have defeated the boss");
	}
	
	public double getBossHealth()
	{
		return health;
	}
	
	public void setBossHealth(double h)
	{
		health = h;
	}
	
	public double bossStrike()
	{
		double r = random.nextDouble();
		dmg2 = dmg + ((2*pRange)/100) * (r - 0.5) * dmg;
		if (r < (crit/100))
			dmg2 = 2*dmg2;
		return dmg2;
	}
	
	public double splashDamage()
	{
		double r = random.nextDouble();
		dmg2 = dmg + ((2*pRange)/100) * (r - 0.5) * dmg;
		return dmg2;
	}
	
	//Calculates the max aggro based on the array given, returns the index where that max value lies
	public int aggroTable(double[] a, int n)
	{
		int maxAggro = 0;
		double max = 0;
		for (int i=0; i<=n; i++)
		{
			if (a[i] > max)
			{
				max = a[i];
				maxAggro = i;
			}	
		}
		return maxAggro;
	}
	
	
}
