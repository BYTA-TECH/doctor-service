package com.bytatech.ayoos.doctor.config;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

@Configuration
public class MinioServerConfiguration {
	

	@Value("${minio.server.url}")
	private String url;

	@Value("${minio.server.accessKey}")
	private String accesskey;

	@Value("${minio.server.secretKey}")
	private String secretKey;

	@Value("${minio.buckets.doctor}")
	private String doctorBucketName;


	@Bean
	public MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
		MinioClient minioClient = new MinioClient(url, accesskey, secretKey);
		try {
			boolean doctorBucketFound = minioClient.bucketExists(doctorBucketName);
			if (doctorBucketFound) {
				Log.info("DoctorBucket already exists " + doctorBucketName);
			} else {
				minioClient.makeBucket(doctorBucketName);
				Log.info("DoctorBucket created " + doctorBucketName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return minioClient;
	}

}
