package com.hangdaoju.core.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;

@ControllerAdvice
public class GlobalException {

	private ResponseModel getErrorMessage(Exception e, String messageStr) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(baos));
		ResponseModel responseModel = new ResponseModel();
		responseModel.setRet(500);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("500", messageStr);
		responseModel.setData(map);
		return responseModel.setInformation(baos.toString());
	}

	/**
	 * 空指针异常
	 * 
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public ResponseModel nullPointerException(NullPointerException e, HttpServletResponse response) {
		return getErrorMessage(e, "空指针异常，请联系后台");
	}

	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public ResponseModel classCastException(ClassCastException e, HttpServletResponse response) {
		return getErrorMessage(e, "强制类型转换异常，请联系后台");
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public ResponseModel numberFormatException(NumberFormatException e, HttpServletResponse response) {
		return getErrorMessage(e, "字符串转换数字异常，请联系后台");
	}

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public ResponseModel arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e,
			HttpServletResponse response) {
		return getErrorMessage(e, "数组下标越界异常，请联系后台");
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public ResponseModel sqlException(SQLException e, HttpServletResponse response) {
		return getErrorMessage(e, "数据库操作异常，请联系后台");
	}

	@ExceptionHandler(IOException.class)
	@ResponseBody
	public ResponseModel ioException(IOException e, HttpServletResponse response) {
		return getErrorMessage(e, "io流操作异常，请联系后台");
	}
	
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public ResponseModel noSuchMethodException(NoSuchMethodException e) {
		return getErrorMessage(e, "方法未找到，请仔细阅读文档或联系后台");
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseModel runtimeExceptionHandler(RuntimeException e, HttpServletResponse response) {
		return getErrorMessage(e, "系统内部错误，请联系后台");
	}
}
