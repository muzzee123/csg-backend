package za.co.csg.assessment.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.csg.assessment.model.Students;
import za.co.csg.assessment.repository.StudentsRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
@Transactional
public class StudentImpl {

    private final StudentsRepository studentsRepository;

    public StudentImpl(StudentsRepository studentsRepository){
        this.studentsRepository=studentsRepository;

    }

    @PostMapping("/students-report-by-date-range")
    public Page<Students> studentExtract(Pageable pageable, String className, String dateRange) {
        if (dateRange.equals("Today")) {
            return new PageImpl<>(studentsRepository.findAllByClassName(null, className.trim())
                    .filter(item -> item.getCreatedDate().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                    .collect(Collectors.toList()));
        }

        if (dateRange.equals("90 Days")) {
            return new PageImpl<>(studentsRepository.findAllByClassName(null, className.trim())
                    .filter(item -> !item.getCreatedDate().isBefore(LocalDateTime.now().minusMonths(3)))
                    .collect(Collectors.toList()));
        }

        return new PageImpl<>(new ArrayList<>());
    }
}
