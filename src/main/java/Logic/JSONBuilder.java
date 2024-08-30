/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author soyky
 */
public class JSONBuilder {
    
    
    public JSONBuilder(){}
    
    public JSONObject createFromGenericObject(Object[] fields , String[] FieldsNames){
        JSONObject mainJSON = new JSONObject();
        for(int i = 0 ; i < FieldsNames.length ; i++){
            Object curField = fields[0];
            String curKey = FieldsNames[0];
            mainJSON.append(curKey, curField);
        }
        return mainJSON;
        
    }
    
    public JSONObject createFromListGenericsObjects(List<Object[]> listGenericsObjects , String[] FieldsNames , String JSONGenericKey){
        int currentGenericObjectCounter = 1;
        JSONObject mainJSON = new JSONObject();
        for(Object[] genericObject : listGenericsObjects){
            JSONObject subJSON = createFromGenericObject(genericObject, FieldsNames);
            String curKey = JSONGenericKey + currentGenericObjectCounter;
            mainJSON.append(curKey, subJSON);
            currentGenericObjectCounter++;
        }
        return mainJSON;
    }
    
}
