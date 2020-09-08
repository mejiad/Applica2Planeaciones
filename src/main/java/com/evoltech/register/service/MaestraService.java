package com.evoltech.register.service;

import com.evoltech.register.model.jpa.Maestra;
import com.evoltech.register.model.jpa.User;
import com.evoltech.register.repository.MaestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestraService {

    @Autowired
    MaestraRepository maestraRepository;

    public boolean authenticate(User user){
        Boolean ret = false;
        List<Maestra> list = maestraRepository.findByEmail(user.getEmail());
        if (list.size() > 0 ){ ret = true; }
        return ret;
    }

    Maestra findMaestraByEmail(User user){
        Maestra maestra = null;

        List<Maestra> list = maestraRepository.findByEmail(user.getEmail());
        if (list.size() > 0 ){ maestra = list.get(0); }
        return maestra;
    }
}
