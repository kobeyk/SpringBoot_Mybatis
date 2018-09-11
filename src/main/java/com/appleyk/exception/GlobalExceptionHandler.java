package com.appleyk.exception;

import com.appleyk.result.ResponseResult;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseResult processException(Exception ex, HttpServletRequest request, HttpServletResponse response){
		
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatDate.format(new Date());

		/**
		 * view参数匹配异常
		 */
		if(ex instanceof MissingServletRequestParameterException){
			logger.info("\n view参数匹配异常--"+date+"\n"+ex);
			return new ResponseResult(400, "操作失败（参数不匹配）");
		}
		
		if(ex instanceof BaseException) {
			logger.info("\n "+ ex.getMessage() +" --" + date + "\n" + ex);
			return new ResponseResult(((BaseException) ex).getErrorCode(), ex.getMessage());
		}
		
		if(ex instanceof BadSqlGrammarException) {
			logger.info("\n SQL语句异常--"+date+"\n"+ex);
			return new ResponseResult(400, "操作失败（数据操作异常）");
		}
		
		if(ex instanceof MyBatisSystemException) {
			logger.info("\n SQL语句异常--"+date+"\n"+ex);
			return new ResponseResult(400, "操作失败（数据操作异常）");
		}

		/**
		 * json反序列化失败
		 */
		if(ex instanceof HttpMessageNotReadableException) {
			logger.info("\n json反序列化失败--"+date+"\n"+ex);
			return new ResponseResult(400, "操作失败（json对象不完整）" + ex.getMessage());
		}	
		
		/**
		 * 未知异常
		 */
		logger.info(ex.toString());
		return new ResponseResult(500, ex);
	}

}
