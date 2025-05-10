package org.prRedSocialArray;

public class RedSocialConRestriccion extends RedSocial{

     private TipoRestriccion tipoRestriccion;
     public RedSocialConRestriccion(TipoRestriccion tipoRestriccion){
          super();
          this.tipoRestriccion = tipoRestriccion;
     }

     @Override
     public void darAlta(Persona persona){
          if(persona == null)
               throw new RedSocialException("ERROR. Persona nula");
          if(tipoRestriccion.valida(persona))
               super.darAlta(persona);
     }
}
