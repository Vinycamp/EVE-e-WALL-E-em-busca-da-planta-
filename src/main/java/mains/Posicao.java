package mains;

public class Posicao {
    private int x;
    private int y;

    public Posicao() {
        this.x = 0;
        this.y = 0;
    }

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica se é o mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica tipo
        Posicao posicao = (Posicao) obj; // Faz o cast
        return x == posicao.x && y == posicao.y; // Compara os valores de x e y
    }

}