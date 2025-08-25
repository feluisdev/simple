package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.license2.filter.SectorFilter;
import cv.igrp.simple.licenciamento.domain.license2.repository.SectorRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.SectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListSectorDTO;

@Component
public class GetSectoresQueryHandler implements QueryHandler<GetSectoresQuery, ResponseEntity<WrapperListSectorDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetSectoresQueryHandler.class);

  private final SectorRepository sectorRepository;
  private final SectorMapper sectorMapper;

  public GetSectoresQueryHandler(SectorRepository sectorRepository, SectorMapper sectorMapper) {

      this.sectorRepository = sectorRepository;
      this.sectorMapper = sectorMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListSectorDTO> handle(GetSectoresQuery query) {

     var filter = SectorFilter.builder()
             .sectorType(query.getSectorType())
             .name(query.getName())
             .code(query.getCode())
             .active(query.isActive())
             .pageNumber(query.getPageNumber() != null ? Integer.parseInt(query.getPageNumber()) : 0)
             .pageSize(query.getPageSize() != null ? Integer.parseInt(query.getPageSize()) : 20)
             .build();

     var sectors = sectorRepository.findAll(filter);

     var content = sectors.stream()
             .map(sectorMapper::toResponseDTO)
             .toList();

     WrapperListSectorDTO wrapper = new WrapperListSectorDTO();
     wrapper.setContent(content);
     wrapper.setPageNumber(filter.getPageNumber());
     wrapper.setPageSize(filter.getPageSize());
     wrapper.setTotalElements((long) content.size());
     wrapper.setTotalPages((int) Math.ceil((double) content.size() / filter.getPageSize()));
     wrapper.setFirst(filter.getPageNumber() == 0);
     wrapper.setLast(content.size() < filter.getPageSize());

     return ResponseEntity.ok(wrapper);
  }

}