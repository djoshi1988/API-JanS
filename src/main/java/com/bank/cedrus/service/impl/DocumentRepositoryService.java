package com.bank.cedrus.service.impl;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.cedrus.model.request.ClaimDetails;
import com.bank.cedrus.model.request.Document;


@Service
public class DocumentRepositoryService {
	
	
  @Value("${claim.document.save.directory}")
  private String documentSaveDirectory;
  
  public void saveDocument(ClaimDetails claimDetails, Document document) throws Exception {
      String baseDirectoryPath = documentSaveDirectory;
      String directoryPath = baseDirectoryPath + "\\" + claimDetails.getSchemeName() + "\\" + claimDetails.getUrn();
      String filePath = directoryPath + "\\" + document.getDocumentId() + ".pdf";

      
      
      Path directory = Paths.get(directoryPath);
      if (!Files.exists(directory)) {
          Files.createDirectories(directory);
      }

      byte[] decodedBytes = Base64.getDecoder().decode(document.getDocument());

      try (FileOutputStream fos = new FileOutputStream(filePath)) {
          fos.write(decodedBytes);
      }
  }

}
