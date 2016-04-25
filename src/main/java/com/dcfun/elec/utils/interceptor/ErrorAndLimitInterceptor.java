package com.dcfun.elec.utils.interceptor;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dcfun.elec.domain.ElecRole;
import com.dcfun.elec.domain.ElecUser;
import com.dcfun.elec.service.IElecRoleService;
import com.dcfun.elec.utils.annotation.AnnotationLimit;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ErrorAndLimitInterceptor extends MethodFilterInterceptor {

	/**拦截器*/
	@Override
	protected String doIntercept(ActionInvocation actioninvocation) throws Exception {
		//把自定义错误信息 放置到request中
		HttpServletRequest request = (HttpServletRequest) actioninvocation
						.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		try {
			//获取请求的action对象
			Object action = actioninvocation.getAction();
			//获取请求的方法的名称
			String methodName = actioninvocation.getProxy().getMethod();
			//获取action中的方法的封装类(action中的方法没有参数)
			Method method = action.getClass().getMethod(methodName, null);
			// Action的返回值   
			String result = null; 
			
			//在完成跳转Action之前完成细颗粒权限控制，控制Action的每个方法
			//检查注解，是否可以操作权限的URL
			boolean flag = isCheckLimit(request,method);
			if(flag){
				// 运行被拦截的Action,期间如果发生异常会被catch住   
				result = actioninvocation.invoke();
			}
			else{
				request.setAttribute("errorMsg", "对不起！您没有权限操作此功能！");
				return "errorMsg";
			}
			return result;
		} catch (Exception e) {
			/**  
			 * 处理异常  
			 */
			String errorMsg = "出现错误信息，请查看日志！";
			//通过instanceof判断到底是什么异常类型   
			if (e instanceof RuntimeException) {
				//未知的运行时异常   
				RuntimeException re = (RuntimeException) e;
				//re.printStackTrace();
				errorMsg = re.getMessage().trim();
			}
			/**  
			 * 发送错误消息到页面  
			 */
			request.setAttribute("errorMsg", errorMsg);

			/**  
			 * log4j记录日志  
			 */
			Log log = LogFactory
					.getLog(actioninvocation.getAction().getClass());
			log.error(errorMsg, e);
			return "errorMsg";
		}// ...end of catch   
	}
	
	
	/**验证细颗粒权限控制*/
	public boolean isCheckLimit(HttpServletRequest request, Method method) {
		if(method == null){
			return false;
		}
		//获取当前的登陆用户
		ElecUser elecUser = (ElecUser)request.getSession().getAttribute("globle_user");
		if(elecUser == null){
			return false;
		}
		
		//获取当前登陆用户的角色
		ElecRole role = elecUser.getElecRole();
		if(role == null){
			return false;
		}
		//处理注解，判断方法上是否存在注解（注解的名称为：AnnotationLimit）
		/*
		 * 例如：
		 * 	@AnnotationLimit(mid="aa",pid="0")
	        public String home(){
		 */
		boolean isAnnotationPresent = method.isAnnotationPresent(AnnotationLimit.class);
		
		//不存在注解（此时不能操作该方法）
		if(!isAnnotationPresent){
			return false;
		}
		
		//存在注解（调用注解）
		AnnotationLimit limit = method.getAnnotation(AnnotationLimit.class);
		
		//获取注解上的值
		String mid = limit.mid();  //权限子模块名称
		String pid = limit.pid();  //权限父操作名称
		
		/**
		 * 如果登陆用户的角色id+注解上的@AnnotationLimit(mid="aa",pid="0")
		 *   * 在elec_role_popedom表中存在   flag=true，此时可以访问Action的方法;
		 *   * 在elec_role_popedom表中不存在 flag=false，此时不能访问Action的方法;
		 */
		boolean flag = false;
		//拦截器中加载spring容器，从而获取Service类，使用Service类查询对应的用户信息
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		IElecRoleService elecRoleService = (IElecRoleService)wac.getBean(IElecRoleService.SERVICE_NAME);

		flag = elecRoleService.findRolePopedomByID(role.getRoleID(), mid, pid);
		
		return flag;
	}


}
