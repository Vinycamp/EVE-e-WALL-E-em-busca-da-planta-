package mains;

public abstract class Obstaculo {
    protected int id;
    protected Posicao posicao;

    public Obstaculo(int id, Posicao posicao) {
        this.id = id;
        this.posicao = posicao;
    }

    public abstract void bater(Robo robo);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
