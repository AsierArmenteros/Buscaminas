public class Celda {
    private boolean bomba;
    private char valorActual;
    private boolean mostrada;

    public boolean isMostrada() {
        return mostrada;
    }

    public void setMostrada(boolean mostrada) {
        this.mostrada = mostrada;
    }

    public void setValorActual(char valorActual) {
        this.valorActual = valorActual;
    }

    public char getValorActual() {

        return valorActual;
    }

    public Celda(){
        this.bomba=false;
        this.setMostrada(false);
    }
    public void asignarBomba() {
        this.bomba=true;
    }


    public boolean isBomba() {

        return bomba;
    }






}
