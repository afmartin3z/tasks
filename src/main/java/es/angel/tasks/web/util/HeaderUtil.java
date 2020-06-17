package es.angel.tasks.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(HeaderUtil.class);

    /** The Constant APPLICATION_NAME. */
    private static final String APPLICATION_NAME = "crud";

    /**
     * Instantiates a new header util.
     */
    private HeaderUtil() {
    }

    /**
     * Creates the alert.
     *
     * @param message the message
     * @param param   the param
     * @return the http headers
     */
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-alert", message);
        headers.add("X-" + APPLICATION_NAME + "-params", param);
        return headers;
    }

    /**
     * Creates the entity creation alert.
     *
     * @param entityName the entity name
     * @param param      the param
     * @return the http headers
     */
    public static HttpHeaders createEntityCreationAlert(String entityName, Object param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".created", param.toString());
    }

    /**
     * Creates the entity update alert.
     *
     * @param entityName the entity name
     * @param param      the param
     * @return the http headers
     */
    public static HttpHeaders createEntityUpdateAlert(String entityName, Object param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param.toString());
    }

    /**
     * Creates the entity deletion alert.
     *
     * @param entityName the entity name
     * @param param      the param
     * @return the http headers
     */
    public static HttpHeaders createEntityDeletionAlert(String entityName, Object param) {
        return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param.toString());
    }

    /**
     * Creates the failure alert.
     *
     * @param entityName     the entity name
     * @param errorKey       the error key
     * @param defaultMessage the default message
     * @return the http headers
     */
    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        LOG.error("Entity processing failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-error", "error." + errorKey);
        headers.add("X-" + APPLICATION_NAME + "-params", entityName);
        return headers;
    }

    /**
     * Creates the download file headers.
     *
     * @param filename the filename
     * @return the http headers
     */
    public static HttpHeaders createDownloadFileHeaders(String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return headers;
    }
}
