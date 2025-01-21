package mains;

public class Robo {
    protected Posicao posicao;
    protected String cor;
    protected int quantidadeDeMovimentos;
    protected boolean roboDestruido;


    public boolean isRoboDestruido() {
        return roboDestruido;
    }

    public int getDirecao() {
        return direcao;
    }

    protected int direcao;

    public int getQuantidadeDeMovimentosInvalidos() {
        return quantidadeDeMovimentosInvalidos;
    }

    public void setQuantidadeDeMovimentosInvalidos(int quantidadeDeMovimentosInvalidos) {
        this.quantidadeDeMovimentosInvalidos = quantidadeDeMovimentosInvalidos;
    }

    public int getQuantidadeDeMovimentos() {
        return quantidadeDeMovimentos;
    }

    public void setQuantidadeDeMovimentos(int quantidadeDeMovimentos) {
        this.quantidadeDeMovimentos = quantidadeDeMovimentos;
    }

    protected int quantidadeDeMovimentosInvalidos;

    public Robo(String cor) {
        this.posicao = new Posicao();
        this.cor = cor;
        this.quantidadeDeMovimentos = 0;
        this.quantidadeDeMovimentosInvalidos = 0;
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        int direcaoInt = 0;
        Posicao novaPosicao = new Posicao(this.posicao.getX(), this.posicao.getY());
        switch (direcao) {
            case "up":
                direcaoInt = 2;
                novaPosicao.setY(novaPosicao.getY() + 1);
                break;
            case "down":
                direcaoInt = 1;
                novaPosicao.setY(novaPosicao.getY() - 1);
                break;
            case "left":
                direcaoInt = 4;
                novaPosicao.setX(novaPosicao.getX() - 1);
                break;
            case "right":
                direcaoInt = 3;
                novaPosicao.setX(novaPosicao.getX() + 1);
                break;
        }
        if (novaPosicao.getX() < 0 || novaPosicao.getY() < 0 || novaPosicao.getX() > 19 || novaPosicao.getY() > 9) {
            throw new MovimentoInvalidoException(novaPosicao);
        } else {
            this.direcao = direcaoInt;
            this.posicao = novaPosicao;
        }
    }

    public void mover(int direcao) throws MovimentoInvalidoException {
        Posicao novaPosicao = new Posicao(this.posicao.getX(), this.posicao.getY());
        switch (direcao) {
            case 1:
                novaPosicao.setY(novaPosicao.getY() + 1);
                direcao = 2;
                break;
            case 2:
                novaPosicao.setY(novaPosicao.getY() - 1);
                direcao = 1;
                break;
            case 3:
                novaPosicao.setX(novaPosicao.getX() - 1);
                direcao = 4;
                break;
            case 4:
                direcao = 3;
                novaPosicao.setX(novaPosicao.getX() + 1);
                break;
        }
        if (novaPosicao.getX() < 0 || novaPosicao.getY() < 0 || novaPosicao.getX() > 19 || novaPosicao.getY() > 9) {
            throw new MovimentoInvalidoException(novaPosicao);
        } else {
            this.direcao = direcao;
            this.posicao = novaPosicao;
        }
    }

    public void destruir() {
        this.roboDestruido = true;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

}
