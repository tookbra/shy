package com.tookbra.shy.Handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * global exception
 * Created by tookbra on 2016/7/7.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ModelAndView errorHandler(HttpServletRequest request, Exception exception) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", exception);
        mv.setViewName("");
        return mv;
    }
}
