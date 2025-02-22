package fpt.intern.fa_api.util;

import fpt.intern.fa_api.dto.LessonDTO;
import fpt.intern.fa_api.dto.UnitDTO;
import fpt.intern.fa_api.model.entity.SyllabusContent;
import fpt.intern.fa_api.model.response.SyllabusContentResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class SyllabusContentTransformer {

    public static List<SyllabusContentResponse> transform(List<SyllabusContent> syllabusContents) {
        // Nhóm các đối tượng SyllabusContent theo ngày và unit
        Map<Long, Map<Long, List<SyllabusContent>>> groupedByDayAndUnit = syllabusContents.stream()
                .collect(Collectors.groupingBy(SyllabusContent::getDay,
                        Collectors.groupingBy(SyllabusContent::getUnit)));

        // Biến đổi từng nhóm
        return groupedByDayAndUnit.entrySet().stream()
                .map(entry -> transformGroup(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static SyllabusContentResponse transformGroup(Long day, Map<Long, List<SyllabusContent>> unitGroup) {
        // Lấy thông tin chung từ bất kỳ đối tượng trong nhóm
        SyllabusContent sampleSyllabusContent = unitGroup.values().iterator().next().get(0);

        // Biến đổi từng nhóm thành SyllabusContentResponse
        return SyllabusContentResponse.builder()
                .id(sampleSyllabusContent.getId())
                .day(day)
                .Listunit(mapToUnitDTOList(unitGroup))
                .unitName(sampleSyllabusContent.getUnitName())
                .unitNumber(sampleSyllabusContent.getUnit())
                .syllabusId(sampleSyllabusContent.getSyllabus().getId())
                .build();
    }

    private static List<UnitDTO> mapToUnitDTOList(Map<Long, List<SyllabusContent>> unitGroup) {
        return unitGroup.entrySet().stream()
                .map(entry -> mapToUnitDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static UnitDTO mapToUnitDTO(Long unit, List<SyllabusContent> syllabusContents) {
        return UnitDTO.builder()
                .UnitName(syllabusContents.get(0).getUnitName())
                .Unit(unit)
                .lesson(mapToLessonDTOList(syllabusContents))
                .build();
    }

    private static List<LessonDTO> mapToLessonDTOList(List<SyllabusContent> syllabusContents) {
        return syllabusContents.stream()
                .map(SyllabusContentTransformer::mapToLessonDTO)
                .collect(Collectors.toList());
    }

    private static LessonDTO mapToLessonDTO(SyllabusContent syllabusContent) {
        return LessonDTO.builder()
                .lessonName(syllabusContent.getLessonName())
                .trainingMaterial(syllabusContent.getTrainingMaterial())
                .minute(syllabusContent.getMinute())
                .deliveryType(syllabusContent.getDeliveryType())
                .method(syllabusContent.getMethod())
                .build();
    }
}
