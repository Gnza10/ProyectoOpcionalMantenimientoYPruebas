package com.mygdx.game;


import java.sql.SQLException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.*;



public class Dinine extends Game {

	public SpriteBatch batch;
	public BitmapFont font;
	public int user;
	public Music log;
	public Music MapSound;
	public Music intro;
	public Music juego;
	public float volume=(float) 0.5;

	public void create() {
		batch = new SpriteBatch();

		MapSound=Gdx.audio.newMusic(Gdx.files.internal("ewe.mp3"));
		log=Gdx.audio.newMusic(Gdx.files.internal("Map.mp3"));
		intro=Gdx.audio.newMusic(Gdx.files.internal("QuizTime.mp3"));
		juego=Gdx.audio.newMusic(Gdx.files.internal("owo.mp3"));
		

		font= new BitmapFont(Gdx.files.internal("lanueva.fnt"),Gdx.files.internal("lanueva.png"),false);
		this.setScreen(new Inicio(this));
	}


	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}