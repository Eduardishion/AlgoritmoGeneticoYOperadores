package algoritmogenetico;

import java.util.*;
import java.util.Arrays;
/**
 * @author Eduardishion
 */
public class metodosGeneticos {
    /*numeroDeIndividuos inicializa el numero de individuos base   
     *numeroDeGenes es el numero de cromosomas del individuo
     *numeroDeGeneraciones son el numero de generaciones para la poblacion
     *[][] individuos que contiene a los individuos 
     *[] valorIndividuos es el valor que representa en entero cada uno de los individuos
     *[] valordelaFuncionAptitud es el valor obtenido por la funcion de actitud (Fitness)
     *[][] mejoresIndividosPadres se almacenan los padres selectos de la generacion
     *[][] individuosCruzados los individuos obtenidos despues de la operador de cruza de padres
     *[][] individuosMutados que se mutaron despues de operador de mutacion
     */
    private int numeroDeIndividuos;
    private int numeroDeGenes;
    private int numeroDeGeneraciones;
    private int cuentaGeneraciones;
    private double mejorIndividuo=0.0;
    private String opcionDeSeleccion="";
    private String opcionCruza="";
    private int numeroCortes;
    private int muestra;
    private double porcentaje;
    
    private int[][] individuos ;
    private double[] valorIndividuos ;
    private double[] valordelaFuncionAptitud ;
    private int[][] mejoresIndividosPadres ;
    private int[][] individuosCruzados ;
    private int[][] individuosMutados ;
    private double[] mejoresInvidiosDegeneraciones ;
    private int[][] cortesDecruzeAdaptativos ;/*para sabes la posicion de corte de cada individuo*/
    ArrayList usados = new ArrayList();       /*para saber cortes ya generados*/
   
    
    public metodosGeneticos() {
    }
    /*Contructor para inicializar variables de inicio*/
    public metodosGeneticos(int numeroDeIndividuos, int numeroDeGenes, int numeroDeGeneraciones,int cuentaGeneraciones) {
        
        this.numeroDeIndividuos = numeroDeIndividuos;
        this.numeroDeGenes = numeroDeGenes;
        this.numeroDeGeneraciones = numeroDeGeneraciones;
        this.cuentaGeneraciones=cuentaGeneraciones;
        
        individuos = new int[numeroDeIndividuos][numeroDeGenes];
        valorIndividuos = new double[numeroDeIndividuos];
        valordelaFuncionAptitud = new double[numeroDeIndividuos];
        mejoresIndividosPadres = new int[numeroDeIndividuos][numeroDeGenes];
        individuosCruzados = new int[numeroDeIndividuos][numeroDeGenes];
        individuosMutados = new int[numeroDeIndividuos][numeroDeGenes];
        mejoresInvidiosDegeneraciones = new double[numeroDeGeneraciones]; 
        cortesDecruzeAdaptativos = new int[numeroDeIndividuos][numeroDeGenes];

    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getMuestra() {
        return muestra;
    }

    public void setMuestra(int muestra) {
        this.muestra = muestra;
    }

    public int getNumeroCortes() {
        return numeroCortes;
    }

    public void setNumeroCortes(int numeroCortes) {
        this.numeroCortes = numeroCortes;
    }
    
    public String getOpcionCruza() {
        return opcionCruza;
    }

    public void setOpcionCruza(String opcionCruza) {
        this.opcionCruza = opcionCruza;
    }

    
    public String getOpcionDeSeleccion() {
        return opcionDeSeleccion;
    }

    public void setOpcionDeSeleccion(String opcionDeSeleccion) {
        this.opcionDeSeleccion = opcionDeSeleccion;
    }

    public int getNumeroDeIndividuos() {
        return numeroDeIndividuos;
    }

    public void setNumeroDeIndividuos(int numeroDeIndividuos) {
        this.numeroDeIndividuos = numeroDeIndividuos;
    }

    public int getNumeroDeGenes() {
        return numeroDeGenes;
    }

    public void setNumeroDeGenes(int numeroDeGenes) {
        this.numeroDeGenes = numeroDeGenes;
    }

    public int getNumeroDeGeneraciones() {
        return numeroDeGeneraciones;
    }

    public void setNumeroDeGeneraciones(int numeroDeGeneraciones) {
        this.numeroDeGeneraciones = numeroDeGeneraciones;
    }

    public int getCuentaGeneraciones() {
        return cuentaGeneraciones;
    }

    public void setCuentaGeneraciones(int cuentaGeneraciones) {
        this.cuentaGeneraciones = cuentaGeneraciones;
    }

    public double getMejorIndividuo() {
        return mejorIndividuo;
    }

    public void setMejorIndividuo(double mejorIndividuo) {
        this.mejorIndividuo = mejorIndividuo;
    }

    public int[][] getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int[][] individuos) {
        this.individuos = individuos;
    }

    public double[] getValorIndividuos() {
        return valorIndividuos;
    }

    public void setValorIndividuos(double[] valorIndividuos) {
        this.valorIndividuos = valorIndividuos;
    }

    public double[] getValordelaFuncionAptitud() {
        return valordelaFuncionAptitud;
    }

    public void setValordelaFuncionAptitud(double[] valordelaFuncionAptitud) {
        this.valordelaFuncionAptitud = valordelaFuncionAptitud;
    }

    public int[][] getMejoresIndividosPadres() {
        return mejoresIndividosPadres;
    }

    public void setMejoresIndividosPadres(int[][] mejoresIndividosPadres) {
        this.mejoresIndividosPadres = mejoresIndividosPadres;
    }

    public int[][] getIndividuosCruzados() {
        return individuosCruzados;
    }

    public void setIndividuosCruzados(int[][] individuosCruzados) {
        this.individuosCruzados = individuosCruzados;
    }

    public int[][] getIndividuosMutados() {
        return individuosMutados;
    }

    public void setIndividuosMutados(int[][] individuosMutados) {
        this.individuosMutados = individuosMutados;
    }

    public double[] getMejoresInvidiosDegeneraciones() {
        return mejoresInvidiosDegeneraciones;
    }

    public void setMejoresInvidiosDegeneraciones(double[] mejoresInvidiosDegeneraciones) {
        this.mejoresInvidiosDegeneraciones = mejoresInvidiosDegeneraciones;
    }

    public int[][] getCortesDecruzeAdaptativos() {
        return cortesDecruzeAdaptativos;
    }

    public void setCortesDecruzeAdaptativos(int[][] cortesDecruzeAdaptativos) {
        this.cortesDecruzeAdaptativos = cortesDecruzeAdaptativos;
    }
    
    
    
    /*Metodo que genera conjunto de inicial de individuos en su primera iteracion
     *El for generara la generacion de la poblacion, la variable 
     *numeroDeGeneraciones son las veses que se retira o las veses 
     *que se creara otra nueva poblacion dependiendo del numero asignado 
     */
    public void generarPoblacion() {
        
        for (int i = 0; i < numeroDeGeneraciones; i++) {
            System.out.println("\nGeneracion:" + (i + 1));
            if (i == 0) {
                generarIndividuosDePoblacion();
            } else {
                tipoSeleccion(opcionDeSeleccion);
                tipoCruza(opcionCruza,i);
                mutacion();
                generarNuevaPoblacion();
            }
        }
        
        for (int i = 0; i < mejoresInvidiosDegeneraciones.length; i++) {
                System.out.println(mejoresInvidiosDegeneraciones[i]);
        }
        cuentaGeneraciones=0;
        
    }

