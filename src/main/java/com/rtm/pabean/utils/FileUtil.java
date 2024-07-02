package com.rtm.pabean.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

public class FileUtil {
    
    @SuppressWarnings("null")
    public static File createTempFile(MultipartFile multipartFile, String tempFolder){
        Path filepath = Paths.get(tempFolder, multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(filepath);
            return filepath.toFile();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean createDirectories(String path){
        File directories = new File(path);
        return directories.mkdirs();
    }

    public static String generateDirDate(String baseDir){
        String today = DateUtil.toString(new Date(), DateUtil.YYYYMMDD);
        String dir = baseDir + "/" + today;
        if(new File(dir).exists()){
            return dir;
        }
        if(createDirectories(dir)){
            return dir;
        }
        return "";
    }

    public static boolean deleteFile(String filepath) throws IOException {
        return Files.deleteIfExists(Paths.get(filepath));
    }

    public static String base64ToFile(String baseFolder, String encodedContent, String filename) {
        if (Optional.ofNullable(encodedContent).orElse("").isEmpty()) {
            return "";
        }
        try {
            String directory = generateDirDate(baseFolder);
            Path path = Paths.get(directory);
            File file = new File(path.toString() + "/" + filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] decoder = Base64.getDecoder().decode(encodedContent);
            outputStream.write(decoder);
            outputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
