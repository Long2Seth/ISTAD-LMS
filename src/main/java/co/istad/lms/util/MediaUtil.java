package co.istad.lms.util;

import org.springframework.beans.factory.annotation.Value;

public class MediaUtil {

    private static String mediaEndpoint;
    private static String baseUri;

    @Value("${media.end-point}")
    public void setMediaEndpoint(String mediaEndpoint) {
        MediaUtil.mediaEndpoint = mediaEndpoint;
    }

    @Value("${media.base-uri}")
    public void setBaseUri(String baseUri) {
        MediaUtil.baseUri = baseUri;
    }

    public static String getUrl(String fileName) {
        System.out.println("base url = " + baseUri);
        return baseUri + mediaEndpoint + "/view/" + fileName;
    }
}
