package br.com.carlos.ecommerce.config;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderToAwsS3 {

    @Autowired
    private AWSConfiguration awsConfiguration;

    @Value("${aws.buckt.name}")
    private String bucktName;

    public String envia(MultipartFile imagem) throws IOException, NoSuchAlgorithmException {
        var meta = new ObjectMetadata();
        meta.setContentLength(imagem.getSize());
        meta.setContentType("image/png");

        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(imagem.getOriginalFilename().getBytes(StandardCharsets.UTF_8));
        String sha3Hex = Hex.encodeHexString(hashbytes);

        PutObjectRequest request = new PutObjectRequest(bucktName, sha3Hex, imagem.getInputStream(),meta);
        try {
            awsConfiguration.amazonS3Client().putObject(request);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
        return "https://"+bucktName+".s3-sa-east-1.amazonaws.com/"+ sha3Hex;
    }


    public Set<String> SaveImagemS3(List<MultipartFile> imagens)throws NoSuchAlgorithmException{
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        return imagens.stream().map(imagem -> {
            Assert.isTrue(!imagem.isEmpty(),"Imagem Vazia");
                String URL = "";
                try {  URL = (envia(imagem)); }
                catch (IOException | NoSuchAlgorithmException e) { e.printStackTrace(); }
                return URL;
            }
        ).collect(Collectors.toSet());
    }
}