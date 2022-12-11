package com.example.TRPOhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrpOhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrpOhomeApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(EmployeeService employeeService, PasswordEncoder encoder) {
        return args -> {
            employeeService.save(new Employee("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));
        };
    }*/
    /*@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }*/
}
