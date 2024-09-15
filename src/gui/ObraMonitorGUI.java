package gui;

import entities.*;
import interfaces.RepositorioObras;
import services.*;
import interfaces.ServicoPlanejamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ObraMonitorGUI extends JFrame {
    private ServicoPlanejamento servicoPlanejamento;
    private JTextField nomeObraField;
    private JTextField nomeEngenheiroField;
    private JTextField registroEngenheiroField;
    private JComboBox<String> tipoObraCombo;
    private JTextArea listaObrasArea;
    private JComboBox<String> listaObrasCombo;  // ComboBox para seleção de obra
    private JButton atualizarObraButton;
    private JButton excluirObraButton;

    public ObraMonitorGUI() {
        // Configurar o serviço de planejamento
        RepositorioObras repositorio = new ObrasRepositorioImpl();
        servicoPlanejamento = new PlanejamentoService(repositorio);

        // Configuração básica da janela
        setTitle("Aplicação de Monitoramento de Obras");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior para inserir ou atualizar obras
        JPanel painelInserir = new JPanel();
        painelInserir.setLayout(new GridLayout(6, 2));

        // Campos de entrada para Obra e Engenheiro
        painelInserir.add(new JLabel("Nome da Obra:"));
        nomeObraField = new JTextField();
        painelInserir.add(nomeObraField);

        painelInserir.add(new JLabel("Nome do Engenheiro:"));
        nomeEngenheiroField = new JTextField();
        painelInserir.add(nomeEngenheiroField);

        painelInserir.add(new JLabel("Registro do Engenheiro:"));
        registroEngenheiroField = new JTextField();
        painelInserir.add(registroEngenheiroField);

        painelInserir.add(new JLabel("Tipo de Obra:"));
        tipoObraCombo = new JComboBox<>(new String[]{"Residencial", "Comercial"});
        painelInserir.add(tipoObraCombo);

        JButton adicionarObraButton = new JButton("Adicionar Obra");
        painelInserir.add(adicionarObraButton);

        atualizarObraButton = new JButton("Atualizar Obra");
        atualizarObraButton.setEnabled(false);  // Desabilitado até que uma obra seja selecionada
        painelInserir.add(atualizarObraButton);

        // Painel inferior para exibir lista de obras e selecionar para editar/excluir
        JPanel painelLista = new JPanel();
        painelLista.setLayout(new GridLayout(3, 1));

        listaObrasArea = new JTextArea();
        listaObrasArea.setEditable(false); // Deixar o campo somente leitura
        JScrollPane scrollPane = new JScrollPane(listaObrasArea);

        painelLista.add(new JLabel("Lista de Obras:"));
        painelLista.add(scrollPane);

        listaObrasCombo = new JComboBox<>();
        painelLista.add(listaObrasCombo);

        excluirObraButton = new JButton("Excluir Obra");
        excluirObraButton.setEnabled(false);  // Desabilitado até que uma obra seja selecionada

        // Adicionando os painéis e botões à interface
        add(painelInserir, BorderLayout.NORTH);
        add(painelLista, BorderLayout.CENTER);
        add(excluirObraButton, BorderLayout.SOUTH);

        // Ações dos botões
        adicionarObraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarObra();
            }
        });

        atualizarObraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarObra();
            }
        });

        excluirObraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirObra();
            }
        });

        listaObrasCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarObraSelecionada();
            }
        });

        // Atualizar a lista de obras ao iniciar
        atualizarListaObras();
    }

    // Função para adicionar uma obra
    private void adicionarObra() {
        String nomeObra = nomeObraField.getText();
        String nomeEngenheiro = nomeEngenheiroField.getText();
        String registroEngenheiro = registroEngenheiroField.getText();
        Engenheiro engenheiro = new Engenheiro(nomeEngenheiro, registroEngenheiro);

        String tipoObra = (String) tipoObraCombo.getSelectedItem();
        Obra obra;
        if ("Residencial".equals(tipoObra)) {
            obra = new ObraResidencial(nomeObra, engenheiro);
        } else {
            obra = new ObraComercial(nomeObra, engenheiro);
        }

        // Adicionar obra ao repositório
        servicoPlanejamento.adicionarObra(obra);

        // Atualizar a lista de obras e limpar campos de texto
        atualizarListaObras();
        nomeObraField.setText("");
        nomeEngenheiroField.setText("");
        registroEngenheiroField.setText("");
    }

    // Função para carregar obra selecionada para edição
    private void carregarObraSelecionada() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            for (Obra obra : servicoPlanejamento.listarObras()) {
                if (obra.getNome().equals(nomeObraSelecionada)) {
                    nomeObraField.setText(obra.getNome());
                    nomeEngenheiroField.setText(obra.getEngenheiro().getNome());
                    registroEngenheiroField.setText(obra.getEngenheiro().getRegistro());
                    tipoObraCombo.setSelectedItem(obra.getTipo());
                    atualizarObraButton.setEnabled(true);
                    excluirObraButton.setEnabled(true);
                    break;
                }
            }
        }
    }

    // Função para atualizar uma obra
    private void atualizarObra() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            for (Obra obra : servicoPlanejamento.listarObras()) {
                if (obra.getNome().equals(nomeObraSelecionada)) {
                    obra.setNome(nomeObraField.getText());
                    obra.getEngenheiro().setNome(nomeEngenheiroField.getText());
                    obra.getEngenheiro().setRegistro(registroEngenheiroField.getText());
                    obra.setTipo((String) tipoObraCombo.getSelectedItem());
                    atualizarListaObras();
                    break;
                }
            }
        }
    }

    // Função para excluir uma obra
    private void excluirObra() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            List<Obra> obras = servicoPlanejamento.listarObras();
            obras.removeIf(obra -> obra.getNome().equals(nomeObraSelecionada));
            atualizarListaObras();
        }
    }

    // Função para atualizar a lista de obras e ComboBox
    private void atualizarListaObras() {
        listaObrasArea.setText("");
        listaObrasCombo.removeAllItems();

        List<Obra> obras = servicoPlanejamento.listarObras();
        for (Obra obra : obras) {
            listaObrasArea.append(obra.getNome() + " (" + obra.getTipo() + ") - Engenheiro: " + obra.getEngenheiro().getNome() + "\n");
            listaObrasCombo.addItem(obra.getNome());
        }

        atualizarObraButton.setEnabled(false);
        excluirObraButton.setEnabled(false);
    }
}
