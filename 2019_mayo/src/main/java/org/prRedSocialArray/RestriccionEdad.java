package org.prRedSocialArray;

public class RestriccionEdad implements TipoRestriccion{

     private int edadMinima;

     public RestriccionEdad(int edadMinima){
          if(edadMinima < 0)
               throw new RedSocialException("ERROR. Edad minima negativa");
          this.edadMinima = edadMinima;
     }

     @Override
     public boolean valida(Persona persona){
          return persona.getEdad() >= this.edadMinima;
     }
}
