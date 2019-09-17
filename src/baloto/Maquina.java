/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author RAFAEL RAMIREZ
 */
public class Maquina  {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String nomarchivo = "ventas.txt";
    String archivorevancha = "ventas2.txt";
    String nom2doarchivo = "sorteo.txt";
    StringTokenizer tokens;
    StringTokenizer tokens2;
    int cantotal = 0;
    boleto [] bol = new boleto[1000];
    boleto [] bolre = new boleto[1000];
    
    public void mostrar_resultados() throws FileNotFoundException, IOException{
        
        System.out.print("Numeros ganadores del Baloto: ");
        
        FileReader fr = new FileReader(nom2doarchivo);
        BufferedReader entrada_archivo = new BufferedReader(fr);
        String linea;
        
        linea = entrada_archivo.readLine();
        System.out.println(linea);
        System.out.print("Numeros ganadores del Baloto Revancha: ");
        linea = entrada_archivo.readLine();
        System.out.println(linea);
    }
    
    public int [] randomsixsiege(){
        //Este método genera los numeros aleatorios
        Random r = new Random();
        int [] numbers = new int[5];
        int flag = 0;
        for(int x = 0;x<5;x++){
            int ayudante = r.nextInt((43-1)+1) + 1;
                    if(x==0){
                        numbers[x] = ayudante;
                    }else if(x>0){
                        for(int y = 0;y<x;y++){
                            if(ayudante==numbers[y]){
                                flag = 1;
                                break;
                            }else{
                                flag = 0;
                            }
                        }
                        if(flag==1){
                            x--;
                        }else{
                            numbers[x] = ayudante;
                        }
                    }
                }
        return numbers;
    }
    
    public int [] ordenar(int [] numbers){
        int temp = 0;
        for(int x=0;x<numbers.length;x++){
            for(int y=x+1;y<numbers.length;y++){
                if(numbers[x]>numbers[y]){
                    temp = numbers[x];
                    numbers[x] = numbers[y];
                    numbers[y] = temp;
                }
            }
        }
        return numbers;
    }
    
    public int superbola(){
        Random r = new Random();
        int superbola = r.nextInt((16-1)+1) +1;
        return superbola;
    }
    
    public int menu() throws IOException{
        int error = 0;
        int opt = 0;
        
        System.out.println("Bienvenido al Baloto");
        
        do{
            try{
                
                System.out.println("1-Comprar");
                System.out.println("2-Sorteo");
                System.out.println("3-Salir");
                opt = Integer.parseInt(reader.readLine());
                if(opt>=4 || opt<=0){
                    System.out.println("Ingrese un numero válido");
                    error = 1;
                }else{
                    error = 0;
                }
            }catch(NumberFormatException e){
                System.out.println("Ingrese un numero");
                error=1;
            }
        }while(error!=0);
        
        return opt;
    }
    
