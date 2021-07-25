package com.gmail.aba.service;

import com.gmail.aba.model.Gym;
import com.gmail.aba.repository.GymRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymService {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;

    }

    public List <Gym> getAllGums() {
        return gymRepository.findAll();
    }


    public void saveGym(Gym gym) {
        this.gymRepository.save(gym);
    }

    public Gym getGymById(long id) {
        Optional<Gym> optional = gymRepository.findById(id);
        Gym gym = null;
        if (optional.isPresent()) {
            gym = optional.get();
        } else {
            throw new RuntimeException("Gym not found for id :: " + id);
        }
        return gym;
    }


    public void deleteGymById(long id) {
        this.gymRepository.deleteById(id);
    }


    public Page<Gym> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.gymRepository.findAll(pageable);
    }

}
