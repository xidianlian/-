package converter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * p.304 转换器
 */
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	//日期格式，可以通过配置注入
	private String datePattern;
	public  StringToDateConverter(String p) {
		datePattern=p;
		System.out.println("转换的格式为："+"MM-dd-yyyy");
	}
	@Override
	public Date convert(String s) {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);//解析是否 不严格，true为严格
		try {
			return dateFormat.parse(s);
		} catch (ParseException e) {
			//当用<form:errors>时，错误信息将会显示
			//参数传递不合法的异常
			throw new IllegalArgumentException("please use:"+datePattern);
			
		}
	}

}
