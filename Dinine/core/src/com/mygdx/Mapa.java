// package com.mygdx;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Screen;
// import com.badlogic.gdx.graphics.OrthographicCamera;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.math.Rectangle;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.mygdx.game.Dinine;

// public class Mapa implements Screen{
//     private Dinine game;

//     Rectangle lvl1;
//     Rectangle lvl2;
//     Rectangle lvl3;
//     Rectangle lvl4;
//     Rectangle lvl5;
//     Rectangle lvl6;
//     Rectangle conf;

    

//     private Stage stage;
//     private Texture background = new Texture("Mapa/MapaVacio.png");

//     private OrthographicCamera camera;

//     private Texture img1;
//     private Texture img2;
//     private Texture img3;
//     private Texture img4;
//     private Texture img5;
//     private Texture img6;
//     private Texture confimg;
//     private int width;
//     private int height;

    
//     public Mapa(Dinine din){
//         this.game = din;

//         width= Gdx.graphics.getWidth();
//         height= Gdx.graphics.getHeight();
     

        
//         game.MapSound.setLooping(true);
//         game.MapSound.play();
//         game.MapSound.setVolume(game.volume);


//         stage = new Stage();
//         Gdx.input.setInputProcessor(stage);
//         camera = new OrthographicCamera();
//         camera.setToOrtho(false, 1920, 1080);

//         lvl1 = new Rectangle();
//         lvl1.x = 800;
//         lvl1.y = 245;
//         lvl1.width = width/(2);
//         lvl1.height = height/(18);


//         lvl2 = new Rectangle();
//         lvl2.x = 870;
//         lvl2.y = 700;
//         lvl2.width = width/(30);
//         lvl2.height = height/(18);

//         lvl3 = new Rectangle();
//         lvl3.x = 320;
//         lvl3.y = 850;
//         lvl3.width = width/(30);
//         lvl3.height = height/(18);

//         lvl4 = new Rectangle();
//         lvl4.x = 950;
//         lvl4.y = 485;
//         lvl4.width = width/(30);
//         lvl4.height = height/(18);

//         lvl5 = new Rectangle();
//         lvl5.x = 1670;
//         lvl5.y = 450;
//         lvl5.width = width/(30);
//         lvl5.height = height/(18);

//         lvl6 = new Rectangle();
//         lvl6.x = 1270;
//         lvl6.y = 300;
//         lvl6.width = width/(30);
//         lvl6.height = height/(18);

//         conf = new Rectangle();
//         conf.x = width/100;
//         conf.y = height/100;
//         conf.width = width/(25);
//         conf.height = height/(12);


        

//         img1=new Texture("Dojos/BaseDeDatos.png");
//         img2=new Texture("Dojos/Discreta.png");
//         img3=new Texture("Dojos/Empresas.png");
//         img4=new Texture("Dojos/Fisica.png");
//         img5=new Texture("Dojos/SistemasO.png");
//         img6=new Texture("Dojos/TALF.png");
//         confimg=new Texture("Boton2.png");
//     }

//     @Override
//     public void show() {
//         // TODO Auto-generated method stub
       
//     }

//     @Override
//     public void render(float delta) {
//         // TODO Auto-generated method stub
        
//         game.batch.begin();
//         game.batch.draw(background,0,0);
//         game.batch.draw(img1, lvl1.x, lvl1.y);
//         game.batch.draw(img2, lvl2.x, lvl2.y);
//         game.batch.draw(img3, lvl3.x, lvl3.y);
//         game.batch.draw(img4, lvl4.x, lvl4.y);
//         game.batch.draw(img5, lvl5.x, lvl5.y);
//         game.batch.draw(img6, lvl6.x, lvl6.y);
//         game.batch.draw(confimg, conf.x, conf.y); //Deberia ser configuracion
    
//         game.batch.end();
// 		camera.update();
// 		game.batch.setProjectionMatrix(camera.combined);

// 		stage.act(delta);
//         stage.draw();
//         if (Gdx.input.isTouched()) {

