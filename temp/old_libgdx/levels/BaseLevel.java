package com.mdg.ballx2.levels;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mdg.ballx2.arrow.Arrow;
import com.mdg.ballx2.ball.Ball;
import com.mdg.ballx2.main.LoadResources;
import com.mdg.ballx2.player.Player;

public abstract class BaseLevel {
	
	public static boolean pauseGame = false;
	public static int controlType = 0;
	public static boolean shootEnabled;
	public static boolean shoot = false;
	TextureRegion ball,player;
	OrthographicCamera camera;
	Player p;
	ArrayList<Ball> ballList = new ArrayList<Ball>();
	Arrow arrow;
	
	
	
	
	public abstract void initializeNumberOfBalls();
	
	public void setUpGameArena(){
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
		ball = LoadResources.ball;
		player =  LoadResources.player;
		p = new Player(360);
		arrow = new Arrow();
		
	}
	
	public void updateGame(){
		//updating ball's positions
		for(int i =0;i<ballList.size();i++){
			ballList.get(i).updateBallPosition();
			ballList.get(i).collisionEdges();
		}
		//updating player position	
		p.updatePosition(0);
		p.detectCollision();
		
		// dealing with arrow
		if(!shoot){
			arrow.setCoordinates(0, p.posX);
		}
		if(shootEnabled){
			shoot = true;			
			shootEnabled = false;
		}
		if(shoot){
			arrow.updateArrowPosition();
			arrow.detectCollisionWithWalls();
		}
		//updating gifts
		
	}
	public abstract void renderGame();
	
	public void restartLevel(){
		
	}
	public void levelFinished(){
		
	}
	
}
