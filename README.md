# 🧪 Sistema de Gerenciamento de Exames - ST Diagnósticos

Este projeto implementa um sistema para gerenciamento de exames médicos e emissão de laudos para a **IF Diagnósticos**, com foco em flexibilidade, extensibilidade e reutilização, conforme os requisitos propostos pela **ST Software**. A solução foi modelada utilizando múltiplos padrões de projeto para garantir um design robusto e de fácil manutenção.

---

### 📌 Diagrama de Classes
O diagrama a seguir representa a arquitetura completa da solução, orquestrada pela `SistemaDiagnosticosFacade` e demonstrando como os padrões de projeto se conectam para resolver os requisitos do sistema.

<img width="4151" height="2632" alt="projeto_pps drawio (2)" src="https://github.com/user-attachments/assets/8b431166-c5a6-4dea-90dc-8808b94fa84e" />

---

### 🎯 Requisitos e Padrões Aplicados

✅ **R1 – Carregar dados de arquivos CSV**
* **🔧 Padrão: Strategy**
    * `CSVDataLoader<T>` é o contexto que executa a estratégia de carregamento.
    * A interface `CSVMappingStrategy<T>` define o contrato para mapear um registro CSV para um objeto.
    * `PacienteMappingStrategy` e `MedicoMappingStrategy` são as estratégias concretas, permitindo que o `CSVDataLoader` carregue diferentes tipos de dados sem alterar sua estrutura.

✅ **R2 – Gerar número sequencial sem repetição**
* **🔧 Padrão: Singleton**
    * A classe `IdGenerator` garante que apenas uma instância seja responsável por gerar todos os IDs do sistema.
    * Isso assegura uma sequência centralizada e não repetitiva de identificadores para os exames.

✅ **R3 – Emitir laudo para diferentes tipos de exames (Sanguíneo, Raio-X, Ressonância)** **e R4 – Gerar laudos em diferentes formatos (TXT, HTML, PDF)**
* **🔧 Padrão: Visitor**
    * Este padrão permite adicionar novas operações (formatos de laudo) a uma hierarquia de classes (`Exame`) sem modificá-las.
    * A classe abstrata `VisitorFormatter` define a interface para os "visitantes", com um método para cada tipo de exame (`gerarLaudo(Sanguineo)`, `gerarLaudo(RaioX)`, etc.).
    * As classes `TXTFormatterVisitor`, `HTMLFormatterVisitor` e `PDFFormatterVisitor` são os visitantes concretos, cada uma encapsulando a lógica para gerar o laudo em um formato específico.
    * As classes de `Exame` aceitam um `Visitor` e o direcionam para o método correto, desacoplando a estrutura do exame da sua representação.

* **🔧 Padrão Adicional: Composite**
    * Para atender à complexidade do exame sanguíneo, o padrão **Composite** foi usado.
    * `ItemSanguineo` é o componente base.
    * `Indicador` (a "folha") representa um resultado individual (ex: Glicose).
    * `GrupoIndicadores` (o "composto") agrupa múltiplos `ItemSanguineo`, permitindo a criação de estruturas hierárquicas como um hemograma completo.

✅ **R5 – Regras de validação extensíveis**
* **🔧 Padrão: Chain of Responsibility**
    * As hierarquias `ValidadorRaioxHandler` e `ValidadorRessonanciaHandler` definem a interface para os nós da cadeia de validação.
    * Validadores concretos (`AssinaturaValidador`, `ImplantesValidador`, etc.) implementam regras específicas.
    * Cada validador processa o exame e, opcionalmente, passa a responsabilidade para o próximo na cadeia, permitindo adicionar ou reordenar validações sem alterar o código cliente.

✅ **R6 – Notificar o paciente ao emitir o laudo**
* **🔧 Padrão: Decorator**
    * A interface `INotificador` define o contrato comum para todas as formas de notificação.
    * `NotificadorBase` é o decorador abstrato que encapsula outro `INotificador`.
    * `NotificadorSistema`, `NotificadorEmail` e `NotificadorTelegram` são os decoradores concretos. Eles "envelopam" um notificador base (ou outro decorador), adicionando novas funcionalidades (canais de notificação) em tempo de execução. Isso permite combinar notificações de forma flexível (ex: enviar por E-mail e Telegram ao mesmo tempo).

