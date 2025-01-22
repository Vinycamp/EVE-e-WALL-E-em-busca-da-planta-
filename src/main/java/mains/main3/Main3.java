package mains.main3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import mains.MovimentoInvalidoException;
import mains.Particula;
import mains.Posicao;
import mains.Robo;
import mains.RoboInteligente;

public class Main3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private static final int LARGURA = 800;
    private static final int ALTURA = 600;
    private List<Particula> particulas = new ArrayList<>();
    private Random random = new Random();
    private String corSelecionadaEVE;
    private String corSelecionadaWALLE;
    private Posicao alimento = null;

    public void start(Stage stage) {
        // Layout principal
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 450, 400);
        stage.setTitle("Jogo");
        stage.setScene(scene);
        stage.setResizable(false);

        Image directive = new Image("file:src/main/resources/images/directive.png");
        ImageView directiveView = new ImageView(directive);
        directiveView.setFitWidth(452);
        directiveView.setFitHeight(410);
        directiveView.setPreserveRatio(false);
        root.getChildren().add(directiveView);

        // Topo - Formulário
        VBox formLayout = new VBox(10);
        formLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label titulo = new Label("EVA e Wall-e ao resgate da planta!");
        titulo.setStyle("-fx-font-size: 20; -fx-alignment: center; -fx-font-weight: bold; -fx-text-fill: white;");

        Label label = new Label(" Digite a posição da planta:");
        label.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        Label eixoX = new Label("Eixo X");
        eixoX.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        Label eixoY = new Label("Eixo Y");
        eixoY.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        HBox hBox = new HBox(70, eixoX, eixoY);
        hBox.setStyle("-fx-alignment: center;");

        TextField textFieldX = new TextField();
        textFieldX.setMaxWidth(100);
        textFieldX.setStyle("-fx-alignment: center;");
        TextField textFieldY = new TextField();
        textFieldY.setMaxWidth(100);
        textFieldY.setStyle("-fx-alignment: center;");

        HBox hBox2 = new HBox(10, textFieldX, textFieldY);
        hBox2.setStyle("-fx-alignment: center;");

        Label minMaxX = new Label("Min: 2 Max: 20");
        minMaxX.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        Label minMaxY = new Label("Min: 2 Max: 10");
        minMaxY.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
        HBox hBox3 = new HBox(10, minMaxX, minMaxY);
        hBox3.setStyle("-fx-alignment: center;");

        Button button = new Button("Enviar");

        //Caixa de escolha de cor
        ChoiceBox<String> escolherCorEVE = new ChoiceBox<>();
        escolherCorEVE.getItems().addAll("Azul", "Branco", "Laranja", "Verde", "Vermelho");
        escolherCorEVE.setValue("Branco");
        corSelecionadaEVE = escolherCorEVE.getValue().toLowerCase();

        escolherCorEVE.setOnAction(e ->{
            corSelecionadaEVE = escolherCorEVE.getValue().toLowerCase();
        });

        Label corEVE = new Label("Cor da EVE:");
        corEVE.setStyle("-fx-text-fill: white; -fx-font-weight: bold");

        ChoiceBox<String> escolherCorWALLE = new ChoiceBox<>();
        escolherCorWALLE.getItems().addAll("Azul", "Branco", "Laranja", "Verde", "Vermelho");
        escolherCorWALLE.setValue("Laranja");
        corSelecionadaWALLE = escolherCorWALLE.getValue().toLowerCase();

        escolherCorWALLE.setOnAction(e ->{
            corSelecionadaWALLE = escolherCorWALLE.getValue().toLowerCase();
        });

        Label corWALLE = new Label("Cor da WALLE:");
        corWALLE.setStyle("-fx-text-fill: white; -fx-font-weight: bold");

        VBox vBox = new VBox(10, corEVE, escolherCorEVE, corWALLE, escolherCorWALLE);

        // Adicionar os elementos do formulário
        formLayout.getChildren().addAll(titulo, label, hBox, hBox2, hBox3, button, vBox);
        root.setCenter(formLayout);

        // Lógica do botão "Enviar"
        button.setOnAction(event -> {
            if (corSelecionadaEVE != null && corSelecionadaWALLE != null) {
                if (textFieldX.getText().equals("Hello") && textFieldY.getText().equals("Everynyan")) {
                    Media media = new Media(getClass().getResource("/videos/ohmygah.mp4").toExternalForm());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(mediaPlayer);
                    mediaView.setFitWidth(400);
                    mediaView.setFitHeight(300);
                    root.setCenter(mediaView);
                    mediaPlayer.play();
                } else {
                    int x = Integer.parseInt(textFieldX.getText()) - 1;
                    int y = Integer.parseInt(textFieldY.getText()) - 1;
                    if (x > 0 && x < 20 && y > 0 && y < 10) {
                        this.alimento = new Posicao(x, y);
                        // Exibir o jogo
                        this.iniciarJogo(stage);
                    } else {
                        System.out.println("X ou Y inválidos.");
                    }
                }
            } else {
                System.out.println("Selecione a cor da EVE!\nSelecione a cor da WALLE!");
            }
        });

        stage.show();
    }

    private void iniciarJogo(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1054, 796);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.sizeToScene();

        // Adiciona o background do piso
        // Tiles a esquerda da área principal
        for (int i = 0; i < 15; i++) {
            Image tile;
            if (Math.random() < 0.5) {
                tile = new Image("file:src/main/resources/images/dark_tile.png");
            } else {
                tile = new Image("file:src/main/resources/images/light_tile.png");
            }
            ImageView tileView = new ImageView(tile);
            tileView.setLayoutY(i * 48);
            root.getChildren().add(tileView);
        }
        // Tiles embaixo da área principal
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 2; j++) {
                Image tile;
                if (Math.random() < 0.5) {
                    tile = new Image("file:src/main/resources/images/dark_tile.png");
                } else {
                    tile = new Image("file:src/main/resources/images/light_tile.png");
                }
                ImageView tileView = new ImageView(tile);
                tileView.setLayoutY(j * 48 + 724);
                tileView.setLayoutX(i * 50 + 54);
                root.getChildren().add(tileView);
            }
        }
        // Tiles a embaixo e a esquerda da área principal
        for (int i = 0; i < 2; i++) {
            Image tile;
            if (Math.random() < 0.5) {
                tile = new Image("file:src/main/resources/images/dark_tile.png");
            } else {
                tile = new Image("file:src/main/resources/images/light_tile.png");
            }
            ImageView tileView = new ImageView(tile);
            tileView.setLayoutY(724 + i * 48);
            root.getChildren().add(tileView);
        }
        // Tiles na área principal
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 15; j++) {
                Image tile;
                if (Math.random() < 0.5) {
                    tile = new Image("file:src/main/resources/images/dark_tile.png");
                } else {
                    tile = new Image("file:src/main/resources/images/light_tile.png");
                }
                ImageView tileView = new ImageView(tile);
                tileView.setLayoutY(-j * 48 + 672);
                tileView.setLayoutX(i * 50 + 54);
                root.getChildren().add(tileView);
            }
        }

        // Eixos
        Rectangle eixoX = new Rectangle(1054, 4, Color.BLACK);
        eixoX.setLayoutY(720);

        Rectangle eixoY = new Rectangle(4, 796, Color.BLACK);
        eixoY.setLayoutX(50);

        // Imagem dos robôs
        Image eveImage = new Image("file:src/main/resources/images/eve_"+ corSelecionadaEVE + ".png");
        ImageView eveView = new ImageView(eveImage);
        eveView.setLayoutX(56);
        eveView.setLayoutY(650);
        Image walleImage = new Image("file:src/main/resources/images/walle_"+ corSelecionadaWALLE + ".png");
        ImageView walleView = new ImageView(walleImage);
        walleView.setLayoutX(56);
        walleView.setLayoutY(650);

        // Imagem do alimento
        Image plantaAlimento = new Image("file:src/main/resources/images/planta.png");
        ImageView plantaAlimentoView = new ImageView(plantaAlimento);
        plantaAlimentoView.setLayoutX(56 + (this.alimento.getX() * 50));
        plantaAlimentoView.setLayoutY(650 - (this.alimento.getY() * 72));

        // Adicionar os elementos do jogo
        root.getChildren().addAll(eixoX, eixoY, eveView, walleView, plantaAlimentoView);

        // Instanciar o robô
        Robo eve = new RoboInteligente("default");
        Robo walle = new Robo("default");
        boolean[] vez = { false };

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50), e -> {
            int direcao = (int) (Math.random() * 4 + 1);
            try {
                if (vez[0]) {
                    eve.mover(direcao);
                    eve.setQuantidadeDeMovimentos(eve.getQuantidadeDeMovimentos() + 1);
                    eveView.setLayoutX(56 + eve.getPosicao().getX() * 50);
                    eveView.setLayoutY(650 - eve.getPosicao().getY() * 72);
                    vez[0] = false;
                } else {
                    walle.mover(direcao);
                    walle.setQuantidadeDeMovimentos(walle.getQuantidadeDeMovimentos() + 1);
                    walleView.setLayoutX(56 + walle.getPosicao().getX() * 50);
                    walleView.setLayoutY(650 - walle.getPosicao().getY() * 72);
                    vez[0] = true;
                }
            } catch (MovimentoInvalidoException ex) {
                if (vez[0]) {
                    eve.setQuantidadeDeMovimentosInvalidos(eve.getQuantidadeDeMovimentosInvalidos() + 1);
                    vez[0] = false;
                } else {
                    walle.setQuantidadeDeMovimentosInvalidos(walle.getQuantidadeDeMovimentosInvalidos() + 1);
                    vez[0] = true;
                }
                System.out.println(ex.getMessage());
            }
            if (eve.getPosicao().equals(this.alimento)) {
                timeline.stop();
                this.venceu(stage, "EVE", eve.getQuantidadeDeMovimentos(), eve.getQuantidadeDeMovimentosInvalidos());
            }
            if (walle.getPosicao().equals(this.alimento)) {
                timeline.stop();
                this.venceu(stage, "Wall-e", walle.getQuantidadeDeMovimentos(),
                        walle.getQuantidadeDeMovimentosInvalidos());
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void venceu(Stage stage, String quemVenceu, int quantidadeDeMovimentos,
                       int quantidadeDeMovimentosInvalidos) {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, LARGURA, ALTURA);
        stage.setScene(scene);
        stage.centerOnScreen();

        Image fundo = new Image("file:src/main/resources/images/chiyofatherNatal.png");
        ImageView fundoView = new ImageView(fundo);
        fundoView.setFitHeight(600);
        fundoView.setFitWidth(800);

        Canvas canvas = new Canvas(LARGURA, ALTURA);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().addAll(fundoView, canvas);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16),
                e -> desenhar(gc, quemVenceu, quantidadeDeMovimentos, quantidadeDeMovimentosInvalidos)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.show();

        // Gera partículas ao redor do texto
        gerarParticulasNoTexto("JavaFX", LARGURA / 2 - 100, ALTURA / 2);
    }

    private void desenhar(GraphicsContext gc, String quemVenceu, int quantidadeDeMovimentos,
                          int quantidadeDeMovimentosInvalidos) {
        // Limpa a tela
        gc.clearRect(0, 0, LARGURA, ALTURA);

        // Desenha o texto no centro
        Text textNode = new Text(quemVenceu + " achou a planta!");
        textNode.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        double textWidth = textNode.getLayoutBounds().getWidth();
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        gc.setFill(Color.GRAY);
        gc.fillText(quemVenceu + " achou a planta!", (LARGURA - textWidth) / 2 + 2, ALTURA / 2 - 128); // sombra
        gc.setFill(Color.BLACK);
        gc.fillText(quemVenceu + " achou a planta!", (LARGURA - textWidth) / 2, ALTURA / 2 - 130);

        gc.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        textNode = new Text("Movimentos válidos: " + quantidadeDeMovimentos);
        textNode.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        textWidth = textNode.getLayoutBounds().getWidth();
        gc.setFill(Color.GRAY);
        gc.fillText("Movimentos válidos: " + quantidadeDeMovimentos, (LARGURA - textWidth) / 2 + 2, ALTURA / 2 + 172); // sombra
        gc.setFill(Color.BLACK);
        gc.fillText("Movimentos válidos: " + quantidadeDeMovimentos, (LARGURA - textWidth) / 2, ALTURA / 2 + 170);

        textNode = new Text("Movimentos inválidos: " + quantidadeDeMovimentosInvalidos);
        textNode.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        textWidth = textNode.getLayoutBounds().getWidth();
        gc.setFill(Color.GRAY);
        gc.fillText("Movimentos inválidos: " + quantidadeDeMovimentosInvalidos, (LARGURA - textWidth) / 2 + 2,
                ALTURA / 2 + 222); // sombra
        gc.setFill(Color.BLACK);
        gc.fillText("Movimentos inválidos: " + quantidadeDeMovimentosInvalidos, (LARGURA - textWidth) / 2,
                ALTURA / 2 + 220);

        // Atualiza e desenha as partículas
        particulas.removeIf(p -> !p.isAtiva()); // Remove partículas "mortas"
        for (Particula particula : particulas) {
            particula.atualizar();
            gc.setFill(particula.cor);
            gc.fillOval(particula.x, particula.y, particula.raio, particula.raio);
        }
    }

    private void gerarParticulasNoTexto(String texto, double xTexto, double yTexto) {
        // Gera partículas ao redor do texto
        for (int i = 0; i < 100; i++) {
            double x = xTexto + random.nextInt(200) - 50; // Ao redor do texto
            double y = yTexto + random.nextInt(100) - 50;
            double velocidadeX = (random.nextDouble() - 0.5) * 2; // Velocidade aleatória
            double velocidadeY = (random.nextDouble() - 0.5) * 2;
            double raio = random.nextDouble() * 5 + 2; // Raio aleatório entre 2 e 7
            Color cor = Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 0.8); // Cor
            // aleatória

            particulas.add(new Particula(x, y, velocidadeX, velocidadeY, raio, cor));
        }
    }
}