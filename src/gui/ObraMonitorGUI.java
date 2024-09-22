package gui;

import entities.Engenheiro;
import entities.Obra;
import entities.ObraComercial;
import entities.ObraResidencial;
import interfaces.IObrasRepositorio;
import interfaces.IPlanejamentoServico;
import services.ObrasIImplRepositorio;
import services.PlanejamentoServicoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ObraMonitorGUI extends JFrame {
    private IPlanejamentoServico iPlanejamentoServico;
    private JTextField nomeObraField;
    private JTextField nomeEngenheiroField;
    private JTextField registroEngenheiroField;
    private JComboBox<String> tipoObraCombo;
    private JTextArea listaObrasArea;
    private JComboBox<String> listaObrasCombo;

    // Campos adicionais para obras residenciais e comerciais
    private JTextField numeroQuartosField;
    private JCheckBox possuiJardimCheckBox;
    private JTextField numeroAndaresField;
    private JCheckBox possuiEstacionamentoCheckBox;

    private JButton atualizarObraButton;
    private JButton excluirObraButton;

    public ObraMonitorGUI() {

        IObrasRepositorio repositorio = new ObrasIImplRepositorio();
        iPlanejamentoServico = new PlanejamentoServicoService(repositorio);

        setTitle("Aplicação de Monitoramento de Obras");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelInserir = new JPanel();
        painelInserir.setLayout(new GridLayout(9, 2));

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
        tipoObraCombo.addActionListener(e -> atualizarCamposEspecificos());
        painelInserir.add(tipoObraCombo);

        // Campos para Obra Residencial
        painelInserir.add(new JLabel("Número de Quartos:"));
        numeroQuartosField = new JTextField();
        painelInserir.add(numeroQuartosField);

        painelInserir.add(new JLabel("Possui Jardim?"));
        possuiJardimCheckBox = new JCheckBox();
        painelInserir.add(possuiJardimCheckBox);

        // Campos para Obra Comercial
        painelInserir.add(new JLabel("Número de Andares:"));
        numeroAndaresField = new JTextField();
        painelInserir.add(numeroAndaresField);

        painelInserir.add(new JLabel("Possui Estacionamento?"));
        possuiEstacionamentoCheckBox = new JCheckBox();
        painelInserir.add(possuiEstacionamentoCheckBox);

        JButton adicionarObraButton = new JButton("Adicionar Obra");
        painelInserir.add(adicionarObraButton);

        atualizarObraButton = new JButton("Atualizar Obra");
        atualizarObraButton.setEnabled(false);
        painelInserir.add(atualizarObraButton);

        JPanel painelLista = new JPanel();
        painelLista.setLayout(new GridLayout(3, 1));

        listaObrasArea = new JTextArea();
        listaObrasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaObrasArea);
        painelLista.add(new JLabel("Lista de Obras:"));
        painelLista.add(scrollPane);

        listaObrasCombo = new JComboBox<>();
        painelLista.add(listaObrasCombo);

        excluirObraButton = new JButton("Excluir Obra");
        excluirObraButton.setEnabled(false);

        add(painelInserir, BorderLayout.NORTH);
        add(painelLista, BorderLayout.CENTER);
        add(excluirObraButton, BorderLayout.SOUTH);

        adicionarObraButton.addActionListener(e -> adicionarObra());
        atualizarObraButton.addActionListener(e -> atualizarObra());
        excluirObraButton.addActionListener(e -> excluirObra());
        listaObrasCombo.addActionListener(e -> carregarObraSelecionada());

        atualizarCamposEspecificos();  // Iniciar com campos específicos corretos
        atualizarListaObras();


    }



    private void atualizarCamposEspecificos() {
        String tipoObra = (String) tipoObraCombo.getSelectedItem();
        boolean isResidencial = "Residencial".equals(tipoObra);

        numeroQuartosField.setVisible(isResidencial);
        possuiJardimCheckBox.setVisible(isResidencial);
        numeroAndaresField.setVisible(!isResidencial);
        possuiEstacionamentoCheckBox.setVisible(!isResidencial);
    }

    private void adicionarObra() {
        String nomeObra = nomeObraField.getText();
        String nomeEngenheiro = nomeEngenheiroField.getText();
        String registroEngenheiro = registroEngenheiroField.getText();
        String tipoObra = (String) tipoObraCombo.getSelectedItem();

        if (nomeObra.isEmpty() || nomeEngenheiro.isEmpty() || registroEngenheiro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Engenheiro engenheiro = new Engenheiro(nomeEngenheiro, registroEngenheiro);

        Obra obra;
        if ("Residencial".equals(tipoObra)) {
            int numeroQuartos;
            try {
                numeroQuartos = Integer.parseInt(numeroQuartosField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número de quartos inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean possuiJardim = possuiJardimCheckBox.isSelected();
            obra = new ObraResidencial(nomeObra, engenheiro, numeroQuartos, possuiJardim);
        } else {
            int numeroAndares;
            try {
                numeroAndares = Integer.parseInt(numeroAndaresField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Número de andares inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean possuiEstacionamento = possuiEstacionamentoCheckBox.isSelected();
            obra = new ObraComercial(nomeObra, engenheiro, numeroAndares, possuiEstacionamento);
        }

        // Adicionar obra ao repositório
        iPlanejamentoServico.adicionarObra(obra);
        JOptionPane.showMessageDialog(this, "Obra adicionada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Atualizar a lista de obras e limpar campos de texto
        atualizarListaObras();
        limparCampos();
    }

    private void limparCampos() {
        nomeObraField.setText("");
        nomeEngenheiroField.setText("");
        registroEngenheiroField.setText("");
        numeroQuartosField.setText("");
        numeroAndaresField.setText("");
        possuiJardimCheckBox.setSelected(false);
        possuiEstacionamentoCheckBox.setSelected(false);
    }

    private void carregarObraSelecionada() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            for (Obra obra : iPlanejamentoServico.listarObras()) {
                if (obra.getNome().equals(nomeObraSelecionada)) {
                    nomeObraField.setText(obra.getNome());
                    nomeEngenheiroField.setText(obra.getEngenheiro().getNome());
                    registroEngenheiroField.setText(obra.getEngenheiro().getRegistro());
                    tipoObraCombo.setSelectedItem(obra.getTipo());

                    if (obra instanceof ObraResidencial) {
                        ObraResidencial obraResidencial = (ObraResidencial) obra;
                        numeroQuartosField.setText(String.valueOf(obraResidencial.getNumeroDeQuartos()));
                        possuiJardimCheckBox.setSelected(obraResidencial.isPossuiJardim());
                    } else if (obra instanceof ObraComercial) {
                        ObraComercial obraComercial = (ObraComercial) obra;
                        numeroAndaresField.setText(String.valueOf(obraComercial.getNumeroDeAndares()));
                        possuiEstacionamentoCheckBox.setSelected(obraComercial.isPossuiEstacionamento());
                    }

                    atualizarCamposEspecificos();
                    atualizarObraButton.setEnabled(true);
                    excluirObraButton.setEnabled(true);
                    break;
                }
            }
        }
    }

    private void atualizarObra() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            for (Obra obra : iPlanejamentoServico.listarObras()) {
                if (obra.getNome().equals(nomeObraSelecionada)) {
                    obra.setNome(nomeObraField.getText());
                    obra.getEngenheiro().setNome(nomeEngenheiroField.getText());
                    obra.getEngenheiro().setRegistro(registroEngenheiroField.getText());

                    String tipoObra = (String) tipoObraCombo.getSelectedItem();
                    obra.setTipo(tipoObra);

                    if (obra instanceof ObraResidencial) {
                        ((ObraResidencial) obra).setNumeroDeQuartos(Integer.parseInt(numeroQuartosField.getText()));
                        ((ObraResidencial) obra).setPossuiJardim(possuiJardimCheckBox.isSelected());
                    } else if (obra instanceof ObraComercial) {
                        ((ObraComercial) obra).setNumeroDeAndares(Integer.parseInt(numeroAndaresField.getText()));
                        ((ObraComercial) obra).setPossuiEstacionamento(possuiEstacionamentoCheckBox.isSelected());
                    }

                    JOptionPane.showMessageDialog(this, "Obra atualizada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizarListaObras();
                    break;
                }
            }
        }
    }


    private void excluirObra() {
        String nomeObraSelecionada = (String) listaObrasCombo.getSelectedItem();
        if (nomeObraSelecionada != null) {
            iPlanejamentoServico.removerObra(nomeObraSelecionada);
            JOptionPane.showMessageDialog(this, "Obra excluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarListaObras();
            limparCampos();
            atualizarObraButton.setEnabled(false);
            excluirObraButton.setEnabled(false);
        }
    }

    private void atualizarListaObras() {
        listaObrasArea.setText("");
        listaObrasCombo.removeAllItems();

        List<Obra> obras = iPlanejamentoServico.listarObras();
        for (Obra obra : obras) {
            listaObrasArea.append(obra.toString() + "\n");  // Usando o toString das obras
            listaObrasCombo.addItem(obra.getNome());
        }

        atualizarObraButton.setEnabled(false);
        excluirObraButton.setEnabled(false);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ObraMonitorGUI gui = new ObraMonitorGUI();
            gui.setVisible(true);
        });
    }
}
