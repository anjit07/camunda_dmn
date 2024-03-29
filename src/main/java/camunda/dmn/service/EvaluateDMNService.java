package camunda.dmn.service;

import org.apache.commons.lang3.time.DateUtils;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.dmn.DecisionEvaluationBuilder;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class EvaluateDMNService {

    @Autowired
    private ProcessEngine processEngine;

   public List<Map<String,Object>> evaluate(String dmnKey,Map<String,Object> request){

        List<Map<String,Object>> response = null;
        try{
            VariableMap variable = null;
            variable = Variables.createVariables();
            variable.putAll(request);
            variable.put("currentDate", LocalDateTime.now());

            DecisionService decisionService = processEngine.getDecisionService();

            DecisionEvaluationBuilder decisionEvaluationBuilder = decisionService.evaluateDecisionTableByKey(dmnKey);
            decisionEvaluationBuilder.variables(request);
            DmnDecisionTableResult dmnDecisionRuleResults = decisionEvaluationBuilder.evaluate();

            response = dmnDecisionRuleResults.getResultList();

        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
