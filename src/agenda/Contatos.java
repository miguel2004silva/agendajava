
package agenda;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Contatos {
   
            public static ArrayList<Contatos> listaDeContatos = new ArrayList<>();

    private String nome;
    private String email;
    private String telefone;
    static int quantidadeContatos = 1;

public Contatos(){
    
}

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
             if (nome.matches("[a-zA-Z ]+")){
            this.nome = nome;
        } else {
           JOptionPane.showMessageDialog(null,"Nome com caractere inválido");
        }
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            this.email = email;
        } else  {
            JOptionPane.showMessageDialog(null,"Email com caractere inválido");
        }
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
 public void setTelefone(String telefone) {
        if (telefone.matches("\\d+")) {
            this.telefone = telefone;
        } else {
            JOptionPane.showMessageDialog(null,"Telefone com caractere inválido");
        }
    }

        public static ArrayList<Contatos> getListaDeContatos() {
        return listaDeContatos;
    }
       
         public void salvarContato() {
       // Verifica se todos os campos estão preenchidos corretamente
        if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty() && telefone != null && !telefone.isEmpty()) {
            // Se todos os campos estiverem corretos, exibe a mensagem de sucesso
            String mensagem = "Contato salvo com sucesso, número de contatos salvos: "+quantidadeContatos;
            JOptionPane.showMessageDialog(null, mensagem);
            // Lógica para salvar o contato
            quantidadeContatos++;
            System.out.println("Contato salvo com sucesso!");
                    listaDeContatos.add(this);

        } else {
            // Se houver algum campo incorreto, exibe a mensagem de erro
            JOptionPane.showMessageDialog(null, "Contato não foi salvo. Preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
           public static void removerContato(String nomeContato) {
     // Itera sobre a lista de contatos procurando pelo nome do contato
    for (Contatos contato : listaDeContatos) {
        if (contato.getNome().equals(nomeContato)) {
            // Remove o contato da lista e do banco de dados
            listaDeContatos.remove(contato);

            InsereRegistro insereRegistro = new InsereRegistro();
            insereRegistro.deleteRecord(contato.getNome());

            // Exibe mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Contato removido com sucesso.", "Remover Contato", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    // Se o contato não foi encontrado, exibe mensagem de aviso
    JOptionPane.showMessageDialog(null, "Contato não encontrado.", "Remover Contato", JOptionPane.WARNING_MESSAGE);
}

    public static String mostrarContatosParaRemover() {
        // Verifica se há contatos antes de exibir
        if (listaDeContatos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum contato salvo.", "Contatos Salvos", JOptionPane.INFORMATION_MESSAGE);
            return null;
        } else {
            // Converte a lista de contatos em um array de strings para exibição
            String[] opcoes = new String[listaDeContatos.size()];
            for (int i = 0; i < opcoes.length; i++) {
                Contatos contato = listaDeContatos.get(i);
                opcoes[i] = contato.getNome();  // Mostra apenas os nomes
            }

            // Exibe um diálogo de escolha para o usuário selecionar o contato a ser removido
            return (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione um contato para remover:",
                    "Remover Contato",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]  // Contato selecionado por padrão 
            );
        }
    }

            public static void mostrarContatos() {
        // Verifica se há contatos antes de exibir
        if (listaDeContatos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum contato salvo.", "Contatos Salvos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Constrói a mensagem com os contatos
            StringBuilder mensagem = new StringBuilder("Contatos Salvos:\n");

            for (Contatos contato : listaDeContatos) {
                mensagem.append("Nome: ").append(contato.getNome())
                        .append("\nTelefone: ").append(contato.getTelefone())
                        .append("\nE-mail: ").append(contato.getEmail())
                        .append("\n\n");
            }

            JOptionPane.showMessageDialog(null, mensagem.toString(), "Contatos Salvos", JOptionPane.PLAIN_MESSAGE);
        }
    }

         }
