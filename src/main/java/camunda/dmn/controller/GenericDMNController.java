package camunda.dmn.controller;

import camunda.dmn.service.EvaluateDMNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dmn")
public class GenericDMNController {

    @Autowired
    private EvaluateDMNService evaluateDMNService;


    @PostMapping(value = "/{dmnKey}/evaluate",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String,Object>> evaluate(@PathVariable("dmnKey") String dmnKey, @RequestBody Map<String,Object> request){


        return evaluateDMNService.evaluate(dmnKey,request);
    }

}
