import java.util.InputMismatchException;
import java.util.Scanner;

public class Buscaminas {

    private final int TAMAÑO=10;

    private final int porcentajeBombas=10;

    private int numeroBombas;

    private boolean perdido;

    private Celda[][] matriz= new Celda[TAMAÑO][TAMAÑO];

    private int[] eleccion=new int[2];

    private final char BOMBA = '*';

    private final char OCULTA = '#';

    private final char M_SIN_BOMBAS = ' ';

    private int aciertos;

    public Buscaminas(){
        this.numeroBombas=0;
        this.perdido=false;
        this.aciertos=0;
        int bomba;
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                bomba = (int)Math.floor(Math.random()*100);
                matriz[i][j]=new Celda();
                matriz[i][j].setValorActual(OCULTA);
                if(bomba<porcentajeBombas){
                    this.matriz[i][j].asignarBomba();
                    this.numeroBombas++;
                }
            }
        }
    }

    public void start() {
        this.iniciar();
        do{
            this.visualizar();
            this.eleccion();
            this.actualizarSeleccion(eleccion[0], eleccion[1]);

        }while(!this.perdido&&((TAMAÑO*TAMAÑO)-numeroBombas)>aciertos);

        this.visualizar();

        if(this.perdido){
            System.out.println("\nHAS PERDIDO!!");
        }
        else {
            System.out.println("\nHAS GANADO!!");
        }
    }

    private void actualizarSeleccion(int i, int j) {
        if(matriz[i][j].isBomba()){
            matriz[i][j].setValorActual(BOMBA);
            matriz[i][j].setMostrada(true);
            this.perdido=true;
        }
        else{
            int bombas;
            bombas = this.contadorBombas(i, j);
            if(bombas == 0){
                matriz[i][j].setValorActual(M_SIN_BOMBAS);
                matriz[i][j].setMostrada(true);
                this.aciertos++;
                this.actualizarCercanos(i, j);
            }
            else {
                matriz[i][j].setValorActual((char) (bombas + 48));
                matriz[i][j].setMostrada(true);
                this.aciertos++;
            }
        }
    }

    private void actualizarCercanos(int i, int j) {
        int  i0, iN, j0, jN;
        if(i==0) i0=0;
        else i0=i-1;
        if(i==TAMAÑO-1) iN=TAMAÑO-1;
        else iN=i+1;
        if(j==0) j0=0;
        else j0=j-1;
        if(j==TAMAÑO-1) jN=TAMAÑO-1;
        else jN=j+1;
        for (int k = i0; k <= iN; k++) {
            for (int l = j0; l <=jN; l++) {
                if(!matriz[k][l].isMostrada()) {
                    actualizarSeleccion(k, l);
                }
            }
        }
    }

    private int contadorBombas(int i, int j) {
        int cont=0, i0, iN, j0, jN;
        if(i==0) i0=0;
        else i0=i-1;
        if(i==TAMAÑO-1) iN=TAMAÑO-1;
        else iN=i+1;
        if(j==0) j0=0;
        else j0=j-1;
        if(j==TAMAÑO-1) jN=TAMAÑO-1;
        else jN=j+1;

        for (int k = i0; k <=iN; k++) {
            for (int l = j0; l <=jN; l++) {
                if(matriz[k][l].isBomba()&&!matriz[k][l].isMostrada()){

                    cont++;
                }
            }
        }
        return cont;
    }

    private void visualizar() {
        System.out.print("     ");
        for (int i = 0; i <TAMAÑO; i++) {
            System.out.print(i+ "    ");
        }
        System.out.print("\n");
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j <= TAMAÑO; j++) {
                if(j==0){
                    System.out.print(i + "    ");
                }
                else System.out.print(matriz[i][j-1].getValorActual()+ "    ");
            }
            System.out.println("\n");
        }
    }


    private void eleccion() {
        eleccion[0]=this.eleccionX();
        eleccion[1]=this.eleccionY();
        if(matriz[eleccion[0]][eleccion[1]].isMostrada()){
            System.out.println("Casilla introducida ya mostrada, prueba con otra.\n");
            this.eleccion();
        }
    }

    private int eleccionY() {
        int Y;
        Scanner scanner=new Scanner(System.in);
            System.out.print("Introduzca la Coordenada Y deseada:  ");
            try {
                Y=scanner.nextInt();
                if(Y < 0 || Y > 9 ){
                    System.out.println("Valor introducido incorrecto, los limites son: " + 0 + " y " + (TAMAÑO-1) );
                    Y=eleccionY();
                }
            }
            catch (InputMismatchException e){
                Y=eleccionY();
            }
        return Y;
    }

    private int eleccionX() {
        int X;
        Scanner scanner=new Scanner(System.in);
            System.out.print("Introduzca la Coordenada X deseada:  ");
            try {
                X=scanner.nextInt();
                if(X < 0 || X > 9){
                    System.out.println("Valor introducido incorrecto, los limites son: " + 0 + " y " + (TAMAÑO-1) );
                    X=eleccionX();
                }
            }
            catch (InputMismatchException e){
               X=eleccionX();
            }

        return X;
    }

    private void iniciar() {
        System.out.println("***************************************************************");
        System.out.println("                        BUSCAMINAS                             ");
        System.out.println("***************************************************************");
        System.out.println("\n LEYENDA:\n BOMBAS: * \n CELDA OCULTA: # \n CELDA MOSTRADA:   \n");
    }
}
