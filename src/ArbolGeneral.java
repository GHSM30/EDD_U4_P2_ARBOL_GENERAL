/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MEMO0464
 */
public class ArbolGeneral {
    NodoGeneral raiz;
    int altura;
    public ArbolGeneral(){
        raiz = null;
    }
    
    public boolean insertar(char dato, String path){
        /*
        -Si raiz es null
        -Si path vacio
        -buscar padr
        -crear hijo (siempre que no exista otro hijo con el mismo nombre)
        -enlazar padre con hijo
        */
        
        if(raiz == null){
            raiz = new NodoGeneral(dato);
            if(raiz == null) return false;
            return true;
        }
        
        if(path.isEmpty()) return false;
        
        NodoGeneral padre = buscarNodoRe(path, path);
        if(padre == null) return false;
  
        NodoGeneral nuevo = new NodoGeneral(dato);
        return padre.enlazar(nuevo);
    }

    
    
    private NodoGeneral buscarNodo(String path) {  // version 1 de buscar Nodo con CICLO
        // -> /F/W/M/R
        path = path.substring(1);
        // -> F/W/M/R
        String vector[] = path.split("/");
        
        if(vector[0].charAt(0) == raiz.dato){
           //El vector solo contiene una letra ? es decir solo hay raiz como padre
           if(vector.length == 1) return raiz;
            NodoGeneral padre = raiz;
            for(int i = 1; i < vector.length; i++){
                padre = padre.obtenerHijo(vector[i].charAt(0));
                if(padre == null) return null;
            }
            return padre;
        }
        return null;  // No coincidio celdilla 0 con raiz 
    }
    
    
    //----------------------------------------------------------------------------------------------------------------------------------------------------------
    private NodoGeneral buscarNodoRe(String path , String igual) {  // version 1 de buscar Nodo con RECURCIBIDAD
        int tam = 0;
        int tam2 = 0;

        // -> /F/W/M/R
        path = path.substring(1);

        // -> F/W/M/R
        tam = path.length();
        tam2 = igual.length();
        tam2 = tam2 /2 ;
        if(igual.charAt(1) == raiz.dato){

           if(tam2 == 1) return raiz;
            NodoGeneral padre = raiz;
                
            if(tam == 0){
                padre = padre.obtenerHijo(path.charAt(0));
                buscarNodoRe(path.substring(1), igual);
            }
            if(padre == null) return null;
            return padre;
        }
        return null;   
    }
   //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    public boolean eliminar(String path){
        if(raiz == null) return false;        
        NodoGeneral hijo = buscarNodo(path);
        if(hijo == null) return false;
        if(!hijo.esHoja()) return false;
        if(hijo == raiz){
            raiz = null;
            return true;
        }
        
        String pathPadre= obtenerPathPadre(path);
        NodoGeneral padre = buscarNodo(path);
        
        return padre.desenlazar(hijo);
    }

    private String obtenerPathPadre(String path) {
        int posicionAntesUltimaDiagonal = path.lastIndexOf("/")-1;
        return path.substring(0, posicionAntesUltimaDiagonal);
    }
    
   
}
