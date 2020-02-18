package com.bytatech.ayoos.doctor.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bytatech.ayoos.doctor.service.impl.DoctorServiceImpl;

import io.minio.MinioClient;

@Service
public class ImageService {

	private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);
	
	@Autowired
	private MinioClient minioClient;

	@Value("${minio.buckets.doctor}")
	private String doctorBucketName;

//	@Value("${minio.buckets.category}")
//	private String categoryBucketName;

	@Value("${minio.configuration.contentType}")
	private String contentType;
	
	@Value("${minio.server.url}")
	private String url;


	@SuppressWarnings("deprecation")
	public String saveFile(String type, String entityId, byte[] data) {
		InputStream streamData = new ByteArrayInputStream(data);
		String bucket = null;
		String imageName = null; 
		String imageLink = url;
		if (type.equals("doctor")) {
			bucket = doctorBucketName;
			imageName = entityId+"-doctor-image.png";
			imageLink = url+"/"+bucket+"/"+imageName;
			log.info("Saving the doctor Image in bucket "+bucket+" imagename is "+imageName+" image link is "+imageLink);
		} 
//		else if (type.equals("category")) {
//			bucket = categoryBucketName;
//			imageName = entityId+"-category-image.png";
//			imageLink = url+"/"+bucket+"/"+imageName;
//			log.info("Saving the category Image in bucket "+bucket+" imagename is "+imageName+"  image link is "+imageLink);
//		}
		try {
			minioClient.putObject(bucket, imageName, streamData, contentType);
			log.info("Image has been saved successfully");

		} catch (Exception e) {
			log.error("Something went wrong while saving image to miniob server "+ e.getMessage());
		}
		
		return imageLink;
	}


}
