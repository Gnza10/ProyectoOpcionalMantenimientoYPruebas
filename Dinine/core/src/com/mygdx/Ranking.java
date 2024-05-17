package com.mygdx;


import java.sql.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Dinine;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ranking implements Screen{
    private Dinine game;
   
    private Stage stage;
    private Statement stmt;

    private OrthographicCamera camera;
    private Texture background;

    private SpriteBatch user = new SpriteBatch();

    private BitmapFont font = new BitmapFont();

    private Rectangle boton1;
    private Rectangle boton2;
    private Rectangle boton3;
    private Rectangle boton4;
    private Rectangle boton5;
    private Rectangle boton6;

    private Rectangle volver;
    private Rectangle jugar;
    private Rectangle tu;

    private Texture imgtabla;
    private Texture Empresa;
    private Texture Fisica;
    private Texture Talf;
    private Texture Operativos;
    private Texture datos;
    private Texture software;
    private Texture volveri;
    private Texture tuyo;
    private Texture ju;

    

    private int idcuenta;

    private int modo;

    private String consulta1;
    private String consulta2;
    private String consulta3;
    private String consulta4;
    private String consulta5;
    private String consulta6;
    private String usuario;

    private String[] consultas;

    private String[] con1;

    

    public Ranking(Dinine game, int m) throws SQLException {
        this.game = game;
        
        background=new Texture("Ranking/Fondo.png");
        //imgtabla= new Texture("Rankingfondo.png");
        font.getData().setScale(2, 2);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);

        //Play Musica
        game.MapSound.setLooping(true);
        game.MapSound.play();
        game.MapSound.setVolume(game.volume);

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "UBD4191", "dinine1234");
        stmt = con.createStatement();

        // boton1 = new Rectangle();
        // boton1.x = 300;
        // boton1.y = 751;
        // boton1.width = 150;
        // boton1.Gdx.graphics.getHeight() = 100;

        // boton2 = new Rectangle();
        // boton2.x = 550;
        // boton2.y = 751;
        // boton2.width = 150;
        // boton2.Gdx.graphics.getHeight() = 100;

        // boton3 = new Rectangle();
        // boton3.x = 800;
        // boton3.y = 751;
        // boton3.width = 150;
        // boton3.Gdx.graphics.getHeight() = 100;

        // boton4 = new Rectangle();
        // boton4.x = 1050;
        // boton4.y = 751;
        // boton4.Gdx.graphics.getWidth() = 150;
        // boton4.Gdx.graphics.getHeight() = 100;

        // boton5 = new Rectangle();
        // boton5.x = 1300;
        // boton5.y = 751;
        // boton5.Gdx.graphics.getWidth() = 150;
        // boton5.Gdx.graphics.getHeight() = 100;

        // boton6 = new Rectangle();
        // boton6.x = 1550;
        // boton6.y = 751;
        // boton6.Gdx.graphics.getWidth() = 150;
        // boton6.Gdx.graphics.getHeight() = 100;

        // volver = new Rectangle();
        // volver.x = 1409;
        // volver.y = 195;
        // volver.Gdx.graphics.getWidth() = 150;
        // volver.Gdx.graphics.getHeight() = 100;

        // jugar = new Rectangle();
        // jugar.x = 1409;
        // jugar.y = 308;
        // jugar.Gdx.graphics.getWidth() = 150;
        // jugar.Gdx.graphics.getHeight() = 100;

        // tu = new Rectangle();
        // tu.x = 1409;
        // tu.y = 400;
        // tu.Gdx.graphics.getWidth() = 150;
        // tu.Gdx.graphics.getHeight() = 100;


        modo = m;
        idcuenta = game.user;
        usuario = consultarUsuario();
        //idcuenta = Integer.parseInt(game.user);





        Empresa=new Texture("Ranking/Empresas.png");
        Fisica=new Texture("Ranking/Fisica.png");
        Talf=new Texture("Ranking/TALF.png");
        Operativos=new Texture("Ranking/SistemasO.png");
        datos=new Texture("Ranking/BDpng.png");
        software=new Texture("Ranking/MDiscretas.png");
        volveri = new Texture("Ranking/jugar.png");
        tuyo = new Texture("Ranking/TURanking.png");
        ju = new Texture("Ranking/volver.png");


        if(modo == 0){
            consulta1 = consultar((1));
            consulta2 = consultar((2));
            consulta3 = consultar((3));
            consulta4 = consultar((4));
            consulta5 = consultar((5));
            consulta6 = consultar((6));
            
        }else if (modo == 1){
            consultas = consutarMejor(1, 1);
            con1 = consutarMejornombre(1, 1);
            
        }else if (modo == 2){
            consultas = consutarMejor(2, 1);
            con1 = consutarMejornombre(2, 1);
            
        }else if (modo == 3){
            consultas = consutarMejor(3, 1);
            con1 = consutarMejornombre(3, 1);
            
        }else if (modo == 4){
            consultas = consutarMejor(4, 1);
            con1 = consutarMejornombre(4, 1);
            
        }else if (modo == 5){
            consultas = consutarMejor(5, 1);
            con1 = consutarMejornombre(5, 1);
            
        }else if (modo == 6){
            consultas = consutarMejor(6, 1);
            con1 = consutarMejornombre(6, 1);
            
        }
        


        
    }

    public String consultarUsuario()throws SQLException {
        String consulta = "select nombrecuenta from cuenta where id_cuenta =" + idcuenta;
        System.out.println(consulta);
        String respuesta= "Error";
        ResultSet rset;
                
                try {
                    rset =  stmt.executeQuery(consulta);
                    if(rset.next()){
                        respuesta = rset.getString(1);
                    }else{
                        respuesta= "Sin puntuación";
                    }
                } catch (SQLException e1) {
                   
                    e1.printStackTrace();
                }
                return respuesta;
}
    
    public String consultar(int idnivel)throws SQLException {
            String consulta = "SELECT puntos"+" FROM puntuacion where id_nivel = '"+idnivel +"' AND id_cuenta ='"+idcuenta+"'";
            System.out.println(consulta);
            String respuesta= "Error";
            ResultSet rset;
                    
                    try {
                        rset =  stmt.executeQuery(consulta);
                        if(rset.next()){
                            respuesta = rset.getString(1);
                        }else{
                            respuesta= "Sin puntuación";
                        }
                    } catch (SQLException e1) {
                       
                        e1.printStackTrace();
                    }
                    return respuesta;
    }

    public String[] consutarMejor(int idnivel, int pos)throws SQLException {
        String consulta = "SELECT puntos FROM ( SELECT puntos FROM puntuacion where id_nivel = '" + idnivel +"' ORDER BY puntos desc ) WHERE rownum <= 10";
        System.out.println(consulta);
        
        String respuesta[]= new String[10];
        ResultSet rset;
                
                try {
                    rset =  stmt.executeQuery(consulta);
                    for(int i = 0; i < 10; i++){
                        if(rset.next()){
                        
                        try {
                            respuesta[i] = rset.getString(pos);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                           
                        

                        }else{
                        respuesta[i]= "Sin puntuación";
                        }
                    }
                    
                } catch (SQLException e1) {
                   
                    e1.printStackTrace();
                }
                return respuesta;
    }

    public String[] consutarMejornombre(int idnivel, int pos)throws SQLException {
        String consulta = "select nombrecuenta from cuenta where id_cuenta in (SELECT  id_cuenta FROM ( SELECT id_cuenta FROM puntuacion where id_nivel = '" + idnivel + "' ORDER BY puntos desc ) WHERE rownum <= 10)";
        System.out.println(consulta);
        
        String respuesta[]= new String[10];
        ResultSet rset;
                
                try {

                    rset =  stmt.executeQuery(consulta);
                    for (int i = 0; i < 10; i++){
                        if(rset.next()){
                        try {
                            respuesta[i] = rset.getString(pos);
                        } catch (SQLException e1) {
                            respuesta[i]= "Disponible";
                        }
                        }else{
                        respuesta[i]= "Disponible";
                    }
                    }
                    
                } catch (SQLException e1) {
                   
                    e1.printStackTrace();
                }
                return respuesta;
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        
        //game.batch.begin();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

		stage.act(delta);
        stage.draw();
		
        game.batch.begin();
		game.batch.draw(background,0,0);
        //game.batch.draw(imgtabla,0,0);
        game.batch.draw(tuyo, 0, 0);
        game.batch.draw(Empresa, 0, 0);
        game.batch.draw(Fisica, 0, 0);
        game.batch.draw(Talf, 0, 0);
        game.batch.draw(Operativos, 0, 0);
        game.batch.draw(datos, 0, 0);
        game.batch.draw(software, 0, 0);
        //game.batch.draw(volveri, volver.x, volver.y);
        game.batch.draw(ju, 0, 0);

        game.batch.end();

        if (modo == 0){
            user.begin();
            font.draw(user, "Usuario:",Gdx.graphics.getWidth()/5 , (Gdx.graphics.getHeight()/11)*7);
            font.draw(user, usuario,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/11)*7);
            font.draw(user, "Dojo1:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*7);
            font.draw(user, "Dojo2:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*6);
            font.draw(user, "Dojo3:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*5);
            font.draw(user, "Dojo4:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*4);
            font.draw(user, "Dojo5:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*3);
            font.draw(user, "Dojo6:",Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/12)*2);
            

            font.draw(user, consulta1,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*7);
            font.draw(user, consulta2,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*6);
            font.draw(user, consulta3,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*5);
            font.draw(user, consulta4,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*4);
            font.draw(user, consulta5,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*3);
            font.draw(user, consulta6,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/12)*2);
            user.end();

        }else{
            user.begin();
            font.draw(user, "Usuario:",Gdx.graphics.getWidth()/5 , (Gdx.graphics.getHeight()/18)*13);
            font.draw(user, usuario,Gdx.graphics.getWidth()/3 , (Gdx.graphics.getHeight()/18)*13);
            font.draw(user, con1[0],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*12);
            font.draw(user, con1[1],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*11);
            font.draw(user, con1[2],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*10);
            font.draw(user, con1[3],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*9);
            font.draw(user, con1[4],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*8);
            font.draw(user, con1[5],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*7);
            font.draw(user, con1[6],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*6);
            font.draw(user, con1[7],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*5);
            font.draw(user, con1[8],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*4);
            font.draw(user, con1[9],Gdx.graphics.getWidth()/4 , (Gdx.graphics.getHeight()/18)*3);

            
            font.draw(user, consultas[0],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*12);
            font.draw(user, consultas[1],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*11);
            font.draw(user, consultas[2],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*10);
            font.draw(user, consultas[3],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*9);
            font.draw(user, consultas[4],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*8);
            font.draw(user, consultas[5],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*7);
            font.draw(user, consultas[6],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*6);
            font.draw(user, consultas[7],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*5);
            font.draw(user, consultas[8],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*4);
            font.draw(user, consultas[9],Gdx.graphics.getWidth()/2 , (Gdx.graphics.getHeight()/18)*3);


            user.end();

            game.batch.begin();
            game.batch.draw(volveri, 0, 0);
            //game.font.draw(game.batch, Gdx.input.getX() + ", " + Gdx.input.getY(), 100, 150);
            game.batch.end();
        }

        if (Gdx.input.isTouched()) {
            System.out.println(Gdx.graphics.getHeight());
            if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/200)*147 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*94 && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*80 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*87 )) && modo != 0){
                game.MapSound.dispose();
                System.out.println("j");
               try {
                   game.setScreen(new Quiz(game, 0, 0, 180*1000000, modo));
                } catch (SQLException e) {
                    
                   e.printStackTrace();
               }

            } else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/200)*147 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*94 && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/200)*141 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*78 ))){
                game.MapSound.dispose();
                System.out.println("v");
                game.setScreen(new Mapa(game));//boton volver
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/200)*147 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*94 && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*61 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*69 ))){
                game.MapSound.dispose();
                System.out.println("t");//boton tu ranking
                try {
                    game.setScreen(new Ranking(game,0));
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*81 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*92+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("i");
                try {
                    game.setScreen(new Ranking(game,6));//boton introduccion software
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*68 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*79+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("b");
                try {
                    game.setScreen(new Ranking(game,5));//boton base de datos
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*55 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*66+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("s");
                try {
                    game.setScreen(new Ranking(game,4));//boton sistemas operativos
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*42 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*53+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("t");
                try {
                    game.setScreen(new Ranking(game,3));//boton Talf
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*29 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*40+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("f");
                try {
                    game.setScreen(new Ranking(game,2));//boton fisica
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if((Gdx.input.getX() >= (Gdx.graphics.getWidth()/100)*16 && Gdx.input.getX() <= (Gdx.graphics.getWidth()/100)*27+(Gdx.graphics.getWidth()/200) && 
            (Gdx.input.getY() >= (Gdx.graphics.getHeight()/100)*28 && Gdx.input.getY() <= (Gdx.graphics.getHeight()/100)*31 ))){
                game.MapSound.dispose();
                System.out.println("e");
                try {
                    game.setScreen(new Ranking(game,1));//boton empresa
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }

    @Override
    public void resize(int width1, int height1) {
        //stage.getCamera().position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
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
