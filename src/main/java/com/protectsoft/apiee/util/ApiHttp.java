
package com.protectsoft.apiee.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.ByteArrayInputStream;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.internal.MultiPartWriter;

/**
 * 
 */
public class ApiHttp {
    
    public static ClientResponse get(String url,String headerName,String headerValue){
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .accept("application/json")
                .type("application/json")
                .header(headerName, headerValue)
                .get(ClientResponse.class);
        return response;
    }
    
    /**
     * 
     * @param url
     * @param auth
     * @return response.getEntityInputStream()
     * ..also 
     * Response.ok(response.getEntityInputStream())
                  .header("Content-disposition", "attachment;filename=files.zip")
                  .build();
     */
    public static ClientResponse getZip(String url,String auth){
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .accept("application/zip")
                .header("Authorization", auth)
                .get(ClientResponse.class);
        return response;
    }
    
    /**
     * 
     * @param url
     * @param auth
     * @return return Response.ok(response.getEntityInputStream()).build();
     */
    public static ClientResponse getOctetStream(String url,String auth) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .accept("application/octet-stream")
                .header("Authorization", auth)
                .get(ClientResponse.class);
        return response;
    }
    
    public static ClientResponse post(String url,Object ob,String auth) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .type("application/json")
                .accept("application/json")
                .header("Authorization", auth)
                .post(ClientResponse.class,ob);
        
        return response;
    }
    
    
    public static ClientResponse postFile(String url,String name,String filename,byte[] data,String auth) {
        DefaultClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getClasses().add(MultiPartWriter.class);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(url);
        
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        formDataMultiPart.field(name, filename);
        FormDataBodyPart bodyPart = new FormDataBodyPart(name,new ByteArrayInputStream(data),MediaType.APPLICATION_OCTET_STREAM_TYPE);
        formDataMultiPart.bodyPart(bodyPart);
        
        ClientResponse response = webResource
        .type(MediaType.MULTIPART_FORM_DATA_TYPE)
                .header("Authorization", auth)
                .post(ClientResponse.class, formDataMultiPart);

        return response;        
    }
    
    
    public static ClientResponse put(String url,Object ob,String auth) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .type("application/json")
                .accept("application/json")
                .header("Authorization", auth)
                .put(ClientResponse.class,ob);
        
        return response;
    }
    
    
    public static ClientResponse delete(String url,String auth){
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource
                .header("Authorization", auth)
                .delete(ClientResponse.class);
        return response;
    }
    
}
