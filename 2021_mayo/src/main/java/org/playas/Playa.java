package org.playas;

import java.util.Locale;
import java.util.Objects;

public class Playa {

     private String nombre;
     private String localidad;
     private final double latitud;
     private final double longitud;
     private final int aforoMaximo;
     private int numeroPersonas;
     protected String estado;

     public Playa(String nombre, String localidad, double latitud, double longitud, int aforoMaximo){
          if(aforoMaximo < 0)
               throw new PlayaException("ERROR. Aforo negativo");

          this.nombre = nombre;
          this.localidad = localidad;
          this.latitud = latitud;
          this.longitud = longitud;
          this.aforoMaximo = aforoMaximo;
          this.numeroPersonas = 0;
          this.estado = "VACIO";
     }

     public double getLongitud() {
          return longitud;
     }

     public double getLatitud() {
          return latitud;
     }

     public int getAforoMaximo() {
          return aforoMaximo;
     }

     public String getEstado() {
          return estado;
     }

     public void setNumeroPersonas(int numeroPersonas){
          if(numeroPersonas < 0)
               throw new PlayaException("ERROR. Numero de personas negativo");
          this.numeroPersonas = numeroPersonas;

          if(numeroPersonas == 0){
               this.estado = "VACIO";
          } else if (numeroPersonas < (25.0/100) * this.aforoMaximo) {
               this.estado = "LEVE";
          } else if (numeroPersonas < (75.0/100) * this.aforoMaximo) {
               this.estado = "MODERADO";
          } else if (numeroPersonas < this.aforoMaximo) {
               this.estado = "SATURADO";
          }else {
               this.estado = "SIN ESPACIO";
          }
     }

     @Override
     public boolean equals(Object o){
          if(this == o)
               return true;
          return (o instanceof Playa otraPlaya) && this.latitud == otraPlaya.latitud && this.longitud == otraPlaya.longitud;
     }

     @Override
     public int hashCode(){
          return Objects.hash(this.latitud, this.longitud);
     }

     @Override
     public String toString(){
          //Cuando usas el String.format no concatenes usando + ya que puede causar algun error
          //Locale.US para que se ponga un punto decimal (e.g. 3.55)
          //%s es para un string, %d es para un numero decimal (int), %.f, es para fijar un numero exacto de decimales
          return String.format(Locale.US,
                  "(%s, %s, %.3f, %.3f, %d, %d - Nivel de aforo: %s)",
                  this.nombre,
                  this.localidad,
                  this.latitud,
                  this.longitud,
                  this.aforoMaximo,
                  this.numeroPersonas,
                  this.estado);
     }
}
