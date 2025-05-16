//package org.cubco.controller;
//
//import org.cubco.response.CommonResponse;
//import org.cubco.exception.CustomException;
//import org.cubco.exception.ErrorCode;
//import org.cubco.swagger.TestApiDocs;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/test")
//public class TestController implements TestApiDocs {
//
//    @GetMapping("/success")
//    public CommonResponse<?> createUser() {
//        return CommonResponse.createSuccess(HttpStatus.OK ,"성공", "data");
//    }
//
//    @GetMapping("/error")
//    public CommonResponse<Void> error() {
//        throw new CustomException(ErrorCode.INVALID_REQUEST) {};
//    }
//}
