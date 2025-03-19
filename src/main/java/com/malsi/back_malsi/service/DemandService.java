package com.malsi.back_malsi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.model.Category;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Demand;
import com.malsi.back_malsi.repository.CategoryRepository;
import com.malsi.back_malsi.repository.ClientRepository;
import com.malsi.back_malsi.repository.DemandRepository;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public DemandDto createDemand(Demand demand) {
        Client client = clientRepository.findById(demand.getClient().getId()).orElse(null);
        demand.setClient(client);
        Category category = categoryRepository.findById(demand.getCategory().getId()).orElse(null);
        demand.setCategory(category);
        return this.modelMapper.map(this.demandRepository.save(demand), DemandDto.class);
    }

    public DemandDto getDemandById(Integer id) {
        Demand demand = this.demandRepository.findById(id).orElse(null);
        if (demand == null) {
            return null;
        }

        return this.modelMapper.map(demand, DemandDto.class);
    }

    public List<DemandDto> getDemands() {
        List<Demand> demands = this.demandRepository.findAll();
        if (demands == null) {
            return null;
        }
        List<DemandDto> demandsDto = new ArrayList<>();

        for (Demand demand : demands) {
            demandsDto.add(this.modelMapper.map(demand, DemandDto.class));
        }

        return demandsDto;
    }

    public List<DemandDto> getClientDemands(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            return null;
        }
        List<Demand> demands = client.getDemands();

        List<DemandDto> demandsDto = new ArrayList<>();
        for (Demand demand : demands) {
            demandsDto.add(this.modelMapper.map(demand, DemandDto.class));
        }

        return demandsDto;
    }

    public DemandDto updateDemandStatus(Integer id, String status) {
        Demand demand = this.demandRepository.findById(id).orElse(null);
        if (demand == null) {
            return null;
        }
        demand.setStatus(status);
        return this.modelMapper.map(this.demandRepository.save(demand), DemandDto.class);
    }

    public List<DemandDto> getDemandsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return null;
        }
        List<Demand> demands = category.getDemands();
        
        List<DemandDto> demandsDto = new ArrayList<>();
        for (Demand demand : demands) {
            demandsDto.add(this.modelMapper.map(demand, DemandDto.class));
        }

        return demandsDto;
    }

}
