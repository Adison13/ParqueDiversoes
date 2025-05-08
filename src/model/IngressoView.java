package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IngressoView {
    private final SimpleIntegerProperty id;
    private final StringProperty cliente;
    private final StringProperty bilheteria;
    private final StringProperty pagamento;

    public IngressoView(int id, String cliente, String bilheteria, String pagamento) {
        this.id = new SimpleIntegerProperty(id);
        this.cliente = new SimpleStringProperty(cliente);
        this.bilheteria = new SimpleStringProperty(bilheteria);
        this.pagamento = new SimpleStringProperty(pagamento);
    }

    public int getId() { return id.get(); }
    public String getCliente() { return cliente.get(); }
    public String getBilheteria() { return bilheteria.get(); }
    public String getPagamento() { return pagamento.get(); }

    public SimpleIntegerProperty idProperty() { return id; }
    public StringProperty clienteProperty() { return cliente; }
    public StringProperty bilheteriaProperty() { return bilheteria; }
    public StringProperty pagamentoProperty() { return pagamento; }
}





