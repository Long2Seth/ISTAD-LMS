package co.istad.lms.features.classes;

import co.istad.lms.features.classes.dto.ClassRequest;

/**
 * Business logic interface which contains to manage Classes
 *
 * @author Pov Soknem
 * @since 1.0 (2024)
 */
public interface ClassService {

    /**
     * Create new Classes
     *
     * @param classRequest is the object that contain information to create new Class
     * @author Pov Soknem
     * @since 1.0 (2024)
     */
    void createClass(ClassRequest classRequest);

}
