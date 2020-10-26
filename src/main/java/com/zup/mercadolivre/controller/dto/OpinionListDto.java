package com.zup.mercadolivre.controller.dto;

import java.util.List;

public class OpinionListDto {
    
    private List<OpinionDTO> opinions;

    public OpinionListDto(List<OpinionDTO> opinions) {
        this.opinions = opinions;
    }

    public List<OpinionDTO> getOpinions() {
        return this.opinions;
    }

}
