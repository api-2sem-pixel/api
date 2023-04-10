package model;

import java.time.LocalDateTime;

import enums.EtapaExtrato;

public class ExtratoHoraModel {
    private Integer id;
    private String projeto;
    private String modalidade;
    private String cr;
    private String motivo;
    private String justificativa;
    private EtapaExtrato status;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
    
    public ExtratoHoraModel() {
        super();
        id = 0;
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
    
    public void setStatus(EtapaExtrato status) {
        this.status = status;
    }
    
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
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
}
