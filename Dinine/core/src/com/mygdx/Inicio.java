package com.mygdx;

import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Dinine;

public class Inicio implements Screen {

    private final Dinine game;

	private OrthographicCamera camera;
    private Texture background;
    


    public Inicio(Dinine game) {

        this.game = game;
        background=new Texture("PantallaInicio.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);

        
        game.intro.setLooping(true);
        game.intro.play();
        game.intro.setVolume(game.volume);

    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(float delta) {
        

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(background,0,0);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			try {
                game.intro.dispose();
                game.setScreen(new Login(game));

            } catch (SQLException e) {
                
                e.printStackTrace();

            }
            
			dispose();
		}
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
       
    }
    
}
