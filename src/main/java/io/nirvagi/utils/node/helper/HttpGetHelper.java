
 /*---------------------------------------------------------------------------------------------------------
 * Copyright 2016 Nirvagi project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * \*-------------------------------------------------------------------------------------------------------------------*/
 
package io.nirvagi.utils.node.helper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;

public class HttpGetHelper {
	private static final String ERR_MESSAGE = "The url %s passed is malformed";
	private static final String HTTP_GET_FAILED_MESSAGE = "The http get for the url %s failed, The client returned the code of %s with message %s";
	
	private final OkHttpClient client;
	private final Request httpRequest;
	

	public HttpGetHelper(final String url){

		HttpUrl URL = HttpUrl.parse(url);
			/* TODO remove this lines after debugging completion*/

		if (URL == null) {
			throw new RuntimeException(String.format(ERR_MESSAGE, url));
		}
		System.out.println("Url passed over to Httphelper is valid");
		httpRequest = new Request.Builder()
		.url(url)
		.build();
		/* TODO remove this lines after debugging completion*/
		System.out.println("Http request(GET) prepared");
		client = new OkHttpClient();
	}
	
	
	
	
	public void execute(){
		try {
			//TODO add response handler
			Response response = client.newCall(httpRequest).execute();
			if (!response.isSuccessful()) {
				System.out.println(String.format(
						HTTP_GET_FAILED_MESSAGE, httpRequest.url()
								.toString(), response.code(), response
								.message()));
			}
			System.out.println("Get request triggered");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally{
				this.client.dispatcher().executorService().shutdown();
				System.out.println("Http client got closed due to...");

		}
	}

	
	

}
