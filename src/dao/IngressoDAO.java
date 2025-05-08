package dao;

import model.IngressoView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

    public static List<IngressoView> buscarPorCliente(int idCliente) {
        List<IngressoView> lista = new ArrayList<>();

        String sql = """
            SELECT i.id, c.nome AS cliente,
                   CONCAT('R$ ', TO_CHAR(b.preco, 'FM99990.00')) AS bilheteria,
                   i.pagamento
            FROM ingresso i
            JOIN cliente c ON i.id_cliente = c.id
            JOIN bilheteria b ON i.id_bilheteria = b.id
            WHERE c.id = ?
        """;

        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                IngressoView iv = new IngressoView(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("bilheteria"),
                        rs.getString("pagamento")
                );
                lista.add(iv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
















