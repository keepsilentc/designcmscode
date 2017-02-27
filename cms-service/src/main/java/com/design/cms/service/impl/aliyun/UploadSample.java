package com.design.cms.service.impl.aliyun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadFileResult;
import com.design.cms.dao.entity.Attachment;

/**
 * 断点续传上传用法示例
 *
 */
public class UploadSample {
    
    private static String endpoint = "oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAILOJ7yqMrZcbo";
    private static String accessKeySecret = "5fdzfLutShBW3sliuTUcmBkIFSOBIo";
    private static String bucketName = "chicunbucket";
//    private static String uploadFile = "<uploadFile>";

    public static void main(String[] args) throws Exception {        

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
     // 列举bucket
//        List<Bucket> buckets = ossClient.listBuckets();
//        for (Bucket bucket : buckets) {
//            System.out.println(" - " + bucket.getName());
//        }
        
//        File file = new File("D:\\/usr/local/tomcat/upload/productDetail/2017011113312326818.jpg");
//        System.out.println(file.getName());
        
        
        Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/designnew?useUnicode=true&characterEncoding=utf-8","root","Cc2016666!");
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM dn_attachment");
		
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		while(rs.next()){
			attachment = new Attachment();
			attachment.setId(rs.getLong("ID"));
			attachment.setFilePath(rs.getString("filePath"));
			attachment.setServerFileName(rs.getString("ServerFileName"));
			attachment.setClassify(rs.getString("Classify"));
			attachmentList.add(attachment);
		}
		rs.close();
		stmt.close();
		int i = 0;
        try {
        	for(Attachment tmp:attachmentList){
        		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, "chicun/"+tmp.getId());
                // 待上传的本地文件
                uploadFileRequest.setUploadFile("D:\\"+tmp.getFilePath()+tmp.getServerFileName());
                // 设置并发下载数，默认1
//                uploadFileRequest.setTaskNum(5);
                // 设置分片大小，默认100KB
//                uploadFileRequest.setPartSize(1024 * 1024 * 1);
                // 开启断点续传，默认关闭
//                uploadFileRequest.setEnableCheckpoint(false);
                
                ossClient.uploadFile(uploadFileRequest);
                UploadFileResult uploadResult = ossClient.uploadFile(uploadFileRequest);
                
                CompleteMultipartUploadResult multipartUploadResult = 
                        uploadResult.getMultipartUploadResult();
                System.out.println(multipartUploadResult.getETag());
                i++;
                System.out.println(i);
        	}
            System.out.println(i);
           
            
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