//             if((Gdx.input.getX() >= width/2.4 && Gdx.input.getX() <= width/2.4 + width/(30)) && 
//             (Gdx.input.getY() >= height/(1.4) && Gdx.input.getY() <= height/(1.4) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("a");
//                 game.setScreen(new Intermedio(game));
//             } else if((Gdx.input.getX() >= width/2.20 && Gdx.input.getX() <= width/2.20 + width/(30)) && 
//             (Gdx.input.getY() >= height/(3.6) && Gdx.input.getY() <= height/(3.6) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("b");
//                 game.setScreen(new Intermedio(game));
//             }else if((Gdx.input.getX() >=width/5.9 && Gdx.input.getX() <= width/5.9 + width/(30)) && 
//             (Gdx.input.getY() >= height/(6.75) && Gdx.input.getY() <= height/(6.75) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("c");
//                 game.setScreen(new Intermedio(game));
//             }else if((Gdx.input.getX() >= width/2 && Gdx.input.getX() <= width/2+ width/(30)) && 
//             (Gdx.input.getY() >= height/(2.05) && Gdx.input.getY() <= height/(2.05) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("d");
//                 game.setScreen(new Intermedio(game));
//             }else if((Gdx.input.getX() >= width/1.5 && Gdx.input.getX() <= width/1.5 + width/(30)) && 
//             (Gdx.input.getY() >= height/(1.55) && Gdx.input.getY() <= height/(1.55) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("e");
//                 game.setScreen(new Intermedio(game));
//             }else if((Gdx.input.getX() >=width/1.15 && Gdx.input.getX() <= width/1.15 + width/(30)) && 
//             (Gdx.input.getY() >= height/(1.95) && Gdx.input.getY() <= height/(1.95) + height/(16))){
//                 game.MapSound.dispose();
//                 System.out.println("f");
//                 game.setScreen(new Intermedio(game));
//             }else if((Gdx.input.getX() >=conf.x && Gdx.input.getX() <= conf.x + conf.width) && 
//             (Gdx.input.getY() <= height && Gdx.input.getY() >= height - conf.height)){
//                 game.setScreen(new Settings(game));
//             }		
//         }
//     }

//     @Override
//     public void resize(int width, int height) {
//         // TODO Auto-generated method stub
//         stage.getViewport().update(width, height, true);
//         this.width=width;
//         this.height=height;
        
        
        
//     }

//     @Override
//     public void pause() {
//         // TODO Auto-generated method stub
       
//     }

//     @Override
//     public void resume() {
//         // TODO Auto-generated method stub
        
//     }

//     @Override
//     public void hide() {
//         // TODO Auto-generated method stub
      
//     }

//     @Override
//     public void dispose() {
//         // TODO Auto-generated method stub
       
//     }
// }
package com.mygdx;
import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.Dinine;

public class Mapa implements Screen{
    private Dinine game;

    Rectangle lvl1;
    Rectangle lvl2;
    Rectangle lvl3;
    Rectangle lvl4;
    Rectangle lvl5;
    Rectangle lvl6;
    Rectangle conf;
    Rectangle rank;
    

    private Stage stage;
    private Texture background = new Texture("Mapa/MapaVacio.png");

    private OrthographicCamera camera;

