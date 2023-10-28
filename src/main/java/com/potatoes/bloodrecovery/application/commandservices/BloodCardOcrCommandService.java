package com.potatoes.bloodrecovery.application.commandservices;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.potatoes.bloodrecovery.domain.model.view.OcrView;
import com.potatoes.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.potatoes.constants.ResponseCode.FAIL_OCR;
import static com.potatoes.constants.ResponseCode.NO_IMAGE;

@Service
@RequiredArgsConstructor
@Slf4j
public class BloodCardOcrCommandService {

    public OcrView bloodCardOcr (MultipartFile imageFile) throws IOException {

        Image image = checkImageFile(imageFile);
        List<AnnotateImageRequest> requests = requestOcrInit(image);

        //OCR 실행
        String compareName = "성 명: ";
        String compareType = "헌혈종류: ";
        String compareDate = "헌혈일자: ";

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            String responseString = "";
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            responseString += responses.get(0).getTextAnnotationsList().get(0).getDescription();
            log.info(responseString);

            String[] strings = responseString.split("\n");
            String name = "";
            String type = "";
            String date = "";
            String code = strings[2];
            for (String e : strings) {
                if (e.contains(compareName)) {
                    name = e.split(compareName)[1].split(" ")[0];
                }
                if (e.contains(compareType)) {
                    type = e.split(compareType)[1];
                }
                if (e.contains(compareDate)) {
                    date = e.split(compareDate)[1].replaceAll(" ", "");
                    date = date.replaceAll("\\.", "-");
                    if (date.endsWith("-")) {
                        date = date.substring(0, date.length() - 1);
                    }
                }
            }
            log.info("result[ code:" + code + ", name:" + name + ", type:" + type + ", date:" + date + " ]");

            return OcrView.builder()
                    .name(name)
                    .code(code)
                    .donationType(type)
                    .date(date)
                    .build();
        } catch (Exception e) {
            throw new ApiException(FAIL_OCR);
        }
    }

    private Image checkImageFile(MultipartFile imageFile) throws IOException {
        Image image;

        if (ObjectUtils.isNotEmpty(imageFile)) {
            ByteString imgBytes = ByteString.readFrom(imageFile.getInputStream());
            image = Image.newBuilder().setContent(imgBytes).build();
        } else {
            throw new ApiException(NO_IMAGE);
        }

        return image;
    }

    private List<AnnotateImageRequest> requestOcrInit(Image image){
        List<AnnotateImageRequest> requests = new ArrayList<>();

        Feature feature = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image).build();
        requests.add(request);

        return requests;
    }
}
