package com.prwebsitebackend.service;

import com.prwebsitebackend.dto.StatisticalFormDTO;
import com.prwebsitebackend.model.StatisticalForm;
import com.prwebsitebackend.model.StatisticalFormColumn;
import com.prwebsitebackend.repository.StatisticalFormColumnRepository;
import com.prwebsitebackend.repository.StatisticsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final StatisticalFormColumnRepository statisticalFormColumnRepository;

    public StatisticsService(StatisticsRepository statisticsRepository, StatisticalFormColumnRepository statisticalFormColumnRepository) {
        this.statisticsRepository = statisticsRepository;
        this.statisticalFormColumnRepository = statisticalFormColumnRepository;
    }

    public StatisticalForm CreateStatisticalForm(StatisticalFormDTO statisticalFormDTO) {
        ModelMapper mapper = new ModelMapper();
        StatisticalForm statisticalForm = mapper.map(statisticalFormDTO, StatisticalForm.class);
        return statisticsRepository.save(statisticalForm);
    }

    public StatisticalForm updateStatisticalForm(String id,StatisticalFormDTO statisticalFormDTO){
       Optional<StatisticalForm> statisticalForm = statisticsRepository.findById(Long.parseLong(id));

        if(statisticalForm.isPresent()){

            List<StatisticalFormColumn> columns = new ArrayList<>();

            statisticalFormDTO.getColumns().forEach(column -> {
                StatisticalFormColumn statisticalFormColumn = new StatisticalFormColumn();

                statisticalFormColumn.setName(column.getName());
                statisticalFormColumn.setColumn_condition(column.getColumn_condition());
                statisticalFormColumn.setColumn_function(column.getColumn_function());
                statisticalFormColumn.setColumn_option(column.getColumn_option());

                statisticalFormColumn.setStatisticalForm(statisticalForm.get());
                columns.add(statisticalFormColumn);
            });

            statisticalForm.get().setId(Long.parseLong(id));
            statisticalForm.get().setName(statisticalFormDTO.getName());
            statisticalForm.get().setColumns(columns);

                //save columns
                statisticalFormColumnRepository.saveAll(columns);
            return statisticsRepository.save(statisticalForm.get());
        }
        return null;
    }

    public StatisticalForm getStatisticalForm(String id) {
        Optional<StatisticalForm> form = statisticsRepository.findById(Long.parseLong(id));
        return form.orElse(null);
    }

    public List<StatisticalForm> getAllStatisticalForms() {
        return statisticsRepository.findAll();
    }
}
