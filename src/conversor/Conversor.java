/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

/**
 *
 * @author joaog
 */
public class Conversor {
    
    private static String digitos = "";
    
    public static String converterBaseParaDecimal(String digitos, int b){
        int v = 0, s = digitos.length();
        
        for(int i = 0; i < s; i++){
            if(b == 2){
                if(digitos.charAt(s-1-i)-'0' > 1){
                     return "Número "+(digitos.charAt(s-1-i)-'0') +" não correspondem a base";
                }
                v += (digitos.charAt(s-1-i)-'0')*Math.pow(b,i);
            }else if(b == 8){
                if(digitos.charAt(s-1-i)-'0' > 7){
                     return "Número "+(digitos.charAt(s-1-i)-'0') +" não correspondem a base";
                }
                v += (digitos.charAt(s-1-i)-'0')*Math.pow(b,i);
            }
            if(b == 16){
                /*
                if((int)digitos.charAt(s-1-i) > 47 && (int)digitos.charAt(s-1-i) < 58){
                    v += (digitos.charAt(s-1-i)-'0')*Math.pow(b,i);
                }
                if(((int) digitos.charAt(s-1-i) > 64) && ((int) digitos.charAt(s-1-i) < 71)){
                    v += ((int)digitos.charAt(s-1-i)-55)*Math.pow(b,i);
                }*/
                for (int k = 10; k < b; k++){
                    if((int)digitos.charAt(s-1-i) - k == 55){
                        v += k*Math.pow(b, i);
                        break;
                    }else{
                        v += (digitos.charAt(s-1-i)-'0')*Math.pow(b,i);
                        break;
                    }
                }
            }
        }
        
        return String.valueOf(v);
    }
    
    public static String converterDecimalParaBase(int valor, int b){
        if(valor/b == 0){
            digitos = String.valueOf(valor%b) + digitos;
            return digitos;
        }else{
            int resto = valor%b;
            if(resto > 9){
                resto += 55;
                digitos = String.valueOf((char)resto) + digitos;
            }else{
                digitos = String.valueOf(resto) + digitos;
            }
            return converterDecimalParaBase(valor/b, b);
        }
    } 
    
    public static void main(String[] args){
        System.out.println(converterDecimalParaBase(1078, 2));
    }
    
}