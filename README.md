Simulador de Jogo de Tabuleiro com Lista Ligada Circular

Este projeto é uma simulação, em Java, de um jogo de tabuleiro estratégico inspirado em jogos como Banco Imobiliário. O foco principal é a aplicação de estruturas de dados, especialmente uma Lista Ligada Circular para representar o tabuleiro. O jogo inclui cadastro e gerenciamento de jogadores e imóveis, movimentação por lançamento de dados, interação com casas especiais e encerramento com ranking por patrimônio.

Observação: Esta versão não inclui, propositalmente, as funcionalidades de Baralho de Sorte/Revés e Prisão. Elas podem ser adicionadas futuramente sem alterar a base já construída.

Sumário
- Objetivos
- Estruturas de dados utilizadas
- Regras implementadas
- Como executar
- Como jogar (passo a passo)
- Estrutura do projeto
- Decisões e justificativas técnicas
- Limitações e próximos passos

Objetivos
- Representar o tabuleiro com uma Lista Ligada Circular, onde cada nó é uma casa do jogo.
- Implementar um fluxo de jogo por turnos com lançamento de dois dados e movimentação passo a passo, detectando passagem pelo Início para pagamento de salário.
- Permitir o gerenciamento (CRUD) de jogadores e imóveis antes do início do jogo.
- Realizar as interações básicas ao parar em uma casa: compra de imóvel, pagamento de aluguel, cobrança de imposto e restituição.
- Encerrar a partida por número máximo de rodadas ou quando restar apenas um jogador, apresentando um ranking final por patrimônio (saldo + valor de compra dos imóveis).

Estruturas de dados utilizadas
- Lista Ligada Circular (classe Tabuleiro):
  - Mantém referência para a última casa; a primeira casa é obtida por ultimaCasa.getProximaCasa().
  - Suporta inserção no início e no fim, preservando a circularidade.
  - Nós da lista são objetos da classe Casa, que carregam o tipo da casa e, quando aplicável, um Imovel associado.
- Enumerador TipoCasa: INICIO, IMOVEL, IMPOSTO, RESTITUICAO (entradas para PRISAO e SORTE_REVES já previstas, mas não utilizadas nesta etapa).
- Jogadores (classe Jogador): armazenados em uma lista dinâmica (ArrayList) para fácil CRUD.
- Imóveis (classe Imovel): também gerenciados via ArrayList no pré-jogo.

Regras implementadas
- Passagem pelo Início: ao passar pela casa INICIO durante a movimentação, o jogador recebe seu salário por volta.
- Casa de IMÓVEL:
  - Se não tiver dono, o jogador pode comprar (se houver saldo). A propriedade é adicionada à lista do jogador e o dono do imóvel é definido.
  - Se tiver dono e não for o próprio jogador, paga-se o aluguel ao proprietário.
- Casa de IMPOSTO: cobra 5% sobre o patrimônio total do jogador (saldo + soma dos preços de compra dos imóveis que possui).
- Casa de RESTITUIÇÃO: concede 10% do salário do jogador como bônus imediato.
- Encerramento: ocorre quando o número máximo de rodadas é alcançado ou quando restar apenas um jogador. O ranking final é ordenado pelo maior patrimônio total.

Como executar
Pré-requisito: JDK 8 ou superior instalado.

Opção 1: via IDE (IntelliJ IDEA, Eclipse, etc.)
1. Abra o projeto na IDE.
2. Compile o projeto (Build/Recompile).
3. Execute a classe Main (src/Main.java).

Opção 2: via linha de comando (Windows PowerShell)
1. Navegue até a pasta do projeto: C:\Users\seuUsuario\IdeaProjects\AS-ED
2. Compile as classes:
   javac -d out src\*.java
3. Execute o programa:
   java -cp out Main

Como jogar (passo a passo)
1. Ao iniciar, você verá o Menu de Configuração.
2. Cadastre jogadores (mínimo 2, máximo 6) e imóveis (mínimo 10, máximo 40). É possível listar e remover.
3. Defina as configurações da partida: saldo inicial, salário por volta e número máximo de rodadas.
4. Inicie o jogo. Caso não tenha o mínimo de jogadores ou imóveis, o sistema informará o erro e voltará ao menu.
5. Durante a partida, a cada turno, escolha entre:
   - Lançar dados e mover.
   - Ver o status completo (saldo e propriedades).
   - Desistir do jogo.
6. Ao mover, o sistema mostra os dados, avança passo a passo e paga o salário automaticamente sempre que o jogador passa pelo Início.
7. Ao parar em uma casa, a ação correspondente é executada (compra, aluguel, imposto, restituição).
8. O jogo termina ao atingir o número de rodadas configurado ou quando resta apenas um jogador. O ranking final é exibido.

Estrutura do projeto
AS-ED/
- src/
  - Main.java        (menu, configuração, execução do jogo, movimentação e lógica de turnos)
  - Tabuleiro.java   (implementa a Lista Ligada Circular do tabuleiro)
  - Casa.java        (nó do tabuleiro; encapsula o tipo e a ação da casa)
  - Imovel.java      (modelo de imóvel; preço, aluguel, dono)
  - Jogador.java     (modelo de jogador; saldo, salário, posição e propriedades)
  - TipoCasa.java    (enum com os tipos de casa)
- out/               (saída de compilação gerada após build)

Decisões e justificativas técnicas
- Lista Ligada Circular para o tabuleiro: facilita a simulação de voltas infinitas sem verificações de índice; a passagem pelo Início é detectada de forma natural percorrendo os nós.
- Passagem pelo Início: implementada na movimentação, e não como ação ao cair no Início, para refletir a regra de receber salário ao completar uma volta.
- CRUDs com ArrayList: fornecem simplicidade e eficiência suficientes para o número limitado de entidades (até 6 jogadores e 40 imóveis), além de interface direta com os menus de console.
- Cálculo de patrimônio: saldo + soma dos preços de compra dos imóveis, suficiente para compor o ranking final nesta etapa.

Limitações e próximos passos
- Não há Baralho de Sorte/Revés nem lógica de Prisão nesta versão. As entradas no enum e métodos placeholder foram deixados como base para futura expansão.
- Não há persistência em disco; todos os dados são voláteis enquanto o programa executa.
- Não há automação para decisão de compra de imóveis; as escolhas são do usuário via console.
- Próximas melhorias:
  - Implementar Baralho (Pilha de cartas) com embaralhamento e reciclagem ao esgotar.
  - Implementar a casa Prisão com as regras de saída por dados duplos e limite de tentativas.
  - Adicionar relatório detalhado de transações e um modo de simulação automática para testes.

Créditos
- Disciplina: Estrutura de Dados (ADS)
- Projeto acadêmico baseado nas especificações fornecidas pelo professor.