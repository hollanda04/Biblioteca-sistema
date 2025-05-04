import java.util.ArrayList;
import java.util.Scanner;




 //INFORMAÇÕES A SEREM CADASTRADAS NO SISTEMA
class Livro {
    String titulo;
    String autor;
    String numero;
    boolean emprestado;
    String leitor;
    String dataEmprestimo;

            //CADASTRAR LIVRO, JUNTAMENTE DO AUTOR E UM NÚMERO

    Livro(String titulo, String autor, String numero) {
        this.titulo = titulo;
        this.autor = autor;
        this.numero = numero;
        this.emprestado = false;
    }

         // EMPRESTAR 

    void emprestar(String nomeLeitor, String data) {
        this.emprestado = true;
        this.leitor = nomeLeitor;
        this.dataEmprestimo = data;
    }

        // DEVOLVER

    void devolver() {
        this.emprestado = false;
        this.leitor = null;
        this.dataEmprestimo = null;
    }

    boolean isDisponivel() {
        return !emprestado;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", numero, titulo, autor,
                emprestado ? "Emprestado para " + leitor + " em " + dataEmprestimo : "Disponível");
    }
}

            // CRIAR A ARRAYLIST, 
            //O OBJETIVO É ATRIBUIR UMA FUNÇÃO DA APLICAÇÃO PARA CADA NÚMERO

    public class Biblioteca {
        static ArrayList<Livro> livros = new ArrayList<>();
        static Scanner lucaseLuiz = new Scanner(System.in);

        public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- Menu Biblioteca ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Emprestar Livro");
            System.out.println("3. Devolver Livro");
            System.out.println("4. Consultar Livro");
            System.out.println("5. Listar Livros Emprestados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = lucaseLuiz.nextInt();
            lucaseLuiz.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 : cadastrarLivro();
                break;
                case 2 : emprestarLivro();
                break;
                case 3 : devolverLivro();
                break;
                case 4 : consultarLivro();
                break;
                case 5 : listarEmprestados();
                break;
                case 0 : System.out.println("Saindo...");
                default : System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = lucaseLuiz.nextLine();
        System.out.print("Autor: ");
        String autor = lucaseLuiz.nextLine();
        System.out.print("Código: ");
        String codigo = lucaseLuiz.nextLine();

        livros.add(new Livro(titulo, autor, codigo));
        System.out.println("Livro cadastrado com sucesso!");
    }


    
            //ADICIONAR AGORA A FUNÇÃO DE EMPRESTAR LIVROS
            //E TAMBÉM A DE DEVOLVER E LIMPAR OS DADOS

    static void emprestarLivro() {
        System.out.print("Código do livro: ");
        String numero = lucaseLuiz.nextLine();
        Livro livro = buscarLivroPorNumero(numero);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else if (!livro.isDisponivel()) {
            System.out.println("Livro já está emprestado.");
        } else {
            System.out.print("Nome do leitor: ");
            String nome = lucaseLuiz.nextLine();
            System.out.print("Data do empréstimo (ex: 03/05/2025): ");
            String data = lucaseLuiz.nextLine();
            livro.emprestar(nome, data);
            System.out.println("Livro emprestado com sucesso!");
        }
    }


    static void devolverLivro() {
        System.out.print("Código do livro: ");
        String numero = lucaseLuiz.nextLine();
        Livro livro = buscarLivroPorNumero(numero);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
        } else if (livro.isDisponivel()) {
            System.out.println("Livro já está disponível.");
        } else {
            livro.devolver();
            System.out.println("O livro foi devolvido!");
        }
    }

            //CONSULTAR O LIVRO
                //CONSULTAR POR NÚMERO
                //CONSULTAR POR TÍTULO
    static void consultarLivro() {
        System.out.print("Buscar por (1) Número ou (2) Título: ");
        int tipo = lucaseLuiz.nextInt();
        lucaseLuiz.nextLine();

        Livro livro = null;
        if (tipo == 1) {
            System.out.print("Número: ");
            String numero = lucaseLuiz.nextLine();
            livro = buscarLivroPorNumero(numero);
        } else if (tipo == 2) {
            System.out.print("Título: ");
            String titulo = lucaseLuiz.nextLine();
            livro = buscarLivroPorTitulo(titulo);
        }

        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }


            // ECONTRAR LIVROS EMPRESTADOS
            
    static void listarEmprestados() {
        boolean encontrou = false;
        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                System.out.println(livro);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro emprestado no momento.");
        }
    }

    static Livro buscarLivroPorNumero(String numero) {
        for (Livro livro : livros) {
            if (livro.numero.equalsIgnoreCase(numero)) {
                return livro;
            }
        }
        return null;
    }

    static Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.titulo.equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }
}
