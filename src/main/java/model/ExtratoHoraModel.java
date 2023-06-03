package model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import enums.EtapaExtrato;

public class ExtratoHoraModel {
    private Integer id;
    private Integer idUsuario;
    private String solicitante;
    private String projeto;
    private Integer idModalidade;
    private String modalidade;
    private String cr;
    private Integer idCr;
    private Integer idCliente;
    private String cliente;
    private String motivo;
    private String justificativa;
    private EtapaExtrato status;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private boolean isRowEditable = false;

    public ExtratoHoraModel() {
        super();
        id = 0;
    }

    public static ExtratoHoraModel criarLinhaPadrao() {
        var extrato = new ExtratoHoraModel();
        extrato.setCr("SELECIONE");
        extrato.setProjeto("PREENCHA");
        extrato.setCliente("SELECIONE");
        extrato.setModalidade("SELECIONE");
        extrato.setDataHoraInicio(LocalDateTime.now());
        extrato.setDataHoraFim(LocalDateTime.now());
        extrato.setMotivo("-");
        extrato.setJustificativa("PREENCHA");
        extrato.setStatus(EtapaExtrato.CRIACAO);

        return extrato;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer usuario) {
        this.idUsuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getCr() {
        return cr;
    }

    public void setCr(String cr) {
        this.cr = cr;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public EtapaExtrato getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = EtapaExtrato.values()[status];
    }

    public void setStatus(EtapaExtrato status) {
        this.status = status;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getDataHoraInicioS() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHoraInicio.format(formatter);
    }

    public String getDataHoraFimS() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        return dataHoraFim.format(formatter);
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Integer getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Integer idModalidade) {
        this.idModalidade = idModalidade;
    }

    public Integer getIdCr() {
        return idCr;
    }

    public void setIdCr(Integer idCr) {
        this.idCr = idCr;
    }

    public boolean getIsRowEditable() {
        return this.isRowEditable;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer id) {
        this.idCliente = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdMotivo(String motivo2) {
        return null;
    }

}
