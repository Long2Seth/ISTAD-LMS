package co.istad.lms.util;

import org.springframework.beans.factory.annotation.Value;

public class MediaUtil {

    //endpoint that handle manage medias
    @Value("${media.end-point}")
    private static String mediaEndpoint;

    //base uri for media
    @Value("${media.base-uri}")
    private static String baseUri;

    public static String getUrl(String fileName){

        return baseUri + mediaEndpoint + "/view/" + fileName;
    }
}
