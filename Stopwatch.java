import java.util.Scanner;
import java.math.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch 
{
	static int interval;
	static Timer timer;

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Input seconds => : ");
		String secs = sc.nextLine();
		int delay = 1000;
		int period = 1000;
		timer = new Timer();
		interval = Integer.parseInt(secs);
		System.out.println(secs);
		timer.scheduleAtFixedRate(new TimerTask() 
		{
			public void run() 
			{
				System.out.println(setInterval());

			}
		}, delay, period);
	}

	private static final String setInterval() 
	{
		if (interval == 1)
			timer.cancel();
		int hours = Math.floorDiv(interval, 3600);
		int remainders = interval % 3600;
		int minutes = Math.floorDiv(remainders, 60);
		remainders = remainders % 60;
		
		--interval;
		return "" + hours + " hours, " + minutes + " minutes, " + remainders + " seconds";
	}
}