package com.mygdx;

import org.junit.*;
import java.sql.*;
import java.util.Random;

public class testBaseDeDatos {
    Connection con;
    Statement stmt;
    ResultSet rset;
    public testBaseDeDatos() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "UBD4191", "dinine1234");
        stmt = con.createStatement();
    }
    @Test
    public void comprobarLogin() throws SQLException {
        String usuario = "dan";
        String correo = "dan";
        String contraseña = "dan";

        String consulta = "SELECT contraseña"+" FROM cuenta where nombrecuenta='"+usuario+"'";
        rset = null;
        rset = stmt.executeQuery(consulta);

        if(rset.next())org.junit.Assert.assertEquals(contraseña,rset.getString(1));
        else System.out.println("Error: contraseña("+contraseña+") no encontrada");
        consulta = "SELECT correo"+" FROM cuenta where nombrecuenta='"+usuario+"'";
        rset = null;
        rset = stmt.executeQuery(consulta);
        rset.next();
        if(rset.next())org.junit.Assert.assertEquals(correo,rset.getString(1));
        else System.out.println("Error: correo("+correo+") no encontrada");
    }
    @Test
    public void comprobarPregunta() throws SQLException {
        int nDojo = 4;
        Random r = new Random();
        int nPreguntas;

        String numPreg = "SELECT COUNT(NIVEL_ID_NIVEL) FROM PREGUNTA WHERE NIVEL_ID_NIVEL = " + nDojo +" GROUP BY NIVEL_ID_NIVEL";
        rset = stmt.executeQuery(numPreg);
        rset.next();
        nPreguntas = rset.getInt(1);

        int pregActual = r.nextInt(1,nPreguntas);
        String consultaPreg = "SELECT TEXTOPREGUNTA, ID_PREGUNTA FROM (SELECT TEXTOPREGUNTA, ID_PREGUNTA, ROWNUM rn FROM PREGUNTA WHERE NIVEL_ID_NIVEL = " + nDojo + ") WHERE rn = " + pregActual;
        rset = stmt.executeQuery(consultaPreg);
        rset.next();
        String pregunta = rset.getNString(1);
        int idPregunta = rset.getInt(2);

        String consulta = "SELECT NIVEL_ID_NIVEL FROM PREGUNTA WHERE ID_PREGUNTA = "+idPregunta;
        rset = stmt.executeQuery(consulta);
        rset.next();
        int dojo = rset.getInt(1);

        org.junit.Assert.assertEquals(nDojo,dojo);
    }
}