    public void compra() throws IOException{
        int error = 0;
        int option = 0;
        int [] numbers = new int[5];
        int [] ordenados = new int[5];
        int superbola = 0;
        int flag = 0;
        FileWriter fw = new FileWriter(nomarchivo,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter salida_archivo = new PrintWriter(bw,true);
        
        FileWriter fw2 = new FileWriter(archivorevancha,true);
        BufferedWriter bw2 = new BufferedWriter(fw2);
        PrintWriter salida_archivo2 = new PrintWriter(bw2,true);
        
        do{
            try{
                
                System.out.println("1-Automático");
                System.out.println("2-Manual");
                System.out.println("3-Regresar al menú principal");
                option = Integer.parseInt(reader.readLine());
                if(option>=4 || option<=0){
                    System.out.println("Ingrese un numero válido");
                    error = 1;
                }else{
                    error = 0;
                }
            }catch(NumberFormatException e){
                System.out.println("Ingrese un numero");
                error=1;
            }
        }while(error!=0);
        
        switch(option){
            case 1:
                //Automatico, generar los numeros y guardarlos
                int ayudante = 0;
                
                numbers = randomsixsiege();
                
                superbola = superbola();
                
                for(int x = 0;x<numbers.length;x++){
                    System.out.println("Numero aleatorio no repetido: " + numbers[x]);
                }
                System.out.println("Superbalota: "+superbola);
                numbers = ordenar(numbers);
                //En este punto, ordenamos los numeros
                String sal = numbers[0]+"-"+numbers[1]+"-"+numbers[2]+"-"+numbers[3]+"-"+numbers[4]+"-"+superbola;
                //Aqui preguntamos si quiere ser el sorteo normal, o el baloto revancha
                System.out.println("¿Desea entrar al baloto revancha con este numero?");
                do{
                    try{

                        System.out.println("1-Si");
                        System.out.println("2-No");
                        option = Integer.parseInt(reader.readLine());
                        if(option>=3 || option<=0){
                            System.out.println("Ingrese un numero válido");
                            error = 1;
                        }else{
                            error = 0;
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Ingrese un numero");
                        error=1;
                    }
                }while(error!=0);
                
                if(option==1){
                    salida_archivo2.print(sal);
                    salida_archivo2.println();
                    salida_archivo2.close();
                    option=0;
                }else if(option==2){
                    salida_archivo.print(sal);
                    salida_archivo.println();
                    salida_archivo.close();
                    option=0;
                }
                
                break;
                
            case 2:
                //Manual, insertar los numeros y guardarlos
                flag=0;
                System.out.println("Ingrese 5 numeros no repetidos entre 1 y 42");
                int ayudante2 = 0;
                for(int x = 0;x<5;x++){
                    do{
                        try{
                            ayudante2 = Integer.parseInt(reader.readLine());
                            if(option>=44 || option<=0){
                                System.out.println("Ingrese un numero válido");
                                error = 1;
                            }else{
                                error = 0;
                            }
                        }catch(NumberFormatException e){
                            System.out.println("Ingrese un numero");
                            error=1;
                        }
                    }while(error!=0);
                    
                    if(x==0){
                        numbers[x] = ayudante2;
                    }else if(x>0){
                        for(int y=0;y<x;y++){
                            if(ayudante2==numbers[y]){
                                flag = 1;
                                break;
                            }else{
                                flag = 0;
                            }
                        }
                        if(flag==1){
                            x--;
                            System.out.println("Ingrese un numero que no este repetido");
                        }else{
                            numbers[x] = ayudante2;
                        }
                    }
                }
                    
                System.out.println("Ahora, ingrese el valor de la superbalota");
                    do{
                        try{
                            ayudante2 = Integer.parseInt(reader.readLine());
                            if(ayudante2>=17 || ayudante2<=0){
                                System.out.println("Ingrese un numero entre 16 y 1");
                                error = 1;
                            }else{
                                error = 0;
                                superbola = ayudante2;
                            }
                        }catch(NumberFormatException e){
                            System.out.println("Ingrese un numero");
                            error=1;
                        }
                    }while(error!=0);
                    
                
                
                for(int x = 0;x<numbers.length;x++){
                    System.out.println("Numero aleatorio no repetido: " + numbers[x]);
                }
                System.out.println("Superbalota: "+superbola);
                numbers = ordenar(numbers);
                //En este punto, ordenamos los numeros
                sal = numbers[0]+"-"+numbers[1]+"-"+numbers[2]+"-"+numbers[3]+"-"+numbers[4]+"-"+superbola;
                System.out.println("¿Desea entrar al baloto revancha con este numero?");
                do{
                    try{

                        System.out.println("1-Si");
                        System.out.println("2-No");
                        option = Integer.parseInt(reader.readLine());
                        if(option>=3 || option<=0){
                            System.out.println("Ingrese un numero válido");
                            error = 1;
                        }else{
                            error = 0;
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Ingrese un numero");
                        error=1;
                    }
                }while(error!=0);
                
                if(option==1){
                    salida_archivo2.print(sal);
                    salida_archivo2.println();
                    salida_archivo2.close();
                    option=0;
                }else if(option==2){
                    salida_archivo.print(sal);
                    salida_archivo.println();
                    salida_archivo.close();
                    option=0;
                }
                    
                
                
                break;
            case 3:
                break;
        }
    }
    
    public void sorteo() throws IOException{
        int opt = 0;
        int error = 0;
        int [] num2 = new int[5];
        int [] num3 = new int[5];
        int superbola1 = 0;
        int superbola2 = 0;
        Random r = new Random();
        
        do{
            try{
                
                System.out.println("1-Nuevo Sorteo");
                System.out.println("2-Resultados Baloto");
                System.out.println("3-Borrar las ventas realizadas");
                System.out.println("4-Numero Favorito");
                System.out.println("5-Regresar al menu principal");
                opt = Integer.parseInt(reader.readLine());
                if(opt>=6 || opt<=0){
                    System.out.println("Ingrese un numero válido");
                    error = 1;
                }else{
                    error = 0;
                }
            }catch(NumberFormatException e){
                System.out.println("Ingrese un numero");
                error=1;
            }
        }while(error!=0);
        
        switch(opt){
            case 1:
                FileWriter fw = new FileWriter(nom2doarchivo);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter salida_archivo = new PrintWriter(bw,true);
                String sal1;
                String sal2;
                
                num2 = randomsixsiege();
                num3 = randomsixsiege();
                num2 = ordenar(num2);
                num3 = ordenar(num3);
                superbola1 = superbola();
                superbola2 = superbola();
                sal1 = num2[0]+"-"+num2[1]+"-"+num2[2]+"-"+num2[3]+"-"+num2[4]+"-"+superbola1;
                sal2 = num3[0]+"-"+num3[1]+"-"+num3[2]+"-"+num3[3]+"-"+num3[4]+"-"+superbola2;
                salida_archivo.print(sal1);
                salida_archivo.println();
                salida_archivo.print(sal2);
                salida_archivo.println();
                salida_archivo.close();
                
                mostrar_resultados();
                //Nuevo Sorteo
                break;
            case 2:
                //Primero, total de ventas
                int [] numganadores = new int[5];
                int [] numrevancha = new int[5];
                int balotaganadora = 0;
                int balotarevancha = 0;
                //Hay que leer el archivo sorteo.txt y guardarlo ahi para futura evaluacion
                
                
                FileReader fr = new FileReader(nomarchivo);
                BufferedReader entrada_archivo = new BufferedReader(fr);
                String linea;
                
                FileReader f2 = new FileReader(nom2doarchivo);
                BufferedReader entrada_2 = new BufferedReader(f2);
                String lin2;
                String linRevancha;
                
                FileReader f3 = new FileReader(archivorevancha);
                BufferedReader entrada_revancha = new BufferedReader(f3);
                String lin3;
                
                lin2 = entrada_2.readLine();
                linRevancha = entrada_2.readLine();
                
                    tokens = new StringTokenizer(lin2);
                    for(int z = 0;z<5;z++){
                        numganadores[z] = Integer.parseInt(tokens.nextToken("-"));
                    }
                    balotaganadora = Integer.parseInt(tokens.nextToken("-"));
                
                    tokens2 = new StringTokenizer(linRevancha);
                    for(int z = 0;z<5;z++){
                        numrevancha[z] = Integer.parseInt(tokens2.nextToken("-"));
                    }
                    
                    balotarevancha = Integer.parseInt(tokens2.nextToken("-"));
                
                
                
                for(int x=0;x<5;x++){
                    System.out.println("Numeros ganadores : " + numganadores[x]);
                }
                
                System.out.println("Balota ganadora : " + balotaganadora);
                
                for(int x=0;x<5;x++){
                    System.out.println("Numeros ganadores revancha : " + numrevancha[x]);
                }
                
                System.out.println("Balota ganadora revancha : " + balotarevancha);
                
                linea = entrada_archivo.readLine();
                int cantidad = 0;
                
                while(linea!=null){
                    bol[cantidad] = new boleto(linea);
                    cantidad++;
                    linea = entrada_archivo.readLine();
                }
                
                //revancha
                lin3 = entrada_revancha.readLine();
                int cantrevancha = 0;
                
                while(lin3!=null){
                    bolre[cantrevancha] = new boleto(lin3);
                    cantrevancha++;
                    lin3 = entrada_revancha.readLine();
                }
                
                
                System.out.println("Cantidad de balotos vendidas: " + cantidad);
                cantotal = cantidad;
                System.out.println("Cantidad de balotos revancha vendidas: " + cantrevancha);
                //Premios:
                
                //Gran acumulado: 5 aciertos y superbola 35%
                int acierto = 0;
                int aciertobalota = 0;
                int posganador1 = 0;
                int posganador2 = 0;
                int posganador3 = 0;
                int posganador4 = 0;
                int posganador5 = 0;
                int flag = 0;
                int pago = 0;
                for(int x = 0;x<cantidad;x++)
                {
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                        }
                    }
                    if(acierto==5){
                        if(balotaganadora==bol[x].superbalota){
                            aciertobalota = 1;
                            posganador1 = x;
                            break;
                        }
                    }
                }
                if(acierto==5 && aciertobalota ==1){
                    System.out.println("Felicidades! Hay un ganador para el gran acumulado! Su premio es de " + (35 * (cantidad*30000) / 100) +" Bs. S" );
                    pago = (35 * (cantidad*30000) / 100);
                }
                
                //5 sin bola 15%
                acierto = 0;
                for(int x = 0;x<cantidad;x++)
                {   
                    if(x==posganador1){
                        x = x+1;
                    }
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                            if(acierto==5){
                                posganador2 = x;
                                flag = 1;
                            }
                        }
                    }
                    if(flag == 1){
                        break;
                    }
                    
                }
                if(acierto==5){
                    System.out.println("Felicidades! Hay un ganador para 5 aciertos sin super balota! Su premio es de " + (15 * (cantidad*30000) / 100)+" Bs. S" );
                    pago = pago + (15 * (cantidad*30000) / 100);
                }
                //4 con bola 10%
                acierto = 0;
                flag= 0;
                 for(int x = 0;x<cantidad;x++)
                {
                    if(x==posganador1){
                        x++;
                    }
                    if(x==posganador2){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                        }
                    }
                    if(acierto==4){
                        if(balotaganadora==bol[x].superbalota){
                            aciertobalota = 1;
                            posganador3 = x;
                            break;
                        }
                    }else{
                        acierto=0;
                    }
                }
                if(acierto==4 && aciertobalota ==1){
                    System.out.println("Felicidades! Hay un ganador para cuatro aciertos con super balota! Su premio es de " + (10 * (cantidad*30000) / 100) +" Bs. S" );
                    pago = pago + (10 * (cantidad*30000) / 100);
                }
                //4 sin bola 5%
                acierto = 0;
                for(int x = 0;x<cantidad;x++)
                {   
                    if(x==posganador1){
                        x++;
                    }
                    if(x==posganador2){
                        x++;
                    }
                    if(x==posganador3){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                            if(acierto==4){
                                posganador4 = x;
                                flag = 1;
                            }
                        }
                    }
                    if(flag==1){
                        break;
                    }
                }
                if(acierto==4){
                    System.out.println("Felicidades! Hay un ganador para 4 aciertos sin super balota! Su premio es de " + (5 * (cantidad*30000) / 100)+" Bs. S" );
                    pago = pago + (5 * (cantidad*30000) / 100);
                }
                
                //3 con bola 3%
                flag = 0;
                acierto = 0;
                
                for(int x = 0;x<cantidad;x++)
                {
                    if(x==posganador1){
                        x++;
                    }
                    if(x==posganador2){
                        x++;
                    }
                    if(x==posganador3){
                        x++;
                    }
                    if(x==posganador4){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                        }
                    }
                    if(acierto==3){
                        if(balotaganadora==bol[x].superbalota){
                            aciertobalota = 1;
                            posganador5 = x;
                            break;
                        }
                    }
                }
                if(acierto==3 && aciertobalota ==1){
                    System.out.println("Felicidades! Hay un ganador para 3 aciertos con super balota! Su premio es de " + (3 * (cantidad*30000) / 100) +" Bs. S" );
                    pago = pago + (3 * (cantidad*30000) / 100);
                }
                
                //3 sin bola 2%
                acierto = 0;
                for(int x = 0;x<cantidad;x++)
                {   
                    if(x==posganador1){
                        x++;
                    }
                    if(x==posganador2){
                        x++;
                    }
                    if(x==posganador3){
                        x++;
                    }
                    if(x==posganador4){
                        x++;
                    }
                    if(x==posganador5){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numganadores[y] == bol[x].numeros[y]){
                            acierto++;
                            if(acierto==3){
                                flag = 1;
                            }
                        }
                    }
                    if(flag==1){
                        break;
                    }
                }
                if(acierto==3){
                    System.out.println("Felicidades! Hay un ganador para 3 aciertos sin super balota! Su premio es de " + (2 * (cantidad*30000) / 100)+" Bs. S" );
                    pago = pago + (2 * (cantidad*30000) / 100);
                }
                
                //Aqui ira todo lo referente al baloto revancha
                int aciertor = 0;
                int aciertobalotar = 0;
                int posganador1r = 0;
                int posganador2r = 0;
                int posganador3r = 0;
                int posganador4r = 0;
                int posganador5r = 0;
                int flagr = 0;
                int pagor = 0;
                for(int x = 0;x<cantrevancha;x++)
                {
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                        }
                    }
                    if(aciertor==5){
                        if(balotarevancha==bolre[x].superbalota){
                            aciertobalotar = 1;
                            posganador1r = x;
                            break;
                        }
                    }
                }
                if(aciertor==5 && aciertobalotar ==1){
                    System.out.println("Felicidades! Hay un ganador para el gran acumulado del baloto revancha! Su premio es de " + (35 * (cantrevancha*30000) / 100) +" Bs. S" );
                    pagor = (35 * (cantrevancha*35000) / 100);
                }
                
                //5 sin bola 15%
                aciertor = 0;
                for(int x = 0;x<cantrevancha;x++)
                {   
                    if(x==posganador1r){
                        x = x+1;
                    }
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                            if(aciertor==5){
                                posganador2r = x;
                                flagr = 1;
                            }
                        }
                    }
                    if(flagr == 1){
                        break;
                    }
                    
                }
                if(aciertor==5){
                    System.out.println("Felicidades! Hay un ganador para 5 aciertos sin super balota revancha! Su premio es de " + (15 * (cantrevancha*30000) / 100)+" Bs. S" );
                    pagor = pagor + (15 * (cantrevancha*35000) / 100);
                }
                //4 con bola 10%
                aciertor = 0;
                flagr= 0;
                 for(int x = 0;x<cantrevancha;x++)
                {
                    if(x==posganador1r){
                        x++;
                    }
                    if(x==posganador2r){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                        }
                    }
                    if(aciertor==4){
                        if(balotarevancha==bolre[x].superbalota){
                            aciertobalotar = 1;
                            posganador3r = x;
                            break;
                        }
                    }else{
                        aciertor=0;
                    }
                }
                if(aciertor==4 && aciertobalotar ==1){
                    System.out.println("Felicidades! Hay un ganador para cuatro aciertos con super balota revancha! Su premio es de " + (10 * (cantrevancha*30000) / 100) +" Bs. S" );
                    pagor = pagor + (10 * (cantrevancha*35000) / 100);
                }
                //4 sin bola 5%
                aciertor = 0;
                for(int x = 0;x<cantrevancha;x++)
                {   
                    if(x==posganador1r){
                        x++;
                    }
                    if(x==posganador2r){
                        x++;
                    }
                    if(x==posganador3r){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                            if(aciertor==4){
                                posganador4r = x;
                                flagr = 1;
                            }
                        }
                    }
                    if(flagr==1){
                        break;
                    }
                }
                if(aciertor==4){
                    System.out.println("Felicidades! Hay un ganador para 4 aciertos sin super balota revancha! Su premio es de " + (5 * (cantrevancha*30000) / 100)+" Bs. S" );
                    pagor = pagor + (5 * (cantrevancha*35000) / 100);
                }
                
                //3 con bola 3%
                flagr = 0;
                aciertor = 0;
                
                for(int x = 0;x<cantrevancha;x++)
                {
                    if(x==posganador1r){
                        x++;
                    }
                    if(x==posganador2r){
                        x++;
                    }
                    if(x==posganador3r){
                        x++;
                    }
                    if(x==posganador4r){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                        }
                    }
                    if(aciertor==3){
                        if(balotarevancha==bolre[x].superbalota){
                            aciertobalotar = 1;
                            posganador5r = x;
                            break;
                        }
                    }
                }
                if(aciertor==3 && aciertobalotar ==1){
                    System.out.println("Felicidades! Hay un ganador para 3 aciertos con super balota revancha! Su premio es de " + (3 * (cantrevancha*30000) / 100) +" Bs. S" );
                    pagor = pagor + (3 * (cantrevancha*35000) / 100);
                }
                
                //3 sin bola 2%
                aciertor = 0;
                for(int x = 0;x<cantrevancha;x++)
                {   
                    if(x==posganador1r){
                        x++;
                    }
                    if(x==posganador2r){
                        x++;
                    }
                    if(x==posganador3r){
                        x++;
                    }
                    if(x==posganador4r){
                        x++;
                    }
                    if(x==posganador5r){
                        x++;
                    }
                    for(int y=0;y<5;y++){
                        if(numrevancha[y] == bolre[x].numeros[y]){
                            aciertor++;
                            if(aciertor==3){
                                flagr = 1;
                            }
                        }
                    }
                    if(flagr==1){
                        break;
                    }
                }
                if(aciertor==3){
                    System.out.println("Felicidades! Hay un ganador para 3 aciertos sin super balota revancha! Su premio es de " + (2 * (cantrevancha*30000) / 100)+" Bs. S" );
                    pagor = pagor + (2 * (cantrevancha*35000) / 100);
                }
                
                //pagos baloto normal
                System.out.println("Ventas baloto : " + (cantidad * 30000));
                System.out.println("Ventas revancha : " +(cantrevancha) * 35000);
                System.out.println("Pagos Baloto : " + pago);
                System.out.println("Pagos Baloto Revancha: " +pagor);
                System.out.println("Utilidades : " + ((cantidad * 30000) + (cantrevancha * 35000) - pagor - pago));
                //pagos baloto revancha
                entrada_archivo.close();
                entrada_2.close();
                entrada_revancha.close();
                break;
            case 3:
                File f1 = new File("ventas.txt");
                File fi2 = new File("sorteo.txt");
                if(f1.delete()){
                    System.out.println("Los sorteos han sido eliminados");
                }
                if(fi2.delete()){
                    System.out.println("Las ventas han sido eliminadas");
                }
                
                break;
            case 4:
                //Primero, debemos guardar todos los numeros en un arreglo de enteros
                int [] numeros = new int[cantotal*5];
                int [] cantrep = new int[cantotal*5];
                for(int x = 0;x<cantotal*5;x++){
                    cantrep[x] = 0;
                    //Se pobla con ceros, ya que este va a contar cuantos duplicados hay de cada uno
                }
                int cant = 0;
                for(int x=0;x<cantotal;x++){
                    for(int y=0;y<5;y++){
                        numeros[cant] = bol[x].numeros[y];
                        cant++;
                    }
                }
                System.out.println("Numeros");
                for(int x=0;x<cantotal*5;x++){
                    System.out.println(numeros[x]+" ");
                }
                flag = 0;
                int numrep = 0;
                for(int x=0;x<cantotal*5;x++){
                    for(int y = x+1;y<cantotal*5;y++){
                        if(numeros[x]==numeros[y]){
                            cantrep[x]++;
                            flag = 1;
                        }
                    }
                    if(flag==1){
                        numrep++;
                    }
                }
                int numayor = 0;
                int ayudante = (cantotal*5) - 1;
                System.out.println(ayudante);
                for(int x=0;x<cantotal*5;x++){
                    if(x<ayudante){
                        if(cantrep[x]>cantrep[x+1]){
                        numayor = x;
                        }
                    }
                    
                }

                System.out.println("El numero favorito es el numero "+numeros[numayor]);
                
                break;
            case 5:
                break;
        }
    }
    
}
