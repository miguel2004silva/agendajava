package agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InsereRegistro {
    private final String url = "jdbc:mysql://localhost:3306/adicionarcontato";
    private final String user = "root";
    private final String password = "";
    private Connection connection;
                private static ArrayList<Contatos> listaDeContatos = new ArrayList<>();


    public Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Conexão bem-sucedida ao banco de dados!");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao conectar-se ao banco de dados: " + e.getMessage());
        }
        return connection;
    }

    public void insertRecords(String nome, String email, String telefone) {
        connection = connect();

        try {
            String query = "INSERT INTO usuario (nome, email, telefone) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, telefone);
            preparedStatement.executeUpdate();

            System.out.println("Registro inserido com sucesso na tabela tbAgenda.");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao inserir registros: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }
 public void deleteRecord(String nome) {
        connection = connect();
    try {
        String query = "DELETE FROM usuario WHERE nome=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, nome);  // Usar o parâmetro nome fornecido
        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Registro deletado da tabela");
        } else {
            System.out.println("Nenhum registro foi deletado");
        }
    } catch (SQLException e) {
        System.out.println("Ocorreu um erro ao deletar o registro: " + e.getMessage());
    } finally {
        closeConnection();
    }
}
 public void mostrarRecord(){
     connection = connect();

    // Limpar a lista antes de adicionar novos contatos
    listaDeContatos.clear();

    try {
        String query = "SELECT nome, email, telefone FROM usuario";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        // StringBuilder para armazenar a mensagem final
        StringBuilder mensagem = new StringBuilder("Contatos Salvos:\n");

        // Iterar sobre os resultados do banco de dados
        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String email = resultSet.getString("email");
            String telefone = resultSet.getString("telefone");

            // Adicionar os dados do banco de dados à mensagem
            mensagem.append("Nome: ").append(nome)
                    .append("\nTelefone: ").append(telefone)
                    .append("\nE-mail: ").append(email)
                    .append("\n\n");

            // Criar um novo objeto Contatos e adicioná-lo à lista
            Contatos contato = new Contatos();
            contato.setNome(nome);
            contato.setEmail(email);
            contato.setTelefone(telefone);
            listaDeContatos.add(contato);
        }

        // Exibir a mensagem usando JOptionPane
        JOptionPane.showMessageDialog(null, mensagem.toString(), "Contatos Salvos", JOptionPane.PLAIN_MESSAGE);

    } catch (SQLException e) {
        // Exibir mensagem de erro usando JOptionPane
        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter contatos do banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        closeConnection();
    }
}

}
