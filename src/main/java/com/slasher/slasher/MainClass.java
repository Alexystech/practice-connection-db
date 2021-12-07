/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slasher.slasher;

import com.slasher.slasher.entity.User;
import com.slasher.slasher.repository.impl.UserRepositoryImpl;

/**
 *
 * @author defin
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();

        User usuario = User.builder()
            .idUser(1L)
            .userName("slasher")
            .password("12345")
            .build();

        userRepositoryImpl.save(usuario);
    }
    
}
