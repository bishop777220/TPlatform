/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tsystem.tplatform.entityes;
import com.tsystem.tplatform.security.TPUser;

/**
 *
 * @author mrbis
 */
 public class CurrentSession {
     
    static TPUser currentUser;

    public CurrentSession() {
    }

    public static TPUser getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(TPUser currentUser) {
        CurrentSession.currentUser = currentUser;
    }
    
    
}