    /*Metetodo encargado de generar la primera generacion base de individuos de 
     *la primera gemeracion el cual llena cada cromosoma de los individuos 
     *dependiendo de una probabilidad de 0.5, si es mayor se asigna un 1 si es 
     *menor se asigna un 0
     */
    public void generarIndividuosDePoblacion() {
        
        //System.out.println("el vector es de:"+individuos.length);
        //System.out.println("el numero de individuos es:"+numeroDeIndividuos);
        //System.out.println("el numero de genes es:"+numeroDeGenes);
        //System.out.println("el numero de generaciones es"+numeroDeGeneraciones);
        double auxAleatorio;
        Random aleatorio = new Random();
        for (int i = 0; i < individuos.length; i++) {
            for (int j = 0; j < individuos[i].length; j++) {
                auxAleatorio = aleatorio.nextDouble() * 1 + 0;
                if (auxAleatorio < 0.5) {
                    individuos[i][j] = 0;
                } else {
                    individuos[i][j] = 1;
                }
            }
        }
       
        obtenerValordelosIndividuos();
        obtenerValordeAptitud();
        mostrarIndividuos();
        buscarMejorIndividuo();
    }
    
    /*Metodo que muestra los individuos generados en cada cada generacion 
     *se recorre la matriz de individuos 
     */
    public void mostrarIndividuos() {
        System.out.println("\nIndividuos\tValor Decimal\t\tValor funcion Aptitud  ");
        for (int i = 0; i < individuos.length; i++) {
            //System.out.print((i+1)+"._");
            System.out.print((i) + "._");
            for (int j = 0; j < individuos[i].length; j++) {
                System.out.print(individuos[i][j]);
            }
            System.out.println("\t" + valorIndividuos[i] + "\t\t\t" + valordelaFuncionAptitud[i]);
        }

    }
    
    /*Metodo que muestra los padres selwctos de cada generacion se recorre 
     *la matraz de padres selectos*/
    public void mostrarPadresSelectos() {
        //System.out.println("\nIndividuos\tValor Decimal\t\tValor funcion Aptitud  ");
        for (int i = 0; i < mejoresIndividosPadres.length; i++) {
            //System.out.print((i+1)+"._");
            System.out.println("\t");
            for (int j = 0; j < mejoresIndividosPadres[i].length; j++) {
                System.out.print(mejoresIndividosPadres[i][j]);
            }
            //System.out.println("\t"+valorIndividuos [i]+"\t\t\t"+valordelaFuncionAptitud[i]);
        }
    }
    
    /*Metodo que muesta los individuos cruzados de cada generacion se recorre 
     * la matrizde individuos cruzados*/
    public void individuosCruzados() {
        System.out.println("\nindividuos cruzados");
        //System.out.println("\nIndividuos\tValor Decimal\t\tValor funcion Aptitud  ");
        for (int i = 0; i < individuosCruzados.length; i++) {
            //System.out.print((i+1)+"._");
            System.out.println("\t");
            for (int j = 0; j < individuosCruzados[i].length; j++) {
                System.out.print(individuosCruzados[i][j]);
            }
            //System.out.println("\t"+valorIndividuos [i]+"\t\t\t"+valordelaFuncionAptitud[i]);
        }
    }
    
    /*Metodo que muestra los individuos mutados, se recorre la matriz de 
     *individuos vruzados*/
    public void individuosMutados() {
        System.out.println("\nindividuos Mutados");
        //System.out.println("\nIndividuos\tValor Decimal\t\tValor funcion Aptitud  ");
        for (int i = 0; i < individuosMutados.length; i++) {
            //System.out.print((i+1)+"._");
            System.out.println("\t");
            for (int j = 0; j < individuosMutados[i].length; j++) {
                System.out.print(individuosCruzados[i][j]);
            }
                //System.out.println("\t"+valorIndividuos [i]+"\t\t\t"+valordelaFuncionAptitud[i]);
        }
    }

    /*Metodo que convierte cada cada individuo de binario individuo a numero entero
     */
    public int convertirBinarioaEntero(int[] vectordeIndividuo) {
        int resultado = 0;
        int longitud = vectordeIndividuo.length - 1;
        for (int i = 0; i < vectordeIndividuo.length; i++) {
            if (vectordeIndividuo[i] == 1) {
                resultado += Math.pow(2, longitud - i);
            }
        }
        return resultado;
    }

    /*Metodo para obtener Obtener el valor decimal de cada individuo dependiendo 
     *de su valor en binario */
    public void obtenerValordelosIndividuos() {
        for (int i = 0; i < individuos.length; i++) {
            valorIndividuos[i] = convertirBinarioaEntero(individuos[i]);
        }
    }
    
    /*Metodo para obtener la aptitud de cada individuo*/
    public void obtenerValordeAptitud() {
        double valorAptitud;
        for (int j = 0; j < valorIndividuos.length; j++) {
            valorAptitud = funciondeActitud((int) valorIndividuos[j]);
            valordelaFuncionAptitud[j] = valorAptitud;
            //System.out.println(valordelaFuncionAptitud[j]);
        }
    }

    /*Metodo para obtener el valor de la funcion (fitness) 
     *para cada uno de los individuos sea evaluada y obtener su aptitud  
     */
    public double funciondeActitud(double x) {
        /*solo se cambia el tipo de funcion al que quiera evaluar*/
        double valorAptitud = 0;
        valorAptitud = -2 * Math.pow(x, 2) + (11 * x) / 3;
        //valorAptitud=x*2 + 2; 
        //valorAptitud =Math.abs((x-5)/(2+Math.sin(x)));
        return valorAptitud;
    }
    
    public double buscarMejorIndividuo(){
        
        //double mayor = Double.MIN_VALUE;
        double mayor = -1000000000;
        for (int i = 0; i < valordelaFuncionAptitud.length; i++) {
                if(valordelaFuncionAptitud[i] >=  mayor){
                     mayor=valordelaFuncionAptitud[i];
                }
        }
        System.out.println("generaciones"+(cuentaGeneraciones+1));
        
        mejoresInvidiosDegeneraciones[cuentaGeneraciones]=mayor;
        System.out.println("El mejor es:"+mejoresInvidiosDegeneraciones[cuentaGeneraciones]);
        cuentaGeneraciones++;
        return mayor;
        
    }
    
   
    /*Metodo de seleccion aleatoria que permite seleccionar individuos
    padre de forma aleatoria*/
    public void seleccionAleatoria() {
        for (int i = 0; i < individuos.length; i++) {
            for (int j = 0; j < 2; j++) {
                int individuoAleayotio = (int) (Math.random() * numeroDeIndividuos);
                //System.out.println("num:"+(num+1));
                for (int k = 0; k < individuos[i].length; k++) {
                    mejoresIndividosPadres[i][k] = individuos[individuoAleayotio][k];
                    //copiarVector(individuos, mejoresIndividosPadres, i, i);
                    //System.out.println(individuos[num][k]);
                }
            }
        }
    }
    
    
    
    /*Metodo de seleccion de ruleta que selcciona individuos del conjunto de 
     de individuos muestra*/
    public void seleleccionPorRuleta() {
        double sumatoria = 0;
        int selecto = 0;
        double[] FxActitud = new double[numeroDeIndividuos];
        /*para leccion de rulata*/
        /*
         double sumatoria=0;
         int selecto=0;
         double [] FxActitud = new double [numeroDeIndividuos];
         for (int i = 0; i < valordelaFuncionAptitud.length; i++) {
            sumatoria+=valordelaFuncionAptitud[i];
            FxActitud[i]=sumatoria;
         }
         System.out.println("la sumatoria es:"+sumatoria);
         for (int i = 0; i < FxActitud.length; i++) {
            System.out.println(FxActitud[i]);
         }
         int numAleatorio=(int)(Math.random() * sumatoria);
         System.out.println("num="+numAleatorio);
         for (int i = 0; i < FxActitud.length; i++) {
                        
         }
         */
        /*para leccion de rulata*/
        System.out.println("\nselectos");
        for (int i = 0; i < valordelaFuncionAptitud.length; i++) {
            sumatoria += valordelaFuncionAptitud[i];
            FxActitud[i] = sumatoria;
        }
        System.out.println("sumatoria es de :" + sumatoria);
        /*n son las veses que re repetira el proceso */
        for (int n = 0; n < individuos.length; n++) {
            ////////////////////////////////////////////////////////////////////
            System.out.println("\n");
            int numAleatorio = (int) (Math.random() * sumatoria);
            for (int j = 0; j < FxActitud.length; j++) {
                if (FxActitud[j] <= numAleatorio) {
                    System.out.println(FxActitud[j] + "-------\t\t\t > \t" + numAleatorio + ":mayor");
                    selecto = j;
                    break;
                } else {
                    System.out.println(FxActitud[j] + "-------\t\t\t > \t" + numAleatorio + ":menor");
                }
            }
            ////////////////////////////////////////////////////////////////////
            System.out.println("selecto es:" + selecto);
            for (int x = 0; x < individuos[n].length; x++) {
                mejoresIndividosPadres[n][x] = individuos[selecto][x];
            }
        }
    }

