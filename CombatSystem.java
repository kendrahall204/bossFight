import java.util.*;

/**
	Class uses instances of the Tank, Healer, Mage, and Boss classes to simulate a boss fight. Values for each character
	can be changed to affect the outcome of the fight, current numbers seem to simulate a fairly fair fight with Allies and 
	the boss each having about a 50% chance of winning
*/

public class CombatSystem {

	public static void main (String [] args)
	{
		System.out.println ("Welcome to the boss fight.");
		
		//Health, damage, aggro, pRange, crit
		Tank tank1 = new Tank (30, 2, 5, .7, 1.5);
		
		//Health damage, aggro, small heal, large heal, pRange, crit
		Healer healer1 = new Healer (13, 3, 3, 2.5, 5, .5, 2);
		
		//Health, damage, aggro, pRange, crit
		Mage mage1 = new Mage (16, 5.8, 1, .5, 2.7);
		Mage mage2 = new Mage (15, 7, 1, .5, 2.5);
		Mage mage3 = new Mage (17, 6, 1, .6, 3);
		
		//Health, damage, pRange, crit
		Boss boss1 = new Boss (500, 3, .5, 3);
		
		double bossTarget; 
		int timeTick = 0;
		
		//Keeping track of starting healths to help calculate who to heal based on difference in current health
		double tank1StartingHealth = tank1.getTankHealth();
		double mage1StartingHealth = mage1.getMageHealth();
		double mage2StartingHealth = mage2.getMageHealth();
		double mage3StartingHealth = mage3.getMageHealth();
		double healer1StartingHealth = healer1.getHealerHealth();
		
		//Use this to track when the team has died
		double totalTeamHealth = (tank1.getTankHealth() + mage1.getMageHealth() + mage2.getMageHealth() + mage3.getMageHealth() + healer1.getHealerHealth());
		
		//do while loop to simulate game play
		do
		{
			timeTick++;
			
			//Allies strike boss
			System.out.println("Allies attack");
			boss1.setBossHealth(boss1.getBossHealth() - (tank1.tankStrike()+mage1.mageStrike()+mage2.mageStrike()+mage3.mageStrike()));
	
			//Creating aggro table for each round
			double [] aggroTable = new double [] {tank1.getAggro(), healer1.getAggro(), mage1.getAggro(), mage2.getAggro(), mage3.getAggro()};
			bossTarget = boss1.aggroTable(aggroTable, 4);
			
			//Determining who to strike based on who has the highest aggro
			if (bossTarget == 0)
			{
				tank1.setTankHealth(tank1.getTankHealth() - boss1.bossStrike());
				System.out.println("Boss attacks Tank!\n");
			}
			else if (bossTarget == 1)
			{
				healer1.setHealerHealth(healer1.getHealerHealth() - boss1.bossStrike());
				System.out.println("Boss attacks Healer!\n");
			}
			else if (bossTarget == 2)
			{
				mage1.setMageHealth(mage1.getMageHealth() - boss1.bossStrike());
				System.out.println("Boss attacks Mage 1\n");
			}	
			else if (bossTarget == 3)
			{
				mage2.setMageHealth(mage2.getMageHealth() - boss1.bossStrike());
				System.out.println("Boss attacks Mage 2!\n");
			}
			else 
			{
				mage3.setMageHealth(mage3.getMageHealth() - boss1.bossStrike());
				System.out.println("Boss attacks mage 3!\n");
			}
			
			/**
			This if block determines which ally, if any, the healer heals. Does this by comparing there starting health
			to their current health, and only heals if the difference is greater than the strength of the small heal
			Tank is prioritized over mages, and mages are prioritized over itself. 
			*/
			if (timeTick % 3 == 0)
			{
				double smHeal = healer1.smallHeal();
				if ((tank1StartingHealth - tank1.getTankHealth()) <= smHeal && tank1.getTankHealth() > 0)
					tank1.setTankHealth(tank1.getTankHealth() + smHeal);
				else if ((mage1StartingHealth - mage1.getMageHealth()) <= smHeal && mage1.getMageHealth() > 0)
					mage1.setMageHealth(mage1.getMageHealth() +smHeal);
				else if ((mage2StartingHealth - mage2.getMageHealth()) <= smHeal && mage2.getMageHealth() > 0)
					mage2.setMageHealth(mage2.getMageHealth() +smHeal);	
				else if ((mage3StartingHealth - mage3.getMageHealth()) <= smHeal && mage3.getMageHealth() > 0)
					mage3.setMageHealth(mage3.getMageHealth() +smHeal);	
				else if ((healer1StartingHealth - healer1.getHealerHealth()) <= smHeal && healer1.getHealerHealth() > 0)
					healer1.setHealerHealth(healer1.getHealerHealth()+smHeal);
			}
			
			/**
			Same idea for the large heal just with a larger "Cool down" time, if no allies including itself require 
			healing then it will strike the boss
			*/
	
			if (timeTick % 11 == 0)
			{
				double lgHeal = healer1.lgHeal();
				if ((tank1StartingHealth - tank1.getTankHealth()) <= lgHeal && tank1.getTankHealth() > 0)
					tank1.setTankHealth(tank1.getTankHealth() +lgHeal);
				else if ((mage1StartingHealth - mage1.getMageHealth()) <= lgHeal && mage1.getMageHealth() > 0)
					mage1.setMageHealth(mage1.getMageHealth() +lgHeal);
				else if ((mage2StartingHealth - mage2.getMageHealth()) <= lgHeal && mage2.getMageHealth() > 0)
					mage2.setMageHealth(mage2.getMageHealth() +lgHeal);	
				else if ((mage3StartingHealth - mage3.getMageHealth()) <= lgHeal && mage3.getMageHealth() > 0)
					mage3.setMageHealth(mage3.getMageHealth() +lgHeal);	
				else if ((healer1StartingHealth - healer1.getHealerHealth()) <= lgHeal && healer1.getHealerHealth() > 0)
					healer1.setHealerHealth(healer1.getHealerHealth()+lgHeal);
				else boss1.setBossHealth(boss1.getBossHealth() - healer1.healerStrike());
			}	
			
			//If block for triggering the boss to do splash damage (damage to the whole team)
			if (timeTick % 13 == 0)
			{
				System.out.println("Splash damage!"); 
				tank1.setTankHealth(tank1.getTankHealth() - boss1.bossStrike());
				mage1.setMageHealth(mage1.getMageHealth() - boss1.bossStrike());
				mage2.setMageHealth(mage2.getMageHealth() - boss1.bossStrike());
				mage3.setMageHealth(mage3.getMageHealth() - boss1.bossStrike());
				healer1.setHealerHealth(healer1.getHealerHealth() - boss1.bossStrike());
			}
		
			System.out.println("Boss health: "+boss1.getBossHealth());
			
			//Used to indicate death of an ally, calls the methods that will set all their stats to 0
			if (tank1.getTankHealth() <= 0)
				tank1.tankDeath();

			if (mage1.getMageHealth() <= 0)
				mage1.mageDeath();				
			
			if (mage2.getMageHealth() <= 0)
				mage2.mageDeath();
			
			if (mage3.getMageHealth() <= 0)
				mage3.mageDeath();

			if (healer1.getHealerHealth() <= 0)
				healer1.healerDeath();
			
			totalTeamHealth = tank1.getTankHealth() + mage1.getMageHealth() + mage2.getMageHealth() + mage3.getMageHealth() +         
			healer1.getHealerHealth();
			
			System.out.println("Team health: "+totalTeamHealth+"\n");
			
		} while (boss1.getBossHealth() > 0 && totalTeamHealth > 0);
		
		if (totalTeamHealth <= 0)
			System.out.println("Boss wins!\n");
		else System.out.println("Allies win!\n");
			
		System.out.println("Thank you for playing!");
				
	}

}
		
