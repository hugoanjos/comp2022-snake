
/**
 * Write a description of class Fila here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fila
{
    private Snake inicio;
    private Snake ultimo;
    private int max = 1;
    
    public Fila() {
        this.inicio = new Snake();
    }
    
    public void inserir(Snake _snake) {
        Snake aux = inicio;
        while (aux.getProximo() != null) {
            aux = aux.getProximo();
        }
        aux.setImage();
        aux.setProximo(_snake);
        ultimo = _snake;
    }
    
    public void remover() {
        Snake aux = inicio;
        aux = null;
    }
    
    public Snake getSnake(int position) {
        if(position < 0 || position > getSize()){
            return null;
        }
        
        Snake aux = inicio;
        for(int i = 1; i < position; i++) {
            aux = aux.getProximo();
        }
        return aux;
    }
    
    public int getSize(){
        int tamanho = 1;
        Snake aux = inicio;
        if (aux == null) {
            return 0;
        } else {
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
                tamanho++;
            }
        }
        return tamanho;
    }
    
    public int getMax() {
        return max;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
    public Snake getSnake() {
        return inicio;
    }
    
    public Snake getHead() {
        return ultimo;
    }
}
