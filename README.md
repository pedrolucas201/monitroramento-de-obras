Aplicação de Monitoramento de Obras

Introdução

> A Aplicação de Monitoramento de Obras foi desenvolvida com o objetivo de facilitar o gerenciamento e acompanhamento de obras, permitindo o controle de dados das construções, engenheiros responsáveis e suas tarefas. O projeto oferece funcionalidades CRUD (Criar, Ler, Atualizar, Deletar) tanto para as obras quanto para os engenheiros e tarefas associadas, e utiliza a interface gráfica para tornar o uso mais intuitivo.

Estrutura do Projeto

Entidades Principais:

> Obra: Representa uma construção geral com atributos como nome, localização, engenheiro responsável e lista de tarefas.

> Engenheiro: Responsável pela obra, com atributos como nome, registro e experiência.

> Tarefa: Cada obra pode ter uma lista de tarefas, que são atividades relacionadas à construção.

Herança:

> Obra Residencial: Herda da classe Obra e representa construções de residências.

> Obra Comercial: Herda da classe Obra e representa construções comerciais.

Interfaces:

> Repositório de Obras: Interface responsável pela manipulação de dados das obras.

> Serviço de Planejamento: Interface responsável pela lógica de planejamento e controle de tarefas e engenheiros.

Implementação do CRUD:

> O projeto implementa um CRUD completo, permitindo criar, visualizar, atualizar e deletar informações de obras, engenheiros e tarefas.

> A interface gráfica foi desenvolvida utilizando Swing, onde o usuário pode interagir diretamente com as entidades, adicionando obras, listando todas as obras em uma tabela, selecionando itens para edição e removendo-os da lista.

Funcionalidades

> Adicionar Obras: O usuário pode inserir informações sobre novas obras, escolhendo entre obras residenciais ou comerciais, e vinculando engenheiros a essas obras.

> Listar Obras: As obras adicionadas são exibidas em uma tabela na interface gráfica, tornando a visualização e o gerenciamento mais acessíveis.

> Atualizar Obras: Ao selecionar uma obra na tabela, os campos de edição são preenchidos, permitindo modificar os dados da obra ou do engenheiro responsável.

> Deletar Obras: O usuário pode remover uma obra da lista, clicando no botão de exclusão.

> Gerenciamento de Tarefas: Cada obra tem uma lista de tarefas que pode ser gerenciada de forma independente.

Considerações Finais

> O projeto de Monitoramento de Obras entrega uma solução completa para o gerenciamento de obras, engenheiros e tarefas. Com a interface gráfica intuitiva e as funcionalidades CRUD, os usuários podem facilmente adicionar, visualizar e atualizar os dados relacionados a suas construções. A utilização de herança para as obras residenciais e comerciais permite escalabilidade, possibilitando a adição de novos tipos de obras no futuro.