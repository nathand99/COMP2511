package unsw.dungeon;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class for adding sound effects to entities
 *
 */
public class SoundEffects {

	private Clip sound;
	private boolean canPlay = true;
	
	/**
	 * Start playing the sound file
	 * @param fname file name
	 */
	public void playSound(String fname) {
		if(canPlay) {
			try {
				sound = AudioSystem.getClip();
				sound.open(AudioSystem.getAudioInputStream(new File(fname)));
				sound.start();
			}catch (Exception error) {
				System.out.println("error with sound stuff");
				 error.printStackTrace(System.out);
			}
		}
	}

	public boolean getCanPlay() {
		return canPlay;
	}
	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}
	
	
}
