package org.prRedSocialArray;

public class RestriccionNombre implements TipoRestriccion{

     private char letra;

     public RestriccionNombre(char letra){
          this.letra = letra;
     }

     @Override
     public boolean valida(Persona persona){
          return this.letra == persona.getNombre().charAt(0);
     }

}
