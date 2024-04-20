/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util;

import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Fiorella
 */
public class ImageConverter {

    public static String toBase64(BufferedImage image, String format) {
        try {
            ByteArrayOutputStream byteConverter = new ByteArrayOutputStream();
            ImageIO.write(image, format, byteConverter);
            byte[] imageBytes = byteConverter.toByteArray();
            byteConverter.flush();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image fromBase64(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            return new Image(new ByteArrayInputStream(decodedBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFormat(File file) {
        String name = file.getName();
        if (name.contains(".")) {
            return name.substring(name.lastIndexOf(".")+1).toUpperCase();
        }
        return null;
    }
}
