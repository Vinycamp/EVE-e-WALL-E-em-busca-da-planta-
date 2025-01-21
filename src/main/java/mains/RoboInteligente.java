package mains;

public class RoboInteligente extends Robo {
    public RoboInteligente(String cor) {
        super(cor);
    }

    public void mover(String direcao) {
        do {
            String direcaoOposta = "";
            int direcaoInt = 0;
            Posicao novaPosicao = new Posicao(this.posicao.getX(), this.posicao.getY());
            switch (direcao) {
                case "up":
                    direcaoOposta = "down";
                    direcaoInt = 2;
                    novaPosicao.setY(novaPosicao.getY() + 1);
                    break;
                case "down":
                    direcaoOposta = "up";
                    direcaoInt = 1;
                    novaPosicao.setY(novaPosicao.getY() - 1);
                    break;
                case "left":
                    direcaoOposta = "right";
                    direcaoInt = 4;
                    novaPosicao.setX(novaPosicao.getX() - 1);
                    break;
                case "right":
                    direcaoOposta = "left";
                    direcaoInt = 3;
                    novaPosicao.setX(novaPosicao.getX() + 1);
                    break;
            }
            if (novaPosicao.getX() < 0 || novaPosicao.getY() < 0 || novaPosicao.getX() > 19 || novaPosicao.getY() > 9) {
                direcao = direcaoOposta;
            } else {
                this.direcao = direcaoInt;
                this.posicao = novaPosicao;
                break;
            }
        } while (true);
    }

    public void mover(int direcao) {
        do {
            int direcaoOposta = 0;
            Posicao novaPosicao = new Posicao(this.posicao.getX(), this.posicao.getY());
            switch (direcao) {
                case 1:
                    direcaoOposta = 2;
                    novaPosicao.setY(novaPosicao.getY() + 1);
                    break;
                case 2:
                    direcaoOposta = 1;
                    novaPosicao.setY(novaPosicao.getY() - 1);
                    break;
                case 3:
                    direcaoOposta = 4;
                    novaPosicao.setX(novaPosicao.getX() - 1);
                    break;
                case 4:
                    direcaoOposta = 3;
                    novaPosicao.setX(novaPosicao.getX() + 1);
                    break;
            }
            if (novaPosicao.getX() < 0 || novaPosicao.getY() < 0 || novaPosicao.getX() > 19 || novaPosicao.getY() > 9) {
                direcao = direcaoOposta;
            } else {
                this.direcao = direcaoOposta;
                this.posicao = novaPosicao;
                break;
            }
        } while (true) ;
    }
}
