package com.mdg.ballx2.screens.start;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mdg.ballx2.Ballx2;
import com.mdg.ballx2.data.Constants;
import com.mdg.ballx2.screens.BaseScreen;
import com.mdg.ballx2.screens.TestScreen;

/**
 * @author DEEPANKAR
 * @since 23-03-2016.
 */
public class PlayGameScreen extends BaseScreen {

    private Skin skin;
    private Stage stage;

    private TextButton startButton;
    private TextButton quitButton;

    public PlayGameScreen(Ballx2 game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        skin = new Skin();
        stage = new Stage(new ExtendViewport(Constants.worldWidth,Constants.worldHeight,mCamera));
        Gdx.input.setInputProcessor(stage);

        setUpSkin();

        Table table = new Table();
        table.setFillParent(true);

        startButton = new TextButton("New Game",skin,"default");
        quitButton = new TextButton("Quit Game",skin,"default");

        table.defaults().fill().width(100).height(60);
        table.add(startButton).padBottom(30);
        table.row();
        table.add(quitButton);

        setClickListeners();

        stage.setDebugAll(true);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }


    private void setUpSkin(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", mFont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    private void setClickListeners(){
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked Start","Yep, you did");
                mGame.setScreen(new TestScreen(mGame));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked quit", "Yep, you did");
            }
        });
    }

}
