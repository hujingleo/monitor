package com.monitor.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {

	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 通过Get方法调用第三方接口
	 * 
	 * @param url
	 * @return
	 */
	public static String getGetResponse(String url, Map<String, String> headerMap) {
		String responseContent = null;

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(2000); // 超时时间
		params.setSoTimeout(2000);
		params.setMaxTotalConnections(500); // 最大连接数
		params.setDefaultMaxConnectionsPerHost(500);
		params.setStaleCheckingEnabled(true);

		SimpleHttpConnectionManager simpleConnectionManage = new SimpleHttpConnectionManager();
		simpleConnectionManage.setParams(params); // 设置参数

		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));// 连接不上
																													// 重试次数

		HttpClient httpClient = new HttpClient(simpleConnectionManage); // 构造HttpClient的实例
		httpClient.setParams(httpClientParams);

		GetMethod getMethod = new GetMethod(url); // 创建GET方法的实例

		if (null != headerMap && !headerMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getKey())) {
					getMethod.setRequestHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); // 使用系统提供的默认的恢复策略
		try {

			int statusCode = httpClient.executeMethod(getMethod); // 执行调用
			if (statusCode == HttpStatus.SC_OK) {
				responseContent = getMethod.getResponseBodyAsString();// 返回内容
			} else {
				log.error("Method failed: " + getMethod.getStatusLine());
			}

		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(); // 发生网络异常
			log.error(e.getMessage());
		} finally {
			getMethod.releaseConnection(); // 释放连接
		}
		return responseContent;
	}

	public static String getGetResponse(String url) {
		return getGetResponse(url, null);
	}

	public static String getPostResponse(String url, Map<String, String> paramMap) {
		return getPostResponse(url, paramMap, null);
	}

	/**
	 * 通过Post方法调用第三方接口
	 * 
	 * @param url
	 * @param paramMap
	 *            添加的参数
	 * @return
	 */
	public static String getPostResponse(String url, Map<String, String> paramMap, Map<String, String> headerMap) {
		String responseContent = null;

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(10 * 1000); // 超时时间
		params.setSoTimeout(10 * 1000);
		params.setMaxTotalConnections(500); // 最大连接数
		params.setDefaultMaxConnectionsPerHost(500);
		params.setStaleCheckingEnabled(true);

		SimpleHttpConnectionManager simpleConnectionManage = new SimpleHttpConnectionManager();
		simpleConnectionManage.setParams(params); // 设置参数

		HttpClientParams httpClientParams = new HttpClientParams();
		httpClientParams.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));// 连接不上
																													// 重试次数

		HttpClient httpClient = new HttpClient(simpleConnectionManage); // 构造HttpClient的实例
		httpClient.setParams(httpClientParams);

		PostMethod postMethod = new PostMethod(url); // 创建POST方法的实例

		// 设置参数
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		if (null != headerMap && !headerMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				if (StringUtils.isNotEmpty(entry.getKey())) {
					postMethod.setRequestHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		if (paramMap != null) {
			for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				nameValuePairList.add(new NameValuePair(key, paramMap.get(key)));
			}
		}
		postMethod.setRequestBody(nameValuePairList.toArray(new NameValuePair[] {}));

		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); // 使用系统提供的默认的恢复策略
		try {

			int statusCode = httpClient.executeMethod(postMethod); // 执行调用
			if (statusCode == HttpStatus.SC_OK) {
				responseContent = postMethod.getResponseBodyAsString();// 返回内容
			} else {
				log.error("Method failed: " + postMethod.getStatusLine());
			}

		} catch (HttpException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace(); // 发生网络异常
			log.error(e.getMessage());
		} finally {
			postMethod.releaseConnection(); // 释放连接
		}

		return responseContent;
	}
}
