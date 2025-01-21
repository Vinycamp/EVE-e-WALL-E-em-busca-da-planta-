package mains;

public class Bomba extends Obstaculo{
    public Bomba(int id, Posicao posicao) {
        super(id, posicao);
    }

    public void bater(Robo robo) {
        if (this.posicao.equals(robo.getPosicao())) {
            robo.destruir();
            this.posicao = new Posicao(-1,-1);
        }
    }
}
