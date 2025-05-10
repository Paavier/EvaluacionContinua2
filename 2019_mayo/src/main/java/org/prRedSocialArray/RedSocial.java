package org.prRedSocialArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RedSocial {

     private static final int INIT_RED = 1;
     private int numeroPersonas;
     private Persona[] red;

     public RedSocial(){
          this.numeroPersonas = 0;
          red = new Persona[INIT_RED];
     }

     public RedSocial(int tamañoInicial){
          if(tamañoInicial < 0)
               throw new RedSocialException("ERROR. Tamaño inicial negativo");
          this.numeroPersonas = 0;
          red = new Persona[tamañoInicial];
     }

     private int buscar(Persona persona){
          int posicion = -1;
          boolean encontrado = false;

          for(int i = 0; i<this.numeroPersonas && !encontrado; i++){
               if(this.red[i].equals(persona)){
                    encontrado = true;
                    posicion = i;
               }
          }

          return posicion;
     }

     public void darAlta(Persona persona){
          if(persona == null)
               throw new RedSocialException("ERROR. Persona nula");

          int posicion = buscar(persona);
          if(posicion == -1){
               if(this.numeroPersonas == this.red.length)
                    duplicarCapacidad();
               this.red[numeroPersonas] = persona;
               this.numeroPersonas++;
          }
     }

     private void duplicarCapacidad(){
          Persona[] redAuxiliar = new Persona[2 * this.red.length];
          for(int i = 0; i < this.red.length; i++){
               redAuxiliar[i] = this.red[i];
          }
          this.red = redAuxiliar;
     }

     public void darAlta(String nombreFichero) throws IOException {
          Path rutaFichero = Path.of(nombreFichero);
          try(BufferedReader bufferedReader = Files.newBufferedReader(rutaFichero)){
               String linea = bufferedReader.readLine();
               while (linea != null){
                    procesar(linea);
                    bufferedReader.readLine();
               }
          }
     }

     private void procesar(String datosPersona){
          //El + es para separarlos por una O MAS ocurrencias
          //El catch asumo que hay que mostrarlos por pantalla, sino, pues se deja en blanco y arreando
          try{
               String[] datos = datosPersona.split("[%]+");
               if(datos.length != 3)
                    throw new RedSocialException("ERROR. Faltan datos");
               Persona persona = new Persona(datos[0], Integer.parseInt(datos[1]), datos[2]);
               darAlta(persona);
          }catch (RedSocialException e){
               System.err.println(e.getMessage());
          }catch (NumberFormatException e){
               System.err.println("ERROR. Valor no numerico");
          }
     }

     @Override
     public String toString(){
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = 0; i < this.numeroPersonas; i++){
               // el "\n" es para añadiir un salto de linea al final
               stringBuilder.append(this.red[i]).append("\n");
          }
          return stringBuilder.toString();
     }
}
