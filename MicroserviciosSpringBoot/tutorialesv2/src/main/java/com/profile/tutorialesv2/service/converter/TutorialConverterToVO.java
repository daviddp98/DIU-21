package com.profile.tutorialesv2.service.converter;

import com.profile.tutorialesv2.model.TutorialVO;
import com.profile.tutorialesv2.model.dto.TutorialDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialConverterToVO implements Converter<TutorialDTO, TutorialVO> {

    @Override
    public TutorialVO convert(TutorialDTO tutorialDTO) {
        return TutorialVO.builder()
                .titulo(tutorialDTO.getTitulo())
                .descripcion(tutorialDTO.getDescripcion())
                .publicado(tutorialDTO.isPublicado())
                .id(tutorialDTO.getId())
                .build();
    }
}