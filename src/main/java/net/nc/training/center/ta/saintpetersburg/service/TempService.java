package net.nc.training.center.ta.saintpetersburg.service;

import net.nc.training.center.ta.saintpetersburg.model.TempModelMessage;
import net.nc.training.center.ta.saintpetersburg.repository.TempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempService {

    private final TempRepository tempRepository;

    @Autowired
    public TempService(TempRepository tempRepository) {
        this.tempRepository = tempRepository;
    }

    public List<TempModelMessage> findAll(){
        return tempRepository.findAll();
    }
}
