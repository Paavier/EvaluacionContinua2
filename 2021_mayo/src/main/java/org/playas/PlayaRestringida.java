package org.playas;

public class PlayaRestringida extends Playa {

     private int porcentaje;

     public PlayaRestringida (String nombre, String localidad, double latitud, double longitud, int aforoMaximo){
          super(nombre, localidad, latitud, longitud, aforoMaximo);
          this.porcentaje = 0;
     }

     public void setPorcentaje(int porcentaje){
          if(porcentaje < 0 || porcentaje > 100)
               throw new PlayaException("ERROR. Porcentaje no valido");
          this.porcentaje = porcentaje;
     }

     @Override
     public void setNumeroPersonas(int numeroPersonas){
          super.setNumeroPersonas(numeroPersonas);

          if(numeroPersonas == 0){
               this.estado = "VACIO";
          } else if (numeroPersonas < ((25.0 * this.porcentaje)/100) * this.getAforoMaximo()) {
               this.estado = "LEVE";
          } else if (numeroPersonas < ((75.0 * this.porcentaje)/100) * this.getAforoMaximo()) {
               this.estado = "MODERADO";
          } else if (numeroPersonas < this.porcentaje * this.getAforoMaximo()) {
               this.estado = "SATURADO";
          }else {
               this.estado = "SIN ESPACIO";
          }
     }

     @Override
     public String toString(){
          return "[" + super.toString() + ", " + this.porcentaje + "%]";
     }


}
