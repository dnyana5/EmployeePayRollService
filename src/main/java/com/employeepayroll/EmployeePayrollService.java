package com.employeepayroll;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {


    private IOService ioService;

    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    private List<EmployeePayrollData> employeePayrollList;
    public EmployeePayrollService() {
    }
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee id");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee name");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee salary");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
        System.out.println("\nWriting Employee Payroll Roaster to Console\n" +employeePayrollList);
        else if(ioService.equals(IOService.FILE_IO))
         new EmployeePayrollFileIOService().writeData(employeePayrollList);
    }
    long readEmployeePayrollData(IOService ioService) {
        this.ioService = ioService;
        if (ioService.equals(EmployeePayrollService.IOService.FILE_IO))
            this.employeePayrollList = new EmployeePayrollFileIOService().readData();
        return employeePayrollList.size();
    }


    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIOService().printData();

    }
    public long countEntries(EmployeePayrollService.IOService ioService) {
        if (ioService.equals(EmployeePayrollService.IOService.FILE_IO))
            return new EmployeePayrollFileIOService().countEntries();
        return 0;
    }

}

