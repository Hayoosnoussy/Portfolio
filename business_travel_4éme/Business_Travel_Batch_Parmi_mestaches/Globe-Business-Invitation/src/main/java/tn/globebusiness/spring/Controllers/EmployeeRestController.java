package tn.globebusiness.spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Post;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Services.IEmployeeService;

import java.util.List;


@RestController
public class EmployeeRestController {
    @Autowired
    IEmployeeService ies;
    @Autowired
    EmployeeRepository er;

    @PutMapping("/employee/{name}/edit")
    public Employee edit(@PathVariable("name") String name,@RequestBody Employee newEmployee) throws Exception {
        return ies.UpdateEmployee(name,newEmployee);
    }
    @GetMapping ("/employee/{name}")
    public Employee display(@PathVariable("name") String name) throws Exception {
        return ies.DisplayEmployeeData(name);
    }
    @GetMapping("/employee/{name}/posts")
    public List<Post> DisplayPosts(@PathVariable("name")  String name,Long employeeId) throws Exception {
            return ies.DisplayPostHistory(name,employeeId);
   }
}
