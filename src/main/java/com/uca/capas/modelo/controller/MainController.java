package com.uca.capas.modelo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping("/ingresar")
    public String index() {
        return "ingresar";
    }

    @RequestMapping("/resultado")
    public ModelAndView resultado(HttpServletRequest request) {
        String usuario = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNac = request.getParameter("fechaNac").toString();
        String lugarNac = request.getParameter("lugarNac");
        String colegio = request.getParameter("colegio");
        String telFijo = request.getParameter("telFijo").toString();
        String telMovil = request.getParameter("telMovil").toString();

        ModelAndView mav = new ModelAndView();
        List<String> errores = new ArrayList<>();


        if(usuario.length()<1 || usuario.length()>25){
            errores.add("Nombre no puede ser mayor de 25 caracteres ni menor a 1");
        }
        if(apellido.length()<1 || apellido.length()>25){
            errores.add("Apellidos no puede ser mayor de 25 caracteres ni menor a 1");
        }

        int annio = Integer.parseInt(fechaNac.substring(0, 4));
        if(annio < 2003){
            errores.add("Tu año de nacimiento no puede ser menor a 2003");
        }
        if(lugarNac.length()<1 || lugarNac.length()>25){
            errores.add("Lugar de nacimiento no puede ser mayor de 25 caracteres ni menor a 1");
        }
        if(colegio.length()<1 || colegio.length()>100){
            errores.add("Instituto no puede ser mayor de 100 caracteres ni menor a 1");
        }
        if(telFijo.length()!=8 ){
            errores.add("Numero de telefono fijo tiene que ser igual a 8 caracteres");
        }
        if(telMovil.length()!=8 ){
            errores.add("Numero de telefono movil tiene que ser igual a 8 caracteres");
        }
        for(String error : errores ){
            System.out.println(error);
        }
        if(errores.size()==0) {
            mav.setViewName("loginSucces");
        }else{
            mav.addObject("errores", errores);
            mav.setViewName("loginFailed");
        }
        return mav;
    }

}