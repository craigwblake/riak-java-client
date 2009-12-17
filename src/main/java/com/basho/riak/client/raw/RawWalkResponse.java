/*
This file is provided to you under the Apache License,
Version 2.0 (the "License"); you may not use this file
except in compliance with the License.  You may obtain
a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.  
*/
package com.basho.riak.client.raw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpMethod;

import com.basho.riak.client.response.HttpResponse;
import com.basho.riak.client.response.WalkResponse;
import com.basho.riak.client.util.Constants;

public class RawWalkResponse implements WalkResponse {

    private HttpResponse impl;
    
    public boolean hasSteps() { return this.steps != null; }
    public List<? extends List<RawObject>> getSteps() { return steps; }
    private List<? extends List<RawObject>> steps;

    public RawWalkResponse(HttpResponse r) {
        this.impl = r;
        if (r.isSuccess())
            this.steps = parseSteps(r.getHttpHeaders().get(Constants.HDR_CONTENT_TYPE), r.getBody());
    }

    public String getBody() { return impl.getBody(); }
    public String getBucket() { return impl.getBucket(); }
    public Map<String, String> getHttpHeaders() { return impl.getHttpHeaders(); }
    public HttpMethod getHttpMethod() { return impl.getHttpMethod(); } 
    public String getKey() { return impl.getKey(); }
    public int getStatusCode() { return impl.getStatusCode(); }
    public boolean isError() { return impl.isError(); }
    public boolean isSuccess() { return impl.isSuccess(); }

    private static List<? extends List<RawObject>> parseSteps(String contentType, String body) {
        List<? extends List<RawObject>> steps = new ArrayList<ArrayList<RawObject>>();
        List<String> parts = parseMultipart(contentType, body);
        
        for (String part : parts) {
        }
        
        return steps;
    }
    
    private static List<String> parseMultipart(String contentType, String body) {
        List<String> parts = new ArrayList<String>();
        String boundary = getBoundary(contentType);

        if (boundary == null)
            return parts;
        
        return parts;
    }
    
    private static String getBoundary(String contentType) {
        if (contentType == null)
            return null;
    
        int start = contentType.toLowerCase().indexOf("boundary=");
        if (start > -1) {
            return "--" + contentType.substring(start + 9);
        }

        return null;
    }
}
