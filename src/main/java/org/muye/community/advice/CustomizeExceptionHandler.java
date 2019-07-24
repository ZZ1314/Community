//package org.muye.community.advice;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author Zz
// * create 2019--07--20--14:59
// **/
//@ControllerAdvice
//public class CustomizeExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model){
//        HttpStatus status = getStatus(request);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("error");
//        modelAndView.addObject("message",status);
//        return modelAndView;
//    }
//    private HttpStatus getStatus(HttpServletRequest request){
//        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.statue_code");
//        if(statusCode == null ){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
//}
