/**
 *  Copyright (c) 2015 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.bpmn.rest.service.query;

import io.netty.handler.codec.http.QueryStringDecoder;
import org.wso2.carbon.bpmn.rest.common.RestUrlBuilder;
import org.wso2.carbon.bpmn.rest.model.common.DataResponse;
import org.wso2.carbon.bpmn.rest.model.history.HistoricTaskInstanceQueryRequest;
import org.wso2.carbon.bpmn.rest.service.base.BaseHistoricTaskInstanceService;
import org.wso2.msf4j.Request;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 */

@Path("/historic-task-instances")
public class HistoricTaskInstanceQueryService extends BaseHistoricTaskInstanceService {

    @POST
    @Path("/")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DataResponse queryProcessInstances(HistoricTaskInstanceQueryRequest queryRequest,
                                              @Context Request request) {
        Map<String, String> allRequestParams = new HashMap<>();
        QueryStringDecoder decoder = new QueryStringDecoder(request.getUri());
        if(decoder.parameters().size() > 0) {
            for (String property : ALL_PROPERTIES_LIST) {
                String value = decoder.parameters().get(property).get(0);

                if (value != null) {
                    allRequestParams.put(property, value);
                }
            }
        }
        RestUrlBuilder builder = new RestUrlBuilder(request.getUri());
        String serverRootUrl = builder.getBaseUrl();
        return getQueryResponse(queryRequest, allRequestParams, serverRootUrl, request.getUri());
    }
}


