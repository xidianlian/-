package cn.webrelax.form;

import java.util.Arrays;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 获取表单数据方式一
 * 使用ActionContext类中的getParameters()方法
 */
public class Form1Action extends ActionSupport{
	public String execute() {
		//通过静态方法获取对象
		ActionContext context = ActionContext.getContext();
		Map<String, Object> parameters = context.getParameters();
		//map遍历，先得到keySet再得到value
		for(String str:parameters.keySet()) {
			//注意，这里是对象数组，因为表单里有可能是多选项
			Object[] object = (Object[]) parameters.get(str);
			System.out.println(Arrays.toString(object));
		}
		for(Map.Entry<String, Object> m:parameters.entrySet()) {
			System.out.println(m.getKey()+":"+Arrays.toString((Object[])m.getValue()));
		}
		return NONE;
	}
}
