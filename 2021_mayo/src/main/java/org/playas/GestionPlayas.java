package org.playas;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class GestionPlayas {

     private static final int CAP_INICIAL = 5;
     private String provincia;
     private int numPlayas;
     private Playa[] playas;

     public GestionPlayas(String provincia){
          this.provincia = provincia;
          this.numPlayas = 0;
          this.playas = new Playa[CAP_INICIAL];
     }

     public GestionPlayas (String provincia, String[] informacionPlayas){
          this.provincia = provincia;
          this.numPlayas = 0;
          this.playas = new Playa[CAP_INICIAL];
          rellenarArrayPlayas(informacionPlayas);
     }

     private void rellenarArrayPlayas(String[] informacionPlayas){
          String[] datosPlaya;
          Playa playa;
          for(String datos: informacionPlayas){
               try{
                    datosPlaya = datos.split(";");
                    if(datosPlaya.length != 5)
                         throw new PlayaException("ERROR. Faltan datos en la linea: " + datos);
                    playa = new Playa(datosPlaya[0], datosPlaya[1], Double.parseDouble(datosPlaya[2]), Double.parseDouble(datosPlaya[3]), Integer.parseInt(datosPlaya[4]));
                    incluye(playa);
               }catch (PlayaException e){
                    System.err.println(e.getMessage());
               }catch(NumberFormatException e){
                    System.err.println("ERROR. Valor no numerico");
               }
          }
     }

     private int posicion (Playa playa){
          int posicion = -1;
          boolean encontrada = false;

          for(int i = 0; i < numPlayas && !encontrada; i++){
               if(this.playas[i].equals(playa)){
                    posicion = i;
                    encontrada = true;
               }
          }

          return posicion;
     }

     private void duplicarTamaño(){
          Playa[] playasAuxiliar = new Playa[2 * this.playas.length];
          for(int i = 0; i < this.playas.length; i++){
               playasAuxiliar[i] = this.playas[i];
          }

          //se podria haber usado lo siguiente
          //Playa[] playasAuxiliar = Arrays.copyOf(playas, 2 * this.playas.length);

          this.playas = playasAuxiliar;
     }

     public void incluye(Playa playa){
          int posicion = posicion(playa);
          if(posicion == -1){
               if(this.numPlayas == this.playas.length){
                    duplicarTamaño();
                    this.playas[this.numPlayas] = playa;
                    this.numPlayas++;
               }
          }else{
               this.playas[posicion] = playa;
          }
     }

     private Playa buscar(double latitud, double longitud){
          Playa playaAuxiliar = new Playa("", "", latitud, longitud, 0);
          int posicion = posicion(playaAuxiliar);

          if(posicion == -1)
               throw new PlayaException("ERROR. Playa no encontrada");

          return this.playas[posicion];
     }

     private void procesar(String informacionPlaya){
          String[] datosPlaya = informacionPlaya.split(";");
          Playa playa;
          try{
               if(datosPlaya.length != 3)
                    throw new PlayaException("ERROR. Faltan datos en la linea : " + informacionPlaya);
               playa = buscar(Double.parseDouble(datosPlaya[0]), Double.parseDouble(datosPlaya[1]));
               playa.setNumeroPersonas(Integer.parseInt(datosPlaya[2]));
          }catch (PlayaException e){
               System.err.println(e.getMessage());
          } catch (NumberFormatException e) {
               System.err.println("ERROR. Valor no numerico");
          }
     }

     public void registrarOcupacion(String nombreFichero) throws IOException {
          Path ruta = Path.of(nombreFichero);
          try(BufferedReader bufferedReader = Files.newBufferedReader(ruta)){
               String linea = bufferedReader.readLine();
               while(linea != null) {
                    procesar(linea);
                    linea = bufferedReader.readLine();
               }
          }
     }

     @Override
     public String toString(){
          StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
          for(int i = 0; i<this.numPlayas; i++){
               stringJoiner.add(playas[i].toString());
          }
          return stringJoiner.toString();
     }
}
