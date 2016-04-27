import java.util.Scanner;
import java.math.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import sun.audio.*;

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
		timer.scheduleAtFixedRate(new TimerTask() 
		{
			public void run() 
			{
				String thing = setInterval();
				System.out.println(thing);
				if(thing.equals("0:0:1"))
				{
					try {
						playAlarmSound();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
		return "" + hours + ":" + minutes + ":" + remainders;
	}
	
	public static void playAlarmSound() throws Exception
	{
		// open the sound file as a Java input stream
		String filename = "/Users/Steven/Documents/filename.au";
		InputStream in = new FileInputStream(filename);

		// create an audiostream from the inputstream
		AudioStream audioStream = new AudioStream(in);

		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);
	}
}