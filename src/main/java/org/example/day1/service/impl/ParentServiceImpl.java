package org.example.day1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.day1.domain.entity.Parent;
import org.example.day1.repository.ParentRepository;
import org.example.day1.service.ParentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    public Optional<Parent> update(Long id, Parent parent) {
        Optional<Parent> currentParent = parentRepository.findById(id);
        if(currentParent.isPresent()) {
            Parent updateParent = currentParent.get();
            updateParent.setFullName(parent.getFullName());
            updateParent.setPhone(parent.getPhone());
            updateParent.setEmail(parent.getEmail());
            updateParent.setAddress(parent.getAddress());
            return Optional.of(parentRepository.save(updateParent));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        Optional<Parent> parent = parentRepository.findById(id);
        if(parent.isPresent()) {
            parentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
