package org.cubco.s3;

import lombok.extern.slf4j.Slf4j;
import org.cubco.exception.ErrorCode;
import org.cubco.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class S3Service {
    private final String bucketName;
    private final AWSConfig awsConfig;
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png", "image/jpg", "image/webp", "image/heic", "image/heif");
    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L; // 최대 5MB

    public S3Service(@Value("${aws-property.s3.bucketName}") String bucketName, AWSConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    public String uploadImage(String directoryPath, MultipartFile image, Long id) throws IOException {
        final S3Client s3Client = awsConfig.getS3Client();
        String imageName = directoryPath + id + generateImageFileName(image);  // 이미지 파일 이름 생성
        // 파일 유효성 검사
        checkInvalidUploadFile(image);
        validateExtension(image);
        validateFileSize(image);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(imageName)
                .contentType(image.getContentType())
                .contentDisposition("inline")
                .build();
        RequestBody requestBody = RequestBody.fromBytes(image.getBytes());
        try {
            s3Client.putObject(request, requestBody);
        } catch (S3Exception e) {
            log.error("Image Upload Error: {}", e.getMessage());
            throw new IOException("파일 업로드에 실패했습니다.", e);
        }
        return s3Client.utilities().getUrl(builder ->
                builder.bucket(bucketName).key(imageName)).toExternalForm();
    }

    public void deleteImage(String imageUrl) {
        final S3Client s3Client = awsConfig.getS3Client();
        String imageName = extractKeyFromUrl(imageUrl);
        try {
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(imageName)
                    .build());
        } catch (S3Exception e) {
            log.error("Image Delete Error: {}", e.getMessage());
            throw new InvalidValueException(ErrorCode.FAIL_DELETE_IMAGE);
        }
    }

    private String extractKeyFromUrl(String imageUrl) {
        String host = "https://" + bucketName + ".s3." + awsConfig.getRegion().id() + ".amazonaws.com/";
        if (imageUrl.startsWith(host)) {
            return imageUrl.substring(host.length());
        }
        throw new InvalidValueException(ErrorCode.INVALID_IMAGE_URL);
    }

    // 이미지 파일 이름을 UUID 기반으로 생성
    public String generateImageFileName(MultipartFile image) {
        String extension = getExtension(Objects.requireNonNull(image.getContentType()));
        return "/" + UUID.randomUUID() + extension;
    }

    // 확장자에 맞는 파일 포맷 가져오기
    private String getExtension(String contentType) {
        return switch (contentType) {
            case "image/png" -> ".png";
            case "image/webp" -> ".webp";
            case "image/heic" -> ".heic";
            case "image/heif" -> ".heif";
            default -> ".jpg";
        };
    }

    // 이미지 파일 확장자 유효성 검사
    private void validateExtension(MultipartFile image) {
        String contentType = image.getContentType();
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new InvalidValueException(ErrorCode.INVALID_FILE_EXTENSION);
        }
    }

    // 이미지 파일 크기 유효성 검사
    private void validateFileSize(MultipartFile image) {
        if (image.getSize() > MAX_FILE_SIZE) {
            throw new InvalidValueException(ErrorCode.INVALID_FILE_SIZE);
        }
    }

    // 파일이 비어있거나 크기가 0인 경우 유효하지 않음
    private void checkInvalidUploadFile(MultipartFile image) {
        if (image.isEmpty() || image.getSize() == 0) {
            throw new InvalidValueException(ErrorCode.INVALID_FILE_EMPTY);
        }
    }
}
