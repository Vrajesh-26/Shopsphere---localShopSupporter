package in.vrajeshdarji.shopsphere.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import in.vrajeshdarji.shopsphere.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;



import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final Cloudinary cloudinary;

    public FileUploadServiceImpl(Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String filenameExtension = "";
        if(originalFilename != null && originalFilename.contains(".")){
            filenameExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String uniqueFileName = UUID.randomUUID().toString();
        String publicId = "shopsphere/uploads/" + uniqueFileName;

        try{
            Map<String, Object> uploadOptions = ObjectUtils.asMap(
//TODO : DO CHANGE HERE
                    "public_id", publicId,
                    "resource_type", "image"
            );
            // it return file from cloudinary
            Map result = cloudinary.uploader().upload(file.getBytes(), uploadOptions);

            if(result.containsKey("secure_url")){
                return result.get("secure_url").toString();
            }else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred: URL not found in Cloudinary response.");
            }
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while uploading the file.", e);
        }
    }

    @Override
    public boolean deleteFile(String imgUrl) {
        try{
            String publicId = getPublicIdFromUrl(imgUrl);
            if(publicId == null) {
                System.err.println("Could not extract public_id from URL" + imgUrl);
                return false;
            }

            //Set options, specifying the resource_type as "image". to delete file
            Map<String, Object> options = ObjectUtils.asMap("resource_type", "image");
            Map result = cloudinary.uploader().destroy(publicId, options);

            return "ok".equals(result.get("result"));

        }catch (IOException e){
            System.err.println("Failed to delete file from Cloudinary: " + e.getMessage());
            return false;
        }
    }

    private String getPublicIdFromUrl(String url){
        try{
            int startIndex = url.indexOf("/upload/") + "/upload/".length();
            startIndex = url.indexOf('/', startIndex) + 1;
            int endIndex = url.lastIndexOf('.');

            if(startIndex > 0 && endIndex > startIndex){
                return url.substring(startIndex, endIndex);
            }
        }catch (Exception e){
            System.err.println("Error parsing public_id from URL: " + url);
        }

        return null;
    }
}