    private Texture img1;
    private Texture img2;
    private Texture img3;
    private Texture img4;
    private Texture img5;
    private Texture img6;
    private Texture confimg;
    private Texture rankin;
    private int width;
    private int height;

    
    public Mapa(Dinine din){
        this.game = din;

        width= Gdx.graphics.getWidth();
        height= Gdx.graphics.getHeight();
     

        
        game.MapSound.setLooping(true);
        game.MapSound.play();
        game.MapSound.setVolume(game.volume);


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        lvl1 = new Rectangle();
        lvl1.x = 800;
        lvl1.y = 245;
        lvl1.width = width/(2);
        lvl1.height = height/(18);


        lvl2 = new Rectangle();
        lvl2.x = 870;
        lvl2.y = 700;
        lvl2.width = width/(30);
        lvl2.height = height/(18);

        lvl3 = new Rectangle();
        lvl3.x = 320;
        lvl3.y = 850;
        lvl3.width = width/(30);
        lvl3.height = height/(18);

        lvl4 = new Rectangle();
        lvl4.x = 950;
        lvl4.y = 485;
        lvl4.width = width/(30);
        lvl4.height = height/(18);

        lvl5 = new Rectangle();
        lvl5.x = 1670;
        lvl5.y = 450;
        lvl5.width = width/(30);
        lvl5.height = height/(18);

        lvl6 = new Rectangle();
        lvl6.x = 1270;
        lvl6.y = 300;
        lvl6.width = width/(30);
        lvl6.height = height/(18);

        conf = new Rectangle();
        conf.x = width/100;
        conf.y = height/100;
        conf.width = width/(25);
        conf.height = height/(12);

        rank = new Rectangle();
        rank.x = (width/200)*3;
        rank.y = (height/100)*13 ;
        rank.width = width/(25);
        rank.height = height/(12);

        
        img1=new Texture("Dojos/BaseDeDatos.png");
        img2=new Texture("Dojos/Software.png");
        img3=new Texture("Dojos/Empresas.png");
        img4=new Texture("Dojos/Fisica.png");
        img5=new Texture("Dojos/SistemasO.png");
        img6=new Texture("Dojos/TALF.png");
        confimg=new Texture("Boton2.png");
        rankin = new Texture("ranking.png");
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        
        game.batch.begin();
        game.batch.draw(background,0,0);
        game.batch.draw(img1, lvl1.x, lvl1.y);
        game.batch.draw(img2, lvl2.x, lvl2.y);
        game.batch.draw(img3, lvl3.x, lvl3.y);
        game.batch.draw(img4, lvl4.x, lvl4.y);
        game.batch.draw(img5, lvl5.x, lvl5.y);
        game.batch.draw(img6, lvl6.x, lvl6.y);
        game.batch.draw(confimg, conf.x, conf.y); //Deberia ser configuracion
        game.batch.draw(rankin,rank.x,rank.y);

        game.batch.end();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		stage.act(delta);
        stage.draw();
        if (Gdx.input.isTouched()) {

            if((Gdx.input.getX() >= width/2.4 && Gdx.input.getX() <= width/2.4 + width/(30)) && 
            (Gdx.input.getY() >= height/(1.4) && Gdx.input.getY() <= height/(1.4) + height/(16))){
                game.MapSound.dispose();
                System.out.println("a");
                try {
                    game.setScreen(new Ranking(game,5));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if((Gdx.input.getX() >= width/2.20 && Gdx.input.getX() <= width/2.20 + width/(30)) && 
            (Gdx.input.getY() >= height/(3.6) && Gdx.input.getY() <= height/(3.6) + height/(16))){
                game.MapSound.dispose();
                System.out.println("b");//Software
                try {
                    game.setScreen(new Ranking(game,6));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >=width/5.9 && Gdx.input.getX() <= width/5.9 + width/(30)) && 
            (Gdx.input.getY() >= height/(6.75) && Gdx.input.getY() <= height/(6.75) + height/(16))){
                game.MapSound.dispose();
                System.out.println("c");//Empresa
                try {
                    game.setScreen(new Ranking(game,1));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= width/2 && Gdx.input.getX() <= width/2+ width/(30)) && 
            (Gdx.input.getY() >= height/(2.05) && Gdx.input.getY() <= height/(2.05) + height/(16))){
                game.MapSound.dispose();
                System.out.println("d");//fisica
                try {
                    game.setScreen(new Ranking(game,2));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= width/1.5 && Gdx.input.getX() <= width/1.5 + width/(30)) && 
            (Gdx.input.getY() >= height/(1.55) && Gdx.input.getY() <= height/(1.55) + height/(16))){
                game.MapSound.dispose();
                System.out.println("e");//Talf
                try {
                    game.setScreen(new Ranking(game,3));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >=width/1.15 && Gdx.input.getX() <= width/1.15 + width/(30)) && 
            (Gdx.input.getY() >= height/(1.95) && Gdx.input.getY() <= height/(1.95) + height/(16))){
                game.MapSound.dispose();
                System.out.println("f");//Operativos
                try {
                    game.setScreen(new Ranking(game,4));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >=conf.x && Gdx.input.getX() <= conf.x + conf.width) && 
            (Gdx.input.getY() <= height && Gdx.input.getY() >= height - conf.height)){
                game.setScreen(new Settings(game));
            }else if((Gdx.input.getX() >=rank.x && Gdx.input.getX() <= rank.x + rank.width) && 
            (Gdx.input.getY() <= height - conf.height && Gdx.input.getY() >= height - conf.height - rank.height)){
                    try {
                        game.setScreen(new Ranking(game,0));
                    } catch (SQLException e) {
                        
                        e.printStackTrace();
                    }
                
            }	
        }
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        stage.getViewport().update(width, height, true);
        this.width=width;
        this.height=height;
        
        
        
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