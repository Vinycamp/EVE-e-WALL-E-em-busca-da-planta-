package mains;

public class MovimentoInvalidoException extends Exception {
        public MovimentoInvalidoException(Posicao posicao) {
            super("Movimento para (" + posicao.getX() + ", " + posicao.getY() + ") é invalido");
        }
}