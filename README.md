
#  Sistema de Biblioteca

> **ADS_JULIALAITHARTH_BIBLIOTECA**  
> Aplicação web Jakarta EE para gerenciamento de biblioteca, desenvolvida como projeto da disciplina de Análise e Desenvolvimento de Sistemas — UPF.

##  Sobre o projeto

Sistema web para gerenciamento de uma biblioteca, permitindo o controle de **autores**, **livros**, **usuários** e **empréstimos**, com área administrativa protegida por autenticação via filtro.

##  Tecnologias utilizadas

- **Java 21 (LTS)**
- **Jakarta EE 10**
- **JSF (Jakarta Faces)** com **PrimeFaces 13.0.6**
- **Tema Manhattan 7.0.0** (layout administrativo)
- **JPA** (persistência via unit `BibliotecaPU`, transações **JTA**)
- **Jakarta REST (JAX-RS)**
- **Maven** (empacotamento `.war`)
- **GlassFish / Payara** (servidor de aplicação)
- Suporte a **tema escuro** (`dark-theme.css`)

##  Funcionalidades

1. **Gerenciar Autores** — CRUD de autores
2. **Gerenciar Livros** — controle do acervo
3. **Gerenciar Usuários** — cadastro dos usuários da biblioteca
4. **Gerenciar Empréstimos** — registro e controle de empréstimos
5. **Login / Autenticação** — acesso à área administrativa protegido por `FiltroAdministrativo`

##  Arquitetura

Arquitetura em camadas:

```
br.upf.biblioteca
├── controller   # Controllers JSF + Converters
├── entity       # Entidades JPA
├── facade       # Camada de acesso a dados (AbstractFacade + Facades)
└── filter       # FiltroAdministrativo (controle de acesso)

upf.br.biblioteca
├── JakartaRestConfiguration   # Configuração JAX-RS
└── resources                  # Recursos REST
```

##  Estrutura do projeto

```
biblioteca/
├── pom.xml
└── src/main/
    ├── java/br/upf/biblioteca/
    │   ├── controller/   # Autor, Livro, Emprestimo, UsuarioBiblioteca, Login + Converters
    │   ├── entity/       # AutorEntity, LivroEntity, UsuarioBibliotecaEntity, EmprestimoEntity
    │   ├── facade/       # AbstractFacade + Facades
    │   └── filter/       # FiltroAdministrativo
    ├── resources/META-INF/
    │   └── persistence.xml
    └── webapp/
        ├── admin/        # autor.xhtml, livro.xhtml, emprestimo.xhtml, usuariobiblioteca.xhtml
        ├── login.xhtml
        ├── index.html
        └── WEB-INF/      # web.xml, beans.xml, glassfish-web.xml, glassfish-resources.xml, template.xhtml
```

##  Pré-requisitos

- **JDK 21**
- **Maven 3.x**
- **GlassFish 7** ou **Payara 6** (compatível com Jakarta EE 10)
- Banco de dados configurado no DataSource JNDI **`JNDI_DBBIBLIOTECA`**

##  Como executar

```bash
# 1. Clone o repositório
git clone https://github.com/JuliaLaitharth/ADS_JULIALAITHARTH_BIBLIOTECA.git
cd ADS_JULIALAITHARTH_BIBLIOTECA/biblioteca

# 2. Gere o pacote .war
mvn clean package

# 3. Faça o deploy do arquivo target/biblioteca-1.0-SNAPSHOT.war no GlassFish/Payara
```

>  Configure o **DataSource JNDI** `java:app/JNDI_DBBIBLIOTECA` no servidor.  
> O schema do banco é gerado automaticamente (`schema-generation.database.action = create`).

Após o deploy, acesse:

```
http://localhost:8080/biblioteca/
```

Desenvolvido por **Júlia Laitharth**  
Análise e Desenvolvimento de Sistemas — UPF

