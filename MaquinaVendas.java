import java.util.HashMap;
import java.util.Map;

interface Slot {
    boolean aceitaMoeda(int valor);
}

class SlotUmCentavo implements Slot {
    public boolean aceitaMoeda(int valor) {
        return valor == 1;
    }
}

class SlotCincoCentavos implements Slot {
    public boolean aceitaMoeda(int valor) {
        return valor == 5;
    }
}

class SlotDezCentavos implements Slot {
    public boolean aceitaMoeda(int valor) {
        return valor == 10;
    }
}

class MaquinaVendas {
    private Map<Integer, Slot> slots;
    private int total;

    public MaquinaVendas() {
        slots = new HashMap<>();
        slots.put(1, new SlotUmCentavo());
        slots.put(5, new SlotCincoCentavos());
        slots.put(10, new SlotDezCentavos());
        total = 0;
    }

    public void inserirMoeda(int valor) {
        Slot slot = slots.get(valor);
        if (slot != null && slot.aceitaMoeda(valor)) {
            total += valor;
        } else {
            System.out.println("Moeda não aceita");
        }
    }

    public void comprarProduto(int valorProduto) {
        if (total >= valorProduto) {
            total -= valorProduto;
            System.out.println("Produto comprado. Seu troco eh: " + total + "centavos.");
            total = 0;
        } else {
            System.out.println("Valor insuficiente para comprar o produto");
        }
    }

    public static void main(String[] args) {
        MaquinaVendas maquina = new MaquinaVendas();

        // Inserir moedas
        maquina.inserirMoeda(1);
        maquina.inserirMoeda(5);
        maquina.inserirMoeda(10);

        // Comprar produto
        maquina.comprarProduto(15); // Valor do produto é 15 centavos

        // Inserir mais moedas
        maquina.inserirMoeda(10);
        maquina.inserirMoeda(10);

        // Tentar comprar produto com valor maior que o total inserido
        maquina.comprarProduto(25); // Valor do produto é 25 centavos

    }

}
