package za.co.csg.assessment.rest;


import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.csg.assessment.model.Students;
import za.co.csg.assessment.repository.StudentsRepository;

import za.co.csg.assessment.rest.util.HeaderUtil;
import za.co.csg.assessment.rest.util.PaginationUtil;


import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentsController {

    private final StudentImpl studentService;

    public StudentsController(StudentImpl studentService) {
        this.studentService = studentService;
    }

    @Autowired
    private StudentsRepository studentsRepository;

    @GetMapping("/students")
    public List<Students> getStudents() {
        return studentsRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public EntityModel<Students> getStudent(@PathVariable long id) throws Exception {
        Optional<Students> student = studentsRepository.findById(id);

        if (!student.isPresent())
            throw new Exception("Student not found");
        EntityModel<Students> studentFound = EntityModel.of(student.get());

        return studentFound;

    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentsRepository.deleteById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> addStudent(@RequestBody Students student) {
        Students savedStudent = studentsRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Students student, @PathVariable long id) {

        Optional<Students> studentOptional = studentsRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentsRepository.save(student);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("students/students-report-by-date-range/{className}/{dateRange}")
    public ResponseEntity<List<Students>> getStudentExtract(@PathVariable String className, @PathVariable String dateRange, @ApiParam Pageable pageable) {
        Page<Students> page=null;
            page= studentService.studentExtract(pageable, className, dateRange);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, " ");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
