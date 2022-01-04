package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.nnk.springboot.domain.CurvePoint;

import javax.transaction.Transactional;


@Service
@Transactional
public class CurveService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    public List<CurvePoint> findAll(){
        return curvePointRepository.findAll();
    }

    public CurvePoint findById(Integer id){
        return curvePointRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("CurvePoint", id));
    }

    public CurvePoint save(CurvePoint curvePoint){
        return curvePointRepository.save(curvePoint);
    }

    public void delete(Integer id){
        CurvePoint curvePointToDelete = findById(id);
        curvePointRepository.delete(curvePointToDelete);
    }

}
