package core.io.helpdesk.domain.enums;

import lombok.Getter;

@Getter
public enum Status {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), FECHADO(2, "FECHADO");
    private Integer codigo;
    private String descricao;
    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (Status p : Status.values()){
            if(cod.equals(p.getCodigo())){
                return p;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }

}
