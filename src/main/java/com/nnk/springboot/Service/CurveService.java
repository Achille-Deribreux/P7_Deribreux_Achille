package com.nnk.springboot.Service;

import com.nnk.springboot.Exceptions.CustomExceptions.ObjectNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(CurveService.class);

    public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    /**
     * Method who call the findAll method of the repository
     * @Return  a list of all the CurvePoint (List of CurvePoint)
     */
    public List<CurvePoint> findAll(){
        LOGGER.info("Find All CurvePoint");
        return curvePointRepository.findAll();
    }

    /**
     * Method who call the findById method of the repository to search in the database the CurvePoint with the given ID
     * @Param  Id of the CurvePoint that we are searching for (Integer)
     * @Return  the CurvePoint corresponding to the given Id (CurvePoint)
     */
    public CurvePoint findById(Integer id){
        LOGGER.info("Find CurvePoint By id "+id);
        return curvePointRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("CurvePoint", id));
    }

    /**
     * Method who call the save method of the repository to save the given CurvePoint in the database
     * @Param  CurvePoint to save(CurvePoint)
     * @Return  Saved CurvePoint, with Id (CurvePoint)
     */
    public CurvePoint save(CurvePoint curvePoint){
        LOGGER.info("Save CurvePoint with curveId:"+curvePoint.getCurveId());
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Method who call the delete method of the repository to delete the wanted CurvePoint in the database
     * @Param  Id of the CurvePoint that we want to delete to save(Integer)
     */
    public void delete(Integer id){
        CurvePoint curvePointToDelete = findById(id);
        LOGGER.info("Delete CurvePoint with curveId:"+curvePointToDelete.getCurveId());
        curvePointRepository.delete(curvePointToDelete);
    }

}
