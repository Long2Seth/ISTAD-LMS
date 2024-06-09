package co.istad.lms.features.minio;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

public class UrlCache {
    private final Cache<String, CachedUrl> cache;

    @Value("${cache.duration}")
    private long cacheDuration;

    @Value("${cache.url-duration}")
    private long cacheUrlDuration;

    public UrlCache() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(cacheDuration*60*60, TimeUnit.SECONDS) // Example expiration policy
                .build();
    }

    public String getCachedUrl(String objectName) {
        CachedUrl cachedUrl = cache.getIfPresent(objectName);
        return (cachedUrl != null && !cachedUrl.isExpired()) ? cachedUrl.getUrl() : null;
    }

    public void cacheUrl(String objectName, String url) {
        cache.put(objectName, new CachedUrl(url,
                System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cacheUrlDuration*60*60)));
    }

    @AllArgsConstructor
    @Getter
    private static class CachedUrl {
        private final String url;
        private final long expiryTime;

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }
}
