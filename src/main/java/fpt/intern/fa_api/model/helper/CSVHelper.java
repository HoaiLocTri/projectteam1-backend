package fpt.intern.fa_api.model.helper;

import fpt.intern.fa_api.model.entity.Syllabus;
import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.entity.User;
import fpt.intern.fa_api.model.enums.DeliveryType;
import fpt.intern.fa_api.model.enums.Method;
import fpt.intern.fa_api.model.enums.SyllabusStatus;
import fpt.intern.fa_api.model.request.AsEntity.SyllabusContentRequest;
import fpt.intern.fa_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Syllabus> csvToSyllabuses(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Syllabus> syllabusList = new ArrayList<Syllabus>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Syllabus syllabus = new Syllabus();
                syllabus.setId(Long.parseLong(csvRecord.get("ID")));
                syllabus.setName(csvRecord.get("Name"));
                syllabus.setTechnicalRequirement(csvRecord.get("TechnicalRequirement"));
                syllabus.setVersion(csvRecord.get("Version"));
                syllabus.setTrainingAudience(Integer.parseInt(csvRecord.get("Audience")));
                syllabus.setTopicOutline(csvRecord.get("TopicOutline"));
                syllabus.setTrainingMaterials(csvRecord.get("trainingMaterials"));
                syllabus.setTrainingPrinciples(csvRecord.get("trainingPrinciples"));
                syllabus.setPriority(csvRecord.get("Priority"));
                syllabus.setStatus(SyllabusStatus.valueOf(csvRecord.get("Status")));
                User user = new User();
                user.setId(Long.parseLong(csvRecord.get("Author")));
                syllabus.setCreatedBy(user);

                syllabusList.add(syllabus);
            }

            return syllabusList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<SyllabusContent> csvToSyllabusOutLine(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SyllabusContent> syllabusContentRequests = new ArrayList<SyllabusContent>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SyllabusContent syllabusContent = new SyllabusContent();
                syllabusContent.setId(Long.parseLong(csvRecord.get("ID")));
                syllabusContent.setUnitName(csvRecord.get("Name"));
                syllabusContent.setDay(Long.parseLong(csvRecord.get("Day") != null ? csvRecord.get("Day") : "0"));
                syllabusContent.setUnit(Long.parseLong(csvRecord.get("Unit") != null ? csvRecord.get("Unit") : "0"));
                syllabusContent.setTrainingMaterial(csvRecord.get("Material"));
                syllabusContent.setMinute(Long.parseLong(csvRecord.get("StudyTime") != null ? csvRecord.get("StudyTime") : "0"));
                syllabusContent.setDeliveryType(DeliveryType.valueOf(csvRecord.get("LessonType")));
                syllabusContent.setMethod(Method.valueOf(csvRecord.get("LearningForm")));
                syllabusContent.setLessonName(csvRecord.get("Lesson"));
                Syllabus syllabus = new Syllabus();
                syllabus.setId(Long.parseLong(csvRecord.get("SyllabusID")));
                syllabusContent.setSyllabus(syllabus);

                syllabusContentRequests.add(syllabusContent);
            }

            return syllabusContentRequests;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

//  public static ByteArrayInputStream tutorialsToCSV(List<Tutorial> tutorials) {
//    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
//
//    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
//        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
//      for (Tutorial tutorial : tutorials) {
//        List<String> data = Arrays.asList(
//              String.valueOf(tutorial.getId()),
//              tutorial.getTitle(),
//              tutorial.getDescription(),
//              String.valueOf(tutorial.isPublished())
//            );
//
//        csvPrinter.printRecord(data);
//      }
//
//      csvPrinter.flush();
//      return new ByteArrayInputStream(out.toByteArray());
//    } catch (IOException e) {
//      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
//    }
//  }

}