✅ **R7 – Aplicar descontos conforme regras**
* **🔧 Padrão: Chain of Responsibility**
    * `DescontosBaseHandler` é o handler abstrato da cadeia.
    * Subclasses como `DescontoConvenioHandler` (15%) e `DescontoIdososHandler` (8%) aplicam regras de desconto específicas.
    * O encadeamento permite que múltiplos descontos sejam aplicados de forma acumulativa e extensível a novas regras (ex: campanhas sazonais).

✅ **R8 – Priorização de exames com fila**
* **🔧 Implementação: Estratégia de Fila Personalizada**
    * A classe `FilaExames` encapsula a lógica de uma fila de prioridade.
    * Utiliza um `Enum Prioridade` (`URGENTE`, `POUCO_URGENTE`, `ROTINA`) para determinar a posição de inserção de um novo exame na fila, seguindo as regras de negócio especificadas.

✅ **R10 – Contabilizar a geração de novos laudos em tempo real**
* **🔧 Padrão: Observer**
    **Funcionalidade Adicional:** Foi implementado um sistema de contabilidade que monitora a geração de laudos em tempo real. [cite: 182]
    * O padrão **Observer** foi utilizado para gerar relatórios do processo de geração de laudos (`ServicoExame`, o "Subject") notifica o (`EstatisticaLaudosObserver`, o "Observer") quando um novo laudo é emitido.
    * Quando `ServicoExame` gera um novo laudo, ele notifica todos os observadores inscritos, que então atualizam suas estatísticas. Isso permite adicionar novos "ouvintes" (como um sistema de log ou faturamento) no futuro sem alterar a lógica principal do serviço de exames.
---

### 🧱 Componentes Principais

| Classe/Interface             | Responsabilidade                                                                                                | Padrão(ões) Associado(s)                                   |
| :--------------------------- | :-------------------------------------------------------------------------------------------------------------- | :--------------------------------------------------------- |
| **`SistemaDiagnosticosFacade`** | Ponto de entrada único e simplificado para todas as operações do sistema.                                       | **Facade** |
| **`ServicoExame`** | Orquestra o ciclo de vida dos exames: criação, validação e geração de laudos.                                   | Facade, Factory (implícito)                                |
| **`ServicoFinanceiro`** | Orquestra a aplicação de descontos.                                                                             | Facade                                                     |
| **`ServicoNotificacao`** | Orquestra o envio de notificações por múltiplos canais.                                                         | Facade                                                     |
| **`Exame`** (e subclasses)       | Classe base para os diferentes tipos de exames (Sanguíneo, RaioX, etc.).                                        | Visitor (Element), Composite (Client)                      |
| **`ItemSanguineo`** (e subs)     | Define a estrutura hierárquica para os resultados de exames de sangue.                                          | **Composite** |
| **`VisitorFormatter`** (e subs)  | Encapsula os algoritmos para gerar laudos em diferentes formatos (PDF, HTML, TXT).                              | **Visitor** |
| **`INotificador`** (e subs)      | Define a interface para notificações e permite adicionar canais dinamicamente.                                  | **Decorator** |
| **`DescontosBaseHandler`** (e subs)| Encapsula as regras de desconto e permite seu encadeamento.                                                   | **Chain of Responsibility** |
| **`Validador...Handler`** (e subs)| Encapsula as regras de validação dos exames e permite seu encadeamento.                                       | **Chain of Responsibility** |
| **`CSVDataLoader`** | Carrega dados de arquivos CSV.                                                                                  | **Strategy** (Context)                                     |
| **`CSVMappingStrategy`** | Define a interface para as estratégias de mapeamento de dados.                                                  | **Strategy** (Interface)                                   |
| **`IdGenerator`** | Garante a geração de IDs únicos para os exames.                                                                 | **Singleton** |
| **`FilaExames`** | Gerencia a fila de exames com base nas regras de prioridade.                                                    | -                                                          |
| **`EstatisticaLaudosObserver`** | Observar quando novos laudos são emitidos para gerar relatórios                                   | Observer                                                     |
| **`MainApp`** | Classe que simula a aplicação                                   | - |
