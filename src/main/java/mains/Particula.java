package mains;

import javafx.scene.paint.Color;

public class Particula {
    public double x; // Posição X da partícula
    public double y; // Posição Y da partícula
    public double velocidadeX; // Velocidade no eixo X
    public double velocidadeY; // Velocidade no eixo Y
    public double raio; // Tamanho da partícula
    public Color cor; // Cor da partícula

    public Particula(double x, double y, double velocidadeX, double velocidadeY, double raio, Color cor) {
        this.x = x;
        this.y = y;
        this.velocidadeX = velocidadeX;
        this.velocidadeY = velocidadeY;
        this.raio = raio;
        this.cor = cor;
    }

    public void atualizar() {
        // Atualiza a posição com base na velocidade
        this.x += this.velocidadeX;
        this.y += this.velocidadeY;

        // Reduz o tamanho da partícula para criar o efeito de desaparecer
        if (this.raio > 0) {
            this.raio -= 0.1; // Diminui gradualmente o raio
        }
    }

    public boolean isAtiva() {
        return this.raio > 0; // A partícula é visível enquanto o raio for maior que 0
    }
}
