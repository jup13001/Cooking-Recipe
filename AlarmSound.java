import java.io.*;
import sun.audio.*;
 
public class AlarmSound
{
  public static void main(String[] args) 
  throws Exception
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