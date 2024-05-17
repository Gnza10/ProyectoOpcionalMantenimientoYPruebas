package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Dinine;

public class Settings implements Screen {
    private Dinine game;

    Rectangle boton;
    Rectangle musica;


    private OrthographicCamera camera;
    private Texture background;
    private Texture imgvolver;
    private Texture imgsalir;
    private Texture imgmusica;
    private Texture imgbarra;
    private Texture imgboton;

    private float pos = 0;


    public Settings(Dinine game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        background=new Texture("Nueva Carpeta/AjustesFondo.png");
        imgvolver=new Texture("Nueva Carpeta/Volver.png");
        imgsalir=new Texture("Nueva Carpeta/Salir.png");
        imgmusica=new Texture("Nueva Carpeta/Musica.png");
        imgbarra=new Texture("SinNada.png");
        imgboton=new Texture("Nueva Carpeta/Boton.png");

        boton = new Rectangle(0,-15,imgboton.getWidth(), imgboton.getHeight());
        musica = new Rectangle(Gdx.graphics.getWidth()*0.2552f, Gdx.graphics.getHeight()*0.396f,Gdx.graphics.getWidth()*0.7416f - Gdx.graphics.getWidth()*0.2552f, Gdx.graphics.getHeight()*0.48f - Gdx.graphics.getHeight()*0.396f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		game.batch.draw(background,0,0);
        game.batch.draw(imgsalir, 0, 100);
        game.batch.draw(imgvolver, 0, 110);
        game.batch.draw(imgmusica, 0, 100);
        game.batch.draw(imgbarra, 437, 545, imgbarra.getWidth()+6, imgbarra.getHeight());
        game.batch.draw(imgboton, boton.x, boton.y);

       

		game.batch.end();

        if(Gdx.input.isTouched()) {
            if ((Gdx.input.getX() >= Gdx.graphics.getWidth()*0.2552  && Gdx.input.getX() <= Gdx.graphics.getWidth()*0.742) &&
                    (Gdx.input.getY() >= Gdx.graphics.getHeight()*0.396 && Gdx.input.getY() <= Gdx.graphics.getHeight()*0.48)) {
                boton.x = (Gdx.input.getX())-Gdx.graphics.getWidth()/2;
                float valor = (Gdx.input.getX() - musica.x) / musica.width;
                game.log.setVolume(valor);
                game.intro.setVolume(valor);
                game.MapSound.setVolume(valor);
                game.volume = valor;
            }
        }
        if(Gdx.input.justTouched()){
            if((Gdx.input.getX() >= Gdx.graphics.getWidth()*0.2276  && Gdx.input.getX() <= Gdx.graphics.getWidth()*0.774) &&
                    (Gdx.input.getY() >= Gdx.graphics.getHeight()*0.525  && Gdx.input.getY() <= Gdx.graphics.getHeight()*0.636)){
                game.setScreen(new Mapa(game));
            }
            if((Gdx.input.getX() >= Gdx.graphics.getWidth()*0.2276  && Gdx.input.getX() <= Gdx.graphics.getWidth()*0.774) &&
                    (Gdx.input.getY() >= Gdx.graphics.getHeight()*0.66  && Gdx.input.getY() <= Gdx.graphics.getHeight()*0.772)){
                System.exit(0);
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
