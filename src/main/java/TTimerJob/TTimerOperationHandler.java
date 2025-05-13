/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TTimerJob;

import java.util.Date;
import java.util.List;

/**
 *
 * @author mrbis
 */
public class TTimerOperationHandler {

    private final String[][] startingTypeList = {{"d","День"},{"D","Неделя"},{"М","Месяц"},{"y","Год"}};
    
    private String[] startingType;
    
    private List<TJob> tjobList;
    
    Date date = new Date();

    public TTimerOperationHandler() {
        
    }
    
    
    
}
