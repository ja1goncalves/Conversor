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
        int v = 0;
        
        if(digitos.length() < 32) {
        	for(int j = 0; j < 32 - digitos.length();) {
        		digitos = "0"+digitos;
        	}
        }
        
        for(int i = 0; i < digitos.length(); i++){
            if(b == 2){
                 if(digitos.charAt(digitos.length()-1-i)-'0' > 1){
                         return "Número "+(digitos.charAt(digitos.length()-1-i)-'0') +" não correspondem a base";
                }
                if(digitos.length()-1-i == 0) {
                        break;
                }
                if(digitos.charAt(0) == '1'){
                    v += (1 - (digitos.charAt(digitos.length()-1-i)-'0'))*Math.pow(b,i);
                }else{
                    v += (digitos.charAt(digitos.length()-1-i)-'0')*Math.pow(b,i);
                }
            }else if(b == 8){
                if(digitos.charAt(digitos.length()-1-i)-'0' > 7){
                     return "Número "+(digitos.charAt(digitos.length()-1-i)-'0') +" não correspondem a base";
                }
                if(digitos.length()-1-i == 0) {
            		break;
            	}
                if(digitos.charAt(0) == '7'){
                    v += (7 - (digitos.charAt(digitos.length()-1-i)-'0'))*Math.pow(b,i);
                }else{
                    v += (digitos.charAt(digitos.length()-1-i)-'0')*Math.pow(b,i);
                }
            }else if(b == 16){
                if(digitos.charAt(0) == 'F'){
                    if(digitos.charAt(digitos.length()-1-i)-'0' > 9){
                        v += (15 - (digitos.charAt(digitos.length()-1-i)-'7'))*Math.pow(b,i);
                    }else
                        v += (15 - (digitos.charAt(digitos.length()-1-i)-'0'))*Math.pow(b,i);
                }else{
                    if(digitos.charAt(digitos.length()-1-i) - '7' > 9){
                        v += (digitos.charAt(digitos.length()-1-i) - '7')*Math.pow(b, i);
                    }else{
                        v += (digitos.charAt(digitos.length()-1-i)-'0')*Math.pow(b,i);
                    }
                }
            }else{
                return "Base incompatível com o sistema.";
            }
        }

    	if(((digitos.charAt(0) == '1') && (b == 2))
                || ((digitos.charAt(0) == '7') && (b == 8)) 
                || ((digitos.charAt(0) == 'F') && (b == 16))) {
    		v = v*(-1);
    	}
        return String.valueOf(v);
    }
    
    public static String converterDecimalParaBase(int valor, int b){
    	if(valor/b == 0){
            String newDigito= "";
            if(valor%b < 1){
                digitos = String.valueOf(valor%b*(-1)) + digitos;
            }else{
                digitos = String.valueOf(valor%b) + digitos;
            }
            if(digitos.length() < 32) {
        	for(int j = 0; j < 32 - digitos.length();) {
        		digitos = "0"+digitos;
        	}
        }
            if((valor < 0)) {
                for(int i = 0; i < digitos.length();i++){
                    if(b == 2){
                        newDigito = String.valueOf(1 - (digitos.charAt(digitos.length()-1-i) - '0')) + newDigito;
                    }else if(b == 8){
                        newDigito = String.valueOf(7 - (digitos.charAt(digitos.length()-1-i) - '0')) + newDigito;
                    }else if(b == 16){
                        if(digitos.charAt(digitos.length()-1-i) >= 'A')
                            newDigito = String.valueOf(15 - (digitos.charAt(digitos.length()-1-i) - '7')) + newDigito;
                        else{
                            if(15 - (digitos.charAt(digitos.length()-1-i) - '0') > 9){
                                newDigito = String.valueOf((char)(70 - (digitos.charAt(digitos.length()-1-i) - '0'))) + newDigito;
                            }else
                                newDigito = String.valueOf(15 - (digitos.charAt(digitos.length()-1-i) - '0')) + newDigito;
                        }
                    }
                }
            }else{
                return digitos;
            }
            return newDigito;
        }else{
            int resto = valor%b;
            if(resto < 0) {
            	resto = resto*(-1);
            }
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
        System.out.println(converterDecimalParaBase(-92, 16));
        System.out.println(converterBaseParaDecimal("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA3", 16));
    }
    
}