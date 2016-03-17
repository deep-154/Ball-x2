package com.mdg.ballx2.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mdg.ballx2.ball.Ball;
import com.mdg.ballx2.main.MainGame;

public class Level1 extends BaseLevel implements Screen,InputProcessor{
	
	final MainGame game;    
    public Texture background;   
   
   

    public Level1(final MainGame game) {    	
        this.game = game;
        background = new Texture("background.png");
        initializeNumberOfBalls();
        setUpGameArena();
        Gdx.input.setInputProcessor(this);
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        updateGame();
        game.batch.begin();
        renderGame(); 
        game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		background.dispose();
	}

	@Override
	public void initializeNumberOfBalls() {
		// TODO Auto-generated method stub
		
		ballList.add(new Ball(30,100,400,3,0));
	//	ballList.add(new Ball(60,800,500,-3,0));
		
	}
	
	@Override
	public void setUpGameArena() {
		// TODO Auto-generated method stub
		super.setUpGameArena();
		
	}

	@Override
	public void updateGame() {
		// TODO Auto-generated method stub
		super.updateGame();
		
		
	}

	@Override
	public void renderGame() {
		// TODO Auto-generated method stub
		game.batch.disableBlending();
		game.batch.draw(background,0,0,1280,720);
		game.batch.enableBlending();
		for(int i =0;i<ballList.size();i++){			
			game.batch.draw(ball,ballList.get(i).posX,ballList.get(i).posY,
					2*ballList.get(i).radius,2*ballList.get(i).radius);				
		}
		if(shoot){
			game.batch.draw(arrow.arrow,arrow.posX+p.playerWidth/2-arrow.width/2,arrow.posY,arrow.width,arrow.height);
		}
				
		 p.playerSprite.draw(game.batch);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		shootEnabled = true;
		//Gdx.app.debug("jsnckjs", ""+shoot);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		//Gdx.app.debug(""+screenX, ""+screenY);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	

}
