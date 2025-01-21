package mains;

public class Rocha extends Obstaculo{

    public Rocha(int id, Posicao posicao) {
        super(id, posicao);
    }

    public void bater(Robo robo) {
        if (this.posicao.equals(robo.getPosicao())){
            try {
                robo.mover(robo.getDirecao());
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
