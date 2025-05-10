package org.prRedSocialArray;

import java.util.Objects;

public class Persona {

     private String nombre;
     private int edad;
     private String email;

     public Persona(String nombre, int edad, String email){
          if(edad<0)
               throw new RedSocialException("ERROR. Edad negativa");
          if(nombre == null)
               throw new RedSocialException("ERROR. Nombre nulo");
          if(email == null)
               throw new RedSocialException("ERROR. Email nulo");

          this.nombre = nombre;
          this.edad = edad;
          this.email = email;
     }

     public String getNombre() {
          return nombre;
     }

     public int getEdad() {
          return edad;
     }

     public String getEmail() {
          return email;
     }

     @Override
     public boolean equals(Object o){
          if(this == o)
               return true;
          return (o instanceof Persona otraPersona) && this.nombre.equalsIgnoreCase(otraPersona.nombre) && this.email.equals(otraPersona.email);
     }

     @Override
     public int hashCode(){
          return Objects.hash(this.nombre.toLowerCase(), this.email);
     }

     @Override
     public String toString(){
          return this.nombre + " " + this.edad + "(" + this.email + ")";
     }

}
