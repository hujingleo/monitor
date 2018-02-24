package com.monitor.utils;



import com.monitor.exceptions.SessionException;
import com.monitor.model.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static String SESSION_APPUSERINFO = "SESSION_APPUSERINFO";

	/**
	 * 获取当前登录的用户名
	 * 
	 * @param session
	 * @return
	 * @throws SessionException
	 */
	public static String getCurrentUserName(HttpSession session) {

		User userInfo = (User) session.getAttribute(SESSION_APPUSERINFO);
		if (userInfo == null || StringTool.isNullOrEmpty(userInfo.getUsername())) {
			throw new SessionException();
		} else
			return userInfo.getUsername();

	}

//	public static String getCurrentDisplayName(HttpSession session) {
//
//		User userInfo = (User) session.getAttribute(SESSION_APPUSERINFO);
//		if (userInfo == null || StringTool.isNullOrEmpty(userInfo.getUsername())) {
//			throw new SessionException();
//		} else {
//			String truename = userInfo.getTruename();
//			if (StringTool.isNullOrEmpty(truename))
//				return userInfo.getUsername();
//			else
//				return truename;
//		}
//
//	}
}
