/*
 *  Copyright (c) 2015 Michal Ďuračík
 */
package com.unlink.common.HttpClient;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Unlink
 */
public interface IHttpClient {

	public String get(String paUrl) throws IOException;

	public String get(String paUrl, String paEnconding) throws IOException;

	public String post(String paUrl, List<BasicNameValuePair> paParams) throws IOException;

	public String post(String paUrl, List<BasicNameValuePair> paParams, String paEnconding) throws IOException;
}
