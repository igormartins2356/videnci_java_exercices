# videnci_java_exercices
**Lista de exercícios feitos em java**

  Exercício 2:

**Diferença entre usar o StringBuffer, StringBuilder.**

  Ambos são bem mais rápidos para concatenação de valores do que a String comum e fazem exatamente a mesma função. A principal diferença é que o StringBuffer é sincronizado, enquanto que o outro não. Assim, você garante a consistência do seu código quando há diversas threads lendo ou modificando a mesma String.

**Diferença entre "==" e ".equals".**

  1. o "==" é um operador em java e .equals é uma função ou metodo.
  2. o "==" geralmente é usado para comparar valores e .equals para comparar o conteúdo real de um objeto.

  Pelo que entendi o certo seria
  comparação pelo "==" -> 1 == 1
  comparação pelo .equals -> batata1.equals(batata2)

**Diferença entre o int e o integer**

  Um int é um tipo de dados que armazena um inteiro complementar de dois assinados de 32 bits.
  Por outro lado, Integer é uma classe wrapper que envolve um tipo primitivo int em um objeto.
  int fornece menos flexibilidade em comparação com Integer, pois permite apenas o valor binário de um inteiro nele.
  Integer, por outro lado, é mais flexível no armazenamento e manipulação de dados int. Como as classes Wrapper herdam a classe Object, elas podem ser usadas em coleções com referência a Object ou genéricas.
  Em java, não se pode atribuir um valor de string (contendo apenas um número inteiro) a uma variável int diretamente ou mesmo por conversão.
  No caso de Integer, podemos atribuir string a um objeto do tipo Integer usando o construtor Integer(String) ou até mesmo usar parseInt(String) para converter um literal String em um valor int.
