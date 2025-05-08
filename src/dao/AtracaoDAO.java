package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Atracao;
import dao.ConnectionFactory;

public class AtracaoDAO {

    public List<Atracao> buscarTodas() {
        List<Atracao> lista = new ArrayList<>();

        String sql = "SELECT id, nome, descricao, horario, capacidade FROM atracao";

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Atracao atracao = new Atracao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("horario"),
                        rs.getInt("capacidade")
                );
                lista.add(atracao);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean registrarParticipacao(int idCliente, int idAtracao) {
        String verificarSql = """
            SELECT i.id
            FROM ingresso i
            LEFT JOIN atracao_cliente ac ON i.id = ac.id_ingresso
            WHERE i.id_cliente = ? AND ac.id IS NULL
            LIMIT 1
        """;

        String inserirSql = """
            INSERT INTO atracao_cliente (id_atracao, id_ingresso, horario_uso)
            VALUES (?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement verificarStmt = conn.prepareStatement(verificarSql)) {

            verificarStmt.setInt(1, idCliente);
            ResultSet rs = verificarStmt.executeQuery();

            if (rs.next()) {
                int idIngresso = rs.getInt("id");

                try (PreparedStatement inserirStmt = conn.prepareStatement(inserirSql)) {
                    inserirStmt.setInt(1, idAtracao);
                    inserirStmt.setInt(2, idIngresso);
                    inserirStmt.executeUpdate();
                    System.out.println("✔ Participação registrada!");
                    return true;
                }

            } else {
                System.out.println("✘ Cliente não possui ingresso válido ou já utilizou.");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}