    public void seleleccionPorMuestreoEstocasticoUniversal() {
        double sumatoria = 0;
        int posicion = 0;
        double elegido=0;
        double[] elegidos = new double[numeroDeIndividuos];
        double[] FxActitud = new double[numeroDeIndividuos];
        
        System.out.println("\nselectos");
        for (int i = 0; i < valordelaFuncionAptitud.length; i++) {
            sumatoria += valordelaFuncionAptitud[i];
            FxActitud[i] = sumatoria;
        }
        int numAleatorio = (int) (Math.random() * sumatoria);
        
        for (int n = 0; n < individuos.length; n++) {
            ////////////////////////////////////////////////////////////////////
            elegido=(numAleatorio+(n*sumatoria)-sumatoria)/numeroDeIndividuos;
            
            for (int j = 0; j < FxActitud.length; j++) {
                if (FxActitud[j] <= elegido) {
                    System.out.println(FxActitud[j] + "-------\t\t\t > \t" + numAleatorio + ":mayor");
                    posicion = j;
                    break;
                } else {
                    System.out.println(FxActitud[j] + "-------\t\t\t > \t" + numAleatorio + ":menor");
                }
            }
            ////////////////////////////////////////////////////////////////////
            System.out.println("la posicion"+posicion);
            System.out.println("selecto es:" + elegido);
            for (int x = 0; x < individuos[n].length; x++) {
                mejoresIndividosPadres[n][x] = individuos[posicion][x];
            }
        }
    }

    public void seleccionPorTorneo() {
        //valordelaFuncionActitud
        int kmuestra = muestra;/*muestra indica cuantos deseamos seleccionar*/
        int selecto = 0;/*individuo del torneo*/
        double[] individuosDeTorneo = new double[kmuestra];
        double[] aleatoriosGenerados = new double[kmuestra];
        
        for (int n = 0; n < numeroDeIndividuos  ; n++) {
            ////////////////////////////////////////////////////////////////////
            for (int k = 0; k < kmuestra; k++) {
                int numAleatorio = (int) (Math.random() * (valordelaFuncionAptitud.length));
                System.out.println("ale:"+numAleatorio);
                individuosDeTorneo[k]=valordelaFuncionAptitud[numAleatorio];
                aleatoriosGenerados[k]=numAleatorio;
            }
            //double mayor = Double.MIN_VALUE;
            double mayor = -1000000000;
            int posicion=0;
            for (int i = 0; i < individuosDeTorneo.length; i++) {
                System.out.println("individuo"+i+":"+individuosDeTorneo[i]);
                if(individuosDeTorneo[i] > mayor){
                    mayor=individuosDeTorneo[i];
                    posicion=i;
                }
            }
            System.out.println("el aleatiro ganador es:"+aleatoriosGenerados[posicion]);
            System.out.println("mayor es:"+mayor+"posicion es:"+posicion);
            ////////////////////////////////////////////////////////////////////
            int numIndividuo=(int) aleatoriosGenerados[posicion];
            System.arraycopy(individuos[numIndividuo], 0, mejoresIndividosPadres[n], 0, numeroDeGenes);
        }
            
        
    }
    /*Matodo de para cruzar los individuos padre que se han seleccionado 
    previamente con un punto de corte (Operador de cruze en un punto de manera 
    aleatoria X1PA)*/

