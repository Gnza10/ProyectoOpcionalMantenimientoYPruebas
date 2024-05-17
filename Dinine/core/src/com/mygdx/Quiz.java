package com.mygdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Dinine;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Quiz implements Screen {

    final Dinine game;

    OrthographicCamera camera;

    //Contador de por cual pregunta vamos, las preguntas acertadas, el tiempo del comienzo y la puntuacion

    int nDojo;
    int contador;
    int acertadas;
    long tiempoInicio;
    int puntuacion;

    //Bool que indica si se acabo la pantalla
    Boolean isEnded = false;

    //Area de las respuestas
    Rectangle rectRespuestaMarcada1;
    Rectangle rectRespuestaMarcada2;
    Rectangle rectRespuestaMarcada3;
    Rectangle rectRespuestaMarcada4;

    //Textura respuestas marcadas
    Texture respuestaMarcada1;
    Texture respuestaMarcada2;
    Texture respuestaMarcada3;
    Texture respuestaMarcada4;

    //textura revelacion respuesta
    Texture respuestaRevelada1;
    Texture respuestaRevelada2;
    Texture respuestaRevelada3;
    Texture respuestaRevelada4;

    //Integer que indica que respuesta fue revelada
    int revelada = -1;

    //Textura fondo
    Texture fondoPregunta;
    Texture fondoPelea;
    Texture fondoRespuestas;
    Texture profesor;

    //Textura derrota y victoria
    Texture victoria;
    Texture derrota;

    //Cargamos las respuestas correctas y erroneas
    Boolean uno = false;
    Boolean dos = false;
    Boolean tres = false;
    Boolean cuatro = false;

    //Texto pregunta y repuestas
    String pregunta;
    String respuesta1;
    String respuesta2;
    String respuesta3;
    String respuesta4;

    Random r;
    int nPreguntas;
    int pregActual;
    int idPregunta;
    int idRespCorrecta;

    //Tiempo para pasar a la siguiente pantalla
    long timerFinal;
    long tiempoYaPerdido;

    //Tiempo para finalizar los tests
    long timerTests;

    //Base de datos
    Connection con;
    Statement stmt;
    ResultSet rset;

    public Quiz(final Dinine game, int numeroJuego , int pregCorrectas, long miliSegTimer, int dojo) throws SQLException {
        this.game = game;

        //Play musica
        if(numeroJuego == 0){
            game.juego.setLooping(true);
            game.juego.play();
            game.juego.setVolume(game.volume);
        }

        //Dojo actual
        nDojo = dojo;

        //Contador de cuantas preguntas llevamos y las acertadas
        contador = numeroJuego;
        acertadas = pregCorrectas;

        //Timer de los tests
        tiempoInicio = TimeUtils.nanoTime()/1000;
        tiempoYaPerdido = miliSegTimer;

        //Creamos la camara y el Spritebatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        //Cargamos los Sprites de victoria y derrota
        victoria = new Texture(Gdx.files.internal("Quiz/BienHecho.png"));
        derrota = new Texture(Gdx.files.internal("Quiz/HasPerdido.png"));

        //Cargamos los Sprites del fondo
        fondoPregunta = new Texture(Gdx.files.internal("Quiz/Preguntas.png"));
        fondoPelea = new Texture(Gdx.files.internal("Quiz/Fondo.png"));
        if(dojo==1){
            profesor = new Texture(Gdx.files.internal("Quiz/DinoLargo.png"));
        }
        else if(dojo==2){
            profesor = new Texture(Gdx.files.internal("Quiz/DinoFisica.png"));
        }
        else if(dojo==3){
            profesor = new Texture(Gdx.files.internal("Quiz/TALF.png"));
        }
        else if(dojo==4){
            profesor = new Texture(Gdx.files.internal("Quiz/DinoTriceratops.png"));
        }
        else if(dojo==5){
            profesor = new Texture(Gdx.files.internal("Quiz/DinoDatos.png"));
        }
        else if(dojo==6){
            profesor = new Texture(Gdx.files.internal("Quiz/DinoSoftware.png"));
        }
        
        fondoRespuestas = new Texture(Gdx.files.internal("Quiz/RespuestasYContador.png"));

        //Conexion base de datos
        con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "UBD4191", "dinine1234");
        stmt = con.createStatement();

        //numero de preguntas del nivel
        String numPreg = "SELECT COUNT(NIVEL_ID_NIVEL) FROM PREGUNTA WHERE NIVEL_ID_NIVEL = " + nDojo +" GROUP BY NIVEL_ID_NIVEL";
        rset = stmt.executeQuery(numPreg);
        rset.next();
        nPreguntas = rset.getInt(1);

        //sacamos la pregunta
        r = new Random();
        pregActual = r.nextInt(1,nPreguntas);
        String consultaPreg = "SELECT TEXTOPREGUNTA, ID_PREGUNTA FROM (SELECT TEXTOPREGUNTA, ID_PREGUNTA, ROWNUM rn FROM PREGUNTA WHERE NIVEL_ID_NIVEL = " + nDojo + ") WHERE rn = " + pregActual;
        rset = stmt.executeQuery(consultaPreg);
        rset.next();
        pregunta = rset.getNString(1);
        idPregunta = rset.getInt(2);


        String consultaIDRespCorr = "SELECT ID_RESPUESTA FROM RESPUESTACORRECTA WHERE PREGUNTA_ID_PREGUNTA = " + idPregunta;
        rset = stmt.executeQuery(consultaIDRespCorr);
        rset.next();
        idRespCorrecta = rset.getInt(1);

        String consultaRespuestas = "SELECT TEXTOPREGUNTA, ID_RESPUESTA FROM RESPUESTA WHERE PREGUNTA_ID_PREGUNTA = " + idPregunta;
        rset = stmt.executeQuery(consultaRespuestas);
        //ArrayList arrayResp = new ArrayList<>();
        boolean yano = true;
        boolean there = true;
        for(int i = 0; i < 4; i++){
            /*
            int rand;
            do{
                rand = r.nextInt(0,3);
            }while(arrayResp.contains(rand));
            arrayResp.add(rand);

             */

            if(yano){
                there = rset.next();
            }

            if(there){
                if(rset.getInt(2) == idRespCorrecta){
                    switch (i){

                        case 0:
                            uno = true;
                            break;
                        case 1:
                            dos = true;
                            break;
                        case 2:
                            tres = true;
                            break;
                        case 3:
                            cuatro = true;
                            break;
                    }
                }
            }

            switch (i){
                case 0:
                    respuesta1 = rset.getNString(1);
                    break;
                case 1:
                    respuesta2 = rset.getNString(1);
                    break;
                case 2:
                    if(there){
                        respuesta3 = rset.getNString(1);
                    }
                    else{
                        yano = false;
                        respuesta3="";
                    }
                    break;
                case 3:
                    if(there){
                        respuesta4 = rset.getNString(1);
                    }
                    else{
                        yano = false;
                        respuesta4="";
                    }
                    break;
            }

        }

        //Cargamos los sprites de las respuestas a marcar, correctas y erroneas
        respuestaMarcada1 = new Texture(Gdx.files.internal("Quiz/MArribaIzq.png"));
        respuestaMarcada2 = new Texture(Gdx.files.internal("Quiz/MArribaDer.png"));
        respuestaMarcada3 = new Texture(Gdx.files.internal("Quiz/MAbajoIzq.png"));
        respuestaMarcada4 = new Texture(Gdx.files.internal("Quiz/MAbajoDer.png"));

        //Cargamos si son verdaderas o falsas las respuestas
        if(uno){
            respuestaRevelada1 = new Texture(Gdx.files.internal("Quiz/CorrectaArribaIzq.png"));
        }
        else{
            respuestaRevelada1 = new Texture(Gdx.files.internal("Quiz/FalsaArribaIzq.png"));
        }
        if(dos){
            respuestaRevelada2 = new Texture(Gdx.files.internal("Quiz/CorrectaArribaDer.png"));
        }
        else{
            respuestaRevelada2 = new Texture(Gdx.files.internal("Quiz/FalsaArribaDer.png"));
        }
        if(tres){
            respuestaRevelada3 = new Texture(Gdx.files.internal("Quiz/CorrectaAbajoIzq.png"));
        }
        else{
            respuestaRevelada3 = new Texture(Gdx.files.internal("Quiz/FalsaAbajoIzq.png"));
        }
        if(cuatro){
            respuestaRevelada4 = new Texture(Gdx.files.internal("Quiz/CorrectaAbajoDer.png"));
        }
        else{
            respuestaRevelada4 = new Texture(Gdx.files.internal("Quiz/FalsaAbajoDer.png"));
        }

        //Creamos los rectangulos de las preguntas
        rectRespuestaMarcada1 = new Rectangle(34,750,680,120);
        rectRespuestaMarcada2 = new Rectangle(745,750,680,120);
        rectRespuestaMarcada3 = new Rectangle(34,900,680,120);
        rectRespuestaMarcada4 = new Rectangle(745,900,680,120);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //Iniciamos Batch
        game.batch.begin();

        //Creamos fondo de la pregunta, pelea
        game.batch.draw(fondoPelea, 0, 0,fondoPelea.getWidth(), fondoPelea.getHeight());
        game.batch.draw(fondoPregunta, 0, 0 ,fondoPregunta.getWidth(), fondoPregunta.getHeight());
        game.batch.draw(fondoRespuestas, 0, 0 ,fondoRespuestas.getWidth(), fondoRespuestas.getHeight());
        game.batch.draw(profesor, 0, 0 ,profesor.getWidth(), profesor.getHeight());


        if(!isEnded){
            if((Gdx.input.getX() >= Gdx.graphics.getWidth()/50 && Gdx.input.getX() <= Gdx.graphics.getWidth()/50 + Gdx.graphics.getWidth()/2.9) && 
            (Gdx.input.getY() >= Gdx.graphics.getHeight()/(1.4) && Gdx.input.getY() <= Gdx.graphics.getHeight()/(1.5) + rectRespuestaMarcada1.getHeight())){
                if(Gdx.input.isTouched()){
                    timerFinal = TimeUtils.nanoTime();
                    revelada = 1;
                    isEnded = true;
                    if(uno){
                        acertadas++;
                    }
                }
                else{
                    game.batch.draw(respuestaMarcada1,0,0,respuestaMarcada1.getWidth(),respuestaMarcada1.getHeight());
                }
            }
            if((Gdx.input.getX() >=rectRespuestaMarcada1.x + Gdx.graphics.getWidth()/(2.75) && Gdx.input.getX() <= rectRespuestaMarcada1.x + Gdx.graphics.getWidth()/(2.75)+ Gdx.graphics.getWidth()/2.9) && 
            (Gdx.input.getY() >= Gdx.graphics.getHeight()/(1.4) && Gdx.input.getY() <= Gdx.graphics.getHeight()/(1.5) + rectRespuestaMarcada1.getHeight())){
                if(Gdx.input.isTouched()){
                    timerFinal = TimeUtils.nanoTime();
                    revelada = 2;
                    isEnded = true;
                    if(dos){
                        acertadas++;
                    }
                }
                else{
                    game.batch.draw(respuestaMarcada2,0,0,respuestaMarcada2.getWidth(),respuestaMarcada2.getHeight());
                }
            }
            if((Gdx.input.getX() >=rectRespuestaMarcada1.x  && Gdx.input.getX() <= rectRespuestaMarcada1.x + Gdx.graphics.getWidth()/2.8) && 
            (Gdx.input.getY() >= Gdx.graphics.getHeight()/(1.17) && Gdx.input.getY() <= Gdx.graphics.getHeight()/(1.17) + rectRespuestaMarcada1.getHeight()*0.8)){
                if(Gdx.input.isTouched()){
                    timerFinal = TimeUtils.nanoTime();
                    revelada = 3;
                    isEnded = true;
                    if(tres){
                        acertadas++;
                    }
                }
                else{
                    game.batch.draw(respuestaMarcada3,0,0,respuestaMarcada3.getWidth(),respuestaMarcada3.getHeight());
                }
            }
            if((Gdx.input.getX() >=rectRespuestaMarcada1.x + Gdx.graphics.getWidth()/(2.75) && Gdx.input.getX() <= rectRespuestaMarcada1.x + Gdx.graphics.getWidth()/(2.75)+ Gdx.graphics.getWidth()/2.9) && 
            (Gdx.input.getY() >= Gdx.graphics.getHeight()/(1.17) && Gdx.input.getY() <= Gdx.graphics.getHeight()/(1.17) + rectRespuestaMarcada1.getHeight()*0.8)){
                if(Gdx.input.isTouched()){
                    timerFinal = TimeUtils.nanoTime();
                    revelada = 4;
                    isEnded = true;
                    if(cuatro){
                        acertadas++;
                    }
                }
                else{
                    game.batch.draw(respuestaMarcada4,0,0,respuestaMarcada4.getWidth(),respuestaMarcada4.getHeight());
                }
            }
        }
        //Revelamos la respuesta
        switch (revelada){
            case 1:
                game.batch.draw(respuestaRevelada1,0,0,respuestaRevelada1.getWidth(),respuestaRevelada1.getHeight());
                break;
            case 2:
                game.batch.draw(respuestaRevelada2,0,0,respuestaRevelada2.getWidth(),respuestaRevelada2.getHeight());
                break;
            case 3:
                game.batch.draw(respuestaRevelada3,0,0,respuestaRevelada3.getWidth(),respuestaRevelada3.getHeight());
                break;
            case 4:
                game.batch.draw(respuestaRevelada4,0,0,respuestaRevelada4.getWidth(),respuestaRevelada4.getHeight());
                break;
        }

        //escribimos la pregunta
        game.font.getData().setScale(4);
        if(pregunta.length() > 50) game.font.getData().setScale(3);
        game.font.draw(game.batch, pregunta,  75, 1080 - 85, 1770, 50, true);
        game.font.getData().setScale(6);
        timerTests =tiempoYaPerdido - ((TimeUtils.nanoTime()/1000) - tiempoInicio);
        if((timerTests/1000000)%60 < 10){
            game.font.draw(game.batch, timerTests/60000000 + ":0" + (timerTests/1000000)%60, 1585, 240);
        }
        else{
            game.font.draw(game.batch, timerTests/60000000 + ":" + (timerTests/1000000)%60, 1585, 240);
        }

        //Escribimos las respuestas
        game.font.getData().setScale(1.5f);
        game.font.draw(game.batch, respuesta1,  50, 285, 650, 50, true);
        game.font.draw(game.batch, respuesta2,  760, 285, 650, 50, true);
        game.font.draw(game.batch, respuesta3,  50, 125, 650, 50, true);
        game.font.draw(game.batch, respuesta4,  760, 125, 650, 50, true);

        game.font.getData().setScale(4);
        //Porcentaje preguntas acertadas
        game.font.draw(game.batch, acertadas + "/15", 1615, 130);

        //Almaceno el ranking
        if(isEnded || timerTests <= 0){
            if(timerTests <= 0) puntuacion = acertadas * 100;
            else puntuacion = acertadas * 100 + (int)((timerTests*100)/(180000000L));
            String ranking = "SELECT * FROM PUNTUACION WHERE ID_CUENTA = " + game.user + " AND ID_NIVEL = " + nDojo;
            try {
                String consultaAdd;
                rset = stmt.executeQuery(ranking);
                if(!rset.next()){
                    consultaAdd = "INSERT INTO PUNTUACION VALUES(" + puntuacion + ", " + game.user +", " + nDojo + ")";
                    rset = stmt.executeQuery(consultaAdd);
                }
                else{
                    if(rset.getInt(1) < puntuacion) {
                        consultaAdd = "UPDATE PUNTUACION SET PUNTOS = " + puntuacion + " WHERE ID_CUENTA = " + game.user + " AND ID_NIVEL = " + nDojo;
                        rset = stmt.executeQuery(consultaAdd);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if((timerTests > 0 && timerTests < 100000) && !isEnded) timerFinal = TimeUtils.nanoTime();
        //Al pasar 0'5 segundos se pasa la pantalla
        if ((isEnded && (TimeUtils.nanoTime() - timerFinal > 500000000)) || timerTests <= 0){
            if(++contador < 15 && timerTests > 0){
                try {
                    game.setScreen(new Quiz(game, contador, acertadas, timerTests, nDojo));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                if(acertadas < 8){
                    game.batch.draw(derrota, 0, 0, derrota.getWidth(), derrota.getHeight());
                }
                else{
                    game.batch.draw(victoria, 0, 0, victoria.getWidth(), victoria.getHeight());
                }

                if((TimeUtils.nanoTime() - timerFinal > 6000000000L)){
                    game.juego.dispose();
                    game.setScreen(new Mapa(game));
                }
            }
        }
        game.batch.end();

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