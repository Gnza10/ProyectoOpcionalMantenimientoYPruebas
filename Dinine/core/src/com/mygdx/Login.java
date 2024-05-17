package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Dinine;

import java.sql.*;


public class Login implements Screen {

    private final Dinine game;

    private TextField txtUsser;
    private TextField correo;
    private TextField txtUsser2;
    

    private boolean errorcuenta=false;
    private boolean errorcrearcuenta=false;
    private boolean next=false;

    private OrthographicCamera camera;

    private Stage stage;
    private Texture background;
    private Statement stmt;
    private Skin skin;
    TextButton enter;
    TextButton creat;

    

    boolean set1=false;
    boolean set2=false;
    boolean set3=false;
    

    public Login( Dinine game) throws SQLException{

        this.game = game;

        
        game.log.setLooping(true);
        game.log.play();
        game.log.setVolume(game.volume);

        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
        int width= Gdx.graphics.getWidth();
        int height= Gdx.graphics.getHeight();
        skin=new Skin(Gdx.files.internal("uiskin.json"));
        
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "UBD4191", "dinine1234");
        stmt = con.createStatement();

        correo=new TextField("correo", skin);
        
        correo.setPosition(width/(11/3), height/(4));
        correo.setSize(width/(3),height/(12));
        stage.addActor(correo);
        txtUsser=new TextField("usuario", skin);
        txtUsser.setPosition(width/(11/3), height/(6));
        txtUsser.setSize(width/(3),height/(12));
        stage.addActor(txtUsser);
        txtUsser2=new TextField("contraseña", skin);
        txtUsser2.setPosition(width/(11/3), height/(12));
        txtUsser2.setSize(width/(3),height/(12));
        stage.addActor(txtUsser2);
        background=new Texture("Login.png");
        enter=new TextButton("log in", skin);
        enter.setPosition(width/(11/3), height/(40));
        enter.setSize(width/(6), height/(16));
        enter.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e,float x, float y){
                errorcrearcuenta=false;
                String consulta = "SELECT contraseña"+" FROM cuenta where nombrecuenta='"+txtUsser.getText()+"' AND correo='"+correo.getText()+"'";
                
                    ResultSet rset;
                    
                    try {
                        rset = stmt.executeQuery(consulta);
                        if(rset.next()){
                            
                            if(txtUsser2.getText().compareTo(rset.getString(1))==0){
                             
                                next=true;
                            }else{
                               
                                errorcuenta=true;
                            }
                        }else{
                            System.out.println("b");
                            errorcuenta=true;
                        }
                    } catch (SQLException e1) {
                        
                        e1.printStackTrace();
                    }
                    
                
            }
        });
        stage.addActor(enter);
        creat=new TextButton("crear cuenta", skin);
        creat.setPosition(width/(7/3), height/(40));
        creat.setSize(width/(6), height/(16));
        creat.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e,float x, float y){
                
                String consulta = "SELECT contraseña"+" FROM cuenta where nombrecuenta='"+txtUsser.getText()+"' OR correo='"+correo.getText()+"'";
                String nId="SELECT COUNT(id_cuenta)"+" FROM cuenta";
                String nId2="0";
                
                try {
                    ResultSet nidf=stmt.executeQuery(nId);
                    if(nidf.next()){
                        nId2=nidf.getString(1);
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if(nId2!=null){
                    int nid=Integer.parseInt(nId2)+1;
                    nId2=Integer.toString(nid);
                    System.out.println(nId2+" "+nid);
                }else{
                    nId2="0";
                }
                

                errorcuenta=false;
                String crear = "INSERT INTO cuenta(nombrecuenta, id_cuenta, correo, contraseña)\n" + 
                "VALUES('" + txtUsser.getText() + "', '" + nId2 + "', '" + correo.getText() + "', '" + txtUsser2.getText()  +  "')";
                    ResultSet rset;
                    try {
                        rset = stmt.executeQuery(consulta);
                        if(!rset.next()){
                            rset = stmt.executeQuery(crear);
                            next=true;
                        }else{
                            errorcrearcuenta=true;
                        }
                        
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
        });
        stage.addActor(creat);
        game.font.getData().setScale(2, 2);
        
    }

    @Override
    public void show() {
     
       
    }

    @Override
    public void render(float delta) {
        
        
        
        
        game.batch.begin();
        game.batch.draw(background,0,0);
        if(errorcuenta){
            game.font.draw(game.batch, "error de contraseña ", 800, 30);
            
        }
        if(errorcrearcuenta){
            game.font.draw(game.batch, "la cuenta ya existe ", 800, 30);
        }
        if(txtUsser.hasKeyboardFocus()){
            if(!set2){
                 txtUsser.setText("");
                 set2=true;
            }
           
            if(txtUsser2.getText().compareTo("")==0){
                txtUsser2.setText("contraseña");
                set3=false;
           }
            if(correo.getText().compareTo("")==0){
               correo.setText("correo");
               set1=false;
           }
            
        }
        if(txtUsser2.hasKeyboardFocus()){
            if(!set3){
                txtUsser2.setText("");
                set3=true;
            }
            
            if(txtUsser.getText().compareTo("")==0){
                 txtUsser.setText("usuario");
                 set2=false;
            }
            if(correo.getText().compareTo("")==0){
                correo.setText("correo");
                set1=false;
            }
            
        }
        if(correo.hasKeyboardFocus()){
            if(!set1){
                correo.setText("");
                set1=true;
            }
            
            if(txtUsser.getText().compareTo("")==0){
                txtUsser.setText("usuario");
                set2=false;
            }
            if(txtUsser2.getText().compareTo("")==0){
                txtUsser2.setText("contraseña");
                set3=false;
           }
        }
        game.batch.end();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		stage.act(delta);
        stage.draw();

        

        if(next){
            game.log.dispose();
            String consulta = "SELECT id_cuenta"+" FROM cuenta where nombrecuenta='"+txtUsser.getText()+"' AND correo='"+correo.getText()+"'";
                
            ResultSet rset;
            try {
                rset=stmt.executeQuery(consulta);
                rset.next();
                game.user=Integer.parseInt(rset.getString(1));
                System.out.println(game.user);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            game.setScreen(new Mapa(game));
        }
        
		
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        stage.getViewport().update(width, height, true);
        
        
        
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
