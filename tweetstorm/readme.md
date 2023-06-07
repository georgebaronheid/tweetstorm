# Desafio Fliper

Criar um programa que receba um texto de tamanho arbitrário e criar uma  tweetstorm.

# Executando a aplicação:

Esta é uma aplicação Kotlin/JVM compilada para um .jar. Junto com o projeto disponibilizei o jar pronto que deve ser
utilizado com o seguinte comando:

`java -jar tweetstorm.jar "sua mensagem"`

# Compilando a aplicação:

Por conta da utilização de kotlin é necessária a utilização da ferramenta `kotlinc` da seguinte maneira:

`kotlinc Main.kt services/Splitter.kt -include-runtime -d tweetstorm.jar`

Isto irá gerar o mesmo .jar que disponibilizei em conjunto com o projeto.

# Minha solução:

Analisando o caso de uso julguei que seria mais interessante ao usuário possuir um limite de caracteres da mensagem em
troca de o resultado manter a coerência semântica da mensagem ao evitar que os tweets fossem finalizados e inicializados
quebrando palavras.

A solução necessária para respeitar o limite das palavras, o que por consequência faz o tamanho do tweet variar dentro do
range de 140 caracteres, header incluso, levou uma boa parte das aproximadamente duas horas necessárias para o desenvolvimento.
Isto resultou em um tamanho consideravelmente grande de mensagem aceita - o equivalente a um pouco mais de uma
página de um arquivo Word - que mantém a coerência no início e fim dos tweets.

# Melhorias desejadas:

A complexidade da solução se atentando a quebra de palavras levou uma boa parte do tempo de desenvolvimento. Gostaria de no
futuro implementar uma solução que aceitasse uma quantidade maior de caracteres e em última instância abrir mão desse limite,
bem como implementar alguma biblioteca ou solução que realizasse uma análise semântica mais minuciosa e de maneira mais inteligente
para definir os momentos corretos de quebra de mensagem.

Seria interessante também realizar um refactoring mais atencioso permitindo o uso de métodos públicos com menor responsabilidade
unitária. Isso me permitira fazer uso completo das funcionalidades do JUnit5, facilitar manutenção e implementação de novas
features bem como tratar casos de exceção decorrentes
do uso não previsto da ferramenta no MVP, mesmo que sejam casos incomuns como por exemplo mensagens contínuas - não contendo
espaço algum.
