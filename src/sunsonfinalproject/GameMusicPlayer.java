package sunsonfinalproject;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameMusicPlayer {
	private String gameBGM = "game.mp3";
	private String BGMLocation;
	private Media gameBGMMedia;
	private MediaPlayer mediaPlayer;
	
	public GameMusicPlayer(){
		BGMLocation = Paths.get(gameBGM).toUri().toString();
		System.out.println(BGMLocation);
		gameBGMMedia = new Media(BGMLocation);
		//gameBGMMedia = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(gameBGMMedia);
	}
	
	public void gameMusicPlay(){
		this.mediaPlayer.play();
	}
}
