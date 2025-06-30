# 🧪 Sistema de Gerenciamento de Exames - IF Diagnósticos

Este projeto simula um sistema para gerenciamento de exames médicos e emissão de laudos, com foco em flexibilidade, extensibilidade e boas práticas de design orientado a objetos. A solução foi modelada com base nos requisitos propostos pela empresa ST Software, utilizando padrões de projeto consagrados.

---

### 📌 Diagrama de Classes
O diagrama a seguir representa os principais componentes da solução, estruturados com múltiplos padrões de projeto:

*(Local para inserir o diagrama de classes)*

---

### 🎯 Requisitos e Padrões Aplicados

✅ **R1 – Carregar dados de arquivos CSV**
* **🔧 Padrão: Template Method**
    * `DataLoader` define o algoritmo genérico.
    * `CSVDataLoader` implementa a leitura real dos dados.
    * Permite adicionar novos formatos no futuro (JSON, XML etc.) sem alterar o processo geral.

✅ **R2 – Gerar número sequencial sem repetição**
* **🔧 Padrão: Singleton**
    * `Id` controla a geração de identificadores únicos.
    * Garante sequência centralizada e não repetitiva de IDs.
    * **Exemplo de uso:**
        ```java
        int id = Id.getInstancia().gerarId();
        ```

✅ **R3 – Emitir diferentes tipos de exames**
* **🔧 Padrão: Factory Method**
    * A classe abstrata `ExameFactory` define a criação de exames.
    * Subclasses como `HemogramaFactory`, `UltrassonografiaFactory` e `RessonanciaMagneticaFactory` encapsulam a criação específica.
    * Permite adicionar novos tipos sem modificar o código existente.

✅ **R4 – Gerar laudos em diferentes formatos**
* **🔧 Padrão: Bridge**
    * `Laudo` utiliza a interface `GeradorDeLaudo`.
    * Implementações como `TextoFormatter`, `HTMLFormatter` e `PDFFormatter` definem a forma de saída.
    * Laudo e formato são desacoplados.

✅ **R5 – Regras de validação extensíveis**
* **🔧 Padrão: Chain of Responsibility**
    * A interface `Validador` permite encadear múltiplas regras.
    * Ex: `HemoglobinaValidacao`, `ImplantesGeraisValidacao` verificam diferentes aspectos do exame.
    * Permite adicionar/remover validações sem impactar a cadeia.

✅ **R6 – Notificar o paciente ao emitir o laudo**
* **🔧 Padrão: Observer**
    * A interface `Notificador` define o comportamento genérico.
    * Implementações como `WhatsAppNotificador`, `EmailNotificador`, etc.
    * `GerenciadorDeNotificacoes` gerencia os canais de notificação (observadores).

✅ **R7 – Aplicar descontos conforme regras**
* **🔧 Padrão: Chain of Responsibility**
    * `Desconto` representa o nó da cadeia.
    * Subclasses como `ConvenioDesconto` e `IdosoDesconto` aplicam regras específicas.
    * Encadeamento permite múltiplos descontos acumulativos ou condicionais.

✅ **R8 – Priorização de exames com fila**
* **🔧 Implementação: `PriorityQueue` com `Enum Prioridade`**
    * O `Enum Prioridade` define `URGENTE`, `POUCO_URGENTE`, `ROTINA`.
    * A classe `Sistema` usa uma `PriorityQueue<Exame>`, ordenada por prioridade.
    * Permite inserção e processamento automático conforme a gravidade do exame.

---

### 🧱 Componentes Principais

| Classe/Interface | Responsabilidade |
| :--- | :--- |
| **Exame** | Classe base para todos os exames. |
| **ExameFactory** | Criação dinâmica de exames. |
| **Laudo** | Representa o laudo de um exame. |
| **GeradorDeLaudo** | Interface para gerar laudos em diferentes formatos. |
| **TextoFormatter, etc.** | Implementações específicas do formato de laudo. |
| **Validador** | Interface para regras de validação. |
| **Desconto** | Interface para descontos dinâmicos. |
| **Notificador** | Interface para canais de notificação. |
| **Sistema** | Classe central que orquestra o funcionamento do sistema. |