    public void cruzaUnPunto() {
        System.out.println("\nPadres selectos ");
        mostrarPadresSelectos();
        System.out.println("\ncruza de un punto  ");
        for (int x = 0; x < mejoresIndividosPadres.length; x++) {
            if (x % 2 == 0 || x == 0) {
                //System.out.println("\n"+x);
                //System.out.println("\n"+(x+1));
                /*rango que incia desde desde 0 a 7*/
                Random aleatorio = new Random();
                int puntoDecorte = (int) (aleatorio.nextDouble() * (numeroDeGenes-1) +1);
                //int puntoDecorte = (int) (Math.random() * numeroDeGenes );

                System.out.println("\n\npc=" + puntoDecorte);
                System.out.println("\tNuevo inviduo 1");
                /*para individuo 1*/
                for (int i = x; i < (x + 1); i++) {
                    System.out.print("\t");
                    for (int j = 0; j < puntoDecorte; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }

                for (int i = (x + 1); i < (x + 2); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte; j < mejoresIndividosPadres[x].length; j++) {
                        individuosCruzados[i - 1][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                /*para individuo 2*/
                for (int i = (x + 1); i < (x + 2); i++) {
                    System.out.print("\t");
                    for (int j = 0; j < puntoDecorte; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                for (int i = x; i < (x + 1); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte; j < mejoresIndividosPadres[x].length; j++) {
                        individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }

            }
        }

    }
    
    /*Matodo de para cruzar los individuos padre que se han seleccionado 
     previamente con dos puntos de corte (Operador de cruze de dos puntos de corte 
    de manera aleatoria X12PA)*/
    public void cruzaDedosPuntos() {
        System.out.println("\nPadres selectos ");
        mostrarPadresSelectos();
        System.out.println("\ncruza de dos punto  ");
        for (int x = 0; x < mejoresIndividosPadres.length; x++) {
           
            if (x % 2 == 0 || x == 0) {
               
                
                Random aleatorio1 = new Random();
                int puntoDecorte1 = (int) (aleatorio1.nextDouble() * (numeroDeGenes-1) +1);
                Random aleatorio2 = new Random();
                int puntoDecorte2 =0; 
                do{
                    puntoDecorte2=(int) (aleatorio2.nextDouble() * (numeroDeGenes-1) +1);
                } while(puntoDecorte2==puntoDecorte1);
               
                //System.out.println("\npc1:"+puntoDecorte1);
                //System.out.println("\npc2:"+puntoDecorte2);
                
                if(puntoDecorte2 < puntoDecorte1){
                    int aux,aux2;
                    aux=puntoDecorte2;
                    aux2=puntoDecorte1;
                    puntoDecorte1=aux;
                    puntoDecorte2=aux2;
                }
                
                System.out.println("\npc1:-->"+puntoDecorte1);
                System.out.println("\npc2:-->"+puntoDecorte2);
                /*para individuo 1*/
                
                for (int i = x; i < (x + 1); i++) {
                    System.out.print("\t");
                    for (int j = 0; j < puntoDecorte1; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                for (int i = (x + 1); i < (x + 2); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte1; j < puntoDecorte2; j++) {
                        individuosCruzados[i-1][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                for (int i = x; i < (x + 1); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte2; j < numeroDeGenes; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                
                
                /*para individuo 2*/
                
                for (int i = (x + 1); i < (x + 2); i++) {
                    System.out.print("\t");
                    for (int j = 0; j < puntoDecorte1; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                for (int i = x; i < (x + 1); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte1; j < puntoDecorte2; j++) {
                        individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                for (int i = (x + 1); i < (x + 2); i++) {
                    System.out.print("\t");
                    for (int j = puntoDecorte2; j < numeroDeGenes; j++) {
                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                        System.out.print(mejoresIndividosPadres[i][j]);
                    }
                }
                
                
                
            }
        }
    }
    
     /*Matodo de para cruzar los individuos padres que se han seleccionado 
    previamente con un punto de corte de manera uniforme
    (Operador de cruze en un punto de manera uniforme X1PU)*/
    public void cruzedeUnPuntoUniforme(){
        System.out.println("\ncruza uniforme ");
        if(numeroDeGenes%2==0){
            int corte=numeroDeGenes/2;
            for (int x = 0; x < mejoresIndividosPadres.length; x++) {
                if (x % 2 == 0 || x == 0) {
                    //System.out.println("\n"+x);
                    //System.out.println("\n"+(x+1));
                    /*rango que incia desde desde 0 a 7*/
                   
                    System.out.println("\n\npc=" + corte);
                    System.out.println("\tNuevo inviduo 1");
                    /*para individuo 1*/
                    for (int i = x; i < (x + 1); i++) {
                        System.out.print("\t");
                        for (int j = 0; j < corte; j++) {
                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                    for (int i = (x + 1); i < (x + 2); i++) {
                        System.out.print("\t");
                        for (int j = corte; j < numeroDeGenes; j++) {
                            individuosCruzados[i - 1][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }
                    /*para individuo 2*/
                    for (int i = (x + 1); i < (x + 2); i++) {
                        System.out.print("\t");
                        for (int j = 0; j < corte; j++) {
                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                    for (int i = x; i < (x + 1); i++) {
                        System.out.print("\t");
                        for (int j = corte; j < numeroDeGenes; j++) {
                            individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                }
            }
        }else{
            int corte=((numeroDeGenes/2)+1);
            for (int x = 0; x < mejoresIndividosPadres.length; x++) {
                if (x % 2 == 0 || x == 0) {
                    //System.out.println("\n"+x);
                    //System.out.println("\n"+(x+1));
                    /*rango que incia desde desde 0 a 7*/
                   
                    System.out.println("\n\npc=" + corte);
                    System.out.println("\tNuevo inviduo 1");
                    /*para individuo 1*/
                    for (int i = x; i < (x + 1); i++) {
                        System.out.print("\t");
                        for (int j = 0; j < corte; j++) {
                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                    for (int i = (x + 1); i < (x + 2); i++) {
                        System.out.print("\t");
                        for (int j = corte; j < numeroDeGenes; j++) {
                            individuosCruzados[i - 1][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }
                    /*para individuo 2*/
                    for (int i = (x + 1); i < (x + 2); i++) {
                        System.out.print("\t");
                        for (int j = 0; j < corte; j++) {
                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                    for (int i = x; i < (x + 1); i++) {
                        System.out.print("\t");
                        for (int j = corte; j < numeroDeGenes; j++) {
                            individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                            System.out.print(mejoresIndividosPadres[i][j]);
                        }
                    }

                }
            }
        }
    } 
    
    public void cruzedeUnpuntoManeraApaptativa(int k){
        System.out.println("\ncruza adaptaviva  ");
        if(k==1){
                //System.out.println("ES LA PRIMERA GENERACION ");
                for (int x = 0; x < mejoresIndividosPadres.length; x++) {
                        if (x % 2 == 0 || x == 0) {
                            //System.out.println("\n"+x);
                            //System.out.println("\n"+(x+1));
                            /*rango que incia desde desde 0 a 7*/
                            Random aleatorio = new Random();
                            int puntoDecorte = (int) (aleatorio.nextDouble() * (numeroDeGenes-1) +1);
                            //int puntoDecorte = (int) (Math.random() * numeroDeGenes );

                            System.out.println("\n\npc=" + puntoDecorte);
                            System.out.println("\tNuevo inviduo 1");
                            /*para individuo 1*/
                            for (int i = x; i < (x + 1); i++) {
                                System.out.print("\t");
                                for (int j = 0; j < puntoDecorte; j++) {
                                    individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }

                            for (int i = (x + 1); i < (x + 2); i++) {
                                System.out.print("\t");
                                for (int j = puntoDecorte; j < numeroDeGenes; j++) {
                                    individuosCruzados[i - 1][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }
                            /*****asociacion de punto de corte individuo1*****************/
                            for (int i = x; i < (x + 1); i++) {
                                for (int j = 0; j < numeroDeGenes; j++) {
                                    if(j==puntoDecorte){
                                        cortesDecruzeAdaptativos[i][j-1]=1;
                                    }else{
                                        cortesDecruzeAdaptativos[i][j]=0;
                                    }
                                }
                            }
                            /*****asociacion de punto de corte individuo1*****************/
                            
                            /*para individuo 2*/
                            for (int i = (x + 1); i < (x + 2); i++) {
                                System.out.print("\t");
                                for (int j = 0; j < puntoDecorte; j++) {
                                    individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }

                            for (int i = x; i < (x + 1); i++) {
                                System.out.print("\t");
                                for (int j = puntoDecorte; j < numeroDeGenes; j++) {
                                    individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }
                            /*****asociacion de punto de corte individuo2*****************/
                            for (int i = (x + 1); i < (x + 2); i++) {
                                for (int j = 0; j < numeroDeGenes; j++) {
                                    if(j==puntoDecorte){
                                        cortesDecruzeAdaptativos[i][j-1]=1;
                                    }else{
                                        cortesDecruzeAdaptativos[i][j]=0;
                                    }
                                }
                            }
                            /*****asociacion de punto de corte individuo2*****************/

                        }
                }
            /*********************cortes de cada individuos ***************/
            for (int[] cortesDecruzeAdaptativo : cortesDecruzeAdaptativos) {
                System.out.println("\t");
                for (int j = 0; j < numeroDeGenes; j++) {
                    System.out.print(cortesDecruzeAdaptativo[j]);
                }
            }
            /*********************cortes de cada individuos ***************/
                
        }else{
            System.out.println("El mejor individuos es:"+ mejorIndividuo);
            int encontrado=0;
            int corteSeleccionado=0;
            int corte=0; 
            
            for (int i = 0; i < valordelaFuncionAptitud.length; i++) {
                if(valordelaFuncionAptitud[i] == mejorIndividuo){
                    encontrado=i;
                }
            }
            
            System.out.println("..."+encontrado);
            for (int i = 0; i < cortesDecruzeAdaptativos.length; i++){   
                if(i==encontrado){
                    System.out.println(i);
                    for (int j = 0; j < numeroDeGenes; j++){
                        if(cortesDecruzeAdaptativos[i][j]==1){
                            corteSeleccionado=j;
                            System.out.println(j);
                        }
                    } 
                }
            }

            
            corte=(corteSeleccionado+1);
            System.out.println("corte seleccionado:"+corte);
            
            for (int x = 0; x < mejoresIndividosPadres.length; x++) {
                        if (x % 2 == 0 || x == 0) {
                         
                            System.out.println("\n\npc=" + corte);
                            System.out.println("\tNuevo inviduo 1");
                            /*para individuo 1*/
                            for (int i = x; i < (x + 1); i++) {
                                System.out.print("\t");
                                for (int j = 0; j < corte; j++) {
                                    individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }

                            for (int i = (x + 1); i < (x + 2); i++) {
                                System.out.print("\t");
                                for (int j = corte; j < numeroDeGenes; j++) {
                                    individuosCruzados[i - 1][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }
                            /*****asociacion de punto de corte individuo1*****************/
                            for (int i = x; i < (x + 1); i++) {
                                for (int j = 0; j < numeroDeGenes; j++) {
                                    if(j==corte){
                                        cortesDecruzeAdaptativos[i][j-1]=1;
                                    }else{
                                        cortesDecruzeAdaptativos[i][j]=0;
                                    }
                                }
                            }
                            /*****asociacion de punto de corte individuo1*****************/
                            
                            /*para individuo 2*/
                            for (int i = (x + 1); i < (x + 2); i++) {
                                System.out.print("\t");
                                for (int j = 0; j < corte; j++) {
                                    individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }

                            for (int i = x; i < (x + 1); i++) {
                                System.out.print("\t");
                                for (int j = corte; j < numeroDeGenes; j++) {
                                    individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                                    System.out.print(mejoresIndividosPadres[i][j]);
                                }
                            }
                            /*****asociacion de punto de corte individuo2*****************/
                            for (int i = (x + 1); i < (x + 2); i++) {
                                for (int j = 0; j < numeroDeGenes; j++) {
                                    if(j==corte){
                                        cortesDecruzeAdaptativos[i][j-1]=1;
                                    }else{
                                        cortesDecruzeAdaptativos[i][j]=0;
                                    }
                                }
                            }
                            /*****asociacion de punto de corte individuo2*****************/

                        }
                }
            /*********************cortes de cada individuos ***************/
            for (int[] cortesDecruzeAdaptativo : cortesDecruzeAdaptativos) {
                System.out.println("\t");
                for (int j = 0; j < numeroDeGenes; j++) {
                    System.out.print(cortesDecruzeAdaptativo[j]);
                }
            }
            /*********************cortes de cada individuos ***************/
                /*********************cortes de cada individuos ***************/
            
           
        }
        
    }
    
    public void cruzeMultipuntoAleatorio(){
        
        int numeroDepuntosAletoriosMaximos=numeroDeGenes;
        int numeroDepuntosAgenerar=numeroCortes;/*este parametro es cambiante lo da el usuario paro no debe de revasar
        la cantidad maxima de genes del cromosoma ni el la posicion menor ni en la mayor el cromosoma inicia
        desde la posicion 1 y termina el la pocion n-1 osea si el cromosoma es de 8 el maximo es 7*/
        //individuos = (int[][])redimencionarArreglo(individuos,numeroDeIndividuos);
        int corte;
        int puntosDecorte[]=new int [numeroDepuntosAgenerar];
        ArrayList  aleatorios = new ArrayList();

         /*para generar N puntos de cortes aleatorios con vector estatico*/
                /*
                for (int k = 0; k < puntosDecorte.length; k++) {
                                corte=aleatorio3(1,numeroDepuntosAletoriosMaximos);
                                puntosDecorte[k]=corte;
                }
                Arrays.sort(puntosDecorte);        
                System.out.println("");
                for (int k = 0; k <  puntosDecorte.length; k++) {
                    System.out.println("pc"+k+":"+puntosDecorte[k]);
                }
                */
        /*para generar N puntos de cortes aleatorios con vector estatico*/
                
        /*para generar N puntos de cortes aleatorios  pero con array list*/
            /*Añadir elementos al array list*/
        /*
            for (int i = 0; i < numeroDepuntosAgenerar; i++) {
                corte=aleatorio3(1,numeroDepuntosAletoriosMaximos);
                aleatorios.add(corte);
            }
            //se ordena el array list
            Collections.sort(aleatorios);
            //mostramos el arraya list

            for(int x=0;x<aleatorios.size();x++) {
                System.out.println(aleatorios.get(x));
            }
        */
        /*
        con array list pero transformado en  funcion 
        aleatorios=generaNumerosAleatorios(numeroDepuntosAgenerar,numeroDepuntosAletoriosMaximos);
        
        for(int x=0;x<aleatorios.size();x++) {
                System.out.println(aleatorios.get(x));
        }
        */
        /*--este codigo estava afuewra del for------ */
        
        /*para generar N puntos de cortes aleatorios  pero con array list*/
         /*
         System.out.println("\n");
               for (int k = 0; k < numeroDepuntosAgenerar; k++) {
                    corte=aleatorio3(1,numeroDepuntosAletoriosMaximos);
                    //System.out.println("k:"+k);
                    aleatorios.add(corte);
                }
                //System.out.println("\ntam de array list"+aleatorios.size());
                //se ordena el array list
                Collections.sort(aleatorios);
                //mostramos el arraya list

                for(int k=0;k<aleatorios.size();k++) {
                    System.out.println(aleatorios.get(k));
                }
                System.out.println("");
             */  
               /*para generar N puntos de cortes aleatorios  pero con array list*/ 
       
        //int[][] mejoresIndividosPadresprueva = new int[numeroDeIndividuos][numeroDeGenes];
        //int[][] cruzaprueva = new int[numeroDeIndividuos][numeroDeGenes];
        //mejoresIndividosPadresprueva=mejoresIndividosPadres;/*para solo provar funcion */
        /*
        for (int i = 0; i < mejoresIndividosPadres.length; i++) {
            //System.out.print((i+1)+"._");
            System.out.println("\t|");
            for (int j = 0; j < mejoresIndividosPadres[i].length; j++) {
                System.out.print(mejoresIndividosPadres[i][j]);
            }
            //System.out.println("\t"+valorIndividuos [i]+"\t\t\t"+valordelaFuncionAptitud[i]);
        }
        */
        System.out.println("\nindividuos seleccionados"); 
        mostrarPadresSelectos();
        System.out.println("\n");
        
        for (int x = 0; x < mejoresIndividosPadres.length; x++) {//x es el numero de individuos por deneracion
            if (x % 2 == 0 || x == 0) {//identificamos cada uno de los individuos en par para cruzar cada par de individuos padre
                usados.clear();
                aleatorios=generaNumerosAleatorios(numeroDepuntosAgenerar,numeroDepuntosAletoriosMaximos);
                for(int k=0;k<aleatorios.size();k++) {//inicio de recorrido de vector de cortes
                    System.out.print("\nPC"+k+":"+aleatorios.get(k)+",");//mostrar el vector de cortes generado
                    //System.out.println("K:"+k);
                            ///////////////////////////////////para hacer cruze multipunto///////////////////////////////////////////////////////////
                    /*para individuo 1*/
                          /*Segmento inicial */
                            if(k==0){//k == 0 indica que es el primer corte 
                              int auxPC=(int)aleatorios.get(k);  
                              for (int i = x; i < (x + 1); i++) {//x equivale al primer individuo asi ue aqui esta posicionado en el primer individuo
                                  System.out.print("\t\t");
                                  for (int j = 0; j < auxPC; j++) {//j se utiliza para rocorrer entra cada cromosoma de indiduo
                                      individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                      System.out.print(mejoresIndividosPadres[i][j]);
                                  }
                              }
                            }
                          /*Segmento inicial */
                          
                          /*segmento intermedio*/
                          if(k != 0 && k < aleatorios.size()){ // si k es diferente de cero dice que es la siguiente corte despesu del primero
                              
                              int auxPC1=(int)aleatorios.get(k-1);
                              int auxPC2=(int)aleatorios.get(k);
                              for (int i = (x + 1); i < (x + 2); i++) {//x+1 equivale a segundo individuo
                                    System.out.print("\t\t");
                                    for (int j = auxPC1; j < auxPC2; j++) {
                                        individuosCruzados[i-1][j] = mejoresIndividosPadres[i][j];
                                        System.out.print(mejoresIndividosPadres[i][j]);
                                    }
                              }
                          } 
                          /*segmento intermedio*/
                         
                          /*segmento final */
                            if(k == aleatorios.size()-1){ //if k es igual al tamaño de aleatorio entonces es el ultimo corte
                              
                                int auxPC3=(int)aleatorios.get(k); 
                                for (int i = x; i < (x + 1); i++) {
                                        System.out.print("\t");
                                        for (int j = auxPC3; j < numeroDeGenes; j++) {
                                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                        }
                                }
                            }
                          /*segmento final*/
                            
                    /*para individuo 1*/

                    /*para individuo 2*/
                            
                           /*Segmento inicial */
                           if(k==0){  
                               int auxPC=(int)aleatorios.get(k); 
                                for (int i = (x + 1); i < (x + 2); i++) {
                                    System.out.print("\t");
                                    for (int j = 0; j < auxPC; j++) {
                                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                        System.out.print(mejoresIndividosPadres[i][j]);
                                    }
                                }
                            } 
                           /*Segmento inicial */
                         
                            /*segmento intermedio*/
                            if(k != 0 && k < aleatorios.size()){ 
                                int auxPC1=(int)aleatorios.get(k-1);
                                int auxPC2=(int)aleatorios.get(k);
                                for (int i = x; i < (x + 1); i++) {
                                    System.out.print("\t");
                                    for (int j = auxPC1; j < auxPC2; j++) {
                                        individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                                        System.out.print(mejoresIndividosPadres[i][j]);
                                    }
                                }
                            }
                            /*segmento intermedio*/
                            
                            /*segmento final*/
                            if(k == aleatorios.size()-1){
                               
                                int auxPC3=(int)aleatorios.get(k); 
                                for (int i = (x + 1); i < (x + 2); i++) {
                                    System.out.print("\t");
                                    for (int j = auxPC3; j < numeroDeGenes; j++) {
                                        individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                        System.out.print(mejoresIndividosPadres[i][j]);
                                    }
                                }
                            }
                            /*segmento final*/
                          
                     ///////////////////////////////////para hacer cruze///////////////////////////////////////////////////////////
                }///fin de recorrido de vector de cortes
                 
                 
                 //copiarMatriz(int[][] vectorPadre, int[][] vectorHijo,int inicio,int fin)
                 //copiarVector(int[] vectorPadre, int[] vectorHijo,int inicio,int fin)
                 System.out.println("\n----------");   
                 aleatorios.clear();
            } 
        }
        /*
            Para mostrar prueva de cruzamultipunto
            System.out.println("\ncruzados prueva");
            for (int i = 0; i < cruzaprueva.length; i++) {
                //System.out.print((i+1)+"._");
                System.out.println("\t");
                for (int j = 0; j < cruzaprueva[i].length; j++) {
                    System.out.print(cruzaprueva[i][j]);
                }
                //System.out.println("\t"+valorIndividuos [i]+"\t\t\t"+valordelaFuncionAptitud[i]);
            }
            System.out.println("cruzados prueva---");
            //usados.clear();
        */
    }

    
    
    /*funcion de numeros aleatorios por rango especifico vercion 1*/
    public int aleatorio1(int minimo, int maximo){ 
        int numero;
            numero =(int) Math.floor(Math.random()*(maximo-minimo+1))+minimo;
        return numero; 
    }
    
    /*funcion de numeros aleatorios por rango especifico cercion 2*/
    public int aleatorio2(int minimo, int maximo){ 
         Random aleatorio = new Random();
        int numero;
            numero =(int) (aleatorio.nextDouble() * (maximo-1) +minimo);
        return numero; 
    }
    
    /*funcion de numeros aleatorios por rango especifico cercion 2*/
    public int aleatorio3(int minimo, int maximo){ 
        Random aleatorio = new Random();
        if (usados.size() != (maximo - minimo)) { 
            int numero=0;
            boolean repe = false;
            do {
                //numero =(int) Math.floor(Math.random()*(maximo-minimo+1))+minimo;
                numero =(int) (aleatorio.nextDouble() * (maximo-1) +minimo);
                repe=repetido(numero);
                if(repe==true){
                    //System.out.println("esta repetido");
                }
            }while (repe != false);
            usados.add(numero);      
            return numero; 
        }else{
            return 0; 
        }
    }

    public boolean repetido(int numero){ 
        boolean repe= false;
        //System.out.println("en metodo");
        for (int i = 0; i < usados.size(); i++) {
            int auxRepe=((int)usados.get(i));
            if (numero == auxRepe) {
                repe = true; 
            }
        }
        return repe; 
    } 
    
    public ArrayList generaNumerosAleatorios(int numeroDepuntosAgenerar,int numeroDepuntosAletoriosMaximos){
        ArrayList  ale = new ArrayList  (numeroDepuntosAgenerar);
        int corte;
        for (int i = 0; i < numeroDepuntosAgenerar; i++) {
                corte=aleatorio3(1,numeroDepuntosAletoriosMaximos);
                ale.add(corte);
        }
        //se ordena el array list
        Collections.sort(ale);
        //mostramos el arraya list
/*
        for(int x=0;x<aleatorios.size();x++) {
                System.out.println(aleatorios.get(x));
        }
*/
        return ale;
    }
    
    public void cruzaMultipuntoUniforme(){
        //System.out.println("\ncruza uniforme ");
       
            
            int numeroDepuntosAletoriosMaximos=numeroDeGenes;
            int numeroDepuntosAgenerar=numeroCortes;/*este parametro es cambiante lo da el usuario paro no debe de revasar
            la cantidad maxima de genes del cromosoma ni el la posicion menor ni en la mayor el cromosoma inicia
            desde la posicion 1 y termina el la pocion n-1 osea si el cromosoma es de 8 el maximo es 7*/

            int corte;
            int puntosDecorte[]=new int [numeroDepuntosAgenerar];
            ArrayList  aleatorios = new ArrayList();

            System.out.println("\nindividuos seleccionados"); 
            mostrarPadresSelectos();
            System.out.println("\n");
            /*el vector selo se genera una vez*/
            usados.clear();
            aleatorios=generaNumerosAleatorios(numeroDepuntosAgenerar,numeroDepuntosAletoriosMaximos);
            for (int x = 0; x < mejoresIndividosPadres.length; x++) {//x es el numero de individuos por deneracion
                if (x % 2 == 0 || x == 0) {//identificamos cada uno de los individuos en par para cruzar cada par de individuos padre
                    /**/
                    for(int k=0;k<aleatorios.size();k++) {//inicio de recorrido de vector de cortes
                        System.out.print("\nPC"+k+":"+aleatorios.get(k)+",");//mostrar el vector de cortes generado
                        //System.out.println("K:"+k);
                                ///////////////////////////////////para hacer cruze multipunto///////////////////////////////////////////////////////////
                        /*para individuo 1*/
                              /*Segmento inicial */
                                if(k==0){//k == 0 indica que es el primer corte 
                                  int auxPC=(int)aleatorios.get(k);  
                                  for (int i = x; i < (x + 1); i++) {//x equivale al primer individuo asi ue aqui esta posicionado en el primer individuo
                                      System.out.print("\t\t");
                                      for (int j = 0; j < auxPC; j++) {//j se utiliza para rocorrer entra cada cromosoma de indiduo
                                          individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                          System.out.print(mejoresIndividosPadres[i][j]);
                                      }
                                  }
                                }
                              /*Segmento inicial */

                              /*segmento intermedio*/
                              if(k != 0 && k < aleatorios.size()){ // si k es diferente de cero dice que es la siguiente corte despesu del primero

                                  int auxPC1=(int)aleatorios.get(k-1);
                                  int auxPC2=(int)aleatorios.get(k);
                                  for (int i = (x + 1); i < (x + 2); i++) {//x+1 equivale a segundo individuo
                                        System.out.print("\t\t");
                                        for (int j = auxPC1; j < auxPC2; j++) {
                                            individuosCruzados[i-1][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                        }
                                  }
                              } 
                              /*segmento intermedio*/

                              /*segmento final */
                                if(k == aleatorios.size()-1){ //if k es igual al tamaño de aleatorio entonces es el ultimo corte

                                    int auxPC3=(int)aleatorios.get(k); 
                                    for (int i = x; i < (x + 1); i++) {
                                            System.out.print("\t");
                                            for (int j = auxPC3; j < numeroDeGenes; j++) {
                                                individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                                System.out.print(mejoresIndividosPadres[i][j]);
                                            }
                                    }
                                }
                              /*segmento final*/

                        /*para individuo 1*/

                        /*para individuo 2*/

                               /*Segmento inicial */
                               if(k==0){  
                                   int auxPC=(int)aleatorios.get(k); 
                                    for (int i = (x + 1); i < (x + 2); i++) {
                                        System.out.print("\t");
                                        for (int j = 0; j < auxPC; j++) {
                                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                        }
                                    }
                                } 
                               /*Segmento inicial */

                                /*segmento intermedio*/
                                if(k != 0 && k < aleatorios.size()){ 
                                    int auxPC1=(int)aleatorios.get(k-1);
                                    int auxPC2=(int)aleatorios.get(k);
                                    for (int i = x; i < (x + 1); i++) {
                                        System.out.print("\t");
                                        for (int j = auxPC1; j < auxPC2; j++) {
                                            individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                        }
                                    }
                                }
                                /*segmento intermedio*/

                                /*segmento final*/
                                if(k == aleatorios.size()-1){

                                    int auxPC3=(int)aleatorios.get(k); 
                                    for (int i = (x + 1); i < (x + 2); i++) {
                                        System.out.print("\t");
                                        for (int j = auxPC3; j < numeroDeGenes; j++) {
                                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                        }
                                    }
                                }
                                /*segmento final*/

                         ///////////////////////////////////para hacer cruze///////////////////////////////////////////////////////////
                    }///fin de recorrido de vector de cortes

                    System.out.println("\n----------");   
                    //aleatorios.clear();
                } 
            }

        
    }
    
    public void cruzaMultipuntoAdaptativa(int g){
        
        if(g==1){
            int numeroDepuntosAletoriosMaximos=numeroDeGenes;
            int numeroDepuntosAgenerar=numeroCortes;/*este parametro es cambiante lo da el usuario paro no debe de revasar
            la cantidad maxima de genes del cromosoma ni el la posicion menor ni en la mayor el cromosoma inicia
            desde la posicion 1 y termina el la pocion n-1 osea si el cromosoma es de 8 el maximo es 7*/
            //individuos = (int[][])redimencionarArreglo(individuos,numeroDeIndividuos);
            int corte;
            int puntosDecorte[]=new int [numeroDepuntosAgenerar];
            ArrayList  aleatorios = new ArrayList();

            System.out.println("\nindividuos seleccionados"); 
            mostrarPadresSelectos();
            System.out.println("\n");

            for (int x = 0; x < mejoresIndividosPadres.length; x++) {//x es el numero de individuos por deneracion
                if (x % 2 == 0 || x == 0) {//identificamos cada uno de los individuos en par para cruzar cada par de individuos padre
                    usados.clear();
                    aleatorios=generaNumerosAleatorios(numeroDepuntosAgenerar,numeroDepuntosAletoriosMaximos);
                    for(int k=0;k<aleatorios.size();k++) {///inicio de recorrido de vector de cortes
                        System.out.print("\nPC"+k+":"+aleatorios.get(k)+",");//mostrar el vector de cortes generado
                        //System.out.println("K:"+k);
                                ///////////////////////////////////para hacer cruze multipunto///////////////////////////////////////////////////////////
                        /*para individuo 1*/
                              /*Segmento inicial */
                                if(k==0){//k == 0 indica que es el primer corte 
                                  int auxPC=(int)aleatorios.get(k);  
                                  for (int i = x; i < (x + 1); i++) {//x equivale al primer individuo asi ue aqui esta posicionado en el primer individuo
                                      System.out.print("\t\t");
                                      for (int j = 0; j < auxPC; j++) {//j se utiliza para rocorrer entra cada cromosoma de indiduo
                                          individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                          System.out.print(mejoresIndividosPadres[i][j]);
                                          /*para asocicion de cortes*/
                                           if(j==(auxPC-1)){
                                                cortesDecruzeAdaptativos[i][j]=1;
                                           }else{
                                                cortesDecruzeAdaptativos[i][j]=0;
                                           }
                                          /*para asocicion de cortes*/ 
                                      }
                                  }
                                }
                              /*Segmento inicial */

                              /*segmento intermedio*/
                              if(k != 0 && k < aleatorios.size()){ // si k es diferente de cero dice que es la siguiente corte despesu del primero

                                  int auxPC1=(int)aleatorios.get(k-1);
                                  int auxPC2=(int)aleatorios.get(k);
                                  for (int i = (x + 1); i < (x + 2); i++) {//x+1 equivale a segundo individuo
                                        System.out.print("\t\t");
                                        for (int j = auxPC1; j < auxPC2; j++) {
                                            individuosCruzados[i-1][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                            /*para asocicion de cortes*/
                                            if(j==(auxPC2-1)){
                                                cortesDecruzeAdaptativos[i][j]=1;
                                            }else{
                                                cortesDecruzeAdaptativos[i][j]=0;
                                            }
                                            /*para asocicion de cortes*/
                                        }
                                  }
                              } 
                              /*segmento intermedio*/

                              /*segmento final */
                                if(k == aleatorios.size()-1){ //if k es igual al tamaño de aleatorio entonces es el ultimo corte

                                    int auxPC3=(int)aleatorios.get(k); 
                                    for (int i = x; i < (x + 1); i++) {
                                            System.out.print("\t");
                                            for (int j = auxPC3; j < numeroDeGenes; j++) {
                                                individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                                System.out.print(mejoresIndividosPadres[i][j]);
                                                /*para asocicion de cortes*/
                                                if(j==(auxPC3)){
                                                    cortesDecruzeAdaptativos[i][j-1]=1;
                                                }else{
                                                    cortesDecruzeAdaptativos[i][j]=0;
                                                }
                                                /*para asocicion de cortes*/
                                                }
                                    }
                                }
                              /*segmento final*/
                              
                              /*****asociacion de punto de corte individuo1*****************/
                              /*  
                                for (int i = x; i < (x + 1); i++) {
                                    for (int j = 0; j < numeroDeGenes; j++) {
                                        if(j==puntoDecorte){
                                            cortesDecruzeAdaptativos[i][j-1]=1;
                                        }else{
                                            cortesDecruzeAdaptativos[i][j]=0;
                                        }
                                    }
                                }
                               */  
                            /*****asociacion de punto de corte individuo1*****************/
                        /*para individuo 1*/

                        /*para individuo 2*/

                               /*Segmento inicial */
                               if(k==0){  
                                   int auxPC=(int)aleatorios.get(k); 
                                    for (int i = (x + 1); i < (x + 2); i++) {
                                        System.out.print("\t");
                                        for (int j = 0; j < auxPC; j++) {
                                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                            /*para asocicion de cortes*/
                                            if(j==auxPC){
                                                cortesDecruzeAdaptativos[i][j-1]=1;
                                            }else{
                                                cortesDecruzeAdaptativos[i][j]=0;
                                            }
                                            /*para asocicion de cortes*/
                                        }
                                    }
                                } 
                               /*Segmento inicial */

                                /*segmento intermedio*/
                                if(k != 0 && k < aleatorios.size()){ 
                                    int auxPC1=(int)aleatorios.get(k-1);
                                    int auxPC2=(int)aleatorios.get(k);
                                    for (int i = x; i < (x + 1); i++) {
                                        System.out.print("\t");
                                        for (int j = auxPC1; j < auxPC2; j++) {
                                            individuosCruzados[i + 1][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                            /*para asocicion de cortes*/
                                            if(j==auxPC2){
                                                cortesDecruzeAdaptativos[i][j-1]=1;
                                            }else{
                                                cortesDecruzeAdaptativos[i][j]=0;
                                            }
                                            /*para asocicion de cortes*/
                                        }
                                    }
                                }
                                /*segmento intermedio*/

                                /*segmento final*/
                                if(k == aleatorios.size()-1){

                                    int auxPC3=(int)aleatorios.get(k); 
                                    for (int i = (x + 1); i < (x + 2); i++) {
                                        System.out.print("\t");
                                        for (int j = auxPC3; j < numeroDeGenes; j++) {
                                            individuosCruzados[i][j] = mejoresIndividosPadres[i][j];
                                            System.out.print(mejoresIndividosPadres[i][j]);
                                            /*para asocicion de cortes*/
                                            if(j==auxPC3){
                                                cortesDecruzeAdaptativos[i][j-1]=1;
                                            }else{
                                                cortesDecruzeAdaptativos[i][j]=0;
                                            }
                                            /*para asocicion de cortes*/
                                        }
                                    }
                                }
                                /*segmento final*/
                                
                                /*****asociacion de punto de corte individuo2*****************/
                                /*
                                for (int i = (x + 1); i < (x + 2); i++) {
                                    for (int j = 0; j < numeroDeGenes; j++) {
                                        if(j==puntoDecorte){
                                            cortesDecruzeAdaptativos[i][j-1]=1;
                                        }else{
                                            cortesDecruzeAdaptativos[i][j]=0;
                                        }
                                    }
                                }
                                */
                            /*****asociacion de punto de corte individuo2*****************/

                         ///////////////////////////////////para hacer cruze///////////////////////////////////////////////////////////
                    }///fin de recorrido de vector de cortes


                     //copiarMatriz(int[][] vectorPadre, int[][] vectorHijo,int inicio,int fin)
                     //copiarVector(int[] vectorPadre, int[] vectorHijo,int inicio,int fin)
                     System.out.println("\n----------");   
                     aleatorios.clear();
                     
                    
                } 
            }
            
            /*********************cortes de cada individuos ***************/
                for (int[] cortesDecruzeAdaptativo : cortesDecruzeAdaptativos) {
                    System.out.println("\t");
                    for (int j = 0; j < numeroDeGenes; j++) {
                        System.out.print(cortesDecruzeAdaptativo[j]);
                        }
                    }
            /*********************cortes de cada individuos ***************/
        }else{
            
        }
    }
    /*Metodo de muctacion de individuos para obtener una muctacion con una 
     probabilidad de 1 entre 1000*/
    public void mutacion() {
        individuosCruzados();
        for (int[] individuosCruzado : individuosCruzados) {
            System.out.print("\t");
            for (int j = 0; j < individuosCruzado.length; j++) {
                int total=obtenerProvabilidadDeMutacion(porcentaje);/*el valor de entrada se obtiene de la interfaz*/
                int probalidaDeMutacion = (int) (Math.random() * total);
                //System.out.print(probalidaDeMutacion);
                if (probalidaDeMutacion == 1) {
                    //System.out.println("es uno");
                    if (individuosCruzado[j] == 0) {
                        individuosCruzado[j] = 1;
                    } else {
                        individuosCruzado[j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < individuosCruzados.length; i++) {
            System.arraycopy(individuosCruzados[i], 0, individuosMutados[i], 0, individuosCruzados[i].length);

        }
        individuosMutados();

    }
    
    /*Metodo para probabilidad de mutacion*/
    public int obtenerProvabilidadDeMutacion(double porcentaje){
        int probabilidad = 0;
            probabilidad=(int)( 1000 * porcentaje ) ;  
            //System.out.println("La probabilidad es de :"+probabilidad);
        return probabilidad;
    }
    
    /*Metodo que genera una nueva poblacion despues de generar la generacion 
     *base*/
 
    public void generarNuevaPoblacion() {
        for (int i = 0; i < individuos.length; i++) {
            for (int j = 0; j < numeroDeGenes; j++) {
                individuos[i][j] = individuosMutados[i][j];
            }
        }
        System.out.println("\n");
        obtenerValordelosIndividuos();
        obtenerValordeAptitud();
        mostrarIndividuos();
        mejorIndividuo=buscarMejorIndividuo();
        
    }
    
    public void tipoSeleccion(String opcionDeSeleccion){
        switch(opcionDeSeleccion){
            case"Aleatoria":
                seleccionAleatoria();
            break;
            case"Ruleta":
                seleleccionPorRuleta();
            break;
            case"Torneo":
                seleccionPorTorneo();
            break;
            case"MuestreoEstocastico":
                seleleccionPorMuestreoEstocasticoUniversal();
            break;
            default:
                seleccionAleatoria();
        }  
    }
    
    public void tipoCruza(String opcionCruza,int i){
        switch(opcionCruza){
            case"cruzaUnPunto":
                cruzaUnPunto();           //cruze de un punto de manera aleatoria (X1PA)
            break;
            case"cruzaDedosPuntos":
                cruzaDedosPuntos();       //cruze de dos puntos de manera aleatoria (X12PA)
            break;
            case"cruzedeUnPuntoUniforme":
                cruzedeUnPuntoUniforme(); //cruze en un punto de manera uniforme X1PU
            break;
            case"cruzedeUnpuntoManeraApaptativa":
                cruzedeUnpuntoManeraApaptativa(i);
            break;
            case"cruzaMultipunto":
                cruzeMultipuntoAleatorio();      
            break;
            case"cruzaMultipuntoUniforme":
                cruzaMultipuntoUniforme(); 
            break;
            case"cruzaMultipuntoAdaptativa":
                cruzaMultipuntoAdaptativa(i);
            break;
            default:
                cruzaUnPunto();
        }  
    }
    
    public static void copiarMatriz(int[][] vectorPadre, int[][] vectorHijo,int inicio,int fin) {
            for (int i = 0; i < vectorPadre.length; i++) {
                System.arraycopy(vectorPadre[i], inicio, vectorHijo[i], inicio, fin);
            }
    }
    
    public static void copiarVector(int[] vectorPadre, int[] vectorHijo,int inicio,int fin) {
                System.arraycopy(vectorPadre, inicio, vectorHijo, inicio, fin);

    }
    
    public void redimecionarTodo(int numeroDeIndividuos,int numeroDeGenes,int numeroDeGeneraciones){
         
         //ejemplo---vec = (int[])redimencionarArreglo(vec, (vec.length)-1);
        //redimencion de vectores 
         valorIndividuos = (double[])redimencionarArreglo(valorIndividuos, numeroDeIndividuos);
         valordelaFuncionAptitud = (double[])redimencionarArreglo(valordelaFuncionAptitud, numeroDeIndividuos);
         mejoresInvidiosDegeneraciones = (double[])redimencionarArreglo( mejoresInvidiosDegeneraciones, numeroDeGeneraciones);

         
         /* ejemplo
         matrizDeGramaticasHechas = (String[][])redimencionarArreglo(matrizDeGramaticasHechas, (matrizDeGramaticasHechas.length)+1);
                for (int i=0; i<matrizDeGramaticasHechas.length; i++) {
                    if (matrizDeGramaticasHechas[i] == null)
                        matrizDeGramaticasHechas[i] = new String[2];
                    else matrizDeGramaticasHechas[i] = (String[])redimencionarArreglo(matrizDeGramaticasHechas[i], 2);
                }
         */
         
         individuos = (int[][])redimencionarArreglo(individuos,numeroDeIndividuos);
         for (int i=0; i<individuos.length; i++) {
                    if (individuos[i] == null)
                        individuos[i] = new int[numeroDeGenes];
                    else individuos[i] = (int[])redimencionarArreglo(individuos[i], numeroDeGenes);
         }

         mejoresIndividosPadres = (int[][])redimencionarArreglo(mejoresIndividosPadres,numeroDeIndividuos);
         for (int i=0; i<mejoresIndividosPadres.length; i++) {
                    if (mejoresIndividosPadres[i] == null)
                        mejoresIndividosPadres[i] = new int[numeroDeGenes];
                    else mejoresIndividosPadres[i] = (int[])redimencionarArreglo(mejoresIndividosPadres[i], numeroDeGenes);
         }
         
         individuosCruzados = (int[][])redimencionarArreglo(individuosCruzados,numeroDeIndividuos);
         for (int i=0; i<individuosCruzados.length; i++) {
                    if (individuosCruzados[i] == null)
                        individuosCruzados[i] = new int[numeroDeGenes];
                    else individuosCruzados[i] = (int[])redimencionarArreglo(individuosCruzados[i], numeroDeGenes);
         }
         
         individuosMutados = (int[][])redimencionarArreglo(individuosMutados,numeroDeIndividuos);
         for (int i=0; i<individuosMutados.length; i++) {
                    if (individuosMutados[i] == null)
                        individuosMutados[i] = new int[numeroDeGenes];
                    else individuosMutados[i] = (int[])redimencionarArreglo(individuosMutados[i], numeroDeGenes);
         }
         
         cortesDecruzeAdaptativos = (int[][])redimencionarArreglo(cortesDecruzeAdaptativos,numeroDeIndividuos);
         for (int i=0; i<cortesDecruzeAdaptativos.length; i++) {
                    if (cortesDecruzeAdaptativos[i] == null)
                        cortesDecruzeAdaptativos[i] = new int[numeroDeGenes];
                    else cortesDecruzeAdaptativos[i] = (int[])redimencionarArreglo(cortesDecruzeAdaptativos[i], numeroDeGenes);
         }
         
         
              
    }
    
    private static Object redimencionarArreglo (Object oldArray, int newSize) {

        int oldSize = java.lang.reflect.Array.getLength(oldArray);

       Class elementType = oldArray.getClass().getComponentType();
       Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
       int preserveLength = Math.min(oldSize, newSize);
       if (preserveLength > 0)
          System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
       return newArray; 
    } 
}
