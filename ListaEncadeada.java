import java.util.Scanner;

public class ListaEncadeada {
    private Node primeiro;
    private int tamanho;
    
    public class Node {
        public int info;
        public Node next;
        
        public Node(int info) {
            this.info = info;
            this.next = null;
        }
        
        public String toString() {
            return "Node{" + "info=" + info + '}';
        }
    }
    
    public ListaEncadeada() {
        this.primeiro = null;
        this.tamanho = 0;
    }
    
    public boolean vazia() {
        return primeiro == null;
    }
    
    public void inserePrimeiro(int info) {
        Node novoNode = new Node(info);
        novoNode.next = primeiro;
        primeiro = novoNode;
        tamanho++;
        System.out.println("Elemento " + info + " inserido no início.");
    }
    
    public void insereDepois(Node node, int info) {
        if (node == null) {
            System.out.println("Nó não pode ser nulo!");
            return;
        }
        
        Node novoNode = new Node(info);
        novoNode.next = node.next;
        node.next = novoNode;
        tamanho++;
        System.out.println("Elemento " + info + " inserido após o nó " + node.info + ".");
    }
    
    public void insereUltimo(int info) {
        Node novoNode = new Node(info);
        
        if (vazia()) {
            primeiro = novoNode;
        } else {
            Node atual = primeiro;
            while (atual.next != null) {
                atual = atual.next;
            }
            atual.next = novoNode;
        }
        tamanho++;
        System.out.println("Elemento " + info + " inserido no final.");
    }
    
    public void insereOrdenado(int info) {
        Node novoNode = new Node(info);
        
        if (vazia() || info < primeiro.info) {
            novoNode.next = primeiro;
            primeiro = novoNode;
            tamanho++;
            System.out.println("Elemento " + info + " inserido em ordem.");
            return;
        }
        
        Node atual = primeiro;
        Node anterior = null;
        
        while (atual != null && info >= atual.info) {
            anterior = atual;
            atual = atual.next;
        }
        
        novoNode.next = atual;
        anterior.next = novoNode;
        tamanho++;
        System.out.println("Elemento " + info + " inserido em ordem.");
    }
    
    public void imprime() {
        if (vazia()) {
            System.out.println("Lista vazia!");
            return;
        }
        
        Node atual = primeiro;
        System.out.print("Lista: ");
        int indice = 0;
        while (atual != null) {
            System.out.print("[" + indice + "]=" + atual.info + " → ");
            atual = atual.next;
            indice++;
        }
        System.out.println("null");
    }
    
    public Node removePrimeiro() {
        if (vazia()) {
            System.out.println("Lista vazia - não há elementos para remover!");
            return null;
        }
        
        Node removido = primeiro;
        primeiro = primeiro.next;
        removido.next = null;
        tamanho--;
        System.out.println("Primeiro elemento (" + removido.info + ") removido.");
        return removido;
    }
    
    public Node removeUltimo() {
        if (vazia()) {
            System.out.println("Lista vazia - não há elementos para remover!");
            return null;
        }
        
        if (primeiro.next == null) {
            Node removido = primeiro;
            primeiro = null;
            tamanho--;
            System.out.println("Último elemento (" + removido.info + ") removido.");
            return removido;
        }
        
        Node atual = primeiro;
        Node anterior = null;
        
        while (atual.next != null) {
            anterior = atual;
            atual = atual.next;
        }
        
        Node removido = atual;
        anterior.next = null;
        tamanho--;
        System.out.println("Último elemento (" + removido.info + ") removido.");
        return removido;
    }
    
    public Node remove(Node no) {
        if (vazia()) {
            System.out.println("Lista vazia - não há elementos para remover!");
            return null;
        }
        
        if (no == null) {
            System.out.println("Nó não pode ser nulo!");
            return null;
        }
        
        if (primeiro == no) {
            return removePrimeiro();
        }
        
        Node atual = primeiro;
        Node anterior = null;
        
        while (atual != null && atual != no) {
            anterior = atual;
            atual = atual.next;
        }
        
        if (atual == null) {
            System.out.println("Nó não encontrado na lista!");
            return null;
        }
        
        Node removido = atual;
        anterior.next = atual.next;
        removido.next = null;
        tamanho--;
        System.out.println("Elemento (" + removido.info + ") removido.");
        return removido;
    }
    
    public Node buscarPorIndice(int indice) {
        if (indice < 0 || indice >= tamanho) {
            return null;
        }
        
        Node atual = primeiro;
        int contador = 0;
        
        while (contador < indice) {
            atual = atual.next;
            contador++;
        }
        
        return atual;
    }
    
    public int getTamanho() {
        return tamanho;
    }
    
    public static void exibirMenu() {
        System.out.println("\n=== MENU LISTA ENCADEADA ===");
        System.out.println("1. Inserir elemento no início");
        System.out.println("2. Inserir elemento no final");
        System.out.println("3. Inserir elemento após um nó (por índice)");
        System.out.println("4. Inserir elemento em ordem");
        System.out.println("5. Remover primeiro elemento");
        System.out.println("6. Remover último elemento");
        System.out.println("7. Remover nó específico (por índice)");
        System.out.println("8. Verificar se a lista está vazia");
        System.out.println("9. Imprimir lista");
        System.out.println("10. Mostrar tamanho da lista");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();
        Scanner scanner = new Scanner(System.in);
        int opcao, valor, indice;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido no início: ");
                    valor = scanner.nextInt();
                    lista.inserePrimeiro(valor);
                    break;
                    
                case 2:
                    System.out.print("Digite o valor a ser inserido no final: ");
                    valor = scanner.nextInt();
                    lista.insereUltimo(valor);
                    break;
                    
                case 3:
                    System.out.print("Digite o índice do nó de referência: ");
                    indice = scanner.nextInt();
                    Node nodeRef = lista.buscarPorIndice(indice);
                    if (nodeRef != null) {
                        System.out.print("Digite o valor a ser inserido: ");
                        valor = scanner.nextInt();
                        lista.insereDepois(nodeRef, valor);
                    } else {
                        System.out.println("Índice inválido!");
                    }
                    break;
                    
                case 4:
                    System.out.print("Digite o valor a ser inserido em ordem: ");
                    valor = scanner.nextInt();
                    lista.insereOrdenado(valor);
                    break;
                    
                case 5:
                    lista.removePrimeiro();
                    break;
                    
                case 6:
                    lista.removeUltimo();
                    break;
                    
                case 7:
                    System.out.print("Digite o índice do nó a ser removido: ");
                    indice = scanner.nextInt();
                    Node nodeRemover = lista.buscarPorIndice(indice);
                    if (nodeRemover != null) {
                        lista.remove(nodeRemover);
                    } else {
                        System.out.println("Índice inválido!");
                    }
                    break;
                    
                case 8:
                    System.out.println("Lista está vazia? " + lista.vazia());
                    break;
                    
                case 9:
                    lista.imprime();
                    break;
                    
                case 10:
                    System.out.println("Tamanho da lista: " + lista.getTamanho());
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
        
        scanner.close();
    }
}