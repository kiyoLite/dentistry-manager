/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import org.json.JSONObject;

/**
 *
 * @author soyky
 */
public class JSONBuilder {
    
    public JSONObject createFromGenericObject(Object[] fields , String[] FieldsNames){
        JSONObject mainJSON = new JSONObject();
        for(int i = 0 ; i < FieldsNames.length ; i++){
            Object curField = fields[0];
            String curKey = FieldsNames[0];
            mainJSON.append(curKey, curField);
        }
        return mainJSON;
        
    }
    
}
